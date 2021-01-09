/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 15:05
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

package org.mcnative.runtime.api;

import org.mcnative.runtime.api.network.component.ConnectableNetworkComponent;
import org.mcnative.runtime.api.network.component.server.ServerStatusResponse;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.chat.ChatChannel;
import org.mcnative.runtime.api.player.tablist.Tablist;
import org.mcnative.runtime.api.protocol.packet.PacketManager;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;

public interface LocalService extends ConnectableNetworkComponent {

    InetSocketAddress getAddress();

    Collection<ConnectedMinecraftPlayer> getConnectedPlayers();

    ConnectedMinecraftPlayer getConnectedPlayer(int id);

    ConnectedMinecraftPlayer getConnectedPlayer(UUID uniqueId);

    ConnectedMinecraftPlayer getConnectedPlayer(String nme);

    ConnectedMinecraftPlayer getConnectedPlayer(long xBoxId);


    PacketManager getPacketManager();


    void setStatus(String status);


    ChatChannel getServerChat();

    void setServerChat(ChatChannel channel);


    Tablist getServerTablist();

    void setServerTablist(Tablist tablist);


    ServerStatusResponse getStatusResponse();

    void setStatusResponse(ServerStatusResponse status);

    ServerPerformance getServerPerformance();

}
