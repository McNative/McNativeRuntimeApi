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

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.pretronic.libraries.command.manager.CommandManager;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.plugin.service.ServicePriority;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.annonations.Internal;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mcnative.bukkit.player.BukkitPlayerManager;
import org.mcnative.bukkit.serviceprovider.economy.VaultEconomyProvider;
import org.mcnative.bukkit.serviceprovider.permission.VaultPermissionProvider;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.MinecraftServerType;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.chat.ChatChannel;
import org.mcnative.common.player.tablist.Tablist;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.DefaultPacketManager;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketManager;
import org.mcnative.common.serviceprovider.economy.EconomyProvider;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.service.MinecraftService;
import org.mcnative.service.ObjectCreator;
import org.mcnative.service.world.World;
import org.mcnative.service.world.WorldCreator;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BukkitService implements MinecraftService, MinecraftServer {

    private final static NetworkIdentifier NO_NETWORK_IDENTIFIER = new NetworkIdentifier(Bukkit.getName(),new UUID(0,0));

    private final PacketManager packetManager;
    private final BukkitPlayerManager playerManager;
    private final CommandManager commandManager;
    private final EventBus eventBus;

    private ChatChannel serverChat;
    private Tablist serverTablist;
    private ServerStatusResponse statusResponse;
    private final ObjectCreator objectCreator;

    protected BukkitService(CommandManager commandManager,BukkitPlayerManager playerManager, EventBus eventBus) {
        this.packetManager = new DefaultPacketManager();
        this.commandManager = commandManager;
        this.playerManager = playerManager;
        this.eventBus = eventBus;
        this.objectCreator = new BukkitObjectCreator();
        initVaultHook();
    }

    @Override
    public ObjectCreator getObjectCreator() {
        return this.objectCreator;
    }

    @Override
    public World getDefaultWorld() {
        return getWorld(McNative.getInstance(BukkitMcNative.class).getServerProperties().getString("level-name"));
    }

    @Override
    public World getWorld(String name) {
        return new BukkitWorld(Bukkit.getWorld(name)); //@Todo optimize with world pool
    }

    @Internal
    public World getMappedWorld(org.bukkit.World world) {
        return new BukkitWorld(world);
    }

    @Override
    public World loadWorld(String name) {
        File file = new File(name);
        if(file.exists()) {
            return new BukkitWorld(Bukkit.createWorld(new org.bukkit.WorldCreator(name)));
        }
        throw new IllegalArgumentException("World " + name + " doesn't exist. Try to generate it.");
    }

    @Override
    public void unloadWorld(World world, boolean save) {
        Bukkit.unloadWorld(((BukkitWorld)world).getOriginal(), save);
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
    public Tablist getServerTablist() {
        return serverTablist;
    }

    @Override
    public void setServerTablist(Tablist tablist) {
        Validate.notNull(tablist);
        this.serverTablist = tablist;
    }

    @Override
    public ServerStatusResponse getStatusResponse() {
        return statusResponse;
    }

    @Override
    public void setStatusResponse(ServerStatusResponse status) {
        this.statusResponse = status;
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
        if(McNative.getInstance().isNetworkAvailable()){
            try{
                return McNative.getInstance().getNetwork().getLocalIdentifier().getName();
            }catch (Exception ignored){}
        }
        return Bukkit.getName();
    }

    @Override
    public int getOnlineCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    @Override
    public Collection<OnlineMinecraftPlayer> getOnlinePlayers() {
        return Iterators.map(playerManager.getConnectedPlayers(), player -> player);
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
        if(McNative.getInstance().isNetworkAvailable()){
            try{
                return McNative.getInstance().getNetwork().getLocalIdentifier();
            }catch (Exception ignored){}
        }
        return NO_NETWORK_IDENTIFIER;
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

            RegisteredServiceProvider<Permission> permissionService = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
            RegisteredServiceProvider<Chat> chatService = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
            if(permissionService != null || chatService != null) {
                VaultPermissionProvider vaultPermissionProvider = new VaultPermissionProvider(permissionService != null ? permissionService.getProvider() : null,
                        chatService != null ? chatService.getProvider() : null);
                McNative.getInstance().getRegistry().registerService(McNative.getInstance(), PermissionProvider.class,
                        vaultPermissionProvider, ServicePriority.LOWEST);
            }
        }
    }
}
