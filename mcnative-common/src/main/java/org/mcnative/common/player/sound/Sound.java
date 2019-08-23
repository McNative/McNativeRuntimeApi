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
        Iterators.remove(SOUNDS, sound -> sound.getName().equals(soundName));
    }

    private static Sound createDefault(String name) {
        Sound sound = new Sound(name, ObjectOwner.SYSTEM);
        SOUNDS.add(sound);
        return sound;
    }
}