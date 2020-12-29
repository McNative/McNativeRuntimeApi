/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 16.09.19, 20:49
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

package org.mcnative.runtime.api.service.inventory.item.potion;

public class PotionEffect {

    private final PotionEffectType type;
    private final int amplifier, duration;
    private final boolean ambient, particles, icon;

    public PotionEffect(PotionEffectType type, int amplifier, int duration, boolean ambient, boolean particles, boolean icon) {
        this.type = type;
        this.amplifier = amplifier;
        this.duration = duration;
        this.ambient = ambient;
        this.particles = particles;
        this.icon = icon;
    }

    public PotionEffectType getType() {
        return type;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public boolean isParticles() {
        return particles;
    }

    public boolean isIcon() {
        return icon;
    }

    public static Builder create() {
        return new Builder();
    }

    private static class Builder {

        private PotionEffectType type;
        private int amplifier;
        private int duration;
        private boolean ambient;
        private boolean particles;
        private boolean icon;

        public Builder type(PotionEffectType type) {
            this.type = type;
            return this;
        }

        public Builder amplifier(int amplifier) {
            this.amplifier = amplifier;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder ambient(boolean ambient) {
            this.ambient = ambient;
            return this;
        }

        public Builder particles(boolean particles) {
            this.particles = particles;
            return this;
        }

        public Builder icon(boolean icon) {
            this.icon = icon;
            return this;
        }

        public PotionEffect build() {
            return new PotionEffect(type, amplifier, duration, ambient, particles, icon);
        }
    }
}
