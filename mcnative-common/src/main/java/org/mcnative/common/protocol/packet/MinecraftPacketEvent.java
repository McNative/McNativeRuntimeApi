/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.08.19, 14:10
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

package org.mcnative.common.protocol.packet;

import net.prematic.libraries.event.Cancellable;
import org.mcnative.common.event.MinecraftEvent;
import org.mcnative.common.player.MinecraftPlayer;

public interface MinecraftPacketEvent extends MinecraftEvent, Cancellable {

    MinecraftPacket getPacket();

    <T extends MinecraftPacket> T getPacket(Class<T> packetClass);

    MinecraftPlayer getPlayer();


    boolean isIncoming();

    boolean isOutgoing();

    void setPacket(MinecraftPacket packet);

}
