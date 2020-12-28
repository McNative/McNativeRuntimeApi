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

import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.concurrent.TaskScheduler;
import net.pretronic.libraries.dependency.DependencyManager;
import net.pretronic.libraries.logging.PretronicLogger;
import net.pretronic.libraries.plugin.description.PluginVersion;
import net.pretronic.libraries.plugin.manager.PluginManager;
import net.pretronic.libraries.plugin.service.ServiceRegistry;
import net.pretronic.libraries.utility.annonations.Nullable;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.network.Network;
import org.mcnative.common.player.PlayerManager;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.rollout.RolloutConfiguration;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public interface McNative extends ObjectOwner {

    String CONSOLE_PREFIX = "[McNative] ";

    String getServiceName();

    RolloutConfiguration getRolloutConfiguration();

    McNativeServerIdentifier getMcNativeServerId();


    PluginVersion getVersion();

    boolean isReady();

    MinecraftPlatform getPlatform();

    PretronicLogger getLogger();

    ServiceRegistry getRegistry();

    TaskScheduler getScheduler();

    CommandSender getConsoleSender();

    PluginManager getPluginManager();

    DependencyManager getDependencyManager();

    ObjectCreator getObjectCreator();

    PlayerManager getPlayerManager();

    ExecutorService getExecutorService();

    /**
     * Check if this service instance belongs to a network.
     *
     * <p>Supported Networks:</p>
     * <p>BungeeCord</p>
     *
     * @return If a network communication is available
     */
    boolean isNetworkAvailable();

    @Nullable
    Network getNetwork();

    void setNetwork(Network network);


    LocalService getLocal();

    void shutdown();


    default void check(Consumer<ProtocolCheck> checker){
        getPlatform().check(checker);
    }

    @Override
    default String getName() {
        return "McNative";
    }


    static boolean isAvailable(){
        return getInstance() != null;
    }

    static McNative getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    static <T extends McNative> T getInstance(Class<T> instanceClass) {
        if(getInstance().getClass() != instanceClass) throw new IllegalArgumentException("McNative is not an instance of " + instanceClass.getName());
        return (T) getInstance();
    }

    static void setInstance(McNative instance) {
        if(InstanceHolder.INSTANCE != null && instance != null) throw new IllegalArgumentException("Instance is already set.");
        InstanceHolder.INSTANCE = instance;
    }

    class InstanceHolder {

        private static McNative INSTANCE;
    }
}
