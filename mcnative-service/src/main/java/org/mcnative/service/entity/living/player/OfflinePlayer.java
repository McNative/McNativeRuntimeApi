/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:51
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

package org.mcnative.service.entity.living.player;

import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.service.entity.living.AnimalTamer;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.location.Location;

public interface OfflinePlayer extends MinecraftPlayer, AnimalTamer {

    Inventory getEnderchestInventory();

    Location getBedSpawnLocation();

    void setBedSpawnLocation(Location location);

}
