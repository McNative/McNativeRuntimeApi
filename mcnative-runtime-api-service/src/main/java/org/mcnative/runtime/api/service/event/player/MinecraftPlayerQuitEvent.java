/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.02.20, 18:59
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

package org.mcnative.runtime.api.service.event.player;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.text.components.MessageComponent;

public interface MinecraftPlayerQuitEvent extends MinecraftEntityPlayerEvent {

    MessageComponent<?> getQuietMessage();

    VariableSet getQuietMessageVariables();

    default void setQuietMessage(MessageComponent<?> joinMessage){
        setQuietMessage(joinMessage,VariableSet.newEmptySet());
    }

    void setQuietMessage(MessageComponent<?> joinMessage, VariableSet variables);

    default void disableQuietMessage(){
        setQuietMessage(null,null);
    }

}
