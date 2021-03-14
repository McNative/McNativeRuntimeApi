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

package org.mcnative.runtime.api.service.world;

import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.player.sound.SoundCategory;
import org.mcnative.runtime.api.service.entity.Entity;
import org.mcnative.runtime.api.service.entity.living.Player;
import org.mcnative.runtime.api.service.entity.living.animal.Animal;
import org.mcnative.runtime.api.service.entity.living.monster.Monster;
import org.mcnative.runtime.api.service.entity.projectile.arrow.Arrow;
import org.mcnative.runtime.api.service.inventory.item.DroppedItem;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;
import org.mcnative.runtime.api.service.inventory.item.material.Material;
import org.mcnative.runtime.api.service.world.location.Location;
import org.mcnative.runtime.api.utils.positioning.Vector;
import org.mcnative.runtime.api.service.world.block.Block;
import org.mcnative.runtime.api.service.world.block.data.BlockData;
import org.mcnative.runtime.api.service.world.particle.Particle;

import java.util.Collection;
import java.util.function.Predicate;

public interface WorldSequence {

    Block getBlock(int x, int y, int z);

    Block getBlock(Vector location);

    Block getHighestBlock(int x, int z);

    Block getHighestBlock(Vector location);

    Block getLowestBlock(int x, int z);

    Block getLowestBlock(Vector location);


    Biome getBiome(int x, int z);

    Biome getBiome(Vector point);

    void setBiome(int x, int z, Biome biome);

    void setBiome(Vector point, Biome biome);


    double getTemperature(int x, int z);

    double getHumidity(int x, int z);


    Collection<Entity> getEntities();

    Collection<Entity> getLivingEntities();

    Collection<Animal> getAnimals();

    Collection<Monster> getMonsters();

    Collection<Player> getPlayers();


    Collection<Entity> getEntitiesNear(Vector point, Vector offset);

    Collection<Entity> getEntitiesNear(Vector point, Vector offset, Predicate<Entity> filter);

    <E extends Entity> Collection<E> getEntitiesNear(Class<E> entityType, Vector point, Vector offset);

    <E extends Entity> Collection<E> getEntitiesNear(Class<E> entityType, Vector point, Vector offset, Predicate<Entity> filter);


    <E extends Entity>Collection<E> getEntities(Class<E> entityClass);


    <E extends Entity> E spawnEntity(Location location, Class<?> clazz);//entity builder ?

    <E extends Entity> E spawnNoAIEntity(Location location,Class<?> clazz);


    <A extends Arrow> A spawnArrow(Vector point, Vector direction, float speed, float spread, Class<A> arrowClass);

    Arrow spawnArrow(Vector point, Vector direction);


    void spawnParticle(Vector point, Particle particle, int amount, Iterable<OnlineMinecraftPlayer> receivers);

    void spawnParticle(Vector point, Particle particle, int amount, Vector offset, Iterable<? extends OnlineMinecraftPlayer> receivers);


    void spawnParticle(Vector point, Particle particle, int amount);

    void spawnParticle(Vector point, Particle particle, int amount, Vector offset);



    DroppedItem dropItem(Vector location, ItemStack item);

    DroppedItem dropItemNaturally(Vector location, ItemStack item);


    boolean createExplosion(Vector point, float power);

    boolean createExplosion(Vector point, float power, boolean fire);

    boolean createExplosion(Vector point, float power, boolean fire, boolean destroyBlocks);

    boolean createExplosion(Vector point, Vector offset, float power);

    boolean createExplosion(Vector point, Vector offset, float power, boolean fire);

    boolean createExplosion(Vector point, Vector offset, float power, boolean fire, boolean destroyBlocks);


    //Return lightning
    default void strikeLightning(Vector location){
        strikeLightning(location,true);
    }

    void strikeLightning(Vector location, boolean damage);


    //return FallingBlock
    void createFallingBlock(Vector point, Material material);

    void createFallingBlock(Vector point, BlockData data);


    void playEffect(Location location, Effect effect);

    void playEffect(Location location, Effect effect, Vector offset);

    //@Todo add note

    void playSound(Vector point, String sound, float volume, float pitch);

    void playSound(Vector point, String sound, SoundCategory category, float volume, float pitch);


    boolean generateTree(Vector location, TreeType treeType);


    //RayTraceResult rayTraceEntities(Point start, Vector direction, double maxDistance);

    //RayTraceResult rayTraceEntities(Point start, Vector direction, double maxDistance, double raySize);

    //RayTraceResult rayTraceEntities(Point start, Vector direction, double maxDistance,Predicate<Entity> filter);

    //RayTraceResult rayTraceEntities(Point start, Vector direction, double maxDistance, double raySize, Predicate<Entity> filter);

    //RayTraceResult rayTraceBlocks(Point start, Vector direction, double maxDistance);

    //RayTraceResult rayTraceBlocks(Point start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode);

    //RayTraceResult rayTraceBlocks(Point start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks);

    //RayTraceResult rayTrace(Point start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks, double raySize, Predicate<Entity> filter);
}
