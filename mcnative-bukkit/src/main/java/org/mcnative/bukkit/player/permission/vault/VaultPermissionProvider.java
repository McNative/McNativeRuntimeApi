/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.03.20, 18:49
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

import net.milkbowl.vault.permission.Permission;
import org.bukkit.World;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;

import java.util.Arrays;
import java.util.Collection;

public class VaultPermissionProvider implements PermissionProvider {

    private final Permission permission;

    public VaultPermissionProvider(Permission permission) {
        this.permission = permission;
    }

    @Override
    public Collection<MinecraftPlayer> getOperators() {
        return null;
    }

    public Permission getPermission() {
        return permission;
    }

    @Override
    public Collection<String> getGroups() {
        return Arrays.asList(permission.getGroups());
    }

    @Override
    public PermissionHandler getPlayerHandler(MinecraftPlayer player) {
        return null;
    }

    @Override
    public boolean createGroup(String name) {
        throw new UnsupportedOperationException("Vault does not support modifying permission groups");
    }

    @Override
    public boolean deleteGroup(String name) {
        throw new UnsupportedOperationException("Vault does not support modifying permission groups");
    }

    @Override
    public void setGroupPermission(String group, String permission, boolean allowed) {
        if(allowed) this.permission.groupAdd((World)null,group,permission);
        else this.permission.groupRemove((World)null,group,permission);
    }

    @Override
    public void unsetGroupPermission(String group, String permission) {
        this.permission.groupRemove((World)null,group,permission);
    }
}
