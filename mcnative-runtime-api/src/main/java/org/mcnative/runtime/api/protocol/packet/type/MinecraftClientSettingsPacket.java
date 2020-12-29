/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.01.20, 19:11
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

package org.mcnative.runtime.api.protocol.packet.type;

import org.mcnative.runtime.api.player.PlayerClientSettings;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;

public class MinecraftClientSettingsPacket implements MinecraftPacket {

    private String languageTag;
    private PlayerClientSettings settings;


    public String getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    public PlayerClientSettings getSettings() {
        return settings;
    }

    public void setSettings(PlayerClientSettings settings) {
        this.settings = settings;
        this.languageTag = settings.getLocale().toLanguageTag();
    }
}
