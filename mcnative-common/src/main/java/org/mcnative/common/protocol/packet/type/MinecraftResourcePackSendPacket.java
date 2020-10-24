/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 03.01.20, 17:41
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

package org.mcnative.common.protocol.packet.type;

import io.netty.buffer.ByteBuf;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class MinecraftResourcePackSendPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(MinecraftResourcePackSendPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_8,0x48)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x32)
                    ,map(MinecraftProtocolVersion.JE_1_12,0x33)
                    ,map(MinecraftProtocolVersion.JE_1_13,0x37)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x39)
                    ,map(MinecraftProtocolVersion.JE_1_15,0x3A)
                    ,map(MinecraftProtocolVersion.JE_1_16,0x39)
                    ,map(MinecraftProtocolVersion.JE_1_16_3,0x38)));

    private String url;
    private String hash;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        url = MinecraftProtocolUtil.readString(buffer);
        hash = MinecraftProtocolUtil.readString(buffer);
    }

    @Override
    public void write(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeString(buffer,url);
        MinecraftProtocolUtil.writeString(buffer,hash);
    }
}
