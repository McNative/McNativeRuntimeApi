/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 28.12.19, 22:26
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

package org.mcnative.runtime.api.player;

import net.pretronic.libraries.command.sender.CommandSender;
import org.mcnative.runtime.api.connection.MinecraftConnection;
import org.mcnative.runtime.api.connection.PendingConnection;
import org.mcnative.runtime.api.player.bossbar.BossBar;
import org.mcnative.runtime.api.player.chat.ChatChannel;
import org.mcnative.runtime.api.player.scoreboard.BelowNameInfo;
import org.mcnative.runtime.api.player.scoreboard.sidebar.Sidebar;
import org.mcnative.runtime.api.player.tablist.Tablist;

import java.util.Collection;

public interface ConnectedMinecraftPlayer extends OnlineMinecraftPlayer, MinecraftConnection, CommandSender{

    PendingConnection getConnection();

    PlayerClientSettings getClientSettings();


    ChatChannel getPrimaryChatChannel();

    void setPrimaryChatChannel(ChatChannel channel);


    Sidebar getSidebar();

    void setSidebar(Sidebar sidebar);


    Tablist getTablist();

    void setTablist(Tablist tablist);


    Collection<BossBar> getActiveBossBars();

    void addBossBar(BossBar bossBar);

    void removeBossBar(BossBar bossBar);


    BelowNameInfo getBelowNameInfo();

    void setBelowNameInfo(BelowNameInfo info);


    void sendResourcePackRequest(String url);

    void sendResourcePackRequest(String url, String hash);


    @Override
    default boolean isPlayer(){
        return true;
    }

}
