/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 23.08.19, 22:06
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

package org.mcnative.runtime.api.player.sound;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.ArrayList;
import java.util.Collection;

public class Sound {

    private static final Collection<Sound> SOUNDS = new ArrayList<>();

    public static final Sound AMBIENT_CAVE = createDefault("AMBIENT_CAVE");
    public static final Sound AMBIENT_UNDERWATER_ENTER = createDefault("AMBIENT_UNDERWATER_ENTER");
    public static final Sound AMBIENT_UNDERWATER_EXIT = createDefault("AMBIENT_UNDERWATER_EXIT");
    public static final Sound AMBIENT_UNDERWATER_LOOP = createDefault("AMBIENT_UNDERWATER_LOOP");
    public static final Sound AMBIENT_UNDERWATER_LOOP_ADDITIONS = createDefault("AMBIENT_UNDERWATER_LOOP_ADDITIONS");
    public static final Sound AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE = createDefault("AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE");
    public static final Sound AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE = createDefault("AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE");

    public static final Sound BLOCK_ANVIL_BREAK = createDefault("BLOCK_ANVIL_BREAK");
    public static final Sound BLOCK_ANVIL_DESTROY = createDefault("BLOCK_ANVIL_DESTROY");
    public static final Sound BLOCK_ANVIL_FALL = createDefault("BLOCK_ANVIL_FALL");
    public static final Sound BLOCK_ANVIL_HIT = createDefault("BLOCK_ANVIL_HIT");
    public static final Sound BLOCK_ANVIL_LAND = createDefault("BLOCK_ANVIL_LAND");
    public static final Sound BLOCK_ANVIL_PLACE = createDefault("BLOCK_ANVIL_PLACE");
    public static final Sound BLOCK_ANVIL_STEP = createDefault("BLOCK_ANVIL_STEP");
    public static final Sound BLOCK_ANVIL_USE = createDefault("BLOCK_ANVIL_USE");

    public static final Sound BLOCK_BAMBOO_BREAK = createDefault("BLOCK_BAMBOO_BREAK");
    public static final Sound BLOCK_BAMBOO_FALL = createDefault("BLOCK_BAMBOO_FALL");
    public static final Sound BLOCK_BAMBOO_HIT = createDefault("BLOCK_BAMBOO_HIT");
    public static final Sound BLOCK_BAMBOO_PLACE = createDefault("BLOCK_BAMBOO_PLACE");
    public static final Sound BLOCK_BAMBOO_SAPLING_BREAK = createDefault("BLOCK_BAMBOO_SAPLING_BREAK");
    public static final Sound BLOCK_BAMBOO_SAPLING_HIT = createDefault("BLOCK_BAMBOO_SAPLING_HIT");
    public static final Sound BLOCK_BAMBOO_SAPLING_PLACE = createDefault("BLOCK_BAMBOO_SAPLING_PLACE");
    public static final Sound BLOCK_BAMBOO_STEP = createDefault("BLOCK_BAMBOO_STEP");

    public static final Sound BLOCK_BARREL_CLOSE = createDefault("BLOCK_BARREL_CLOSE");
    public static final Sound BLOCK_BARREL_OPEN = createDefault("BLOCK_BARREL_OPEN");

    public static final Sound BLOCK_BEACON_ACTIVATE = createDefault("BLOCK_BEACON_ACTIVATE");
    public static final Sound BLOCK_BEACON_AMBIENT = createDefault("BLOCK_BEACON_AMBIENT");
    public static final Sound BLOCK_BEACON_DEACTIVATE = createDefault("BLOCK_BEACON_DEACTIVATE");
    public static final Sound BLOCK_BEACON_POWER_SELECT = createDefault("BLOCK_BEACON_POWER_SELECT");

    public static final Sound BLOCK_BELL_RESONATE = createDefault("BLOCK_BELL_RESONATE");
    public static final Sound BLOCK_BELL_USE = createDefault("BLOCK_BELL_USE");

    public static final Sound BLOCK_BLASTFURNACE_FIRE_CRACKLE = createDefault("BLOCK_BLASTFURNACE_FIRE_CRACKLE");

    public static final Sound BLOCK_BREWING_STAND_BREW = createDefault("BLOCK_BREWING_STAND_BREW");

    public static final Sound BLOCK_BUBBLE_COLUMN_BUBBLE_POP = createDefault("BLOCK_BUBBLE_COLUMN_BUBBLE_POP");
    public static final Sound BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT = createDefault("BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT");
    public static final Sound BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE = createDefault("BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE");
    public static final Sound BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT = createDefault("BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT");
    public static final Sound BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE = createDefault("BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE");

    public static final Sound BLOCK_CAMPFIRE_CRACKLE = createDefault("BLOCK_CAMPFIRE_CRACKLE");

    public static final Sound BLOCK_CHEST_CLOSE = createDefault("BLOCK_CHEST_CLOSE");
    public static final Sound BLOCK_CHEST_LOCKED = createDefault("BLOCK_CHEST_LOCKED");
    public static final Sound BLOCK_CHEST_OPEN = createDefault("BLOCK_CHEST_OPEN");

    public static final Sound BLOCK_CHORUS_FLOWER_DEATH = createDefault("BLOCK_CHORUS_FLOWER_DEATH");
    public static final Sound BLOCK_CHORUS_FLOWER_GROW = createDefault("BLOCK_CHORUS_FLOWER_GROW");

    public static final Sound BLOCK_COMPARATOR_CLICK = createDefault("BLOCK_COMPARATOR_CLICK");

    public static final Sound BLOCK_COMPOSTER_EMPTY = createDefault("BLOCK_COMPOSTER_EMPTY");
    public static final Sound BLOCK_COMPOSTER_FILL = createDefault("BLOCK_COMPOSTER_FILL");
    public static final Sound BLOCK_COMPOSTER_FILL_SUCCESS = createDefault("BLOCK_COMPOSTER_FILL_SUCCESS");
    public static final Sound BLOCK_COMPOSTER_READY = createDefault("BLOCK_COMPOSTER_READY");

    public static final Sound BLOCK_CONDUIT_ACTIVATE = createDefault("BLOCK_CONDUIT_ACTIVATE");
    public static final Sound BLOCK_CONDUIT_AMBIENT = createDefault("BLOCK_CONDUIT_AMBIENT");
    public static final Sound BLOCK_CONDUIT_AMBIENT_SHORT = createDefault("BLOCK_CONDUIT_AMBIENT_SHORT");
    public static final Sound BLOCK_CONDUIT_ATTACK_TARGET = createDefault("BLOCK_CONDUIT_ATTACK_TARGET");
    public static final Sound BLOCK_CONDUIT_DEACTIVATE = createDefault("BLOCK_CONDUIT_DEACTIVATE");

    public static final Sound BLOCK_CORAL_BLOCK_BREAK = createDefault("BLOCK_CORAL_BLOCK_BREAK");
    public static final Sound BLOCK_CORAL_BLOCK_FALL = createDefault("BLOCK_CORAL_BLOCK_FALL");
    public static final Sound BLOCK_CORAL_BLOCK_HIT = createDefault("BLOCK_CORAL_BLOCK_HIT");
    public static final Sound BLOCK_CORAL_BLOCK_PLACE = createDefault("BLOCK_CORAL_BLOCK_PLACE");
    public static final Sound BLOCK_CORAL_BLOCK_STEP = createDefault("BLOCK_CORAL_BLOCK_STEP");

    public static final Sound BLOCK_CROP_BREAK = createDefault("BLOCK_CROP_BREAK");

    public static final Sound BLOCK_DISPENSER_DISPENSE = createDefault("BLOCK_DISPENSER_DISPENSE");
    public static final Sound BLOCK_DISPENSER_FAIL = createDefault("BLOCK_DISPENSER_FAIL");
    public static final Sound BLOCK_DISPENSER_LAUNCH = createDefault("BLOCK_DISPENSER_LAUNCH");

    public static final Sound BLOCK_ENCHANTMENT_TABLE_USE = createDefault("BLOCK_ENCHANTMENT_TABLE_USE");

    public static final Sound BLOCK_ENDER_CHEST_CLOSE = createDefault("BLOCK_ENDER_CHEST_CLOSE");
    public static final Sound BLOCK_ENDER_CHEST_OPEN = createDefault("BLOCK_ENDER_CHEST_OPEN");

    public static final Sound BLOCK_END_GATEWAY_SPAWN = createDefault("BLOCK_END_GATEWAY_SPAWN");
    public static final Sound BLOCK_END_PORTAL_FRAME_FILL = createDefault("BLOCK_END_PORTAL_FRAME_FILL");
    public static final Sound BLOCK_END_PORTAL_SPAWN = createDefault("BLOCK_END_PORTAL_SPAWN");

    public static final Sound BLOCK_FENCE_GATE_CLOSE = createDefault("BLOCK_FENCE_GATE_CLOSE");
    public static final Sound BLOCK_FENCE_GATE_OPEN = createDefault("BLOCK_FENCE_GATE_OPEN");

    public static final Sound BLOCK_FIRE_AMBIENT = createDefault("BLOCK_FIRE_AMBIENT");
    public static final Sound BLOCK_FIRE_EXTINGUISH = createDefault("BLOCK_FIRE_EXTINGUISH");

    public static final Sound BLOCK_FURNACE_FIRE_CRACKLE = createDefault("BLOCK_FURNACE_FIRE_CRACKLE");
    public static final Sound BLOCK_GLASS_BREAK = createDefault("BLOCK_GLASS_BREAK");
    public static final Sound BLOCK_GLASS_FALL = createDefault("BLOCK_GLASS_FALL");
    public static final Sound BLOCK_GLASS_HIT = createDefault("BLOCK_GLASS_HIT");
    public static final Sound BLOCK_GLASS_PLACE = createDefault("BLOCK_GLASS_PLACE");
    public static final Sound BLOCK_GLASS_STEP = createDefault("BLOCK_GLASS_STEP");

    public static final Sound BLOCK_GRASS_BREAK = createDefault("BLOCK_GRASS_BREAK");
    public static final Sound BLOCK_GRASS_FALL = createDefault("BLOCK_GRASS_FALL");
    public static final Sound BLOCK_GRASS_HIT = createDefault("BLOCK_GRASS_HIT");
    public static final Sound BLOCK_GRASS_PLACE = createDefault("BLOCK_GRASS_PLACE");
    public static final Sound BLOCK_GRASS_STEP = createDefault("BLOCK_GRASS_STEP");

    public static final Sound BLOCK_GRAVEL_BREAK = createDefault("BLOCK_GRAVEL_BREAK");
    public static final Sound BLOCK_GRAVEL_FALL = createDefault("BLOCK_GRAVEL_FALL");
    public static final Sound BLOCK_GRAVEL_HIT = createDefault("BLOCK_GRAVEL_HIT");
    public static final Sound BLOCK_GRAVEL_PLACE = createDefault("BLOCK_GRAVEL_PLACE");
    public static final Sound BLOCK_GRAVEL_STEP = createDefault("BLOCK_GRAVEL_STEP");

    public static final Sound BLOCK_GRINDSTONE_USE = createDefault("BLOCK_GRINDSTONE_USE");

    public static final Sound BLOCK_IRON_DOOR_CLOSE = createDefault("BLOCK_IRON_DOOR_CLOSE");
    public static final Sound BLOCK_IRON_DOOR_OPEN = createDefault("BLOCK_IRON_DOOR_OPEN");
    public static final Sound BLOCK_IRON_TRAPDOOR_CLOSE = createDefault("BLOCK_IRON_TRAPDOOR_CLOSE");
    public static final Sound BLOCK_IRON_TRAPDOOR_OPEN = createDefault("BLOCK_IRON_TRAPDOOR_OPEN");

    public static final Sound BLOCK_LADDER_BREAK = createDefault("BLOCK_LADDER_BREAK");
    public static final Sound BLOCK_LADDER_FALL = createDefault("BLOCK_LADDER_FALL");
    public static final Sound BLOCK_LADDER_HIT = createDefault("BLOCK_LADDER_HIT");
    public static final Sound BLOCK_LADDER_PLACE = createDefault("BLOCK_LADDER_PLACE");
    public static final Sound BLOCK_LADDER_STEP = createDefault("BLOCK_LADDER_STEP");

    public static final Sound BLOCK_LANTERN_BREAK = createDefault("BLOCK_LANTERN_BREAK");
    public static final Sound BLOCK_LANTERN_FALL = createDefault("BLOCK_LANTERN_FALL");
    public static final Sound BLOCK_LANTERN_HIT = createDefault("BLOCK_LANTERN_HIT");
    public static final Sound BLOCK_LANTERN_PLACE = createDefault("BLOCK_LANTERN_PLACE");
    public static final Sound BLOCK_LANTERN_STEP = createDefault("BLOCK_LANTERN_STEP");

