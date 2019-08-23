/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 14:52
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

package org.mcnative.common.player.scoreboard.deprecated;

import net.prematic.libraries.utility.annonations.NotNull;
import org.mcnative.common.player.scoreboard.deprecated.DisplayMode;
import org.mcnative.common.player.scoreboard.deprecated.Score;
import org.mcnative.common.player.scoreboard.deprecated.Scoreboard;

import java.util.Collection;

public interface ScoreboardObjective {

    @NotNull
    String getName();

    @NotNull
    Scoreboard getScoreboard();


    @NotNull
    String getDisplayName();

    void setDisplayName(@NotNull String displayName);


    @NotNull
    DisplayMode getDisplayMode();

    void setDisplayMode(@NotNull DisplayMode slot);

    @NotNull
    String getCriteria();

    void setCriteria(@NotNull String criteria);

    @NotNull
    Collection<Score> getScores();
    //change to int?
    @NotNull
    Score getScore(String entry);

    boolean hasScore(String entry);

    void resetScore(String entry);


    void unregister();
}
