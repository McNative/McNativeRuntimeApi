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

package org.mcnative.common;

import net.prematic.libraries.plugin.Plugin;
import org.mcnative.common.network.component.ConnectableNetworkComponent;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.network.messaging.MessageChannelListener;
import org.mcnative.common.player.ChatChannel;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.protocol.packet.PacketManager;

import java.util.Collection;
import java.util.UUID;

public interface LocalService extends ConnectableNetworkComponent {

    Collection<ConnectedMinecraftPlayer> getConnectedPlayers();

    ConnectedMinecraftPlayer getConnectedPlayer(int id);

    ConnectedMinecraftPlayer getConnectedPlayer(UUID uniqueId);

    ConnectedMinecraftPlayer getConnectedPlayer(String nme);

    ConnectedMinecraftPlayer getConnectedPlayer(long xBoxId);


    PacketManager getPacketManager();


    ChatChannel getServerChat();

    void setServerChat(ChatChannel channel);


    Tablist getDefaultTablist();

    void setDefaultTablist(Tablist tablist);


    ServerStatusResponse getStatusResponse();

    void setStatusResponse(ServerStatusResponse status);


    Collection<String> getMessageChannels();

    Collection<String> getMessageChannels(Plugin<?> owner);

    MessageChannelListener getMessageMessageChannelListener(String name);

    void registerMessageChannel(String name, Plugin<?> owner, MessageChannelListener listener);

    void unregisterMessageChannel(String name);

    void unregisterMessageChannel(MessageChannelListener listener);

    void unregisterMessageChannels(Plugin<?> owner);

}
