/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 27.09.20, 16:57
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
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.map.Maps;
import net.pretronic.libraries.utility.map.Pair;
import net.pretronic.libraries.utility.map.Triple;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.protocol.packet.type.player.PlayerListHeaderAndFooterPacket;
import org.mcnative.common.text.Text;

import java.util.Map;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class InventoryOpenWindowPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(PlayerListHeaderAndFooterPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_8,0x2D)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x13)
                    ,map(MinecraftProtocolVersion.JE_1_13,0x14)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x2E)
                    ,map(MinecraftProtocolVersion.JE_1_15,0x2F)
                    ,map(MinecraftProtocolVersion.JE_1_16,0x2E)
                    ,map(MinecraftProtocolVersion.JE_1_16_2,0x2D)));

    public static final Map<String, Triple<String, Integer, Integer>> INVENTORY_TYPE_MAPPER = Maps.ofValues(
            new Pair<>("AnvilInventory", new Triple<>("minecraft:anvil", 7, 0)),
            new Pair<>("BrewerInventory", new Triple<>("minecraft:brewing_stand", 10, 0)));


    private final int windowId;
    private final String windowType;
    private final String windowTitle;

    public InventoryOpenWindowPacket(int windowId, String windowType, String windowTitle) {
        Validate.notNull(windowType);
        Validate.notNull(windowTitle);
        this.windowId = windowId;
        this.windowType = windowType;
        this.windowTitle = windowTitle;
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
        Triple<String, Integer, Integer> inventoryInformation = INVENTORY_TYPE_MAPPER.get(this.windowType);
        if(inventoryInformation == null) {
            throw new IllegalArgumentException("OpenWindowPacket inventory mapper for " + this.windowType + " does not exist");
        }
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_14)) {
            MinecraftProtocolUtil.writeVarInt(buffer, this.windowId);
            MinecraftProtocolUtil.writeVarInt(buffer, inventoryInformation.getSecond());
            MinecraftProtocolUtil.writeString(buffer, Text.of(this.windowTitle).compileToString(version));
        } else {
            buffer.writeByte(this.windowId);
            MinecraftProtocolUtil.writeString(buffer, inventoryInformation.getFirst());
            MinecraftProtocolUtil.writeString(buffer, Text.of(this.windowTitle).compileToString(version));
            buffer.writeByte(inventoryInformation.getThird());
        }
    }
}
