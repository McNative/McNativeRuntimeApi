/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 02.03.20, 20:43
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

package org.mcnative.bukkit.network;

import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.event.EventBus;
import net.prematic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.MinecraftServerType;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BungeeCordNetworkServer implements MinecraftServer {

    private final NetworkIdentifier identifier;

    public BungeeCordNetworkServer(NetworkIdentifier identifier) {
        this.identifier = identifier;
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
    public void sendData(String channel, byte[] data, boolean queued) {

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
    public void broadcast(MessageComponent<?> component, VariableSet variables) {

    }

    @Override
    public void broadcast(String permission, MessageComponent<?> component, VariableSet variables) {

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
    public EventBus getEventBus() {
        return null;
    }

    @Override
    public CommandManager getCommandManager() {
        return null;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public NetworkIdentifier getIdentifier() {
        return null;
    }

    @Override
    public void sendMessage(String channel, Document request) {

    }

    @Override
    public CompletableFuture<Document> sendQueryMessageAsync(String channel, Document request) {
        return null;
    }
}
