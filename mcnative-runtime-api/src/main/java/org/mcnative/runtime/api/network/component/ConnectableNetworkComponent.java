/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 15:01
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

package org.mcnative.runtime.api.network.component;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.Collection;
import java.util.UUID;

public interface ConnectableNetworkComponent extends NetworkComponent {

    int getMaxPlayerCount();

    int getOnlineCount();

    default boolean isFull(){
        return getOnlineCount() >= getMaxPlayerCount();
    }


    Collection<OnlineMinecraftPlayer> getOnlinePlayers();

    OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId);

    OnlineMinecraftPlayer getOnlinePlayer(String name);

    OnlineMinecraftPlayer getOnlinePlayer(long xBoxId);


    default void broadcast(MessageComponent<?> component){
        broadcast(component, VariableSet.createEmpty());
    }

    void broadcast(MessageComponent<?> component, VariableSet variables);

    default void broadcast(String permission,MessageComponent<?> component){
        broadcast(permission, component,VariableSet.createEmpty());
    }

    void broadcast(String permission,MessageComponent<?> component, VariableSet variables);


}
