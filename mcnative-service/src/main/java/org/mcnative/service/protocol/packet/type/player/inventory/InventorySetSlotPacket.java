/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 27.09.20, 17:37
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
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.protocol.ServiceMinecraftProtocolUtil;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;


public class InventorySetSlotPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(InventorySetSlotPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_8, 0x2F)
                    ,map(MinecraftProtocolVersion.JE_1_9, 0x16)
                    ,map(MinecraftProtocolVersion.JE_1_13, 0x17)
                    ,map(MinecraftProtocolVersion.JE_1_14, 0x16)
                    ,map(MinecraftProtocolVersion.JE_1_15,0x17)
                    ,map(MinecraftProtocolVersion.JE_1_16,0x16)
                    ,map(MinecraftProtocolVersion.JE_1_16_2,0x15)));

    private final byte windowId;
    private final short slot;
    private final ItemStack itemStack;

    public InventorySetSlotPacket(byte windowId, short slot, ItemStack itemStack) {
        this.windowId = windowId;
        this.slot = slot;
        this.itemStack = itemStack;
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
        buffer.writeShort(this.slot);
        ServiceMinecraftProtocolUtil.writeSlot(buffer, version, this.itemStack);
    }
}
