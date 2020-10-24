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
import org.mcnative.common.protocol.packet.type.player.PlayerNamedSoundEffectPacket;
import org.mcnative.common.protocol.packet.type.scoreboard.MinecraftScoreboardTeamsPacket;

import java.util.List;

/**
 * The packet manager is responsible to manage incoming and outing packets, it holds packet specifications and
 * contains listeners to intercept and manipulate packets.
 */
public interface PacketManager {

    /**
     * Get the packet identifiers and specifications (Used for outgoing packets).
     *
     * @param packetClass The implementation class of the packet
     * @return The packet identifier
     */
    PacketIdentifier getPacketIdentifier(Class<?> packetClass);

    /**
     * Get the packet identifiers and specifications (Used for incoming packets).
     *
     * @param state The state of the connection
     * @param direction The packet sending  direction
     * @param version The Minecraft protocol version
     * @param packetId The id of the packet
     * @return The packet identifier
     */
    PacketIdentifier getPacketIdentifier(ConnectionState state,PacketDirection direction,MinecraftProtocolVersion version,int packetId);

    /**
     * Register a new Minecraft packet
     *
     * @param identifier The package identifier with the package specification to be registered
     */
    void registerPacket(PacketIdentifier identifier);

    /**
     * Unregister a Minecraft packet
     *
     * @param identifier The package identifier with the package specification to be registered
     */
    void unregisterPacket(PacketIdentifier identifier);

    /**
     * Get registered packet listeners
     *
     * @param endpoint The packet endpoint
     * @param direction The packet direction
     * @param packetClass The class of the packet implementation
     * @return A list of all listeners
     */
    List<MinecraftPacketListener> getPacketListeners(Endpoint endpoint, PacketDirection direction,Class<?> packetClass);

    /**
     *
     * @param endpoint The packet endpoint
     * @param direction The packet direction
     * @param packetClass The class of the packet implementation
     * @param listener The listener to register
     */
    void registerPacketListener(Endpoint endpoint, PacketDirection direction,Class<?> packetClass, MinecraftPacketListener listener);

    /**
     * Unregister a packet listener.
     *
     * @param listener The listener to be unregistered
     */
    void unregisterPacketListener(MinecraftPacketListener listener);

    static void registerDefaultPackets(PacketManager packetManager){
        packetManager.registerPacket(MinecraftChatPacket.IDENTIFIER);
        packetManager.registerPacket(MinecraftTitlePacket.IDENTIFIER);
        packetManager.registerPacket(MinecraftResourcePackSendPacket.IDENTIFIER);
        packetManager.registerPacket(MinecraftClientSettingsPacket.IDENTIFIER);

        packetManager.registerPacket(MinecraftScoreboardTeamsPacket.IDENTIFIER);
        packetManager.registerPacket(PlayerListHeaderAndFooterPacket.IDENTIFIER);
        packetManager.registerPacket(PlayerNamedSoundEffectPacket.IDENTIFIER);
    }
}
