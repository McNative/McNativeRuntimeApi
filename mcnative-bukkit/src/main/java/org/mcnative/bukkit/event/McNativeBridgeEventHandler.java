/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.02.20, 14:08
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

package org.mcnative.bukkit.event;

import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.mcnative.bukkit.event.player.*;
import org.mcnative.bukkit.player.BukkitPendingConnection;
import org.mcnative.bukkit.player.BukkitPlayer;
import org.mcnative.bukkit.player.BukkitPlayerManager;
import org.mcnative.bukkit.player.connection.BukkitChannelInjector;
import org.mcnative.bukkit.player.connection.ChannelConnection;
import org.mcnative.bukkit.plugin.event.BukkitEventBus;
import org.mcnative.bukkit.plugin.event.McNativeHandlerList;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.event.player.MinecraftPlayerLogoutEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerLoginEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerPostLoginEvent;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.service.entity.living.Player;
import org.mcnative.service.event.player.MinecraftPlayerJoinEvent;
import org.mcnative.service.event.player.MinecraftPlayerWorldChangedEvent;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class McNativeBridgeEventHandler {

    private final BukkitEventBus eventBus;
    private final BukkitChannelInjector injector;
    private final BukkitPlayerManager playerManager;
    private final Map<UUID,BukkitPendingConnection> pendingConnections;

    public McNativeBridgeEventHandler(BukkitChannelInjector injector, BukkitEventBus eventBus, BukkitPlayerManager playerManager) {
        this.injector = injector;
        this.eventBus = eventBus;
        this.playerManager = playerManager;

        this.pendingConnections = new ConcurrentHashMap<>();

        setup();
    }

    private void setup(){
        //Pre Login
        eventBus.registerMappedClass(MinecraftPlayerLoginEvent.class, AsyncPlayerPreLoginEvent.class);
        eventBus.registerManagedEvent(AsyncPlayerPreLoginEvent.class, this::handlePreLoginEvent);

        //Login
        eventBus.registerMappedClass(MinecraftPlayerLoginEvent.class, PlayerLoginEvent.class);
        eventBus.registerManagedEvent(PlayerLoginEvent.class, this::handleLoginEvent);

        //Join
        eventBus.registerMappedClass(MinecraftPlayerJoinEvent.class, PlayerJoinEvent.class);
        eventBus.registerManagedEvent(PlayerJoinEvent.class, this::handleJoinEvent);

        //World Changed
        eventBus.registerMappedClass(MinecraftPlayerWorldChangedEvent.class, PlayerChangedWorldEvent.class);
        eventBus.registerManagedEvent(PlayerChangedWorldEvent.class, this::handleWorldChangedEvent);

        //Logout
        eventBus.registerMappedClass(MinecraftPlayerLogoutEvent.class, PlayerQuitEvent.class);
        eventBus.registerManagedEvent(PlayerQuitEvent.class, this::handleLogoutEvent);

    }

    private void handleServerListPing(McNativeHandlerList handler, ServerListPingEvent event){

    }

    private void handlePreLoginEvent(McNativeHandlerList handler, AsyncPlayerPreLoginEvent event) {
        ChannelConnection connection0 = injector.findConnection(event.getUniqueId());
        if(connection0 == null){
            event.setKickMessage("§cAn error occurred.");//@Todo configurable error message
            return;
        }

        BukkitPendingConnection connection = new BukkitPendingConnection(connection0.getChannel()
                ,connection0.getGameProfile(),(InetSocketAddress) connection0.getChannel().remoteAddress()
                ,new InetSocketAddress(connection0.getHostname(),connection0.getPort())
                ,connection0.getProtocolVersion());

        BukkitPendingLoginEvent mcnativeEvent = new BukkitPendingLoginEvent(event,connection);
        handler.callEvents(mcnativeEvent,event);

        if(event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED){
            if(mcnativeEvent.hasMessageChanged()){
                connection.disconnect(mcnativeEvent.getCancelReason(),mcnativeEvent.getCancelReasonVariables());
            }
        }else{
            this.pendingConnections.put(connection.getUniqueId(),connection);
        }
    }

    private void handleLoginEvent(McNativeHandlerList handler, PlayerLoginEvent event) {
        BukkitPendingConnection connection = this.pendingConnections.remove(event.getPlayer().getUniqueId());
        if(connection == null){
            event.setKickMessage("§cAn error occurred.");//@Todo configurable error message
            return;
        }

        PlayerDataProvider dataProvider = McNative.getInstance().getRegistry().getService(PlayerDataProvider.class);
        MinecraftPlayerData data = dataProvider.getPlayerData(event.getPlayer().getUniqueId());
        if(data == null){
            long now = System.currentTimeMillis();
            data = dataProvider.createPlayerData(
                    event.getPlayer().getName()
                    ,event.getPlayer().getUniqueId()
                    ,-1,now,now
                    ,connection.getGameProfile());
        }else data.updateLoginInformation(connection.getName(),connection.getGameProfile(),System.currentTimeMillis());
        BukkitPlayer player = new BukkitPlayer(event.getPlayer(),connection,data);

        BukkitLoginEvent mcnativeEvent = new BukkitLoginEvent(event,connection,player);
        handler.callEvents(mcnativeEvent,event);

        if(event.getResult() != PlayerLoginEvent.Result.ALLOWED){
            if(mcnativeEvent.hasMessageChanged()){
                connection.disconnect(mcnativeEvent.getCancelReason(),mcnativeEvent.getCancelReasonVariables());
            }
        }else{
            connection.setState(ConnectionState.GAME);

            MinecraftPlayerPostLoginEvent postLoginEvent = new BukkitPostLoginEvent(player);
            McNative.getInstance().getLocal().getEventBus().callEvent(MinecraftPlayerPostLoginEvent.class,postLoginEvent);

            playerManager.registerPlayer(player);
        }
    }

    private void handleJoinEvent(McNativeHandlerList handler, PlayerJoinEvent event){
        Player player = playerManager.getMappedPlayer(event.getPlayer());
        BukkitJoinEvent mcnativeEvent = new BukkitJoinEvent(event,player);

        handler.callEvents(mcnativeEvent,event);

        if(mcnativeEvent.hasMessageChanged()){
            event.setJoinMessage(null);
            McNative.getInstance().getLocal().broadcast(mcnativeEvent.getJoinMessage(),mcnativeEvent.getJoinMessageVariables());
            //@Todo implement chat channel
        }

        if(mcnativeEvent.hasLocationChanged()){
            player.teleport(mcnativeEvent.getSpawnLocation());
        }
    }

    private void handleLogoutEvent(McNativeHandlerList handler, PlayerQuitEvent event){
        Player player = playerManager.getMappedPlayer(event.getPlayer());

        BukkitQuitEvent mcnativeEvent = new BukkitQuitEvent(event,player);
        handler.callEvents(mcnativeEvent,event);

        if(mcnativeEvent.hasMessageChanged()){
            event.setQuitMessage(null);
            McNative.getInstance().getLocal().broadcast(mcnativeEvent.getQuietMessage(),mcnativeEvent.getQuietMessageVariables());
            //@Todo implement chat channel
        }

        MinecraftPlayerLogoutEvent logoutEvent = new BukkitPlayerLogoutEvent(player);
        McNative.getInstance().getLocal().getEventBus().callEvent(MinecraftPlayerLogoutEvent.class,logoutEvent);
    }

    private void handleWorldChangedEvent(McNativeHandlerList handler,PlayerChangedWorldEvent event){
        BukkitPlayer player = playerManager.getMappedPlayer(event.getPlayer());
        player.setWorld(new BukkitWorld(event.getPlayer().getWorld()));//@Todo get world from pool
        BukkitWorld from = new BukkitWorld(event.getFrom());
        MinecraftPlayerWorldChangedEvent mcnativeEvent = new BukkitWorldChangedEvent(player,from);
        handler.callEvents(event,mcnativeEvent);
    }



    private void handleServerReload(McNativeHandlerList handler, ServerLoadEvent event){
        if(event.getType() == ServerLoadEvent.LoadType.RELOAD){

        }else{
            handler.callEvents(event);
        }
    }

}
