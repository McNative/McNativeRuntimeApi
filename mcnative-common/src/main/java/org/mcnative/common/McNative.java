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

package org.mcnative.common;

import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.concurrent.TaskScheduler;
import net.prematic.libraries.event.EventManager;
import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.manager.PluginManager;
import org.mcnative.common.messaging.PluginMessageListener;
import org.mcnative.common.player.chat.ChatChannel;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.text.TextComponent;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.registry.Registry;

import java.util.Collection;
import java.util.UUID;

public interface McNative {

    MinecraftPlatform getPlatform();

    PrematicLogger getLogger();

    Registry getRegistry();

    TaskScheduler getScheduler();

    CommandManager getCommandManager();

    EventManager getEventManager();

    PluginManager<McNative> getPluginManager();


    ChatChannel getServerChat();

    void setServerChat(ChatChannel channel);


    Tablist getDefaultTablist();

    void setDefaultTablist(Tablist tablist);


    int getOnlineCount();

    MinecraftPlayer getPlayer(UUID uniqueId);

    MinecraftPlayer getPlayer(long xBoxId);

    MinecraftPlayer getPlayer(String name);

    OnlineMinecraftPlayer getOnlinePlayer(UUID uniqueId);

    OnlineMinecraftPlayer getOnlinePlayer(long xBoxId);

    OnlineMinecraftPlayer getOnlinePlayer(String name);

    Collection<OnlineMinecraftPlayer> getOnlinePlayers();


    void broadcast(String message);

    void broadcast(TextComponent... components);

    void broadcast(String permission, String message);

    void broadcast(String permission,TextComponent... components);


    Collection<String> getOpenChannels();

    void registerChannel(String name, Plugin owner, PluginMessageListener listener);

    void unregisterChannel(String name);

    void unregisterChannel(PluginMessageListener listener);

    void unregisterChannels(Plugin owner);


    ServerPingResponse getPingResponse();

    void setPingResponse(ServerPingResponse ping);


    void shutdown();

    void restart();


    default ProtocolCheck check(){
        return getPlatform().check();
    }

    static boolean isAvailable(){
        return getInstance() != null;
    }

    static McNative getInstance() {
        return InstanceHolder.INSTANCE;
    }

    static void setInstance(McNative instance) {
        if(InstanceHolder.INSTANCE != null) throw new IllegalArgumentException("Instance is already set.");
        InstanceHolder.INSTANCE = instance;
    }

    class InstanceHolder {

        private static McNative INSTANCE;
    }
}