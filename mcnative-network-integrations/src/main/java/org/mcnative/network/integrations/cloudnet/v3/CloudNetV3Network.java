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

package org.mcnative.network.integrations.cloudnet.v3;

import de.dytanic.cloudnet.driver.service.ServiceEnvironmentType;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgePlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import de.dytanic.cloudnet.wrapper.Wrapper;
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

public class CloudNetV3Network implements Network {

    private final CloudNetV3Messenger messenger;
    private final NetworkOperations operations;
    private final NetworkIdentifier localIdentifier;

    public CloudNetV3Network(Executor executor) {
        this.messenger = new CloudNetV3Messenger(executor);
        this.operations = new CloudNetV3NetworkOperations(this);
        this.localIdentifier = new NetworkIdentifier(
                Wrapper.getInstance().getServiceId().getName()
                ,Wrapper.getInstance().getServiceId().getUniqueId());
    }

    @Override
    public String getTechnology() {
        return "CloudNet V3";
    }

    @Override
    public CloudNetV3Messenger getMessenger() {
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
        ServiceInfoSnapshot service = Wrapper.getInstance().getCloudServiceProvider().getCloudServiceByName(name);
        if(service == null) return null;//throw new OperationFailedException("Server is not registered in cloud");
        return new NetworkIdentifier(name,service.getServiceId().getUniqueId());
    }

    @Override
    public Collection<ProxyServer> getProxies() {
        Collection<ServiceEnvironmentType> types = new ArrayList<>();
        for (ServiceEnvironmentType value : ServiceEnvironmentType.values()) {
            if(value.isMinecraftJavaProxy() || value.isMinecraftBedrockProxy()) types.add(value);
        }
        Collection<ServiceInfoSnapshot> snapshots = new ArrayList<>();
        for (ServiceEnvironmentType type : types) {
            snapshots.addAll(Wrapper.getInstance().getCloudServiceProvider().getCloudServices(type));
        }

        Collection<ProxyServer> servers = new ArrayList<>();
        for (ServiceInfoSnapshot snapshot : snapshots) {
            servers.add(new CloudNetProxy(snapshot));
        }
        return servers;
    }

    @Override
    public ProxyServer getProxy(String name) {
        ServiceInfoSnapshot snapshot = Wrapper.getInstance().getCloudServiceProvider().getCloudServiceByName(name);
        ServiceEnvironmentType environment = snapshot.getConfiguration().getServiceId().getEnvironment();
        if(environment.isMinecraftJavaProxy() || environment.isMinecraftBedrockProxy()){
            return new CloudNetProxy(snapshot);
        }
        return null;
    }

    @Override
    public ProxyServer getProxy(UUID uniqueId) {
        ServiceInfoSnapshot snapshot = Wrapper.getInstance().getCloudServiceProvider().getCloudService(uniqueId);
        ServiceEnvironmentType environment = snapshot.getConfiguration().getServiceId().getEnvironment();
        if(environment.isMinecraftJavaProxy() || environment.isMinecraftBedrockProxy()){
            return new CloudNetProxy(snapshot);
        }
        return null;
    }

    @Override
    public Collection<MinecraftServer> getServers() {
        Collection<ServiceEnvironmentType> types = new ArrayList<>();
        for (ServiceEnvironmentType value : ServiceEnvironmentType.values()) {
            if(value.isMinecraftJavaServer() || value.isMinecraftBedrockServer()) types.add(value);
        }
        Collection<ServiceInfoSnapshot> snapshots = new ArrayList<>();
        for (ServiceEnvironmentType type : types) {
            snapshots.addAll(Wrapper.getInstance().getCloudServiceProvider().getCloudServices(type));
        }

        Collection<MinecraftServer> servers = new ArrayList<>();
        for (ServiceInfoSnapshot snapshot : snapshots) {
            servers.add(new CloudNetServer(snapshot));
        }
        return servers;
    }

    @Override
    public MinecraftServer getServer(String name) {
        ServiceInfoSnapshot snapshot = Wrapper.getInstance().getCloudServiceProvider().getCloudServiceByName(name);
        ServiceEnvironmentType environment = snapshot.getConfiguration().getServiceId().getEnvironment();
        if(environment.isMinecraftJavaServer() || environment.isMinecraftBedrockServer()){
            return new CloudNetServer(snapshot);
        }
        return null;
    }

    @Override
    public MinecraftServer getServer(UUID uniqueId) {
        ServiceInfoSnapshot snapshot = Wrapper.getInstance().getCloudServiceProvider().getCloudService(uniqueId);
        ServiceEnvironmentType environment = snapshot.getConfiguration().getServiceId().getEnvironment();
        if(environment.isMinecraftJavaServer() || environment.isMinecraftBedrockServer()){
            return new CloudNetServer(snapshot);
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
    public int getOnlineCount() {
        return BridgePlayerManager.getInstance().getOnlineCount();
    }

    @Override
    public Collection<OnlineMinecraftPlayer> getOnlinePlayers() {
        Collection<OnlineMinecraftPlayer> result = new ArrayList<>();
        for (ICloudPlayer onlinePlayer : BridgePlayerManager.getInstance().getOnlinePlayers()) {
            result.add(new CloudNetOnlinePlayer(onlinePlayer));
        }
        return result;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId) {
        OnlineMinecraftPlayer connectedPlayer = McNative.getInstance().getLocal().getConnectedPlayer(uniqueId);
        if(connectedPlayer != null) return connectedPlayer;

        ICloudPlayer player = BridgePlayerManager.getInstance().getOnlinePlayer(uniqueId);
        return player != null ? new CloudNetOnlinePlayer(player) : null;
    }

    public OnlineMinecraftPlayer getDirectOnlinePlayer(UUID uniqueId) {
        ICloudPlayer player = BridgePlayerManager.getInstance().getOnlinePlayer(uniqueId);
        return player != null ? new CloudNetOnlinePlayer(player) : null;
    }


    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(String name) {
        OnlineMinecraftPlayer connectedPlayer = McNative.getInstance().getLocal().getConnectedPlayer(name);
        if(connectedPlayer != null) return connectedPlayer;

        ICloudPlayer player = BridgePlayerManager.getInstance().getFirstOnlinePlayer(name);
        return player != null ? new CloudNetOnlinePlayer(player) : null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(long xBoxId) {
        throw new UnsupportedOperationException("Currently not supported");
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
}
