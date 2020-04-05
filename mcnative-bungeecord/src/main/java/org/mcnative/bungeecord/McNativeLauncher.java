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
import net.pretronic.libraries.utility.reflect.ReflectException;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.internal.event.McNativeBridgeEventHandler;
import org.mcnative.bungeecord.network.BungeecordProxyNetwork;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.plugin.BungeeCordPluginManager;
import org.mcnative.bungeecord.plugin.McNativeEventBus;
import org.mcnative.bungeecord.plugin.command.BungeeCordCommandManager;
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.common.McNative;
import org.mcnative.common.protocol.packet.DefaultPacketManager;
import org.mcnative.proxy.McNativeProxyConfiguration;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
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

        if(!McNativeProxyConfiguration.load(new JdkPretronicLogger(logger),new File("plugins/McNative/"))) return;

        BungeeCordServerMap serverMap = new BungeeCordServerMap();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised and injected server map.");

        BungeeCordPluginManager pluginManager = new BungeeCordPluginManager();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised plugin manager.");

        BungeeCordPlayerManager playerManager = new BungeeCordPlayerManager();
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised player manager.");

        BungeeCordCommandManager commandManager = new BungeeCordCommandManager(pluginManager,ProxyServer.getInstance().getPluginManager());
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised and injected command manager.");

        BungeeCordService localService = new BungeeCordService(new DefaultPacketManager(),commandManager,playerManager,new DefaultEventBus(),serverMap);
        BungeeCordMcNative instance = new BungeeCordMcNative(version,pluginManager,playerManager,new BungeecordProxyNetwork(localService), localService);
        McNative.setInstance(instance);
        instance.registerDefaultProviders(serverMap);
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

    @SuppressWarnings("unchecked")
    private static Plugin setupDummyPlugin(){
        PluginDescription description = new PluginDescription();
        description.setName("McNative");
        description.setVersion(McNativeLauncher.class.getPackage().getImplementationVersion());//@Todo update
        description.setAuthor("Pretronic and McNative contributors");
        description.setMain("reflected");

        System.out.println("CHECK 1"+McNativeLauncher.class.getClassLoader().getClass().getName());

        ClassLoader loader = new URLClassLoader(new URL[]{},McNativeLauncher.class.getClassLoader());

        Plugin plugin; //new DummyPlugin(description);
        try {
            plugin = (Plugin) loader.loadClass(DummyPlugin.class.getName()).getConstructor(PluginDescription.class).newInstance(description);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new ReflectException(e);
        }
        /*
        ReflectionUtil.invokeMethod(plugin,"init",new Class[]{ProxyServer.class,PluginDescription.class}
                ,new Object[]{ProxyServer.getInstance(),description});
         */

        Map<String, Plugin> plugins = ReflectionUtil.getFieldValue(PluginManager.class,ProxyServer.getInstance().getPluginManager(),"plugins", Map.class);
        plugins.put(description.getName(),plugin);
        return plugin;
    }

    private static class DummyPlugin extends Plugin{

        public DummyPlugin(PluginDescription description) {
            super(ProxyServer.getInstance(),description);
            System.out.println("CHECK 2"+McNativeLauncher.class.getClassLoader().getClass().getName());
        }
    }

}
