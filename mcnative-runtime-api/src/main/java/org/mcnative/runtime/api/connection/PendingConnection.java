/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.10.19, 11:08
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

package org.mcnative.runtime.api.connection;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.player.profile.GameProfile;

import java.net.InetSocketAddress;
import java.util.UUID;

public interface PendingConnection extends MinecraftConnection {

    UUID getUniqueId();

    void setUniqueId(UUID uniqueId);

    long getXBoxId();


    GameProfile getGameProfile();

    void setGameProfile(GameProfile profile);


    boolean isOnlineMode();

    void setOnlineMode(boolean online);

    InetSocketAddress getVirtualHost();


    boolean isPlayerAvailable();

    ConnectedMinecraftPlayer getPlayer();
}
