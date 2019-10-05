/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 22:19
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
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.proxy.server.MinecraftServer;
import org.mcnative.proxy.server.MinecraftServerType;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BungeeMinecraftServer implements MinecraftServer, ServerInfo {

    private final String name;
    private final InetSocketAddress address;

    private String permission;
    private MinecraftServerType type;

    public BungeeMinecraftServer(String name, InetSocketAddress address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public Collection<ProxiedPlayer> getPlayers() {
        return null;
    }

    @Override
    public String getMotd() {
        return null;
    }

    @Override
    public boolean isRestricted() {
        return permission != null;
    }

    @Override
    public boolean canAccess(CommandSender sender) {
        return sender.hasPermission(permission);
    }

    @Override
    public boolean sendData(String channel, byte[] data, boolean queue) {
        return false;
    }

    @Override
    public void ping(Callback<ServerPing> callback) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public MinecraftServerType getType() {
        return type;
    }

    @Override
    public void setType(MinecraftServerType type) {
        this.type = type;
    }

    @Override
    public Collection<org.mcnative.proxy.ProxiedPlayer> getConnectedPlayers() {
        return null;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public ServerPingResponse ping() {
        return null;
    }

    @Override
    public CompletableFuture<ServerPingResponse> pingAsync() {
        return null;
    }

    @Override
    public InetSocketAddress getAddress() {
        return address;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void disconnect(String message) {

    }

    @Override
    public void disconnect(TextComponent... reason) {

    }

    @Override
    public void sendPacket(MinecraftPacket packet) {

    }

    @Override
    public void sendPacketAsync(MinecraftPacket packet) {

    }

    @Override
    public void sendData(String channel, byte[] output) {

    }

    @Override
    public InputStream sendDataQuery(String channel, byte[] output) {
        return null;
    }

    @Override
    public InputStream sendDataQuery(String channel, Consumer<OutputStream> output) {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if(object == this) return true;
        return object instanceof MinecraftServer
                && ((MinecraftServer) object).getName().equalsIgnoreCase(name)
                && ((MinecraftServer) object).getAddress().equals(address);
    }
}
