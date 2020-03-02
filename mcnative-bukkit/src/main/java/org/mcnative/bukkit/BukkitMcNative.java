/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.01.20, 20:21
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

package org.mcnative.bukkit;

import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.concurrent.TaskScheduler;
import net.prematic.libraries.concurrent.simple.SimpleTaskScheduler;
import net.prematic.libraries.dependency.DependencyManager;
import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.logging.bridge.JdkPrematicLogger;
import net.prematic.libraries.logging.bridge.slf4j.SLF4JStaticBridge;
import net.prematic.libraries.message.MessageProvider;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.libraries.plugin.service.ServiceRegistry;
import net.prematic.libraries.utility.GeneralUtil;
import org.bukkit.Bukkit;
import org.mcnative.bukkit.network.PluginMessageGateway;
import org.mcnative.bukkit.player.permission.BukkitPermissionProvider;
import org.mcnative.bukkit.plugin.command.McNativeCommand;
import org.mcnative.common.LocalService;
import org.mcnative.common.McNative;
import org.mcnative.common.MinecraftPlatform;
import org.mcnative.common.ObjectCreator;
import org.mcnative.common.network.Network;
import org.mcnative.common.network.messaging.MessagingProvider;
import org.mcnative.common.player.PlayerManager;
import org.mcnative.common.player.data.DefaultPlayerDataProvider;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.common.plugin.configuration.ConfigurationProvider;
import org.mcnative.common.plugin.configuration.DefaultConfigurationProvider;
import org.mcnative.common.serviceprovider.message.DefaultMessageProvider;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;

import java.io.File;
import java.util.concurrent.ExecutorService;

public class BukkitMcNative implements McNative {

    private final MinecraftPlatform platform;
    private final PrematicLogger logger;
    private final TaskScheduler scheduler;
    private final CommandSender consoleSender;

    private final PluginManager pluginManager;
    private final DependencyManager dependencyManager;
    private final PlayerManager playerManager;
    private final LocalService local;

    private Network network;

    protected BukkitMcNative(PluginManager pluginManager, PlayerManager playerManager, LocalService local, Network network) {
        this.platform = new BukkitPlatform();
        this.logger = new JdkPrematicLogger(Bukkit.getLogger());
        this.scheduler = new SimpleTaskScheduler();
        this.dependencyManager = new DependencyManager(this.logger,new File("McNative/lib/dependencies/"));

        this.consoleSender = new McNativeCommand.MappedCommandSender(Bukkit.getConsoleSender());
        this.pluginManager = pluginManager;
        this.playerManager = playerManager;
        this.local = local;
        this.network = network;

        SLF4JStaticBridge.trySetLogger(logger);
    }

    @Override
    public String getServiceName() {
        return Bukkit.getName();
    }

    @Override
    public MinecraftPlatform getPlatform() {
        return platform;
    }

    @Override
    public PrematicLogger getLogger() {
        return logger;
    }

    @Override
    public ServiceRegistry getRegistry() {
        return pluginManager;
    }

    @Override
    public TaskScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public CommandSender getConsoleSender() {
        return consoleSender;
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public DependencyManager getDependencyManager() {
        return dependencyManager;
    }

    @Override
    public ObjectCreator getObjectCreator() {
        return null;
    }

    @Override
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public ExecutorService getExecutorService() {
        return GeneralUtil.getDefaultExecutorService();
    }

    @Override
    public boolean isNetworkAvailable() {
        return network != null;
    }

    @Override
    public Network getNetwork() {
        return network;
    }

    @Override
    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public LocalService getLocal() {
        return local;
    }

    @Override
    public void shutdown() {
        Bukkit.shutdown();
    }

    public void registerDefaultProviders(){
        pluginManager.registerService(this, ConfigurationProvider.class,new DefaultConfigurationProvider());
        pluginManager.registerService(this, PlayerDataProvider.class,new DefaultPlayerDataProvider());
        pluginManager.registerService(this, MessageProvider.class,new DefaultMessageProvider());
        pluginManager.registerService(this, PermissionProvider.class,new BukkitPermissionProvider());
        pluginManager.registerService(this, MessagingProvider.class,new PluginMessageGateway(getExecutorService()));
    }

}
