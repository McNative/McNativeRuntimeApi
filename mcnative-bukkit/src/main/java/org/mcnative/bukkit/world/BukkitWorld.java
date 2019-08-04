/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:46
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

package org.mcnative.bukkit.world;


import net.prematic.mcnative.service.world.Chunk;
import net.prematic.mcnative.service.world.Location;
import org.bukkit.World;

import java.util.Collection;

public class BukkitWorld implements net.prematic.mcnative.service.world.World {

    private final World world;

    public BukkitWorld(World world) {
        this.world = world;
    }

    public Chunk getChunk(int x, int y, int z) {
        return null;
    }

    public Chunk getChunk(Location location) {
        return null;
    }

    public Collection<Chunk> getLoadedChunks() {
        return null;
    }

    public boolean isChunkInUse(int x, int z) {
        return false;
    }

    public void loadChunk(int x, int y) {

    }

    public void loadChunk(Location location) {

    }

    public void loadChunk(Chunk chunk) {

    }

    public void unloadChunk(int x, int y) {

    }

    public void unloadChunk(Location location) {

    }

    public void unloadChunk(Chunk chunk) {

    }
}