    public static final Sound BLOCK_LAVA_AMBIENT = createDefault("BLOCK_LAVA_AMBIENT");
    public static final Sound BLOCK_LAVA_EXTINGUISH = createDefault("BLOCK_LAVA_EXTINGUISH");
    public static final Sound BLOCK_LAVA_POP = createDefault("BLOCK_LAVA_POP");

    public static final Sound BLOCK_LEVER_CLICK = createDefault("BLOCK_LEVER_CLICK");

    public static final Sound BLOCK_LILY_PAD_PLACE = createDefault("BLOCK_LILY_PAD_PLACE");

    public static final Sound BLOCK_METAL_BREAK = createDefault("BLOCK_METAL_BREAK");
    public static final Sound BLOCK_METAL_FALL = createDefault("BLOCK_METAL_FALL");
    public static final Sound BLOCK_METAL_HIT = createDefault("BLOCK_METAL_HIT");
    public static final Sound BLOCK_METAL_PLACE = createDefault("BLOCK_METAL_PLACE");
    public static final Sound BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF = createDefault("BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF");
    public static final Sound BLOCK_METAL_PRESSURE_PLATE_CLICK_ON = createDefault("BLOCK_METAL_PRESSURE_PLATE_CLICK_ON");
    public static final Sound BLOCK_METAL_STEP = createDefault("BLOCK_METAL_STEP");

    public static final Sound BLOCK_NETHER_WART_BREAK = createDefault("BLOCK_NETHER_WART_BREAK");

    public static final Sound BLOCK_NOTE_BLOCK_BANJO = createDefault("BLOCK_NOTE_BLOCK_BANJO");
    public static final Sound BLOCK_NOTE_BLOCK_BASEDRUM = createDefault("BLOCK_NOTE_BLOCK_BASEDRUM");
    public static final Sound BLOCK_NOTE_BLOCK_BASS = createDefault("BLOCK_NOTE_BLOCK_BASS");
    public static final Sound BLOCK_NOTE_BLOCK_BELL = createDefault("BLOCK_NOTE_BLOCK_BELL");
    public static final Sound BLOCK_NOTE_BLOCK_BIT = createDefault("BLOCK_NOTE_BLOCK_BIT");
    public static final Sound BLOCK_NOTE_BLOCK_CHIME = createDefault("BLOCK_NOTE_BLOCK_CHIME");
    public static final Sound BLOCK_NOTE_BLOCK_COW_BELL = createDefault("BLOCK_NOTE_BLOCK_COW_BELL");
    public static final Sound BLOCK_NOTE_BLOCK_DIDGERIDOO = createDefault("BLOCK_NOTE_BLOCK_DIDGERIDOO");
    public static final Sound BLOCK_NOTE_BLOCK_FLUTE = createDefault("BLOCK_NOTE_BLOCK_FLUTE");
    public static final Sound BLOCK_NOTE_BLOCK_GUITAR = createDefault("BLOCK_NOTE_BLOCK_GUITAR");
    public static final Sound BLOCK_NOTE_BLOCK_HARP = createDefault("BLOCK_NOTE_BLOCK_HARP");
    public static final Sound BLOCK_NOTE_BLOCK_HAT = createDefault("BLOCK_NOTE_BLOCK_HAT");
    public static final Sound BLOCK_NOTE_BLOCK_IRON_XYLOPHONE = createDefault("BLOCK_NOTE_BLOCK_IRON_XYLOPHONE");
    public static final Sound BLOCK_NOTE_BLOCK_PLING = createDefault("BLOCK_NOTE_BLOCK_PLING");
    public static final Sound BLOCK_NOTE_BLOCK_SNARE = createDefault("BLOCK_NOTE_BLOCK_SNARE");
    public static final Sound BLOCK_NOTE_BLOCK_XYLOPHONE = createDefault("BLOCK_NOTE_BLOCK_XYLOPHONE");

    public static final Sound BLOCK_PISTON_CONTRACT = createDefault("BLOCK_PISTON_CONTRACT");
    public static final Sound BLOCK_PISTON_EXTEND = createDefault("BLOCK_PISTON_EXTEND");

    public static final Sound BLOCK_PORTAL_AMBIENT = createDefault("BLOCK_PORTAL_AMBIENT");
    public static final Sound BLOCK_PORTAL_TRAVEL = createDefault("BLOCK_PORTAL_TRAVEL");
    public static final Sound BLOCK_PORTAL_TRIGGER = createDefault("BLOCK_PORTAL_TRIGGER");

    public static final Sound BLOCK_PUMPKIN_CARVE = createDefault("BLOCK_PUMPKIN_CARVE");

    public static final Sound BLOCK_REDSTONE_TORCH_BURNOUT = createDefault("BLOCK_REDSTONE_TORCH_BURNOUT");

    public static final Sound BLOCK_SAND_BREAK = createDefault("BLOCK_SAND_BREAK");
    public static final Sound BLOCK_SAND_FALL = createDefault("BLOCK_SAND_FALL");
    public static final Sound BLOCK_SAND_HIT = createDefault("BLOCK_SAND_HIT");
    public static final Sound BLOCK_SAND_PLACE = createDefault("BLOCK_SAND_PLACE");
    public static final Sound BLOCK_SAND_STEP = createDefault("BLOCK_SAND_STEP");

    public static final Sound BLOCK_SCAFFOLDING_BREAK = createDefault("BLOCK_SCAFFOLDING_BREAK");
    public static final Sound BLOCK_SCAFFOLDING_FALL = createDefault("BLOCK_SCAFFOLDING_FALL");
    public static final Sound BLOCK_SCAFFOLDING_HIT = createDefault("BLOCK_SCAFFOLDING_HIT");
    public static final Sound BLOCK_SCAFFOLDING_PLACE = createDefault("BLOCK_SCAFFOLDING_PLACE");
    public static final Sound BLOCK_SCAFFOLDING_STEP = createDefault("BLOCK_SCAFFOLDING_STEP");

    public static final Sound BLOCK_SHULKER_BOX_CLOSE = createDefault("BLOCK_SHULKER_BOX_CLOSE");
    public static final Sound BLOCK_SHULKER_BOX_OPEN = createDefault("BLOCK_SHULKER_BOX_OPEN");

    public static final Sound BLOCK_SLIME_BLOCK_BREAK = createDefault("BLOCK_SLIME_BLOCK_BREAK");
    public static final Sound BLOCK_SLIME_BLOCK_FALL = createDefault("BLOCK_SLIME_BLOCK_FALL");
    public static final Sound BLOCK_SLIME_BLOCK_HIT = createDefault("BLOCK_SLIME_BLOCK_HIT");
    public static final Sound BLOCK_SLIME_BLOCK_PLACE = createDefault("BLOCK_SLIME_BLOCK_PLACE");
    public static final Sound BLOCK_SLIME_BLOCK_STEP = createDefault("BLOCK_SLIME_BLOCK_STEP");

    public static final Sound BLOCK_SMOKER_SMOKE = createDefault("BLOCK_SMOKER_SMOKE");

    public static final Sound BLOCK_SNOW_BREAK = createDefault("BLOCK_SNOW_BREAK");
    public static final Sound BLOCK_SNOW_FALL = createDefault("BLOCK_SNOW_FALL");
    public static final Sound BLOCK_SNOW_HIT = createDefault("BLOCK_SNOW_HIT");
    public static final Sound BLOCK_SNOW_PLACE = createDefault("BLOCK_SNOW_PLACE");
    public static final Sound BLOCK_SNOW_STEP = createDefault("BLOCK_SNOW_STEP");

    public static final Sound BLOCK_STONE_BREAK = createDefault("BLOCK_STONE_BREAK");
    public static final Sound BLOCK_STONE_BUTTON_CLICK_OFF = createDefault("BLOCK_STONE_BUTTON_CLICK_OFF");
    public static final Sound BLOCK_STONE_BUTTON_CLICK_ON = createDefault("BLOCK_STONE_BUTTON_CLICK_ON");
    public static final Sound BLOCK_STONE_FALL = createDefault("BLOCK_STONE_FALL");
    public static final Sound BLOCK_STONE_HIT = createDefault("BLOCK_STONE_HIT");
    public static final Sound BLOCK_STONE_PLACE = createDefault("BLOCK_STONE_PLACE");
    public static final Sound BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF = createDefault("BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF");
    public static final Sound BLOCK_STONE_PRESSURE_PLATE_CLICK_ON = createDefault("BLOCK_STONE_PRESSURE_PLATE_CLICK_ON");
    public static final Sound BLOCK_STONE_STEP = createDefault("BLOCK_STONE_STEP");

    public static final Sound BLOCK_SWEET_BERRY_BUSH_BREAK = createDefault("BLOCK_SWEET_BERRY_BUSH_BREAK");
    public static final Sound BLOCK_SWEET_BERRY_BUSH_PLACE = createDefault("BLOCK_SWEET_BERRY_BUSH_PLACE");

    public static final Sound BLOCK_TRIPWIRE_ATTACH = createDefault("BLOCK_TRIPWIRE_ATTACH");
    public static final Sound BLOCK_TRIPWIRE_CLICK_OFF = createDefault("BLOCK_TRIPWIRE_CLICK_OFF");
    public static final Sound BLOCK_TRIPWIRE_CLICK_ON = createDefault("BLOCK_TRIPWIRE_CLICK_ON");
    public static final Sound BLOCK_TRIPWIRE_DETACH = createDefault("BLOCK_TRIPWIRE_DETACH");

    public static final Sound BLOCK_WATER_AMBIENT = createDefault("BLOCK_WATER_AMBIENT");

    public static final Sound BLOCK_WET_GRASS_BREAK = createDefault("BLOCK_WET_GRASS_BREAK");
    public static final Sound BLOCK_WET_GRASS_FALL = createDefault("BLOCK_WET_GRASS_FALL");
    public static final Sound BLOCK_WET_GRASS_HIT = createDefault("BLOCK_WET_GRASS_HIT");
    public static final Sound BLOCK_WET_GRASS_PLACE = createDefault("BLOCK_WET_GRASS_PLACE");
    public static final Sound BLOCK_WET_GRASS_STEP = createDefault("BLOCK_WET_GRASS_STEP");

    public static final Sound BLOCK_WOODEN_BUTTON_CLICK_OFF = createDefault("BLOCK_WOODEN_BUTTON_CLICK_OFF");
    public static final Sound BLOCK_WOODEN_BUTTON_CLICK_ON = createDefault("BLOCK_WOODEN_BUTTON_CLICK_ON");
    public static final Sound BLOCK_WOODEN_DOOR_CLOSE = createDefault("BLOCK_WOODEN_DOOR_CLOSE");
    public static final Sound BLOCK_WOODEN_DOOR_OPEN = createDefault("BLOCK_WOODEN_DOOR_OPEN");
    public static final Sound BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF = createDefault("BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF");
    public static final Sound BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON = createDefault("BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON");
    public static final Sound BLOCK_WOODEN_TRAPDOOR_CLOSE = createDefault("BLOCK_WOODEN_TRAPDOOR_CLOSE");
    public static final Sound BLOCK_WOODEN_TRAPDOOR_OPEN = createDefault("BLOCK_WOODEN_TRAPDOOR_OPEN");

    public static final Sound BLOCK_WOOD_BREAK = createDefault("BLOCK_WOOD_BREAK");
    public static final Sound BLOCK_WOOD_FALL = createDefault("BLOCK_WOOD_FALL");
    public static final Sound BLOCK_WOOD_HIT = createDefault("BLOCK_WOOD_HIT");
    public static final Sound BLOCK_WOOD_PLACE = createDefault("BLOCK_WOOD_PLACE");
    public static final Sound BLOCK_WOOD_STEP = createDefault("BLOCK_WOOD_STEP");

    public static final Sound BLOCK_WOOL_BREAK = createDefault("BLOCK_WOOL_BREAK");
    public static final Sound BLOCK_WOOL_FALL = createDefault("BLOCK_WOOL_FALL");
    public static final Sound BLOCK_WOOL_HIT = createDefault("BLOCK_WOOL_HIT");
    public static final Sound BLOCK_WOOL_PLACE = createDefault("BLOCK_WOOL_PLACE");
    public static final Sound BLOCK_WOOL_STEP = createDefault("BLOCK_WOOL_STEP");

    public static final Sound ENCHANT_THORNS_HIT = createDefault("ENCHANT_THORNS_HIT");

