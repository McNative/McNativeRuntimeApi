/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 11.08.19, 19:02
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

package org.mcnative.common.player.bossbar;

import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.text.format.TextColor;

import java.util.Collection;

public interface BossBar {

    String getTitle();

    BossBar setTitle(String title);


    TextColor getColor();

    BossBar setColor(TextColor color);


    BarStyle getStyle();

    BossBar setStyle(BarStyle style);


    Collection<BarFlag> getFlags();

    boolean hasFlag(BarFlag flag);

    BossBar addFlag(BarFlag flag);

    BossBar removeFlag(BarFlag flag);


    int getMaximum();

    BossBar setMaximum(int maximum);


    double getProgress();

    BossBar setProgress(double progress);


    boolean isVisible();

    BossBar setVisible(boolean visible);


    Collection<OnlineMinecraftPlayer> getPlayers();

    BossBar addPlayer(OnlineMinecraftPlayer player);

    BossBar removePlayer(OnlineMinecraftPlayer player);

    void clearPlayers();
}
