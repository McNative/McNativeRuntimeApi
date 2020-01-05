/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 18:43
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

package org.mcnative.common.network.component.server;

import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Collection;
import java.util.UUID;

public interface ServerStatusResponse {

    ServerVersion getVersion();

    ServerStatusResponse setVersion(ServerVersion version);

    ServerStatusResponse setVersion(String name, MinecraftProtocolVersion protocol);


    MessageComponent<?>[] getDescription();

    ServerStatusResponse setDescription(MessageComponent<?>[] description);

    default ServerStatusResponse setDescription(MessageComponent<?> line1, MessageComponent<?> line2){
        return setDescription(line1, line2,VariableSet.newEmptySet());
    }

    ServerStatusResponse setDescription(MessageComponent<?> line1, MessageComponent<?> line2, VariableSet variables);

    default ServerStatusResponse setLine1(MessageComponent<?> component){
        return setLine1(component,VariableSet.newEmptySet());
    }

    ServerStatusResponse setLine1(MessageComponent<?> component, VariableSet variables);

    default ServerStatusResponse setLine2(MessageComponent<?> component){
        return setLine2(component,VariableSet.newEmptySet());
    }

    ServerStatusResponse setLine2(MessageComponent<?> component, VariableSet variables);


    String getFavicon();

    ServerStatusResponse setFavicon(String favicon);

    ServerStatusResponse setFavicon(ImageIO image);

    ServerStatusResponse setFavicon(File location);


    int getMaxPlayers();

    ServerStatusResponse setMaxPlayers(int maxPlayers);


    int getOnlinePlayers();

    ServerStatusResponse setOnlinePlayers(int onlinePlayers);


    Collection<PlayerInfo> getPlayerInfo();

    ServerStatusResponse setPlayerInfo(Collection<PlayerInfo> playerInfo);

    ServerStatusResponse addPlayerInfo(PlayerInfo info);

    default ServerStatusResponse addPlayerInfo(String text){
        return addPlayerInfo(newPlayerInfo(UUID.randomUUID(),text));
    }

    ServerStatusResponse removePlayerInfo(PlayerInfo info);


    static ServerStatusResponse newServerPingResponse(){
        throw new UnsupportedOperationException("McNative is not finished yet");
    }

    static PlayerInfo newPlayerInfo(UUID uuid,String text){
        throw new UnsupportedOperationException("McNative is not finished yet");
    }

    interface PlayerInfo {

        UUID getUniqueId();

        String getName();
    }

}