    public static final Sound ENTITY_ARMOR_STAND_BREAK = createDefault("ENTITY_ARMOR_STAND_BREAK");
    public static final Sound ENTITY_ARMOR_STAND_FALL = createDefault("ENTITY_ARMOR_STAND_FALL");
    public static final Sound ENTITY_ARMOR_STAND_HIT = createDefault("ENTITY_ARMOR_STAND_HIT");
    public static final Sound ENTITY_ARMOR_STAND_PLACE = createDefault("ENTITY_ARMOR_STAND_PLACE");
    public static final Sound ENTITY_ARROW_HIT = createDefault("ENTITY_ARROW_HIT");
    public static final Sound ENTITY_ARROW_HIT_PLAYER = createDefault("ENTITY_ARROW_HIT_PLAYER");
    public static final Sound ENTITY_ARROW_SHOOT = createDefault("ENTITY_ARROW_SHOOT");

    public static final Sound ENTITY_BAT_AMBIENT = createDefault("ENTITY_BAT_AMBIENT");
    public static final Sound ENTITY_BAT_DEATH = createDefault("ENTITY_BAT_DEATH");
    public static final Sound ENTITY_BAT_HURT = createDefault("ENTITY_BAT_HURT");
    public static final Sound ENTITY_BAT_LOOP = createDefault("ENTITY_BAT_LOOP");
    public static final Sound ENTITY_BAT_TAKEOFF = createDefault("ENTITY_BAT_TAKEOFF");

    public static final Sound ENTITY_BLAZE_AMBIENT = createDefault("ENTITY_BLAZE_AMBIENT");
    public static final Sound ENTITY_BLAZE_BURN = createDefault("ENTITY_BLAZE_BURN");
    public static final Sound ENTITY_BLAZE_DEATH = createDefault("ENTITY_BLAZE_DEATH");
    public static final Sound ENTITY_BLAZE_HURT = createDefault("ENTITY_BLAZE_HURT");
    public static final Sound ENTITY_BLAZE_SHOOT = createDefault("ENTITY_BLAZE_SHOOT");

    public static final Sound ENTITY_BOAT_PADDLE_LAND = createDefault("ENTITY_BOAT_PADDLE_LAND");
    public static final Sound ENTITY_BOAT_PADDLE_WATER = createDefault("ENTITY_BOAT_PADDLE_WATER");

    public static final Sound ENTITY_CAT_AMBIENT = createDefault("ENTITY_CAT_AMBIENT");
    public static final Sound ENTITY_CAT_BEG_FOR_FOOD = createDefault("ENTITY_CAT_BEG_FOR_FOOD");
    public static final Sound ENTITY_CAT_DEATH = createDefault("ENTITY_CAT_DEATH");
    public static final Sound ENTITY_CAT_EAT = createDefault("ENTITY_CAT_EAT");
    public static final Sound ENTITY_CAT_HISS = createDefault("ENTITY_CAT_HISS");
    public static final Sound ENTITY_CAT_HURT = createDefault("ENTITY_CAT_HURT");
    public static final Sound ENTITY_CAT_PURR = createDefault("ENTITY_CAT_PURR");
    public static final Sound ENTITY_CAT_PURREOW = createDefault("ENTITY_CAT_PURREOW");
    public static final Sound ENTITY_CAT_STRAY_AMBIENT = createDefault("ENTITY_CAT_STRAY_AMBIENT");

    public static final Sound ENTITY_CHICKEN_AMBIENT = createDefault("ENTITY_CHICKEN_AMBIENT");
    public static final Sound ENTITY_CHICKEN_DEATH = createDefault("ENTITY_CHICKEN_DEATH");
    public static final Sound ENTITY_CHICKEN_EGG = createDefault("ENTITY_CHICKEN_EGG");
    public static final Sound ENTITY_CHICKEN_HURT = createDefault("ENTITY_CHICKEN_HURT");
    public static final Sound ENTITY_CHICKEN_STEP = createDefault("ENTITY_CHICKEN_STEP");

    public static final Sound ENTITY_COD_AMBIENT = createDefault("ENTITY_COD_AMBIENT");
    public static final Sound ENTITY_COD_DEATH = createDefault("ENTITY_COD_DEATH");
    public static final Sound ENTITY_COD_FLOP = createDefault("ENTITY_COD_FLOP");
    public static final Sound ENTITY_COD_HURT = createDefault("ENTITY_COD_HURT");
    public static final Sound ENTITY_COW_AMBIENT = createDefault("ENTITY_COW_AMBIENT");
    public static final Sound ENTITY_COW_DEATH = createDefault("ENTITY_COW_DEATH");
    public static final Sound ENTITY_COW_HURT = createDefault("ENTITY_COW_HURT");
    public static final Sound ENTITY_COW_MILK = createDefault("ENTITY_COW_MILK");
    public static final Sound ENTITY_COW_STEP = createDefault("ENTITY_COW_STEP");

    public static final Sound ENTITY_CREEPER_DEATH = createDefault("ENTITY_CREEPER_DEATH");
    public static final Sound ENTITY_CREEPER_HURT = createDefault("ENTITY_CREEPER_HURT");
    public static final Sound ENTITY_CREEPER_PRIMED = createDefault("ENTITY_CREEPER_PRIMED");

    public static final Sound ENTITY_DOLPHIN_AMBIENT = createDefault("ENTITY_DOLPHIN_AMBIENT");
    public static final Sound ENTITY_DOLPHIN_AMBIENT_WATER = createDefault("ENTITY_DOLPHIN_AMBIENT_WATER");
    public static final Sound ENTITY_DOLPHIN_ATTACK = createDefault("ENTITY_DOLPHIN_ATTACK");
    public static final Sound ENTITY_DOLPHIN_DEATH = createDefault("ENTITY_DOLPHIN_DEATH");
    public static final Sound ENTITY_DOLPHIN_EAT = createDefault("ENTITY_DOLPHIN_EAT");
    public static final Sound ENTITY_DOLPHIN_HURT = createDefault("ENTITY_DOLPHIN_HURT");
    public static final Sound ENTITY_DOLPHIN_JUMP = createDefault("ENTITY_DOLPHIN_JUMP");
    public static final Sound ENTITY_DOLPHIN_PLAY = createDefault("ENTITY_DOLPHIN_PLAY");
    public static final Sound ENTITY_DOLPHIN_SPLASH = createDefault("ENTITY_DOLPHIN_SPLASH");
    public static final Sound ENTITY_DOLPHIN_SWIM = createDefault("ENTITY_DOLPHIN_SWIM");

    public static final Sound ENTITY_DONKEY_AMBIENT = createDefault("ENTITY_DONKEY_AMBIENT");
    public static final Sound ENTITY_DONKEY_ANGRY = createDefault("ENTITY_DONKEY_ANGRY");
    public static final Sound ENTITY_DONKEY_CHEST = createDefault("ENTITY_DONKEY_CHEST");
    public static final Sound ENTITY_DONKEY_DEATH = createDefault("ENTITY_DONKEY_DEATH");
    public static final Sound ENTITY_DONKEY_HURT = createDefault("ENTITY_DONKEY_HURT");

    public static final Sound ENTITY_DRAGON_FIREBALL_EXPLODE = createDefault("ENTITY_DRAGON_FIREBALL_EXPLODE");

    public static final Sound ENTITY_DROWNED_AMBIENT = createDefault("ENTITY_DROWNED_AMBIENT");
    public static final Sound ENTITY_DROWNED_AMBIENT_WATER = createDefault("ENTITY_DROWNED_AMBIENT_WATER");
    public static final Sound ENTITY_DROWNED_DEATH = createDefault("ENTITY_DROWNED_DEATH");
    public static final Sound ENTITY_DROWNED_DEATH_WATER = createDefault("ENTITY_DROWNED_DEATH_WATER");
    public static final Sound ENTITY_DROWNED_HURT = createDefault("ENTITY_DROWNED_HURT");
    public static final Sound ENTITY_DROWNED_HURT_WATER = createDefault("ENTITY_DROWNED_HURT_WATER");
    public static final Sound ENTITY_DROWNED_SHOOT = createDefault("ENTITY_DROWNED_SHOOT");
    public static final Sound ENTITY_DROWNED_STEP = createDefault("ENTITY_DROWNED_STEP");
    public static final Sound ENTITY_DROWNED_SWIM = createDefault("ENTITY_DROWNED_SWIM");

    public static final Sound ENTITY_EGG_THROW = createDefault("ENTITY_EGG_THROW");

    public static final Sound ENTITY_ELDER_GUARDIAN_AMBIENT = createDefault("ENTITY_ELDER_GUARDIAN_AMBIENT");
    public static final Sound ENTITY_ELDER_GUARDIAN_AMBIENT_LAND = createDefault("ENTITY_ELDER_GUARDIAN_AMBIENT_LAND");
    public static final Sound ENTITY_ELDER_GUARDIAN_CURSE = createDefault("ENTITY_ELDER_GUARDIAN_CURSE");
    public static final Sound ENTITY_ELDER_GUARDIAN_DEATH = createDefault("ENTITY_ELDER_GUARDIAN_DEATH");
    public static final Sound ENTITY_ELDER_GUARDIAN_DEATH_LAND = createDefault("ENTITY_ELDER_GUARDIAN_DEATH_LAND");
    public static final Sound ENTITY_ELDER_GUARDIAN_FLOP = createDefault("ENTITY_ELDER_GUARDIAN_FLOP");
    public static final Sound ENTITY_ELDER_GUARDIAN_HURT = createDefault("ENTITY_ELDER_GUARDIAN_HURT");
    public static final Sound ENTITY_ELDER_GUARDIAN_HURT_LAND = createDefault("ENTITY_ELDER_GUARDIAN_HURT_LAND");

    public static final Sound ENTITY_ENDERMAN_AMBIENT = createDefault("ENTITY_ENDERMAN_AMBIENT");
    public static final Sound ENTITY_ENDERMAN_DEATH = createDefault("ENTITY_ENDERMAN_DEATH");
    public static final Sound ENTITY_ENDERMAN_HURT = createDefault("ENTITY_ENDERMAN_HURT");
    public static final Sound ENTITY_ENDERMAN_SCREAM = createDefault("ENTITY_ENDERMAN_SCREAM");
    public static final Sound ENTITY_ENDERMAN_STARE = createDefault("ENTITY_ENDERMAN_STARE");
    public static final Sound ENTITY_ENDERMAN_TELEPORT = createDefault("ENTITY_ENDERMAN_TELEPORT");
    public static final Sound ENTITY_ENDERMITE_AMBIENT = createDefault("ENTITY_ENDERMITE_AMBIENT");
    public static final Sound ENTITY_ENDERMITE_DEATH = createDefault("ENTITY_ENDERMITE_DEATH");
    public static final Sound ENTITY_ENDERMITE_HURT = createDefault("ENTITY_ENDERMITE_HURT");
    public static final Sound ENTITY_ENDERMITE_STEP = createDefault("ENTITY_ENDERMITE_STEP");

    public static final Sound ENTITY_ENDER_DRAGON_AMBIENT = createDefault("ENTITY_ENDER_DRAGON_AMBIENT");
    public static final Sound ENTITY_ENDER_DRAGON_DEATH = createDefault("ENTITY_ENDER_DRAGON_DEATH");
    public static final Sound ENTITY_ENDER_DRAGON_FLAP = createDefault("ENTITY_ENDER_DRAGON_FLAP");
    public static final Sound ENTITY_ENDER_DRAGON_GROWL = createDefault("ENTITY_ENDER_DRAGON_GROWL");
    public static final Sound ENTITY_ENDER_DRAGON_HURT = createDefault("ENTITY_ENDER_DRAGON_HURT");
    public static final Sound ENTITY_ENDER_DRAGON_SHOOT = createDefault("ENTITY_ENDER_DRAGON_SHOOT");
    public static final Sound ENTITY_ENDER_EYE_DEATH = createDefault("ENTITY_ENDER_EYE_DEATH");
    public static final Sound ENTITY_ENDER_EYE_LAUNCH = createDefault("ENTITY_ENDER_EYE_LAUNCH");
    public static final Sound ENTITY_ENDER_PEARL_THROW = createDefault("ENTITY_ENDER_PEARL_THROW");

    public static final Sound ENTITY_EVOKER_AMBIENT = createDefault("ENTITY_EVOKER_AMBIENT");
    public static final Sound ENTITY_EVOKER_CAST_SPELL = createDefault("ENTITY_EVOKER_CAST_SPELL");
    public static final Sound ENTITY_EVOKER_CELEBRATE = createDefault("ENTITY_EVOKER_CELEBRATE");
    public static final Sound ENTITY_EVOKER_DEATH = createDefault("ENTITY_EVOKER_DEATH");
    public static final Sound ENTITY_EVOKER_FANGS_ATTACK = createDefault("ENTITY_EVOKER_FANGS_ATTACK");
    public static final Sound ENTITY_EVOKER_HURT = createDefault("ENTITY_EVOKER_HURT");
    public static final Sound ENTITY_EVOKER_PREPARE_ATTACK = createDefault("ENTITY_EVOKER_PREPARE_ATTACK");
    public static final Sound ENTITY_EVOKER_PREPARE_SUMMON = createDefault("ENTITY_EVOKER_PREPARE_SUMMON");
    public static final Sound ENTITY_EVOKER_PREPARE_WOLOLO = createDefault("ENTITY_EVOKER_PREPARE_WOLOLO");

