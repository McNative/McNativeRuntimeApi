/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 21:08
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

package org.mcnative.bungeecord.server;

import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.mcnative.common.ServerPingResponse;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;
import org.mcnative.proxy.server.MinecraftServer;
import org.mcnative.proxy.server.MinecraftServerType;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WrappedMcNativeMinecraftServer implements MinecraftServer, ServerInfo {

    private final MinecraftServer original;

    public WrappedMcNativeMinecraftServer(MinecraftServer original) {
        this.original = original;
    }

    public MinecraftServer getOriginalServer() {
        return original;
    }

    @Override
    public String getName() {
        return original.getName();
    }

    @Override
    public InetSocketAddress getAddress() {
        return original.getAddress();
    }

    @Override
    public Collection<ProxiedPlayer> getPlayers() {
        //@Todo map players
        return Collections.emptyList();
    }

    @Override
    public String getMotd() {
        return "";
    }

    @Override
    public boolean isRestricted() {
        return original.getPermission() != null;
    }

    @Override
    public String getPermission() {
        return original.getPermission();
    }

    @Override
    public boolean canAccess(CommandSender sender) {
        return sender.hasPermission(original.getPermission());
    }

    @Override
    public void sendData(String channel, byte[] data) {
        sendData(channel, data);
    }

    @Override
    public boolean sendData(String channel, byte[] data, boolean queue) {
        sendData(channel, data);
        return true;
    }

    @Override
    public void ping(Callback<ServerPing> callback) {
        //@Todo add pinging and wrap
    }


    @Override
    public void setPermission(String permission) {
        original.setPermission(permission);
    }

    @Override
    public MinecraftServerType getType() {
        return original.getType();
    }

    @Override
    public void setType(MinecraftServerType type) {
        original.setType(type);
    }

    @Override
    public Collection<org.mcnative.proxy.ProxiedPlayer> getConnectedPlayers() {
        return original.getConnectedPlayers();
    }

    @Override
    public boolean isOnline() {
        return original.isOnline();
    }

    @Override
    public ServerPingResponse ping() {
        return original.ping();
    }

    @Override
    public CompletableFuture<ServerPingResponse> pingAsync() {
        return original.pingAsync();
    }

    @Override
    public boolean isConnected() {
        return original.isConnected();
    }

    @Override
    public void disconnect(String message) {
        original.disconnect(message);
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return null;
    }

    @Override
    public ConnectionState getState() {
        return null;
    }

    @Override
    public void disconnect(MessageComponent reason, VariableSet variables) {

    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
        original.sendPacket(packet);
    }

    @Override
    public void sendLocalLoopPacket(MinecraftPacket packet) {
        original.sendLocalLoopPacket(packet);
    }

    @Override
    public InputStream sendDataQuery(String channel, byte[] output) {
        return original.sendDataQuery(channel, output);
    }

    @Override
    public InputStream sendDataQuery(String channel, Consumer<OutputStream> output) {
        return original.sendDataQuery(channel, output);
    }

    @Override
    public boolean equals(Object object) {
        if(object == this || original == object || original.equals(object)) return true;
        else if(object instanceof MinecraftServer){
            return ((MinecraftServer) object).getName().equalsIgnoreCase(original.getName())
                    && ((MinecraftServer) object).getAddress().equals(original.getAddress());
        }else if(object instanceof ServerInfo){
            return ((ServerInfo) object).getName().equalsIgnoreCase(((ServerInfo) object).getName())
                    && ((ServerInfo) object).getAddress().equals(original.getAddress());
        }
        return false;
    }
}
