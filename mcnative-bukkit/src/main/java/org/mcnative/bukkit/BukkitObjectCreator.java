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

import net.prematic.libraries.utility.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.mcnative.bukkit.inventory.BukkitInventoryOwner;
import org.mcnative.bukkit.inventory.item.BukkitItemStack;
import org.mcnative.bukkit.inventory.type.*;
import org.mcnative.service.ObjectCreator;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.InventoryOwner;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.inventory.type.*;

public class BukkitObjectCreator implements ObjectCreator {

    //@Todo implements InventoryOwner
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Inventory> T newInventory(InventoryOwner owner, Class<T> inventoryClass, int size, String title) {
        InventoryHolder holder = owner != null ? new BukkitInventoryOwner(owner) : null;
        if(inventoryClass == AnvilInventory.class) {
            return (T) new BukkitAnvilInventory(owner, (org.bukkit.inventory.AnvilInventory)
                    Bukkit.createInventory(holder, InventoryType.ANVIL, title));
        } else if(inventoryClass == BeaconInventory.class) {
            return (T) new BukkitBeaconInventory(owner, (org.bukkit.inventory.BeaconInventory)
                    Bukkit.createInventory(holder, InventoryType.BEACON, title));
        } else if(inventoryClass == BrewerInventory.class) {
            return (T) new BukkitBrewerInventory(owner,(org.bukkit.inventory.BrewerInventory)
                    Bukkit.createInventory(holder, InventoryType.BREWING, title));
        } else if(inventoryClass == CartographyInventory.class) {
            return (T) new BukkitCartographyInventory(owner, (org.bukkit.inventory.CartographyInventory)
                    Bukkit.createInventory(holder, InventoryType.CARTOGRAPHY, title));
        } else if(inventoryClass == ChestInventory.class) {
            return (T) new BukkitChestInventory(owner, Bukkit.createInventory(null, size, title));
        } else if(inventoryClass == CraftingInventory.class) {
            return (T) new BukkitCraftingInventory(owner, (org.bukkit.inventory.CraftingInventory)
                    Bukkit.createInventory(holder, InventoryType.CRAFTING, title));
        } else if(inventoryClass == EnchantingInventory.class) {
            return (T) new BukkitEnchantingInventory(owner, (org.bukkit.inventory.EnchantingInventory)
                    Bukkit.createInventory(holder, InventoryType.ENCHANTING, title));
        } else if(inventoryClass == FurnaceInventory.class) {
            return (T) new BukkitFurnaceInventory(owner, (org.bukkit.inventory.FurnaceInventory)
                    Bukkit.createInventory(holder, InventoryType.FURNACE, title));
        } else if(inventoryClass == GrindstoneInventory.class) {
            return (T) new BukkitGrindstoneInventory(owner, (org.bukkit.inventory.GrindstoneInventory)
                    Bukkit.createInventory(holder, InventoryType.GRINDSTONE, title));
        } else if(inventoryClass == LecternInventory.class) {
            return (T) new BukkitLecternInventory(owner, (org.bukkit.inventory.LecternInventory)
                    Bukkit.createInventory(holder, InventoryType.LECTERN, title));
        } else if(inventoryClass == LoomInventory.class) {
            return (T) new BukkitLoomInventory(owner, (org.bukkit.inventory.LoomInventory)
                    Bukkit.createInventory(holder, InventoryType.LOOM, title));
        } else if(inventoryClass == PlayerInventory.class) {
            return (T) new BukkitPlayerInventory(owner, (org.bukkit.inventory.PlayerInventory)
                    Bukkit.createInventory(holder, InventoryType.PLAYER, title));
        } else if(inventoryClass == StonecutterInventory.class) {
            return (T) new BukkitStonecutterInventory(owner, (org.bukkit.inventory.StonecutterInventory)
                    Bukkit.createInventory(holder, InventoryType.STONECUTTER, title));
        }
        throw new IllegalArgumentException("Can't create inventory for " + inventoryClass + ".");
    }

    @Override
    public ItemStack newItemStack(Material material) {
        org.bukkit.Material bukkitMaterial = org.bukkit.Material.getMaterial(material.getName());
        Validate.notNull(bukkitMaterial, "Can't create itemstack for " + material + ".");
        return new BukkitItemStack(new org.bukkit.inventory.ItemStack(bukkitMaterial));
    }
}
