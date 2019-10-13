/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 15.09.19, 16:20
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

package org.mcnative.common.protocol.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.protocol.packet.PacketManager;

public class MinecraftProtocolEncoder extends MessageToByteEncoder<MinecraftPacket> {

    private final PacketManager packetManager;
    private final PacketDirection direction;
    private final MinecraftConnection connection;
    private final MinecraftProtocolVersion version;

    public MinecraftProtocolEncoder(PacketManager packetManager,PacketDirection direction, MinecraftConnection connection) {
        this.packetManager = packetManager;
        this.direction = direction;
        this.connection = connection;
        this.version = connection.getProtocolVersion();
    }

    @Override
    protected void encode(ChannelHandlerContext context, MinecraftPacket packet, ByteBuf buffer){
        final MinecraftPacket finalPacket = packet = this.packetManager.handlePacket(PacketDirection.OUTGOING,this.version,connection,packet);
        MinecraftProtocolUtil.writeVarInt(buffer,packet.getIdentifier().getId(PacketDirection.OUTGOING,connection.getState(),version));
        finalPacket.write(direction,this.version,buffer);
    }
}
