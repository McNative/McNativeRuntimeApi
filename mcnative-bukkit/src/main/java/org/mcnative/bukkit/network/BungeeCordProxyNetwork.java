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

import net.pretronic.libraries.command.manager.CommandManager;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.synchronisation.NetworkSynchronisationCallback;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.OwnedObject;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.common.McNative;
import org.mcnative.common.network.Network;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.network.messaging.Messenger;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class BungeeCordProxyNetwork implements Network {

    private final Messenger messenger;
    private final Collection<OwnedObject<NetworkSynchronisationCallback>> statusCallbacks;

    private NetworkIdentifier localIdentifier;
    private ProxyServer proxy;
    private Collection<MinecraftServer> servers;

    public BungeeCordProxyNetwork(ExecutorService executor) {
        this.messenger = new PluginMessageMessenger(this,executor);
        this.statusCallbacks = new ArrayList<>();
    }

    @Override
    public String getTechnology() {
        return "BungeeCord Proxy Network";
    }

    @Override
    public Messenger getMessenger() {
        return messenger;
    }

    @Override
    public boolean isConnected() {
        return messenger.isAvailable();
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
        MinecraftServer server = getServer(name);
        return server != null ? server.getIdentifier() : null;
    }

    @Override
    public Collection<ProxyServer> getProxies() {
        return Collections.singleton(proxy);
    }

    @Override
    public ProxyServer getProxy(String name) {
        return proxy.getName().equalsIgnoreCase(name) ? proxy : null;
    }

    @Override
    public ProxyServer getProxy(UUID uniqueId) {
        return proxy.getIdentifier().getUniqueId().equals(uniqueId) ? proxy : null;
    }

    @Override
    public ProxyServer getProxy(InetSocketAddress address) {
        return proxy.getAddress().equals(address) ? proxy: null;
    }

    @Override
    public Collection<MinecraftServer> getServers() {
        return servers;
    }

    @Override
    public MinecraftServer getServer(String name) {
        if(localIdentifier.getName().equalsIgnoreCase(name)) return (MinecraftServer) McNative.getInstance().getLocal();
        else return Iterators.findOne(this.servers, server -> server.getName().equalsIgnoreCase(name));
    }

    @Override
    public MinecraftServer getServer(UUID uniqueId) {
        if(localIdentifier.getUniqueId().equals(uniqueId)) return (MinecraftServer) McNative.getInstance().getLocal();
        else return Iterators.findOne(this.servers, server -> server.getIdentifier().getUniqueId().equals(uniqueId));
    }

    @Override
    public MinecraftServer getServer(InetSocketAddress address) {
        return Iterators.findOne(this.servers, server -> server.getAddress().equals(address));
    }

    @Override
    public void sendBroadcastMessage(String channel, Document request) {
        messenger.sendMessage(NetworkIdentifier.BROADCAST,channel,request);
    }

    @Override
    public void sendProxyMessage(String channel, Document request) {
        messenger.sendMessage(NetworkIdentifier.BROADCAST_PROXY,channel,request);
    }

    @Override
    public void sendServerMessage(String channel, Document request) {
        messenger.sendMessage(NetworkIdentifier.BROADCAST_SERVER,channel,request);
    }

    @Override
    public Collection<NetworkSynchronisationCallback> getStatusCallbacks() {
        return Iterators.map(statusCallbacks, OwnedObject::getObject);
    }

    @Override
    public void registerStatusCallback(Plugin<?> owner, NetworkSynchronisationCallback synchronisationCallback) {
        Validate.notNull(owner,synchronisationCallback);
        this.statusCallbacks.add(new OwnedObject<>(owner,synchronisationCallback));
    }

    @Override
    public void unregisterStatusCallback(NetworkSynchronisationCallback synchronisationCallback) {
        Validate.notNull(synchronisationCallback);
        Iterators.removeOne(this.statusCallbacks, entry -> entry.getObject().equals(synchronisationCallback));
    }

    @Override
    public void unregisterStatusCallbacks(Plugin<?> owner) {
        Validate.notNull(owner);
        Iterators.removeOne(this.statusCallbacks, entry -> entry.getOwner().equals(owner));
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

    public void requestServer(){
        Document document = Document.newDocument();
       // localIdentifier = document.get
    }
}
