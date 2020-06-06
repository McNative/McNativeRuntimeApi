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

import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.common.McNative;
import org.mcnative.common.event.player.design.MinecraftPlayerDesignRequestEvent;
import org.mcnative.common.event.player.design.MinecraftPlayerDesignSetEvent;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;
import org.mcnative.common.serviceprovider.permission.PermissionResult;
import org.mcnative.common.serviceprovider.punishment.PunishmentProvider;
import org.mcnative.common.serviceprovider.whitelist.WhitelistProvider;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OfflineMinecraftPlayer implements MinecraftPlayer {

    public static String DISPLAY_NAME_FORMAT = "{name}";

    protected MinecraftPlayerData data;
    protected PermissionHandler permissionHandler;

    private Collection<PlayerSetting> settings;

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
    public Language getLanguage() {
        return getData().getLanguage();
    }

    @Override
    public void setLanguage(Language language) {
        Validate.notNull(language);
        data.updateLanguage(language);
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
        String result = DISPLAY_NAME_FORMAT;
        if(result != null){
            VariableSet variables = VariableSet.create();
            variables.add("name",getName());
            variables.add("player",player);
            PlayerDesign design = getDesign(player);
            if(design != null){
                variables.add("design",design);
                design.appendAdditionalVariables(variables);
            }
            result = variables.replace(result);
        }
        return result;
    }

    @Override
    public PlayerDesign getDesign() {
        return getDesign(null);
    }

    @Override
    public PlayerDesign getDesign(MinecraftPlayer player) {
        PermissionHandler handler = getPermissionHandler();
        PlayerDesign design = handler != null ? handler.getDesign(player): null;
        if(design == null) design = getData().getDesign();
        MinecraftPlayerDesignRequestEvent event = new MinecraftPlayerDesignRequestEvent(this,design);
        McNative.getInstance().getLocal().getEventBus().callEvent(event);
        return event.getDesign();
    }

    @Override
    public void setDesign(PlayerDesign design) {
        Validate.notNull(design);
        MinecraftPlayerDesignSetEvent event = new MinecraftPlayerDesignSetEvent(this,design);
        McNative.getInstance().getLocal().getEventBus().callEventAsync(event)
                .thenAccept(event1 -> getData().updateDesign(event1.getDesign()));
    }


    @Override
    public Collection<PlayerSetting> getSettings() {
        if(settings == null){
            settings = McNative.getInstance().getRegistry().getService(PlayerDataProvider.class).loadSettings(getUniqueId());
        }
        return settings;
    }

    @Override
    public Collection<PlayerSetting> getSettings(String owner) {
        return Iterators.filter(getSettings(), setting -> setting.getOwner().equalsIgnoreCase(owner));
    }

    @Override
    public PlayerSetting getSetting(String owner, String key) {
        return Iterators.findOne(getSettings(), setting
                -> setting.getOwner().equalsIgnoreCase(owner)
                && setting.getKey().equalsIgnoreCase(key));
    }

    @Override
    public PlayerSetting setSetting(String owner, String key, Object value) {
        PlayerSetting setting = getSetting(owner,key);
        if(setting == null){
            setting = McNative.getInstance().getRegistry().getService(PlayerDataProvider.class)
                    .createSetting(getUniqueId(),owner,key,value);
            this.settings.add(setting);
        }else {
            setting.setValue(value);
        }
        return setting;
    }

    @Override
    public void removeSetting(String owner, String key) {
        PlayerSetting setting = getSetting(owner,key);
        this.settings.remove(setting);
        McNative.getInstance().getRegistry().getService(PlayerDataProvider.class)
                .deleteSetting(setting);
    }

    @Override
    public <T> T getAs(Class<T> otherPlayerClass) {
        Validate.notNull(otherPlayerClass);
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

    @Override
    public PermissionHandler getPermissionHandler() {
        if(permissionHandler == null){
            permissionHandler = McNative.getInstance().getRegistry().getService(PermissionProvider.class).getPlayerHandler(this);
        }else if(!permissionHandler.isCached()){
            permissionHandler = permissionHandler.reload();
        }
        return permissionHandler;
    }

    private PermissionHandler getPermissionHandlerExcepted() {
        PermissionHandler handler = getPermissionHandler();
        if(handler == null) throw new UnsupportedOperationException("No permission handler available");
        return handler;
    }

    @Override
    public boolean isOperator() {
        return getPermissionHandlerExcepted().isOperator();
    }

    @Override
    public void setOperator(boolean operator) {
        getPermissionHandlerExcepted().setOperator(operator);
    }

    @Override
    public Collection<String> getPermissions() {
        return getPermissionHandlerExcepted().getPermissions();
    }

    @Override
    public Collection<String> getEffectivePermissions() {
        return getPermissionHandlerExcepted().getEffectivePermissions();
    }

    @Override
    public Collection<String> getGroups() {
        return getPermissionHandlerExcepted().getGroups();
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return getPermissionHandlerExcepted().isPermissionSet(permission);
    }

    @Override
    public boolean isPermissionAssigned(String permission) {
        return getPermissionHandlerExcepted().isPermissionAssigned(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        return getPermissionHandlerExcepted().hasPermission(permission);
    }

    @Override
    public PermissionResult hasPermissionExact(String permission) {
        return getPermissionHandlerExcepted().hasPermissionExact(permission);
    }

    @Override
    public void setPermission(String permission, boolean allowed) {
        getPermissionHandlerExcepted().setPermission(permission, allowed);
    }

    @Override
    public void unsetPermission(String permission) {
        getPermissionHandlerExcepted().unsetPermission(permission);
    }

    @Override
    public void addGroup(String name) {
        getPermissionHandlerExcepted().addGroup(name);
    }

    @Override
    public void removeGroup(String name) {
        getPermissionHandlerExcepted().removeGroup(name);
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
