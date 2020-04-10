/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 21.03.20, 13:56
 * @web %web%
 *
 * The McNative Project is under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.mcnative.bukkit;

import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.configuration.DefaultCommandConfiguration;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.DocumentRegistry;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.logging.bridge.JdkPretronicLogger;
import net.pretronic.libraries.plugin.description.PluginVersion;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.mcnative.bukkit.event.McNativeBridgeEventHandler;
import org.mcnative.bukkit.network.BungeeCordProxyNetwork;
import org.mcnative.bukkit.network.cloudnet.v2.CloudNetV2Network;
import org.mcnative.bukkit.network.cloudnet.v3.CloudNetV3Network;
import org.mcnative.bukkit.player.BukkitPlayerManager;
import org.mcnative.bukkit.player.connection.BukkitChannelInjector;
import org.mcnative.bukkit.plugin.BukkitPluginManager;
import org.mcnative.bukkit.plugin.command.BukkitCommandManager;
import org.mcnative.bukkit.plugin.event.BukkitEventBus;
import org.mcnative.bukkit.serviceprovider.economy.ServiceListener;
import org.mcnative.bukkit.serviceprovider.placeholder.PlaceHolderApiProvider;
import org.mcnative.common.McNative;
import org.mcnative.common.network.Network;
import org.mcnative.common.serviceprovider.placeholder.PlaceholderProvider;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class McNativeLauncher {

    private static Plugin PLUGIN;

    private static BukkitPluginManager PLUGIN_MANAGER;
    private static BukkitCommandManager COMMAND_MANAGER;
    private static BukkitChannelInjector CHANNEL_INJECTOR;

    public static Plugin getPlugin() {
        return PLUGIN;
    }

    public static void launchMcNative(){
        launchMcNativeInternal(createDummyPlugin());
    }

    public static void launchMcNativeInternal(Plugin plugin){
        if(McNative.isAvailable()) return;
        PluginVersion version = PluginVersion.ofImplementation(McNativeLauncher.class);

        PLUGIN = plugin;
        Logger logger = Bukkit.getLogger();
        logger.info(McNative.CONSOLE_PREFIX+"McNative is starting, please wait...");
        logger.info(McNative.CONSOLE_PREFIX+"Version: "+version.getName());

        DocumentRegistry.getDefaultContext().registerMappingAdapter(CommandConfiguration.class, DefaultCommandConfiguration.class);

        if(!McNativeBukkitConfiguration.load(new JdkPretronicLogger(logger),new File("plugins/McNative/"))) return;

        BukkitPluginManager pluginManager = new BukkitPluginManager();
        pluginManager.inject();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised and injected plugin manager.");

        BukkitEventBus eventBus = new BukkitEventBus(GeneralUtil.getDefaultExecutorService(),pluginManager,getPlugin());//@Todo add better executor
        BukkitCommandManager commandManager = new BukkitCommandManager(pluginManager);
        BukkitPlayerManager playerManager = new BukkitPlayerManager();

        BukkitService localService = new BukkitService(commandManager,playerManager,eventBus);
        BukkitMcNative instance = new BukkitMcNative(version,pluginManager,playerManager,localService,null);

        McNative.setInstance(instance);
        instance.setNetwork(setupNetwork(logger,instance.getExecutorService()));

        BukkitChannelInjector injector = new BukkitChannelInjector();
        commandManager.inject();

        instance.registerDefaultProviders();
        instance.registerDefaultCommands();
        instance.registerDescribers();

        new McNativeBridgeEventHandler(injector,eventBus,playerManager);

        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten default bukkit events.");

        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten the channel initializer.");

        registerDependencyHooks(pluginManager,playerManager);

        PLUGIN_MANAGER = pluginManager;
        COMMAND_MANAGER = commandManager;
        CHANNEL_INJECTOR = injector;

        instance.getScheduler().createTask(ObjectOwner.SYSTEM).delay(1500, TimeUnit.MILLISECONDS).execute(() ->{
            injector.injectChannelInitializer();
            playerManager.loadConnectedPlayers();
            instance.setReady(true);
        }).addListener(future -> {
            if(future.isFailed())future.getThrowable().printStackTrace();
              instance.setReady(true);
        });

        registerDefaultListener(eventBus, pluginManager);


        playerManager.loadConnectedPlayers();
        //instance.setReady(true);

        //Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ALL);

        logger.info(McNative.CONSOLE_PREFIX+"McNative successfully started.");
    }

    private static void registerDefaultListener(EventBus eventBus, BukkitPluginManager pluginManager) {
        eventBus.subscribe(McNative.getInstance(), new ServiceListener(pluginManager));
    }

    public static void shutdown(){
        if(!McNative.isAvailable()) return;
        Logger logger = Bukkit.getLogger();
        logger.info(McNative.CONSOLE_PREFIX+"McNative is stopping, please wait...");
        McNative instance = McNative.getInstance();

        if(instance != null){
            instance.getLogger().shutdown();
            instance.getScheduler().shutdown();
            instance.getExecutorService().shutdown();
            instance.getPluginManager().shutdown();
        }

        if(PLUGIN_MANAGER != null) PLUGIN_MANAGER.reset();
        if(COMMAND_MANAGER != null) COMMAND_MANAGER.reset();
        if(CHANNEL_INJECTOR != null) CHANNEL_INJECTOR.reset();

        McNative.setInstance(null);

        logger.info(McNative.CONSOLE_PREFIX+"McNative successfully stopped.");
    }

    private static Network setupNetwork(Logger logger,ExecutorService executor){
        if(Bukkit.getPluginManager().getPlugin("CloudNetAPI") != null){
            logger.info(McNative.CONSOLE_PREFIX+"(Network) Initialized CloudNet V2 networking technology");
            return new CloudNetV2Network(executor);
        }else if(Bukkit.getPluginManager().getPlugin("CloudNet-Bridge") != null){
            logger.info(McNative.CONSOLE_PREFIX+"(Network) Initialized CloudNet V3 networking technology");
            return new CloudNetV3Network(executor);
        }else if(!Bukkit.getOnlineMode()){
            File spigotConfigFile = new File("spigot.yml");
            if(spigotConfigFile.exists()){
                Document spigotConfig = DocumentFileType.YAML.getReader().read(spigotConfigFile);
                if(!spigotConfig.getBoolean("settings.bungeecord")){
                    logger.warning(McNative.CONSOLE_PREFIX+"(Network) Online mode is enabled, but BungeeCord is disabled in spigot.yml");
                    logger.warning(McNative.CONSOLE_PREFIX+"(Network) If you are using BungeeCord, make sure to set bungeecord to 'true' in your spigt.yml config");
                    return null;
                }
            }
            logger.info(McNative.CONSOLE_PREFIX+"(Network) Initialized BungeeCord networking technology");
            return new BungeeCordProxyNetwork(executor);
        }
        return null;
    }

    private static void registerDependencyHooks(BukkitPluginManager pluginManager, BukkitPlayerManager playerManager){
        if(Bukkit.getPluginManager().getPlugin("PlaceHolderApi") != null){
            McNative.getInstance().getRegistry().registerService(McNative.getInstance(), PlaceholderProvider.class
                    ,new PlaceHolderApiProvider(playerManager,pluginManager));
        }
    }

    @SuppressWarnings("unchecked")
    private static Plugin createDummyPlugin(){
        McNativeDummyPlugin plugin = new McNativeDummyPlugin(McNativeLauncher.class.getPackage().getImplementationVersion());
        List<Plugin> plugins = (List<Plugin>) ReflectionUtil.getFieldValue(Bukkit.getPluginManager(),"plugins");
        plugins.add(plugin);
        return plugin;
    }
}
