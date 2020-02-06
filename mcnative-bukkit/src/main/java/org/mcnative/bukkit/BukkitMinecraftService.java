/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 18.10.19, 00:07
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

import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.concurrent.TaskScheduler;
import net.prematic.libraries.concurrent.simple.SimpleTaskScheduler;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.event.EventBus;
import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.logging.bridge.JdkPrematicLogger;
import net.prematic.libraries.message.bml.variable.VariableSet;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.libraries.utility.Iterators;
import org.bukkit.Bukkit;
import org.mcnative.bukkit.plugin.BukkitCommandManager;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.common.MinecraftPlatform;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServerType;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.network.messaging.MessageChannelListener;
import org.mcnative.common.player.ChatChannel;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.PlayerManager;
import org.mcnative.common.player.data.DefaultPlayerDataProvider;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.common.player.profile.GameProfileLoader;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.DefaultPacketManager;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketManager;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;
import org.mcnative.common.serviceprovider.punishment.PunishmentProvider;
import org.mcnative.common.serviceprovider.whitelist.DefaultWhitelistProvider;
import org.mcnative.common.serviceprovider.whitelist.WhitelistProvider;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.service.MinecraftService;
import org.mcnative.service.ObjectCreator;
import org.mcnative.service.world.World;
import org.mcnative.service.world.WorldCreator;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BukkitMinecraftService implements MinecraftService {

    private final MinecraftPlatform platform;
    private final PrematicLogger logger;

    private final ObjectCreator objectCreator;

    private final TaskScheduler scheduler;
    private final CommandManager commandManager;
    //private final EventManager eventManager;
    private final PluginManager pluginManager;
    private final PacketManager packetManager;
    private final PlayerManager playerManager;
    // private final StorageManager storageManager;

    private PermissionProvider permissionHandler;
    private PunishmentProvider punishmentHandler;
    private WhitelistProvider whitelistHandler;
    private PlayerDataProvider playerDataStorageHandler;

    private GameProfileLoader gameProfileLoader;
    private ReceiverChannel serverChat;
    private Tablist tablist;

    private final Collection<World> worlds;

    public BukkitMinecraftService() {
        this.platform = new BukkitPlatform();
        this.logger = new JdkPrematicLogger(Bukkit.getLogger());
        this.objectCreator = new BukkitObjectCreator();
        // this.registry = null;
        this.scheduler = new SimpleTaskScheduler();
        this.commandManager = new BukkitCommandManager();
        // this.eventManager = new DefaultEventManager();
        this.pluginManager = null;
        this.packetManager = new DefaultPacketManager();
        this.playerManager = null;
        //this.storageManager = new StorageManager(null);

        this.permissionHandler = null;
        this.punishmentHandler = null;
        this.whitelistHandler = new DefaultWhitelistProvider();
        this.playerDataStorageHandler = new DefaultPlayerDataProvider();

        this.gameProfileLoader = null;
        this.serverChat = null;
        this.tablist = null;

        this.worlds = new ArrayList<>();
    }

    @Override
    public ObjectCreator getObjectCreator() {
        return this.objectCreator;
    }

    @Override
    public World getDefaultWorld() {
        return Iterators.findOne(this.worlds, World::isDefault);
    }

    @Override
    public World getWorld(String name) {
        World world = Iterators.findOne(this.worlds, searchWorld -> searchWorld.getName().equalsIgnoreCase(name));
        if(world == null) {
            world = Bukkit.getWorld(name) != null ? new BukkitWorld(Bukkit.getWorld(name)) : null;
            if(world != null) this.worlds.add(world);
        }
        return world;
    }

    @Override
    public World loadWorld(String name) {
        World world = Iterators.findOne(this.worlds, searchWorld -> searchWorld.getName().equalsIgnoreCase(name));
        if(world == null) {
            world = new BukkitWorld(Bukkit.createWorld(org.bukkit.WorldCreator.name(name)));
            this.worlds.add(world);
        }
        return world;
    }

    @Override
    public void unloadWorld(World world, boolean save) {
        Bukkit.unloadWorld(world.getName(), save);
    }

    @Override
    public World createWorld(WorldCreator creator) {
        return null;
    }

    @Override
    public EventBus getEventBus() {
        return null;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public Collection<ConnectedMinecraftPlayer> getConnectedPlayers() {
        return null;
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(int id) {
        return null;
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(UUID uniqueId) {
        return null;
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(String nme) {
        return null;
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(long xBoxId) {
        return null;
    }

    @Override
    public PacketManager getPacketManager() {
        return this.packetManager;
    }

    @Override
    public ChatChannel getServerChat() {
        return null;
    }

    @Override
    public void setServerChat(ChatChannel channel) {

    }

    @Override
    public Tablist getDefaultTablist() {
        return this.tablist;
    }

    @Override
    public void setDefaultTablist(Tablist tablist) {
        this.tablist = tablist;
    }

    @Override
    public ServerStatusResponse getStatusResponse() {
        return null;
    }

    @Override
    public void setStatusResponse(ServerStatusResponse status) {

    }

    @Override
    public Collection<String> getMessageChannels() {
        return null;
    }

    @Override
    public Collection<String> getMessageChannels(Plugin<?> owner) {
        return null;
    }

    @Override
    public MessageChannelListener getMessageMessageChannelListener(String name) {
        return null;
    }

    @Override
    public void registerMessageChannel(String name, Plugin<?> owner, MessageChannelListener listener) {

    }

    @Override
    public void unregisterMessageChannel(String name) {

    }

    @Override
    public void unregisterMessageChannel(MessageChannelListener listener) {

    }

    @Override
    public void unregisterMessageChannels(Plugin<?> owner) {

    }

    @Override
    public void broadcast(MessageComponent component, VariableSet variables) {

    }

    @Override
    public void broadcast(String permission, MessageComponent component, VariableSet variables) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getOnlineCount() {
        return 0;
    }

    @Override
    public Collection<OnlineMinecraftPlayer> getOnlinePlayers() {
        return null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(int id) {
        return null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId) {
        return null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(String nme) {
        return null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(long xBoxId) {
        return null;
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet) {

    }

    @Override
    public void broadcastPacket(MinecraftPacket packet, String permission) {

    }

    @Override
    public void kickAll(MessageComponent<?> component, VariableSet variables) {

    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return null;
    }

    @Override
    public InetSocketAddress getAddress() {
        return null;
    }

    @Override
    public MinecraftServerType getType() {
        return null;
    }

    @Override
    public void setType(MinecraftServerType type) {

    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public void setPermission(String permission) {

    }

    @Override
    public ServerStatusResponse ping() {
        return null;
    }

    @Override
    public CompletableFuture<ServerStatusResponse> pingAsync() {
        return null;
    }

    @Override
    public void sendData(String channel, byte[] data) {

    }

    @Override
    public NetworkIdentifier getIdentifier() {
        return null;
    }

    @Override
    public void sendMessage(String channel, Document request) {

    }

    @Override
    public Document sendQueryMessage(String channel, Document request) {
        return null;
    }
}
