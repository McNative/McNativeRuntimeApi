/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.03.20, 18:55
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

package org.mcnative.bukkit.player.permission.vault;

import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.serviceprovider.permission.PermissionResult;

import java.util.Collection;

public class VaultPermissionHandler implements PermissionHandler {

    //private final OfflinePlayer player;
    //private final VaultPermissionProvider provider;


    @Override
    public Collection<String> getGroups() {
        return null;
    }

    @Override
    public Collection<String> getPermissions() {
        return null;
    }

    @Override
    public Collection<String> getEffectivePermissions() {
        return null;
    }

    @Override
    public PlayerDesign getDesign() {
        return null;
    }

    @Override
    public PlayerDesign getDesign(MinecraftPlayer forPlayer) {
        return null;
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return false;
    }

    @Override
    public boolean isPermissionAssigned(String permission) {
        return false;
    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    @Override
    public PermissionResult hasPermissionExact(String permission) {
        return null;
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
        return false;
    }

    @Override
    public void setOperator(boolean operator) {

    }

    @Override
    public boolean isCached() {
        return false;//Unused
    }

    @Override
    public boolean setCached(boolean b) {
        return false;//Unused
    }

    @Override
    public PermissionHandler reload() {
        return this;//Unused
    }

}
