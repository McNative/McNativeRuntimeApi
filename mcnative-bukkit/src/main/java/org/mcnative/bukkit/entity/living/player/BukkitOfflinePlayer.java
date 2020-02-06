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

import net.prematic.libraries.document.Document;
import org.mcnative.bukkit.location.BukkitLocation;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.common.McNative;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.serviceprovider.permission.PermissionGroup;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.service.entity.living.player.OfflinePlayer;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.location.Location;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class BukkitOfflinePlayer<P extends org.bukkit.OfflinePlayer> implements OfflinePlayer {

    protected final P original;
    private final MinecraftPlayer offlinePlayer;

    public BukkitOfflinePlayer(P original) {
        this.original = original;
        this.offlinePlayer = McNative.getInstance().getPlayerManager().getPlayer(this.original.getUniqueId());
    }

    //@Todo implements handling for offline ender chest
    @Override
    public Inventory getEnderchestInventory() {
       return null;
    }

    @Override
    public Location getBedSpawnLocation() {
        if(this.original.getBedSpawnLocation() == null) return null;
        return new BukkitLocation(this.original.getBedSpawnLocation(), new BukkitWorld(this.original.getBedSpawnLocation().getWorld()));
    }

    @Override
    public void setBedSpawnLocation(Location location) {

    }

    @Override
    public UUID getUniqueId() {
        return this.offlinePlayer.getUniqueId();
    }

    @Override
    public String getName() {
        return this.offlinePlayer.getName();
    }

    @Override
    public long getXBoxId() {
        return this.offlinePlayer.getXBoxId();
    }

    @Override
    public long getFirstPlayed() {
        return this.offlinePlayer.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return this.offlinePlayer.getLastPlayed();
    }

    @Override
    public GameProfile getGameProfile() {
        return this.offlinePlayer.getGameProfile();
    }

    @Override
    public Document getProperties() {
        return this.offlinePlayer.getProperties();
    }

    @Override
    public String getDisplayName() {
        return this.offlinePlayer.getDisplayName();
    }

    @Override
    public String getDisplayName(MinecraftPlayer player) {
        return this.offlinePlayer.getDisplayName(player);
    }

    @Override
    public Collection<String> getPermissions() {
        return this.offlinePlayer.getPermissions();
    }

    @Override
    public Collection<String> getAllPermissions() {
        return this.offlinePlayer.getAllPermissions();
    }

    @Override
    public PlayerDesign getDesign() {
        return this.offlinePlayer.getDesign();
    }

    @Override
    public PlayerDesign getDesign(MinecraftPlayer player) {
        return this.offlinePlayer.getDesign(player);
    }

    @Override
    public <T> T getAs(Class<T> otherPlayerClass) {
        return null;
    }

    @Override
    public ConnectedMinecraftPlayer getAsConnectedPlayer() {
        return null;
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return this.offlinePlayer.isPermissionSet(permission);
    }

    @Override
    public boolean isPermissionAssigned(String permission) {
        return false;
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.offlinePlayer.hasPermission(permission);
    }

    @Override
    public void setPermission(String permission, boolean allowed) {

    }

    @Override
    public void unsetPermission(String permission) {

    }

    @Override
    public void addGroup(String name) {

    }

    @Override
    public void removeGroup(String name) {

    }


    @Override
    public boolean isOperator() {
        return this.offlinePlayer.isOperator();
    }

    @Override
    public void setOperator(boolean operator) {
        this.offlinePlayer.setOperator(operator);
    }

    @Override
    public void setPlayerDesignGetter(BiFunction<MinecraftPlayer, PlayerDesign, PlayerDesign> designGetter) {
        this.offlinePlayer.setPlayerDesignGetter(designGetter);
    }

    @Override
    public OnlineMinecraftPlayer getAsOnlinePlayer() {
        return this.offlinePlayer.getAsOnlinePlayer();
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isOnline() {
        return this.offlinePlayer.isOnline();
    }

    @Override
    public boolean isWhitelisted() {
        return this.offlinePlayer.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean whitelisted) {
        this.offlinePlayer.setWhitelisted(whitelisted);
    }

    @Override
    public boolean isBanned() {
        return this.offlinePlayer.isBanned();
    }

    @Override
    public String getBanReason() {
        return this.offlinePlayer.getBanReason();
    }

    @Override
    public void ban(String reason) {
        this.offlinePlayer.ban(reason);
    }

    @Override
    public void ban(String reason, long time, TimeUnit unit) {
        this.offlinePlayer.ban(reason, time, unit);
    }

    @Override
    public void unban() {
        this.offlinePlayer.unban();
    }

    @Override
    public boolean isMuted() {
        return this.offlinePlayer.isMuted();
    }

    @Override
    public String getMuteReason() {
        return this.offlinePlayer.getMuteReason();
    }

    @Override
    public void mute(String reason) {
        this.offlinePlayer.mute(reason);
    }

    @Override
    public void mute(String reason, long time, TimeUnit unit) {
        this.offlinePlayer.mute(reason, time, unit);
    }

    @Override
    public void unmute() {
        this.offlinePlayer.unmute();
    }

    @Override
    public PermissionHandler getPermissionHandler() {
        return this.offlinePlayer.getPermissionHandler();
    }

    @Override
    public Collection<PermissionGroup> getGroups() {
        return null;
    }
}
