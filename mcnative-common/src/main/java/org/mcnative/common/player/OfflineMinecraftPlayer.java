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

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OfflineMinecraftPlayer implements MinecraftPlayer{

    private final MinecraftPlayerData data;

    public OfflineMinecraftPlayer(MinecraftPlayerData data) {
        this.data = data;
    }

    @Override
    public String getName() {
        return data.getName();
    }

    @Override
    public UUID getUniqueId() {
        return data.getUniqueId();
    }

    @Override
    public long getXBoxId() {
        return data.getXBoxId();
    }

    @Override
    public long getFirstPlayed() {
        return data.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return data.getLastPlayed();
    }

    @Override
    public GameProfile getGameProfile() {
        return McNative.getInstance().getGameProfileLoader().getGameProfile(getUniqueId());//Todo Change to data?
    }

    @Override
    public Document getProperties() {
        return data.getProperties();
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
        return getDesign(null);
    }

    @Override
    public PlayerDesign getDesign(MinecraftPlayer player) {
        return McNative.getInstance().getPermissionHandler().getPlayerDesign(this);
    }

    @Override
    public <T extends MinecraftPlayer> T getAs(Class<T> otherPlayerClass) {
        return (T) McNative.getInstance().getPlayerManager().translate(otherPlayerClass,this);
    }

    @Override
    public OnlineMinecraftPlayer getAsOnlinePlayer() {
        return McNative.getInstance().getPlayerManager().getOnlinePlayer(getUniqueId());
    }

    @Override
    public boolean isOnline() {
        return getAsOnlinePlayer() != null;
    }

    @Override
    public boolean isWhitelisted() {
        return McNative.getInstance().getWhitelistHandler().isWhitelisted(this);
    }

    @Override
    public void setWhitelisted(boolean whitelisted) {
        McNative.getInstance().getWhitelistHandler().set(this,whitelisted);
    }

    @Override
    public boolean isOperator() {
        return McNative.getInstance().getPermissionHandler().isOperator(this);
    }

    @Override
    public void setOperator(boolean operator) {
        McNative.getInstance().getPermissionHandler().setOperator(this,operator);
    }

    @Override
    public Collection<String> getPermissions() {
        return McNative.getInstance().getPermissionHandler().getPermissions(this);
    }

    @Override
    public Collection<String> getAllPermissions() {
        return McNative.getInstance().getPermissionHandler().getAllPermissions(this);
    }

    @Override
    public Collection<String> getGroups() {
        return McNative.getInstance().getPermissionHandler().getGroups(this);
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return McNative.getInstance().getPermissionHandler().isPermissionSet(this,permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        return McNative.getInstance().getPermissionHandler().hasPermission(this,permission);
    }

    @Override
    public void addPermission(String permission) {
        McNative.getInstance().getPermissionHandler().addPermission(this,permission);
    }

    @Override
    public void removePermission(String permission) {
        McNative.getInstance().getPermissionHandler().removePermission(this,permission);
    }

    @Override
    public boolean isBanned() {
        return McNative.getInstance().getPunishmentHandler().isBanned(this);
    }

    @Override
    public String getBanReason() {
        return McNative.getInstance().getPunishmentHandler().getBanReason(this);
    }

    @Override
    public void ban(String reason) {
        McNative.getInstance().getPunishmentHandler().ban(this,reason);
    }

    @Override
    public void ban(String reason, long time, TimeUnit unit) {
        McNative.getInstance().getPunishmentHandler().ban(this,reason,time,unit);
    }

    @Override
    public void unban() {
        McNative.getInstance().getPunishmentHandler().unban(this);
    }

    @Override
    public boolean isMuted() {
        return McNative.getInstance().getPunishmentHandler().isMuted(this);
    }

    @Override
    public String getMuteReason() {
        return McNative.getInstance().getPunishmentHandler().getMuteReason(this);
    }

    @Override
    public void mute(String reason) {
        McNative.getInstance().getPunishmentHandler().mute(this,reason);
    }

    @Override
    public void mute(String reason, long time, TimeUnit unit) {
        McNative.getInstance().getPunishmentHandler().mute(this,reason,time,unit);
    }

    @Override
    public void unmute() {
        McNative.getInstance().getPunishmentHandler().unmute(this);
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
