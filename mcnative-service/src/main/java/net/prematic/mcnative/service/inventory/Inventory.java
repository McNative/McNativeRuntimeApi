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

package net.prematic.mcnative.service.inventory;

import net.prematic.mcnative.service.entity.HumanEntity;
import net.prematic.mcnative.service.inventory.item.ItemStack;
import net.prematic.mcnative.service.material.Material;

import java.util.Collection;
import java.util.stream.Stream;

public interface Inventory extends Iterable<ItemStack>{

    String getName();

    String getTitle();

    int getSize();

    InventoryHolder getHolder();

    Collection<HumanEntity> getViewers();

    ItemStack getItem(int index);

    ItemStack getItem(int x, int y);

    Collection<ItemStack> getItems();

    Stream<ItemStack> stream();

    int countItems();

    int countItems(Material material);


    boolean contains(ItemStack item);

    boolean contains(int index, ItemStack item);

    boolean contains(int x, int y,ItemStack item);

    boolean contains(Material material);

    boolean contains(int index, Material material);

    boolean contains(int x, int y,int index, Material material);

    boolean isEmpty();

    boolean isEmpty(int startIndex);

    boolean isEmpty(int startIndex, int endIndex);

    boolean hasPlace();

    boolean hasPlace(ItemStack item);

    boolean hasPlace(Material material);


    void setHolder(InventoryHolder holder);

    void setName(String name);

    void setTitle(String title);


    void setItem(int index, ItemStack item);

    void setItem(int x, int y, ItemStack item);

    void addItem(ItemStack items);

    void addItems(ItemStack... items);

    void addItem(int startIndex, int endIndex, ItemStack item);

    void remove(ItemStack item);

    void remove(Material material);

    void remove(int index);


    void clear();

    void clear(int startIndex);

    void clear(int startIndex, int endIndex);



    void replace(ItemStack source,ItemStack replacement);

    void replace(Material source, ItemStack replacement);

    void replace(Material source,Material replacement);


    void fill(ItemStack item);

    void fill(int startIndex, ItemStack item);

    void fill(int startIndex, int endIndex, ItemStack item);

    void fillSpaces(ItemStack item);

    void fillSpaces(int startIndex, ItemStack item);

    void fillSpaces(int startIndex, int endIndex, ItemStack item);


    void move(int index, int destination, short speed);


    void show(HumanEntity entity);

    void showAllPlayers();

    void close(HumanEntity entity);

    void close();


    void addListener();//InventoryListener

    void addAnimation(InventoryAnimation animation);

    void addAnimation(int startIndex, int endIndex, InventoryAnimation animation);



    static int toIndex(int x, int y){
        return y*9+x;//-1 Start item index by 0 or 1?
    }
}
