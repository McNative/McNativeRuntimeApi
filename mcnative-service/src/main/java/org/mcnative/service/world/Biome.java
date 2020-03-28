/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.08.19, 19:06
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

import net.pretronic.libraries.utility.Iterators;

import java.util.HashSet;
import java.util.Set;

public class Biome {

    private static final Set<Biome> BIOMES = new HashSet<>();

    public static final Biome OCEAN = registerBiome("OCEAN");
    public static final Biome PLAINS = registerBiome("PLAINS");
    public static final Biome DESERT = registerBiome("DESERT");
    public static final Biome MOUNTAINS = registerBiome("MOUNTAINS");
    public static final Biome FOREST = registerBiome("FOREST");
    public static final Biome TAIGA = registerBiome("TAIGA");
    public static final Biome SWAMP = registerBiome("SWAMP");
    public static final Biome RIVER = registerBiome("RIVER");
    public static final Biome NETHER = registerBiome("NETHER");
    public static final Biome THE_END = registerBiome("THE_END");
    public static final Biome FROZEN_OCEAN = registerBiome("FROZEN_OCEAN");
    public static final Biome FROZEN_RIVER = registerBiome("FROZEN_RIVER");
    public static final Biome SNOWY_TUNDRA = registerBiome("SNOWY_TUNDRA");
    public static final Biome SNOWY_MOUNTAINS = registerBiome("SNOWY_MOUNTAINS");
    public static final Biome MUSHROOM_FIELDS = registerBiome("MUSHROOM_FIELDS");
    public static final Biome MUSHROOM_FIELD_SHORE = registerBiome("MUSHROOM_FIELD_SHORE");
    public static final Biome BEACH = registerBiome("BEACH");
    public static final Biome DESERT_HILLS = registerBiome("DESERT_HILLS");
    public static final Biome WOODED_HILLS = registerBiome("WOODED_HILLS");
    public static final Biome TAIGA_HILLS = registerBiome("TAIGA_HILLS");
    public static final Biome MOUNTAIN_EDGE = registerBiome("MOUNTAIN_EDGE");
    public static final Biome JUNGLE = registerBiome("JUNGLE");
    public static final Biome JUNGLE_HILLS = registerBiome("JUNGLE_HILLS");
    public static final Biome JUNGLE_EDGE = registerBiome("JUNGLE_EDGE");
    public static final Biome DEEP_OCEAN = registerBiome("DEEP_OCEAN");
    public static final Biome STONE_SHORE = registerBiome("STONE_SHORE");
    public static final Biome SNOWY_BEACH = registerBiome("SNOWY_BEACH");
    public static final Biome BIRCH_FOREST = registerBiome("BIRCH_FOREST");
    public static final Biome BIRCH_FOREST_HILLS = registerBiome("BIRCH_FOREST_HILLS");
    public static final Biome DARK_FOREST = registerBiome("DARK_FOREST");
    public static final Biome SNOWY_TAIGA = registerBiome("SNOWY_TAIGA");
    public static final Biome SNOWY_TAIGA_HILLS = registerBiome("SNOWY_TAIGA_HILLS");
    public static final Biome GIANT_TREE_TAIGA = registerBiome("GIANT_TREE_TAIGA");
    public static final Biome GIANT_TREE_TAIGA_HILLS = registerBiome("GIANT_TREE_TAIGA_HILLS");
    public static final Biome WOODED_MOUNTAINS = registerBiome("WOODED_MOUNTAINS");
    public static final Biome SAVANNA = registerBiome("SAVANNA");
    public static final Biome SAVANNA_PLATEAU = registerBiome("SAVANNA_PLATEAU");
    public static final Biome BADLANDS = registerBiome("BADLANDS");
    public static final Biome WOODED_BADLANDS_PLATEAU = registerBiome("WOODED_BADLANDS_PLATEAU");
    public static final Biome BADLANDS_PLATEAU = registerBiome("BADLANDS_PLATEAU");
    public static final Biome SMALL_END_ISLANDS = registerBiome("SMALL_END_ISLANDS");
    public static final Biome END_MIDLANDS = registerBiome("END_MIDLANDS");
    public static final Biome END_HIGHLANDS = registerBiome("END_HIGHLANDS");
    public static final Biome END_BARRENS = registerBiome("END_BARRENS");
    public static final Biome WARM_OCEAN = registerBiome("WARM_OCEAN");
    public static final Biome LUKEWARM_OCEAN = registerBiome("LUKEWARM_OCEAN");
    public static final Biome COLD_OCEAN = registerBiome("COLD_OCEAN");
    public static final Biome DEEP_WARM_OCEAN = registerBiome("DEEP_WARM_OCEAN");
    public static final Biome DEEP_LUKEWARM_OCEAN = registerBiome("DEEP_LUKEWARM_OCEAN");
    public static final Biome DEEP_COLD_OCEAN = registerBiome("DEEP_COLD_OCEAN");
    public static final Biome DEEP_FROZEN_OCEAN = registerBiome("DEEP_FROZEN_OCEAN");
    public static final Biome THE_VOID = registerBiome("THE_VOID");
    public static final Biome SUNFLOWER_PLAINS = registerBiome("SUNFLOWER_PLAINS");
    public static final Biome DESERT_LAKES = registerBiome("DESERT_LAKES");
    public static final Biome GRAVELLY_MOUNTAINS = registerBiome("GRAVELLY_MOUNTAINS");
    public static final Biome FLOWER_FOREST = registerBiome("FLOWER_FOREST");
    public static final Biome TAIGA_MOUNTAINS = registerBiome("TAIGA_MOUNTAINS");
    public static final Biome SWAMP_HILLS = registerBiome("SWAMP_HILLS");
    public static final Biome ICE_SPIKES = registerBiome("ICE_SPIKES");
    public static final Biome MODIFIED_JUNGLE = registerBiome("MODIFIED_JUNGLE");
    public static final Biome MODIFIED_JUNGLE_EDGE = registerBiome("MODIFIED_JUNGLE_EDGE");
    public static final Biome TALL_BIRCH_FOREST = registerBiome("TALL_BIRCH_FOREST");
    public static final Biome TALL_BIRCH_HILLS  = registerBiome("TALL_BIRCH_HILLS");
    public static final Biome DARK_FOREST_HILLS = registerBiome("DARK_FOREST_HILLS");
    public static final Biome SNOWY_TAIGA_MOUNTAINS = registerBiome("SNOWY_TAIGA_MOUNTAINS");
    public static final Biome GIANT_SPRUCE_TAIGA = registerBiome("GIANT_SPRUCE_TAIGA");
    public static final Biome GIANT_SPRUCE_TAIGA_HILLS = registerBiome("GIANT_SPRUCE_TAIGA_HILLS");
    public static final Biome MODIFIED_GRAVELLY_MOUNTAINS = registerBiome("MODIFIED_GRAVELLY_MOUNTAINS");
    public static final Biome SHATTERED_SAVANNA = registerBiome("SHATTERED_SAVANNA");
    public static final Biome SHATTERED_SAVANNA_PLATEAU = registerBiome("SHATTERED_SAVANNA_PLATEAU");
    public static final Biome ERODED_BADLANDS = registerBiome("ERODED_BADLANDS");
    public static final Biome MODIFIED_WOODED_BADLANDS_PLATEAU = registerBiome("MODIFIED_WOODED_BADLANDS_PLATEAU");
    public static final Biome MODIFIED_BADLANDS_PLATEAU = registerBiome("MODIFIED_BADLANDS_PLATEAU");
    public static final Biome BAMBOO_JUNGLE = registerBiome("BAMBOO_JUNGLE");
    public static final Biome BAMBOO_JUNGLE_HILLS = registerBiome("BAMBOO_JUNGLE_HILLS");


    private final String name;

    private Biome(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Biome getBiome(String name) {
        Biome biome = Iterators.findOne(BIOMES, biome0 -> biome0.getName().equals(name));
        if(biome == null) throw new IllegalArgumentException("Biome with name " + name + " not found");
        return biome;
    }

    public static Biome registerBiome(String name) {
        Biome biome = new Biome(name);
        BIOMES.add(biome);
        return biome;
    }

    public static void unregisterBiome(Biome biome) {
        BIOMES.remove(biome);
    }

    public static void unregisterBiome(String name) {
        unregisterBiome(getBiome(name));
    }
}
