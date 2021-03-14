/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 27.08.19, 21:23
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

package org.mcnative.runtime.api.service.loot;

import org.mcnative.runtime.api.service.entity.Entity;
import org.mcnative.runtime.api.service.entity.living.HumanEntity;
import org.mcnative.runtime.api.service.world.location.Location;

public class LootContext {

    public static final int DEFAULT_LOOT_MODIFIER = -1;

    private final Location location;
    private final float luck;
    private final int lootingModifier;
    private final Entity lootedEntity;
    private final HumanEntity killer;

    private LootContext(Location location, float luck, int lootingModifier, Entity lootedEntity, HumanEntity killer) {
        this.location = location;
        this.luck = luck;
        this.lootingModifier = lootingModifier;
        this.lootedEntity = lootedEntity;
        this.killer = killer;
    }

    public Location getLocation() {
        return location;
    }

    public float getLuck() {
        return luck;
    }

    public int getLootingModifier() {
        return lootingModifier;
    }

    public Entity getLootedEntity() {
        return lootedEntity;
    }

    public HumanEntity getKiller() {
        return killer;
    }

    public static class Builder {

        private final Location location;
        private float luck;
        private int lootingModifier = LootContext.DEFAULT_LOOT_MODIFIER;
        private Entity lootedEntity;
        private HumanEntity killer;

        public Builder(Location location) {
            this.location = location;
        }

        public Builder luck(float luck) {
            this.luck = luck;
            return this;
        }

        public Builder lootingModifier(int modifier) {
            this.lootingModifier = modifier;
            return this;
        }

        public Builder lootedEntity(Entity lootedEntity) {
            this.lootedEntity = lootedEntity;
            return this;
        }

        public Builder killer(HumanEntity killer) {
            this.killer = killer;
            return this;
        }

        public LootContext build() {
            return new LootContext(location, luck, lootingModifier, lootedEntity, killer);
        }
    }
}