    public static final Sound ENTITY_EXPERIENCE_BOTTLE_THROW = createDefault("ENTITY_EXPERIENCE_BOTTLE_THROW");
    public static final Sound ENTITY_EXPERIENCE_ORB_PICKUP = createDefault("ENTITY_EXPERIENCE_ORB_PICKUP");

    public static final Sound ENTITY_FIREWORK_ROCKET_BLAST = createDefault("ENTITY_FIREWORK_ROCKET_BLAST");
    public static final Sound ENTITY_FIREWORK_ROCKET_BLAST_FAR = createDefault("ENTITY_FIREWORK_ROCKET_BLAST_FAR");
    public static final Sound ENTITY_FIREWORK_ROCKET_LARGE_BLAST = createDefault("ENTITY_FIREWORK_ROCKET_LARGE_BLAST");
    public static final Sound ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR = createDefault("ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR");
    public static final Sound ENTITY_FIREWORK_ROCKET_LAUNCH = createDefault("ENTITY_FIREWORK_ROCKET_LAUNCH");
    public static final Sound ENTITY_FIREWORK_ROCKET_SHOOT = createDefault("ENTITY_FIREWORK_ROCKET_SHOOT");
    public static final Sound ENTITY_FIREWORK_ROCKET_TWINKLE = createDefault("ENTITY_FIREWORK_ROCKET_TWINKLE");
    public static final Sound ENTITY_FIREWORK_ROCKET_TWINKLE_FAR = createDefault("ENTITY_FIREWORK_ROCKET_TWINKLE_FAR");

    public static final Sound ENTITY_FISHING_BOBBER_RETRIEVE = createDefault("ENTITY_FISHING_BOBBER_RETRIEVE");
    public static final Sound ENTITY_FISHING_BOBBER_SPLASH = createDefault("ENTITY_FISHING_BOBBER_SPLASH");
    public static final Sound ENTITY_FISHING_BOBBER_THROW = createDefault("ENTITY_FISHING_BOBBER_THROW");
    public static final Sound ENTITY_FISH_SWIM = createDefault("ENTITY_FISH_SWIM");

    public static final Sound ENTITY_FOX_AGGRO = createDefault("ENTITY_FOX_AGGRO");
    public static final Sound ENTITY_FOX_AMBIENT = createDefault("ENTITY_FOX_AMBIENT");
    public static final Sound ENTITY_FOX_BITE = createDefault("ENTITY_FOX_BITE");
    public static final Sound ENTITY_FOX_DEATH = createDefault("ENTITY_FOX_DEATH");
    public static final Sound ENTITY_FOX_EAT = createDefault("ENTITY_FOX_EAT");
    public static final Sound ENTITY_FOX_HURT = createDefault("ENTITY_FOX_HURT");
    public static final Sound ENTITY_FOX_SCREECH = createDefault("ENTITY_FOX_SCREECH");
    public static final Sound ENTITY_FOX_SNIFF = createDefault("ENTITY_FOX_SNIFF");
    public static final Sound ENTITY_FOX_SPIT = createDefault("ENTITY_FOX_SPIT");

    public static final Sound ENTITY_GENERIC_BIG_FALL = createDefault("ENTITY_GENERIC_BIG_FALL");
    public static final Sound ENTITY_GENERIC_BURN = createDefault("ENTITY_GENERIC_BURN");
    public static final Sound ENTITY_GENERIC_DEATH = createDefault("ENTITY_GENERIC_DEATH");
    public static final Sound ENTITY_GENERIC_DRINK = createDefault("ENTITY_GENERIC_DRINK");
    public static final Sound ENTITY_GENERIC_EAT = createDefault("ENTITY_GENERIC_EAT");
    public static final Sound ENTITY_GENERIC_EXPLODE = createDefault("ENTITY_GENERIC_EXPLODE");
    public static final Sound ENTITY_GENERIC_EXTINGUISH_FIRE = createDefault("ENTITY_GENERIC_EXTINGUISH_FIRE");
    public static final Sound ENTITY_GENERIC_HURT = createDefault("ENTITY_GENERIC_HURT");
    public static final Sound ENTITY_GENERIC_SMALL_FALL = createDefault("ENTITY_GENERIC_SMALL_FALL");
    public static final Sound ENTITY_GENERIC_SPLASH = createDefault("ENTITY_GENERIC_SPLASH");
    public static final Sound ENTITY_GENERIC_SWIM = createDefault("ENTITY_GENERIC_SWIM");

    public static final Sound ENTITY_GHAST_AMBIENT = createDefault("ENTITY_GHAST_AMBIENT");
    public static final Sound ENTITY_GHAST_DEATH = createDefault("ENTITY_GHAST_DEATH");
    public static final Sound ENTITY_GHAST_HURT = createDefault("ENTITY_GHAST_HURT");
    public static final Sound ENTITY_GHAST_SCREAM = createDefault("ENTITY_GHAST_SCREAM");
    public static final Sound ENTITY_GHAST_SHOOT = createDefault("ENTITY_GHAST_SHOOT");
    public static final Sound ENTITY_GHAST_WARN = createDefault("ENTITY_GHAST_WARN");

    public static final Sound ENTITY_GUARDIAN_AMBIENT = createDefault("ENTITY_GUARDIAN_AMBIENT");
    public static final Sound ENTITY_GUARDIAN_AMBIENT_LAND = createDefault("ENTITY_GUARDIAN_AMBIENT_LAND");
    public static final Sound ENTITY_GUARDIAN_ATTACK = createDefault("ENTITY_GUARDIAN_ATTACK");
    public static final Sound ENTITY_GUARDIAN_DEATH = createDefault("ENTITY_GUARDIAN_DEATH");
    public static final Sound ENTITY_GUARDIAN_DEATH_LAND = createDefault("ENTITY_GUARDIAN_DEATH_LAND");
    public static final Sound ENTITY_GUARDIAN_FLOP = createDefault("ENTITY_GUARDIAN_FLOP");
    public static final Sound ENTITY_GUARDIAN_HURT = createDefault("ENTITY_GUARDIAN_HURT");
    public static final Sound ENTITY_GUARDIAN_HURT_LAND = createDefault("ENTITY_GUARDIAN_HURT_LAND");

    public static final Sound ENTITY_HORSE_AMBIENT = createDefault("ENTITY_HORSE_AMBIENT");
    public static final Sound ENTITY_HORSE_ANGRY = createDefault("ENTITY_HORSE_ANGRY");
    public static final Sound ENTITY_HORSE_ARMOR = createDefault("ENTITY_HORSE_ARMOR");
    public static final Sound ENTITY_HORSE_BREATHE = createDefault("ENTITY_HORSE_BREATHE");
    public static final Sound ENTITY_HORSE_DEATH = createDefault("ENTITY_HORSE_DEATH");
    public static final Sound ENTITY_HORSE_EAT = createDefault("ENTITY_HORSE_EAT");
    public static final Sound ENTITY_HORSE_GALLOP = createDefault("ENTITY_HORSE_GALLOP");
    public static final Sound ENTITY_HORSE_HURT = createDefault("ENTITY_HORSE_HURT");
    public static final Sound ENTITY_HORSE_JUMP = createDefault("ENTITY_HORSE_JUMP");
    public static final Sound ENTITY_HORSE_LAND = createDefault("ENTITY_HORSE_LAND");
    public static final Sound ENTITY_HORSE_SADDLE = createDefault("ENTITY_HORSE_SADDLE");
    public static final Sound ENTITY_HORSE_STEP = createDefault("ENTITY_HORSE_STEP");
    public static final Sound ENTITY_HORSE_STEP_WOOD = createDefault("ENTITY_HORSE_STEP_WOOD");

    public static final Sound ENTITY_HOSTILE_BIG_FALL = createDefault("ENTITY_HOSTILE_BIG_FALL");
    public static final Sound ENTITY_HOSTILE_DEATH = createDefault("ENTITY_HOSTILE_DEATH");
    public static final Sound ENTITY_HOSTILE_HURT = createDefault("ENTITY_HOSTILE_HURT");
    public static final Sound ENTITY_HOSTILE_SMALL_FALL = createDefault("ENTITY_HOSTILE_SMALL_FALL");
    public static final Sound ENTITY_HOSTILE_SPLASH = createDefault("ENTITY_HOSTILE_SPLASH");
    public static final Sound ENTITY_HOSTILE_SWIM = createDefault("ENTITY_HOSTILE_SWIM");

    public static final Sound ENTITY_HUSK_AMBIENT = createDefault("ENTITY_HUSK_AMBIENT");
    public static final Sound ENTITY_HUSK_CONVERTED_TO_ZOMBIE = createDefault("ENTITY_HUSK_CONVERTED_TO_ZOMBIE");
    public static final Sound ENTITY_HUSK_DEATH = createDefault("ENTITY_HUSK_DEATH");
    public static final Sound ENTITY_HUSK_HURT = createDefault("ENTITY_HUSK_HURT");
    public static final Sound ENTITY_HUSK_STEP = createDefault("ENTITY_HUSK_STEP");

    public static final Sound ENTITY_ILLUSIONER_AMBIENT = createDefault("ENTITY_ILLUSIONER_AMBIENT");
    public static final Sound ENTITY_ILLUSIONER_CAST_SPELL = createDefault("ENTITY_ILLUSIONER_CAST_SPELL");
    public static final Sound ENTITY_ILLUSIONER_DEATH = createDefault("ENTITY_ILLUSIONER_DEATH");
    public static final Sound ENTITY_ILLUSIONER_HURT = createDefault("ENTITY_ILLUSIONER_HURT");
    public static final Sound ENTITY_ILLUSIONER_MIRROR_MOVE = createDefault("ENTITY_ILLUSIONER_MIRROR_MOVE");
    public static final Sound ENTITY_ILLUSIONER_PREPARE_BLINDNESS = createDefault("ENTITY_ILLUSIONER_PREPARE_BLINDNESS");
    public static final Sound ENTITY_ILLUSIONER_PREPARE_MIRROR = createDefault("ENTITY_ILLUSIONER_PREPARE_MIRROR");

    public static final Sound ENTITY_IRON_GOLEM_ATTACK = createDefault("ENTITY_IRON_GOLEM_ATTACK");
    public static final Sound ENTITY_IRON_GOLEM_DEATH = createDefault("ENTITY_IRON_GOLEM_DEATH");
    public static final Sound ENTITY_IRON_GOLEM_HURT = createDefault("ENTITY_IRON_GOLEM_HURT");
    public static final Sound ENTITY_IRON_GOLEM_STEP = createDefault("ENTITY_IRON_GOLEM_STEP");

    public static final Sound ENTITY_ITEM_BREAK = createDefault("ENTITY_ITEM_BREAK");
    public static final Sound ENTITY_ITEM_FRAME_ADD_ITEM = createDefault("ENTITY_ITEM_FRAME_ADD_ITEM");
    public static final Sound ENTITY_ITEM_FRAME_BREAK = createDefault("ENTITY_ITEM_FRAME_BREAK");
    public static final Sound ENTITY_ITEM_FRAME_PLACE = createDefault("ENTITY_ITEM_FRAME_PLACE");
    public static final Sound ENTITY_ITEM_FRAME_REMOVE_ITEM = createDefault("ENTITY_ITEM_FRAME_REMOVE_ITEM");
    public static final Sound ENTITY_ITEM_FRAME_ROTATE_ITEM = createDefault("ENTITY_ITEM_FRAME_ROTATE_ITEM");
    public static final Sound ENTITY_ITEM_PICKUP = createDefault("ENTITY_ITEM_PICKUP");

    public static final Sound ENTITY_LEASH_KNOT_BREAK = createDefault("ENTITY_LEASH_KNOT_BREAK");
    public static final Sound ENTITY_LEASH_KNOT_PLACE = createDefault("ENTITY_LEASH_KNOT_PLACE");

    public static final Sound ENTITY_LIGHTNING_BOLT_IMPACT = createDefault("ENTITY_LIGHTNING_BOLT_IMPACT");
    public static final Sound ENTITY_LIGHTNING_BOLT_THUNDER = createDefault("ENTITY_LIGHTNING_BOLT_THUNDER");
    public static final Sound ENTITY_LINGERING_POTION_THROW = createDefault("ENTITY_LINGERING_POTION_THROW");

