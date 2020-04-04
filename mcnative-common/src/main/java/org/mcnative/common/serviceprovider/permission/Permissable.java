/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.10.19, 21:52
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

package org.mcnative.common.serviceprovider.permission;

import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;

import java.util.Collection;

public interface Permissable {

    PermissionHandler getPermissionHandler();

    Collection<String> getGroups();

    Collection<String> getPermissions();

    Collection<String> getEffectivePermissions();


    PlayerDesign getDesign();

    PlayerDesign getDesign(MinecraftPlayer forPlayer);


    boolean isPermissionSet(String permission);

    boolean isPermissionAssigned(String permission);

    boolean hasPermission(String permission);

    PermissionResult hasPermissionExact(String permission);


    void setPermission(String permission, boolean allowed);

    void unsetPermission(String permission);


    void addGroup(String name);

    void removeGroup(String name);


    boolean isOperator();

    void setOperator(boolean operator);
}
