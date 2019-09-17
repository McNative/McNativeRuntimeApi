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
import org.mcnative.common.player.*;
import org.mcnative.common.player.data.PlayerDataStorageHandler;
import org.mcnative.common.player.permission.PermissionHandler;
import org.mcnative.common.player.profile.GameProfileLoader;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketManager;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.registry.Registry;
import org.mcnative.common.text.TextComponent;

import java.util.Collection;
import java.util.function.Consumer;

public interface McNative<P extends OnlineMinecraftPlayer> {

    String getServiceName();

    MinecraftPlatform getPlatform();

    PrematicLogger getLogger();

    Registry getRegistry();

    TaskScheduler getScheduler();

    CommandManager getCommandManager();

    EventManager getEventManager();

    PluginManager getPluginManager();

    PacketManager getPacketManager();

    PlayerManager<P> getPlayerManager();



    PermissionHandler getPermissionHandler();

    void setPermissionHandler(PermissionHandler handler);


    PunishmentHandler getPunishmentHandler();

    void setPunishmentHandler(PunishmentHandler handler);

    WhitelistHandler getWhitelistHandler();

    void setWhitelistHandler(WhitelistHandler handler);

    PlayerDataStorageHandler getPlayerDataStorageHandler();

    void setPlayerDataStorageHandler(PlayerDataStorageHandler handler);

    GameProfileLoader getGameProfileLoader();

    void setGameProfileLoader(GameProfileLoader loader);



    ReceiverChannel getServerChat();

    void setServerChat(ReceiverChannel channel);


    Tablist getDefaultTablist();

    void setDefaultTablist(Tablist tablist);


    void broadcast(String message);

    void broadcast(TextComponent... components);

    void broadcast(String permission, String message);

    void broadcast(String permission,TextComponent... components);


    Collection<String> getOpenChannels();

    void registerChannel(String name, Plugin owner, PluginMessageListener listener);

    void unregisterChannel(String name);

    void unregisterChannel(PluginMessageListener listener);

    void unregisterChannels(Plugin owner);


    void broadcastPacket(MinecraftPacket packet);

    void broadcastPacket(MinecraftPacket packet, String permission);


    ServerPingResponse getPingResponse();

    void setPingResponse(ServerPingResponse ping);

    void shutdown();

    void restart();


    default void check(Consumer<ProtocolCheck> checker){
        getPlatform().check(checker);
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