    public static final Sound ENTITY_LLAMA_AMBIENT = createDefault("ENTITY_LLAMA_AMBIENT");
    public static final Sound ENTITY_LLAMA_ANGRY = createDefault("ENTITY_LLAMA_ANGRY");
    public static final Sound ENTITY_LLAMA_CHEST = createDefault("ENTITY_LLAMA_CHEST");
    public static final Sound ENTITY_LLAMA_DEATH = createDefault("ENTITY_LLAMA_DEATH");
    public static final Sound ENTITY_LLAMA_EAT = createDefault("ENTITY_LLAMA_EAT");
    public static final Sound ENTITY_LLAMA_HURT = createDefault("ENTITY_LLAMA_HURT");
    public static final Sound ENTITY_LLAMA_SPIT = createDefault("ENTITY_LLAMA_SPIT");
    public static final Sound ENTITY_LLAMA_STEP = createDefault("ENTITY_LLAMA_STEP");
    public static final Sound ENTITY_LLAMA_SWAG = createDefault("ENTITY_LLAMA_SWAG");

    public static final Sound ENTITY_MAGMA_CUBE_DEATH = createDefault("ENTITY_MAGMA_CUBE_DEATH");
    public static final Sound ENTITY_MAGMA_CUBE_DEATH_SMALL = createDefault("ENTITY_MAGMA_CUBE_DEATH_SMALL");
    public static final Sound ENTITY_MAGMA_CUBE_HURT = createDefault("ENTITY_MAGMA_CUBE_HURT");
    public static final Sound ENTITY_MAGMA_CUBE_HURT_SMALL = createDefault("ENTITY_MAGMA_CUBE_HURT_SMALL");
    public static final Sound ENTITY_MAGMA_CUBE_JUMP = createDefault("ENTITY_MAGMA_CUBE_JUMP");
    public static final Sound ENTITY_MAGMA_CUBE_SQUISH = createDefault("ENTITY_MAGMA_CUBE_SQUISH");
    public static final Sound ENTITY_MAGMA_CUBE_SQUISH_SMALL = createDefault("ENTITY_MAGMA_CUBE_SQUISH_SMALL");

    public static final Sound ENTITY_MINECART_INSIDE = createDefault("ENTITY_MINECART_INSIDE");
    public static final Sound ENTITY_MINECART_RIDING = createDefault("ENTITY_MINECART_RIDING");
    public static final Sound ENTITY_MOOSHROOM_CONVERT = createDefault("ENTITY_MOOSHROOM_CONVERT");
    public static final Sound ENTITY_MOOSHROOM_EAT = createDefault("ENTITY_MOOSHROOM_EAT");
    public static final Sound ENTITY_MOOSHROOM_MILK = createDefault("ENTITY_MOOSHROOM_MILK");
    public static final Sound ENTITY_MOOSHROOM_SHEAR = createDefault("ENTITY_MOOSHROOM_SHEAR");
    public static final Sound ENTITY_MOOSHROOM_SUSPICIOUS_MILK = createDefault("ENTITY_MOOSHROOM_SUSPICIOUS_MILK");

    public static final Sound ENTITY_MULE_AMBIENT = createDefault("ENTITY_MULE_AMBIENT");
    public static final Sound ENTITY_MULE_CHEST = createDefault("ENTITY_MULE_CHEST");
    public static final Sound ENTITY_MULE_DEATH = createDefault("ENTITY_MULE_DEATH");
    public static final Sound ENTITY_MULE_HURT = createDefault("ENTITY_MULE_HURT");

    public static final Sound ENTITY_OCELOT_AMBIENT = createDefault("ENTITY_OCELOT_AMBIENT");
    public static final Sound ENTITY_OCELOT_DEATH = createDefault("ENTITY_OCELOT_DEATH");
    public static final Sound ENTITY_OCELOT_HURT = createDefault("ENTITY_OCELOT_HURT");

    public static final Sound ENTITY_PAINTING_BREAK = createDefault("ENTITY_PAINTING_BREAK");
    public static final Sound ENTITY_PAINTING_PLACE = createDefault("ENTITY_PAINTING_PLACE");

    public static final Sound ENTITY_PANDA_AGGRESSIVE_AMBIENT = createDefault("ENTITY_PANDA_AGGRESSIVE_AMBIENT");
    public static final Sound ENTITY_PANDA_AMBIENT = createDefault("ENTITY_PANDA_AMBIENT");
    public static final Sound ENTITY_PANDA_BITE = createDefault("ENTITY_PANDA_BITE");
    public static final Sound ENTITY_PANDA_CANT_BREED = createDefault("ENTITY_PANDA_CANT_BREED");
    public static final Sound ENTITY_PANDA_DEATH = createDefault("ENTITY_PANDA_DEATH");
    public static final Sound ENTITY_PANDA_EAT = createDefault("ENTITY_PANDA_EAT");
    public static final Sound ENTITY_PANDA_HURT = createDefault("ENTITY_PANDA_HURT");
    public static final Sound ENTITY_PANDA_PRE_SNEEZE = createDefault("ENTITY_PANDA_PRE_SNEEZE");
    public static final Sound ENTITY_PANDA_SNEEZE = createDefault("ENTITY_PANDA_SNEEZE");
    public static final Sound ENTITY_PANDA_STEP = createDefault("ENTITY_PANDA_STEP");
    public static final Sound ENTITY_PANDA_WORRIED_AMBIENT = createDefault("ENTITY_PANDA_WORRIED_AMBIENT");

    public static final Sound ENTITY_PARROT_AMBIENT = createDefault("ENTITY_PARROT_AMBIENT");
    public static final Sound ENTITY_PARROT_DEATH = createDefault("ENTITY_PARROT_DEATH");
    public static final Sound ENTITY_PARROT_EAT = createDefault("ENTITY_PARROT_EAT");
    public static final Sound ENTITY_PARROT_FLY = createDefault("ENTITY_PARROT_FLY");
    public static final Sound ENTITY_PARROT_HURT = createDefault("ENTITY_PARROT_HURT");
    public static final Sound ENTITY_PARROT_IMITATE_BLAZE = createDefault("ENTITY_PARROT_IMITATE_BLAZE");
    public static final Sound ENTITY_PARROT_IMITATE_CREEPER = createDefault("ENTITY_PARROT_IMITATE_CREEPER");
    public static final Sound ENTITY_PARROT_IMITATE_DROWNED = createDefault("ENTITY_PARROT_IMITATE_DROWNED");
    public static final Sound ENTITY_PARROT_IMITATE_ELDER_GUARDIAN = createDefault("ENTITY_PARROT_IMITATE_ELDER_GUARDIAN");
    public static final Sound ENTITY_PARROT_IMITATE_ENDERMAN = createDefault("ENTITY_PARROT_IMITATE_ENDERMAN");
    public static final Sound ENTITY_PARROT_IMITATE_ENDERMITE = createDefault("ENTITY_PARROT_IMITATE_ENDERMITE");
    public static final Sound ENTITY_PARROT_IMITATE_ENDER_DRAGON = createDefault("ENTITY_PARROT_IMITATE_ENDER_DRAGON");
    public static final Sound ENTITY_PARROT_IMITATE_EVOKER = createDefault("ENTITY_PARROT_IMITATE_EVOKER");
    public static final Sound ENTITY_PARROT_IMITATE_GHAST = createDefault("ENTITY_PARROT_IMITATE_GHAST");
    public static final Sound ENTITY_PARROT_IMITATE_GUARDIAN = createDefault("ENTITY_PARROT_IMITATE_GUARDIAN");
    public static final Sound ENTITY_PARROT_IMITATE_HUSK = createDefault("ENTITY_PARROT_IMITATE_HUSK");
    public static final Sound ENTITY_PARROT_IMITATE_ILLUSIONER = createDefault("ENTITY_PARROT_IMITATE_ILLUSIONER");
    public static final Sound ENTITY_PARROT_IMITATE_MAGMA_CUBE = createDefault("ENTITY_PARROT_IMITATE_MAGMA_CUBE");
    public static final Sound ENTITY_PARROT_IMITATE_PANDA = createDefault("ENTITY_PARROT_IMITATE_PANDA");
    public static final Sound ENTITY_PARROT_IMITATE_PHANTOM = createDefault("ENTITY_PARROT_IMITATE_PHANTOM");
    public static final Sound ENTITY_PARROT_IMITATE_PILLAGER = createDefault("ENTITY_PARROT_IMITATE_PILLAGER");
    public static final Sound ENTITY_PARROT_IMITATE_POLAR_BEAR = createDefault("ENTITY_PARROT_IMITATE_POLAR_BEAR");
    public static final Sound ENTITY_PARROT_IMITATE_RAVAGER = createDefault("ENTITY_PARROT_IMITATE_RAVAGER");
    public static final Sound ENTITY_PARROT_IMITATE_SHULKER = createDefault("ENTITY_PARROT_IMITATE_SHULKER");
    public static final Sound ENTITY_PARROT_IMITATE_SILVERFISH = createDefault("ENTITY_PARROT_IMITATE_SILVERFISH");
    public static final Sound ENTITY_PARROT_IMITATE_SKELETON = createDefault("ENTITY_PARROT_IMITATE_SKELETON");
    public static final Sound ENTITY_PARROT_IMITATE_SLIME = createDefault("ENTITY_PARROT_IMITATE_SLIME");
    public static final Sound ENTITY_PARROT_IMITATE_SPIDER = createDefault("ENTITY_PARROT_IMITATE_SPIDER");
    public static final Sound ENTITY_PARROT_IMITATE_STRAY = createDefault("ENTITY_PARROT_IMITATE_STRAY");
    public static final Sound ENTITY_PARROT_IMITATE_VEX = createDefault("ENTITY_PARROT_IMITATE_VEX");
    public static final Sound ENTITY_PARROT_IMITATE_VINDICATOR = createDefault("ENTITY_PARROT_IMITATE_VINDICATOR");
    public static final Sound ENTITY_PARROT_IMITATE_WITCH = createDefault("ENTITY_PARROT_IMITATE_WITCH");
    public static final Sound ENTITY_PARROT_IMITATE_WITHER = createDefault("ENTITY_PARROT_IMITATE_WITHER");
    public static final Sound ENTITY_PARROT_IMITATE_WITHER_SKELETON = createDefault("ENTITY_PARROT_IMITATE_WITHER_SKELETON");
    public static final Sound ENTITY_PARROT_IMITATE_WOLF = createDefault("ENTITY_PARROT_IMITATE_WOLF");
    public static final Sound ENTITY_PARROT_IMITATE_ZOMBIE = createDefault("ENTITY_PARROT_IMITATE_ZOMBIE");
    public static final Sound ENTITY_PARROT_IMITATE_ZOMBIE_PIGMAN = createDefault("ENTITY_PARROT_IMITATE_ZOMBIE_PIGMAN");
    public static final Sound ENTITY_PARROT_IMITATE_ZOMBIE_VILLAGER = createDefault("ENTITY_PARROT_IMITATE_ZOMBIE_VILLAGER");
    public static final Sound ENTITY_PARROT_STEP = createDefault("ENTITY_PARROT_STEP");

    public static final Sound ENTITY_PHANTOM_AMBIENT = createDefault("ENTITY_PHANTOM_AMBIENT");
    public static final Sound ENTITY_PHANTOM_BITE = createDefault("ENTITY_PHANTOM_BITE");
    public static final Sound ENTITY_PHANTOM_DEATH = createDefault("ENTITY_PHANTOM_DEATH");
    public static final Sound ENTITY_PHANTOM_FLAP = createDefault("ENTITY_PHANTOM_FLAP");
    public static final Sound ENTITY_PHANTOM_HURT = createDefault("ENTITY_PHANTOM_HURT");
    public static final Sound ENTITY_PHANTOM_SWOOP = createDefault("ENTITY_PHANTOM_SWOOP");

    public static final Sound ENTITY_PIG_AMBIENT = createDefault("ENTITY_PIG_AMBIENT");
    public static final Sound ENTITY_PIG_DEATH = createDefault("ENTITY_PIG_DEATH");
    public static final Sound ENTITY_PIG_HURT = createDefault("ENTITY_PIG_HURT");
    public static final Sound ENTITY_PIG_SADDLE = createDefault("ENTITY_PIG_SADDLE");
    public static final Sound ENTITY_PIG_STEP = createDefault("ENTITY_PIG_STEP");

    public static final Sound ENTITY_PILLAGER_AMBIENT = createDefault("ENTITY_PILLAGER_AMBIENT");
    public static final Sound ENTITY_PILLAGER_CELEBRATE = createDefault("ENTITY_PILLAGER_CELEBRATE");
    public static final Sound ENTITY_PILLAGER_DEATH = createDefault("ENTITY_PILLAGER_DEATH");
    public static final Sound ENTITY_PILLAGER_HURT = createDefault("ENTITY_PILLAGER_HURT");

