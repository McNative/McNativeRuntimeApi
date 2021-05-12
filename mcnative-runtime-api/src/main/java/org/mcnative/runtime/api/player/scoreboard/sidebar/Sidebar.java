/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:45
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

package org.mcnative.runtime.api.player.scoreboard.sidebar;

import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;

import java.util.Collection;

public interface Sidebar {

    Collection<ConnectedMinecraftPlayer> getReceivers();


    String getTitle();

    void setTitle(String title);


    Collection<SidebarEntry> getEntries();

    SidebarEntry createEntry();

    void removeEntry(SidebarEntry entry);


    void update();

    void update(ConnectedMinecraftPlayer player);

    void update(SidebarEntry entry);

    void update(SidebarEntry entry, ConnectedMinecraftPlayer player);


    static Sidebar newSidebar(){
        return McNative.getInstance().getObjectFactory().createObject(Sidebar.class);
    }

}
