/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 12:07
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

package org.mcnative.runtime.api.service.world;

import java.util.Collection;

public interface WorldSettings {

    int getMaximumHeight();

    void setMaximumHeight(int height);


    int getSeaLevel();

    void setSeaLevel(int seaLevel);


    Difficulty getDifficulty();

    void setDifficulty(Difficulty difficulty);


    Collection<String> getGameRules();

    boolean hasGameRule(String rule);

    String getGameRule(String rule);

    int getGameRuleAmount(String rule);

    boolean isGameRuleEnable(String rule);

    void setGameRule(String rule, Object value);


    int getAmbientSpawnLimit();

    void setAmbientSpawnLimit(int limit);


    int getWaterAnimalSpawnLimit();

    void setWaterAnimalSpawnLimit(int limit);


    int getMonsterSpawnLimit();

    void setMonsterSpawnLimit(int limit);


    long getTicksPerAnimalSpawns();

    void setTicksPerAnimalSpawns(int ticksPerAnimalSpawns);


    long getTicksPerMonsterSpawns();

    void setTicksPerMonsterSpawns(int ticksPerMonsterSpawns);


    boolean getAllowAnimals();

    boolean getAllowMonsters();
}
