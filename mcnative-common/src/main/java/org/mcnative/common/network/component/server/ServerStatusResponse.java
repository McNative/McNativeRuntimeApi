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

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.bml.variable.EmptyVariableSet;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.io.FileUtil;
import org.mcnative.common.McNative;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.text.components.MessageComponent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface ServerStatusResponse {

    URL DEFAULT_FAVICON_URL = FileUtil.newUrl("https://raw.githubusercontent.com/McNative/McNative/master/images/McNativeDefaultServerIcon.png");

    int DYNAMIC_CALCULATED = Integer.MIN_VALUE;

    ServerVersion getVersion();

    ServerStatusResponse setVersion(ServerVersion version);

    ServerStatusResponse setVersion(String name, MinecraftProtocolVersion protocol);


    MessageComponent<?> getDescription();

    VariableSet getDescriptionVariables();

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

    ServerStatusResponse setFavicon(URL url);

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

    ServerStatusResponse removePlayerInfo(PlayerInfo info);

    ServerStatusResponse clearPlayerInfo();

    default Document compile(){
        Document result = Document.newDocument();

        Document versionEntry = Document.newDocument("version");
        versionEntry.set("name",getVersion().getName());
        versionEntry.set("protocol",getVersion().getProtocol().getNumber());

        result.addEntry(versionEntry);
        result.set("description",getDescription() != null ? getDescription().compile() : "");
        result.set("favicon",getFavicon());

        Document players = Document.newDocument("players");
        result.addEntry(players);
        Document samples = Document.factory().newArrayEntry("sample");
        players.addEntry(samples);
        players.set("max",getMaxPlayers());
        players.set("online",getOnlinePlayers());

        for (PlayerInfo player : getPlayerInfo()) {
            Document playerEntry = Document.newDocument();
            playerEntry.set("name",player.getName());
            playerEntry.set("id",player.getUniqueId());
            samples.addEntry(playerEntry);
        }

        return result;
    }

    default String compileToString(){
        return DocumentFileType.JSON.getWriter().write(compile(),false);
    }

    ServerStatusResponse clone();

    static ServerStatusResponse newServerPingResponse(){
        return McNative.getInstance().getObjectCreator().createServerStatusResponse();
    }

    static PlayerInfo newPlayerInfo(String text){
        return new TextPlayerInfo(text);
    }

    interface PlayerInfo {

        UUID getUniqueId();

        String getName();

    }

    class TextPlayerInfo implements PlayerInfo {

        private final String text;


        public TextPlayerInfo(String text) {
            this.text = text;
        }

        @Override
        public UUID getUniqueId() {
            return UUID.randomUUID();
        }

        @Override
        public String getName() {
            return text;
        }
    }

}
