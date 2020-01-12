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

import net.prematic.databasequery.api.collection.DatabaseCollection;
import net.prematic.databasequery.api.collection.field.FieldOption;
import net.prematic.databasequery.api.datatype.DataType;
import net.prematic.databasequery.api.query.result.QueryResult;
import net.prematic.databasequery.api.query.result.QueryResultEntry;
import net.prematic.databasequery.api.query.type.InsertQuery;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.utility.Validate;
import org.mcnative.common.McNative;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.plugin.configuration.ConfigurationProvider;

import java.util.Objects;
import java.util.UUID;

public class DefaultPlayerDataProvider implements PlayerDataProvider {

    final DatabaseCollection playerDataStorage;

    public DefaultPlayerDataProvider() {
        this.playerDataStorage = McNative.getInstance().getRegistry().getService(ConfigurationProvider.class)
                .getDatabase(McNative.getInstance()).createCollection("PlayerData")
                .field("uniqueId", DataType.UUID, FieldOption.NOT_NULL,FieldOption.UNIQUE, FieldOption.INDEX)
                .field("xBoxId", DataType.LONG, FieldOption.NOT_NULL, FieldOption.UNIQUE, FieldOption.INDEX)
                .field("name", DataType.STRING, 32, FieldOption.NOT_NULL, FieldOption.UNIQUE, FieldOption.INDEX)
                .field("firstPlayed", DataType.LONG, FieldOption.NOT_NULL)
                .field("lastPlayed", DataType.LONG, FieldOption.NOT_NULL)
                .field("gameProfile", DataType.LONG_TEXT)
                .field("properties", DataType.LONG_TEXT)
                .create();
    }

    public DatabaseCollection getPlayerDataStorage() {
        return playerDataStorage;
    }

    @Override
    public MinecraftPlayerData getPlayerData(String name) {
        Objects.requireNonNull(name);
        return getPlayerDataByQueryResult(this.playerDataStorage.find().where("name", name).execute());
    }

    @Override
    public MinecraftPlayerData getPlayerData(UUID uniqueId) {
        Objects.requireNonNull(uniqueId);
        return getPlayerDataByQueryResult(this.playerDataStorage.find().where("uniqueId", uniqueId).execute());
    }

    @Override
    public MinecraftPlayerData getPlayerData(long xBoxId) {
        Validate.isTrue(xBoxId == 0,"XBoxId can't be 0");
        return getPlayerDataByQueryResult(this.playerDataStorage.find().where("xBoxId", xBoxId).execute());
    }

    private MinecraftPlayerData getPlayerDataByQueryResult(QueryResult result) {
        if(result.isEmpty()) return null;
        QueryResultEntry entry = result.first();
        String name = entry.getString("name");
        UUID uniqueId = entry.getUniqueId("uniqueId");
        return new DefaultMinecraftPlayerData(this,
                name,
                uniqueId,
                entry.getLong("xBoxId"),
                entry.getLong("firstPlayed"),
                entry.getLong("lastPlayed"),
                GameProfile.fromJsonPart(uniqueId,name,entry.getString("gameProfile")),
                DocumentFileType.JSON.getReader().read(entry.getString("properties")));
    }

    @Override
    public MinecraftPlayerData createPlayerData(String name, UUID uniqueId, long xBoxId, long firstPlayed, long lastPlayed, GameProfile gameProfile) {
        InsertQuery insertQuery = this.playerDataStorage.insert()
                .set("uniqueId", uniqueId)
                .set("xBoxId", xBoxId)
                .set("name", name)
                .set("firstPlayed", firstPlayed)
                .set("lastPlayed", lastPlayed)
                .set("gameProfile", DocumentFileType.JSON.getWriter().write(Document.newDocument(gameProfile.getProperties()), false));
        insertQuery.execute();
        return new DefaultMinecraftPlayerData(this, name, uniqueId, xBoxId, firstPlayed, lastPlayed, gameProfile, Document.newDocument());
    }
}
