/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 07.10.20, 16:33
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

package org.mcnative.service.protocol.packet.type.player.inventory;

import io.netty.buffer.ByteBuf;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.protocol.packet.type.player.PlayerListHeaderAndFooterPacket;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;


public class InventoryWindowPropertyPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(InventoryWindowPropertyPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_8, 0x31)
                    ,map(MinecraftProtocolVersion.JE_1_9, 0x15)
                    ,map(MinecraftProtocolVersion.JE_1_13, 0x16)
                    ,map(MinecraftProtocolVersion.JE_1_14, 0x15)
                    ,map(MinecraftProtocolVersion.JE_1_15, 0x16)
                    ,map(MinecraftProtocolVersion.JE_1_16, 0x15)
                    ,map(MinecraftProtocolVersion.JE_1_16_2, 0x14)));

    private final byte windowId;
    private final short property;
    private final short value;

    public InventoryWindowPropertyPacket(byte windowId, short property, short value) {
        this.windowId = windowId;
        this.property = property;
        this.value = value;
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }

    @Override
    public void write(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        buffer.writeByte(this.windowId);
        buffer.writeShort(this.property);
        buffer.writeShort(this.value);
    }
}
