/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 07.10.20, 16:58
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

package org.mcnative.runtime.api.service.protocol.packet.type.player.inventory;

import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.service.inventory.ClickType;
import org.mcnative.runtime.api.service.inventory.InventoryAction;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public class InventoryClickWindowPacket implements MinecraftPacket {

    private byte windowId;
    private short slot;
    private byte button;
    private short actionNumber;
    private int mode;
    private ItemStack itemStack;
    private InventoryAction inventoryAction;
    private ClickType clickType;

    public byte getWindowId() {
        return windowId;
    }

    public short getSlot() {
        return slot;
    }

    public byte getButton() {
        return button;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public int getMode() {
        return mode;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public InventoryAction getInventoryAction() {
        return inventoryAction;
    }

    public ClickType getClickType() {
        return clickType;
    }

}
