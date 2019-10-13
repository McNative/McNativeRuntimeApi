/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 26.08.19, 19:20
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

package org.mcnative.bungeecord.plugin;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.*;
import net.prematic.libraries.event.EventManager;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.bungeecord.internal.event.player.BungeeMinecraftLoginEvent;
import org.mcnative.bungeecord.internal.event.player.BungeeMinecraftPendingLoginEvent;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.bungeecord.player.BungeePendingConnection;
import org.mcnative.bungeecord.player.BungeeProxiedPlayer;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.event.player.login.MinecraftPlayerLoginEvent;
import org.mcnative.common.event.player.login.MinecraftPlayerPendingLoginEvent;
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerManager;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class McNativeBungeePluginManager extends PluginManager {

    private PluginManager original;
    private EventManager eventManager;
    private BungeeCordPlayerManager playerManager;

    private Map<UUID, BungeeProxiedPlayer> pendingPlayers;

    public McNativeBungeePluginManager(PluginManager original,EventManager eventManager,BungeeCordPlayerManager playerManager) {
        super(null, null, null);
        this.original = original;
        this.eventManager = eventManager;
        this.playerManager = playerManager;
        this.pendingPlayers = new ConcurrentHashMap<>();
    }

    @Override
    public void registerCommand(Plugin plugin, Command command) {
        original.registerCommand(plugin, command);
    }

    @Override
    public void unregisterCommand(Command command) {
        original.unregisterCommand(command);
    }

    @Override
    public void unregisterCommands(Plugin plugin) {
        original.unregisterCommands(plugin);
    }

    @Override
    public boolean isExecutableCommand(String commandName, CommandSender sender) {
        return original.isExecutableCommand(commandName, sender);
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) {
        return original.dispatchCommand(sender, commandLine);
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine, List<String> tabResults) {
        return original.dispatchCommand(sender, commandLine, tabResults);
    }

    @Override
    public Collection<Plugin> getPlugins() {
        return original.getPlugins();
    }

    @Override
    public Plugin getPlugin(String name) {
        return original.getPlugin(name);
    }

    @Override
    public void loadPlugins() {
        original.loadPlugins();
    }

    @Override
    public void enablePlugins() {
        original.enablePlugins();
    }

    @Override
    public void detectPlugins(File folder) {
        original.detectPlugins(folder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Event> T callEvent(T event) {
        if(event instanceof LoginEvent) return (T) handleLogin((LoginEvent)event);
        else if(event instanceof PostLoginEvent){
            BungeeProxiedPlayer player = pendingPlayers.get(((PostLoginEvent) event).getPlayer().getUniqueId());
            player.postLogin(((PostLoginEvent) event).getPlayer());
            playerManager.registerPlayer(player);
            System.out.println("POST");
        }

        return original.callEvent(event);
    }

    @Override
    public void registerListener(Plugin plugin, Listener listener) {
        System.out.println("REGISTER: "+plugin.getDescription().getName());
        eventManager.subscribe(ObjectOwner.SYSTEM,listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        eventManager.unsubscribe(listener);
    }

    @Override
    public void unregisterListeners(Plugin plugin) {
        //eventManager.unsubscribe();
    }

    @Override
    public Collection<Map.Entry<String, Command>> getCommands() {
        return original.getCommands();
    }

    public void addOldEvents(){

    }

    private LoginEvent handleLogin(LoginEvent event){
        BungeePendingConnection connection = new BungeePendingConnection(((LoginEvent) event).getConnection());
        connection.setState(ConnectionState.LOGIN);

        MinecraftPlayerPendingLoginEvent pendingEvent = new BungeeMinecraftPendingLoginEvent(connection);
        eventManager.callEvent(MinecraftPlayerPendingLoginEvent.class,pendingEvent);
        if(pendingEvent.isCancelled()){
            connection.disconnect(pendingEvent.getCancelReason(),pendingEvent.getCancelReasonVariables());
            event.postCall();
            return event;
        }

        BungeeProxiedPlayer player = new BungeeProxiedPlayer(connection,null,null);

        MinecraftPlayerLoginEvent loginEvent = new BungeeMinecraftLoginEvent((LoginEvent) event,null);
        eventManager.callEvent(MinecraftPlayerLoginEvent.class,loginEvent);

        if(loginEvent.isCancelled()){
            if(((LoginEvent) event).getCancelReasonComponents() == null){
                connection.disconnect(loginEvent.getCancelReason(),loginEvent.getCancelReasonVariables());
                ((LoginEvent) event).setCancelled(false);
            }
        }else pendingPlayers.put(player.getUniqueId(),player);
        return original.callEvent(event);
    }
}
