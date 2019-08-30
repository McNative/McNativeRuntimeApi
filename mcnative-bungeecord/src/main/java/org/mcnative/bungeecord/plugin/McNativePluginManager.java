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
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.event.EventBus;
import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.lifecycle.LifecycleState;
import net.prematic.libraries.plugin.loader.PluginLoader;
import org.mcnative.common.McNative;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

public class McNativePluginManager extends PluginManager {

    public McNativePluginManager(ProxyServer proxy) {
        super(proxy);
    }

    @Override
    public void registerCommand(Plugin plugin, Command command) {
        super.registerCommand(plugin, command);
    }

    @Override
    public void unregisterCommand(Command command) {
        super.unregisterCommand(command);
    }

    @Override
    public void unregisterCommands(Plugin plugin) {
        super.unregisterCommands(plugin);
    }

    @Override
    public boolean isExecutableCommand(String commandName, CommandSender sender) {
        return super.isExecutableCommand(commandName, sender);
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) {
        return super.dispatchCommand(sender, commandLine);
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine, List<String> tabResults) {
        return super.dispatchCommand(sender, commandLine, tabResults);
    }

    @Override
    public Collection<Plugin> getPlugins() {
        return super.getPlugins();
    }

    @Override
    public Plugin getPlugin(String name) {
        return super.getPlugin(name);
    }

    @Override
    public void loadPlugins() {
        super.loadPlugins();
    }

    @Override
    public void enablePlugins() {
        super.enablePlugins();
    }

    @Override
    public void detectPlugins(File folder) {
        super.detectPlugins(folder);
    }

    @Override
    public <T extends Event> T callEvent(T event) {
        return super.callEvent(event);
    }

    @Override
    public void registerListener(Plugin plugin, Listener listener) {
        super.registerListener(plugin, listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        super.unregisterListener(listener);
    }

    @Override
    public void unregisterListeners(Plugin plugin) {
        super.unregisterListeners(plugin);
    }

    @Override
    public Collection<Map.Entry<String, Command>> getCommands() {
        return super.getCommands();
    }

    public McNativePluginManager(ProxyServer proxy, Yaml yaml, EventBus eventBus) {
        super(proxy, yaml, eventBus);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
