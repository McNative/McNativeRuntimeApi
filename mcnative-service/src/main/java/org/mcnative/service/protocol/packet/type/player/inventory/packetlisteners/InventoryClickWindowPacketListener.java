/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 10.10.20, 16:09
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

package org.mcnative.service.protocol.packet.type.player.inventory.packetlisteners;

import net.pretronic.libraries.utility.Iterators;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.protocol.packet.MinecraftPacketEvent;
import org.mcnative.common.protocol.packet.MinecraftPacketListener;
import org.mcnative.service.entity.living.Player;
import org.mcnative.service.event.player.inventory.InventoryRegistry;
import org.mcnative.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.service.event.player.inventory.defaults.DefaultMinecraftPlayerInventoryClickEvent;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.inventory.type.implementation.DefaultAnvilInventory;
import org.mcnative.service.protocol.packet.type.player.inventory.InventoryClickWindowPacket;
import org.mcnative.service.protocol.packet.type.player.inventory.InventorySetSlotPacket;
import org.mcnative.service.protocol.packet.type.player.inventory.InventoryWindowItemsPacket;

import java.util.Arrays;

public class InventoryClickWindowPacketListener implements MinecraftPacketListener {

    @Override
    public void handle(MinecraftPacketEvent event) {
        if(event.getConnection() instanceof PendingConnection) {
            PendingConnection connection = (PendingConnection) event.getConnection();
            if(connection.isPlayerAvailable()) {
                if(connection.getPlayer() instanceof Player) {
                    Player player = (Player) connection.getPlayer();
                    InventoryClickWindowPacket packet = event.getPacket(InventoryClickWindowPacket.class);

                    Inventory inventory = Iterators.findOne(InventoryRegistry.getInventories(), search ->
                            search instanceof DefaultAnvilInventory && ((DefaultAnvilInventory) search).matchWith(player, packet.getWindowId()));
                    if(inventory == null) return;
                    packet.setInventoryActionAndClickType(inventory, player);
                    if(packet.getMode() != 5) {
                        MinecraftPlayerInventoryClickEvent inventoryClickEvent = new DefaultMinecraftPlayerInventoryClickEvent(packet.getInventoryAction(),
                                packet.getClickType(), packet.getItemStack(), 0, 0, packet.getSlot(), null, false,
                                null, player);

                        //Crafting inventory handle

                        //Call event
                        McNative.getInstance().getLocal().getEventBus().callEvent(MinecraftPlayerInventoryClickEvent.class, inventoryClickEvent);
                        if(inventoryClickEvent.isCancelled()) {
                            switch (packet.getInventoryAction()) {
                                case PICKUP_ALL:
                                case MOVE_TO_OTHER_INVENTORY:
                                case HOTBAR_MOVE_AND_READD:
                                case HOTBAR_SWAP:
                                case COLLECT_TO_CURSOR:
                                case UNKNOWN: {
                                    //Update inventory
                                    ItemStack[] items = new ItemStack[41];
                                    int count = 0;
                                    for (ItemStack item : inventory.getItems()) {
                                        items[count] = item;
                                        count++;
                                    }
                                    if(count <= 2) {
                                        for (int i = count; i <= 2; i++) {
                                            items[0] = ItemStack.newItemStack(Material.AIR);
                                            count++;
                                        }
                                    }

                                    player.sendPacket(new InventoryWindowItemsPacket(packet.getWindowId(), Arrays.asList(items)));
                                    player.sendPacket(new InventorySetSlotPacket((byte) -1, (short) -1, player.getItemOnCursor()));
                                    break;
                                }
                                case PICKUP_SOME:
                                case PICKUP_HALF:
                                case PICKUP_ONE:
                                case PLACE_ALL:
                                case PLACE_SOME:
                                case PLACE_ONE:
                                case SWAP_WITH_CURSOR: {
                                    player.sendPacket(new InventorySetSlotPacket((byte)-1, (short)-1, player.getItemOnCursor()));

                                    player.sendPacket(new InventorySetSlotPacket(packet.getWindowId(),
                                            packet.getSlot(), packet.getItemStack()));
                                    break;
                                }
                                case DROP_ALL_CURSOR:
                                case DROP_ONE_CURSOR:
                                case CLONE_STACK: {
                                    player.sendPacket(new InventorySetSlotPacket((byte)-1, (short)-1, player.getItemOnCursor()));
                                    break;
                                }
                                case DROP_ONE_SLOT:
                                case DROP_ALL_SLOT: {
                                    player.sendPacket(new InventorySetSlotPacket(packet.getWindowId(),
                                            packet.getSlot(), packet.getItemStack()));
                                    break;
                                }
                            }
                        } else {
                            //@Todo Change item stack
                            //itemstack = this.player.activeContainer.a(packetplayinwindowclick.c(), packetplayinwindowclick.d(), packetplayinwindowclick.g(), this.player);
                        }
                    }

                    //@Todo transaction check


                }
            }
        }
    }

    private void convertSlot(int rawSlot, int maxSize) {
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
    }
}
