/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 07.10.20, 17:29
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

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class InventoryCloseWindowPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(InventoryCloseWindowPacket.class
            ,on(PacketDirection.INCOMING,
                    map(MinecraftProtocolVersion.JE_1_8, 0x0D),
                    map(MinecraftProtocolVersion.JE_1_9, 0x08),
                    map(MinecraftProtocolVersion.JE_1_12, 0x09),
                    map(MinecraftProtocolVersion.JE_1_12_1, 0x08),
                    map(MinecraftProtocolVersion.JE_1_13, 0x09),
                    map(MinecraftProtocolVersion.JE_1_14, 0x0A)));

    private byte windowId;

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    public byte getWindowId() {
        return windowId;
    }

    @Override
    public void read(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        this.windowId = (byte) buffer.readUnsignedByte();
    }

    @Override
    public void write(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }
}
