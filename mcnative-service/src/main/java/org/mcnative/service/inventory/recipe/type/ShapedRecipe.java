/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 25.08.19, 12:29
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

import net.prematic.libraries.utility.Validate;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.recipe.NamedRecipe;
import org.mcnative.service.inventory.recipe.choice.RecipeChoice;

public class ShapedRecipe implements NamedRecipe {

    private final String namespace, key, group;
    private final int maxXIngredients, maxRows;
    private final ItemStack result;

    private final RecipeChoice[] ingredients;

    public ShapedRecipe(String namespace, String key, String group, int maxXIngredients, int maxRows, ItemStack result) {
        this.namespace = namespace;
        this.key = key;
        this.group = group;
        this.ingredients = new RecipeChoice[maxRows*maxXIngredients];
        this.maxXIngredients = maxXIngredients;
        this.maxRows = maxRows;
        this.result = result;
    }

    @Override
    public ItemStack getResult() {
        return this.result;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public String getGroup() {
        return group;
    }

    public ShapedRecipe addIngredient(int index, RecipeChoice recipeChoice) {
        Validate.isTrue(this.ingredients.length+1 > (maxRows*maxXIngredients), "Shaped recipes cannot have more than %s ingredients",maxRows*maxXIngredients);
        this.ingredients[index] = recipeChoice;
        return this;
    }

    public ShapedRecipe addIngredient(int row, int place, RecipeChoice recipeChoice) {
        return addIngredient(toIndex(row, place), recipeChoice);
    }

    public ShapedRecipe removeIngredient(RecipeChoice recipeChoice) {
        for (int i = 0; i < this.ingredients.length; i++) {
            if(this.ingredients[i].equals(recipeChoice)) this.ingredients[i] = null;
        }
        return this;
    }

    public ShapedRecipe removeIngredient(int index) {
        this.ingredients[index] = null;
        return this;
    }

    public ShapedRecipe removeIngredient(int row, int place) {
        this.ingredients[toIndex(row, place)] = null;
        return this;
    }

    private int toIndex(int row, int place) {
        return (row*this.maxXIngredients+place)-1;
    }
}
