/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 06.09.19, 17:04
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

package org.mcnative.runtime.api.service.world.particle;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.ArrayList;
import java.util.Collection;

public class Particle {

    private static final Collection<Particle> PARTICLES = new ArrayList<>();

    public static final Particle EXPLOSION_NORMAL = createDefault("EXPLOSION_NORMAL");
    public static final Particle EXPLOSION_LARGE = createDefault("EXPLOSION_LARGE");
    public static final Particle EXPLOSION_HUGE = createDefault("EXPLOSION_HUGE");
    public static final Particle FIREWORKS_SPARK = createDefault("FIREWORKS_SPARK");
    public static final Particle WATER_BUBBLE = createDefault("WATER_BUBBLE");
    public static final Particle WATER_SPLASH = createDefault("WATER_SPLASH");
    public static final Particle WATER_WAKE = createDefault("WATER_WAKE");
    public static final Particle SUSPENDED = createDefault("SUSPENDED");
    public static final Particle SUSPENDED_DEPTH = createDefault("SUSPENDED_DEPTH");
    public static final Particle CRIT = createDefault("CRIT");
    public static final Particle CRIT_MAGIC = createDefault("CRIT_MAGIC");
    public static final Particle SMOKE_NORMAL = createDefault("SMOKE_NORMAL");
    public static final Particle SMOKE_LARGE = createDefault("SMOKE_LARGE");
    public static final Particle SPELL = createDefault("SPELL");
    public static final Particle SPELL_INSTANT = createDefault("SPELL_INSTANT");
    public static final Particle SPELL_MOB = createDefault("SPELL_MOB");
    public static final Particle SPELL_MOB_AMBIENT = createDefault("SPELL_MOB_AMBIENT");
    public static final Particle SPELL_WITCH = createDefault("SPELL_WITCH");
    public static final Particle DRIP_WATER = createDefault("DRIP_WATER");
    public static final Particle DRIP_LAVA = createDefault("DRIP_LAVA");
    public static final Particle VILLAGER_ANGRY = createDefault("VILLAGER_ANGRY");
    public static final Particle VILLAGER_HAPPY = createDefault("VILLAGER_HAPPY");
    public static final Particle TOWN_AURA = createDefault("TOWN_AURA");
    public static final Particle NOTE = createDefault("NOTE");
    public static final Particle PORTAL = createDefault("PORTAL");
    public static final Particle ENCHANTMENT_TABLE = createDefault("ENCHANTMENT_TABLE");
    public static final Particle FLAME = createDefault("FLAME");
    public static final Particle LAVA = createDefault("LAVA");
    public static final Particle CLOUD = createDefault("CLOUD");
    public static final Particle REDSTONE = createDefault("REDSTONE");
    public static final Particle SNOWBALL = createDefault("SNOWBALL");
    public static final Particle SNOW_SHOVEL = createDefault("SNOW_SHOVEL");
    public static final Particle SLIME = createDefault("SLIME");
    public static final Particle HEART = createDefault("HEART");
    public static final Particle BARRIER = createDefault("BARRIER");
    public static final Particle ITEM_CRACK = createDefault("ITEM_CRACK");
    public static final Particle BLOCK_CRACK = createDefault("BLOCK_CRACK");
    public static final Particle BLOCK_DUST = createDefault("BLOCK_DUST");
    public static final Particle WATER_DROP = createDefault("WATER_DROP");
    public static final Particle MOB_APPEARANCE = createDefault("MOB_APPEARANCE");
    public static final Particle DRAGON_BREATH = createDefault("DRAGON_BREATH");
    public static final Particle END_ROD = createDefault("END_ROD");
    public static final Particle DAMAGE_INDICATOR = createDefault("DAMAGE_INDICATOR");
    public static final Particle SWEEP_ATTACK = createDefault("SWEEP_ATTACK");
    public static final Particle FALLING_DUST = createDefault("FALLING_DUST");
    public static final Particle TOTEM = createDefault("TOTEM");
    public static final Particle SPIT = createDefault("SPIT");
    public static final Particle SQUID_INK = createDefault("SQUID_INK");
    public static final Particle BUBBLE_POP = createDefault("BUBBLE_POP");
    public static final Particle CURRENT_DOWN = createDefault("CURRENT_DOWN");
    public static final Particle BUBBLE_COLUMN_UP = createDefault("BUBBLE_COLUMN_UP");
    public static final Particle NAUTILUS = createDefault("NAUTILUS");
    public static final Particle DOLPHIN = createDefault("DOLPHIN");

    private final ObjectOwner owner;
    private final String name;
    private final Class<?> data;

    public Particle(ObjectOwner owner, String name, Class<?> data) {
        this.owner = owner;
        this.name = name;
        this.data = data;
    }
    public Particle(ObjectOwner owner, String name) {
        this(owner, name, null);
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Class<?> getData() {
        return data;
    }

    public boolean hasData() {
        return this.data != null;
    }

    public boolean isCustom() {
        return !this.owner.equals(ObjectOwner.SYSTEM);
    }


    public static Collection<Particle> getAll() {
        return PARTICLES;
    }

    public static Particle byName(String name) {
        return Iterators.findOne(PARTICLES, particle -> particle.getName().equals(name));
    }

    public static void register(Particle particle) {
        PARTICLES.add(particle);
    }

    public static void unregister(Particle particle) {
        PARTICLES.remove(particle);
    }

    public static void unregister(String particleName) {
        Iterators.remove(PARTICLES, particle -> particle.getName().equalsIgnoreCase(particleName));
    }

    private static Particle createDefault(String name, Class<?> data) {
        Particle particle = new Particle(ObjectOwner.SYSTEM, name, data);
        PARTICLES.add(particle);
        return particle;
    }

    private static Particle createDefault(String name) {
        return createDefault(name, null);
    }
}
