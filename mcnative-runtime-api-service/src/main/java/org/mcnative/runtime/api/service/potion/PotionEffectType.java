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

    private final String name;
    private final boolean instant;
    private final TextColor color;

    public PotionEffectType(String name, boolean instant, TextColor color) {
        this.name = name;
        this.instant = instant;
        this.color = color;
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
        return new PotionEffectType(name, instant, color);
    }
}
