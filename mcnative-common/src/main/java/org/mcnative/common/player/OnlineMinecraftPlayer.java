/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.support.ProtocolCheck;

import java.awt.*;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Locale;

public interface OnlineMinecraftPlayer extends MinecraftPlayer{

    DeviceInfo getDevice();

    MinecraftProtocolVersion getClientVersion();

    String getClientName();

    InetSocketAddress getAddress();

    Locale getLocale();

    int getPing();

    byte getViewDistance();

    BossBar getBossBar();

    ProtocolCheck check();


    default void kick(){
        kick("Kicked by an operator.");
    }

    void kick(String reason);


    void performCommand(String command);

    void chat(String message);


    void setTablistHeader(String header);

    void setTablistFooter(String footer);


    void sendMessage(String message);

    void sendMessage(TextComponent... components);


    void sendTitle(String title, String subTitle, short duration);

    void sendTitle(String TextComponent, TextComponent subTitle, short duration);

    void resetTitle();

    void sendActionbar(TextComponent message, short duration);

    void sendData(String channel, InputStream stream);

    void sendData(String channel,String data);

    void sendData(String channel, byte[] data);


    void playNote(Instrument instrument, Note note);

    void playSound(Sound sound, SoundCategory category, float volume, float pitch);

    void stopSound(Sound sound);

    void stopSound(String sound, SoundCategory category);

    void setResourcePack(String url);

    void setResourcePack(String url, String hash);


    /*
        Xp
        Tablist

     */

}
