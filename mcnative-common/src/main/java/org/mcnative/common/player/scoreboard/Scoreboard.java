/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 10.08.19, 13:11
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

import org.mcnative.common.McNative;

import java.util.Collection;

public interface Scoreboard {

    Collection<ScoreboardObjective> getObjectives();

    Collection<ScoreboardObjective> getObjectivesByCriteria(String criteria);

    default ScoreboardObjective registerNewObjective(String name, String criteria){
        return registerNewObjective(name,name, criteria);
    }

    ScoreboardObjective registerNewObjective(String name, String displayName, String criteria);//add object?

    ScoreboardObjective getObjective(String name);

    ScoreboardObjective getObjective(DisplayMode slot);

    void unregisterObjective(ScoreboardObjective objective);

    void unregisterObjective(String name);

    void unregisterObjective(DisplayMode slot);


    Collection<ScoreboardTeam> getTeams();

    ScoreboardTeam getTeam(String name);

    ScoreboardTeam getTeamByEntry(String entry);

    ScoreboardTeam registerNewTeam(String name);


    Collection<String> getEntries();

    Collection<Score> getScores(String entry);


    static Scoreboard newScoreboard(){
        return McNative.getInstance().getRegistry().create(Scoreboard.class);
    }

    static Scoreboard getMainScoreboard(){
        return McNative.getInstance().getRegistry().getInstance(Scoreboard.class,"MAIN");
    }

    /*
    Fragen:
    TextComponent - with extra
    Event - interface
    Scoreboard -
    Packets - interface


     */

}
