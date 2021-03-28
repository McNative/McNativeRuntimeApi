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

package org.mcnative.runtime.api.service.inventory.item;


import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.service.NBTTag;
import org.mcnative.runtime.api.service.inventory.item.data.ItemData;
import org.mcnative.runtime.api.service.inventory.item.material.Enchantment;
import org.mcnative.runtime.api.service.inventory.item.material.Material;

import java.util.List;
import java.util.Map;

public interface ItemStack {

    ItemStack[] EMPTY = new ItemStack[0];

    Material getMaterial();

    ItemData getData();

    int getAmount();

    String getDisplayName();

    NBTTag getTag();

    Map<Enchantment, Integer> getEnchantments();

    List<String> getLore();

    List<ItemFlag> getFlags();


    boolean hasDisplayName();

    boolean hasLore();

    boolean hasFlag(ItemFlag flag);


    ItemStack setMaterial(Material material);

    ItemStack setData(ItemData data);

    ItemStack setAmount(int amount);

    ItemStack setDisplayName(String name);

    ItemStack setTag(NBTTag tag);

    ItemStack addEnchantment(Enchantment enchantment);

    ItemStack addEnchantment(Enchantment enchantment, int level);

    ItemStack setLore(List<String> lore);

    ItemStack setLore(String... lore);

    ItemStack setLore(int index, String lore);

    ItemStack addLore(List<String> lore);

    ItemStack addLore(String... lore);

    ItemStack addLore(String lore);

    ItemStack setFlags(ItemFlag... flags);

    ItemStack addFlags(ItemFlag... flags);

    ItemStack removeFlag(ItemFlag... flags);

    static ItemStack newItemStack(Material material) {
        return McNative.getInstance().getObjectFactory().createObject(ItemStack.class,material);
    }
}
