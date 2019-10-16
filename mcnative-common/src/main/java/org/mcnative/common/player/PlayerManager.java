/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.09.19, 20:30
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

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;

public interface PlayerManager<O extends OnlineMinecraftPlayer> {

    int getOnlineCount();

    Collection<O> getOnlinePlayers();

    <T extends MinecraftPlayer> Collection<T> getOnlinePlayers(Class<T> playerClass);

    MinecraftPlayer getPlayer(int id);

    MinecraftPlayer getPlayer(UUID uniqueId);

    MinecraftPlayer getPlayer(long xBoxId);

    MinecraftPlayer getPlayer(String name);


    O getOnlinePlayer(int id);

    O getOnlinePlayer(UUID uniqueId);

    O getOnlinePlayer(long xBoxId);

    O getOnlinePlayer(String name);


    <T extends MinecraftPlayer> void registerPlayerAdapter(Class<T> playerClass, Function<MinecraftPlayer,T> translator);

    <T extends MinecraftPlayer> T translate(Class<T> translatedClass, MinecraftPlayer player);

}
