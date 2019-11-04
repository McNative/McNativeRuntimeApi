/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 27.10.19, 20:25
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

package org.mcnative.bukkit.entity;

import org.mcnative.bukkit.location.BukkitLocation;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.service.entity.Entity;
import org.mcnative.service.location.Location;
import org.mcnative.service.world.World;

import java.util.List;
import java.util.UUID;

public class BukkitEntity<E extends org.bukkit.entity.Entity> implements Entity {

    protected final E original;
    protected final BukkitWorld world;

    public BukkitEntity(E original, BukkitWorld world) {
        this.original = original;
        this.world = world;
    }

    @Override
    public int getId() {
        return this.original.getEntityId();
    }

    @Override
    public UUID getUniqueId() {
        return this.original.getUniqueId();
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public Location getLocation() {
        return new BukkitLocation(this.original.getLocation(), world);
    }

    @Override
    public boolean isOnGround() {
        return this.original.isOnGround();
    }

    @Override
    public List<Entity> getNeighbors(double radius) {
        return null;
    }

    @Override
    public List<Entity> getNeighbors(double x, double y, double z) {
        return null;
    }

    @Override
    public void teleport(Location location) {
        this.original.teleport(((BukkitLocation)location).getOriginal());
    }

    @Override
    public void teleport(int x, int y, int z) {
        this.original.teleport(new org.bukkit.Location(this.world.getOriginal(), x, y, z));
    }

    @Override
    public void teleport(int x, int y, int z, short pitch, short yaw) {
        this.original.teleport(new org.bukkit.Location(this.world.getOriginal(), x, y, z, pitch, yaw));
    }

    @Override
    public void teleport(World world, int x, int y, int z) {
        this.original.teleport(new org.bukkit.Location(this.world.getOriginal(), x, y, z));
    }

    @Override
    public void teleport(World world, int x, int y, int z, short pitch, short yaw) {
        this.original.teleport(new org.bukkit.Location(((BukkitWorld)world).getOriginal(), x, y, z, pitch, yaw));
    }

    //@Todo
    @Override
    public void despawn() {

    }
}
