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

package org.mcnative.common.player.sound;

import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.interfaces.ObjectOwner;

import java.util.ArrayList;
import java.util.Collection;

//@Todo finish all sounds
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
        return this.owner.equals(ObjectOwner.SYSTEM);
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