    public static final Sound ENTITY_PLAYER_ATTACK_CRIT = createDefault("ENTITY_PLAYER_ATTACK_CRIT");
    public static final Sound ENTITY_PLAYER_ATTACK_KNOCKBACK = createDefault("ENTITY_PLAYER_ATTACK_KNOCKBACK");
    public static final Sound ENTITY_PLAYER_ATTACK_NODAMAGE = createDefault("ENTITY_PLAYER_ATTACK_NODAMAGE");
    public static final Sound ENTITY_PLAYER_ATTACK_STRONG = createDefault("ENTITY_PLAYER_ATTACK_STRONG");
    public static final Sound ENTITY_PLAYER_ATTACK_SWEEP = createDefault("ENTITY_PLAYER_ATTACK_SWEEP");
    public static final Sound ENTITY_PLAYER_ATTACK_WEAK = createDefault("ENTITY_PLAYER_ATTACK_WEAK");
    public static final Sound ENTITY_PLAYER_BIG_FALL = createDefault("ENTITY_PLAYER_BIG_FALL");
    public static final Sound ENTITY_PLAYER_BREATH = createDefault("ENTITY_PLAYER_BREATH");
    public static final Sound ENTITY_PLAYER_BURP = createDefault("ENTITY_PLAYER_BURP");
    public static final Sound ENTITY_PLAYER_DEATH = createDefault("ENTITY_PLAYER_DEATH");
    public static final Sound ENTITY_PLAYER_HURT = createDefault("ENTITY_PLAYER_HURT");
    public static final Sound ENTITY_PLAYER_HURT_DROWN = createDefault("ENTITY_PLAYER_HURT_DROWN");
    public static final Sound ENTITY_PLAYER_HURT_ON_FIRE = createDefault("ENTITY_PLAYER_HURT_ON_FIRE");
    public static final Sound ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH = createDefault("ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH");
    public static final Sound ENTITY_PLAYER_LEVELUP = createDefault("ENTITY_PLAYER_LEVELUP");
    public static final Sound ENTITY_PLAYER_SMALL_FALL = createDefault("ENTITY_PLAYER_SMALL_FALL");
    public static final Sound ENTITY_PLAYER_SPLASH = createDefault("ENTITY_PLAYER_SPLASH");
    public static final Sound ENTITY_PLAYER_SPLASH_HIGH_SPEED = createDefault("ENTITY_PLAYER_SPLASH_HIGH_SPEED");
    public static final Sound ENTITY_PLAYER_SWIM = createDefault("ENTITY_PLAYER_SWIM");

    public static final Sound ENTITY_POLAR_BEAR_AMBIENT = createDefault("ENTITY_POLAR_BEAR_AMBIENT");
    public static final Sound ENTITY_POLAR_BEAR_AMBIENT_BABY = createDefault("ENTITY_POLAR_BEAR_AMBIENT_BABY");
    public static final Sound ENTITY_POLAR_BEAR_DEATH = createDefault("ENTITY_POLAR_BEAR_DEATH");
    public static final Sound ENTITY_POLAR_BEAR_HURT = createDefault("ENTITY_POLAR_BEAR_HURT");
    public static final Sound ENTITY_POLAR_BEAR_STEP = createDefault("ENTITY_POLAR_BEAR_STEP");
    public static final Sound ENTITY_POLAR_BEAR_WARNING = createDefault("ENTITY_POLAR_BEAR_WARNING");

    public static final Sound ENTITY_PUFFER_FISH_AMBIENT = createDefault("ENTITY_PUFFER_FISH_AMBIENT");
    public static final Sound ENTITY_PUFFER_FISH_BLOW_OUT = createDefault("ENTITY_PUFFER_FISH_BLOW_OUT");
    public static final Sound ENTITY_PUFFER_FISH_BLOW_UP = createDefault("ENTITY_PUFFER_FISH_BLOW_UP");
    public static final Sound ENTITY_PUFFER_FISH_DEATH = createDefault("ENTITY_PUFFER_FISH_DEATH");
    public static final Sound ENTITY_PUFFER_FISH_FLOP = createDefault("ENTITY_PUFFER_FISH_FLOP");
    public static final Sound ENTITY_PUFFER_FISH_HURT = createDefault("ENTITY_PUFFER_FISH_HURT");
    public static final Sound ENTITY_PUFFER_FISH_STING = createDefault("ENTITY_PUFFER_FISH_STING");

    public static final Sound ENTITY_RABBIT_AMBIENT = createDefault("ENTITY_RABBIT_AMBIENT");
    public static final Sound ENTITY_RABBIT_ATTACK = createDefault("ENTITY_RABBIT_ATTACK");
    public static final Sound ENTITY_RABBIT_DEATH = createDefault("ENTITY_RABBIT_DEATH");
    public static final Sound ENTITY_RABBIT_HURT = createDefault("ENTITY_RABBIT_HURT");
    public static final Sound ENTITY_RABBIT_JUMP = createDefault("ENTITY_RABBIT_JUMP");

    public static final Sound ENTITY_RAVAGER_AMBIENT = createDefault("ENTITY_RAVAGER_AMBIENT");
    public static final Sound ENTITY_RAVAGER_ATTACK = createDefault("ENTITY_RAVAGER_ATTACK");
    public static final Sound ENTITY_RAVAGER_CELEBRATE = createDefault("ENTITY_RAVAGER_CELEBRATE");
    public static final Sound ENTITY_RAVAGER_DEATH = createDefault("ENTITY_RAVAGER_DEATH");
    public static final Sound ENTITY_RAVAGER_HURT = createDefault("ENTITY_RAVAGER_HURT");
    public static final Sound ENTITY_RAVAGER_ROAR = createDefault("ENTITY_RAVAGER_ROAR");
    public static final Sound ENTITY_RAVAGER_STEP = createDefault("ENTITY_RAVAGER_STEP");
    public static final Sound ENTITY_RAVAGER_STUNNED = createDefault("ENTITY_RAVAGER_STUNNED");

    public static final Sound ENTITY_SALMON_AMBIENT = createDefault("ENTITY_SALMON_AMBIENT");
    public static final Sound ENTITY_SALMON_DEATH = createDefault("ENTITY_SALMON_DEATH");
    public static final Sound ENTITY_SALMON_FLOP = createDefault("ENTITY_SALMON_FLOP");
    public static final Sound ENTITY_SALMON_HURT = createDefault("ENTITY_SALMON_HURT");
    public static final Sound ENTITY_SHEEP_AMBIENT = createDefault("ENTITY_SHEEP_AMBIENT");
    public static final Sound ENTITY_SHEEP_DEATH = createDefault("ENTITY_SHEEP_DEATH");
    public static final Sound ENTITY_SHEEP_HURT = createDefault("ENTITY_SHEEP_HURT");
    public static final Sound ENTITY_SHEEP_SHEAR = createDefault("ENTITY_SHEEP_SHEAR");
    public static final Sound ENTITY_SHEEP_STEP = createDefault("ENTITY_SHEEP_STEP");

    public static final Sound ENTITY_SHULKER_AMBIENT = createDefault("ENTITY_SHULKER_AMBIENT");
    public static final Sound ENTITY_SHULKER_BULLET_HIT = createDefault("ENTITY_SHULKER_BULLET_HIT");
    public static final Sound ENTITY_SHULKER_BULLET_HURT = createDefault("ENTITY_SHULKER_BULLET_HURT");
    public static final Sound ENTITY_SHULKER_CLOSE = createDefault("ENTITY_SHULKER_CLOSE");
    public static final Sound ENTITY_SHULKER_DEATH = createDefault("ENTITY_SHULKER_DEATH");
    public static final Sound ENTITY_SHULKER_HURT = createDefault("ENTITY_SHULKER_HURT");
    public static final Sound ENTITY_SHULKER_HURT_CLOSED = createDefault("ENTITY_SHULKER_HURT_CLOSED");
    public static final Sound ENTITY_SHULKER_OPEN = createDefault("ENTITY_SHULKER_OPEN");
    public static final Sound ENTITY_SHULKER_SHOOT = createDefault("ENTITY_SHULKER_SHOOT");
    public static final Sound ENTITY_SHULKER_TELEPORT = createDefault("ENTITY_SHULKER_TELEPORT");

    public static final Sound ENTITY_SILVERFISH_AMBIENT = createDefault("ENTITY_SILVERFISH_AMBIENT");
    public static final Sound ENTITY_SILVERFISH_DEATH = createDefault("ENTITY_SILVERFISH_DEATH");
    public static final Sound ENTITY_SILVERFISH_HURT = createDefault("ENTITY_SILVERFISH_HURT");
    public static final Sound ENTITY_SILVERFISH_STEP = createDefault("ENTITY_SILVERFISH_STEP");

    public static final Sound ENTITY_SKELETON_AMBIENT = createDefault("ENTITY_SKELETON_AMBIENT");
    public static final Sound ENTITY_SKELETON_DEATH = createDefault("ENTITY_SKELETON_DEATH");
    public static final Sound ENTITY_SKELETON_HORSE_AMBIENT = createDefault("ENTITY_SKELETON_HORSE_AMBIENT");
    public static final Sound ENTITY_SKELETON_HORSE_AMBIENT_WATER = createDefault("ENTITY_SKELETON_HORSE_AMBIENT_WATER");
    public static final Sound ENTITY_SKELETON_HORSE_DEATH = createDefault("ENTITY_SKELETON_HORSE_DEATH");
    public static final Sound ENTITY_SKELETON_HORSE_GALLOP_WATER = createDefault("ENTITY_SKELETON_HORSE_GALLOP_WATER");
    public static final Sound ENTITY_SKELETON_HORSE_HURT = createDefault("ENTITY_SKELETON_HORSE_HURT");
    public static final Sound ENTITY_SKELETON_HORSE_JUMP_WATER = createDefault("ENTITY_SKELETON_HORSE_JUMP_WATER");
    public static final Sound ENTITY_SKELETON_HORSE_STEP_WATER = createDefault("ENTITY_SKELETON_HORSE_STEP_WATER");
    public static final Sound ENTITY_SKELETON_HORSE_SWIM = createDefault("ENTITY_SKELETON_HORSE_SWIM");
    public static final Sound ENTITY_SKELETON_HURT = createDefault("ENTITY_SKELETON_HURT");
    public static final Sound ENTITY_SKELETON_SHOOT = createDefault("ENTITY_SKELETON_SHOOT");
    public static final Sound ENTITY_SKELETON_STEP = createDefault("ENTITY_SKELETON_STEP");

    public static final Sound ENTITY_SLIME_ATTACK = createDefault("ENTITY_SLIME_ATTACK");
    public static final Sound ENTITY_SLIME_DEATH = createDefault("ENTITY_SLIME_DEATH");
    public static final Sound ENTITY_SLIME_DEATH_SMALL = createDefault("ENTITY_SLIME_DEATH_SMALL");
    public static final Sound ENTITY_SLIME_HURT = createDefault("ENTITY_SLIME_HURT");
    public static final Sound ENTITY_SLIME_HURT_SMALL = createDefault("ENTITY_SLIME_HURT_SMALL");
    public static final Sound ENTITY_SLIME_JUMP = createDefault("ENTITY_SLIME_JUMP");
    public static final Sound ENTITY_SLIME_JUMP_SMALL = createDefault("ENTITY_SLIME_JUMP_SMALL");
    public static final Sound ENTITY_SLIME_SQUISH = createDefault("ENTITY_SLIME_SQUISH");
    public static final Sound ENTITY_SLIME_SQUISH_SMALL = createDefault("ENTITY_SLIME_SQUISH_SMALL");

    public static final Sound ENTITY_SNOWBALL_THROW = createDefault("ENTITY_SNOWBALL_THROW");

    public static final Sound ENTITY_SNOW_GOLEM_AMBIENT = createDefault("ENTITY_SNOW_GOLEM_AMBIENT");
    public static final Sound ENTITY_SNOW_GOLEM_DEATH = createDefault("ENTITY_SNOW_GOLEM_DEATH");
    public static final Sound ENTITY_SNOW_GOLEM_HURT = createDefault("ENTITY_SNOW_GOLEM_HURT");
    public static final Sound ENTITY_SNOW_GOLEM_SHOOT = createDefault("ENTITY_SNOW_GOLEM_SHOOT");

    public static final Sound ENTITY_SPIDER_AMBIENT = createDefault("ENTITY_SPIDER_AMBIENT");
    public static final Sound ENTITY_SPIDER_DEATH = createDefault("ENTITY_SPIDER_DEATH");
    public static final Sound ENTITY_SPIDER_HURT = createDefault("ENTITY_SPIDER_HURT");
    public static final Sound ENTITY_SPIDER_STEP = createDefault("ENTITY_SPIDER_STEP");

