/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 23.10.19, 18:27
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

package org.mcnative.bukkit.world;

import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.service.Effect;
import org.mcnative.service.entity.Entity;
import org.mcnative.service.entity.living.animal.Animal;
import org.mcnative.service.entity.living.monster.Monster;
import org.mcnative.service.entity.living.player.Player;
import org.mcnative.service.entity.projectile.arrow.Arrow;
import org.mcnative.service.inventory.item.DroppedItem;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.location.Location;
import org.mcnative.service.location.Offset;
import org.mcnative.service.location.Vector;
import org.mcnative.service.world.Biome;
import org.mcnative.service.world.Chunk;
import org.mcnative.service.world.TreeType;
import org.mcnative.service.world.World;
import org.mcnative.service.world.block.Block;
import org.mcnative.service.world.block.data.BlockData;
import org.mcnative.service.world.particle.Particle;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class BukkitChunk implements Chunk {

    private final org.bukkit.Chunk original;
    private final BukkitWorld world;

    public BukkitChunk(org.bukkit.Chunk original, BukkitWorld world) {
        this.original = original;
        this.world = world;
    }

    public org.bukkit.Chunk getOriginal() {
        return original;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public Biome getBiome() {
        return null;
    }

    @Override
    public void setBiome(Biome biome) {

    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public boolean isInUse() {
        return false;
    }

    @Override
    public boolean isGenerated() {
        return false;
    }

    @Override
    public void load() {
        this.original.load(true);
    }

    @Override
    public void unload() {
        this.original.unload(true);
    }

    @Override
    public void tryUnload() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void regenerate() {

    }

    @Override
    public boolean isForceLoaded() {
        return this.original.isForceLoaded();
    }

    @Override
    public void setForceLoaded(boolean forceLoaded) {
        this.original.setForceLoaded(forceLoaded);
    }

    @Override
    public void fill(Material material) {

    }

    @Override
    public void fill(int z, Material material) {

    }

    @Override
    public void fill(int startZ, int endZ, Material material) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<Block> iterator() {
        return null;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return null;
    }

    @Override
    public Block getBlock(Vector location) {
        return null;
    }

    @Override
    public Block getHighestBlock(int x, int y) {
        return null;
    }

    @Override
    public Block getHighestBlock(Vector location) {
        return null;
    }

    @Override
    public Block getLowestBlock(int x, int y) {
        return null;
    }

    @Override
    public Block getLowestBlock(Vector location) {
        return null;
    }

    @Override
    public Biome getBiome(int x, int y) {
        return null;
    }

    @Override
    public Biome getBiome(Vector point) {
        return null;
    }

    @Override
    public void setBiome(int x, int y, Biome biome) {

    }

    @Override
    public void setBiome(Vector point, Biome biome) {

    }

    @Override
    public double getTemperature(int x, int z) {

        return 0;
    }

    @Override
    public double getHumidity(int x, int z) {
        return 0;
    }

    @Override
    public Collection<Entity> getEntities() {
        return null;
    }

    @Override
    public Collection<Entity> getLivingEntities() {
        return null;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return null;
    }

    @Override
    public Collection<Monster> getMonsters() {
        return null;
    }

    @Override
    public Collection<Player> getPlayers() {
        return null;
    }

    @Override
    public Collection<Entity> getEntitiesNear(Vector point, Offset offset) {
        return null;
    }

    @Override
    public Collection<Entity> getEntitiesNear(Vector point, Offset offset, Predicate<Entity> filter) {
        return null;
    }

    @Override
    public <E extends Entity> Collection<E> getEntitiesNear(Class<E> entityType, Vector point, Offset offset) {
        return null;
    }

    @Override
    public <E extends Entity> Collection<E> getEntitiesNear(Class<E> entityType, Vector point, Offset offset, Predicate<Entity> filter) {
        return null;
    }

    @Override
    public <E extends Entity> Collection<E> getEntities(Class<E> entityClass) {
        return null;
    }

    @Override
    public <E extends Entity> E spawnEntity(Location location, Class<?> clazz) {
        return null;
    }

    @Override
    public <E extends Entity> E spawnNoAIEntity(Location location, Class<?> clazz) {
        return null;
    }

    @Override
    public <A extends Arrow> A spawnArrow(Vector point, Vector direction, float speed, float spread, Class<A> arrowClass) {
        return null;
    }

    @Override
    public Arrow spawnArrow(Vector point, Vector direction) {
        return null;
    }

    @Override
    public void spawnParticle(Vector point, Particle particle, int amount, Iterable<OnlineMinecraftPlayer> receivers) {

    }

    @Override
    public void spawnParticle(Vector point, Particle particle, int amount, Offset offset, Iterable<? extends OnlineMinecraftPlayer> receivers) {

    }

    @Override
    public void spawnParticle(Vector point, Particle particle, int amount) {

    }

    @Override
    public void spawnParticle(Vector point, Particle particle, int amount, Offset offset) {

    }

    @Override
    public DroppedItem dropItem(Vector location, ItemStack item) {
        return null;
    }

    @Override
    public DroppedItem dropItemNaturally(Vector location, ItemStack item) {
        return null;
    }

    @Override
    public boolean createExplosion(Vector point, float power) {
        return false;
    }

    @Override
    public boolean createExplosion(Vector point, float power, boolean fire) {
        return false;
    }

    @Override
    public boolean createExplosion(Vector point, float power, boolean fire, boolean destroyBlocks) {
        return false;
    }

    @Override
    public boolean createExplosion(Vector point, Offset offset, float power) {
        return false;
    }

    @Override
    public boolean createExplosion(Vector point, Offset offset, float power, boolean fire) {
        return false;
    }

    @Override
    public boolean createExplosion(Vector point, Offset offset, float power, boolean fire, boolean destroyBlocks) {
        return false;
    }

    @Override
    public void strikeLightning(Vector location, boolean damage) {

    }

    @Override
    public void createFallingBlock(Vector point, Material material) {

    }

    @Override
    public void createFallingBlock(Vector point, BlockData data) {

    }

    @Override
    public void playEffect(Location location, Effect effect) {

    }

    @Override
    public void playEffect(Location location, Effect effect, Offset offset) {

    }

    @Override
    public void playSound(Vector point, Sound sound, float volume, float pitch) {

    }

    @Override
    public void playSound(Vector point, Sound sound, SoundCategory category, float volume, float pitch) {

    }

    @Override
    public boolean generateTree(Vector location, TreeType treeType) {
        return false;
    }
}
