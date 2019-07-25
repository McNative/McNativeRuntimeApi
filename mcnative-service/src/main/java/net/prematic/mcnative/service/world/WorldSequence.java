/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 23.07.19 11:48
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

package net.prematic.mcnative.service.world;

import net.prematic.mcnative.service.entity.Entity;
import net.prematic.mcnative.service.entity.Player;
import net.prematic.mcnative.service.entity.animal.Animal;
import net.prematic.mcnative.service.entity.monster.Monster;
import net.prematic.mcnative.service.inventory.item.DroppedItem;
import net.prematic.mcnative.service.inventory.item.ItemStack;
import net.prematic.mcnative.service.world.block.Block;

import java.util.Collection;

public interface WorldSequence {

    Block getBlock(int x, int y, int z);

    Block getBlock(Location location);

    Block getHighestBlock(int x, int y);

    Block getHighestBlock(Location location);

    Block getLoewstBlock(int x, int y);

    Block getLoewstBlock(Location location);

    Collection<Entity> getEntities();

    Collection<Entity> getLivingEntities();

    Collection<Animal> getAnimals();

    Collection<Monster> getMonsters();

    Collection<Player> getPlayers();

    <E extends Entity>Collection<E> getEntities(Class<E> entityClass);


    DroppedItem dropItem(Location location, ItemStack item);

    DroppedItem dropItemNaturally(Location location, ItemStack item);



}
