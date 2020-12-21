/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 07.10.20, 20:16
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

public class ConfirmTransactionPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(ConfirmTransactionPacket.class
            ,on(PacketDirection.OUTGOING,
                    map(MinecraftProtocolVersion.JE_1_8, 0x32),
                    map(MinecraftProtocolVersion.JE_1_9, 0x11),
                    map(MinecraftProtocolVersion.JE_1_13, 0x12),
                    map(MinecraftProtocolVersion.JE_1_15, 0x13),
                    map(MinecraftProtocolVersion.JE_1_16, 0x12),
                    map(MinecraftProtocolVersion.JE_1_16_2, 0x11)));

    private final byte windowId;
    private final short actionNumber;
    private final boolean accepted;

    public ConfirmTransactionPacket(byte windowId, short actionNumber, boolean accepted) {
        this.windowId = windowId;
        this.actionNumber = actionNumber;
        this.accepted = accepted;
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
        buffer.writeShort(this.actionNumber);
        buffer.writeBoolean(this.accepted);
    }
}
