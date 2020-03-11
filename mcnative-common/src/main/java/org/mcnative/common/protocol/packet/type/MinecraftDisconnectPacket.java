/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 07.10.19, 19:47
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
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class MinecraftDisconnectPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(MinecraftDisconnectPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_7,0x40)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x1A)
                    ,map(MinecraftProtocolVersion.JE_1_13,0x1B)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x1A)
                    ,map(MinecraftProtocolVersion.JE_1_15,0x1B))
            ,on(PacketDirection.OUTGOING, ConnectionState.LOGIN
                    ,map(MinecraftProtocolVersion.JE_1_7,0x00)));


    private MessageComponent<?> reason;
    private VariableSet variables;

    public MessageComponent<?> getReason() {
        return reason;
    }

    public void setReason(MessageComponent<?> reason) {
        this.reason = reason;
    }

    public VariableSet getVariables() {
        return variables;
    }

    public void setVariables(VariableSet variables) {
        this.variables = variables;
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        reason = Text.decompile(MinecraftProtocolUtil.readString(buffer));
    }

    @Override
    public void write(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeString(buffer,this.reason.compileToString(variables!=null?variables: VariableSet.newEmptySet()));
    }
}
