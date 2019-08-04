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

package org.mcnative.common.player;

import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.utility.annonations.Nullable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface MinecraftPlayer extends CommandSender {

    String getName();

    String getDisplayName();

    UUID getUniqueId();

    long getXBoxId();

    long getFirstPlayed();

    long getLastPlayed();

    @Nullable
    GameProfile getGameProfile();

    Document getProperties();


    @Nullable
    OnlineMinecraftPlayer getAsOnlinePlayer();

    boolean isOnline();

    default boolean hasPlayedBefore(){
        return getFirstPlayed() != 0 || getFirstPlayed() == getLastPlayed();
    }


    boolean isWhitelisted();

    void setWhitelisted(boolean whitelisted);


    boolean isOperator();

    void setOperator(boolean operator);


    boolean hasPermission(String permission);

    void addPermission(String permission);

    void removePermission(String permission);


    boolean isBanned();

    void ban(String reason);

    void ban(String reason, long time, TimeUnit unit);

    void unban();


    boolean isMuted();

    void mute(String reason);

    void mute(String reason, long time, TimeUnit unit);

    void unmute();
}
