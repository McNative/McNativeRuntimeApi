/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 16.10.19, 20:51
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

package org.mcnative.common.player.data;

import net.prematic.databasequery.core.DatabaseCollection;
import net.prematic.databasequery.core.datatype.DataType;
import net.prematic.databasequery.core.query.InsertQuery;
import net.prematic.databasequery.core.query.option.CreateOption;
import net.prematic.databasequery.core.query.result.QueryResult;
import net.prematic.databasequery.core.query.result.QueryResultEntry;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import org.mcnative.common.McNative;
import org.mcnative.common.player.profile.GameProfile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DefaultPlayerDataStorageHandler implements PlayerDataStorageHandler {

    private final DatabaseCollection playerDataStorage;

    public DefaultPlayerDataStorageHandler() {
        try {
            this.playerDataStorage = McNative.getInstance().getStorageManager().getDatabase(McNative.getInstance())
                    .createCollection("PlayerData")
                    .attribute("id", DataType.INTEGER, CreateOption.AUTO_INCREMENT, CreateOption.PRIMARY_KEY, CreateOption.INDEX)
                    .attribute("name", DataType.STRING, 32, CreateOption.NOT_NULL, CreateOption.INDEX)
                    .attribute("uniqueId", DataType.UUID, CreateOption.INDEX, CreateOption.UNIQUE)
                    .attribute("xBoxId", DataType.LONG, CreateOption.INDEX, CreateOption.UNIQUE)
                    .attribute("firstPlayed", DataType.LONG, CreateOption.NOT_NULL)
                    .attribute("lastPlayed", DataType.LONG, CreateOption.NOT_NULL)
                    .attribute("gameProfile", DataType.LONG_TEXT)
                    .attribute("properties", DataType.LONG_TEXT, -1, "{}")
                    .create().get();
        } catch (InterruptedException | ExecutionException ignored) {
            throw new RuntimeException("Can't create player data storage handler.");
        }
    }

    @Override
    public MinecraftPlayerData getPlayerData(String name) {
        return createPlayerDataByQueryResult(name, this.playerDataStorage.find().where("name", name).execute());
    }

    @Override
    public MinecraftPlayerData getPlayerData(UUID uniqueId) {
        return createPlayerDataByQueryResult(uniqueId, this.playerDataStorage.find().where("uniqueId", uniqueId).execute());
    }

    @Override
    public MinecraftPlayerData getPlayerData(long xBoxId) {
        return createPlayerDataByQueryResult(xBoxId, this.playerDataStorage.find().where("xBoxId", xBoxId).execute());
    }

    private MinecraftPlayerData createPlayerDataByQueryResult(Object identifier, CompletableFuture<QueryResult> result) {
        try {
            QueryResultEntry entry = result.get().first();
            return new DefaultMinecraftPlayerData(entry.getInt("id"),
                    entry.getString("name"),
                    entry.getUniqueId("uniqueId"),
                    entry.getLong("xBoxId"),
                    entry.getLong("firstPlayed"),
                    entry.getLong("lastPlayed"),
                    GameProfile.fromJson(entry.getString("gameProfile")),
                    DocumentFileType.JSON.getReader().read(entry.getString("properties")));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Can't get player data by identifier %s.", identifier));
        }
    }

    @Override
    public MinecraftPlayerData createPlayerData(String name, UUID uniqueId, long xBoxId, long firstPlayed, long lastPlayed, GameProfile gameProfile) {
        InsertQuery insertQuery = this.playerDataStorage.insert()
                .set("name", name)
                .set("firstPlayed", firstPlayed)
                .set("lastPlayed", lastPlayed)
                .set("gameProfile", DocumentFileType.JSON.getWriter().write(Document.newDocument().add("gameProfile", gameProfile), false));
        if(uniqueId != null) insertQuery.set("uniqueId", uniqueId);
        else if(xBoxId != -1) insertQuery.set("xBoxId", xBoxId);
        int id;
        try {
            id = insertQuery.executeAndGetGeneratedKeys("id").get().first().getInt("id");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Can't create player data for player[%s, %s, %s].", name, uniqueId, xBoxId));
        }
        return new DefaultMinecraftPlayerData(id, name, uniqueId, xBoxId, firstPlayed, lastPlayed, gameProfile, Document.newDocument());
    }
}
