/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 12.09.19, 20:07
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

package org.mcnative.runtime.api.service.advancement;

import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.protocol.support.JEProtocolSupport;

@JEProtocolSupport(max = MinecraftProtocolVersion.JE_1_11_2)
public class Achievement implements Criteria {

    public static final Achievement OPEN_INVENTORY = createDefault("OPEN_INVENTORY", null);
    public static final Achievement MINE_WOOD = createDefault("MINE_WOOD", OPEN_INVENTORY);
    public static final Achievement BUILD_WORKBENCH = createDefault("BUILD_WORKBENCH", MINE_WOOD);
    public static final Achievement BUILD_PICKAXE = createDefault("BUILD_PICKAXE", BUILD_WORKBENCH);
    public static final Achievement BUILD_FURNACE = createDefault("BUILD_FURNACE", BUILD_PICKAXE);
    public static final Achievement ACQUIRE_IRON = createDefault("ACQUIRE_IRON", BUILD_FURNACE);
    public static final Achievement BUILD_HOE = createDefault("BUILD_HOE", BUILD_WORKBENCH);
    public static final Achievement BAKE_CAKE  = createDefault("BAKE_CAKE", BUILD_HOE);
    public static final Achievement BUILD_BETTER_PICKAXE = createDefault("BUILD_BETTER_PICKAXE", BUILD_PICKAXE);
    public static final Achievement COOK_FISH = createDefault("COOK_FISH", BUILD_FURNACE);
    public static final Achievement ON_A_RAIL = createDefault("ON_A_RAIL", ACQUIRE_IRON);
    public static final Achievement BUILD_SWORD = createDefault("BUILD_SWORD", BUILD_WORKBENCH);
    public static final Achievement KILL_ENEMY = createDefault("KILL_ENEMY", BUILD_SWORD);
    public static final Achievement KILL_COW = createDefault("KILL_COW", BUILD_SWORD);
    public static final Achievement FLY_PIG = createDefault("FLY_PIG", KILL_COW);
    public static final Achievement SNIPE_SKELETON = createDefault("SNIPE_SKELETON", KILL_ENEMY);
    public static final Achievement GET_DIAMONDS = createDefault("GET_DIAMONDS", ACQUIRE_IRON);
    public static final Achievement NETHER_PORTAL = createDefault("NETHER_PORTAL", GET_DIAMONDS);
    public static final Achievement GHAST_RETURN = createDefault("GHAST_RETURN", NETHER_PORTAL);
    public static final Achievement GET_BLAZE_ROD = createDefault("GET_BLAZE_ROD", NETHER_PORTAL);
    public static final Achievement BREW_POTION = createDefault("BREW_POTION", GET_BLAZE_ROD);
    public static final Achievement END_PORTAL = createDefault("END_PORTAL", GET_BLAZE_ROD);
    public static final Achievement THE_END = createDefault("THE_END", END_PORTAL);
    public static final Achievement ENCHANTMENTS = createDefault("ENCHANTMENTS", GET_DIAMONDS);
    public static final Achievement OVERKILL = createDefault("OVERKILL", ENCHANTMENTS);
    public static final Achievement BOOKCASE = createDefault("BOOKCASE", ENCHANTMENTS);
    public static final Achievement EXPLORE_ALL_BIOMES = createDefault("EXPLORE_ALL_BIOMES", END_PORTAL);
    public static final Achievement SPAWN_WITHER = createDefault("SPAWN_WITHER", THE_END);
    public static final Achievement KILL_WITHER = createDefault("KILL_WITHER", SPAWN_WITHER);
    public static final Achievement FULL_BEACON = createDefault("FULL_BEACON", KILL_WITHER);
    public static final Achievement BREED_COW = createDefault("BREED_COW", KILL_COW);
    public static final Achievement DIAMONDS_TO_YOU = createDefault("DIAMONDS_TO_YOU", GET_DIAMONDS);
    public static final Achievement OVERPOWERED = createDefault("OVERPOWERED", BUILD_BETTER_PICKAXE);

    private final String name;
    private final Criteria parent;

    public Achievement(String name, Criteria parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Criteria getParent() {
        return parent;
    }

    private static Achievement createDefault(String name, Criteria parent) {
        return new Achievement(name, parent);
    }
}
