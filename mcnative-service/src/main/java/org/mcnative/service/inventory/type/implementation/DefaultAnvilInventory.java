/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 07.10.20, 16:33
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

package org.mcnative.service.inventory.type.implementation;

import net.pretronic.libraries.utility.annonations.Internal;
import org.mcnative.service.entity.living.HumanEntity;
import org.mcnative.service.entity.living.Player;
import org.mcnative.service.inventory.InventoryOwner;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.inventory.type.AnvilInventory;
import org.mcnative.service.protocol.packet.type.player.inventory.InventorySetSlotPacket;

import java.util.*;
import java.util.stream.Stream;

public class DefaultAnvilInventory implements AnvilInventory {

    private InventoryOwner owner;
    private final ItemStack[] items;
    private final Map<HumanEntity, Byte> viewers;

    public DefaultAnvilInventory(InventoryOwner owner) {
        this.owner = owner;
        this.items = new ItemStack[3];
        this.viewers = new HashMap<>();
    }

    @Override
    public ItemStack getInputLeft() {
        return getItem(SLOT_INPUT_LEFT);
    }

    @Override
    public ItemStack getInputRight() {
        return getItem(AnvilInventory.SLOT_INPUT_RIGHT);
    }

    @Override
    public ItemStack getOutput() {
        return getItem(AnvilInventory.SLOT_OUTPUT);
    }

    @Override
    public String getRenameText() {
        return null;
    }

    @Override
    public int getRepairCost() {
        return 0;
    }

    @Override
    public int getMaximumRepairCost() {
        return 0;
    }

    @Override
    public void setInputLeft(ItemStack input) {
        setItem(AnvilInventory.SLOT_INPUT_LEFT, input);
    }

    @Override
    public void setInputRight(ItemStack input) {
        setItem(AnvilInventory.SLOT_INPUT_RIGHT, input);
    }

    @Override
    public void setOutput(ItemStack output) {
        setItem(AnvilInventory.SLOT_OUTPUT, output);
    }

    @Override
    public void setRenameText(String text) {

    }

    @Override
    public void setRepairCost(int levels) {

    }

    @Override
    public void setMaximumRepairCost(int levels) {

    }

    @Override
    public void clearItemsOnClose(boolean clear) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getSize() {
        return this.items.length;
    }

    @Override
    public InventoryOwner getOwner() {
        return this.owner;
    }

    @Override
    public Collection<HumanEntity> getViewers() {
        return this.viewers.keySet();
    }

    @Internal
    public boolean matchWith(HumanEntity entity, byte windowId) {
        for (Map.Entry<HumanEntity, Byte> entry : viewers.entrySet()) {
            if(entry.getKey().getUniqueId().equals(entity.getUniqueId()) && entry.getValue() == windowId) return true;
        }
        return true;
    }

    @Internal
    public void addViewer(HumanEntity humanEntity, byte windowId) {
        this.viewers.put(humanEntity, windowId);
    }

    @Internal
    public void removeViewer(HumanEntity humanEntity) {
        this.viewers.remove(humanEntity);
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items[index];
    }

    @Override
    public ItemStack getItem(int x, int y) {
        return null;
    }

    @Override
    public Collection<ItemStack> getItems() {
        return Arrays.asList(this.items);
    }

    @Override
    public Stream<ItemStack> stream() {
        return null;
    }

    @Override
    public int countItemStacks() {
        return 0;
    }

    @Override
    public int countItemStacks(Material material) {
        return 0;
    }

    @Override
    public int countItemStacks(ItemStack itemStack) {
        return 0;
    }

    @Override
    public boolean contains(ItemStack item) {
        for (ItemStack itemStack : this.items) {
            if(itemStack.equals(item)) return true;
        }
        return false;
    }

    @Override
    public boolean contains(int index, ItemStack item) {
        return false;
    }

    @Override
    public boolean contains(int x, int y, ItemStack item) {
        return false;
    }

    @Override
    public boolean contains(Material material) {
        for (ItemStack item : this.items) {
            if(item != null && item.getMaterial().equals(material)) return true;
        }
        return false;
    }

    @Override
    public boolean contains(int index, Material material) {
        return false;
    }

    @Override
    public boolean contains(int x, int y, Material material) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isEmpty(int startIndex) {
        return false;
    }

    @Override
    public boolean isEmpty(int startIndex, int endIndex) {
        return false;
    }

    @Override
    public boolean hasPlace() {
        return false;
    }

    @Override
    public boolean hasPlace(ItemStack item) {
        return false;
    }

    @Override
    public boolean hasPlace(Material material) {
        return false;
    }

    @Override
    public void setOwner(InventoryOwner owner) {
        this.owner = owner;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setItem(int index, ItemStack item) {
        this.items[index] = item;
        for (Map.Entry<HumanEntity, Byte> entry : viewers.entrySet()) {
            HumanEntity viewer = entry.getKey();
            if(viewer instanceof Player) {
                ((Player) viewer).sendPacket(new InventorySetSlotPacket(entry.getValue(), (short) index, item));
            }
        }
    }

    @Override
    public void setItem(int x, int y, ItemStack item) {

    }

    @Override
    public void addItems(ItemStack... items) {

    }

    @Override
    public void addItem(int startIndex, int endIndex, ItemStack item) {

    }

    @Override
    public void remove(ItemStack item) {

    }

    @Override
    public void remove(Material material) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void clear(int startIndex) {

    }

    @Override
    public void clear(int startIndex, int endIndex) {

    }

    @Override
    public void replace(ItemStack source, ItemStack replacement) {

    }

    @Override
    public void replace(Material source, ItemStack replacement) {

    }

    @Override
    public void replace(Material source, Material replacement) {

    }

    @Override
    public void fill(ItemStack item) {

    }

    @Override
    public void fill(int startIndex, ItemStack item) {

    }

    @Override
    public void fill(int startIndex, int endIndex, ItemStack item) {

    }

    @Override
    public void fillSpaces(ItemStack item) {

    }

    @Override
    public void fillSpaces(int startIndex, ItemStack item) {

    }

    @Override
    public void fillSpaces(int startIndex, int endIndex, ItemStack item) {

    }

    @Override
    public void move(int index, int destination, short speed) {

    }

    @Override
    public void show(HumanEntity entity) {

    }

    @Override
    public void showAllPlayers() {

    }

    @Override
    public void close(HumanEntity entity) {
        entity.closeInventory();
    }

    @Override
    public void close() {
        for (HumanEntity viewer : viewers.keySet()) {
            viewer.closeInventory();
        }
    }

    @Override
    public void addListener() {

    }

    @Override
    public Iterator<ItemStack> iterator() {
        return null;
    }
}
