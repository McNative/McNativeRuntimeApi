/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 03.01.20, 23:31
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

package org.mcnative.common.event.server;

import org.mcnative.common.event.player.MinecraftOnlinePlayerEvent;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.event.NetworkEvent;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

@NetworkEvent
public interface MinecraftPlayerServerKickEvent extends MinecraftOnlinePlayerEvent {

    MinecraftServer getCurrentServer();

    MessageComponent<?> getKickReason();


    MinecraftServer getFallbackServer();

    void setFallbackServer(MinecraftServer server);


    default void setKickReason(MessageComponent<?> cancelReason){
        setKickReason(cancelReason, VariableSet.newEmptySet());
    }

    void setKickReason(MessageComponent<?> cancelReason, VariableSet variables);

}
