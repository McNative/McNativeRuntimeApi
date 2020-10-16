/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 08.10.20, 19:39
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

package org.mcnative.service.event.player.inventory.defaults;

import org.mcnative.service.entity.living.Player;
import org.mcnative.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.service.inventory.ClickType;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.InventoryAction;
import org.mcnative.service.inventory.item.ItemStack;

public class DefaultMinecraftPlayerInventoryClickEvent implements MinecraftPlayerInventoryClickEvent {

    private final InventoryAction action;
    private final ClickType clickType;
    private final ItemStack clickedType;
    private final int hotbarButton;
    private final int rawSlot;
    private final int slot;
    private final Inventory.SlotType slotType;
    private boolean cancelled;
    private final Inventory inventory;
    private final Player player;

    public DefaultMinecraftPlayerInventoryClickEvent(InventoryAction action, ClickType clickType, ItemStack clickedType, int hotbarButton, int rawSlot, int slot, Inventory.SlotType slotType, boolean cancelled, Inventory inventory, Player player) {
        this.action = action;
        this.clickType = clickType;
        this.clickedType = clickedType;
        this.hotbarButton = hotbarButton;
        this.rawSlot = rawSlot;
        this.slot = slot;
        this.slotType = slotType;
        this.cancelled = cancelled;
        this.inventory = inventory;
        this.player = player;
    }

    @Override
    public InventoryAction getAction() {
        return this.action;
    }

    @Override
    public ClickType getClickType() {
        return this.clickType;
    }

    @Override
    public ItemStack getClickedItem() {
        return this.clickedType;
    }

    @Override
    public ItemStack getCursor() {
        return getPlayer().getItemOnCursor();
    }

    @Override
    public int getHotbarButton() {
        return this.hotbarButton;
    }

    @Override
    public int getRawSlot() {
        return this.rawSlot;
    }

    @Override
    public int getSlot() {
        return this.slot;
    }

    @Override
    public Inventory.SlotType getSlotType() {
        return this.slotType;
    }

    @Override
    public boolean isLeftClick() {
        return getClickType().isLeftClick();
    }

    @Override
    public boolean isRightClick() {
        return getClickType().isRightClick();
    }

    @Override
    public boolean isShiftClick() {
        return getClickType().isShiftClick();
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Player getOnlinePlayer() {
        return this.player;
    }
}
