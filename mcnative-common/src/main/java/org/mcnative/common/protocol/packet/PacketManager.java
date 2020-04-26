/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 15.09.19, 17:48
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

package org.mcnative.common.protocol.packet;

import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.protocol.Endpoint;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.type.MinecraftChatPacket;
import org.mcnative.common.protocol.packet.type.MinecraftClientSettingsPacket;
import org.mcnative.common.protocol.packet.type.MinecraftResourcePackSendPacket;
import org.mcnative.common.protocol.packet.type.MinecraftTitlePacket;
import org.mcnative.common.protocol.packet.type.player.PlayerListHeaderAndFooterPacket;
import org.mcnative.common.protocol.packet.type.scoreboard.MinecraftScoreboardTeamsPacket;

import java.util.List;

public interface PacketManager {

    PacketIdentifier getPacketIdentifier(Class<?> packetClass);

    PacketIdentifier getPacketIdentifier(ConnectionState state,PacketDirection direction,MinecraftProtocolVersion version,int packetId);

    void registerPacket(PacketIdentifier packet);

    void unregisterPacket(PacketIdentifier identifier);


    List<MinecraftPacketListener> getPacketListeners(Endpoint endpoint, PacketDirection direction,Class<?> packetClass);

    void registerPacketListener(Endpoint endpoint, PacketDirection direction,Class<?> packetClass, MinecraftPacketListener listener);

    void unregisterPacketListener(MinecraftPacketListener listener);


    static void registerDefaultPackets(PacketManager packetManager){
        packetManager.registerPacket(MinecraftChatPacket.IDENTIFIER);
        packetManager.registerPacket(MinecraftTitlePacket.IDENTIFIER);
        packetManager.registerPacket(MinecraftResourcePackSendPacket.IDENTIFIER);
        packetManager.registerPacket(MinecraftClientSettingsPacket.IDENTIFIER);

        packetManager.registerPacket(MinecraftScoreboardTeamsPacket.IDENTIFIER);
        packetManager.registerPacket(PlayerListHeaderAndFooterPacket.IDENTIFIER);
    }
}
