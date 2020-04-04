/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.03.20, 13:27
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

package org.mcnative.common.protocol.packet.type.scoreboard;

import io.netty.buffer.ByteBuf;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;

public class MinecraftScoreboardTeamsPacket implements MinecraftPacket {

    private final String name = null;
    private final Action action = null;
    //private final MessageComponent<?> display;
    //private final MessageComponent<?> prefix;
    //private final MessageComponent<?> suffix;

    //private final OptionStatus nameTagVisibility;
    //private final OptionStatus collisionRule;

    //private final Collection<String> entities;

    @Override
    public PacketIdentifier getIdentifier() {
        return null;
    }

    @Override
    public void read(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }

    @Override
    public void write(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }

    public enum Action {

        CREATE(),
        DELETE(),
        UPDATE(),
        ADD_ENTITIES(),
        REMOVE_ENTITIES();

    }

    public enum OptionStatus {

        ALWAYS,
        NEVER,
        FOR_OTHER_TEAMS,
        FOR_OWN_TEAM;

    }

    public enum FriendlyFlag {

        DISALLOW,
        ALLOW,
        CAN_SEE_INVISIBLE_PLAYERS;

    }
}
