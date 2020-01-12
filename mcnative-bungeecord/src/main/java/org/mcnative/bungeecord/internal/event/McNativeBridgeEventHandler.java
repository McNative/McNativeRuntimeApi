/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 19:01
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

package org.mcnative.bungeecord.internal.event;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.SkinConfiguration;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.event.EventBus;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.internal.event.player.*;
import org.mcnative.bungeecord.internal.event.server.BungeeServerConnectEvent;
import org.mcnative.bungeecord.internal.event.server.BungeeServerConnectedEvent;
import org.mcnative.bungeecord.internal.event.server.BungeeServerKickEvent;
import org.mcnative.bungeecord.internal.event.server.BungeeServerSwitchEvent;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.player.BungeePendingConnection;
import org.mcnative.bungeecord.player.BungeeProxiedPlayer;
import org.mcnative.bungeecord.plugin.McNativeBungeePluginManager;
import org.mcnative.bungeecord.plugin.command.McNativeCommand;
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.event.ServerListPingEvent;
import org.mcnative.common.event.ServiceReloadedEvent;
import org.mcnative.common.event.player.MinecraftPlayerChatEvent;
import org.mcnative.common.event.player.MinecraftPlayerLogoutEvent;
import org.mcnative.common.event.player.MinecraftPlayerTabCompleteEvent;
import org.mcnative.common.event.player.MinecraftPlayerTabCompleteResponseEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerLoginEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerPendingLoginEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerPostLoginEvent;
import org.mcnative.common.event.player.settings.MinecraftPlayerSettingsChangedEvent;
import org.mcnative.common.event.server.MinecraftPlayerServerConnectEvent;
import org.mcnative.common.event.server.MinecraftPlayerServerConnectedEvent;
import org.mcnative.common.event.server.MinecraftPlayerServerKickEvent;
import org.mcnative.common.event.server.MinecraftPlayerServerSwitchEvent;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.PlayerSettings;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.common.serviceprovider.permission.Permissable;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.proxy.ProxyService;
import org.mcnative.proxy.ServerConnectHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public final class McNativeBridgeEventHandler {

    private final McNativeBungeePluginManager pluginManager;
    private final BungeeCordPlayerManager playerManager;
    private final BungeeCordServerMap serverMap;
    private final EventBus eventBus;
    private final Map<UUID, BungeeProxiedPlayer> pendingPlayers;
    private final Map<Connection,String> tabCompleteCursors;

    public McNativeBridgeEventHandler(McNativeBungeePluginManager pluginManager, EventBus eventBus, BungeeCordPlayerManager playerManager,BungeeCordServerMap serverMap) {
        this.pluginManager = pluginManager;
        this.eventBus = eventBus;
        this.playerManager = playerManager;
        this.serverMap = serverMap;

        this.pendingPlayers = new LinkedHashMap<>();
        this.tabCompleteCursors = new LinkedHashMap<>();

        setup();
    }

    private void setup(){
        //Ping
        eventBus.registerMappedClass(ServerListPingEvent.class,ProxyPingEvent.class);
        pluginManager.registerMangedEvent(ProxyPingEvent.class,this::handleProxyPing);

        //Login
        eventBus.registerMappedClass(MinecraftPlayerLoginEvent.class, LoginEvent.class);
        pluginManager.registerMangedEvent(LoginEvent.class,this::handleLogin);

        //PostLogin
        eventBus.registerMappedClass(MinecraftPlayerPostLoginEvent.class, PostLoginEvent.class);
        pluginManager.registerMangedEvent(PostLoginEvent.class,this::handlePostLogin);

        //Connect Server
        eventBus.registerMappedClass(MinecraftPlayerServerConnectEvent.class, ServerConnectEvent.class);
        pluginManager.registerMangedEvent(ServerConnectEvent.class,this::handleServerConnect);

        //Connected Server
        eventBus.registerMappedClass(MinecraftPlayerServerConnectedEvent.class, ServerConnectedEvent.class);
        pluginManager.registerMangedEvent(ServerConnectedEvent.class,this::handleServerConnected);

        //Switch Server
        eventBus.registerMappedClass(MinecraftPlayerServerSwitchEvent.class, ServerSwitchEvent.class);
        pluginManager.registerMangedEvent(ServerSwitchEvent.class,this::handleServerSwitch);

        //Server Kick
        eventBus.registerMappedClass(MinecraftPlayerServerKickEvent.class, ServerKickEvent.class);
        pluginManager.registerMangedEvent(ServerKickEvent.class,this::handleServerKick);

        //Chat event
        eventBus.registerMappedClass(MinecraftPlayerChatEvent.class, ChatEvent.class);
        pluginManager.registerMangedEvent(ChatEvent.class,this::handleChatEvent);

        //Tab complete
        eventBus.registerMappedClass(MinecraftPlayerTabCompleteEvent.class, TabCompleteEvent.class);
        pluginManager.registerMangedEvent(TabCompleteEvent.class,this::handleTabComplete);

        //Tab response
        eventBus.registerMappedClass(MinecraftPlayerTabCompleteResponseEvent.class, TabCompleteResponseEvent.class);
        pluginManager.registerMangedEvent(TabCompleteResponseEvent.class,this::handleTabCompleteResponse);

        //Logout
        eventBus.registerMappedClass(MinecraftPlayerLogoutEvent.class, PlayerDisconnectEvent.class);
        pluginManager.registerMangedEvent(PlayerDisconnectEvent.class,this::handleLogout);

        //Permission
        eventBus.registerMappedClass(org.mcnative.common.event.PermissionCheckEvent.class, PermissionCheckEvent.class);
        pluginManager.registerMangedEvent(PermissionCheckEvent.class,this::handlePermissionCheck);

        //Settings
        eventBus.registerMappedClass(MinecraftPlayerSettingsChangedEvent.class, SettingsChangedEvent.class);
        pluginManager.registerMangedEvent(SettingsChangedEvent.class,this::handleSettingsChange);

        //Reload
        eventBus.registerMappedClass(ServiceReloadedEvent.class, ProxyReloadEvent.class);
        pluginManager.registerMangedEvent(ProxyReloadEvent.class,this::handleProxyReload);
    }

    private void handleProxyPing(ProxyPingEvent event) {
        BungeeServerListPingEvent mcNativeEvent = new BungeeServerListPingEvent(event.getConnection(),event);
        ServerStatusResponse defaultResponse = ProxyService.getInstance().getStatusResponse();
        if(defaultResponse != null) mcNativeEvent.setResponse(defaultResponse);
        eventBus.callEvents(ProxyPingEvent.class,event,mcNativeEvent);
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

        PlayerDataProvider dataProvider = McNative.getInstance().getRegistry().getService(PlayerDataProvider.class);
        MinecraftPlayerData data = dataProvider.getPlayerData(event.getConnection().getUniqueId());
        if(data == null){
            long now = System.currentTimeMillis();
            data = dataProvider.createPlayerData(
                    event.getConnection().getName()
                    ,event.getConnection().getUniqueId()
                    ,-1
                    ,now
                    ,now
                    ,connection.extractGameProfile());
        }else data.updateLoginInformation(connection.getName(),connection.extractGameProfile(),System.currentTimeMillis());
        BungeeProxiedPlayer player = new BungeeProxiedPlayer(serverMap,connection,data);

        MinecraftPlayerLoginEvent loginEvent = new BungeeMinecraftLoginEvent(event,connection,player);
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
        if(player == null){
            event.getPlayer().disconnect(TextComponent.fromLegacyText("§cInternal server error."));
            return;
        }
        player.postLogin(event.getPlayer());
        playerManager.registerPlayer(player);
        MinecraftPlayerPostLoginEvent mcNativeEvent = new BungeeMinecraftPostLoginEvent(player);
        eventBus.callEvents(PostLoginEvent.class,event,mcNativeEvent);
    }

    private void handleServerConnect(ServerConnectEvent event){
        ConnectedMinecraftPlayer player = playerManager.getMappedPlayer(event.getPlayer());
        MinecraftServer server = serverMap.getMappedServer(event.getTarget());
        if(server.getPermission() != null && !player.hasPermission(server.getPermission())) event.setCancelled(true);
        MinecraftPlayerServerConnectEvent mcNativeEvent = new BungeeServerConnectEvent(event,serverMap,player);
        ServerConnectHandler handler = McNative.getInstance().getRegistry().getServiceOrDefault(ServerConnectHandler.class);
        if(handler != null && player.getServer() == null){
            mcNativeEvent.setTarget(handler.getFallbackServer(player,player.getServer()));
        }
        eventBus.callEvents(ServerConnectEvent.class,event,mcNativeEvent);
        if(handler != null && player.getServer() == null && event.getTarget() == null){
            MessageComponent<?> message = handler.getNoFallBackServerMessage(player);
            if(message != null) player.disconnect(message);
        }
    }

    private void handleServerConnected(ServerConnectedEvent event){
        OnlineMinecraftPlayer player = playerManager.getMappedPlayer(event.getPlayer());
        MinecraftServer server = serverMap.getMappedServer(event.getServer().getInfo());
        MinecraftPlayerServerConnectedEvent mcNativeEvent = new BungeeServerConnectedEvent(player,server);
        eventBus.callEvents(ServerConnectedEvent.class,event,mcNativeEvent);
        ((BungeeProxiedPlayer)player).setServer(server);
        ((BungeeProxiedPlayer) player).injectDownstreamProtocolHandlersToPipeline();
    }

    private void handleServerSwitch(ServerSwitchEvent event){
        OnlineMinecraftPlayer player = playerManager.getMappedPlayer(event.getPlayer());
        MinecraftPlayerServerSwitchEvent mcNativeEvent = new BungeeServerSwitchEvent(player);
        eventBus.callEvents(ServerSwitchEvent.class,event,mcNativeEvent);
    }

    private void handleServerKick(ServerKickEvent event){
        OnlineMinecraftPlayer player = playerManager.getMappedPlayer(event.getPlayer());
        MinecraftPlayerServerKickEvent mcNativeEvent = new BungeeServerKickEvent(serverMap,event,player);
        ServerConnectHandler handler = McNative.getInstance().getRegistry().getService(ServerConnectHandler.class);
        if(handler != null){
            mcNativeEvent.setFallbackServer(handler.getFallbackServer(player,player.getServer()));
        }
        eventBus.callEvents(ServerKickEvent.class,event,mcNativeEvent);
        if(handler != null && event.getCancelServer() == null){
            MessageComponent<?> message = handler.getNoFallBackServerMessage(player);
            if(message != null) mcNativeEvent.setKickReason(message);
        }
    }

    private void handleLogout(PlayerDisconnectEvent event){
        OnlineMinecraftPlayer player = playerManager.getMappedPlayer(event.getPlayer());
        MinecraftPlayerLogoutEvent mcNativeEvent = new BungeeMinecraftLogoutEvent(player);
        eventBus.callEvents(PlayerDisconnectEvent.class,event,mcNativeEvent);
    }

    private void handleChatEvent(ChatEvent event) {
        if(event.getSender() instanceof ProxiedPlayer){
            OnlineMinecraftPlayer player = playerManager.getMappedPlayer((ProxiedPlayer) event.getSender());
            MinecraftPlayerChatEvent mcNativeEvent = new BungeeMinecraftPlayerChatEvent(event,player);
            eventBus.callEvents(ChatEvent.class,event,mcNativeEvent);
            if(!event.isCancelled() && mcNativeEvent.getChannel() != null){
                event.setCancelled(true);
                mcNativeEvent.getChannel().chat(player,event.getMessage());
            }
        }else eventBus.callEvent(event);
    }

    private void handleTabComplete(TabCompleteEvent event){
        if(event.getSender() instanceof ProxiedPlayer){
            OnlineMinecraftPlayer player = playerManager.getMappedPlayer((ProxiedPlayer) event.getSender());
            MinecraftPlayerTabCompleteEvent mcNativeEvent = new BungeeTabCompleteEvent(event,player);
            this.tabCompleteCursors.put(event.getSender(),event.getCursor());
            eventBus.callEvents(TabCompleteEvent.class,event,mcNativeEvent);
        }else eventBus.callEvent(event);
    }

    private void handleTabCompleteResponse(TabCompleteResponseEvent event){
        if(event.getReceiver() instanceof ProxiedPlayer){
            OnlineMinecraftPlayer player = playerManager.getMappedPlayer((ProxiedPlayer) event.getSender());
            String cursor = this.tabCompleteCursors.get(event.getReceiver());
            MinecraftPlayerTabCompleteResponseEvent mcNativeEvent = new BungeeTabCompleteResponseEvent(event,player,cursor);
            eventBus.callEvents(TabCompleteEvent.class,event,mcNativeEvent);
        }else eventBus.callEvent(event);
    }

    private void handlePermissionCheck(PermissionCheckEvent event){
        PermissionHandler handler = null;
        CommandSender sender;
        if(event.getSender() instanceof ProxiedPlayer){
            OnlineMinecraftPlayer player = playerManager.getMappedPlayer((ProxiedPlayer) event.getSender());
            sender = player;
            handler = player.getPermissionHandler();
        }else if(event.getSender().equals(ProxyServer.getInstance().getConsole())){
            sender = McNative.getInstance().getConsoleSender();
        }else {
            if(event.getSender() instanceof Permissable){
                handler = ((Permissable) event.getSender()).getPermissionHandler();
            }else if(event.getSender() instanceof PermissionHandler){
                handler = ((PermissionHandler) event.getSender());
            }
            sender = new McNativeCommand.MappedCommandSender(event.getSender());
        }

        if(handler != null) event.setHasPermission(handler.hasPermission(event.getPermission()));

        BungeePermissionCheckEvent mcNativeEvent = new BungeePermissionCheckEvent(event,sender,handler);
        eventBus.callEvents(PermissionCheckEvent.class,event,mcNativeEvent);
    }

    private void handleProxyReload(ProxyReloadEvent event){
        ServiceReloadedEvent mcNativeEvent = new BungeeServiceReloadedEvent();
        eventBus.callEvents(ProxyReloadEvent.class,event,mcNativeEvent);
    }

    private void handleSettingsChange(SettingsChangedEvent event){
        OnlineMinecraftPlayer player = playerManager.getMappedPlayer(event.getPlayer());
        PlayerSettings settings = mapSettings(event.getPlayer());
        MinecraftPlayerSettingsChangedEvent mcNativeEvent = new BungeeMinecraftPlayerSettingsChangedEvent(player,settings);
        eventBus.callEvents(PermissionCheckEvent.class,event,mcNativeEvent);
        ((BungeeProxiedPlayer)player).setSettings(settings);
    }

    private PlayerSettings mapSettings(ProxiedPlayer player){
        return new PlayerSettings(player.getLocale()
                ,player.getViewDistance()
                ,PlayerSettings.ChatMode.valueOf(player.getChatMode().name())
                ,player.hasChatColors()
                ,mapSkinPart(player)
                ,PlayerSettings.MainHand.valueOf(player.getMainHand().name()));
    }

    private PlayerSettings.SkinParts mapSkinPart(ProxiedPlayer player){
        SkinConfiguration skin = player.getSkinParts();
        if(skin.getClass().getName().equals("net.md_5.bungee.PlayerSkinConfiguration")){
            return new PlayerSettings.SkinParts(ReflectionUtil.getFieldValue(skin,"bitmask",Byte.class));
        }else{
            return PlayerSettings.SkinParts.SKIN_SHOW_ALL;
        }
    }

}
