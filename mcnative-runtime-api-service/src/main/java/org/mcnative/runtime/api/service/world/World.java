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

package org.mcnative.runtime.api.service.world;

import org.mcnative.runtime.api.service.location.Location;
import org.mcnative.runtime.api.service.location.Vector;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

public interface World extends WorldSequence {

    String getName();

    UUID getUniqueId();

    long getSeed();

    WorldSettings getSettings();

    WorldEnvironment getEnvironment();

    WorldBorder getBorder();


    Location getSpawnLocation();

    void setSpawnLocation(Location spawnLocation);


    boolean isLoaded();

    void load();

    void unload();


    Chunk getChunk(int x, int z);

    Chunk getChunk(Vector location);

    Collection<Chunk> getLoadedChunks();

    Collection<Chunk> getForceLoadedChunks();

    Chunk loadChunk(int x, int z);

    Chunk loadChunk(int x, int z, boolean generate);

    Chunk loadChunk(Vector location);

    Chunk loadChunk(Vector location, boolean generate);

    Chunk loadChunk(Chunk chunk);

    long getTime();

    void setTime(long time);

    long getFullTime();

    void setFullTime(long time);


    boolean hasStorm();

    void setStorm(boolean storm);

    boolean isThundering();

    void setThundering(boolean thundering);


    int getThunderDuration();

    void setThunderDuration(int duration);

    int getWeatherDuration();

    void setWeatherDuration(int duration);


    boolean isAutoSave();

    void setAutoSave(boolean autoSave);

    void save();

    File getFolder();


    boolean isDefault();
}
