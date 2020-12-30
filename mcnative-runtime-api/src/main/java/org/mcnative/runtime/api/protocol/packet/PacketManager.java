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

package org.mcnative.runtime.api.protocol.packet;

import net.pretronic.libraries.utility.map.Pair;
import org.mcnative.runtime.api.connection.ConnectionState;
import org.mcnative.runtime.api.protocol.Endpoint;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

import java.util.Collection;
import java.util.List;

/**
 * The packet manager is responsible to manage incoming and outing packets, it holds packet specifications and
 * contains listeners to intercept and manipulate packets.
 */
public interface PacketManager {

    Collection<PacketRegistration> getPacketRegistrations();

    PacketRegistration getPacketRegistration(Class<?> packetClass);

    PacketRegistration getPacketRegistration(ConnectionState state, PacketDirection direction, MinecraftProtocolVersion version, int packetId);

    void registerPacket(PacketRegistration registration);


    Pair<PacketRegistration,MinecraftPacketCodec<?>> getPacketCodecData(Class<?> packetClass);

    Pair<PacketRegistration,MinecraftPacketCodec<?>> getPacketCodecData(ConnectionState state, PacketDirection direction, MinecraftProtocolVersion version, int packetId);


    /**
     * Get registered packet listeners
     *
     * @param endpoint The packet endpoint
     * @param direction The packet direction
     * @param packetClass The class of the packet implementation
     * @return A list of all listeners
     */
    <T extends MinecraftPacket> List<MinecraftPacketListener> getPacketListeners(Endpoint endpoint, PacketDirection direction,Class<T> packetClass);

    /**
     *
     * @param endpoint The packet endpoint
     * @param direction The packet direction
     * @param packetClass The class of the packet implementation
     * @param listener The listener to register
     */
    <T extends MinecraftPacket> void registerPacketListener(Endpoint endpoint, PacketDirection direction, Class<T> packetClass, MinecraftPacketListener listener);

    /**
     * Unregister a packet listener.
     *
     * @param listener The listener to be unregistered
     */
    void unregisterPacketListener(MinecraftPacketListener listener);
}
