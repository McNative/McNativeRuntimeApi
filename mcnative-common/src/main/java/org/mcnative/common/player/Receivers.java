/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 08.09.19, 17:21
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

package org.mcnative.common.player;

import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.text.MessageComponent;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public interface Receivers extends Iterable<OnlineMinecraftPlayer> {

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

    void sendReceiveAble(ReceiveAble receiveAble);
}
