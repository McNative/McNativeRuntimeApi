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

    boolean hasDurability();

    int getDurability();

    String getDisplayName();

    NBTTag getTag();

    Map<Enchantment, Integer> getEnchantments();

    List<String> getLore();

    List<ItemFlag> getFlags();


    boolean hasDisplayName();

    boolean hasTag();

    boolean hasLore();

    boolean hasFlag(ItemFlag flag);


    void setMaterial(Material material);

    void setData(ItemData data);

    ItemStack setAmount(int amount);

    void setDurability(int durability);

    void setDisplayName(String name);

    void setTag(NBTTag tag);

    void addEnchantment(Enchantment enchantment);

    void addEnchantment(Enchantment enchantment, int level);

    void setLore(List<String> lore);

    void setLore(String... lore);

    void setLore(int index, String lore);

    void addLore(List<String> lore);

    void addLore(String... lore);

    void addLore(String lore);

    void setFlags(ItemFlag... flags);

    void addFlags(ItemFlag... flags);

    void removeFlag(ItemFlag... flags);

    static ItemStack newItemStack(Material material) {
        return McNative.getInstance().getObjectFactory().createObject(ItemStack.class,material);
    }
}
