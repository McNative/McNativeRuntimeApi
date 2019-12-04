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
import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.concurrent.TaskScheduler;
import net.prematic.libraries.event.EventBus;
import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.libraries.plugin.service.ServiceRegistry;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.serviceprovider.placeholder.PlaceHolderManager;
import org.mcnative.common.messaging.PluginMessageListener;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.PlayerManager;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.common.player.profile.GameProfileLoader;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.plugin.configuration.ConfigurationProvider;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketManager;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Collection;
import java.util.function.Consumer;

public interface McNative<P extends OnlineMinecraftPlayer> extends ObjectOwner {

    String CONSOLE_PREFIX = "[McNative] ";

    String getServiceName();

    MinecraftPlatform getPlatform();

    PrematicLogger getLogger();

    ServiceRegistry getRegistry();

    TaskScheduler getScheduler();

    EventBus getEventBus();

    CommandSender getConsoleSender();

    CommandManager getCommandManager();

    PluginManager getPluginManager();

    PacketManager getPacketManager();

    PlayerManager<P> getPlayerManager();


    PlayerDataProvider getPlayerDataProvider();

    ConfigurationProvider getConfigurationProvider();

    GameProfileLoader getGameProfileLoader();


    ReceiverChannel getServerChat();

    void setServerChat(ReceiverChannel channel);


    Tablist getDefaultTablist();

    void setDefaultTablist(Tablist tablist);



    default void broadcast(MessageComponent<?> component){
        broadcast(component,VariableSet.newEmptySet());
    }

    void broadcast(MessageComponent<?> component, VariableSet variables);

    default void broadcast(String permission,MessageComponent<?> component){
        broadcast(permission, component,VariableSet.newEmptySet());
    }

    void broadcast(String permission,MessageComponent<?> component, VariableSet variables);


    Collection<String> getOpenChannels();

    void registerChannel(String name, Plugin<?> owner, PluginMessageListener listener);

    void unregisterChannel(String name);

    void unregisterChannel(PluginMessageListener listener);

    void unregisterChannels(Plugin<?> owner);


    void broadcastPacket(MinecraftPacket packet);

    void broadcastPacket(MinecraftPacket packet, String permission);


    ServerPingResponse getPingResponse();

    void setPingResponse(ServerPingResponse ping);

    void shutdown();

    void restart();

    default void check(Consumer<ProtocolCheck> checker){
        getPlatform().check(checker);
    }

    @Override
    default String getName() {
        return ObjectOwner.SYSTEM.getName();
    }

    static boolean isAvailable(){
        return getInstance() != null;
    }

    static McNative<? extends OnlineMinecraftPlayer> getInstance() {
        return InstanceHolder.INSTANCE;
    }

    static void setInstance(McNative<? extends OnlineMinecraftPlayer> instance) {
        if(InstanceHolder.INSTANCE != null) throw new IllegalArgumentException("Instance is already set.");
        InstanceHolder.INSTANCE = instance;
    }

    class InstanceHolder {

        private static McNative<? extends OnlineMinecraftPlayer> INSTANCE;
    }
}