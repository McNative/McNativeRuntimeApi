/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 06.04.20, 09:55
 * @web %web%
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

package org.mcnative.network.integrations.cloudnet.v2;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.database.Database;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ProxyInfo;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import net.pretronic.libraries.command.manager.CommandManager;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.synchronisation.NetworkSynchronisationCallback;
import org.mcnative.common.McNative;
import org.mcnative.common.network.Network;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.NetworkOperations;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.network.integrations.McNativeGlobalExecutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.Executor;

public class CloudNetV2Network implements Network {

    private final CloudNetV2Messenger messenger;
    private final NetworkOperations operations;
    private final NetworkIdentifier localIdentifier;
    private final NetworkIdentifier networkIdentifier;

    public CloudNetV2Network(Executor executor) {
        this.messenger = new CloudNetV2Messenger(executor);
        this.operations = new CloudNetV2NetworkOperations(this);
        this.localIdentifier = new NetworkIdentifier(CloudAPI.getInstance().getServerId(),CloudAPI.getInstance().getUniqueId());
        this.networkIdentifier = new NetworkIdentifier(getName(),loadId());
    }

    private UUID loadId(){
        Database database = CloudAPI.getInstance().getDatabaseManager().getDatabase("mcnative");
        de.dytanic.cloudnet.lib.utility.document.Document result = database.getDocument("network-identifier");
        if(result != null){
            UUID uuid = result.getObject("networkId",UUID.class);
            if(uuid != null) return uuid;
        }
        UUID uuid = UUID.randomUUID();
        de.dytanic.cloudnet.lib.utility.document.Document document = new de.dytanic.cloudnet.lib.utility.document.Document();
        document.append(Database.UNIQUE_NAME_KEY,"network-identifier");
        database.insertAsync(document);
        return uuid;
    }

    @Override
    public String getTechnology() {
        return "CloudNet V2";
    }

    @Override
    public CloudNetV2Messenger getMessenger() {
        return messenger;
    }

    @Override
    public NetworkOperations getOperations() {
        return operations;
    }

    @Override
    public boolean isConnected() {
        return messenger.isAvailable();
    }

    @Override
    public EventBus getEventBus() {
        throw new UnsupportedOperationException("Network event bus is currently not integrated");
    }

    @Override
    public CommandManager getCommandManager() {
        throw new UnsupportedOperationException("Network event bus is currently not integrated");
    }

    @Override
    public NetworkIdentifier getLocalIdentifier() {
        return localIdentifier;
    }

    @Override
    public NetworkIdentifier getIdentifier(String name) {
        ServerInfo info = CloudAPI.getInstance().getServerInfo(name);
        if(info == null) return NetworkIdentifier.UNKNOWN;
        return new NetworkIdentifier(name,info.getServiceId().getUniqueId());
    }

    @Override
    public Collection<ProxyServer> getProxies() {
        Collection<ProxyServer> result = new ArrayList<>();
        for (ProxyInfo server : CloudAPI.getInstance().getProxys()) {
            result.add(new CloudNetProxy(server));
        }
        return result;
    }

    @Override
    public ProxyServer getProxy(String name) {
        for (ProxyInfo server : CloudAPI.getInstance().getProxys()) {
            String infoName = server.getServiceId().getGroup()+"-"+server.getServiceId().getId();
            if(infoName.equalsIgnoreCase(name)){
                return new CloudNetProxy(server);
            }
        }
        return null;
    }

    @Override
    public ProxyServer getProxy(UUID uniqueId) {
        for (ProxyInfo server : CloudAPI.getInstance().getProxys()) {
            if(server.getServiceId().getUniqueId() == uniqueId){
                return new CloudNetProxy(server);
            }
        }
        return null;
    }

    @Override
    public Collection<MinecraftServer> getServers() {
        Collection<MinecraftServer> result = new ArrayList<>();
        for (ServerInfo server : CloudAPI.getInstance().getServers()) {
            result.add(new CloudNetServer(server));
        }
        return result;
    }

