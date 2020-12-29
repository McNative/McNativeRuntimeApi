/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 23.08.19, 22:06
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

import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.protocol.support.BEProtocolSupport;
import org.mcnative.runtime.api.protocol.support.JEProtocolSupport;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

@JEProtocolSupport(min= MinecraftProtocolVersion.JE_1_14)
@BEProtocolSupport(min=MinecraftProtocolVersion.BE_1_10)
public interface StonecutterInventory extends Inventory {

    ItemStack getInput();

    void setInput(ItemStack item);

    ItemStack getOutput();

    void setOutput(ItemStack item);


    void clearItemsOnClose(boolean clear);

}
