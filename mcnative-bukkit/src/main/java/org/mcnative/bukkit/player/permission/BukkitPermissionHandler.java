/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.02.20, 14:19
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

package org.mcnative.bukkit.player.permission;

import net.prematic.libraries.utility.Iterators;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.serviceprovider.permission.PermissionGroup;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.serviceprovider.permission.PermissionResult;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;

//@Todo implement design workaround and crate vault integration
public class BukkitPermissionHandler implements PermissionHandler {

    private final Player player;

    public BukkitPermissionHandler(Player player) {
        this.player = player;
    }

    @Override
    public boolean isCached() {
        return true;
    }

    @Override
    public boolean setCached(boolean b) {
        return false;
    }

    @Override
    public PermissionHandler reload() {
        return this;
    }

    @Override
    public Collection<PermissionGroup> getGroups() {
        return Collections.emptyList();
    }

    @Override
    public Collection<String> getPermissions() {
        return Iterators.map(player.getEffectivePermissions(), PermissionAttachmentInfo::getPermission);
    }

    @Override
    public Collection<String> getEffectivePermissions() {
        return getPermissions();
    }

    @Override
    public PlayerDesign getDesign() {
        throw new UnsupportedOperationException("Bukkit permissions do not support permission design (@Todo implement)");
    }

    @Override
    public PlayerDesign getDesign(MinecraftPlayer forPlayer) {
        throw new UnsupportedOperationException("Bukkit permissions do not support permission design (@Todo implement)");
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return player.isPermissionSet(permission);
    }

    @Override
    public boolean isPermissionAssigned(String permission) {
        return player.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    @Override
    public PermissionResult hasPermissionExact(String permission) {
        return player.hasPermission(permission) ? PermissionResult.ALLOWED : PermissionResult.DENIED;
    }

    @Override
    public void setPermission(String permission, boolean allowed) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void unsetPermission(String permission) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void addGroup(String name) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void removeGroup(String name) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean isOperator() {
        return player.isOp();
    }

    @Override
    public void setOperator(boolean operator) {
        player.setOp(operator);
    }

    @Override
    public void setPlayerDesignGetter(BiFunction<MinecraftPlayer, PlayerDesign, PlayerDesign> designGetter) {
        throw new UnsupportedOperationException("Bukkit permissions do not support permission design (@Todo implement)");
    }
}
