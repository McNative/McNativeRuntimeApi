/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 24.08.19, 12:48
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

package org.mcnative.runtime.api.service.inventory.recipe.choice;

import org.mcnative.runtime.api.service.inventory.item.ItemStack;

import java.util.Arrays;
import java.util.Collection;

public class ItemStackRecipeChoice implements RecipeChoice {

    private final Collection<ItemStack> choices;

    public ItemStackRecipeChoice(Collection<ItemStack> choices) {
        this.choices = choices;
    }

    public ItemStackRecipeChoice(ItemStack... choices) {
        this(Arrays.asList(choices));
    }

    @Override
    public boolean test(ItemStack itemStack) {
        for (ItemStack choice : this.choices) {
            if(!choice.equals(itemStack)) return false;
        }
        return true;
    }
}
