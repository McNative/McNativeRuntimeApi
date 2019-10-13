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

import net.prematic.libraries.command.sender.CommandSender;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.player.bossbar.BossBar;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.common.player.scoreboard.BelowNameInfo;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.player.scoreboard.sidebar.Sidebar;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Collection;
import java.util.function.Consumer;


public interface OnlineMinecraftPlayer extends MinecraftPlayer, MinecraftConnection, CommandSender {

    DeviceInfo getDevice();

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

    default void kick(String reason){
        kick(Text.of(reason));
    }

    default void kick(MessageComponent message){
        kick(message,VariableSet.newEmptySet());
    }

    default void kick(MessageComponent message,VariableSet variables){
        disconnect(message, variables);
    }

    void performCommand(String command);

    void chat(String message);


    Collection<ReceiverChannel> getChatChannels();

    void addChatChannel(ReceiverChannel channel);

    void removeChatChannel(ReceiverChannel channel);


    default void sendMessage(String message){
        sendMessage(Text.of(message));
    }

    default void sendMessage(MessageComponent component){
        sendMessage(component,VariableSet.newEmptySet());
    }

    void sendMessage(MessageComponent component, VariableSet variables);

    default void sendSystemMessage(String message){
        sendSystemMessage(Text.of(message));
    }

    default void sendSystemMessage(MessageComponent component){
        sendSystemMessage(component,VariableSet.newEmptySet());
    }

    void sendSystemMessage(MessageComponent component, VariableSet variables);

    default void sendTitle(String title, String subTitle, int stayTime){
        sendTitle(Text.of(title),Text.of(subTitle),stayTime);
    }

    default void sendTitle(MessageComponent title, MessageComponent subTitle, int stayTime){
        sendTitle(title, subTitle, stayTime,VariableSet.newEmptySet());
    }

    default void sendTitle(MessageComponent title, MessageComponent subTitle, int stayTime,VariableSet variables){
        sendTitle(Title.newTitle().title(title).title(subTitle).stayTime(stayTime).variables(variables));
    }


    void sendTitle(Title title);

    void resetTitle();


    default void sendActionbar(MessageComponent message){
        sendActionbar(message,VariableSet.newEmptySet());
    }

    void sendActionbar(MessageComponent message,VariableSet variables);

    default void sendActionbar(MessageComponent message,long stayTime){
        sendActionbar(message,VariableSet.newEmptySet(), stayTime);
    }

    void sendActionbar(MessageComponent message,VariableSet variables,long staySeconds);




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

}
