/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.02.20, 17:25
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
import org.bukkit.Bukkit;
import org.mcnative.common.McNative;
import org.mcnative.common.network.Network;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.network.messaging.MessagingProvider;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;

public class BungeeCordProxyNetwork implements Network {

    private NetworkIdentifier localIdentifier;

    @Override
    public String getTechnology() {
        return "BungeeCord Proxy Network";
    }

    @Override
    public boolean isConnected() {
        return Bukkit.getOnlinePlayers().size() > 0;
    }

    @Override
    public EventBus getEventBus() {
        throw new IllegalArgumentException("Network events are currently not supported");
    }

    @Override
    public CommandManager getCommandManager() {
        throw new IllegalArgumentException("Network commands are currently not supported");
    }

    @Override
    public NetworkIdentifier getLocalIdentifier() {
        if(localIdentifier == null) throw new IllegalArgumentException("Local identifier not received, waiting for first player connection.");
        return localIdentifier;
    }

    @Override
    public NetworkIdentifier getIdentifier(String name) {
        return null;
    }

    @Override
    public Collection<ProxyServer> getProxies() {
        return null;
    }

    @Override
    public ProxyServer getProxy(String name) {
        return null;
    }

    @Override
    public ProxyServer getProxy(UUID uniqueId) {
        return null;
    }

    @Override
    public ProxyServer getProxy(InetSocketAddress address) {
        return null;
    }

    @Override
    public Collection<MinecraftServer> getServers() {
        return null;
    }

    @Override
    public MinecraftServer getServer(String name) {
        return null;
    }

    @Override
    public MinecraftServer getServer(UUID uniqueId) {
        return null;
    }

    @Override
    public MinecraftServer getServer(InetSocketAddress address) {
        return null;
    }

    @Override
    public void sendBroadcastMessage(String channel, Document request) {
        McNative.getInstance().getRegistry().getService(MessagingProvider.class)
                .sendMessage(NetworkIdentifier.BROADCAST,channel,request);
    }

    @Override
    public void sendProxyMessage(String channel, Document request) {
        McNative.getInstance().getRegistry().getService(MessagingProvider.class)
                .sendMessage(NetworkIdentifier.BROADCAST_PROXY,channel,request);
    }

    @Override
    public void sendServerMessage(String channel, Document request) {
        McNative.getInstance().getRegistry().getService(MessagingProvider.class)
                .sendMessage(NetworkIdentifier.BROADCAST_SERVER,channel,request);
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
}
