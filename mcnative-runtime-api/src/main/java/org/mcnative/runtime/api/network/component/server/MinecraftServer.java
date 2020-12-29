/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 15:03
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

package org.mcnative.runtime.api.network.component.server;

import net.pretronic.libraries.document.Document;
import org.mcnative.runtime.api.network.component.ConnectableNetworkComponent;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

public interface MinecraftServer extends ConnectableNetworkComponent {

    MinecraftProtocolVersion getProtocolVersion();

    InetSocketAddress getAddress();


    MinecraftServerType getType();

    void setType(MinecraftServerType type);


    String getPermission();

    void setPermission(String permission);


    ServerStatusResponse ping();

    CompletableFuture<ServerStatusResponse> pingAsync();


    void sendData(String channel, Document data);

    void sendData(String channel, byte[] data);

    void sendData(String channel, byte[] data, boolean queued);

}
