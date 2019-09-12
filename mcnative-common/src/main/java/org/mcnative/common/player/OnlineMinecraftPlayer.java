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

import org.mcnative.common.MinecraftConnection;
import org.mcnative.common.player.bossbar.BossBar;
import org.mcnative.common.player.scoreboard.BelowNameInfo;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.player.scoreboard.sidebar.Sidebar;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.MessageComponent;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.TextComponent;
import org.mcnative.common.text.variable.Variable;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;


public interface OnlineMinecraftPlayer extends MinecraftPlayer, MinecraftConnection {

    DeviceInfo getDevice();

    MinecraftProtocolVersion getClientVersion();

    String getClientName();

    boolean isOnlineMode();

    PlayerSettings getSettings();

    int getPing();


    //Scoreboard

    Sidebar getSidebar();

    void setSidebar(Sidebar sidebar);

    Tablist getTablist();

    void setTablist(Tablist tablist);

    BelowNameInfo getBelowNameInfo();

    void setBelowNameInfo(BelowNameInfo info);


    default void kick(){
        kick("Kicked by an operator.");
    }

    void kick(String reason);


    void performCommand(String command);

    void chat(String message);


    void setTablistHeader(String header);

    void setTablistFooter(String footer);


    Collection<Receivers> getChatChannels();

    void addChatChannel(Receivers channel);

    void removeChatChannel(Receivers channel);


    void sendMessage(String message);

    void sendMessage(MessageComponent... components);


    default void sendMessage(MessageComponent component, Variable... variables){
        sendMessage(component,new VariableSet(Arrays.asList(variables)));
    }

    void sendMessage(MessageComponent component, VariableSet variables);


    default void sendMessageKey(String key, Variable... variables){
        sendMessageKey(key,new VariableSet(Arrays.asList(variables)));
    }

    default void sendMessageKey(String key, VariableSet variables){
        sendMessage(Text.ofKey(key),variables);
    }



    void sendTitle(String title, String subTitle, int stayTime);

    void sendTitle(TextComponent title, MessageComponent subTitle, int stayTime);

    void sendTitle(Title title);

    void resetTitle();


    void sendActionbar(MessageComponent... message);

    void sendActionbar(long seconds, MessageComponent... message);


    Collection<BossBar> getActiveBossBars();

    void addBossBar(BossBar bossBar);

    void removeBossBar(BossBar bossBar);


    void playNote(Instrument instrument, Note note);

    void playSound(Sound sound, SoundCategory category, float volume, float pitch);

    void stopSound(Sound sound);

    void stopSound(String sound, SoundCategory category);

    void setResourcePack(String url);

    void setResourcePack(String url, String hash);



    void check(Consumer<ProtocolCheck> checker);

    /*
        Xp
        Tablist

     */

}
