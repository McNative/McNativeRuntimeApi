/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.07.19 22:26
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

package net.prematic.mcnative.common.player;

import java.awt.*;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;

public interface MinecraftPlayer {

    String getClientVersion();

    String getName();

    String getDisplayName();

    UUID getUniqueId();

    Locale getLocale();

    int getPing();

    byte getViewDistance();

    boolean hasPermission(String permission);

    boolean hasPermissionGroup(String group);

    void sendMessage(String message);

    void sendMessage(TextComponent... components);

    void sendTitle(String title, String subTitle, short duration);

    void sendTitle(String TextComponent, TextComponent subTitle, short duration);

    void sendActionbar(TextComponent message, short duration);

    BossBar getBossBar();

    void sendData(String channel, InputStream stream);

    void sendData(String channel,String data);

    void sendData(String channel, byte[] data);
}
