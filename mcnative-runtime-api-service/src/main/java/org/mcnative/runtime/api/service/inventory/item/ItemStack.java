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
import org.mcnative.runtime.api.service.inventory.item.material.MaterialData;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface ItemStack {

    ItemStack[] EMPTY = new ItemStack[0];

    Material getMaterial();

    ItemData getData();

    <T extends MaterialData> ItemStack getData(Class<T> dataClass, Consumer<T> materialData);

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

    ItemStack setDisplayName(MessageComponent<?> name);

    ItemStack setTag(NBTTag tag);


    ItemStack addEnchantment(Enchantment enchantment);

    ItemStack addEnchantment(Enchantment enchantment, int level);

    ItemStack removeEnchantment(Enchantment enchantment);

    /**
     *
     * @param enchantment
     * @return old enchantment level or 0
     */
    int removeEnchantmentSafe(Enchantment enchantment);


    ItemStack setLore(List<MessageComponent<?>> lore);

    ItemStack setLore(MessageComponent<?>... lore);

    ItemStack setLore(int index, MessageComponent<?> lore);

    ItemStack addLore(List<MessageComponent<?>> lore);

    ItemStack addLore(MessageComponent<?>... lore);

    ItemStack addLore(MessageComponent<?> lore);

    ItemStack setFlags(ItemFlag... flags);

    ItemStack addFlags(ItemFlag... flags);

    ItemStack removeFlag(ItemFlag... flags);

    ItemStack setGlowing(boolean glowing);

    static ItemStack newItemStack(Material material) {
        return McNative.getInstance().getObjectFactory().createObject(ItemStack.class,material);
    }
}
