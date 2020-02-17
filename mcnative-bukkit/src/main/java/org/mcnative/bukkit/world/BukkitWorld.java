/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 18.10.19, 00:40
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

import org.mcnative.bukkit.location.BukkitLocation;
import org.mcnative.bukkit.world.block.BukkitBlock;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.service.Effect;
import org.mcnative.service.entity.Entity;
import org.mcnative.service.entity.living.Player;
import org.mcnative.service.entity.living.animal.Animal;
import org.mcnative.service.entity.living.monster.Monster;
import org.mcnative.service.entity.projectile.arrow.Arrow;
import org.mcnative.service.inventory.item.DroppedItem;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.location.Location;
import org.mcnative.service.location.Offset;
import org.mcnative.service.location.Vector;
import org.mcnative.service.world.*;
import org.mcnative.service.world.block.Block;
import org.mcnative.service.world.block.data.BlockData;
import org.mcnative.service.world.particle.Particle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

public class BukkitWorld implements World {

    private final org.bukkit.World original;

    public BukkitWorld(org.bukkit.World original) {
        this.original = original;
    }

    public org.bukkit.World getOriginal() {
        return original;
    }

    @Override
    public String getName() {
        return this.original.getName();
    }

    @Override
    public UUID getUniqueId() {
        return this.original.getUID();
    }

    @Override
    public long getSeed() {
        return this.original.getSeed();
    }

    @Override
    public WorldEnvironment getEnvironment() {
        switch (this.original.getEnvironment()) {
            case NORMAL: return WorldEnvironment.EARTH;
            case NETHER: return WorldEnvironment.NETHER;
            case THE_END: return WorldEnvironment.End;
        }
        throw new UnsupportedOperationException(String.format("WorldEnvironment %s is unsupported.", this.original.getEnvironment().name()));
    }

    @Override
    public WorldBorder getBorder() {
        return new BukkitWorldBorder(this.original.getWorldBorder(), this);
    }

    @Override
    public Location getSpawnLocation() {
        return new BukkitLocation(this.original.getSpawnLocation(), this);
    }

    @Override
    public void setSpawnLocation(Location spawnLocation) {
        this.original.setSpawnLocation(((BukkitLocation)spawnLocation).getOriginal());
    }

    @Override
    public boolean isLoaded() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void unload() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Chunk getChunk(int x, int z) {//@Todo chunks from pool ?
        return new BukkitChunk(this.original.getChunkAt(x, z), this);
    }

    @Override
    public Chunk getChunk(Vector location) {
        return getChunk((int) location.getX(), (int) location.getZ());
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        Collection<Chunk> loadedChunks = new ArrayList<>();
        for (org.bukkit.Chunk loadedChunk : this.original.getLoadedChunks()) {
            loadedChunks.add(new BukkitChunk(loadedChunk, this));
        }
        return loadedChunks;
    }

    @Override
    public Collection<Chunk> getForceLoadedChunks() {
        Collection<Chunk> loadedChunks = new ArrayList<>();
        for (org.bukkit.Chunk loadedChunk : this.original.getForceLoadedChunks()) {
            loadedChunks.add(new BukkitChunk(loadedChunk, this));
        }
        return loadedChunks;
    }

    @Override
    public Chunk loadChunk(int x, int z) {
        return loadChunk(x, z, true);
    }

    @Override
    public Chunk loadChunk(int x, int z, boolean generate) {
        this.original.loadChunk(x, z, generate);
        return new BukkitChunk(this.original.getChunkAt(x, z), this);
    }

    @Override
    public Chunk loadChunk(Vector location) {
        return loadChunk(location, true);
    }

    @Override
    public Chunk loadChunk(Vector location, boolean generate) {
        return loadChunk((int) location.getX(), (int) location.getZ(), generate);
    }

    @Override
    public Chunk loadChunk(Chunk chunk) {
        this.original.loadChunk(((BukkitChunk)chunk).getOriginal());
        return chunk;
    }

    @Override
    public long getTime() {
        return this.original.getTime();
    }

    @Override
    public void setTime(long time) {
        this.original.setTime(time);
    }

    @Override
    public long getFullTime() {
        return this.original.getFullTime();
    }

    @Override
    public void setFullTime(long time) {
        this.original.setFullTime(time);
    }

    @Override
    public boolean hasStorm() {
        return this.original.hasStorm();
    }

    @Override
    public void setStorm(boolean storm) {
        this.original.setStorm(storm);
    }

    @Override
    public boolean isThundering() {
        return this.original.isThundering();
    }

    @Override
    public void setThundering(boolean thundering) {
        this.original.setThundering(thundering);
    }

    @Override
    public int getThunderDuration() {
        return this.original.getThunderDuration();
    }

    @Override
    public void setThunderDuration(int duration) {
        this.original.setThunderDuration(duration);
    }

    @Override
    public int getWeatherDuration() {
        return this.original.getWeatherDuration();
    }

    @Override
    public void setWeatherDuration(int duration) {
        this.original.setWeatherDuration(duration);
    }

    @Override
    public boolean isAutoSave() {
        return this.original.isAutoSave();
    }

    @Override
    public void setAutoSave(boolean autoSave) {
        this.original.setAutoSave(autoSave);
    }

    @Override
    public void save() {
        this.original.save();
    }

    @Override
    public File getFolder() {
        return this.original.getWorldFolder();
    }

    @Override
    public boolean isDefault() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return new BukkitBlock(this.original.getBlockAt(x, y, z), this);
    }

    @Override
    public Block getBlock(Vector location) {
        return getBlock((int) location.getX(), (int) location.getY(), (int) location.getZ());
    }

    @Override
    public Block getHighestBlock(int x, int z) {
        return new BukkitBlock(this.original.getHighestBlockAt(x, z), this);
    }

    @Override
    public Block getHighestBlock(Vector location) {
        return getHighestBlock((int) location.getX(), (int) location.getZ());
    }

    @Override
    public Block getLowestBlock(int x, int z) {
        for (int y = 0; y < this.original.getMaxHeight(); y++) {
            org.bukkit.block.Block block = this.original.getBlockAt(x, y, z);
            if(!block.isEmpty()) return new BukkitBlock(block, this);
        }
        return null;
    }

    @Override
    public Block getLowestBlock(Vector location) {
        return getLowestBlock((int) location.getX(), (int) location.getZ());
    }

    @Override
    public Biome getBiome(int x, int y) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Biome getBiome(Vector point) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setBiome(int x, int z, Biome biome) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setBiome(Vector point, Biome biome) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public double getTemperature(int x, int z) {
        return this.original.getTemperature(x, z);
    }

    @Override
    public double getHumidity(int x, int z) {
        return this.original.getHumidity(x, z);
    }

    @Override
    public Collection<Entity> getEntities() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Collection<Entity> getLivingEntities() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Collection<Animal> getAnimals() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Collection<Monster> getMonsters() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Collection<Player> getPlayers() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Collection<Entity> getEntitiesNear(Vector point, Offset offset) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Collection<Entity> getEntitiesNear(Vector point, Offset offset, Predicate<Entity> filter) {
        throw new UnsupportedOperationException("Currently not supported");
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
