/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 14:52
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

package org.mcnative.common.player.scoreboard.deprecated;

import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.support.JEProtocolSupport;
import org.mcnative.common.text.format.TextColor;

import java.util.Collection;

public interface ScoreboardTeam {

    String getName();

    String getDisplayName();

    String getPrefix();

    String getSuffix();

    @JEProtocolSupport(min= MinecraftProtocolVersion.JE_1_10)
    TextColor getColor();

    Scoreboard getScoreboard();

    int getSize();

    void setDisplayName(String displayName);

    void setPrefix(String prefix);

    void setSuffix(String suffix);

    @JEProtocolSupport(min= MinecraftProtocolVersion.JE_1_10)
    void setColor(TextColor color);


    Collection<String> getMembers();//As text?

    void addEntry(String member);

    void removeEntry(String member);


    OptionStatus getOption(Option option);

    void setOption(Option option, OptionStatus status);


    void unregister();

    enum Option {

        FRIENDLY_FIRE,
        CAN_SEE_FRIENDLY_INVISIBLE,
        NAME_TAG_VISIBILITY,
        DEATH_MESSAGE_VISIBILITY,
        COLLISION_RULE;

    }

    enum OptionStatus {

        ALWAYS,
        NEVER,
        FOR_OTHER_TEAMS,
        FOR_OWN_TEAM;

    }
}
