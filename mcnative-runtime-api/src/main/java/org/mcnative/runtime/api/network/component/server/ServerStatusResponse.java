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

package org.mcnative.runtime.api.network.component.server;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.io.FileUtil;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.protocol.MinecraftEdition;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface ServerStatusResponse {

    URL DEFAULT_FAVICON_URL = FileUtil.newUrl("https://content.pretronic.net/products/mcnative/icon.png");

    int DYNAMIC_CALCULATED = Integer.MIN_VALUE;

    ServerVersion getVersion();

    ServerStatusResponse setVersion(ServerVersion version);

    ServerStatusResponse setVersion(String name, MinecraftProtocolVersion protocol);


    MessageComponent<?> getDescription();

    VariableSet getDescriptionVariables();

    default ServerStatusResponse setDescription(MessageComponent<?> description){
        return setDescription(description, VariableSet.createEmpty());
    }

    ServerStatusResponse setDescription(MessageComponent<?> description,VariableSet variables);

    default ServerStatusResponse setDescription(MessageComponent<?> line1, MessageComponent<?> line2){
        return setDescription(line1, line2,VariableSet.createEmpty());
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

    int getPing();

    void setPing(int ping);

    default Document compile(MinecraftProtocolVersion version){
        Document result = Document.newDocument();

        Document versionEntry = Document.newDocument("version");
        versionEntry.set("name",getVersion().getName());
        versionEntry.set("protocol",getVersion().getProtocol().getNumber());

        result.addEntry(versionEntry);
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_8)){
            result.set("description",getDescription() != null ? getDescription().compile(version) : "");
        }else{
            result.set("description",getDescription() != null ? getDescription().compileToString(version) : "");
        }
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

    default void read(String input){
        read(DocumentFileType.JSON.getReader().read(input));
    }

    default void read(Document input){
        setDescription(Text.decompile(input.getDocument("description")));
        setOnlinePlayers(input.getInt("players.online"));
        setMaxPlayers(input.getInt("players.max"));
        setVersion(input.getString("version.name"),MinecraftProtocolVersion.of(MinecraftEdition.JAVA,input.getInt("version.protocol")));
        if(input.contains("favicon")){
            setFavicon(input.getString("favicon"));
        }
    }

    default String compileToString(MinecraftProtocolVersion version){
        return DocumentFileType.JSON.getWriter().write(compile(version),false);
    }

    ServerStatusResponse clone();

    static ServerStatusResponse newServerStatusResponse(){
        return McNative.getInstance().getObjectFactory().createObject(ServerStatusResponse.class);
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
