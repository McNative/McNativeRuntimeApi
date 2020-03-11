/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 28.12.19, 21:59
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

package org.mcnative.common.network;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.EventBus;
import org.mcnative.common.network.component.ConnectableNetworkComponent;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Network extends ConnectableNetworkComponent {

    String getTechnology();

    boolean isConnected();

    EventBus getEventBus();


    NetworkIdentifier getLocalIdentifier();

    NetworkIdentifier getIdentifier(String name);


    Collection<ProxyServer> getProxies();

    ProxyServer getProxy(String name);

    ProxyServer getProxy(UUID uniqueId);

    ProxyServer getProxy(InetSocketAddress address);


    Collection<MinecraftServer> getServers();

    MinecraftServer getServer(String name);

    MinecraftServer getServer(UUID uniqueId);

    MinecraftServer getServer(InetSocketAddress address);


    void sendBroadcastMessage(String channel, Document request);

    void sendProxyMessage(String channel, Document request);

    void sendServerMessage(String channel, Document request);


    default String getName() {
        return "Network";
    }

    default NetworkIdentifier getIdentifier(){
        return NetworkIdentifier.BROADCAST;
    }

    default boolean isOnline(){
        return isConnected();
    }

    default void sendMessage(String channel, Document request){
        sendBroadcastMessage(channel,request);
    }

    default CompletableFuture<Document> sendQueryMessageAsync(String channel, Document request) {
        throw new UnsupportedOperationException("It is not possible to broadcast a query message");
    }
}
