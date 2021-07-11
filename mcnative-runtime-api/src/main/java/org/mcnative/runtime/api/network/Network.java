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

package org.mcnative.runtime.api.network;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.synchronisation.NetworkSynchronisationCallback;
import org.mcnative.runtime.api.network.component.ConnectableNetworkComponent;
import org.mcnative.runtime.api.network.component.server.MinecraftServer;
import org.mcnative.runtime.api.network.component.server.ProxyServer;
import org.mcnative.runtime.api.network.messaging.Messenger;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Network extends ConnectableNetworkComponent {

    String getTechnology();

    Messenger getMessenger();

    NetworkOperations getOperations();


    boolean isConnected();


    NetworkIdentifier getLocalIdentifier();

    NetworkIdentifier getIdentifier(String name);

    NetworkIdentifier getIdentifier(UUID id);


    Collection<ProxyServer> getProxies();

    Collection<ProxyServer> getProxies(String group);

    ProxyServer getProxy(String name);

    ProxyServer getProxy(UUID uniqueId);


    ProxyServer getLeaderProxy();

    boolean isLeaderProxy(ProxyServer server);


    Collection<MinecraftServer> getServers();

    Collection<MinecraftServer> getServers(String group);

    MinecraftServer getServer(String name);

    MinecraftServer getServer(UUID uniqueId);


    void sendBroadcastMessage(String channel, Document request);

    void sendProxyMessage(String channel, Document request);

    void sendServerMessage(String channel, Document request);


    default String getName() {
        return "Network";
    }

    default boolean isOnline(){
        return isConnected();
    }

    default void sendMessage(String channel, Document request){
        sendBroadcastMessage(channel,request);
    }


    Collection<NetworkSynchronisationCallback> getStatusCallbacks();

    void registerStatusCallback(Plugin<?> owner, NetworkSynchronisationCallback synchronisationCallback);

    void unregisterStatusCallback(NetworkSynchronisationCallback synchronisationCallback);

    void unregisterStatusCallbacks(Plugin<?> owner);

}
