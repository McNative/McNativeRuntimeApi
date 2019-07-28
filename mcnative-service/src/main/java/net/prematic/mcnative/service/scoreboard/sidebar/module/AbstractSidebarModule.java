/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 27.07.19 21:12
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

package net.prematic.mcnative.service.scoreboard.sidebar.module;

import net.prematic.mcnative.service.entity.Player;
import net.prematic.mcnative.service.scoreboard.sidebar.Sidebar;

public abstract class AbstractSidebarModule implements SidebarModule {

    private final String name;
    private Sidebar sidebar;

    public AbstractSidebarModule(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Sidebar getSidebar() {
        return sidebar;
    }

    @Override
    public void update() {
        sidebar.update(this);
    }

    @Override
    public void initialize(Sidebar sidebar) {
        if(sidebar != null) throw new IllegalArgumentException("Sidebar module is already initialized");
        this.sidebar = sidebar;
    }
}
