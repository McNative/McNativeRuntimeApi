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

import net.pretronic.libraries.logging.bridge.JdkPretronicLogger;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.mcnative.bukkit.event.McNativeBridgeEventHandler;
import org.mcnative.bukkit.network.BungeeCordProxyNetwork;
import org.mcnative.bukkit.player.BukkitPlayerManager;
import org.mcnative.bukkit.player.connection.BukkitChannelInjector;
import org.mcnative.bukkit.plugin.BukkitPluginManager;
import org.mcnative.bukkit.plugin.command.BukkitCommandManager;
import org.mcnative.bukkit.plugin.event.BukkitEventBus;
import org.mcnative.bukkit.serviceprovider.placeholder.PlaceHolderApiProvider;
import org.mcnative.common.McNative;
import org.mcnative.common.network.Network;
import org.mcnative.common.serviceprovider.placeholder.PlaceholderProvider;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class McNativeLauncher {

    private static Plugin PLUGIN;

    private static PluginManager originalManager;

    public static Plugin getPlugin() {
        return PLUGIN;
    }

    public static void launchMcNative(){
        launchMcNativeInternal(createDummyPlugin());
    }

    public static void launchMcNativeInternal(Plugin plugin){
        if(McNative.isAvailable()) return;
        PLUGIN = plugin;
        Logger logger = Bukkit.getLogger();
        logger.info(McNative.CONSOLE_PREFIX+"McNative is starting, please wait...");

        if(!McNativeBukkitConfiguration.load(new JdkPretronicLogger(logger),new File("plugins/McNative/"))) return;

        BukkitPluginManager pluginManager = new BukkitPluginManager();
        pluginManager.inject();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised and injected plugin manager.");

        BukkitEventBus eventBus = new BukkitEventBus(GeneralUtil.getDefaultExecutorService(),pluginManager,getPlugin());//@Todo add better executor
        BukkitCommandManager commandManager = new BukkitCommandManager(pluginManager);
        BukkitPlayerManager playerManager = new BukkitPlayerManager();

        BukkitService localService = new BukkitService(commandManager,playerManager,eventBus);
        Network network = loadNetwork();
        BukkitMcNative instance = new BukkitMcNative(pluginManager,playerManager,localService,network);
        McNative.setInstance(instance);
        instance.registerDefaultProviders();
        instance.registerDefaultCommands();

        BukkitChannelInjector injector = new BukkitChannelInjector();

        new McNativeBridgeEventHandler(injector,eventBus,playerManager);
        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten default bukkit events.");

        injector.injectChannelInitializer();
        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten the channel initializer.");

        logger.info(McNative.CONSOLE_PREFIX+"McNative successfully started.");

        registerDependencyHooks(pluginManager,playerManager);
    }

    public static void shutdown(){
        if(!McNative.isAvailable()) return;
        McNative instance = McNative.getInstance();

        instance.getLogger().shutdown();
        instance.getScheduler().shutdown();
        instance.getExecutorService().shutdown();
        instance.getPluginManager().shutdown();

        Logger logger = Bukkit.getLogger();
        logger.info(McNative.CONSOLE_PREFIX+"McNative is stopping, please wait...");
    }

    private static Network loadNetwork(){
        if(!Bukkit.getOnlineMode()){//@Todo add configuration
            return new BungeeCordProxyNetwork();
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
        //@Todo add version
        McNativeDummyPlugin plugin = new McNativeDummyPlugin("@Todo - Init version info");
        List<Plugin> plugins = (List<Plugin>) ReflectionUtil.getFieldValue(Bukkit.getPluginManager(),"plugins");
        plugins.add(plugin);
        return plugin;
    }
}
