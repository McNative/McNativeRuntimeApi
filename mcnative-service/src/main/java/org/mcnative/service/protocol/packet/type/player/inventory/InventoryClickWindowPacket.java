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

package org.mcnative.service.protocol.packet.type.player.inventory;

import io.netty.buffer.ByteBuf;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.service.GameMode;
import org.mcnative.service.entity.living.Player;
import org.mcnative.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.service.inventory.ClickType;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.InventoryAction;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.inventory.type.PlayerInventory;
import org.mcnative.service.protocol.ServiceMinecraftProtocolUtil;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class InventoryClickWindowPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(InventoryClickWindowPacket.class
            ,on(PacketDirection.INCOMING,
                    //map(MinecraftProtocolVersion.JE_1_8, 0x0E),
                    map(MinecraftProtocolVersion.JE_1_9, 0x07),
                    map(MinecraftProtocolVersion.JE_1_12, 0x08),
                    map(MinecraftProtocolVersion.JE_1_12_1, 0x07),
                    map(MinecraftProtocolVersion.JE_1_13, 0x08),
                    map(MinecraftProtocolVersion.JE_1_14, 0x09),
                    map(MinecraftProtocolVersion.JE_1_15_2, 0x08)));

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
    //C() == slot
    //d() == button
    //g() mode als enum
    public void setInventoryActionAndClickType(Inventory inventory, Player player) {
        ClickType clickType = ClickType.UNKNOWN;
        InventoryAction action = InventoryAction.UNKNOWN;

        byte button = getButton();
        int mode = getMode();
        short slot = getSlot();
        switch (mode) {
            case 0: {
                if(button == 0) {
                    clickType = ClickType.LEFT;
                } else if(button == 1) {
                    clickType = ClickType.RIGHT;
                } else {
                    break;
                }

                action = InventoryAction.NOTHING;
                if(slot == -999) {
                    if(player.getItemOnCursor() != null && !player.getItemOnCursor().getMaterial().equals(Material.AIR)) {
                        action = button == 0 ? InventoryAction.DROP_ALL_CURSOR : InventoryAction.DROP_ONE_CURSOR;
                    }
                } else if(slot < 0) {
                    action = InventoryAction.NOTHING;
                } else {
                    ItemStack clickedItem = getClickedItem(slot, player, inventory);//getItemStack();
                    if(clickedItem == null || clickedItem.getMaterial().equals(Material.AIR)) {
                        if(player.getItemOnCursor() != null && !player.getItemOnCursor().getMaterial().equals(Material.AIR)) {
                            action = button == 0 ? InventoryAction.PLACE_ALL : InventoryAction.PLACE_ONE;
                        }
                    } else {
                        if(player.getItemOnCursor() == null || player.getItemOnCursor().getMaterial().equals(Material.AIR)) {
                            action = button == 0 ? InventoryAction.PICKUP_ALL : InventoryAction.PICKUP_HALF;
                        } else if((player.getItemOnCursor().equals(clickedItem) && clickedItem.getAmount() >= 0
                                && clickedItem.getAmount() + player.getItemOnCursor().getAmount() <= player.getItemOnCursor().getMaterial().getMaxStackSize())) {
                            action = InventoryAction.PICKUP_ALL;
                        } else {//Slot allowed always true
                            if(clickedItem.equals(player.getItemOnCursor())) {
                                int toPlace = button == 0 ? player.getItemOnCursor().getAmount() : 1;
                                toPlace = Math.min(toPlace, clickedItem.getMaterial().getMaxStackSize() - clickedItem.getAmount());
                                //toPlace = Math.min(toPlace, slot.inventory.getMaxStackSize() - clickedItem.getCount());
                                if(toPlace == 1) {
                                    action = InventoryAction.PLACE_ONE;
                                } else if(toPlace == player.getItemOnCursor().getAmount()) {
                                    action = InventoryAction.PLACE_ALL;
                                } else if(toPlace < 0) {
                                    action = toPlace != -1 ? InventoryAction.PICKUP_SOME : InventoryAction.PICKUP_ONE;
                                } else if(toPlace != 0) {
                                    action= InventoryAction.PLACE_SOME;
                                }
                            } else if(player.getItemOnCursor().getAmount() <= clickedItem.getMaterial().getMaxStackSize()) {
                                action = InventoryAction.SWAP_WITH_CURSOR;
                            }
                        }
                    }
                }
                break;
            }
            case 1: {
                if(button == 0) {
                    clickType = ClickType.SHIFT_LEFT;
                } else if(button == 1) {
                    clickType = ClickType.SHIFT_RIGHT;
                } else {
                    break;
                }
                if(slot < 0) {
                    action = InventoryAction.NOTHING;
                } else {
                    ItemStack itemStack = getClickedItem(slot, player, inventory);
                    if(itemStack != null && !itemStack.getMaterial().equals(Material.AIR)) {
                        action = InventoryAction.MOVE_TO_OTHER_INVENTORY;
                    } else {
                        action = InventoryAction.NOTHING;
                    }
                }
                break;
            }
            case 2: {
                if(button < 0 || button > 8) {
                    break;
                }
                clickType = ClickType.NUMBER_KEY;
                ItemStack slotItem = getClickedItem(slot, player, inventory);//getItemStack()
                ItemStack clickedItem = player.getInventory().getItem(button);//maybe wrong
                boolean canCleanSwap = (clickedItem == null || clickedItem.getMaterial().equals(Material.AIR)) || inventory instanceof PlayerInventory/*Check for player inventory*/;
                if(slotItem != null && !slotItem.getMaterial().equals(Material.AIR)) {
                    if(canCleanSwap) {
                        action = InventoryAction.HOTBAR_SWAP;
                    } else {
                        action = InventoryAction.HOTBAR_MOVE_AND_READD;
                    }
                } else {
                    if((slotItem == null || slotItem.getMaterial().equals(Material.AIR)) && (clickedItem != null && !clickedItem.getMaterial().equals(Material.AIR))) {
                        action = InventoryAction.HOTBAR_SWAP;
                    } else {
                        action = InventoryAction.NOTHING;
                    }
                }
                break;
            }
            case 3: {//Stop
                if(button == 2) {
                    clickType = ClickType.MIDDLE;
                    if(slot < 0) {
                        action = InventoryAction.NOTHING;
                        break;
                    }
                    ItemStack clicked = getClickedItem(slot, player, inventory);//getItemStack();
                    if(clicked != null && !clicked.getMaterial().equals(Material.AIR) && player.getItemOnCursor() == null
                            && player.getItemOnCursor().getMaterial().equals(Material.AIR) && player.getGameMode() == GameMode.CREATIVE/*Instant build*/) {
                        action = InventoryAction.CLONE_STACK;
                    } else {
                        action = InventoryAction.NOTHING;
                    }
                }
                break;
            }
            case 4: {//THROW
                if(slot >= 0) {
                    if(button == 0) {
                        clickType = ClickType.DROP;
                        ItemStack clicked = getClickedItem(slot, player, inventory);//getItemStack()
                        if(clicked != null && !clicked.getMaterial().equals(Material.AIR)) {
                            action = InventoryAction.DROP_ONE_SLOT;
                        } else {
                            action = InventoryAction.NOTHING;
                        }
                        break;
                    } else if(button == 1) {
                        clickType = ClickType.CONTROL_DROP;
                        ItemStack clicked = getClickedItem(slot, player, inventory);//getItemStack()
                        if(clicked != null && !clicked.getMaterial().equals(Material.AIR)) {
                            action = InventoryAction.DROP_ONE_SLOT;
                        } else {
                            action = InventoryAction.NOTHING;
                        }
                        break;
                    }
                } else  {
                    if(button == 1) {
                        clickType = ClickType.RIGHT;
                    } else {
                        clickType = ClickType.LEFT;
                    }
                    action = InventoryAction.NOTHING;
                }
                break;
            }
            case 5: {//QUICK_CRAFT
                //@Todo definde action and click type dragging for transaction
                break;
            }
            case 6: {//PICKUP_ALL
                clickType = ClickType.DOUBLE_CLICK;
                action = InventoryAction.NOTHING;
                if(slot >= 0 && player.getItemOnCursor() != null && !player.getItemOnCursor().getMaterial().equals(Material.AIR)) {
                    if(inventory.contains(player.getItemOnCursor()) || player.getInventory().contains(player.getItemOnCursor())) {
                        action = InventoryAction.COLLECT_TO_CURSOR;
                    }
                }
                break;
            }
        }
        this.inventoryAction = action;
        this.clickType = clickType;
    }

    private ItemStack getClickedItem(short rawSlot, Player player, Inventory inventory) {
        int maxSize = inventory.getSize();
        if(rawSlot < maxSize) return inventory.getItem(rawSlot);
        int slot = rawSlot - maxSize;

        //Copied from bukkit
        // 27 = 36 - 9
        if (slot >= 27) {
            // Put into hotbar section
            slot -= 27;
        } else {
            // Take out of hotbar section
            // 9 = 36 - 27
            slot += 9;
        }
        return player.getInventory().getItem(slot);
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        this.windowId = (byte) buffer.readUnsignedByte();
        this.slot = buffer.readShort();
        this.button = buffer.readByte();
        this.actionNumber = buffer.readShort();
        if(version.isOlderOrSame(MinecraftProtocolVersion.JE_1_8)) {
            this.mode = buffer.readByte();
        } else {
            this.mode = MinecraftProtocolUtil.readVarInt(buffer);
        }

        this.itemStack = ServiceMinecraftProtocolUtil.readSlot(buffer, version);
    }

    @Override
    public void write(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }
}
