/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 16.09.19, 20:18
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

package org.mcnative.runtime.api.service.potion;

import org.mcnative.runtime.api.text.format.TextColor;

public class PotionEffectType {

    public static final PotionEffectType SPEED = createDefault("SPEED", false, null);

    public static final PotionEffectType SLOW = createDefault("SLOW", false, null);

    public static final PotionEffectType FAST_DIGGING = createDefault("FAST_DIGGING", false, null);

    public static final PotionEffectType SLOW_DIGGING = createDefault("SLOW_DIGGING", false, null);

    public static final PotionEffectType INCREASE_DAMAGE = createDefault("INCREASE_DAMAGE", false, null);

    public static final PotionEffectType HEAL = createDefault("HEAL", false, null);

    public static final PotionEffectType HARM = createDefault("HARM", false, null);

    public static final PotionEffectType JUMP = createDefault("JUMP", false, null);

    public static final PotionEffectType CONFUSION = createDefault("CONFUSION", false, null);

    public static final PotionEffectType REGENERATION = createDefault("REGENERATION", false, null);

    public static final PotionEffectType DAMAGE_RESISTANCE = createDefault("DAMAGE_RESISTANCE", false, null);

    public static final PotionEffectType FIRE_RESISTANCE = createDefault("FIRE_RESISTANCE", false, null);

    public static final PotionEffectType WATER_BREATHING = createDefault("WATER_BREATHING", false, null);

    public static final PotionEffectType INVISIBILITY = createDefault("INVISIBILITY", false, null);

    public static final PotionEffectType BLINDNESS = createDefault("BLINDNESS", false, null);

    public static final PotionEffectType NIGHT_VISION = createDefault("NIGHT_VISION", false, null);

    public static final PotionEffectType HUNGER = createDefault("HUNGER", false, null);

    public static final PotionEffectType WEAKNESS = createDefault("WEAKNESS", false, null);

    public static final PotionEffectType POISON = createDefault("POISON", false, null);

    public static final PotionEffectType WITHER = createDefault("WITHER", false, null);

    public static final PotionEffectType HEALTH_BOOST = createDefault("HEALTH_BOOST", false, null);

    public static final PotionEffectType ABSORPTION = createDefault("ABSORPTION", false, null);

    public static final PotionEffectType SATURATION = createDefault("SATURATION", false, null);

    public static final PotionEffectType GLOWING = createDefault("GLOWING", false, null);

    public static final PotionEffectType LEVITATION = createDefault("LEVITATION", false, null);

    public static final PotionEffectType LUCK = createDefault("LUCK", false, null);

    public static final PotionEffectType UNLUCK = createDefault("UNLUCK", false, null);

    public static final PotionEffectType SLOW_FALLING = createDefault("SLOW_FALLING", false, null);

    public static final PotionEffectType CONDUIT_POWER = createDefault("CONDUIT_POWER", false, null);

    public static final PotionEffectType DOLPHINS_GRACE = createDefault("DOLPHINS_GRACE", false, null);

    public static final PotionEffectType BAD_OMEN = createDefault("BAD_OMEN", false, null);

    public static final PotionEffectType HERO_OF_THE_VILLAGE = createDefault("HERO_OF_THE_VILLAGE", false, null);


    private final boolean defaultType;
    private final String name;
    private final boolean instant;
    private final TextColor color;

    public PotionEffectType(String name, boolean instant, TextColor color) {
        this(false, name, instant, color);
    }

    private PotionEffectType(boolean defaultType, String name, boolean instant, TextColor color) {
        this.defaultType = defaultType;
        this.name = name;
        this.instant = instant;
        this.color = color;
    }

    public boolean isDefaultType() {
        return defaultType;
    }

    public String getName() {
        return name;
    }

    public boolean isInstant() {
        return instant;
    }

    public TextColor getColor() {
        return color;
    }

    private static PotionEffectType createDefault(String name, boolean instant, TextColor color) {
        return new PotionEffectType(true, name, instant, color);
    }
}
