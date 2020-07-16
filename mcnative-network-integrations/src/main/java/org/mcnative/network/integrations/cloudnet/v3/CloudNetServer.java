/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.07.20, 12:03
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

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.ServiceInfoSnapshotUtil;
import net.pretronic.libraries.command.manager.CommandManager;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.MinecraftServerType;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CloudNetServer implements MinecraftServer {

    private final ServiceInfoSnapshot snapshot;

    public CloudNetServer(ServiceInfoSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return ping().getVersion().getProtocol();
    }

    @Override
    public InetSocketAddress getAddress() {
        return new InetSocketAddress(snapshot.getAddress().getHost(),snapshot.getAddress().getPort());
    }

    @Override
    public MinecraftServerType getType() {
        return MinecraftServerType.NORMAL;
    }

    @Override
    public void setType(MinecraftServerType type) {
        throw new UnsupportedOperationException("It is not possible to modify the server type of a CloudNet server");
    }

    @Override
    public String getPermission() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPermission(String permission) {
        throw new UnsupportedOperationException("It is not possible to modify the permission of a CloudNet server");
    }

    @Override
    public ServerStatusResponse ping() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompletableFuture<ServerStatusResponse> pingAsync() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendData(String channel, byte[] data, boolean queued) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return snapshot.getServiceId().getName();
    }

    @Override
    public int getOnlineCount() {
        return ServiceInfoSnapshotUtil.getOnlineCount(snapshot);
    }

    @Override
    public Collection<OnlineMinecraftPlayer> getOnlinePlayers() {
        Collection<OnlineMinecraftPlayer> result = new ArrayList<>();
        for (JsonDocument player : ServiceInfoSnapshotUtil.getPlayers(snapshot)) {

        }
        return result;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId) {
        return null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(String name) {
        return null;
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer(long xBoxId) {
        return null;
    }

    @Override
    public void broadcast(MessageComponent<?> component, VariableSet variables) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void broadcast(String permission, MessageComponent<?> component, VariableSet variables) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet, String permission) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void kickAll(MessageComponent<?> component, VariableSet variables) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EventBus getEventBus() {
        throw new UnsupportedOperationException("Network commands are currently not supported");
    }

    @Override
    public CommandManager getCommandManager() {
        throw new UnsupportedOperationException("Network commands are currently not supported");
    }

    @Override
    public boolean isOnline() {
        return snapshot.isConnected();
    }

    @Override
    public NetworkIdentifier getIdentifier() {
        return new NetworkIdentifier(snapshot.getServiceId().getName(),snapshot.getServiceId().getUniqueId());
    }

    @Override
    public void sendMessage(String channel, Document request) {
        McNative.getInstance().getNetwork().getMessenger().sendMessage(this,channel,request);
    }

    @Override
    public CompletableFuture<Document> sendQueryMessageAsync(String channel, Document request) {
        return  McNative.getInstance().getNetwork().getMessenger().sendQueryMessageAsync(this,channel,request);
    }
}
