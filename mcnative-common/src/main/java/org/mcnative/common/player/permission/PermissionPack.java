/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.common.player.permission;

import org.mcnative.common.player.MinecraftPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PermissionPack {

    private static final Collection<PermissionPack> PACKS = new ArrayList<>();

    private final String name;
    private final String permission;
    private final Collection<String> children;

    public PermissionPack(String name, String permission){
        this(name,permission,new ArrayList<>());
    }

    public PermissionPack(String name, String permission,String... children) {
        this(name,permission, Arrays.asList(children));
    }

    public PermissionPack(String name, String permission, Collection<String> children) {
        this.name = name;
        this.permission = permission;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public Collection<String> getChildren() {
        return children;
    }

    public boolean canPlayerUse(MinecraftPlayer player){
        return player.hasPermission(permission);
    }

    public static Collection<PermissionPack> getPacks() {
        return PACKS;
    }

    public static void registerPack(PermissionPack pack){
        PACKS.add(pack);
    }

    public static void unregisterPack(PermissionPack pack){
        PACKS.remove(pack);
    }

    public static Collection<String> searchPackPermissionsForPermission(String childPermission){
        Collection<String> permissions = new ArrayList<>();
        PACKS.forEach(pack -> {
            if(pack.children.contains(childPermission)) permissions.add(pack.getPermission());
        });
        return permissions;
    }
}
