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

import org.mcnative.service.NamespacedKey;
import org.mcnative.service.inventory.item.data.ItemData;
import org.mcnative.service.world.block.data.BlockData;

public class Material implements NamespacedKey {

    public static final Material AIR = create("Air").build();
    public static final Material STONE = create("Stone").build();


    private final String name, namespace, key;
    private final Class<?> materialClass;
    private final MaterialCategory[] categories;
    private final int maxStackSize;
    private final short maxDurability;
    private final float hardness, blastResistance;
    private final boolean empty, solid, transparent, flammable, burnable, fuel, occluding, gravity, interactable;

    public Material(String name, String namespace, String key, Class<?> materialClass, MaterialCategory[] categories, int maxStackSize, short maxDurability, float hardness, float blastResistance, boolean empty, boolean solid, boolean transparent, boolean flammable, boolean burnable, boolean fuel, boolean occluding, boolean gravity, boolean interactable) {
        this.name = name;
        this.namespace = namespace;
        this.key = key;
        this.materialClass = materialClass;
        this.categories = categories;
        this.maxStackSize = maxStackSize;
        this.maxDurability = maxDurability;
        this.hardness = hardness;
        this.blastResistance = blastResistance;
        this.empty = empty;
        this.solid = solid;
        this.transparent = transparent;
        this.flammable = flammable;
        this.burnable = burnable;
        this.fuel = fuel;
        this.occluding = occluding;
        this.gravity = gravity;
        this.interactable = interactable;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public Class<?> getMaterialClass() {
        return materialClass;
    }

    public boolean hasCategory(MaterialCategory materialCategory) {
        if(materialCategory == null) return false;
        for (MaterialCategory category : this.categories) {
            if(category.equals(materialCategory)) return true;
        }
        return false;
    }

    public MaterialCategory[] getCategories() {
        return categories;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public short getMaxDurability() {
        return maxDurability;
    }

    public float getHardness() {
        return hardness;
    }

    public float getBlastResistance() {
        return blastResistance;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public boolean isFlammable() {
        return flammable;
    }

    public boolean isBurnable() {
        return burnable;
    }

    public boolean isFuel() {
        return fuel;
    }

    public boolean isOccluding() {
        return occluding;
    }

    public boolean hasGravity() {
        return gravity;
    }

    public boolean isInteractable() {
        return interactable;
    }

    private BlockData newBlockData(){
        return null;
    }

    private ItemData newItemData(){
        return null;
    }


    /*

        Extra data


     */


    public static Builder create(String name){
        return new Builder(name);
    }

    private static class Builder {

        private final String name;
        private String namespace, key;
        private Class<?> materialClass;
        private MaterialCategory[] categories;
        private int maxStackSize;
        private short maxDurability;
        private float hardness, blastResistance;
        private boolean empty, solid, transparent, flammable, burnable, fuel, occluding, gravity, interactable;

        public Builder(String name) {
            this.name = name;
            this.hardness = 0.0f;
            this.blastResistance = 0.0f;
        }

        public Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder materialClass(Class<?> materialClass) {
            this.materialClass = materialClass;
            return this;
        }

        public Builder categories(MaterialCategory[] categories) {
            this.categories = categories;
            return this;
        }

        public Builder maxStackSize(int maxStackSize) {
            this.maxStackSize = maxStackSize;
            return this;
        }

        public Builder maxDurability(short maxDurability) {
            this.maxDurability = maxDurability;
            return this;
        }

        public Builder hardness(float hardness) {
            this.hardness = hardness;
            return this;
        }

        public Builder blastResistance(float blastResistance) {
            this.blastResistance = blastResistance;
            return this;
        }

        public Builder empty(boolean empty) {
            this.empty = empty;
            return this;
        }

        public Builder solid(boolean solid) {
            this.solid = solid;
            return this;
        }

        public Builder transparent(boolean transparent) {
            this.transparent = transparent;
            return this;
        }

        public Builder flammable(boolean flammable) {
            this.flammable = flammable;
            return this;
        }

        public Builder burnable(boolean burnable) {
            this.burnable = burnable;
            return this;
        }

        public Builder fuel(boolean fuel) {
            this.fuel = fuel;
            return this;
        }

        public Builder occluding(boolean occluding) {
            this.occluding = occluding;
            return this;
        }

        public Builder gravity(boolean gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder interactable(boolean interactable) {
            this.interactable = interactable;
            return this;
        }

        public Material build() {
            return new Material(name, namespace, key, materialClass, categories, maxStackSize, maxDurability, hardness, blastResistance, empty, solid, transparent, flammable, burnable, fuel, occluding, gravity, interactable);
        }
    }
}