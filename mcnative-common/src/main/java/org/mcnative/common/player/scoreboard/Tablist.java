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
import org.mcnative.common.text.components.ChatComponent;

import java.util.Collection;

public interface Tablist {

    Collection<MinecraftPlayer> getPlayers();


    void setHeader(String message);

    default void setHeader(ChatComponent<?> component){
        setHeader(component,VariableSet.newEmptySet());
    }

    void setHeader(ChatComponent<?> component, VariableSet variables);


    void setFooter(String message);

    default void setFooter(ChatComponent<?> component){
        setFooter(component,VariableSet.newEmptySet());
    }

    void setFooter(ChatComponent<?> component, VariableSet variables);


    default void setHeaderAndFooter(ChatComponent<?> header, ChatComponent<?> footer){
        setHeaderAndFooter(header, footer,VariableSet.newEmptySet());
    }

    void setHeaderAndFooter(ChatComponent<?> header, ChatComponent<?> footer, VariableSet variables);

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
