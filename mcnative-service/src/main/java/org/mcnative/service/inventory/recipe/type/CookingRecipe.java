/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 25.08.19, 12:30
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

package org.mcnative.service.inventory.recipe.type;

import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.recipe.NamedRecipe;
import org.mcnative.service.inventory.recipe.choice.RecipeChoice;

public class CookingRecipe implements NamedRecipe {

    private final String namespace, key, group;
    private final ItemStack result;
    private final RecipeChoice ingredient;
    private final float experience;
    private final int cookingTime;

    public CookingRecipe(String namespace, String key, String group, ItemStack result, RecipeChoice ingredient, float experience, int cookingTime) {
        this.namespace = namespace;
        this.key = key;
        this.group = group;
        this.result = result;
        this.ingredient = ingredient;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public ItemStack getResult() {
        return this.result;
    }

    public String getGroup() {
        return group;
    }

    public RecipeChoice getIngredient() {
        return ingredient;
    }

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}
