/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 15.09.19, 18:15
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
import org.mcnative.common.player.ChatPosition;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class MinecraftChatPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(MinecraftChatPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_7,0x02)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x0F)
                    ,map(MinecraftProtocolVersion.JE_1_13,0x0E))
            ,on(PacketDirection.INCOMING
                    ,map(MinecraftProtocolVersion.JE_1_7,0x01)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x02)
                    ,map(MinecraftProtocolVersion.JE_1_12,0x03)
                    ,map(MinecraftProtocolVersion.JE_1_12_1,0x02)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x03)));

    private MessageComponent message;
    private VariableSet variables;
    private ChatPosition position;

    public MessageComponent getMessage() {
        return message;
    }

    public void setMessage(MessageComponent message) {
        this.message = message;
    }

    public VariableSet getVariables() {
        return variables;
    }

    public void setVariables(VariableSet variables) {
        this.variables = variables;
    }

    public ChatPosition getPosition() {
        return position;
    }

    public void setPosition(ChatPosition position) {
        this.position = position;
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        message = Text.decompile(MinecraftProtocolUtil.readString(buffer));
        if(direction == PacketDirection.OUTGOING); //@Todo read position
    }

    @Override
    public void write(PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeString(buffer,this.message.compileToString(variables!=null?variables:VariableSet.newEmptySet()));
        if(direction == PacketDirection.OUTGOING) buffer.writeByte(position.getId());
    }
}
