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

import net.pretronic.libraries.message.bml.variable.EmptyVariableSet;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.McNative;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.text.components.MessageComponent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.UUID;

public interface ServerStatusResponse {

    ServerVersion getVersion();

    ServerStatusResponse setVersion(ServerVersion version);

    ServerStatusResponse setVersion(String name, MinecraftProtocolVersion protocol);


    MessageComponent<?> getDescription();

    default ServerStatusResponse setDescription(MessageComponent<?> description){
        return setDescription(description, EmptyVariableSet.newEmptySet());
    }

    ServerStatusResponse setDescription(MessageComponent<?> description,VariableSet variables);

    default ServerStatusResponse setDescription(MessageComponent<?> line1, MessageComponent<?> line2){
        return setDescription(line1, line2,VariableSet.newEmptySet());
    }

    ServerStatusResponse setDescription(MessageComponent<?> line1, MessageComponent<?> line2, VariableSet variables);


    String getFavicon();

    ServerStatusResponse setFavicon(String favicon);

    ServerStatusResponse setFavicon(BufferedImage image);

    ServerStatusResponse setFavicon(File location);


    int getMaxPlayers();

    ServerStatusResponse setMaxPlayers(int maxPlayers);


    int getOnlinePlayers();

    ServerStatusResponse setOnlinePlayers(int onlinePlayers);


    List<PlayerInfo> getPlayerInfo();

    ServerStatusResponse setPlayerInfo(List<PlayerInfo> playerInfo);

    ServerStatusResponse setPlayerInfo(PlayerInfo[] playerInfo);

    ServerStatusResponse addPlayerInfo(PlayerInfo info);

    ServerStatusResponse addPlayerInfo(String text);

    ServerStatusResponse addPlayerInfo(UUID uniqueId, String text);

    ServerStatusResponse removePlayerInfo(PlayerInfo info);

    ServerStatusResponse clearPlayerInfo();

    static ServerStatusResponse newServerPingResponse(){
        return McNative.getInstance().getObjectCreator().createServerStatusResponse();
    }

    static PlayerInfo newPlayerInfo(String name){
        return McNative.getInstance().getObjectCreator().createPlayerInfo(name);
    }

    static PlayerInfo newPlayerInfo(UUID uniqueId,String name){
        return McNative.getInstance().getObjectCreator().createPlayerInfo(uniqueId,name);
    }

    interface PlayerInfo {

        UUID getUniqueId();

        String getName();

    }

}
