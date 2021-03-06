/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.04.20, 20:51
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

package org.mcnative.runtime.api.player.tablist;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.PlayerDesign;

import java.util.Collection;
import java.util.List;

public interface Tablist {

    Collection<ConnectedMinecraftPlayer> getReceivers();

    List<TablistEntry> getEntries();


    TablistFormatter getFormatter();

    void setFormatter(TablistFormatter formatter);


    void addEntry(ConnectedMinecraftPlayer player);

    void addEntry(ConnectedMinecraftPlayer player, PlayerDesign design);


    void addEntry(TablistEntry entry);


    void removeEntry(ConnectedMinecraftPlayer player);

    void removeEntry(TablistEntry entry);

    void reloadEntry(TablistEntry entry);


    TablistOverviewFormatter getOverviewFormatter();

    void setOverviewFormatter(TablistOverviewFormatter formatter);

    void updateOverview(VariableSet headerVariables, VariableSet footerVariables);

    default void updateOverview() {
        updateOverview(VariableSet.createEmpty(), VariableSet.createEmpty());
    }

    void updateOverview(ConnectedMinecraftPlayer player, VariableSet headerVariables, VariableSet footerVariables);

    default void updateOverview(ConnectedMinecraftPlayer player) {
        updateOverview(player, VariableSet.createEmpty(), VariableSet.createEmpty());
    }

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

    static Tablist newTablist(){
        return McNative.getInstance().getObjectFactory().createObject(Tablist.class);
    }

}
