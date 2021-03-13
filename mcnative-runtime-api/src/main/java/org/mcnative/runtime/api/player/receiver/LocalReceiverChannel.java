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

package org.mcnative.runtime.api.player.receiver;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.Collection;
import java.util.function.Consumer;

public interface LocalReceiverChannel extends Iterable<ConnectedMinecraftPlayer> {

    String getName();

    void setName(String name);


    Collection<ConnectedMinecraftPlayer> getPlayers();

    void setPlayers(Collection<ConnectedMinecraftPlayer> players);

    boolean containsPlayer(ConnectedMinecraftPlayer player);

    void addPlayer(ConnectedMinecraftPlayer player);

    void removePlayer(ConnectedMinecraftPlayer player);


    void addJoinListener(Consumer<ConnectedMinecraftPlayer> listener);

    void addRemoveListener(Consumer<ConnectedMinecraftPlayer> listener);


    default void sendMessage(MessageComponent<?> component){
        sendMessage(component,VariableSet.newEmptySet());
    }

    void sendMessage(MessageComponent<?> component, VariableSet variables);

    void sendPacket(MinecraftPacket packet);

    default void send(SendAble sendAble){
        sendAble.execute(this);
    }

    static LocalReceiverChannel create(){
        return McNative.getInstance().getObjectFactory().createObject(LocalReceiverChannel.class);
    }

    static LocalReceiverChannel create(String name){
        return McNative.getInstance().getObjectFactory().createObject(LocalReceiverChannel.class,name);
    }

}
