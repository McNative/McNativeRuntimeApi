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

import org.mcnative.runtime.api.service.inventory.item.material.Material;
import org.mcnative.runtime.api.service.world.block.Block;

public interface Chunk extends WorldSequence, Iterable<Block>{

    World getWorld();


    Biome getBiome();

    void setBiome(Biome biome);


    boolean isLoaded();

    boolean isInUse();

    boolean isGenerated();


    void load();

    void unload();

    void tryUnload();

    void refresh();

    void regenerate();


    boolean isForceLoaded();

    void setForceLoaded(boolean forceLoaded);


    void fill(Material material);

    void fill(int z, Material material);

    void fill(int startZ,int endZ, Material material);

    void clear();


}
