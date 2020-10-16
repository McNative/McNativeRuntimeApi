/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 10.10.20, 16:10
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
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.protocol.packet.MinecraftPacketEvent;
import org.mcnative.common.protocol.packet.MinecraftPacketListener;
import org.mcnative.service.entity.living.Player;
import org.mcnative.service.event.player.inventory.InventoryRegistry;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.type.implementation.DefaultAnvilInventory;
import org.mcnative.service.protocol.packet.type.player.inventory.InventoryCloseWindowPacket;

public class InventoryCloseWindowPacketListener implements MinecraftPacketListener {

    @Override
    public void handle(MinecraftPacketEvent event) {
        if(event.getConnection() instanceof PendingConnection) {
            PendingConnection connection = (PendingConnection) event.getConnection();
            if(connection.isPlayerAvailable()) {
                if(connection.getPlayer() instanceof Player) {
                    Player player = (Player) connection.getPlayer();
                    InventoryCloseWindowPacket packet = event.getPacket(InventoryCloseWindowPacket.class);
                    Inventory inventory = Iterators.findOne(InventoryRegistry.getInventories(), search ->
                            search instanceof DefaultAnvilInventory && ((DefaultAnvilInventory) search).matchWith(player, packet.getWindowId()));
                    if(inventory == null) return;
                    DefaultAnvilInventory anvilInventory = ((DefaultAnvilInventory) inventory);
                    anvilInventory.removeViewer(player);
                }
            }
        }

    }
}
