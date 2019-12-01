/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.11.19, 15:56
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

package org.mcnative.bungeecord.internal.event.player;

import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.prematic.libraries.event.EventBus;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.player.BungeePendingConnection;
import org.mcnative.bungeecord.player.BungeeProxiedPlayer;
import org.mcnative.bungeecord.plugin.McNativeBungeePluginManager;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.event.player.MinecraftPlayerLogoutEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerLoginEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerPendingLoginEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerPostLoginEvent;
import org.mcnative.common.player.OnlineMinecraftPlayer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public final class McNativeBridgeEventHandler {

    private final McNativeBungeePluginManager pluginManager;
    private final EventBus eventBus;
    private final BungeeCordPlayerManager playerManager;
    private final Map<UUID, BungeeProxiedPlayer> pendingPlayers;

    public McNativeBridgeEventHandler(McNativeBungeePluginManager pluginManager, EventBus eventBus, BungeeCordPlayerManager playerManager) {
        this.pluginManager = pluginManager;
        this.eventBus = eventBus;
        this.playerManager = playerManager;

        this.pendingPlayers = new LinkedHashMap<>();
        setup();
    }

    private void setup(){
        //Login
        eventBus.registerMappedClass(MinecraftPlayerLoginEvent.class, LoginEvent.class);
        pluginManager.registerMangedEvent(LoginEvent.class,this::handleLogin);

        //PostLogin
        eventBus.registerMappedClass(MinecraftPlayerPostLoginEvent.class, PostLoginEvent.class);
        pluginManager.registerMangedEvent(PostLoginEvent.class,this::handlePostLogin);

        //Logout
        eventBus.registerMappedClass(MinecraftPlayerLogoutEvent.class, PlayerDisconnectEvent.class);
        pluginManager.registerMangedEvent(PlayerDisconnectEvent.class,this::handleLogout);
    }

    private void handleLogin(LoginEvent event){
        BungeePendingConnection connection = new BungeePendingConnection(event.getConnection());
        connection.setState(ConnectionState.LOGIN);

        MinecraftPlayerPendingLoginEvent pendingEvent = new BungeeMinecraftPendingLoginEvent(connection);
        eventBus.callEvent(MinecraftPlayerPendingLoginEvent.class,pendingEvent);
        if(pendingEvent.isCancelled()){
            connection.disconnect(pendingEvent.getCancelReason(),pendingEvent.getCancelReasonVariables());
            return;
        }

        BungeeProxiedPlayer player = new BungeeProxiedPlayer(connection,null,null);

        MinecraftPlayerLoginEvent loginEvent = new BungeeMinecraftLoginEvent(event,player);
        eventBus.callEvents(LoginEvent.class,event,loginEvent);

        if(loginEvent.isCancelled()){
            if(event.getCancelReasonComponents() == null){
                connection.disconnect(loginEvent.getCancelReason(),loginEvent.getCancelReasonVariables());
                event.setCancelled(false);
            }
        }else pendingPlayers.put(player.getUniqueId(),player);
    }

    private void handlePostLogin(PostLoginEvent event){
        BungeeProxiedPlayer player = pendingPlayers.remove(event.getPlayer().getUniqueId());
        player.postLogin(event.getPlayer());
        playerManager.registerPlayer(player);
        MinecraftPlayerPostLoginEvent mcNativeEvent = new BungeeMinecraftPostLoginEvent(player);
        eventBus.callEvents(PostLoginEvent.class,event,mcNativeEvent);
    }

    private void handleLogout(PlayerDisconnectEvent event){
        OnlineMinecraftPlayer player = playerManager.unregisterPlayer(event.getPlayer().getUniqueId());
        MinecraftPlayerLogoutEvent mcNativeEvent = new BungeeMinecraftLogoutEvent(player);
        eventBus.callEvents(PlayerDisconnectEvent.class,event,mcNativeEvent);
    }

}
