/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.04.20, 16:25
 * @web %web%
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

package org.mcnative.common.player.tablist;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;

import java.util.Collection;
import java.util.List;

public interface Tablist {

    Collection<ConnectedMinecraftPlayer> getReceivers();

    List<TablistEntry> getEntries();


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


    TablistFormatter getFormatter();

    void setFormatter(TablistFormatter formatter);


    void addEntry(ConnectedMinecraftPlayer player);

    void addEntry(ConnectedMinecraftPlayer player, PlayerDesign design);


    void addEntry(TablistEntry entry);


    void removeEntry(ConnectedMinecraftPlayer player);

    void removeEntry(TablistEntry entry);


    void updateOverview();

    void updateOverview(ConnectedMinecraftPlayer player);

    void updateEntries();

    void updateEntries(ConnectedMinecraftPlayer player);

    default void update(){
        updateOverview();
        updateEntries();
    }

    default void update(ConnectedMinecraftPlayer player){
        updateOverview(player);
        updateEntries(player);
    }

}
