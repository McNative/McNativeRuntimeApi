/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 25.08.19, 12:42
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

package org.mcnative.runtime.api.service.inventory.recipe.type;

import net.pretronic.libraries.utility.map.Pair;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;
import org.mcnative.runtime.api.service.inventory.recipe.Recipe;

public class MerchantRecipe implements Recipe {

    private final ItemStack result;
    private final Pair<ItemStack, ItemStack> ingredients;
    private final int uses, maxUses;
    private final boolean experienceReward;
    private final int villagerExperience;
    private final float priceMultiplier;

    public MerchantRecipe(ItemStack result, Pair<ItemStack, ItemStack> ingredients, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier) {
        this.result = result;
        this.ingredients = ingredients;
        this.uses = uses;
        this.maxUses = maxUses;
        this.experienceReward = experienceReward;
        this.villagerExperience = villagerExperience;
        this.priceMultiplier = priceMultiplier;
    }

    @Override
    public ItemStack getResult() {
        return this.result;
    }

    public Pair<ItemStack, ItemStack> getIngredients() {
        return ingredients;
    }

    public int getUses() {
        return uses;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public boolean isExperienceReward() {
        return experienceReward;
    }

    public int getVillagerExperience() {
        return villagerExperience;
    }

    public float getPriceMultiplier() {
        return priceMultiplier;
    }
}
