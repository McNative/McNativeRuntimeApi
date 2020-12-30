/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 16:57
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

package org.mcnative.runtime.api.service.inventory.type;

import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public interface AnvilInventory extends Inventory {

    int SLOT_INPUT_LEFT = 0;

    int SLOT_INPUT_RIGHT = 1;

    int SLOT_OUTPUT = 2;


    ItemStack getInputLeft();

    ItemStack getInputRight();

    ItemStack getOutput();


    String getRenameText();

    int getRepairCost();

    int getMaximumRepairCost();


    void setInputLeft(ItemStack input);

    void setInputRight(ItemStack input);

    void setOutput(ItemStack output);


    void setRenameText(String text);

    void setRepairCost(int levels);

    void setMaximumRepairCost(int levels);

    void clearItemsOnClose(boolean clear);
}