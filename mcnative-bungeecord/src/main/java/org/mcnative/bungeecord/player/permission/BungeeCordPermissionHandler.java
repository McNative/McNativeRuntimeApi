/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.03.20, 15:43
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

package org.mcnative.bungeecord.player.permission;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.serviceprovider.permission.PermissionResult;

import java.util.Collection;
import java.util.function.BiFunction;

public class BungeeCordPermissionHandler implements PermissionHandler {

    private final ProxiedPlayer player;

    public BungeeCordPermissionHandler(ProxiedPlayer player) {
        this.player = player;
    }

    @Override
    public boolean isCached() {
        return true;
    }

    @Override
    public boolean setCached(boolean b) {
        return false;//Unused
    }

    @Override
    public PermissionHandler reload() {
        return this;
    }

    @Override
    public Collection<String> getGroups() {
        return player.getGroups();
    }

    @Override
    public Collection<String> getPermissions() {
        return player.getPermissions();
    }

    @Override
    public Collection<String> getEffectivePermissions() {
        return player.getPermissions();
    }

    @Override
    public PlayerDesign getDesign() {
        throw new UnsupportedOperationException("BungeeCord permissions do not support permission design (@Todo implement)");
    }

    @Override
    public PlayerDesign getDesign(MinecraftPlayer forPlayer) {
        throw new UnsupportedOperationException("BungeeCord permissions do not support permission design (@Todo implement)");
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return player.getPermissions().contains(permission);
    }

    @Override
    public boolean isPermissionAssigned(String permission) {
        return player.getPermissions().contains(permission);
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
        player.setPermission(permission,allowed);
    }

    @Override
    public void unsetPermission(String permission) {
        player.getPermissions().remove(permission);
    }

    @Override
    public void addGroup(String name) {
        player.addGroups(name);
    }

    @Override
    public void removeGroup(String name) {
        player.removeGroups(name);
    }

    @Override
    public boolean isOperator() {
        throw new UnsupportedOperationException("BungeeCord does not support operators");
    }

    @Override
    public void setOperator(boolean operator) {
        throw new UnsupportedOperationException("BungeeCord does not support operators");
    }

    @Override
    public void setPlayerDesignGetter(BiFunction<MinecraftPlayer, PlayerDesign, PlayerDesign> designGetter) {
        throw new UnsupportedOperationException("BungeeCord permissions do not support permission design (@Todo implement)");
    }
}
