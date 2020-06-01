/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.08.19, 15:38
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

import net.pretronic.libraries.caching.CacheStateAble;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.player.profile.GameProfile;

import java.util.UUID;

public interface MinecraftPlayerData extends CacheStateAble<MinecraftPlayerData> {

    String getName();

    UUID getUniqueId();

    long getXBoxId();

    long getFirstPlayed();

    long getLastPlayed();

    GameProfile getGameProfile();

    PlayerDesign getDesign();

    Language getLanguage();


    void updateName(String name);

    void updateGameProfile(GameProfile profile);

    void updateDesign(PlayerDesign design);

    void updateLanguage(Language language);

    void updateProperties();


    void updateLoginInformation(String name,GameProfile profile, long timeStamp);
}
