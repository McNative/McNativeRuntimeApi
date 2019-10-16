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
import org.mcnative.common.McNative;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DefaultPunishmentHandler implements PunishmentHandler {

    private static int TYPE_BAN = 1;
    private static int TYPE_MUTE = 2;

    private final DatabaseCollection punishmentStorage;

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
    }

    @Override
    public Collection<MinecraftPlayer> getBanList() {
        return getPunishmentListByType(TYPE_BAN);
    }

    @Override
    public Collection<MinecraftPlayer> getMuteList() {
        return getPunishmentListByType(TYPE_MUTE);
    }

    private Collection<MinecraftPlayer> getPunishmentListByType(int type) {
        try {
            Collection<MinecraftPlayer> players = new ArrayList<>();
            QueryResult result = this.punishmentStorage.find().get("playerId").where("type", type).execute().get();
            for (QueryResultEntry entry : result) {
                players.add(McNative.getInstance().getPlayerManager().getPlayer(entry.getInt("playerId")));
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
        try {
            return !this.punishmentStorage.find().where("playerId", id).where("type", type).execute().get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Can't get punishment data by id %s.", id));
        }
    }

    private String getPunishReason(int id, int type) {
        try {
            return this.punishmentStorage.find().where("playerId", id).where("type", type).execute().get().first().getString("reason");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Can't get punishment data by id %s.", id));
        }
    }

    private void punish(int id, String reason, long time, TimeUnit unit, int type) {
        this.punishmentStorage.insert()
                .set("playerId", id)
                .set("type", type)
                .set("since", System.currentTimeMillis())
                .set("duration", unit.toMillis(time))
                .set("reason", reason);
    }

    private void unpunish(int id, int type) {
        this.punishmentStorage.delete()
                .where("playerId", id)
                .where("type", type)
                .execute();
    }
}
