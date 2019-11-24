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
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;
import org.mcnative.proxy.ProxiedPlayer;
import org.mcnative.proxy.server.MinecraftServer;
import org.mcnative.proxy.server.MinecraftServerType;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WrappedBungeeMinecraftServer implements MinecraftServer {

    private final ServerInfo original;

    private String permission;
    private MinecraftServerType type;

    public WrappedBungeeMinecraftServer(ServerInfo info) {
        this.original = info;
        this.permission = info.getPermission();
        this.type = MinecraftServerType.NORMAL;
    }

    public ServerInfo getOriginalInfo(){
        return original;
    }

    @Override
    public String getName() {
        return original.getName();
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
        return this.type;
    }

    @Override
    public void setType(MinecraftServerType type) {
        this.type = type;
    }


    //@Todo wrap

    @Override
    public Collection<ProxiedPlayer> getConnectedPlayers() {
        return null;
    }

    @Override
    public boolean isOnline() {
        return !original.getPlayers().isEmpty() || ping() != null;
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
        return original.getAddress();
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void disconnect(String message) {

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

    }

    @Override
    public void sendLocalLoopPacket(MinecraftPacket packet) {

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
        if(object == this || original.equals(object)) return true;
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