    public static final Sound ENTITY_SPLASH_POTION_BREAK = createDefault("ENTITY_SPLASH_POTION_BREAK");
    public static final Sound ENTITY_SPLASH_POTION_THROW = createDefault("ENTITY_SPLASH_POTION_THROW");

    public static final Sound ENTITY_SQUID_AMBIENT = createDefault("ENTITY_SQUID_AMBIENT");
    public static final Sound ENTITY_SQUID_DEATH = createDefault("ENTITY_SQUID_DEATH");
    public static final Sound ENTITY_SQUID_HURT = createDefault("ENTITY_SQUID_HURT");
    public static final Sound ENTITY_SQUID_SQUIRT = createDefault("ENTITY_SQUID_SQUIRT");

    public static final Sound ENTITY_STRAY_AMBIENT = createDefault("ENTITY_STRAY_AMBIENT");
    public static final Sound ENTITY_STRAY_DEATH = createDefault("ENTITY_STRAY_DEATH");
    public static final Sound ENTITY_STRAY_HURT = createDefault("ENTITY_STRAY_HURT");
    public static final Sound ENTITY_STRAY_STEP = createDefault("ENTITY_STRAY_STEP");

    public static final Sound ENTITY_TNT_PRIMED = createDefault("ENTITY_TNT_PRIMED");

    public static final Sound ENTITY_TROPICAL_FISH_AMBIENT = createDefault("ENTITY_TROPICAL_FISH_AMBIENT");
    public static final Sound ENTITY_TROPICAL_FISH_DEATH = createDefault("ENTITY_TROPICAL_FISH_DEATH");
    public static final Sound ENTITY_TROPICAL_FISH_FLOP = createDefault("ENTITY_TROPICAL_FISH_FLOP");
    public static final Sound ENTITY_TROPICAL_FISH_HURT = createDefault("ENTITY_TROPICAL_FISH_HURT");

    public static final Sound ENTITY_TURTLE_AMBIENT_LAND = createDefault("ENTITY_TURTLE_AMBIENT_LAND");
    public static final Sound ENTITY_TURTLE_DEATH = createDefault("ENTITY_TURTLE_DEATH");
    public static final Sound ENTITY_TURTLE_DEATH_BABY = createDefault("ENTITY_TURTLE_DEATH_BABY");
    public static final Sound ENTITY_TURTLE_EGG_BREAK = createDefault("ENTITY_TURTLE_EGG_BREAK");
    public static final Sound ENTITY_TURTLE_EGG_CRACK = createDefault("ENTITY_TURTLE_EGG_CRACK");
    public static final Sound ENTITY_TURTLE_EGG_HATCH = createDefault("ENTITY_TURTLE_EGG_HATCH");
    public static final Sound ENTITY_TURTLE_HURT = createDefault("ENTITY_TURTLE_HURT");
    public static final Sound ENTITY_TURTLE_HURT_BABY = createDefault("ENTITY_TURTLE_HURT_BABY");
    public static final Sound ENTITY_TURTLE_LAY_EGG = createDefault("ENTITY_TURTLE_LAY_EGG");
    public static final Sound ENTITY_TURTLE_SHAMBLE = createDefault("ENTITY_TURTLE_SHAMBLE");
    public static final Sound ENTITY_TURTLE_SHAMBLE_BABY = createDefault("ENTITY_TURTLE_SHAMBLE_BABY");
    public static final Sound ENTITY_TURTLE_SWIM = createDefault("ENTITY_TURTLE_SWIM");

    public static final Sound ENTITY_VEX_AMBIENT = createDefault("ENTITY_VEX_AMBIENT");
    public static final Sound ENTITY_VEX_CHARGE = createDefault("ENTITY_VEX_CHARGE");
    public static final Sound ENTITY_VEX_DEATH = createDefault("ENTITY_VEX_DEATH");
    public static final Sound ENTITY_VEX_HURT = createDefault("ENTITY_VEX_HURT");

    public static final Sound ENTITY_VILLAGER_AMBIENT = createDefault("ENTITY_VILLAGER_AMBIENT");
    public static final Sound ENTITY_VILLAGER_CELEBRATE = createDefault("ENTITY_VILLAGER_CELEBRATE");
    public static final Sound ENTITY_VILLAGER_DEATH = createDefault("ENTITY_VILLAGER_DEATH");
    public static final Sound ENTITY_VILLAGER_HURT = createDefault("ENTITY_VILLAGER_HURT");
    public static final Sound ENTITY_VILLAGER_NO = createDefault("ENTITY_VILLAGER_NO");
    public static final Sound ENTITY_VILLAGER_TRADE = createDefault("ENTITY_VILLAGER_TRADE");
    public static final Sound ENTITY_VILLAGER_WORK_ARMORER = createDefault("ENTITY_VILLAGER_WORK_ARMORER");
    public static final Sound ENTITY_VILLAGER_WORK_BUTCHER = createDefault("ENTITY_VILLAGER_WORK_BUTCHER");
    public static final Sound ENTITY_VILLAGER_WORK_CARTOGRAPHER = createDefault("ENTITY_VILLAGER_WORK_CARTOGRAPHER");
    public static final Sound ENTITY_VILLAGER_WORK_CLERIC = createDefault("ENTITY_VILLAGER_WORK_CLERIC");
    public static final Sound ENTITY_VILLAGER_WORK_FARMER = createDefault("ENTITY_VILLAGER_WORK_FARMER");
    public static final Sound ENTITY_VILLAGER_WORK_FISHERMAN = createDefault("ENTITY_VILLAGER_WORK_FISHERMAN");
    public static final Sound ENTITY_VILLAGER_WORK_FLETCHER = createDefault("ENTITY_VILLAGER_WORK_FLETCHER");
    public static final Sound ENTITY_VILLAGER_WORK_LEATHERWORKER = createDefault("ENTITY_VILLAGER_WORK_LEATHERWORKER");
    public static final Sound ENTITY_VILLAGER_WORK_LIBRARIAN = createDefault("ENTITY_VILLAGER_WORK_LIBRARIAN");
    public static final Sound ENTITY_VILLAGER_WORK_MASON = createDefault("ENTITY_VILLAGER_WORK_MASON");
    public static final Sound ENTITY_VILLAGER_WORK_SHEPHERD = createDefault("ENTITY_VILLAGER_WORK_SHEPHERD");
    public static final Sound ENTITY_VILLAGER_WORK_TOOLSMITH = createDefault("ENTITY_VILLAGER_WORK_TOOLSMITH");
    public static final Sound ENTITY_VILLAGER_WORK_WEAPONSMITH = createDefault("ENTITY_VILLAGER_WORK_WEAPONSMITH");
    public static final Sound ENTITY_VILLAGER_YES = createDefault("ENTITY_VILLAGER_YES");

    public static final Sound ENTITY_VINDICATOR_AMBIENT = createDefault("ENTITY_VINDICATOR_AMBIENT");
    public static final Sound ENTITY_VINDICATOR_CELEBRATE = createDefault("ENTITY_VINDICATOR_CELEBRATE");
    public static final Sound ENTITY_VINDICATOR_DEATH = createDefault("ENTITY_VINDICATOR_DEATH");
    public static final Sound ENTITY_VINDICATOR_HURT = createDefault("ENTITY_VINDICATOR_HURT");

    public static final Sound ENTITY_WANDERING_TRADER_AMBIENT = createDefault("ENTITY_WANDERING_TRADER_AMBIENT");
    public static final Sound ENTITY_WANDERING_TRADER_DEATH = createDefault("ENTITY_WANDERING_TRADER_DEATH");
    public static final Sound ENTITY_WANDERING_TRADER_DISAPPEARED = createDefault("ENTITY_WANDERING_TRADER_DISAPPEARED");
    public static final Sound ENTITY_WANDERING_TRADER_DRINK_MILK = createDefault("ENTITY_WANDERING_TRADER_DRINK_MILK");
    public static final Sound ENTITY_WANDERING_TRADER_DRINK_POTION = createDefault("ENTITY_WANDERING_TRADER_DRINK_POTION");
    public static final Sound ENTITY_WANDERING_TRADER_HURT = createDefault("ENTITY_WANDERING_TRADER_HURT");
    public static final Sound ENTITY_WANDERING_TRADER_NO = createDefault("ENTITY_WANDERING_TRADER_NO");
    public static final Sound ENTITY_WANDERING_TRADER_REAPPEARED = createDefault("ENTITY_WANDERING_TRADER_REAPPEARED");
    public static final Sound ENTITY_WANDERING_TRADER_TRADE = createDefault("ENTITY_WANDERING_TRADER_TRADE");
    public static final Sound ENTITY_WANDERING_TRADER_YES = createDefault("ENTITY_WANDERING_TRADER_YES");

    public static final Sound ENTITY_WITCH_AMBIENT = createDefault("ENTITY_WITCH_AMBIENT");
    public static final Sound ENTITY_WITCH_CELEBRATE = createDefault("ENTITY_WITCH_CELEBRATE");
    public static final Sound ENTITY_WITCH_DEATH = createDefault("ENTITY_WITCH_DEATH");
    public static final Sound ENTITY_WITCH_DRINK = createDefault("ENTITY_WITCH_DRINK");
    public static final Sound ENTITY_WITCH_HURT = createDefault("ENTITY_WITCH_HURT");
    public static final Sound ENTITY_WITCH_THROW = createDefault("ENTITY_WITCH_THROW");

    public static final Sound ENTITY_WITHER_AMBIENT = createDefault("ENTITY_WITHER_AMBIENT");
    public static final Sound ENTITY_WITHER_BREAK_BLOCK = createDefault("ENTITY_WITHER_BREAK_BLOCK");
    public static final Sound ENTITY_WITHER_DEATH = createDefault("ENTITY_WITHER_DEATH");
    public static final Sound ENTITY_WITHER_HURT = createDefault("ENTITY_WITHER_HURT");
    public static final Sound ENTITY_WITHER_SHOOT = createDefault("ENTITY_WITHER_SHOOT");
    public static final Sound ENTITY_WITHER_SKELETON_AMBIENT = createDefault("ENTITY_WITHER_SKELETON_AMBIENT");
    public static final Sound ENTITY_WITHER_SKELETON_DEATH = createDefault("ENTITY_WITHER_SKELETON_DEATH");
    public static final Sound ENTITY_WITHER_SKELETON_HURT = createDefault("ENTITY_WITHER_SKELETON_HURT");
    public static final Sound ENTITY_WITHER_SKELETON_STEP = createDefault("ENTITY_WITHER_SKELETON_STEP");
    public static final Sound ENTITY_WITHER_SPAWN = createDefault("ENTITY_WITHER_SPAWN");

    public static final Sound ENTITY_WOLF_AMBIENT = createDefault("ENTITY_WOLF_AMBIENT");
    public static final Sound ENTITY_WOLF_DEATH = createDefault("ENTITY_WOLF_DEATH");
    public static final Sound ENTITY_WOLF_GROWL = createDefault("ENTITY_WOLF_GROWL");
    public static final Sound ENTITY_WOLF_HOWL = createDefault("ENTITY_WOLF_HOWL");
    public static final Sound ENTITY_WOLF_HURT = createDefault("ENTITY_WOLF_HURT");
    public static final Sound ENTITY_WOLF_PANT = createDefault("ENTITY_WOLF_PANT");
    public static final Sound ENTITY_WOLF_SHAKE = createDefault("ENTITY_WOLF_SHAKE");
    public static final Sound ENTITY_WOLF_STEP = createDefault("ENTITY_WOLF_STEP");
    public static final Sound ENTITY_WOLF_WHINE = createDefault("ENTITY_WOLF_WHINE");

