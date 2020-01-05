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

package org.mcnative.common.protocol.netty.rewrite;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.Endpoint;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.*;

import java.util.List;

public class MinecraftProtocolRewriteDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final PacketManager packetManager;
    private final Endpoint endpoint;
    private final PacketDirection direction;
    private final MinecraftConnection connection;
    private final MinecraftProtocolVersion version;

    public MinecraftProtocolRewriteDecoder(PacketManager packetManager, Endpoint endpoint, PacketDirection direction, MinecraftConnection connection) {
        this.packetManager = packetManager;
        this.endpoint = endpoint;
        this.direction = direction;
        this.connection = connection;
        this.version = connection.getProtocolVersion();
    }

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf in, List<Object> output) {
        ByteBuf out = in.copy();
        int packetId = MinecraftProtocolUtil.readVarInt(in);
        PacketIdentifier identifier = packetManager.getPacketIdentifier(connection.getState(),direction,version,packetId);
        if(identifier != null){
            List<MinecraftPacketListener> listeners = packetManager.getPacketListeners(endpoint,direction,identifier.getPacketClass());
            if(listeners != null && !listeners.isEmpty()){
                MinecraftPacket packet = identifier.newPacketInstance();
                packet.read(direction,version,in);

                MinecraftPacketEvent event = new MinecraftPacketEvent(endpoint,direction,version,packet);
                listeners.forEach(listener -> listener.handle(event));

                if(event.isCancelled()) return;
                else if(event.isRewrite()){
                    packet = event.getPacket();
                    out.clear();
                    MinecraftProtocolUtil.writeVarInt(out,packetId);
                    packet.write(direction,version,out);
                }
            }
        }
        output.add(out);
    }
}
