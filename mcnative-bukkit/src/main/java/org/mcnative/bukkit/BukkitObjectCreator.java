/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 21.03.20, 13:56
 * @web %web%
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

import net.pretronic.libraries.utility.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.mcnative.bukkit.inventory.BukkitInventoryHolder;
import org.mcnative.bukkit.inventory.BukkitInventoryOwner;
import org.mcnative.bukkit.inventory.item.BukkitItemStack;
import org.mcnative.bukkit.inventory.type.*;
import org.mcnative.common.McNative;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.service.ObjectCreator;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.InventoryOwner;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.inventory.type.*;

public class BukkitObjectCreator implements ObjectCreator {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Inventory> T newInventory(InventoryOwner owner, Class<T> inventoryClass, int size, String title) {
        InventoryHolder holder = new BukkitInventoryHolder(owner);
        if(inventoryClass == AnvilInventory.class) {
            org.bukkit.inventory.Inventory inventory = Bukkit.createInventory(holder, InventoryType.ANVIL);
            org.bukkit.inventory.AnvilInventory anvilInventory = (org.bukkit.inventory.AnvilInventory) inventory;
            return (T) new BukkitAnvilInventory(owner, anvilInventory);
        } else if(inventoryClass == BeaconInventory.class) {
            return (T) new BukkitBeaconInventory(owner, (org.bukkit.inventory.BeaconInventory)
                    Bukkit.createInventory(holder, InventoryType.BEACON));
        } else if(inventoryClass == BrewerInventory.class) {
            return (T) new BukkitBrewerInventory(owner,(org.bukkit.inventory.BrewerInventory)
                    Bukkit.createInventory(holder, InventoryType.BREWING));
        } else if(inventoryClass == CartographyInventory.class) {
            if(McNative.getInstance().getPlatform().getProtocolVersion().isOlder(MinecraftProtocolVersion.JE_1_14)) {
                throw new UnsupportedOperationException("Can't create LlamaInventory. Too old version.");
            }
            return (T) new BukkitCartographyInventory(owner, (org.bukkit.inventory.CartographyInventory)
                    Bukkit.createInventory(holder, InventoryType.CARTOGRAPHY));
        } else if(inventoryClass == ChestInventory.class || inventoryClass == Inventory.class || inventoryClass == DoubleChestInventory.class) {
            return (T) new BukkitChestInventory<>(owner, Bukkit.createInventory(holder, size, title));
        } else if(inventoryClass == CraftingInventory.class) {
            return (T) new BukkitCraftingInventory(owner, (org.bukkit.inventory.CraftingInventory)
                    Bukkit.createInventory(holder, InventoryType.CRAFTING));
        } else if(inventoryClass == EnchantingInventory.class) {
            return (T) new BukkitEnchantingInventory(owner, (org.bukkit.inventory.EnchantingInventory)
                    Bukkit.createInventory(holder, InventoryType.ENCHANTING));
        } else if(inventoryClass == FurnaceInventory.class) {
            return (T) new BukkitFurnaceInventory(owner, (org.bukkit.inventory.FurnaceInventory)
                    Bukkit.createInventory(holder, InventoryType.FURNACE));
        } else if(inventoryClass == GrindstoneInventory.class) {
            if(McNative.getInstance().getPlatform().getProtocolVersion().isOlder(MinecraftProtocolVersion.JE_1_14)) {
                throw new UnsupportedOperationException("Can't create LlamaInventory. Too old version.");
            }
            return (T) new BukkitGrindstoneInventory(owner, (org.bukkit.inventory.GrindstoneInventory)
                    Bukkit.createInventory(holder, InventoryType.GRINDSTONE));
        } else if(inventoryClass == LecternInventory.class) {
            if(McNative.getInstance().getPlatform().getProtocolVersion().isOlder(MinecraftProtocolVersion.JE_1_14)) {
                throw new UnsupportedOperationException("Can't create LlamaInventory. Too old version.");
            }
            return (T) new BukkitLecternInventory(owner, (org.bukkit.inventory.LecternInventory)
                    Bukkit.createInventory(holder, InventoryType.LECTERN));
        } else if(inventoryClass == LoomInventory.class) {
            if(McNative.getInstance().getPlatform().getProtocolVersion().isOlder(MinecraftProtocolVersion.JE_1_14)) {
                throw new UnsupportedOperationException("Can't create LlamaInventory. Too old version.");
            }
            return (T) new BukkitLoomInventory(owner, (org.bukkit.inventory.LoomInventory)
                    Bukkit.createInventory(holder, InventoryType.LOOM));
        } else if(inventoryClass == PlayerInventory.class) {
            return (T) new BukkitPlayerInventory(owner, (org.bukkit.inventory.PlayerInventory)
                    Bukkit.createInventory(holder, InventoryType.PLAYER));
        } else if(inventoryClass == StonecutterInventory.class) {
            if(McNative.getInstance().getPlatform().getProtocolVersion().isOlder(MinecraftProtocolVersion.JE_1_14)) {
                throw new UnsupportedOperationException("Can't create LlamaInventory. Too old version.");
            }
            return (T) new BukkitStonecutterInventory(owner, (org.bukkit.inventory.StonecutterInventory)
                    Bukkit.createInventory(holder, InventoryType.STONECUTTER));
        } else if(inventoryClass == ArmorableHorseInventory.class || inventoryClass == HorseInventory.class
                || inventoryClass == LlamaInventory.class) {
            throw new IllegalArgumentException("Not possible to create " + inventoryClass + " on bukkit platform");
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
