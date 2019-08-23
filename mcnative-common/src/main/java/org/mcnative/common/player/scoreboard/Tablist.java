/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 14:49
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

package org.mcnative.common.player.scoreboard;

import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.text.MessageComponent;
import org.mcnative.common.text.TextComponent;
import org.mcnative.common.player.MinecraftPlayer;

import java.util.Collection;

public interface Tablist {

    Collection<MinecraftPlayer> getPlayers();


    void setHeader(String message);

    void setHeader(MessageComponent... components);


    void setFooter(String message);

    void setFooter(MessageComponent... components);


    void setHeaderAndFooter(MessageComponent header, MessageComponent footer);

    void clearHeaderAndFooter();


    void addEntry(MinecraftPlayer player);

    void addEntry(MinecraftPlayer player, PlayerDesign design);

    void addEntry(MinecraftPlayer player,String prefix, String suffix, int priority);

    void addEntry(String entry,String prefix, String suffix, int priority);

    void removeEntry(MinecraftPlayer player);

    void removeEntry(String entry);


    void update();


    static Tablist newTablist(){
        return null;
    }

}
