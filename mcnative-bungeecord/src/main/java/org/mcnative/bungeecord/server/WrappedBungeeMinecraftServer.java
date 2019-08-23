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

package org.mcnative.bungeecord.server;

import net.md_5.bungee.api.config.ServerInfo;
import org.mcnative.common.ServerPingResponse;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.TextComponent;
import org.mcnative.proxy.ProxiedPlayer;
import org.mcnative.proxy.server.MinecraftServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WrappedBungeeMinecraftServer implements MinecraftServer, ServerInfo {

    private final ServerInfo info;

    public WrappedBungeeMinecraftServer(ServerInfo info) {
        this.info = info;
    }

    public String getName() {
        return info.getName();
    }

    public String getPermission() {
        return info.getPermission();
    }

    public Collection<ProxiedPlayer> getConnectedPlayers() {
        return null;
    }

    public boolean isOnline() {
        return false;
    }

    public ServerPingResponse ping() {
        return null;
    }

    public CompletableFuture<ServerPingResponse> pingAsync() {
        return null;
    }

    public InetSocketAddress getAddress() {
        return info.getAddress();
    }

    public boolean isConnected() {
        return false;
    }

    public void disconnect(String message) {

    }

    public void disconnect(TextComponent... reason) {

    }

    public void sendPacket(MinecraftPacket packet) {

    }

    public void sendPacketAsync(MinecraftPacket packet) {

    }

    public void sendData(String channel, byte[] output) {
        this.info.sendData(channel, output);
    }

    public InputStream sendDataQuery(String channel, byte[] output) {
        return null;
    }

    public InputStream sendDataQuery(String channel, Consumer<OutputStream> output) {
        return null;
    }
}
