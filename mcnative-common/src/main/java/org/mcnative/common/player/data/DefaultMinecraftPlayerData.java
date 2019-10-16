/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 16.10.19, 20:53
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

import net.prematic.libraries.document.Document;
import org.mcnative.common.player.profile.GameProfile;

import java.util.UUID;

public class DefaultMinecraftPlayerData implements MinecraftPlayerData {

    private final int id;
    private final String name;
    private final UUID uniqueId;
    private final long xBoxId, firstPlayed, lastPlayed;
    private final GameProfile gameProfile;
    private final Document properties;

    public DefaultMinecraftPlayerData(int id, String name, UUID uniqueId, long xBoxId, long firstPlayed, long lastPlayed, GameProfile gameProfile, Document properties) {
        this.id = id;
        this.name = name;
        this.uniqueId = uniqueId;
        this.xBoxId = xBoxId;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.gameProfile = gameProfile;
        this.properties = properties;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public UUID getUniqueId() {
        return this.uniqueId;
    }

    @Override
    public long getXBoxId() {
        return this.xBoxId;
    }

    @Override
    public long getFirstPlayed() {
        return this.firstPlayed;
    }

    @Override
    public long getLastPlayed() {
        return this.lastPlayed;
    }

    @Override
    public GameProfile getGameProfile() {
        return this.gameProfile;
    }

    @Override
    public Document getProperties() {
        return this.properties;
    }

    @Override
    public void updateLastPlayed(long timeStamp) {

    }

    @Override
    public void updateName(String name) {

    }

    @Override
    public void updateGameProfile(GameProfile profile) {

    }

    @Override
    public void updateProperties() {

    }
}
