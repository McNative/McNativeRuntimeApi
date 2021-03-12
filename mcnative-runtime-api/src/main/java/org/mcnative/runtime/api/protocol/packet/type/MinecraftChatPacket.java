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

package org.mcnative.runtime.api.protocol.packet.type;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.player.chat.ChatPosition;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketValidationException;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.UUID;

public class MinecraftChatPacket implements MinecraftPacket {

    private MessageComponent<?> message;
    private VariableSet variables;
    private ChatPosition position;
    private UUID sender;

    public MessageComponent<?> getMessage() {
        return message;
    }

    public void setMessage(MessageComponent<?> message) {
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

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    @Override
    public void validate() {
        if(message == null) throw new MinecraftPacketValidationException("Message cannot be null");
        if(position == null) throw new MinecraftPacketValidationException("Chat position cannot be null");
    }
}
