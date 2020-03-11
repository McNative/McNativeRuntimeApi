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

import net.pretronic.libraries.utility.Validate;
import org.mcnative.service.NamespacedKey;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.recipe.NamedRecipe;
import org.mcnative.service.inventory.recipe.choice.RecipeChoice;

import java.util.ArrayList;
import java.util.Collection;

public class ShapelessRecipe implements NamedRecipe {

    private final String namespace, key, group;
    private final Collection<RecipeChoice> ingredients;
    private final int maxIngredients;
    private final ItemStack result;

    public ShapelessRecipe(String namespace, String key, String group, Collection<RecipeChoice> ingredients, int maxIngredients, ItemStack result) {
        Validate.checkMatches(namespace, NamespacedKey.VALID_NAMESPACE, "Invalid namespace. Must be [a-z0-9._-]: %s", namespace);
        Validate.checkMatches(key, NamespacedKey.VALID_KEY, "Invalid key. Must be [a-z0-9/._-]: %s", key);
        this.namespace = namespace;
        this.key = key;
        Validate.isTrue(getName().length() > 255, "Name must be less than 256 characters (%s)", getName());
        this.group = group;
        this.ingredients = ingredients;
        this.maxIngredients = maxIngredients;
        this.result = result;
    }

    public ShapelessRecipe(String namespace, String key, int maxIngredients, ItemStack result) {
        this(namespace, key, "", new ArrayList<>(), maxIngredients, result);
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
        return this.group;
    }

    public Collection<RecipeChoice> getIngredients() {
        return ingredients;
    }

    public ShapelessRecipe addIngredient(RecipeChoice recipeChoice) {
        Validate.isTrue(this.ingredients.size()+1 > this.maxIngredients, "Shapeless recipes cannot have more than %s ingredients", this.getClass().getSimpleName(), this.maxIngredients);
        this.ingredients.add(recipeChoice);
        return this;
    }

    public ShapelessRecipe removeIngredient(RecipeChoice recipeChoice) {
        this.ingredients.remove(recipeChoice);
        return this;
    }
}
