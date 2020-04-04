/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.03.20, 13:20
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

package org.mcnative.bukkit.player.scoreboard;

import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.player.scoreboard.ScoreboardEntry;

public class PlayerScoreboardEntry implements ScoreboardEntry {

    private final MinecraftPlayer player;
    private final PlayerDesign design;

    public PlayerScoreboardEntry(MinecraftPlayer player) {
        this(player,null);
    }

    public PlayerScoreboardEntry(MinecraftPlayer player,PlayerDesign design) {
        this.player = player;
        this.design = design;
    }

    public MinecraftPlayer getPlayer() {
        return player;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    private PlayerDesign getDesign(){
        return null;// design != null ? design : player.getde
    }
}
