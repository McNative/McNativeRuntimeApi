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
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.plugin.McNativeBungeePluginManager;
import org.mcnative.common.McNative;
import org.mcnative.common.event.player.login.MinecraftPlayerPendingLoginEvent;
import org.mcnative.common.player.Title;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.TextBuilder;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.proxy.ProxiedPlayer;
import org.mcnative.proxy.ProxyService;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class McNativeLauncher {

    public static void launchMcNative(){
        Logger logger = ProxyServer.getInstance().getLogger();
        if(McNative.isAvailable()) return;
        logger.info("McNative is starting, please wait...");

        ProxyServer proxy = ProxyServer.getInstance();

        BungeeCordPlayerManager playerManager = new BungeeCordPlayerManager();
        BungeeCordService instance = new BungeeCordService(playerManager);
        McNative.setInstance(instance);

        proxy.setConfigurationAdapter(new McNativeConfigurationAdapter(instance.getServers(),proxy.getConfigurationAdapter()));
        logger.info("McNative has overwritten the configuration adapter.");

        //Override plugin manager
        PluginManager oldPluginManager = ReflectionUtil.getFieldValue(proxy,"pluginManager",PluginManager.class);
        ReflectionUtil.changeFieldValue(proxy,"pluginManager",new McNativeBungeePluginManager(oldPluginManager,instance.getEventManager(),playerManager));
        logger.info("McNative initialised plugin manager.");

        //ProxyServer.getInstance().getPluginManager().registerListener(null,new PlayerListener(null));

        //override registry

        //initialise connection handlers

        logger.info("McNative successfully started.");

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
            System.out.println("Lobby -> "+ProxyService.getInstance().getServer("lobby").getAddress());
            ProxyService.getInstance().getEventManager().subscribe(ObjectOwner.SYSTEM, MinecraftPlayerPendingLoginEvent.class, event -> {
                System.out.println("Hallo "+event.getConnection().getName());
                System.out.println("UID: "+event.getConnection().getUniqueId());
                System.out.println(event.getConnection().isOnlineMode());
                //event.setCancelReason(Text.newBuilder().color(TextColor.RED).text("Hey from McNative").build());

                ProxyServer.getInstance().getScheduler().schedule(ProxyServer.getInstance().getPluginManager().getPlugin("McNative"),()->{
                    ProxiedPlayer player = ProxyService.getInstance().getPlayerManager().getOnlinePlayer(event.getConnection().getUniqueId());

                },3,TimeUnit.SECONDS);
            });
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /*
    Plugin integration
    Event integration
    Command integration
    Messaging integration
    Handler integrations (Permission / Punishment / Whitelist / Connect / Reconnect)
    Player integration
     */
}
