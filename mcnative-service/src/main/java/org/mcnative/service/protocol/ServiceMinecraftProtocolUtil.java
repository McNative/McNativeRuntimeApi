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

package org.mcnative.service.protocol;

import io.netty.buffer.ByteBuf;
import org.mcnative.common.McNative;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.networkid.DefaultMaterialProtocolId;
import org.mcnative.service.inventory.item.material.networkid.LegacyMaterialProtocolId;
import org.mcnative.service.inventory.item.material.networkid.MaterialProtocolId;

public class ServiceMinecraftProtocolUtil {

    public static void writeSlot(ByteBuf buffer, MinecraftProtocolVersion protocolVersion, ItemStack itemStack) {
        if(itemStack == null) {
            if(protocolVersion.isNewerOrSame(MinecraftProtocolVersion.JE_1_13)) buffer.writeBoolean(false);
            else buffer.writeShort(-1);
            return;
        }

        MaterialProtocolId protocolId = itemStack.getMaterial().getProtocolIds().get(protocolVersion);
        if(protocolId == null) {
            if(protocolVersion.isNewerOrSame(MinecraftProtocolVersion.JE_1_13)) buffer.writeBoolean(false);
            else buffer.writeShort(-1);
            McNative.getInstance().getLogger().warn("Can't handle item material " + itemStack.getMaterial().getName() + " for protocol version " + protocolVersion.getName());
            return;
        }
        //Item id
        if(protocolId instanceof DefaultMaterialProtocolId) {
            buffer.writeBoolean(true);
            MinecraftProtocolUtil.writeVarInt(buffer, ((DefaultMaterialProtocolId) protocolId).getId());
        } else if(protocolId instanceof LegacyMaterialProtocolId) {
            buffer.writeShort(((LegacyMaterialProtocolId) protocolId).getId());
        } else {
            McNative.getInstance().getLogger().warn("Can't handle item protocol id for " + itemStack.getMaterial().getName() + " for protocol version " + protocolVersion.getName() + " with " + protocolId.getClass().getSimpleName());
            return;
        }

        buffer.writeByte(itemStack.getAmount());//Amount

        if(protocolId instanceof LegacyMaterialProtocolId) {//Data/SubId
            buffer.writeShort(((LegacyMaterialProtocolId) protocolId).getSubId());
        }
        buffer.writeByte(0);//NBT
    }
}
