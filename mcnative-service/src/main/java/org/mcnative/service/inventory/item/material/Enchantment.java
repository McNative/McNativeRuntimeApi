/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:45
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

package org.mcnative.service.inventory.item.material;

import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.service.NamespacedKey;
import org.mcnative.service.inventory.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class Enchantment implements NamespacedKey {

    public static final Collection<Enchantment> ENCHANTMENTS = new ArrayList<>();

    public static final Enchantment PROTECTION_ENVIRONMENTAL = create("protection").materialCategories(MaterialCategory.ARMOR).buildAndRegister();

    private final ObjectOwner owner;
    private final String name, namespace, key;
    private final int startLevel, maxLevel;
    private final MaterialCategory[] materialCategories;
    private final boolean treasure;
    private final Enchantment[] conflicts;

    public Enchantment(ObjectOwner owner, String name, String namespace, String key, int startLevel, int maxLevel, MaterialCategory[] materialCategories, boolean treasure, Enchantment[] conflicts) {
        this.owner = owner;
        this.name = name;
        this.namespace = namespace;
        this.key = key;
        this.startLevel = startLevel;
        this.maxLevel = maxLevel;
        this.materialCategories = materialCategories;
        this.treasure = treasure;
        this.conflicts = conflicts;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public int getStartLevel() {
        return startLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Enchantment[] getConflicts() {
        return conflicts;
    }

    public boolean canEnchant(ItemStack itemStack) {
        for (MaterialCategory materialCategory : this.materialCategories) {
            if(itemStack.getMaterial().hasCategory(materialCategory)) return true;
        }
        return false;
    }

    public MaterialCategory[] getMaterialCategories() {
        return materialCategories;
    }

    public boolean conflictsWith(Enchantment enchantment) {
        for (Enchantment conflict : this.conflicts) {
            if(conflict.equals(enchantment)) return true;
        }
        return false;
    }

    public boolean isTreasure() {
        return treasure;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o instanceof Enchantment) {
            Enchantment other = ((Enchantment) o);
            if(other.getName().equals(getName())) return true;
        }
        return false;
    }

    public static Builder create(String name) {
        return new Builder(name);
    }

    public static Enchantment register(Enchantment enchantment) {
        ENCHANTMENTS.add(enchantment);
        return enchantment;
    }

    private static class Builder {

        private ObjectOwner owner;
        private final String name;
        private String namespace, key;
        private int startLevel, maxLevel;
        private MaterialCategory[] materialCategories;
        private boolean treasure;
        private Enchantment[] conflicts;

        public Builder(String name) {
            this.name = name;
            this.owner = ObjectOwner.SYSTEM;
        }

        public Builder owner(ObjectOwner owner) {
            this.owner = owner;
            return this;
        }

        public Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder startLevel(int startLevel) {
            this.startLevel = startLevel;
            return this;
        }

        public Builder maxLevel(int maxLevel) {
            this.maxLevel = maxLevel;
            return this;
        }

        public Builder materialCategories(MaterialCategory... materialCategories) {
            this.materialCategories = materialCategories;
            return this;
        }

        public Builder treasure(boolean treasure) {
            this.treasure = treasure;
            return this;
        }

        public Builder conflicts(Enchantment[] conflicts) {
            this.conflicts = conflicts;
            return this;
        }

        public Enchantment build() {
            return new Enchantment(owner == null ? ObjectOwner.SYSTEM : owner,
                    name,
                    namespace == null ? NamespacedKey.MCNATIVE : namespace,
                    key,
                    startLevel == 0 ? 1 : startLevel,
                    maxLevel,
                    materialCategories,
                    treasure,
                    conflicts);
        }

        public Enchantment buildAndRegister() {
            return register(build());
        }
    }
}
