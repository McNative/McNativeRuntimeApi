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

import net.pretronic.databasequery.api.collection.DatabaseCollection;
import net.pretronic.databasequery.api.collection.field.FieldOption;
import net.pretronic.databasequery.api.datatype.DataType;
import net.pretronic.databasequery.api.query.result.QueryResult;
import net.pretronic.databasequery.api.query.result.QueryResultEntry;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.utility.Validate;
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
                .field("UniqueId", DataType.UUID, FieldOption.NOT_NULL,FieldOption.UNIQUE, FieldOption.INDEX)
                .field("XBoxId", DataType.LONG, FieldOption.NOT_NULL, FieldOption.INDEX)
                .field("Name", DataType.STRING, 32, FieldOption.NOT_NULL, FieldOption.UNIQUE, FieldOption.INDEX)
                .field("FirstPlayed", DataType.LONG, FieldOption.NOT_NULL)
                .field("LastPlayed", DataType.LONG, FieldOption.NOT_NULL)
                .field("GameProfile", DataType.LONG_TEXT)
                .field("Properties", DataType.LONG_TEXT)
                .create();
    }

    public DatabaseCollection getPlayerDataStorage() {
        return playerDataStorage;
    }

    @Override
    public MinecraftPlayerData getPlayerData(String name) {
        Objects.requireNonNull(name);
        return getPlayerDataByQueryResult(this.playerDataStorage.find().where("Name", name).execute());
    }

    @Override
    public MinecraftPlayerData getPlayerData(UUID uniqueId) {
        Objects.requireNonNull(uniqueId);
        return getPlayerDataByQueryResult(this.playerDataStorage.find().where("UniqueId", uniqueId).execute());
    }

    @Override
    public MinecraftPlayerData getPlayerData(long xBoxId) {
        Validate.isTrue(xBoxId == 0,"XBoxId can't be 0");
        return getPlayerDataByQueryResult(this.playerDataStorage.find().where("XBoxId", xBoxId).execute());
    }

    private MinecraftPlayerData getPlayerDataByQueryResult(QueryResult result) {
        if(result.isEmpty()) return null;
        QueryResultEntry entry = result.first();
        try{
            String name = entry.getString("Name");
            UUID uniqueId = entry.getUniqueId("UniqueId");
            return new DefaultMinecraftPlayerData(this,
                    name,
                    uniqueId,
                    entry.getLong("XBoxId"),
                    entry.getLong("FirstPlayed"),
                    entry.getLong("LastPlayed"),
                    GameProfile.fromJsonPart(uniqueId,name,entry.getString("GameProfile")),
                    DocumentFileType.JSON.getReader().read(entry.getString("Properties")));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MinecraftPlayerData createPlayerData(String name, UUID uniqueId, long xBoxId, long firstPlayed, long lastPlayed, GameProfile gameProfile) {
        this.playerDataStorage.insert()
                .set("UniqueId", uniqueId)
                .set("XBoxId", xBoxId)
                .set("Name", name)
                .set("FirstPlayed", firstPlayed)
                .set("LastPlayed", lastPlayed)
                .set("GameProfile", gameProfile.toJsonPart())
                .set("Properties","{}")
                .execute();
        return new DefaultMinecraftPlayerData(this, name, uniqueId, xBoxId, firstPlayed, lastPlayed, gameProfile, Document.newDocument());
    }
}
