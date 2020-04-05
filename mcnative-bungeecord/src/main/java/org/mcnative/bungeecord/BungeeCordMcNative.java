/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 19:50
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

package org.mcnative.bungeecord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.concurrent.TaskScheduler;
import net.pretronic.libraries.concurrent.simple.SimpleTaskScheduler;
import net.pretronic.libraries.dependency.DependencyManager;
import net.pretronic.libraries.event.EventPriority;
import net.pretronic.libraries.logging.PretronicLogger;
import net.pretronic.libraries.logging.bridge.JdkPretronicLogger;
import net.pretronic.libraries.logging.bridge.slf4j.SLF4JStaticBridge;
import net.pretronic.libraries.message.MessageProvider;
import net.pretronic.libraries.plugin.description.PluginVersion;
import net.pretronic.libraries.plugin.manager.PluginManager;
import net.pretronic.libraries.plugin.service.ServiceRegistry;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.bungeecord.network.PluginMessageMessenger;
import org.mcnative.bungeecord.player.permission.BungeeCordPermissionProvider;
import org.mcnative.bungeecord.plugin.command.McNativeCommand;
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.bungeecord.server.BungeeCordServerStatusResponse;
import org.mcnative.common.LocalService;
import org.mcnative.common.McNative;
import org.mcnative.common.MinecraftPlatform;
import org.mcnative.common.ObjectCreator;
import org.mcnative.common.network.Network;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.network.messaging.Messenger;
import org.mcnative.common.player.PlayerManager;
import org.mcnative.common.player.data.DefaultPlayerDataProvider;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.common.plugin.configuration.ConfigurationProvider;
import org.mcnative.common.plugin.configuration.DefaultConfigurationProvider;
import org.mcnative.common.serviceprovider.message.DefaultMessageProvider;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;
import org.mcnative.common.serviceprovider.placeholder.McNativePlaceholderProvider;
import org.mcnative.common.serviceprovider.placeholder.PlaceholderProvider;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class BungeeCordMcNative implements McNative {

    private final PluginVersion version;
    private final MinecraftPlatform platform;
    private final PretronicLogger logger;
    private final TaskScheduler scheduler;
    private final CommandSender consoleSender;
    private final ObjectCreator creator;

    private final PluginManager pluginManager;
    private final DependencyManager dependencyManager;
    private final PlayerManager playerManager;
    private final LocalService local;

    private Network network;
    private boolean ready;

    public BungeeCordMcNative(PluginVersion version,PluginManager pluginManager, PlayerManager playerManager, Network network, LocalService local) {
        this.version = version;
        this.platform = new BungeeCordPlatform();
        this.logger = new JdkPretronicLogger(ProxyServer.getInstance().getLogger());
        this.scheduler = new SimpleTaskScheduler();
        this.consoleSender = new McNativeCommand.MappedCommandSender(ProxyServer.getInstance().getConsole());
        this.dependencyManager = new DependencyManager(logger,new File("plugins/McNative/lib/dependencies"));
        this.creator = new BungeeObjectCreator();

        this.pluginManager = pluginManager;
        this.playerManager = playerManager;
        this.network = network;
        this.local = local;

        SLF4JStaticBridge.trySetLogger(logger);
    }

    @Override
    public String getServiceName() {
        return ProxyServer.getInstance().getName();
    }

    @Override
    public PluginVersion getVersion() {
        return version;
    }

    @Override
    public MinecraftPlatform getPlatform() {
        return platform;
    }

    @Override
    public PretronicLogger getLogger() {
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
        return creator;
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
        return true;
    }

    @Override
    public Network getNetwork() {
        return network;
    }

    @Override
    public void setNetwork(Network network) {
        Validate.notNull(network);
        this.network = network;
    }

    @Override
    public LocalService getLocal() {
        return local;
    }

    @Override
    public void shutdown() {
        ProxyServer.getInstance().stop();
    }

    @Override
    public boolean isReady() {
        return ready;
    }
    protected void setReady(boolean ready) {
        this.ready = ready;
    }

    protected void registerDefaultProviders(){
        pluginManager.registerService(this, ConfigurationProvider.class,new DefaultConfigurationProvider());
        pluginManager.registerService(this, PlayerDataProvider.class,new DefaultPlayerDataProvider());
        pluginManager.registerService(this, MessageProvider.class,new DefaultMessageProvider());
        pluginManager.registerService(this, PermissionProvider.class,new BungeeCordPermissionProvider());
        pluginManager.registerService(this, PlaceholderProvider.class,new McNativePlaceholderProvider(), EventPriority.LOW);
    }

    protected void registerDefaultCommands() {
        getLocal().getCommandManager().registerCommand(new org.mcnative.common.commands.McNativeCommand(this));
    }

    private static class BungeeObjectCreator implements ObjectCreator{

        @Override
        public ServerStatusResponse createServerStatusResponse() {
            return new BungeeCordServerStatusResponse(new ServerPing());
        }

        @Override
        public ServerStatusResponse.PlayerInfo createPlayerInfo(String name) {
            return new BungeeCordServerStatusResponse.DefaultPlayerInfo(name);
        }

        @Override
        public ServerStatusResponse.PlayerInfo createPlayerInfo(UUID uniqueId, String name) {
            return new BungeeCordServerStatusResponse.DefaultPlayerInfo(name,uniqueId);
        }
    }
}
