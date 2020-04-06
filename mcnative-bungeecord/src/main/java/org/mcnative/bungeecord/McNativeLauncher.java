/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.08.19, 19:22
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

package org.mcnative.bungeecord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.api.plugin.PluginManager;
import net.pretronic.libraries.event.DefaultEventBus;
import net.pretronic.libraries.logging.bridge.JdkPretronicLogger;
import net.pretronic.libraries.plugin.description.PluginVersion;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import net.pretronic.libraries.utility.reflect.UnsafeInstanceCreator;
import org.mcnative.bungeecord.internal.event.McNativeBridgeEventHandler;
import org.mcnative.bungeecord.network.BungeecordProxyNetwork;
import org.mcnative.bungeecord.network.cloudnet.v2.CloudNetV2Network;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.plugin.BungeeCordPluginManager;
import org.mcnative.bungeecord.plugin.McNativeEventBus;
import org.mcnative.bungeecord.plugin.command.BungeeCordCommandManager;
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.common.McNative;
import org.mcnative.common.network.Network;
import org.mcnative.common.protocol.packet.DefaultPacketManager;
import org.mcnative.proxy.ProxyService;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class McNativeLauncher {

    private static Plugin PLUGIN;

    public static Plugin getPlugin() {
        return PLUGIN;
    }

    public static void launchMcNative(){
        launchMcNativeInternal(setupDummyPlugin());
    }

    public static void launchMcNativeInternal(Plugin plugin){
        if(McNative.isAvailable()) return;
        PluginVersion version = PluginVersion.ofImplementation(McNativeLauncher.class);
        PLUGIN = plugin;
        Logger logger = ProxyServer.getInstance().getLogger();
        logger.info(McNative.CONSOLE_PREFIX+"McNative is starting, please wait...");
        logger.info(McNative.CONSOLE_PREFIX+"Version: "+version.getName());
        ProxyServer proxy = ProxyServer.getInstance();

        if(!McNativeBungeeCordConfiguration.load(new JdkPretronicLogger(logger),new File("plugins/McNative/"))) return;

        BungeeCordServerMap serverMap = new BungeeCordServerMap();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised and injected server map.");

        BungeeCordPluginManager pluginManager = new BungeeCordPluginManager();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised plugin manager.");

        BungeeCordPlayerManager playerManager = new BungeeCordPlayerManager();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised player manager.");

        BungeeCordCommandManager commandManager = new BungeeCordCommandManager(pluginManager,ProxyServer.getInstance().getPluginManager());
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised and injected command manager.");

        BungeeCordService localService = new BungeeCordService(new DefaultPacketManager(),commandManager,playerManager,new DefaultEventBus(),serverMap);
        BungeeCordMcNative instance = new BungeeCordMcNative(version,pluginManager,playerManager,null, localService);
        McNative.setInstance(instance);
        instance.setNetwork(setupNetwork(logger,localService,instance.getExecutorService(),serverMap));

        instance.registerDefaultProviders();
        instance.registerDefaultCommands();

        proxy.setConfigurationAdapter(new McNativeConfigurationAdapter(serverMap,proxy.getConfigurationAdapter()));
        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten the configuration adapter.");


        McNativeEventBus eventBus = new McNativeEventBus(localService.getEventBus());
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised and injected event bus.");

        new McNativeBridgeEventHandler(eventBus,localService.getEventBus(),playerManager,serverMap);
        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten default bungeecord events.");

        instance.setReady(true);
        logger.info(McNative.CONSOLE_PREFIX+"McNative successfully started.");
    }

    private static Network setupNetwork(Logger logger,ProxyService proxy,ExecutorService executor, BungeeCordServerMap serverMap){
        if(ProxyServer.getInstance().getPluginManager().getPlugin("CloudNetAPI") != null){
            logger.info(McNative.CONSOLE_PREFIX+"(Network) Initialized CloudNet V2 networking technology");
            return new CloudNetV2Network(executor);
        }else{
            logger.info(McNative.CONSOLE_PREFIX+"(Network) Initialized BungeeCord networking technology");
            return new BungeecordProxyNetwork(proxy,executor,serverMap);
        }
    }

    @SuppressWarnings("unchecked")
    private static Plugin setupDummyPlugin(){
        PluginDescription description = new PluginDescription();
        description.setName("McNative");
        description.setVersion(McNativeLauncher.class.getPackage().getImplementationVersion());//@Todo update
        description.setAuthor("Pretronic and McNative contributors");
        description.setMain("reflected");

        Plugin plugin = UnsafeInstanceCreator.newInstance(Plugin.class);
        ReflectionUtil.invokeMethod(plugin,"init"
                ,new Class[]{ProxyServer.class,PluginDescription.class}
                ,new Object[]{ProxyServer.getInstance(),description});

        Map<String, Plugin> plugins = ReflectionUtil.getFieldValue(PluginManager.class,ProxyServer.getInstance().getPluginManager(),"plugins", Map.class);
        plugins.put(description.getName(),plugin);
        return plugin;
    }

}
