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

package org.mcnative.service.world;

import java.util.Collection;

public interface World extends WorldSequence{

    //WorldSettings?

    long getSeed();

    WorldBorder getBorder();

    int getMaximumHeight();


    Location getSpawnLocation();

    void setSpawnLocation(Location spawnLocation);


    long getTime();

    void setTime(Long time);

    long getFullTime();

    void setFullTime(Long time);


    boolean hasStorm();

    void setStorm(boolean storm);

    boolean isThundering();

    void setThundering(boolean thundering);


    Chunk getChunk(int x, int y, int z);

    Chunk getChunk(Point location);

    Collection<Chunk> getLoadedChunks();

    boolean isChunkInUse(int x, int z);

    boolean isChunkGenerated(int x, int z);


    Biome getBiom(int x, int y);

    Biome getBiom(Point point);

    void setBiom(int x, int y,Biome biome);

    void setBiom(Point point,Biome biome);


    void loadChunk(int x, int y);

    void loadChunk(Point location);

    void loadChunk(Chunk chunk);


    void unloadChunk(int x, int y);

    void unloadChunk(Point location);

    void unloadChunk(Chunk chunk);

    void tryUnloadChunk(int x, int y);

    void tryUnloadChunk(Point location);

    void tryUnloadChunk(Chunk chunk);


    void regenerateChunk(int x, int y);

    void regenerateChunk(Point point);

    void regenerateChunk(Chunk chunk);


    void refreshChunk(int x, int y);

    void refreshChunk(Point point);

    void refreshChunk(Chunk chunk);


    void save();
}