    public static final Sound ENTITY_ZOMBIE_AMBIENT = createDefault("ENTITY_ZOMBIE_AMBIENT");
    public static final Sound ENTITY_ZOMBIE_ATTACK_IRON_DOOR = createDefault("ENTITY_ZOMBIE_ATTACK_IRON_DOOR");
    public static final Sound ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR = createDefault("ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR");
    public static final Sound ENTITY_ZOMBIE_BREAK_WOODEN_DOOR = createDefault("ENTITY_ZOMBIE_BREAK_WOODEN_DOOR");
    public static final Sound ENTITY_ZOMBIE_CONVERTED_TO_DROWNED = createDefault("ENTITY_ZOMBIE_CONVERTED_TO_DROWNED");
    public static final Sound ENTITY_ZOMBIE_DEATH = createDefault("ENTITY_ZOMBIE_DEATH");
    public static final Sound ENTITY_ZOMBIE_DESTROY_EGG = createDefault("ENTITY_ZOMBIE_DESTROY_EGG");
    public static final Sound ENTITY_ZOMBIE_HORSE_AMBIENT = createDefault("ENTITY_ZOMBIE_HORSE_AMBIENT");
    public static final Sound ENTITY_ZOMBIE_HORSE_DEATH = createDefault("ENTITY_ZOMBIE_HORSE_DEATH");
    public static final Sound ENTITY_ZOMBIE_HORSE_HURT = createDefault("ENTITY_ZOMBIE_HORSE_HURT");
    public static final Sound ENTITY_ZOMBIE_HURT = createDefault("ENTITY_ZOMBIE_HURT");
    public static final Sound ENTITY_ZOMBIE_INFECT = createDefault("ENTITY_ZOMBIE_INFECT");
    public static final Sound ENTITY_ZOMBIE_PIGMAN_AMBIENT = createDefault("ENTITY_ZOMBIE_PIGMAN_AMBIENT");
    public static final Sound ENTITY_ZOMBIE_PIGMAN_ANGRY = createDefault("ENTITY_ZOMBIE_PIGMAN_ANGRY");
    public static final Sound ENTITY_ZOMBIE_PIGMAN_DEATH = createDefault("ENTITY_ZOMBIE_PIGMAN_DEATH");
    public static final Sound ENTITY_ZOMBIE_PIGMAN_HURT = createDefault("ENTITY_ZOMBIE_PIGMAN_HURT");
    public static final Sound ENTITY_ZOMBIE_STEP = createDefault("ENTITY_ZOMBIE_STEP");
    public static final Sound ENTITY_ZOMBIE_VILLAGER_AMBIENT = createDefault("ENTITY_ZOMBIE_VILLAGER_AMBIENT");
    public static final Sound ENTITY_ZOMBIE_VILLAGER_CONVERTED = createDefault("ENTITY_ZOMBIE_VILLAGER_CONVERTED");
    public static final Sound ENTITY_ZOMBIE_VILLAGER_CURE = createDefault("ENTITY_ZOMBIE_VILLAGER_CURE");
    public static final Sound ENTITY_ZOMBIE_VILLAGER_DEATH = createDefault("ENTITY_ZOMBIE_VILLAGER_DEATH");
    public static final Sound ENTITY_ZOMBIE_VILLAGER_HURT = createDefault("ENTITY_ZOMBIE_VILLAGER_HURT");
    public static final Sound ENTITY_ZOMBIE_VILLAGER_STEP = createDefault("ENTITY_ZOMBIE_VILLAGER_STEP");

    public static final Sound EVENT_RAID_HORN = createDefault("EVENT_RAID_HORN");

    public static final Sound ITEM_ARMOR_EQUIP_CHAIN = createDefault("ITEM_ARMOR_EQUIP_CHAIN");
    public static final Sound ITEM_ARMOR_EQUIP_DIAMOND = createDefault("ITEM_ARMOR_EQUIP_DIAMOND");
    public static final Sound ITEM_ARMOR_EQUIP_ELYTRA = createDefault("ITEM_ARMOR_EQUIP_ELYTRA");
    public static final Sound ITEM_ARMOR_EQUIP_GENERIC = createDefault("ITEM_ARMOR_EQUIP_GENERIC");
    public static final Sound ITEM_ARMOR_EQUIP_GOLD = createDefault("ITEM_ARMOR_EQUIP_GOLD");
    public static final Sound ITEM_ARMOR_EQUIP_IRON = createDefault("ITEM_ARMOR_EQUIP_IRON");
    public static final Sound ITEM_ARMOR_EQUIP_LEATHER = createDefault("ITEM_ARMOR_EQUIP_LEATHER");
    public static final Sound ITEM_ARMOR_EQUIP_TURTLE = createDefault("ITEM_ARMOR_EQUIP_TURTLE");

    public static final Sound ITEM_AXE_STRIP = createDefault("ITEM_AXE_STRIP");

    public static final Sound ITEM_BOOK_PAGE_TURN = createDefault("ITEM_BOOK_PAGE_TURN");
    public static final Sound ITEM_BOOK_PUT = createDefault("ITEM_BOOK_PUT");

    public static final Sound ITEM_BOTTLE_EMPTY = createDefault("ITEM_BOTTLE_EMPTY");
    public static final Sound ITEM_BOTTLE_FILL = createDefault("ITEM_BOTTLE_FILL");
    public static final Sound ITEM_BOTTLE_FILL_DRAGONBREATH = createDefault("ITEM_BOTTLE_FILL_DRAGONBREATH");

    public static final Sound ITEM_BUCKET_EMPTY = createDefault("ITEM_BUCKET_EMPTY");
    public static final Sound ITEM_BUCKET_EMPTY_FISH = createDefault("ITEM_BUCKET_EMPTY_FISH");
    public static final Sound ITEM_BUCKET_EMPTY_LAVA = createDefault("ITEM_BUCKET_EMPTY_LAVA");
    public static final Sound ITEM_BUCKET_FILL = createDefault("ITEM_BUCKET_FILL");
    public static final Sound ITEM_BUCKET_FILL_FISH = createDefault("ITEM_BUCKET_FILL_FISH");
    public static final Sound ITEM_BUCKET_FILL_LAVA = createDefault("ITEM_BUCKET_FILL_LAVA");

    public static final Sound ITEM_CHORUS_FRUIT_TELEPORT = createDefault("ITEM_CHORUS_FRUIT_TELEPORT");

    public static final Sound ITEM_CROP_PLANT = createDefault("ITEM_CROP_PLANT");

    public static final Sound ITEM_CROSSBOW_HIT = createDefault("ITEM_CROSSBOW_HIT");
    public static final Sound ITEM_CROSSBOW_LOADING_END = createDefault("ITEM_CROSSBOW_LOADING_END");
    public static final Sound ITEM_CROSSBOW_LOADING_MIDDLE = createDefault("ITEM_CROSSBOW_LOADING_MIDDLE");
    public static final Sound ITEM_CROSSBOW_LOADING_START = createDefault("ITEM_CROSSBOW_LOADING_START");
    public static final Sound ITEM_CROSSBOW_QUICK_CHARGE_1 = createDefault("ITEM_CROSSBOW_QUICK_CHARGE_1");
    public static final Sound ITEM_CROSSBOW_QUICK_CHARGE_2 = createDefault("ITEM_CROSSBOW_QUICK_CHARGE_2");
    public static final Sound ITEM_CROSSBOW_QUICK_CHARGE_3 = createDefault("ITEM_CROSSBOW_QUICK_CHARGE_3");
    public static final Sound ITEM_CROSSBOW_SHOOT = createDefault("ITEM_CROSSBOW_SHOOT");

    public static final Sound ITEM_ELYTRA_FLYING = createDefault("ITEM_ELYTRA_FLYING");

    public static final Sound ITEM_FIRECHARGE_USE = createDefault("ITEM_FIRECHARGE_USE");

    public static final Sound ITEM_FLINTANDSTEEL_USE = createDefault("ITEM_FLINTANDSTEEL_USE");

    public static final Sound ITEM_HOE_TILL = createDefault("ITEM_HOE_TILL");

    public static final Sound ITEM_NETHER_WART_PLANT = createDefault("ITEM_NETHER_WART_PLANT");

    public static final Sound ITEM_SHIELD_BLOCK = createDefault("ITEM_SHIELD_BLOCK");
    public static final Sound ITEM_SHIELD_BREAK = createDefault("ITEM_SHIELD_BREAK");

    public static final Sound ITEM_SHOVEL_FLATTEN = createDefault("ITEM_SHOVEL_FLATTEN");

    public static final Sound ITEM_SWEET_BERRIES_PICK_FROM_BUSH = createDefault("ITEM_SWEET_BERRIES_PICK_FROM_BUSH");

    public static final Sound ITEM_TOTEM_USE = createDefault("ITEM_TOTEM_USE");

    public static final Sound ITEM_TRIDENT_HIT = createDefault("ITEM_TRIDENT_HIT");
    public static final Sound ITEM_TRIDENT_HIT_GROUND = createDefault("ITEM_TRIDENT_HIT_GROUND");
    public static final Sound ITEM_TRIDENT_RETURN = createDefault("ITEM_TRIDENT_RETURN");
    public static final Sound ITEM_TRIDENT_RIPTIDE_1 = createDefault("ITEM_TRIDENT_RIPTIDE_1");
    public static final Sound ITEM_TRIDENT_RIPTIDE_2 = createDefault("ITEM_TRIDENT_RIPTIDE_2");
    public static final Sound ITEM_TRIDENT_RIPTIDE_3 = createDefault("ITEM_TRIDENT_RIPTIDE_3");
    public static final Sound ITEM_TRIDENT_THROW = createDefault("ITEM_TRIDENT_THROW");
    public static final Sound ITEM_TRIDENT_THUNDER = createDefault("ITEM_TRIDENT_THUNDER");

    public static final Sound MUSIC_CREATIVE = createDefault("MUSIC_CREATIVE");

    public static final Sound MUSIC_CREDITS = createDefault("MUSIC_CREDITS");

    public static final Sound MUSIC_DISC_11 = createDefault("MUSIC_DISC_11");
    public static final Sound MUSIC_DISC_13 = createDefault("MUSIC_DISC_13");
    public static final Sound MUSIC_DISC_BLOCKS = createDefault("MUSIC_DISC_BLOCKS");
    public static final Sound MUSIC_DISC_CAT = createDefault("MUSIC_DISC_CAT");
    public static final Sound MUSIC_DISC_CHIRP = createDefault("MUSIC_DISC_CHIRP");
    public static final Sound MUSIC_DISC_FAR = createDefault("MUSIC_DISC_FAR");
    public static final Sound MUSIC_DISC_MALL = createDefault("MUSIC_DISC_MALL");
    public static final Sound MUSIC_DISC_MELLOHI = createDefault("MUSIC_DISC_MELLOHI");
    public static final Sound MUSIC_DISC_STAL = createDefault("MUSIC_DISC_STAL");
    public static final Sound MUSIC_DISC_STRAD = createDefault("MUSIC_DISC_STRAD");
    public static final Sound MUSIC_DISC_WAIT = createDefault("MUSIC_DISC_WAIT");
    public static final Sound MUSIC_DISC_WARD = createDefault("MUSIC_DISC_WARD");

    public static final Sound MUSIC_DRAGON = createDefault("MUSIC_DRAGON");
    public static final Sound MUSIC_END = createDefault("MUSIC_END");
    public static final Sound MUSIC_GAME = createDefault("MUSIC_GAME");
    public static final Sound MUSIC_MENU = createDefault("MUSIC_MENU");
    public static final Sound MUSIC_NETHER = createDefault("MUSIC_NETHER");
    public static final Sound MUSIC_UNDER_WATER = createDefault("MUSIC_UNDER_WATER");

    public static final Sound UI_BUTTON_CLICK = createDefault("UI_BUTTON_CLICK");

    public static final Sound UI_CARTOGRAPHY_TABLE_TAKE_RESULT = createDefault("UI_CARTOGRAPHY_TABLE_TAKE_RESULT");

    public static final Sound UI_LOOM_SELECT_PATTERN = createDefault("UI_LOOM_SELECT_PATTERN");
    public static final Sound UI_LOOM_TAKE_RESULT = createDefault("UI_LOOM_TAKE_RESULT");

    public static final Sound UI_STONECUTTER_SELECT_RECIPE = createDefault("UI_STONECUTTER_SELECT_RECIPE");
    public static final Sound UI_STONECUTTER_TAKE_RESULT = createDefault("UI_STONECUTTER_TAKE_RESULT");

    public static final Sound UI_TOAST_CHALLENGE_COMPLETE = createDefault("UI_TOAST_CHALLENGE_COMPLETE");

    public static final Sound UI_TOAST_IN = createDefault("UI_TOAST_IN");
    public static final Sound UI_TOAST_OUT = createDefault("UI_TOAST_OUT");

    public static final Sound WEATHER_RAIN = createDefault("WEATHER_RAIN");
    public static final Sound WEATHER_RAIN_ABOVE = createDefault("WEATHER_RAIN_ABOVE");


    private final String name;
    private final ObjectOwner owner;

    public Sound(String name, ObjectOwner owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public boolean isCustom() {
        return !this.owner.equals(ObjectOwner.SYSTEM);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Sound && ((Sound)obj).getName().equals(this.name);
    }

    public static Collection<Sound> getAll() {
        return SOUNDS;
    }

    public static Sound byName(String name) {
        return Iterators.findOne(SOUNDS, sound -> sound.getName().equals(name));
    }

    public static void register(Sound sound) {
        SOUNDS.add(sound);
    }

    public static void unregister(Sound sound) {
        SOUNDS.remove(sound);
    }

    public static void unregister(String soundName) {
        Iterators.remove(SOUNDS, sound -> sound.getName().equalsIgnoreCase(soundName));
    }

    private static Sound createDefault(String name) {
        Sound sound = new Sound(name, ObjectOwner.SYSTEM);
        SOUNDS.add(sound);
        return sound;
    }
}
