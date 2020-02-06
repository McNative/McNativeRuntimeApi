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
import net.prematic.libraries.event.DefaultEventBus;
import net.prematic.libraries.logging.bridge.JdkPrematicLogger;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.internal.event.McNativeBridgeEventHandler;
import org.mcnative.bungeecord.network.LocalProxyNetwork;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.plugin.BungeeCordPluginManager;
import org.mcnative.bungeecord.plugin.McNativeBungeePluginManager;
import org.mcnative.bungeecord.plugin.command.BungeeCordCommandManager;
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.common.McNative;
import org.mcnative.common.protocol.packet.DefaultPacketManager;
import org.mcnative.proxy.McNativeProxyConfiguration;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

public class McNativeLauncher {

    public static void launchMcNative(){
        launchMcNativeInternal();
        setupDummyPlugin();
    }

    public static void launchMcNativeInternal(){
        if(McNative.isAvailable()) return;
        Logger logger = ProxyServer.getInstance().getLogger();
        logger.info(McNative.CONSOLE_PREFIX+"McNative is starting, please wait...");
        ProxyServer proxy = ProxyServer.getInstance();

        if(!McNativeProxyConfiguration.load(new JdkPrematicLogger(logger),new File("plugins/McNative/"))) return;

        BungeeCordServerMap serverMap = new BungeeCordServerMap();
        BungeeCordPluginManager pluginManager = new BungeeCordPluginManager();
        BungeeCordPlayerManager playerManager = new BungeeCordPlayerManager();
        BungeeCordCommandManager commandManager = new BungeeCordCommandManager(pluginManager,ProxyServer.getInstance().getPluginManager());

        BungeeCordService localService = new BungeeCordService(new DefaultPacketManager(),commandManager,playerManager,new DefaultEventBus(),serverMap);
        BungeeCordMcNative instance = new BungeeCordMcNative(pluginManager,playerManager,new LocalProxyNetwork(localService), localService);
        McNative.setInstance(instance);
        instance.registerDefaultProviders();

        proxy.setConfigurationAdapter(new McNativeConfigurationAdapter(serverMap,proxy.getConfigurationAdapter()));
        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten the configuration adapter.");


        //Override plugin manager
        PluginManager originalPluginManager = proxy.getPluginManager();
        pluginManager.inject(originalPluginManager);
        McNativeBungeePluginManager newPluginManager = new McNativeBungeePluginManager(originalPluginManager,localService.getEventBus());
        pluginManager.setOriginal(newPluginManager);
        ReflectionUtil.changeFieldValue(proxy,"pluginManager",newPluginManager);
        new McNativeBridgeEventHandler(newPluginManager,localService.getEventBus(),playerManager,serverMap);
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised plugin manager.");


        //Override command manager
        commandManager.inject();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised command manager.");


        logger.info(McNative.CONSOLE_PREFIX+"McNative successfully started.");
    }

    @SuppressWarnings("unchecked")
    private static void setupDummyPlugin(){
        PluginDescription description = new PluginDescription();
        description.setName("McNative");
        description.setVersion("1.0.0");//@Todo update
        description.setAuthor("Pretronic and McNative contributors");
        description.setMain("reflected");

        Plugin plugin = new Plugin();

        ReflectionUtil.invokeMethod(plugin,"init",new Class[]{ProxyServer.class,PluginDescription.class}
                ,new Object[]{ProxyServer.getInstance(),description});
        Map<String, Plugin> plugins = ReflectionUtil.getFieldValue(PluginManager.class,ProxyServer.getInstance().getPluginManager(),"plugins", Map.class);
        plugins.put(description.getName(),plugin);
    }

}
