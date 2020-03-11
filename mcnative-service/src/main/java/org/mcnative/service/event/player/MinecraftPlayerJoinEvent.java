/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.02.20, 14:17
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

package org.mcnative.service.event.player;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.service.location.Location;

//@Todo implement chat channel for join message
public interface MinecraftPlayerJoinEvent extends MinecraftEntityPlayerEvent{

    MessageComponent<?> getJoinMessage();

    VariableSet getJoinMessageVariables();

    default void setJoinMessage(MessageComponent<?> joinMessage){
        setJoinMessage(joinMessage,VariableSet.newEmptySet());
    }

    void setJoinMessage(MessageComponent<?> joinMessage, VariableSet variables);

    default void disableJoinMessage(){
        setJoinMessage(null,null);
    }

    Location getSpawnLocation();

    void setSpawnLocation(Location location);

}
