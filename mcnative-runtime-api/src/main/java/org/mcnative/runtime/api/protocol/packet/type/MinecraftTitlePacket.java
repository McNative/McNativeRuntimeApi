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

package org.mcnative.runtime.api.protocol.packet.type;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.text.components.MessageComponent;

public class MinecraftTitlePacket implements MinecraftPacket {

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

    public enum Action {

        SET_TITLE(0,0),
        SET_SUBTITLE(1,1),
        SET_ACTIONBAR(-1,2),
        SET_TIME(2,3),
        HIDE(3,4),
        RESET(4,5);

        private final int legacyId;
        private final int Id;

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
