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

package org.mcnative.service.world.block;

import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.location.Offset;
import org.mcnative.service.location.Vector;
import org.mcnative.service.material.Material;
import org.mcnative.service.world.Biome;
import org.mcnative.service.world.Chunk;
import org.mcnative.service.world.World;
import org.mcnative.service.world.block.data.BlockData;

import java.util.Collection;

public interface Block {

    World getWorld();

    Vector getLocation();


    Material getMaterial();

    void setMaterial(Material material);

    boolean isEmpty();

    boolean isLiquid();

    boolean isPassable();


    BlockData getData();

    void setData(BlockData data);


    Chunk getChunk();

    Biome getBiome();

    void setBiom(Biome biome);


    double getTemperature();

    double getHumidity();


    byte getLightLevel();

    byte getLightFromSky();

    byte getLightFromBlocks();


    Block getRelativeBlock(Offset offset);

    Block getRelativeBlock(BlockDirection direction);


    boolean isBlockPowered();

    boolean isBlockIndirectlyPowered();

    boolean isBlockDirectionPowered(BlockDirection direction);

    boolean isBlockIndirectlyPowered(BlockDirection direction);

    int getPower();

    int getPower(BlockDirection direction);


    void breakNaturally();

    void breakNaturally(ItemStack tool);


    Collection<ItemStack> getDrops();

    Collection<ItemStack> getDrops(ItemStack tool);

    boolean isAllowDrops();

    void setAllowDrop(boolean allowDrop);

    void update();
}
