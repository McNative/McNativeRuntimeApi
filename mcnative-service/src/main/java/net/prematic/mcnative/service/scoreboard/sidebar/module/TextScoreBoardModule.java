/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 23.07.19 16:11
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

public class TextScoreBoardModule implements ScoreboardModule{

    private final String name;
    private String text;

    public TextScoreBoardModule(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String[] render(Player player) {
        return new String[]{text};
    }
}
