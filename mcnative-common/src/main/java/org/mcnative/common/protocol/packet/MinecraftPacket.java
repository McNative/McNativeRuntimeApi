/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

import io.netty.buffer.ByteBuf;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

/**
 * The interface for the packet implementations
 */
public interface MinecraftPacket {

    PacketIdentifier getIdentifier();

    void read(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer);

    void write(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer);


    static PacketIdentifier newIdentifier(Class<? extends MinecraftPacket> packetClass, PacketIdentifier.PacketCondition... conditions){
        return new PacketIdentifier(packetClass, conditions);
    }

    static PacketIdentifier.IdMapping map(MinecraftProtocolVersion version, int identifier){
        return new PacketIdentifier.IdMapping(version,identifier);
    }

    static PacketIdentifier.PacketCondition on(PacketDirection direction, PacketIdentifier.IdMapping... mappings){
        return new PacketIdentifier.PacketCondition(direction,ConnectionState.GAME,mappings);
    }

    static PacketIdentifier.PacketCondition on(PacketDirection direction, ConnectionState state, PacketIdentifier.IdMapping... mappings){
        return new PacketIdentifier.PacketCondition(direction,state,mappings);
    }
}
