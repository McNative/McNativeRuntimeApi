/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.07.19 22:26
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

package net.prematic.mcnative.service.inventory.item;


import net.prematic.mcnative.service.NBTTag;
import net.prematic.mcnative.service.material.Enchantment;
import net.prematic.mcnative.service.material.Material;
import net.prematic.mcnative.service.inventory.item.data.ItemData;

import java.util.List;
import java.util.Map;

public interface ItemStack {

    Material getMaterial();

    ItemData getData();

    int getAmount();

    short getDurability();

    String getDisplayName();

    NBTTag getTag();

    Map<Enchantment, Integer> getEntchantments();

    List<String> getLore();

    List<ItemFlag> getFlags();


    boolean hasDisplayName();

    boolean hasTag();

    boolean hasLore();

    boolean hasFlag(ItemFlag flag);

    boolean isUnbreakable();


    void setMaterial(Material material);

    void setData(ItemData data);

    void setAmount(int amount);

    void setDurability(short durability);

    void setDisplayName(String name);

    void setTag(NBTTag tag);

    void addEntchantment(Enchantment enchantment);

    void addEntchantment(Enchantment enchantment, int level);

    void setLore(List<String> lore);

    void setLore(String... lore);

    void setLore(int index, String lore);

    void addLore(List<String> lore);

    void addLore(String... lore);

    void addLore(String lore);

    void setFlags(ItemFlag... flags);

    void addFlags(ItemFlag... flags);

    void setUnbreakable(boolean unbreakable);
}
