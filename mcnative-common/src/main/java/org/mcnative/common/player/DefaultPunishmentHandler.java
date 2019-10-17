/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 16.10.19, 20:42
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

package org.mcnative.common.player;

import net.prematic.databasequery.core.DatabaseCollection;
import net.prematic.databasequery.core.ForeignKey;
import net.prematic.databasequery.core.datatype.DataType;
import net.prematic.databasequery.core.query.option.CreateOption;
import net.prematic.databasequery.core.query.result.QueryResult;
import net.prematic.databasequery.core.query.result.QueryResultEntry;
import net.prematic.libraries.caching.ArrayCache;
import net.prematic.libraries.caching.Cache;
import org.mcnative.common.McNative;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DefaultPunishmentHandler implements PunishmentHandler {

    private static int TYPE_BAN = 1;
    private static int TYPE_MUTE = 2;

    private final DatabaseCollection punishmentStorage;
    private final Cache<PunishEntry> punishEntryCache;

    public DefaultPunishmentHandler() {
        try {
            this.punishmentStorage = McNative.getInstance().getStorageManager().getDatabase(McNative.getInstance())
                    .createCollection("Punishment")
                    .attribute("id", DataType.INTEGER, CreateOption.AUTO_INCREMENT, CreateOption.PRIMARY_KEY, CreateOption.INDEX)
                    .attribute("playerId", DataType.INTEGER, -1, null, new ForeignKey(McNative.getInstance().getName(), "PlayerData", "id", ForeignKey.Option.CASCADE, null), CreateOption.UNIQUE, CreateOption.INDEX)
                    .attribute("type", DataType.INTEGER, CreateOption.NOT_NULL, CreateOption.INDEX)
                    .attribute("since", DataType.LONG, CreateOption.NOT_NULL)
                    .attribute("duration", DataType.LONG, CreateOption.NOT_NULL)
                    .attribute("reason", DataType.LONG_TEXT).create().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Can't create punishment handler.");
        }
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
        try {
            Collection<MinecraftPlayer> players = new ArrayList<>();
            QueryResult result = this.punishmentStorage.find().get("playerId").where("type", type).execute().get();
            for (QueryResultEntry resultEntry : result) {
                players.add(McNative.getInstance().getPlayerManager().getPlayer(resultEntry.getInt("playerId")));
                cachePunishEntry(resultEntry);
            }
            return players;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Can't get punishment data.");
        }
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
        try {
            QueryResult result = this.punishmentStorage.find().where("playerId", id).where("type", type).execute().get();
            if(result.isEmpty()) return false;
            QueryResultEntry resultEntry = result.first();
            cachePunishEntry(resultEntry);
            return true;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Can't get punishment data by id %s.", id));
        }
    }

    private String getPunishReason(int id, int type) {
        PunishEntry punishEntry = this.punishEntryCache.get("playerIdAndTypeId", id, type);
        if(punishEntry != null) return punishEntry.reason;
        try {
            QueryResult result = this.punishmentStorage.find().where("playerId", id).where("type", type).execute().get();
            if(result.isEmpty()) return null;
            QueryResultEntry resultEntry = result.first();
            cachePunishEntry(resultEntry);
            return resultEntry.getString("reason");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Can't get punishment data by id %s.", id));
        }
    }

    private void punish(int playerId, String reason, long time, TimeUnit unit, int type) {
        try {
            long since = System.currentTimeMillis();
            int id = this.punishmentStorage.insert()
                    .set("playerId", playerId)
                    .set("type", type)
                    .set("since", since)
                    .set("duration", unit.toMillis(time))
                    .set("reason", reason)
                    .executeAndGetGeneratedKeys("id")
                    .get().first().getInt("id");
            this.punishEntryCache.insert(new PunishEntry(id, playerId, type, since, unit.toMillis(time), reason));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Can't insert punishment data for player id %s.", playerId));
        }
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
