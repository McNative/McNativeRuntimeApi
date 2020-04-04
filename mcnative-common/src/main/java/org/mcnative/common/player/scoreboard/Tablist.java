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

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;

import java.util.Collection;
import java.util.List;

public interface Tablist {

    Collection<MinecraftPlayer> getPlayers();

    List<ScoreboardEntry> getEntries();


    default void setHeader(String message){
        setHeader(Text.of(message));
    }

    default void setHeader(MessageComponent<?> component){
        setHeader(component,VariableSet.newEmptySet());
    }

    void setHeader(MessageComponent<?> component, VariableSet variables);


    default void setFooter(String message){
        setFooter(Text.of(message));
    }

    default void setFooter(MessageComponent<?> component){
        setFooter(component,VariableSet.newEmptySet());
    }

    void setFooter(MessageComponent<?> component, VariableSet variables);


    default void setHeaderAndFooter(MessageComponent<?> header, MessageComponent<?> footer){
        setHeaderAndFooter(header, footer,VariableSet.newEmptySet());
    }

    void setHeaderAndFooter(MessageComponent<?> header, MessageComponent<?> footer, VariableSet variables);

    void clearHeaderAndFooter();


    void addEntry(MinecraftPlayer player);

    void addEntry(MinecraftPlayer player, PlayerDesign design);

    void addEntry(MinecraftPlayer player,String prefix, String suffix, int priority);

    void addEntry(String entry,String prefix, String suffix, int priority);

    void addEntry(ScoreboardEntry entry);

    void removeEntry(MinecraftPlayer player);

    void removeEntry(ScoreboardEntry entry);

    void removeEntry(String entry);


    void updateEntries();

    void updateMeta();

    void update();


    static Tablist newTablist(){
        return null;
    }

}
