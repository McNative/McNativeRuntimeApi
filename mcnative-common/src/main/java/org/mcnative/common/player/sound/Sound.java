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