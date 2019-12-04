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

package org.mcnative.common.serviceprovider.punishment;

import net.prematic.databasequery.api.DatabaseCollection;
import net.prematic.databasequery.api.ForeignKey;
import net.prematic.databasequery.api.datatype.DataType;
import net.prematic.databasequery.api.query.option.CreateOption;
import net.prematic.databasequery.api.query.result.QueryResult;
import net.prematic.databasequery.api.query.result.QueryResultEntry;
import net.prematic.libraries.caching.ArrayCache;
import net.prematic.libraries.caching.Cache;
import org.mcnative.common.McNative;
import org.mcnative.common.player.MinecraftPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class DefaultPunishmentProvider implements PunishmentProvider {

    private static int TYPE_BAN = 1;
    private static int TYPE_MUTE = 2;

    private final DatabaseCollection punishmentStorage;
    private final Cache<PunishEntry> punishEntryCache;

    public DefaultPunishmentProvider() {
        this.punishmentStorage = McNative.getInstance().getConfigurationProvider().getDatabase(McNative.getInstance())
                .createCollection("Punishment")
                .attribute("id", DataType.INTEGER, CreateOption.AUTO_INCREMENT, CreateOption.PRIMARY_KEY, CreateOption.INDEX)
                .attribute("playerId", DataType.INTEGER, -1, null, new ForeignKey(McNative.getInstance().getName(), "PlayerData", "id", ForeignKey.Option.CASCADE, null), CreateOption.UNIQUE, CreateOption.INDEX)
                .attribute("type", DataType.INTEGER, CreateOption.NOT_NULL, CreateOption.INDEX)
                .attribute("since", DataType.LONG, CreateOption.NOT_NULL)
                .attribute("duration", DataType.LONG, CreateOption.NOT_NULL)
                .attribute("reason", DataType.LONG_TEXT).create();

        this.punishEntryCache = new ArrayCache<PunishEntry>()
                .setExpireAfterAccess(30, TimeUnit.MINUTES)
                .setRemoveListener(entry -> McNative.getInstance().getPlayerManager().getOnlinePlayer(entry.playerId) != null)
                .registerQuery("playerId", (entry, objects) -> entry.playerId == (int) objects[0] && entry.type == (int) objects[1]);
    }

    @Override
    public Collection<MinecraftPlayer> getBanList() {
        return getPunishmentList(TYPE_BAN);
    }

    @Override
    public Collection<MinecraftPlayer> getMuteList() {
        return getPunishmentList(TYPE_MUTE);
    }

    private Collection<MinecraftPlayer> getPunishmentList(int type) {

        Collection<MinecraftPlayer> players = new ArrayList<>();
        QueryResult result = this.punishmentStorage.find().get("playerId").where("type", type).execute();
        for (QueryResultEntry resultEntry : result) {
            players.add(McNative.getInstance().getPlayerManager().getPlayer(resultEntry.getInt("playerId")));
            cachePunishEntry(resultEntry);
        }
        return players;

    }

    @Override
    public boolean isBanned(MinecraftPlayer player) {
        return isPunished(player.getId(), TYPE_BAN);
    }

    @Override
    public String getBanReason(MinecraftPlayer player) {
        return getPunishReason(player.getId(), TYPE_BAN);
    }

    @Override
    public void ban(MinecraftPlayer player, String reason) {
        punish(player.getId(), reason, -1, TimeUnit.MILLISECONDS, TYPE_BAN);
    }

    @Override
    public void ban(MinecraftPlayer player, String reason, long time, TimeUnit unit) {
        punish(player.getId(), reason, time, unit, TYPE_BAN);
    }

    @Override
    public void unban(MinecraftPlayer player) {
        unpunish(player.getId(), TYPE_BAN);
    }

    @Override
    public boolean isMuted(MinecraftPlayer player) {
        return isPunished(player.getId(), TYPE_MUTE);
    }

    @Override
    public String getMuteReason(MinecraftPlayer player) {
        return getPunishReason(player.getId(), TYPE_MUTE);
    }

    @Override
    public void mute(MinecraftPlayer player, String reason) {
        punish(player.getId(), reason, -1, TimeUnit.MILLISECONDS, TYPE_BAN);
    }

    @Override
    public void mute(MinecraftPlayer player, String reason, long time, TimeUnit unit) {
        punish(player.getId(), reason, time, unit, TYPE_MUTE);
    }

    @Override
    public void unmute(MinecraftPlayer player) {
        unpunish(player.getId(), TYPE_MUTE);
    }

    private boolean isPunished(int id, int type) {
        PunishEntry punishEntry = this.punishEntryCache.get("playerIdAndTypeId", id, type);
        if(punishEntry != null) return true;

        QueryResult result = this.punishmentStorage.find().where("playerId", id).where("type", type).execute();
        if(result.isEmpty()) return false;
        QueryResultEntry resultEntry = result.first();
        cachePunishEntry(resultEntry);
        return true;

    }

    private String getPunishReason(int id, int type) {
        PunishEntry punishEntry = this.punishEntryCache.get("playerIdAndTypeId", id, type);
        if(punishEntry != null) return punishEntry.reason;

        QueryResult result = this.punishmentStorage.find().where("playerId", id).where("type", type).execute();
        if(result.isEmpty()) return null;
        QueryResultEntry resultEntry = result.first();
        cachePunishEntry(resultEntry);
        return resultEntry.getString("reason");

    }

    private void punish(int playerId, String reason, long time, TimeUnit unit, int type) {
        long since = System.currentTimeMillis();
        int id = this.punishmentStorage.insert()
                .set("playerId", playerId)
                .set("type", type)
                .set("since", since)
                .set("duration", unit.toMillis(time))
                .set("reason", reason)
                .executeAndGetGeneratedKeys("id")
                .first().getInt("id");
        this.punishEntryCache.insert(new PunishEntry(id, playerId, type, since, unit.toMillis(time), reason));

    }

    private void unpunish(int id, int type) {
        this.punishmentStorage.delete()
                .where("playerId", id)
                .where("type", type)
                .execute();
        this.punishEntryCache.remove("playerIdAndTypeId", id, type);
    }

    private PunishEntry cachePunishEntry(QueryResultEntry resultEntry) {
        return new PunishEntry(resultEntry.getInt("id"),
                resultEntry.getInt("playerId"),
                resultEntry.getInt("type"),
                resultEntry.getLong("since"),
                resultEntry.getLong("duration"),
                resultEntry.getString("reason"));
    }

    public static class PunishEntry {

        final int id, playerId, type;
        final long since, duration;
        final String reason;

        public PunishEntry(int id, int playerId, int type, long since, long duration, String reason) {
            this.id = id;
            this.playerId = playerId;
            this.type = type;
            this.since = since;
            this.duration = duration;
            this.reason = reason;
        }
    }
}