/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 04.11.19, 13:54
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

package org.mcnative.bukkit.entity.living.player;

import net.prematic.libraries.message.bml.variable.VariableSet;
import org.bukkit.Bukkit;
import org.mcnative.bukkit.entity.BukkitEntity;
import org.mcnative.bukkit.entity.living.BukkitHumanEntity;
import org.mcnative.bukkit.inventory.item.BukkitItemStack;
import org.mcnative.bukkit.location.BukkitLocation;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.player.*;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.service.Effect;
import org.mcnative.service.GameMode;
import org.mcnative.service.advancement.AdvancementProgress;
import org.mcnative.service.entity.Entity;
import org.mcnative.service.entity.living.player.Player;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.location.Location;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BukkitPlayer extends BukkitOfflinePlayer<org.bukkit.entity.Player> implements Player, BukkitHumanEntity<org.bukkit.entity.Player> {

    private final BukkitWorld bukkitWorld;

    public BukkitPlayer(org.bukkit.entity.Player original, BukkitWorld bukkitWorld) {
        super(original);
        this.bukkitWorld = bukkitWorld;
    }

    @Override
    public org.bukkit.entity.Player getOriginal() {
        return this.original;
    }

    @Override
    public BukkitWorld getBukkitWorld() {
        return this.bukkitWorld;
    }

    @Override
    public void openBook(ItemStack book) {
        this.original.openBook(((BukkitItemStack)book).getOriginal());
    }

    @Override
    public void hide(OnlineMinecraftPlayer forPlayer) {
        org.bukkit.entity.Player player = Bukkit.getPlayer(forPlayer.getUniqueId());
        if(player != null) {
         //   this.original.hidePlayer(BukkitMcNativeBootstrap.getInstance(), player);
        }
    }

    @Override
    public void show(OnlineMinecraftPlayer forPlayer) {
        org.bukkit.entity.Player player = Bukkit.getPlayer(forPlayer.getUniqueId());
        if(player != null) {
            //       this.original.showPlayer(BukkitMcNativeBootstrap.getInstance(), player);
        }
    }

    @Override
    public boolean canSee(OnlineMinecraftPlayer forPlayer) {
        org.bukkit.entity.Player player = Bukkit.getPlayer(forPlayer.getUniqueId());
        if(player != null) {
            return this.original.canSee(player);
        }
        return false;
    }

    @Override
    public boolean isSneaking() {
        return this.original.isSneaking();
    }

    @Override
    public void setSneaking(boolean sneak) {
        this.original.setSneaking(sneak);
    }

    @Override
    public boolean isSprinting() {
        return  this.original.isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.original.setSprinting(sprinting);
    }

    @Override
    public boolean isSleepingIgnored() {
        return  this.original.isSleepingIgnored();
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        this.original.setSleepingIgnored(isSleeping);
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.getById(this.original.getGameMode().getValue());
    }

    @Override
    public void setGameMode(GameMode mode) {
        org.bukkit.GameMode bukkitGameMode = org.bukkit.GameMode.getByValue(mode.getId());
        if(bukkitGameMode != null) {
            this.original.setGameMode(bukkitGameMode);
        }
    }

    @Override
    public Location getCompassTarget() {
        return new BukkitLocation(this.original.getCompassTarget(), getBukkitWorld());
    }

    @Override
    public void setCompassTarget(Location location) {
        this.original.setCompassTarget(((BukkitLocation)location).getOriginal());
    }

    @Override
    public long getPlayerTime() {
        return this.original.getPlayerTime();
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        this.original.setPlayerTime(time, relative);
    }

    @Override
    public void resetPlayerTime() {
        this.original.resetPlayerTime();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return this.original.isPlayerTimeRelative();
    }

    @Override
    public float getExperience() {
        return this.original.getExp();
    }

    @Override
    public void setExperience(float xp) {
        this.original.setExp(xp);
    }

    @Override
    public void addExperience(float xp) {
        this.original.setExp(this.original.getExp()+xp);
    }

    @Override
    public void removeExperience(float xp) {
        this.original.setExp(this.original.getExp()-xp);
    }

    @Override
    public int getLevel() {
        return this.original.getLevel();
    }

    @Override
    public void setLevel(int level) {
        this.original.setLevel(level);
    }

    @Override
    public void addLevel(int level) {
        this.original.setLevel(this.original.getLevel()+level);
    }

    @Override
    public void removeLevel(int level) {
        this.original.setLevel(this.original.getLevel()-level);
    }

    @Override
    public int getTotalExperience() {
        return this.original.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int exp) {
        this.original.setTotalExperience(exp);
    }

    @Override
    public int getFoodLevel() {
        return this.original.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int food) {
        this.original.setFoodLevel(food);
    }

    @Override
    public float getSaturation() {
        return this.original.getSaturation();
    }

    @Override
    public void setSaturation(float value) {
        this.original.setSaturation(value);
    }

    @Override
    public float getExhaustion() {
        return this.original.getExhaustion();
    }

    @Override
    public void setExhaustion(float value) {
        this.original.setExhaustion(value);
    }

    @Override
    public boolean isAllowFlight() {
        return this.original.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean flight) {
        this.original.setAllowFlight(flight);
    }

    @Override
    public boolean isFlying() {
        return this.original.isFlying();
    }

    @Override
    public void setFlying(boolean value) {
        this.original.setFlying(value);
    }

    @Override
    public float getFlySpeed() {
        return this.original.getFlySpeed();
    }

    @Override
    public void setFlySpeed(float value) {
        this.original.setFlySpeed(value);
    }

    @Override
    public float getWalkSpeed() {
        return this.original.getWalkSpeed();
    }

    @Override
    public void setWalkSpeed(float value) {
        this.original.setWalkSpeed(value);
    }

    @Override
    public Entity getSpectatorTarget() {
        return null;
    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        this.original.setSpectatorTarget(((BukkitEntity)entity).getOriginal());
    }

    @Override
    public void playEffect(Location location, Effect effect, int data) {

    }

    @Override
    public AdvancementProgress getAdvancementProgress() {
        return null;
    }

    @Override
    public DeviceInfo getDevice() {
        return null;
    }

    @Override
    public boolean isOnlineMode() {
        return false;
    }

    @Override
    public PlayerSettings getSettings() {
        return null;
    }

    @Override
    public int getPing() {
        return 0;
    }

    @Override
    public ProxyServer getProxy() {
        return null;
    }

    @Override
    public MinecraftServer getServer() {
        return null;
    }

    @Override
    public void connect(MinecraftServer target, ServerConnectReason reason) {

    }

    @Override
    public CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target, ServerConnectReason reason) {
        return null;
    }

    @Override
    public ChatChannel getChatChannel() {
        return null;
    }

    @Override
    public void setChatChannel(ChatChannel channel) {

    }

    @Override
    public void kick(MessageComponent<?> message, VariableSet variables) {

    }

    @Override
    public void performCommand(String command) {
        this.original.performCommand(command);
    }

    @Override
    public void chat(String message) {
        this.original.chat(message);
    }

    @Override
    public void sendMessage(ChatPosition position, MessageComponent<?> component, VariableSet variables) {

    }

    @Override
    public void sendMessage(MessageComponent component, VariableSet variables) {

    }

    @Override
    public void sendSystemMessage(MessageComponent component, VariableSet variables) {

    }

    @Override
    public void sendTitle(Title title) {

    }

    @Override
    public void resetTitle() {

    }

    @Override
    public void sendActionbar(MessageComponent message, VariableSet variables) {

    }

    @Override
    public void sendActionbar(MessageComponent message, VariableSet variables, long staySeconds) {

    }

    @Override
    public void playNote(Instrument instrument, Note note) {

    }

    @Override
    public void playSound(Sound sound, SoundCategory category, float volume, float pitch) {

    }

    @Override
    public void stopSound(Sound sound) {

    }

    @Override
    public void stopSound(String sound, SoundCategory category) {

    }

    @Override
    public void check(Consumer<ProtocolCheck> checker) {

    }

    @Override
    public InetSocketAddress getVirtualHost() {
        return null;
    }

    @Override
    public InetSocketAddress getAddress() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {

    }

    @Override
    public void sendMessage(String s) {

    }
}