    @Override
    public MinecraftServer getServer(String name) {
        ServerInfo info = CloudAPI.getInstance().getServerInfo(name);
        if(info != null) return new CloudNetServer(info);
        return null;
    }

    @Override
    public MinecraftServer getServer(UUID uniqueId) {
        for (ServerInfo server : CloudAPI.getInstance().getServers()) {
            if(server.getServiceId().getUniqueId() == uniqueId){
                return new CloudNetServer(server);
            }
        }
        return null;
    }

    @Override
    public void sendBroadcastMessage(String channel, Document request) {
        messenger.sendMessage(NetworkIdentifier.BROADCAST, channel,request);
    }

    @Override
    public void sendProxyMessage(String channel, Document request) {
        messenger.sendMessage(NetworkIdentifier.BROADCAST_PROXY, channel,request);
    }

    @Override
    public void sendServerMessage(String channel, Document request) {
        messenger.sendMessage(NetworkIdentifier.BROADCAST_SERVER, channel,request);
    }

    @Override
    public Collection<NetworkSynchronisationCallback> getStatusCallbacks() {
        return Collections.emptyList();
    }

    @Override
    public void registerStatusCallback(Plugin<?> owner, NetworkSynchronisationCallback synchronisationCallback) {
        //Unused, always connected
    }

    @Override
    public void unregisterStatusCallback(NetworkSynchronisationCallback synchronisationCallback) {
        //Unused, always connected
    }

    @Override
    public void unregisterStatusCallbacks(Plugin<?> owner) {
        //Unused, always connected
    }

    @Override
    public int getMaxPlayerCount() {
        if(!CloudAPI.getInstance().getCloudNetwork().getProxyGroups().isEmpty()){
            throw new IllegalArgumentException("No proxy group available");
        }
        return CloudAPI.getInstance().getCloudNetwork().getProxyGroups()
                .values().iterator().next().getProxyConfig().getMaxPlayers();
    }

    @Override
    public int getOnlineCount() {
        return CloudAPI.getInstance().getOnlineCount();
    }

    @Override
    public Collection<OnlineMinecraftPlayer> getOnlinePlayers() {
        Collection<OnlineMinecraftPlayer> players = new ArrayList<>();
        for (CloudPlayer onlinePlayer : CloudAPI.getInstance().getOnlinePlayers()) {
            players.add(new CloudNetOnlinePlayer(onlinePlayer));
        }
        return players;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId) {
        OnlineMinecraftPlayer player = McNative.getInstance().getLocal().getConnectedPlayer(uniqueId);
        if(player != null) return player;

        return getDirectOnlinePlayer(uniqueId);
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(String name) {
        OnlineMinecraftPlayer player = McNative.getInstance().getLocal().getConnectedPlayer(name);
        if(player != null) return player;

        UUID uniqueId =  CloudAPI.getInstance().getPlayerUniqueId(name);
        if(uniqueId == null) return null;
        CloudPlayer onlinePlayer = CloudAPI.getInstance().getOnlinePlayer(uniqueId);
        return onlinePlayer != null ? new CloudNetOnlinePlayer(onlinePlayer) : null;
    }

    public OnlineMinecraftPlayer getDirectOnlinePlayer(UUID uniqueId) {
        CloudPlayer onlinePlayer = CloudAPI.getInstance().getOnlinePlayer(uniqueId);
        return onlinePlayer != null ? new CloudNetOnlinePlayer(onlinePlayer) : null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(long xBoxId) {
        throw new UnsupportedOperationException("CloudNet does not support support bedrock players.");
    }

    @Override
    public void broadcast(MessageComponent<?> component, VariableSet variables) {
        McNativeGlobalExecutor.broadcast(component, variables);
    }

    @Override
    public void broadcast(String permission, MessageComponent<?> component, VariableSet variables) {
        McNativeGlobalExecutor.broadcast(permission,component, variables);
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet, String permission) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void kickAll(MessageComponent<?> component, VariableSet variables) {
        McNativeGlobalExecutor.kickAll(component, variables);
    }

    @Override
    public NetworkIdentifier getIdentifier() {
        return networkIdentifier;
    }
}
