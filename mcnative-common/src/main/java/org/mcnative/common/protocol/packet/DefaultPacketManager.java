/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.10.19, 11:26
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

import net.prematic.libraries.utility.Iterators;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultPacketManager implements PacketManager {

    private final Collection<PacketIdentifier> identifiers;

    public DefaultPacketManager() {
        this.identifiers = new ArrayList<>();
        PacketManager.registerDefaultPackets(this);
    }

    @Override
    public PacketIdentifier getPacketIdentifier(Class<?> packetClass) {
        PacketIdentifier identifier = Iterators.findOne(identifiers, identifier0 -> identifier0.getPacketClass().equals(packetClass));
        if(identifier == null) throw new IllegalArgumentException("No packet identifier for "+packetClass+" found.");
        return identifier;
    }

    @Override
    public void registerPacket(PacketIdentifier identifier) {
        identifiers.add(identifier);
    }

    @Override
    public void registerPacketListener(PacketDirection direction, Class<? extends MinecraftPacket> packetClass, MinecraftPacketListener listener) {

    }

    @Override
    public void unregisterPacketListener(PacketDirection direction, MinecraftPacketListener listener) {

    }

    @Override
    public MinecraftPacket createIncomingPacket(ConnectionState state, MinecraftProtocolVersion version, int packetId) {
        return null;
    }

    @Override
    public MinecraftPacket handlePacket(PacketDirection direction, MinecraftProtocolVersion version, MinecraftConnection connection, MinecraftPacket packet) {
        return packet;
    }
}
