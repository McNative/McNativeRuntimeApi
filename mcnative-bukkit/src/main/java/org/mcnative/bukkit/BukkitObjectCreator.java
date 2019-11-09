/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 04.11.19, 14:51
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

package org.mcnative.bukkit;

import org.bukkit.Bukkit;
import org.mcnative.bukkit.inventory.BukkitInventory;
import org.mcnative.bukkit.inventory.item.BukkitItemStack;
import org.mcnative.service.ObjectCreator;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.InventoryOwner;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;

public class BukkitObjectCreator implements ObjectCreator {

    //@Todo implements InventoryOwner
    @Override
    public Inventory newInventory(InventoryOwner owner, int size, String title) {
        return new BukkitInventory(Bukkit.createInventory(null, size, title));
    }

    @Override
    public ItemStack newItemStack(Material material) {
        return new BukkitItemStack(new org.bukkit.inventory.ItemStack(org.bukkit.Material.getMaterial(material.getName())));
    }
}
