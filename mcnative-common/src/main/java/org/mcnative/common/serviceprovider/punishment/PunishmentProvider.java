/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 19:41
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

package org.mcnative.common.serviceprovider.punishment;

import org.mcnative.common.player.MinecraftPlayer;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public interface PunishmentProvider {

    Collection<MinecraftPlayer> getBanList();

    Collection<MinecraftPlayer> getMuteList();


    boolean isBanned(MinecraftPlayer player);

    String getBanReason(MinecraftPlayer player);

    void ban(MinecraftPlayer player, String reason);

    void ban(MinecraftPlayer player, String reason, long time, TimeUnit unit);

    void unban(MinecraftPlayer player);


    boolean isMuted(MinecraftPlayer player);

    String getMuteReason(MinecraftPlayer player);

    void mute(MinecraftPlayer player, String reason);

    void mute(MinecraftPlayer player, String reason, long time, TimeUnit unit);

    void unmute(MinecraftPlayer player);

}
