/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 02.01.20, 22:45
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
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class MinecraftStatusResponsePacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(MinecraftStatusResponsePacket.class
            ,on(PacketDirection.OUTGOING,map(MinecraftProtocolVersion.JE_1_7,0x00)));

    private ServerStatusResponse response;

    public ServerStatusResponse getResponse() {
        return response;
    }

    public void setResponse(ServerStatusResponse response) {
        this.response = response;
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }

    @Override
    public void write(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeString(buffer,response.compileToString());
    }
}
