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

package org.mcnative.common.network.component;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;

import java.util.Collection;
import java.util.UUID;

public interface ConnectableNetworkComponent extends NetworkComponent {

    String getName();

    int getOnlineCount();

    Collection<OnlineMinecraftPlayer> getOnlinePlayers();

    OnlineMinecraftPlayer getOnlinePlayer(int id);

    OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId);

    OnlineMinecraftPlayer getOnlinePlayer(String nme);

    OnlineMinecraftPlayer getOnlinePlayer(long xBoxId);


    default void broadcast(MessageComponent<?> component){
        broadcast(component, VariableSet.newEmptySet());
    }

    void broadcast(MessageComponent<?> component, VariableSet variables);

    default void broadcast(String permission,MessageComponent<?> component){
        broadcast(permission, component,VariableSet.newEmptySet());
    }

    void broadcast(String permission,MessageComponent<?> component, VariableSet variables);


    void broadcastPacket(MinecraftPacket packet);

    void broadcastPacket(MinecraftPacket packet, String permission);


    default void kickAll(MessageComponent<?> component){
        kickAll(component,VariableSet.newEmptySet());
    }

    void kickAll(MessageComponent<?> component, VariableSet variables);

}
