/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.02.20, 20:16
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

package org.mcnative.bukkit.player.connection;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.mcnative.common.protocol.MinecraftProtocolUtil;

import java.util.List;

public class McNativeHandshakeDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final static int HANDSHAKE_PACKET_ID = 0;
    private final static int HANDSHAKE_PACKET_ID_LEGACY =  0xFE;

    private final ChannelConnection connection;

    private boolean finished;

    public McNativeHandshakeDecoder(ChannelConnection connection) {
        this.connection = connection;
        this.finished = false;
    }

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> list) {
        ByteBuf out = buffer.copy();
        if(!finished){
            finished = true;
            try{
                int packetId = MinecraftProtocolUtil.readVarInt(buffer);
                if(packetId == HANDSHAKE_PACKET_ID){
                    int protocolVersion = MinecraftProtocolUtil.readVarInt(buffer);
                    String host = MinecraftProtocolUtil.readString(buffer);
                    int port = buffer.readUnsignedShort();
                    int next = MinecraftProtocolUtil.readVarInt(buffer);
                    connection.initHandshake(protocolVersion,host,port);
                    if(next == 1){
                        //out.release();@Todo implement custom event
                        //return;
                    }
                }
            }catch (Exception ignored){
                ignored.printStackTrace();
            }
        }
        list.add(out);
    }
}
