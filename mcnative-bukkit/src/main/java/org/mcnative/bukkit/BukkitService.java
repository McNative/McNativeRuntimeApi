/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.02.20, 11:47
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

import net.milkbowl.vault.economy.Economy;
import net.pretronic.libraries.command.manager.CommandManager;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.plugin.service.ServicePriority;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import net.pretronic.libraries.synchronisation.SynchronisationHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mcnative.bukkit.player.BukkitPlayerManager;
import org.mcnative.bukkit.serviceprovider.economy.VaultEconomyProvider;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.MinecraftServerType;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.network.messaging.MessagingChannelListener;
import org.mcnative.common.network.messaging.SynchronisationMessagingAdapter;
import org.mcnative.common.player.ChatChannel;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.DefaultPacketManager;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketManager;
import org.mcnative.common.serviceprovider.economy.EconomyProvider;
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

public class BukkitService implements MinecraftService, MinecraftServer {

    private final PacketManager packetManager;
    private final BukkitPlayerManager playerManager;
    private final CommandManager commandManager;
    private final EventBus eventBus;

    private Collection<MessageEntry> messageListeners;

    private ChatChannel serverChat;
    private Tablist defaultTablist;
    private ServerStatusResponse statusResponse;
    private final ObjectCreator objectCreator;

    protected BukkitService(CommandManager commandManager,BukkitPlayerManager playerManager, EventBus eventBus) {
        this.packetManager = new DefaultPacketManager();
        this.commandManager = commandManager;
        this.playerManager = playerManager;
        this.eventBus = eventBus;
        this.messageListeners = new ArrayList<>();
        this.objectCreator = new BukkitObjectCreator();
        initVaultHook();
    }

    @Override
    public ObjectCreator getObjectCreator() {
        return this.objectCreator;
    }

    @Override
    public World getDefaultWorld() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public World getWorld(String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public World loadWorld(String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void unloadWorld(World world, boolean save) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public World createWorld(WorldCreator creator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Collection<ConnectedMinecraftPlayer> getConnectedPlayers() {
        return playerManager.getConnectedPlayers();
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(int id) {
        return playerManager.getConnectedPlayer(id);
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(UUID uniqueId) {
        return playerManager.getConnectedPlayer(uniqueId);
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(String name) {
        return playerManager.getConnectedPlayer(name);
    }

    @Override
    public ConnectedMinecraftPlayer getConnectedPlayer(long xBoxId) {
        return playerManager.getConnectedPlayer(xBoxId);
    }

    @Override
    public PacketManager getPacketManager() {
        return packetManager;
    }

    @Override
    public ChatChannel getServerChat() {
        return serverChat;
    }

    @Override
    public void setServerChat(ChatChannel channel) {
        Validate.notNull(channel);
        this.serverChat = channel;
    }

    @Override
    public Tablist getDefaultTablist() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setDefaultTablist(Tablist tablist) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ServerStatusResponse getStatusResponse() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStatusResponse(ServerStatusResponse status) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Collection<String> getMessageChannels() {
        return Iterators.map(this.messageListeners, entry -> entry.name);
    }

    @Override
    public Collection<String> getMessageChannels(Plugin<?> owner) {
        Validate.notNull(owner);
        return Iterators.map(this.messageListeners
                , entry -> entry.name
                , entry -> entry.owner.equals(owner));
    }

    @Override
    public MessagingChannelListener getMessageMessageChannelListener(String name) {
        Validate.notNull(name);
        MessageEntry result = Iterators.findOne(this.messageListeners, entry -> entry.name.equalsIgnoreCase(name));
        return result != null ? result.listener : null;
    }

    @Override
    public void registerMessagingChannel(String name, Plugin<?> owner, MessagingChannelListener listener) {
        Validate.notNull(name,listener);//@Todo validate owner
        if(getMessageMessageChannelListener(name) != null) throw new IllegalArgumentException("Message channel "+name+" already in use");
        this.messageListeners.add(new MessageEntry(name,owner,listener));
    }

    @Override
    public <I> void registerSynchronizingChannel(String channel, Plugin<?> owner, Class<I> identifier, SynchronisationHandler<?, I> handler) {
        registerMessagingChannel(channel,owner,new SynchronisationMessagingAdapter(channel,identifier,handler));
    }

    @Override
    public void unregisterChannel(String name) {
        Validate.notNull(name);
        Iterators.removeOne(this.messageListeners, entry -> entry.name.equalsIgnoreCase(name));
    }

    @Override
    public void unregisterChannel(MessagingChannelListener listener) {
        Validate.notNull(listener);
        Iterators.removeSilent(this.messageListeners, entry -> entry.listener.equals(listener));
    }

    @Override
    public void unregisterChannels(Plugin<?> owner) {
        Validate.notNull(owner);
        Iterators.removeSilent(this.messageListeners, entry -> entry.owner.equals(owner));
    }


    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return McNative.getInstance().getPlatform().getProtocolVersion();
    }

    @Override
    public InetSocketAddress getAddress() {
        return new InetSocketAddress(Bukkit.getIp(),Bukkit.getPort());
    }

    @Override
    public MinecraftServerType getType() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setType(MinecraftServerType type) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getPermission() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setPermission(String permission) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ServerStatusResponse ping() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CompletableFuture<ServerStatusResponse> pingAsync() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void sendData(String channel, byte[] data) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void sendData(String channel, byte[] data, boolean queued) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getName() {
        return Bukkit.getName();
    }

    @Override
    public int getOnlineCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    @Override
    public Collection<OnlineMinecraftPlayer> getOnlinePlayers() {
        return Iterators.map(getOnlinePlayers(), player -> player);
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(int id) {
        return getConnectedPlayer(id);
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId) {
        return getConnectedPlayer(uniqueId);
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(String name) {
        return getConnectedPlayer(name);
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(long xBoxId) {
        return getConnectedPlayer(xBoxId);
    }

    @Override
    public void broadcast(MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(component,variables);
        getConnectedPlayers().forEach(player -> player.sendMessage(component,variables));
    }

    @Override
    public void broadcast(String permission, MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(permission,component,variables);
        getConnectedPlayers().forEach(player -> {
            if(player.hasPermission(permission)){
                player.sendMessage(component, variables);
            }
        });
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet) {
        Validate.notNull(packet);
        getConnectedPlayers().forEach(player -> player.sendPacket(packet));
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet, String permission) {
        Validate.notNull(packet,permission);
        getConnectedPlayers().forEach(player -> {
            if(player.hasPermission(permission)){
                player.sendPacket(packet);
            }
        });
    }

    @Override
    public void kickAll(MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(component,variables);
        getConnectedPlayers().forEach(player -> player.disconnect(component, variables));
    }

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public NetworkIdentifier getIdentifier() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void sendMessage(String channel, Document request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Document sendQueryMessage(String channel, Document request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CompletableFuture<Document> sendQueryMessageAsync(String channel, Document request) {
        return null;
    }

    private void initVaultHook() {
        if(Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            RegisteredServiceProvider<Economy> economyService = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            if (economyService != null) {
                VaultEconomyProvider vaultEconomyProvider = new VaultEconomyProvider(economyService.getProvider());
                McNative.getInstance().getRegistry().registerService(McNative.getInstance(), EconomyProvider.class,
                        vaultEconomyProvider, ServicePriority.LOWEST);
            }
        }
    }

    private static class MessageEntry {

        private final String name;
        private final ObjectOwner owner;
        private final MessagingChannelListener listener;

        public MessageEntry(String name, ObjectOwner owner, MessagingChannelListener listener) {
            this.name = name;
            this.owner = owner;
            this.listener = listener;
        }
    }
}
