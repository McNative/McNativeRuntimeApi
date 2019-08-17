/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 05.08.19, 17:56
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

package org.mcnative.common;

import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.text.MessageComponent;
import org.mcnative.common.text.TextComponent;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Collection;
import java.util.UUID;

public interface ServerPingResponse {

    String getVersion();

    ServerPingResponse setVersion(String name);

    int getProtocolId();

    ServerPingResponse setProtocolId(int id);

    ServerPingResponse setProtocol(MinecraftProtocolVersion version);

    ServerPingResponse setVersion(String name, int id);

    ServerPingResponse setVersion(String name, MinecraftProtocolVersion version);


    TextComponent[] getDescription();

    ServerPingResponse setDescription(TextComponent[] description);

    ServerPingResponse setDescription(MessageComponent line1, MessageComponent line2);

    ServerPingResponse setLine1(MessageComponent... component);

    ServerPingResponse setLine2(MessageComponent... component);


    String getFavicon();

    ServerPingResponse setFavicon(String favicon);

    ServerPingResponse setFavicon(ImageIO image);

    ServerPingResponse setFavicon(File location);


    int getMaxPlayers();

    ServerPingResponse setMaxPlayers(int maxPlayers);


    int getOnlinePlayers();

    ServerPingResponse setOnlinePlayers(int onlinePlayers);


    Collection<PlayerInfo> getPlayerInfo();

    ServerPingResponse setPlayerInfo(Collection<PlayerInfo> playerInfo);

    ServerPingResponse addPlayerInfo(PlayerInfo info);

    default ServerPingResponse addPlayerInfo(String text){
        return addPlayerInfo(newPlayerInfo(UUID.randomUUID(),text));
    }

    ServerPingResponse removePlayerInfo(PlayerInfo info);


    interface PlayerInfo {

        UUID getUniqueId();

        String getName();
    }


    static ServerPingResponse newServerPingResponse(){
        return null;
    }

    static PlayerInfo newPlayerInfo(UUID uuid,String text){
        return null;
    }

}
