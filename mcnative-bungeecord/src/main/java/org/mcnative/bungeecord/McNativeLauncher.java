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
import net.md_5.bungee.api.plugin.PluginManager;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.internal.event.player.McNativeBridgeEventHandler;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.plugin.McNativeBungeePluginManager;
import org.mcnative.common.McNative;
import org.mcnative.common.event.player.login.MinecraftPlayerLoginEvent;
import org.mcnative.proxy.ProxyService;

import java.util.logging.Logger;

public class McNativeLauncher {

    public static void launchMcNative(){
        Logger logger = ProxyServer.getInstance().getLogger();
        if(McNative.isAvailable()) return;
        logger.info(McNative.CONSOLE_PREFIX+"McNative is starting, please wait...");

        ProxyServer proxy = ProxyServer.getInstance();

        BungeeCordPlayerManager playerManager = new BungeeCordPlayerManager();
        BungeeCordService instance = new BungeeCordService(playerManager);
        McNative.setInstance(instance);

        proxy.setConfigurationAdapter(new McNativeConfigurationAdapter(instance.getServers(),proxy.getConfigurationAdapter()));
        logger.info(McNative.CONSOLE_PREFIX+"McNative has overwritten the configuration adapter.");

        //Override plugin manager
        PluginManager originalPluginManager = ReflectionUtil.getFieldValue(proxy,"pluginManager",PluginManager.class);
        McNativeBungeePluginManager pluginManager = new McNativeBungeePluginManager(originalPluginManager,instance.getEventBus());
        ReflectionUtil.changeFieldValue(proxy,"pluginManager",pluginManager);
        new McNativeBridgeEventHandler(pluginManager,instance.getEventBus(),playerManager);
        logger.info(McNative.CONSOLE_PREFIX+"McNative initialised plugin manager.");

        //ProxyServer.getInstance().getPluginManager().registerListener(null,new PlayerListener(null));

        //override registry

        //initialise connection handlers

        logger.info(McNative.CONSOLE_PREFIX+"McNative successfully started.");

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            test();
        }).start();
    }

    public static void test(){
        try{
            ProxyService.getInstance().getEventBus().subscribe(ObjectOwner.SYSTEM, MinecraftPlayerLoginEvent.class, event -> {
                System.out.println("Hallo "+event.getPlayer().getName());
                System.out.println("UID: "+event.getPlayer().getUniqueId());
            });
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /*
    Plugin integration
    Command integration
    Messaging integration
    Handler integrations (Permission / Punishment / Whitelist / Connect / Reconnect)
    Player integration
     */
}
