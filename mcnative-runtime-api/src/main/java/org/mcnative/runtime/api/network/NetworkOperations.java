/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.06.20, 19:16
 * @web %web%
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

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.network.component.server.MinecraftServer;
import org.mcnative.runtime.api.network.component.server.ProxyServer;
import org.mcnative.runtime.api.network.component.server.ServerConnectReason;
import org.mcnative.runtime.api.network.component.server.ServerConnectResult;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.concurrent.CompletableFuture;

public interface NetworkOperations {

    ProxyServer getProxy(OnlineMinecraftPlayer player);

    MinecraftServer getServer(OnlineMinecraftPlayer player);

    void connect(OnlineMinecraftPlayer player,MinecraftServer target, ServerConnectReason reason);

    CompletableFuture<ServerConnectResult> connectAsync(OnlineMinecraftPlayer player, MinecraftServer target, ServerConnectReason reason);

    void kick(OnlineMinecraftPlayer player, MessageComponent<?> message, VariableSet variables);

}
