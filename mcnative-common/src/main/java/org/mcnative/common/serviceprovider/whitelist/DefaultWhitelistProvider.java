/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 19:41
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

package org.mcnative.common.serviceprovider.whitelist;

import net.prematic.databasequery.api.DatabaseCollection;
import net.prematic.databasequery.api.datatype.DataType;
import net.prematic.databasequery.api.query.option.CreateOption;
import net.prematic.databasequery.api.query.result.QueryResult;
import net.prematic.databasequery.api.query.result.QueryResultEntry;
import org.mcnative.common.McNative;
import org.mcnative.common.player.MinecraftPlayer;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultWhitelistProvider implements WhitelistProvider {

    private final DatabaseCollection whitelistSettings, whitelistedPlayers;
    private final Collection<MinecraftPlayer> whitelistedPlayersCache;
    private boolean enabled;

    public DefaultWhitelistProvider() {
        this.whitelistSettings = McNative.getInstance().getStorageManager().getDatabase(McNative.getInstance())
                .createCollection("WhitelistSettings")
                .attribute("enabled", DataType.STRING, CreateOption.NOT_NULL)
                .create();
        this.whitelistedPlayers = McNative.getInstance().getStorageManager().getDatabase(McNative.getInstance())
                .createCollection("WhitelistedPlayers")
                .attribute("playerId", DataType.INTEGER, CreateOption.NOT_NULL)
                .create();
        this.whitelistedPlayersCache = loadWhitelistedPlayers();
        this.enabled = loadWhitelistEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.whitelistSettings.update().set("enabled", enabled).execute();
    }

    @Override
    public Collection<MinecraftPlayer> getPlayers() {
        return this.whitelistedPlayersCache;
    }

    @Override
    public boolean isWhitelisted(MinecraftPlayer player) {
        return this.whitelistedPlayersCache.contains(player);
    }

    @Override
    public void set(MinecraftPlayer player, boolean whitelisted) {
        if(whitelisted && !isWhitelisted(player)) this.whitelistedPlayers.insert().set("playerId", player.getId()).execute();
        else if(!whitelisted) this.whitelistedPlayers.delete().where("playerId", player.getId()).execute();
    }

    @Override
    public void add(MinecraftPlayer player) {
        set(player, true);
    }

    @Override
    public void remove(MinecraftPlayer player) {
        set(player, false);
    }

    private Collection<MinecraftPlayer> loadWhitelistedPlayers() {
        Collection<MinecraftPlayer> whitelistedPlayers = new ArrayList<>();
        for (QueryResultEntry resultEntry : this.whitelistedPlayers.find().execute()) {
            whitelistedPlayers.add(McNative.getInstance().getPlayerManager().getPlayer(resultEntry.getInt("playerId")));
        }
        return whitelistedPlayers;
    }

    private boolean loadWhitelistEnabled() {
        QueryResult result = this.whitelistSettings.find().execute();
        if(result.isEmpty()) {
            this.whitelistSettings.insert().set("enabled", false).execute();
            return false;
        }
        return result.first().getBoolean("enabled");
    }
}