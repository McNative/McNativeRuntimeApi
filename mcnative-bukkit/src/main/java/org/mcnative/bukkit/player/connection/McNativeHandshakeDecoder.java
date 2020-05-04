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
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.bukkit.event.server.ServerListPingEvent;
import org.mcnative.bukkit.event.BukkitServerListPingEvent;
import org.mcnative.bukkit.event.BukkitWrapperServerListPingEvent;
import org.mcnative.common.McNative;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.protocol.MinecraftEdition;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

import java.net.InetSocketAddress;
import java.util.List;

//@Todo implement legacy < 1.6 status ping
public class McNativeHandshakeDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final static int HANDSHAKE_PACKET_ID = 0;
    private final static int HANDSHAKE_PACKET_ID_LEGACY =  0xFE;
    private final static int HANDSHAKE_PING = 0x01;
    private final static int HANDSHAKE_PONG = 0x01;

    private final ChannelConnection connection;

    private boolean finished;
    private boolean statusRequest;

    public McNativeHandshakeDecoder(ChannelConnection connection) {
        this.connection = connection;
        this.finished = false;
    }

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> list) {
        ByteBuf out = buffer.copy();
        if(!finished){
            finished = true;
            int packetId = MinecraftProtocolUtil.readVarInt(buffer);
            if(packetId == HANDSHAKE_PACKET_ID){

                int protocolVersion = MinecraftProtocolUtil.readVarInt(buffer);
                String host = MinecraftProtocolUtil.readString(buffer);
                int port = buffer.readUnsignedShort();
                int next = MinecraftProtocolUtil.readVarInt(buffer);
                connection.initHandshake(protocolVersion,host,port);
                if(next == 10){
                    statusRequest = true;
                    handleServerListPing();
                    out.release();
                    return;
                }
            }
        }else if(statusRequest){
            int packetId = MinecraftProtocolUtil.readVarInt(buffer);
            if(packetId == HANDSHAKE_PING){
                long payload = out.readLong();
                ByteBuf resultBuffer = Unpooled.directBuffer();
                resultBuffer.writeByte(HANDSHAKE_PONG);
                resultBuffer.writeLong(payload);
                connection.getChannel().writeAndFlush(resultBuffer);
            }
            out.release();
            return;
        }
        list.add(out);
    }

    private void handleServerListPing(){
        try{
            ByteBuf resultBuffer = Unpooled.directBuffer();
            ServerStatusResponse response = McNative.getInstance().getLocal().getStatusResponse().clone();

            MinecraftProtocolVersion clientVersion = MinecraftProtocolVersion.UNKNOWN;
            try{
                clientVersion = MinecraftProtocolVersion.of(MinecraftEdition.JAVA,connection.getProtocolVersion());
            }catch (Exception ignored){}

            if(McNative.getInstance().getPlatform().canJoin(clientVersion)){
                response.getVersion().setProtocol(clientVersion);
            }

            BukkitServerListPingEvent mcnativeEvent = new BukkitServerListPingEvent(
                    (InetSocketAddress) connection.getChannel().remoteAddress()
                    ,new InetSocketAddress(connection.getHostname(),connection.getPort())
                    ,response);
            BukkitWrapperServerListPingEvent bukkitEvent = new BukkitWrapperServerListPingEvent(mcnativeEvent);
            McNative.getInstance().getLocal().getEventBus().callEvents(ServerListPingEvent.class,bukkitEvent,mcnativeEvent);

            resultBuffer.writeByte(HANDSHAKE_PACKET_ID);
            MinecraftProtocolUtil.writeString(resultBuffer,response.compileToString());
            connection.getChannel().writeAndFlush(resultBuffer);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
