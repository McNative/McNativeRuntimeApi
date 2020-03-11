/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 22:15
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

import net.pretronic.libraries.document.Document;
import org.mcnative.common.player.profile.GameProfile;

import java.util.UUID;

public class DummyDataProvider implements PlayerDataProvider{

    @Override
    public MinecraftPlayerData getPlayerData(String name) {
        if(name.equalsIgnoreCase("Dkrieger")){
            return new DefaultMinecraftPlayerData(null,name,UUID.fromString("cb7f0812-1fbb-4715-976e-a81e52be4b67")
                    ,-1,-1,-1,null, Document.newDocument());
        }
        return null;
    }

    @Override
    public MinecraftPlayerData getPlayerData(UUID uniqueId) {
        if(uniqueId.equals(UUID.fromString("cb7f0812-1fbb-4715-976e-a81e52be4b67"))){
            return new DefaultMinecraftPlayerData(null,"Dkrieger",uniqueId
                    ,-1,-1,-1,null, Document.newDocument());
        }
        return null;
    }

    @Override
    public MinecraftPlayerData getPlayerData(long xBoxId) {
        return null;
    }

    @Override
    public MinecraftPlayerData createPlayerData(String name, UUID uniqueId, long xBoxId, long firstPlayed, long lastPlayed, GameProfile gameProfile) {
        return null;
    }
}
