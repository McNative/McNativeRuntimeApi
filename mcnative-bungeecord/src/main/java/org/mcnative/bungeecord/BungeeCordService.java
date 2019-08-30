/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 18:19
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
import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.concurrent.TaskScheduler;
import net.prematic.libraries.concurrent.simple.SimpleTaskScheduler;
import net.prematic.libraries.event.EventManager;
import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.libraries.utility.Iterators;
import org.mcnative.bungeecord.server.BungeeMinecraftServer;
import org.mcnative.bungeecord.server.WrappedMcNativeMinecraftServer;
import org.mcnative.common.McNative;
import org.mcnative.common.MinecraftPlatform;
import org.mcnative.common.ServerPingResponse;
import org.mcnative.common.messaging.PluginMessageListener;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.PunishmentHandler;
import org.mcnative.common.player.WhitelistHandler;
import org.mcnative.common.player.chat.ChatChannel;
import org.mcnative.common.player.data.PlayerDataStorageHandler;
import org.mcnative.common.player.permission.PermissionHandler;
import org.mcnative.common.player.profile.GameProfileLoader;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.MinecraftPacketListener;
import org.mcnative.common.registry.Registry;
import org.mcnative.common.text.TextComponent;
import org.mcnative.proxy.ProxiedPlayer;
import org.mcnative.proxy.ProxyService;
import org.mcnative.proxy.server.ConnectHandler;
import org.mcnative.proxy.server.FallbackHandler;
import org.mcnative.proxy.server.MinecraftServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class BungeeCordService implements ProxyService {

    private final BungeeCordPlatform platform;
    private final PrematicLogger logger;
    private final TaskScheduler scheduler;


    private final Collection<PluginMessageListenerEntry> pluginMessageListeners;
    private final Collection<MinecraftServer> servers;

    private ChatChannel serverChannel;

    private PermissionHandler permissionHandler;
    private PunishmentHandler punishmentHandler;
    private ConnectHandler connectHandler;
    private FallbackHandler fallbackHandler;

    private ServerPingResponse serverPingResponse;
    private Tablist tablist;

    public BungeeCordService() {
        this.scheduler = new SimpleTaskScheduler();

        this.pluginMessageListeners = new ArrayList<>();
        this.servers = new ArrayList<>();

        this.logger = null;
        this.platform = null;
    }

    @Override
    public String getServiceName() {
        return ProxyServer.getInstance().getName();
    }

    @Override
    public MinecraftPlatform getPlatform() {
        return platform;
    }

    @Override
    public PrematicLogger getLogger() {
        return logger;
    }

    @Override
    public Registry getRegistry() {
        return null;
    }

    @Override
    public TaskScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public CommandManager getCommandManager() {
        return null;
    }

    @Override
    public EventManager getEventManager() {
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        return null;
    }


    @Override
    public PermissionHandler getPermissionHandler() {
        return permissionHandler;
    }

    @Override
    public void setPermissionHandler(PermissionHandler handler) {
        permissionHandler = handler;
    }

    @Override
    public PunishmentHandler getPunishmentHandler() {
        return punishmentHandler;
    }

    @Override
    public void setPunishmentHandler(PunishmentHandler handler) {
        punishmentHandler = handler;
    }

    @Override
    public ConnectHandler getConnectHandler() {
        return this.connectHandler;
    }

    @Override
    public void setConnectHandler(ConnectHandler handler) {
        this.connectHandler = handler;
    }

    @Override
    public FallbackHandler getFallbackHandler() {
        return this.fallbackHandler;
    }

    @Override
    public void setFallbackHandler(FallbackHandler handler) {
        this.fallbackHandler = handler;
    }

    @Override
    public WhitelistHandler getWhitelistHandler() {
        return null;
    }

    @Override
    public void setWhitelistHandler(WhitelistHandler handler) {

    }

    @Override
    public PlayerDataStorageHandler getPlayerDataStorageHandler() {
        return null;
    }

    @Override
    public void setPlayerDataStorageHandler(PlayerDataStorageHandler handler) {

    }

    @Override
    public GameProfileLoader getGameProfileLoader() {
        return null;
    }

    @Override
    public void setGameProfileLoader(GameProfileLoader loader) {

    }

    @Override
    public void registerIncomingPacketListener(Class<? extends MinecraftPacket> packetClass, MinecraftPacketListener listener) {

    }

    @Override
    public void registerOutgoingPacketListener(Class<? extends MinecraftPacket> packetClass, MinecraftPacketListener listener) {

    }

    @Override
    public void broadcastPacket(MinecraftPacket packet) {

    }

    @Override
    public void broadcastPacket(MinecraftPacket packet, String permission) {

    }

    @Override
    public Collection<MinecraftPlayer> getWhitelist() {
        return null;
    }

    @Override
    public Collection<MinecraftPlayer> getBanList() {
        return null;
    }

    @Override
    public Collection<MinecraftPlayer> getMuteList() {
        return null;
    }

    @Override
    public Collection<MinecraftPlayer> getOperators() {
        return null;
    }

    @Override
    public ChatChannel getServerChat() {
        return serverChannel;
    }

    @Override
    public void setServerChat(ChatChannel channel) {
        serverChannel = channel;
    }

    @Override
    public Tablist getDefaultTablist() {
        return tablist;
    }

    @Override
    public void setDefaultTablist(Tablist tablist) {
        this.tablist = tablist;
    }

    @Override
    public ServerPingResponse getPingResponse() {
        return serverPingResponse;
    }

    @Override
    public void setPingResponse(ServerPingResponse ping) {
        this.serverPingResponse = ping;
    }


    @Override
    public Collection<MinecraftServer> getServers() {
        return this.servers;
    }

    @Override
    public MinecraftServer getServer(String name) {
        MinecraftServer result = Iterators.findOne(servers, server -> server.getName().equalsIgnoreCase(name));
        return result instanceof WrappedMcNativeMinecraftServer?((WrappedMcNativeMinecraftServer) result).getOriginalServer():result;
    }

    @Override
    public MinecraftServer getServer(InetSocketAddress address) {
        MinecraftServer result = Iterators.findOne(this.servers, server -> server.getAddress().equals(address));
        return result instanceof WrappedMcNativeMinecraftServer?((WrappedMcNativeMinecraftServer) result).getOriginalServer():result;
    }

    @Override
    public MinecraftServer registerServer(String name, InetSocketAddress address) {
        validateServer(name,address);
        MinecraftServer server = new BungeeMinecraftServer(name, address);
        this.servers.add(server);
        return server;
    }

    @Override
    public MinecraftServer registerServer(MinecraftServer server) {
        validateServer(server.getName(),server.getAddress());
        MinecraftServer wrappedServer = new WrappedMcNativeMinecraftServer(server);
        this.servers.add(wrappedServer);
        return wrappedServer;
    }

    private void validateServer(String name, InetSocketAddress address){
        if(Iterators.findOne(this.servers, server -> server.getName().equalsIgnoreCase(name) || server.getAddress().equals(address)) != null){
            throw new IllegalArgumentException("A server with the "+name+" or address "+address+" is already registered");
        }
    }

    @Override
    public void unregisterServer(String name) {
        Iterators.removeOne(this.servers, server -> server.getName().equalsIgnoreCase(name));
    }

    @Override
    public void unregisterServer(InetSocketAddress address) {
        Iterators.removeOne(this.servers, server -> server.getAddress().equals(address));
    }

    @Override
    public void unregisterServer(MinecraftServer server) {
        Iterators.removeOne(this.servers, server1 -> server1.equals(server));
    }


    @Override
    public ProxiedPlayer getOnlinePlayer(UUID uniqueId) {
        return null;
    }

    @Override
    public ProxiedPlayer getOnlinePlayer(long xBoxId) {
        return null;
    }

    @Override
    public ProxiedPlayer getOnlinePlayer(String name) {
        return null;
    }

    @Override
    public Collection<ProxiedPlayer> getOnlineProxiedPlayers() {
        return null;
    }

    @Override
    public int getOnlineCount() {
        return ProxyServer.getInstance().getOnlineCount();
    }

    @Override
    public MinecraftPlayer getPlayer(UUID uniqueId) {
        return null;
    }

    @Override
    public MinecraftPlayer getPlayer(long xBoxId) {
        return null;
    }

    @Override
    public MinecraftPlayer getPlayer(String name) {
        return null;
    }

    @Override
    public Collection<OnlineMinecraftPlayer> getOnlinePlayers() {
        return null;
    }

    @Override
    public void broadcast(String message) {
    }

    @Override
    public void broadcast(TextComponent... components) {

    }

    @Override
    public void broadcast(String permission, String message) {

    }

    @Override
    public void broadcast(String permission, TextComponent... components) {

    }

    @Override
    public Collection<String> getOpenChannels() {
        return ProxyServer.getInstance().getChannels();
    }

    public Collection<PluginMessageListenerEntry> getPluginMessageListeners() {
        return pluginMessageListeners;
    }

    @Override
    public void registerChannel(String name, Plugin owner, PluginMessageListener listener) {
        ProxyServer.getInstance().registerChannel(name);//open channel
        this.pluginMessageListeners.add(new PluginMessageListenerEntry(owner,name,listener));
    }

    @Override
    public void unregisterChannel(String name) {
        ProxyServer.getInstance().unregisterChannel(name);//Close channel
        Iterators.remove(this.pluginMessageListeners, entry -> entry.name.equalsIgnoreCase(name));
    }

    @Override
    public void unregisterChannel(PluginMessageListener listener) {
        PluginMessageListenerEntry entry =Iterators.removeOne(this.pluginMessageListeners, entry1 -> entry1.listener.equals(listener));
        ProxyServer.getInstance().unregisterChannel(entry.name);
    }

    @Override
    public void unregisterChannels(Plugin owner) {
        PluginMessageListenerEntry entry = Iterators.removeOne(this.pluginMessageListeners, entry1 -> entry1.owner.equals(owner));
        ProxyServer.getInstance().unregisterChannel(entry.name);
    }


    @Override
    public void shutdown() {
        ProxyServer.getInstance().stop();
    }

    @Override
    public void restart() {

    }



    public static class PluginMessageListenerEntry {

        public String name;
        public Plugin owner;
        public PluginMessageListener listener;

        public PluginMessageListenerEntry(Plugin owner,String name, PluginMessageListener listener) {
            this.owner = owner;
            this.name = name;
            this.listener = listener;
        }
    }
}

