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

package org.mcnative.service.entity;

import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.PlayerInventory;
import org.mcnative.service.inventory.item.ItemStack;

public interface HumanEntity extends Entity {

    PlayerInventory getInventory();

    ItemStack getCurser();

    ItemStack getItemInHand();

    boolean isBlocking();

    boolean isSleeping();

    void setItemInHand(ItemStack item);

    void openInventory(Inventory inventory);

    void openInventory();

    void closeInventory();



}
