/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 03.08.19 15:46
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

package net.prematic.mcnative.bukkit.inventory;

import net.prematic.mcnative.service.entity.HumanEntity;
import net.prematic.mcnative.service.inventory.InventoryHolder;
import net.prematic.mcnative.service.inventory.animation.InventoryAnimation;
import net.prematic.mcnative.service.inventory.item.ItemStack;
import net.prematic.mcnative.service.material.Material;
import org.bukkit.inventory.Inventory;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

public class BukkitInventory implements net.prematic.mcnative.service.inventory.Inventory {

    private final Inventory inventory;

    public BukkitInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getSize() {
        return this.inventory.getSize();
    }

    @Override
    public InventoryHolder getHolder() {
        return null;
    }

    @Override
    public Collection<HumanEntity> getViewers() {
        return null;
    }

    @Override
    public ItemStack getItem(int index) {
        return null;
    }

    @Override
    public ItemStack getItem(int x, int y) {
        return null;
    }

    @Override
    public Collection<ItemStack> getItems() {
        return null;
    }

    @Override
    public Stream<ItemStack> stream() {
        return null;
    }

    @Override
    public int countItems() {
        return 0;
    }

    @Override
    public int countItems(Material material) {
        return 0;
    }

    @Override
    public boolean contains(ItemStack item) {
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
        return false;
    }

    @Override
    public boolean contains(int index, Material material) {
        return false;
    }

    @Override
    public boolean contains(int x, int y, int index, Material material) {
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
    public void setHolder(InventoryHolder holder) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setItem(int index, ItemStack item) {

    }

    @Override
    public void setItem(int x, int y, ItemStack item) {

    }

    @Override
    public void addItem(ItemStack items) {

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

    }

    @Override
    public void close() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void addAnimation(InventoryAnimation animation) {

    }

    @Override
    public void addAnimation(int startIndex, int endIndex, InventoryAnimation animation) {

    }

    @Override
    public Iterator<ItemStack> iterator() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        else if(obj instanceof BukkitInventory) return ((BukkitInventory) obj).getInventory().equals(inventory);
        else return inventory.equals(obj);
    }
}
