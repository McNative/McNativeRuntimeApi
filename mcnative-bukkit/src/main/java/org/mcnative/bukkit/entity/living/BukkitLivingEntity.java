/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 27.10.19, 20:32
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

package org.mcnative.bukkit.entity.living;

import net.prematic.libraries.utility.Iterators;
import org.bukkit.attribute.Attribute;
import org.mcnative.bukkit.entity.BukkitEntity;
import org.mcnative.bukkit.location.BukkitLocation;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.bukkit.world.block.BukkitBlock;
import org.mcnative.service.entity.Entity;
import org.mcnative.service.entity.living.LivingEntity;
import org.mcnative.service.entity.projectile.Projectile;
import org.mcnative.service.inventory.item.material.Material;
import org.mcnative.service.inventory.item.potion.PotionEffect;
import org.mcnative.service.inventory.item.potion.PotionEffectType;
import org.mcnative.service.location.Location;
import org.mcnative.service.location.Vector;
import org.mcnative.service.world.block.Block;

import java.util.*;
import java.util.function.Function;

public class BukkitLivingEntity<E extends org.bukkit.entity.LivingEntity> extends BukkitEntity<E> implements LivingEntity {

    public BukkitLivingEntity(E original, BukkitWorld world) {
        super(original, world);
    }
    
    @Override
    public double getEyeHeight() {
        return this.original.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return this.original.getEyeHeight(ignorePose);
    }

    @Override
    public Location getEyeLocation() {
        return new BukkitLocation(this.original.getEyeLocation(), this.world);
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        Set<org.bukkit.Material> materials = new HashSet<>();
        Iterators.map(transparent, materials, material -> null);
        List<Block> blocks = Iterators.map(
                this.original.getLineOfSight(materials, maxDistance),
                block -> new BukkitBlock(block, (BukkitWorld) getWorld()));
        return null;
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        return false;
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        return null;
    }

    @Override
    public Block getTargetBlock(int maxDistance) {
        return null;
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return null;
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        return null;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        return false;
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {

    }

    @Override
    public void addPotionEffect(PotionEffect effect, boolean force) {

    }

    @Override
    public void addPotionEffects(PotionEffect... potionEffects) {

    }

    @Override
    public void removePotionEffect(PotionEffectType type) {

    }

    @Override
    public int getHandRaisedTime() {
        return 0;
    }

    @Override
    public boolean isHandRaised() {
        return false;
    }

    @Override
    public double getHealth() {
        return this.original.getHealth();
    }

    @Override
    public void setHealth(double health) {
        this.original.setHealth(health);
    }

    @Override
    public void heal() {
        setHealth(this.original.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
    }

    @Override
    public double getMaximumHealth() {
        return this.original.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    @Override
    public void setMaximumHealth(double health) {
        this.original.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
    }

    @Override
    public void damage(double amount) {
        this.original.damage(amount);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<T> projectile) {
        return null;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<T> projectile, Vector vector) {
        return null;
    }
}
