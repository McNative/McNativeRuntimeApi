/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.10.19, 21:51
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
import java.util.function.BiFunction;

public interface PlayerPermissionHandler {

    Collection<String> getGroups();

    Collection<String> getPermissions();

    Collection<String> getAllPermissions();

    PlayerDesign getDesign();

    PlayerDesign getDesign(MinecraftPlayer forPlayer);

    boolean isPermissionSet(String permission);

    boolean hasPermission(String permission);

    void addPermission(String permission);

    void removePermission(String permission);

    boolean isOperator();

    void setOperator(boolean operator);

    void setPlayerDesignGetter(BiFunction<MinecraftPlayer,PlayerDesign,PlayerDesign> designGetter);

}
