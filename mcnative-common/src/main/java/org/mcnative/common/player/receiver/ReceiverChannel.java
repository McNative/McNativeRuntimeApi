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

import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.outdated.MessageComponent;

import java.util.Collection;
import java.util.Iterator;

public interface ReceiverChannel extends Iterable<OnlineMinecraftPlayer> {

    String getName();

    String getPrefix();

    void setPrefix(String prefix);

    Collection<OnlineMinecraftPlayer> getPlayers();

    @Override
    default Iterator<OnlineMinecraftPlayer> iterator() {
        return getPlayers().iterator();
    }

    void addPlayer(OnlineMinecraftPlayer player);

    void removePlayer(OnlineMinecraftPlayer player);

    void sendMessage(String message);

    void sendMessage(MessageComponent... components);

    void sendPacket(MinecraftPacket packet);

    default void send(ReceiveAble receiveAble){
        receiveAble.execute(this);
    }
}
