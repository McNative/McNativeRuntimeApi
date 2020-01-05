/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 07.10.19, 18:58
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
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class MinecraftTitlePacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(MinecraftChatPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_8,0x45)
                    ,map(MinecraftProtocolVersion.JE_1_12,0x47)
                    ,map(MinecraftProtocolVersion.JE_1_12_1,0x48)
                    ,map(MinecraftProtocolVersion.JE_1_13,0x4B)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x4F)
                    ,map(MinecraftProtocolVersion.JE_1_15,0x50)));

    private Action action;
    private Object data;
    private VariableSet variables;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public MessageComponent<?> getMessage(){
        return (MessageComponent<?>) data;
    }

    public void setMessage(MessageComponent<?> message){
        this.data = message;
    }

    public VariableSet getVariables() {
        return variables;
    }

    public void setVariables(VariableSet variables) {
        this.variables = variables;
    }

    public void setTime(int[] timing){
        this.data = timing;
    }

    public void setTime(int fadeIn, int stay, int fadeOut){
        this.data = new int[]{fadeIn,stay,fadeOut};
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        action = Action.of(version,MinecraftProtocolUtil.readVarInt(buffer));
        if(action == Action.SET_TITLE || action == Action.SET_SUBTITLE || action == Action.SET_ACTIONBAR){
            data = Text.decompile(MinecraftProtocolUtil.readString(buffer));
        }else if(action == Action.SET_TIME){
            int[] data = new int[3];
            data[0] = buffer.readInt();
            data[1] = buffer.readInt();
            data[2] = buffer.readInt();
            this.data = data;
        }
    }

    @Override
    public void write(PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeVarInt(buffer,action.getId(version));
        if(data != null){
            if(data instanceof MessageComponent){
                MinecraftProtocolUtil.writeString(buffer,((MessageComponent<?>)this.data).compileToString(variables!=null?variables: VariableSet.newEmptySet()));
            }else if(data instanceof int[]){
                int[] array = (int[]) data;
                buffer.writeInt(array[0]);
                buffer.writeInt(array[1]);
                buffer.writeInt(array[2]);
            }else throw new IllegalArgumentException("Invalid data type.");
        }
    }


    public enum Action {

        SET_TITLE(0,0),
        SET_SUBTITLE(1,1),
        SET_ACTIONBAR(-1,2),
        SET_TIME(2,3),
        HIDE(3,4),
        RESET(4,5);

        private int legacyId;
        private int Id;

        Action(int legacyId, int id) {
            this.legacyId = legacyId;
            Id = id;
        }

        public int getLegacyId() {
            return legacyId;
        }

        public int getId() {
            return Id;
        }

        public int getId(MinecraftProtocolVersion version){
            return version.isNewerOrSame(MinecraftProtocolVersion.JE_1_11) ? getId() : getLegacyId();
        }

        public static Action of(MinecraftProtocolVersion version,int id){
            if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_11)){
                for (Action value : values()) if(value.getId() == id) return value;
            }else{
                for (Action value : values()) if(value.getLegacyId() == id) return value;
            }
            throw new IllegalArgumentException("Title Action not found");
        }
    }
}
