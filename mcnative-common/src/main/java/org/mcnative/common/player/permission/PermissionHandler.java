/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.08.19, 20:19
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

import java.util.Collection;

public interface PermissionHandler {

    Collection<String> getGroups(MinecraftPlayer player);

    Collection<String> getPermissions(MinecraftPlayer player);

    Collection<String> getAllPermissions(MinecraftPlayer player);

    boolean isPermissionSet(MinecraftPlayer player,String permission);

    boolean hasPermission(MinecraftPlayer player, String permission);

    boolean isOperator(MinecraftPlayer player);

    void addPermission(MinecraftPlayer player, String permission);

    void removePermission(MinecraftPlayer player, String permission);

    void setOperator(MinecraftPlayer player, boolean operator);
}
