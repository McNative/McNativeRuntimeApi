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
import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.event.EventBus;
import net.prematic.libraries.message.bml.variable.VariableSet;
import net.prematic.libraries.utility.Validate;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.MinecraftServerType;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.network.messaging.MessagingProvider;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class WrappedBungeeMinecraftServer implements MinecraftServer {

    private final ServerInfo original;
    private final NetworkIdentifier identifier;

    private String permission;
    private MinecraftServerType type;

    public WrappedBungeeMinecraftServer(ServerInfo info) {
        this.original = info;
        this.identifier = McNative.getInstance().getNetwork().getIdentifier(info.getName());
        this.permission = info.isRestricted() ? info.getPermission() : null;
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

    @Override
    public EventBus getEventBus() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CommandManager getCommandManager() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isOnline() {
        return !original.getPlayers().isEmpty() || ping() != null;
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
    public void sendData(String channel, byte[] data,boolean queued) {
        original.sendData(channel, data,queued);
    }

    @Override
    public InetSocketAddress getAddress() {
        return original.getAddress();
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return null;
    }

    @Override
    public int getOnlineCount() {
        return original.getPlayers().size();
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
        Validate.notNull(component,variables);
        getOnlinePlayers().forEach(player -> player.sendMessage(component,variables));
    }

    @Override
    public void broadcast(String permission, MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(permission,component,variables);
        getOnlinePlayers().forEach(player -> {
            if(player.hasPermission(permission)){
                player.sendMessage(component, variables);
            }
        });
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet) {
        Validate.notNull(packet);
        getOnlinePlayers().forEach(player -> player.sendPacket(packet));
    }

    @Override
    public void broadcastPacket(MinecraftPacket packet, String permission) {
        Validate.notNull(packet,permission);
        getOnlinePlayers().forEach(player -> {
            if(player.hasPermission(permission)){
                player.sendPacket(packet);
            }
        });
    }

    @Override
    public void kickAll(MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(component,variables);
        getOnlinePlayers().forEach(player -> player.kick(component, variables));
    }

    @Override
    public NetworkIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void sendMessage(String channel, Document request) {
        McNative.getInstance().getRegistry().getService(MessagingProvider.class).sendMessage(this,channel,request);
    }

    @Override
    public CompletableFuture<Document> sendQueryMessageAsync(String channel, Document request) {
        return McNative.getInstance().getRegistry().getService(MessagingProvider.class).sendQueryMessageAsync(this,channel,request);
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
