/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 27.10.19, 20:21
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

package org.mcnative.bukkit.world.block;

import org.mcnative.bukkit.world.BukkitChunk;
import org.mcnative.bukkit.location.BukkitLocation;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.location.Location;
import org.mcnative.service.location.Offset;
import org.mcnative.service.world.Biome;
import org.mcnative.service.world.Chunk;
import org.mcnative.service.world.World;
import org.mcnative.service.world.block.Block;
import org.mcnative.service.world.block.BlockDirection;
import org.mcnative.service.world.block.data.BlockData;

import java.util.Collection;

public class BukkitBlock implements Block {

    private final org.bukkit.block.Block original;
    private final BukkitWorld world;

    public BukkitBlock(org.bukkit.block.Block original, BukkitWorld world) {
        this.original = original;
        this.world = world;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public Location getLocation() {
        return new BukkitLocation(this.original.getLocation(), this.world);
    }

    @Override
    public Material getMaterial() {
        return null;
    }

    @Override
    public void setMaterial(Material material) {

    }

    @Override
    public boolean isEmpty() {
        return this.original.isEmpty();
    }

    @Override
    public boolean isLiquid() {
        return this.original.isLiquid();
    }

    @Override
    public boolean isPassable() {
        return this.original.isPassable();
    }

    @Override
    public BlockData getData() {
        return null;
    }

    @Override
    public void setData(BlockData data) {

    }

    @Override
    public Chunk getChunk() {
        return new BukkitChunk(this.original.getChunk(), this.world);
    }

    @Override
    public Biome getBiome() {
        return null;
    }

    @Override
    public void setBiome(Biome biome) {

    }

    @Override
    public double getTemperature() {
        return this.original.getTemperature();
    }

    @Override
    public double getHumidity() {
        return this.original.getHumidity();
    }

    @Override
    public byte getLightLevel() {
        return this.original.getLightLevel();
    }

    @Override
    public byte getLightFromSky() {
        return this.original.getLightFromSky();
    }

    @Override
    public byte getLightFromBlocks() {
        return this.original.getLightFromBlocks();
    }

    @Override
    public Block getRelativeBlock(Offset offset) {
        return null;
    }

    @Override
    public Block getRelativeBlock(BlockDirection direction) {
        return null;
    }

    @Override
    public boolean isBlockPowered() {
        return this.original.isBlockPowered();
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return this.original.isBlockIndirectlyPowered();
    }

    @Override
    public boolean isBlockDirectionPowered(BlockDirection direction) {
        return false;
    }

    @Override
    public boolean isBlockIndirectlyPowered(BlockDirection direction) {
        return false;
    }

    @Override
    public int getPower() {
        return this.original.getBlockPower();
    }

    @Override
    public int getPower(BlockDirection direction) {
        return 0;
    }

    @Override
    public void breakNaturally() {
        this.original.breakNaturally();
    }

    @Override
    public void breakNaturally(ItemStack tool) {

    }

    @Override
    public Collection<ItemStack> getDrops() {
        return null;
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack tool) {
        return null;
    }

    @Override
    public boolean isAllowDrops() {
        return false;
    }

    @Override
    public void setAllowDrop(boolean allowDrop) {

    }

    @Override
    public void update() {

    }

    @Override
    public void update(OnlineMinecraftPlayer player) {

    }
}
