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


import net.prematic.mcnative.service.inventory.item.meta.ItemMeta;
import net.prematic.mcnative.service.material.Enchantment;
import net.prematic.mcnative.service.material.Material;

import java.util.Map;

public interface ItemStack {

    Material getMaterial();

    ItemMeta getMeta();

    Map<Enchantment, Integer> getEntchantments();

    void addEnchantment(Enchantment enchantment, int level);

    void removeEnchantment(Enchantment enchantment);

    int getAmount();

    short getDurability();

    void setMaterial(Material material);

    void setMeta(ItemMeta meta);

    void setAmount(int amount);

    void setDurability(short durability);

    void addEntchantment(Enchantment enchantment);

    void addEntchantment(Enchantment enchantment, int level);
}
