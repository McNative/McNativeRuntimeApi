/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 20.09.19, 20:36
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

package org.mcnative.common.player;

import net.prematic.libraries.document.Document;
import org.mcnative.common.McNative;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.serviceprovider.permission.PermissionGroup;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;
import org.mcnative.common.serviceprovider.punishment.PunishmentProvider;
import org.mcnative.common.serviceprovider.whitelist.WhitelistProvider;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class OfflineMinecraftPlayer implements MinecraftPlayer {

    private MinecraftPlayerData data;
    private PermissionHandler permissionHandler;

    public OfflineMinecraftPlayer(MinecraftPlayerData data) {
        this.data = data;
    }

    @Override
    public String getName() {
        return getData().getName();
    }

    @Override
    public UUID getUniqueId() {
        return getData().getUniqueId();
    }

    @Override
    public long getXBoxId() {
        return getData().getXBoxId();
    }

    @Override
    public long getFirstPlayed() {
        return getData().getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return getData().getLastPlayed();
    }

    @Override
    public GameProfile getGameProfile() {
        return getData().getGameProfile();
    }

    @Override
    public Document getProperties() {
        return getData().getProperties();
    }

    private MinecraftPlayerData getData(){
        if(!data.isCached()) data = data.reload();
        return data;
    }

    @Override
    public String getDisplayName() {
        return getDisplayName(null);
    }

    @Override
    public String getDisplayName(MinecraftPlayer player) {
        return null;//@Todo format display name
    }

    @Override
    public PlayerDesign getDesign() {
        return getPermissionHandler().getDesign();
    }

    @Override
    public PlayerDesign getDesign(MinecraftPlayer player) {
        return getPermissionHandler().getDesign(player);
    }

    @Override
    public <T extends MinecraftPlayer> T getAs(Class<T> otherPlayerClass) {
        return McNative.getInstance().getPlayerManager().translate(otherPlayerClass,this);
    }

    @Override
    public ConnectedMinecraftPlayer getAsConnectedPlayer() {
        return McNative.getInstance().getLocal().getConnectedPlayer(getUniqueId());
    }

    @Override
    public OnlineMinecraftPlayer getAsOnlinePlayer() {
        return McNative.getInstance().getNetwork().getOnlinePlayer(getUniqueId());
    }

    @Override
    public boolean isConnected() {
        return getAsConnectedPlayer() != null;
    }

    @Override
    public boolean isOnline() {
        return getAsOnlinePlayer() != null;
    }

    @Override
    public boolean isWhitelisted() {
        return McNative.getInstance().getRegistry().getService(WhitelistProvider.class).isWhitelisted(this);
    }

    @Override
    public void setWhitelisted(boolean whitelisted) {
        McNative.getInstance().getRegistry().getService(WhitelistProvider.class).set(this,whitelisted);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PermissionHandler getPermissionHandler() {
        if(permissionHandler == null){
            permissionHandler = McNative.getInstance().getRegistry().getService(PermissionProvider.class).getPlayerHandler(this);
        }else if(!permissionHandler.isCached()){
            permissionHandler = permissionHandler.reload();
        }
        return permissionHandler;
    }

    @Override
    public void setPlayerDesignGetter(BiFunction<MinecraftPlayer, PlayerDesign, PlayerDesign> designGetter) {
        getPermissionHandler().setPlayerDesignGetter(designGetter);
    }

    @Override
    public boolean isOperator() {
        return getPermissionHandler().isOperator();
    }

    @Override
    public void setOperator(boolean operator) {
        getPermissionHandler().setOperator(operator);
    }

    @Override
    public Collection<String> getPermissions() {
        return getPermissionHandler().getPermissions();
    }

    @Override
    public Collection<String> getAllPermissions() {
        return getPermissionHandler().getAllPermissions();
    }

    @Override
    public Collection<PermissionGroup> getGroups() {
        return getPermissionHandler().getGroups();
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return getPermissionHandler().isPermissionSet(permission);
    }

    @Override
    public boolean isPermissionAssigned(String permission) {
        return getPermissionHandler().isPermissionAssigned(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        return getPermissionHandler().hasPermission(permission);
    }

    @Override
    public void setPermission(String permission, boolean allowed) {
        getPermissionHandler().setPermission(permission, allowed);
    }

    @Override
    public void unsetPermission(String permission) {
        getPermissionHandler().unsetPermission(permission);
    }

    @Override
    public void addGroup(String name) {
        getPermissionHandler().addGroup(name);
    }

    @Override
    public void removeGroup(String name) {
        getPermissionHandler().removeGroup(name);
    }

    @Override
    public boolean isBanned() {
        return McNative.getInstance().getRegistry().getService(PunishmentProvider.class).isBanned(this);
    }

    @Override
    public String getBanReason() {
        return McNative.getInstance().getRegistry().getService(PunishmentProvider.class).getBanReason(this);
    }

    @Override
    public void ban(String reason) {
        McNative.getInstance().getRegistry().getService(PunishmentProvider.class).ban(this,reason);
    }

    @Override
    public void ban(String reason, long time, TimeUnit unit) {
        McNative.getInstance().getRegistry().getService(PunishmentProvider.class).ban(this,reason,time,unit);
    }

    @Override
    public void unban() {
        McNative.getInstance().getRegistry().getService(PunishmentProvider.class).unban(this);
    }

    @Override
    public boolean isMuted() {
        return McNative.getInstance().getRegistry().getService(PunishmentProvider.class).isMuted(this);
    }

    @Override
    public String getMuteReason() {
        return McNative.getInstance().getRegistry().getService(PunishmentProvider.class).getMuteReason(this);
    }

    @Override
    public void mute(String reason) {
        McNative.getInstance().getRegistry().getService(PunishmentProvider.class).mute(this,reason);
    }

    @Override
    public void mute(String reason, long time, TimeUnit unit) {
        McNative.getInstance().getRegistry().getService(PunishmentProvider.class).mute(this,reason,time,unit);
    }

    @Override
    public void unmute() {
        McNative.getInstance().getRegistry().getService(PunishmentProvider.class).unmute(this);
    }

    @Override
    public boolean equals(Object object) {
        if(object == this) return true;
        else if(object instanceof MinecraftPlayerComparable) return ((MinecraftPlayerComparable) object).equals(this);
        else if(object instanceof MinecraftPlayer) return ((MinecraftPlayer) object).getUniqueId().equals(getUniqueId());
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + getName() +
                ",uniqueId=" + getUniqueId() +
                ",xBoxId=" + getXBoxId() +
                '}';
    }


}
