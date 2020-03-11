/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.09.19, 20:42
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

package org.mcnative.common.player.receiver;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.components.MessageComponent;

import java.util.Collection;
import java.util.function.Consumer;

public interface ReceiverChannel extends Iterable<OnlineMinecraftPlayer> {

    String getName();

    Collection<OnlineMinecraftPlayer> getPlayers();

    boolean containsPlayer(MinecraftPlayer player);

    void addPlayer(OnlineMinecraftPlayer player);

    void removePlayer(OnlineMinecraftPlayer player);


    default void sendMessage(MessageComponent<?> component){
        sendMessage(component,VariableSet.newEmptySet());
    }

    void sendMessage(MessageComponent<?> component, VariableSet variables);


    void sendPacket(MinecraftPacket packet);

    default void send(ReceiveAble receiveAble){
        receiveAble.execute(this);
    }


    void addRemovalListener(Consumer<OnlineMinecraftPlayer> listener);

    void addAdditionListener(Consumer<OnlineMinecraftPlayer> listener);

}
