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

package org.mcnative.service.world;

import org.mcnative.service.Effect;
import org.mcnative.service.entity.Entity;
import org.mcnative.service.entity.player.Player;
import org.mcnative.service.entity.animal.Animal;
import org.mcnative.service.entity.monster.Monster;
import org.mcnative.service.inventory.item.DroppedItem;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.material.BlockData;
import org.mcnative.service.material.Material;
import org.mcnative.service.material.MaterialData;
import org.mcnative.service.world.block.Block;

import java.util.Collection;

public interface WorldSequence {

    Block getBlock(int x, int y, int z);

    Block getBlock(Point location);

    Block getHighestBlock(int x, int y);

    Block getHighestBlock(Point location);

    Block getLowestBlock(int x, int y);

    Block getLowestBlock(Point location);

    Collection<Entity> getEntities();

    Collection<Entity> getLivingEntities();

    Collection<Animal> getAnimals();

    Collection<Monster> getMonsters();

    Collection<Player> getPlayers();

    <E extends Entity>Collection<E> getEntities(Class<E> entityClass);


    DroppedItem dropItem(Point location, ItemStack item);

    DroppedItem dropItemNaturally(Point location, ItemStack item);


    boolean createExplosion(Point point, float power);//Explosion builder?

    boolean createExplosion(Point point, float power, boolean fire);

    boolean createExplosion(Point point, float power, boolean fire, boolean destroyBlocks);


    <E extends Entity> E spawnEntity(Location location,Class<?> clazz);//entity builder ?

    <E extends Entity> E spawnFreezedEntity(Location location,Class<?> clazz);

    //return FallingBlock
    void createFallingBlock(Point point, Material material);

    void createFallingBlock(Point point, BlockData data);


    void playEffect(Location location, Effect effect);

    void playEffect(Location location, Effect effect, int radius);
}
