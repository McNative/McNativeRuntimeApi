/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 15:25
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

package org.mcnative.bungeecord.plugin.command;

import com.google.common.collect.Multimap;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.prematic.libraries.command.command.Command;
import net.prematic.libraries.command.command.NotFoundHandler;
import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.Validate;
import net.prematic.libraries.utility.annonations.Internal;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.plugin.BungeeCordPluginManager;
import org.mcnative.bungeecord.plugin.MappedPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BungeeCordCommandManager implements CommandManager {

    private final BungeeCordPluginManager pluginManager;
    private final PluginManager original;
    private final List<Command> commands;

    public BungeeCordCommandManager(BungeeCordPluginManager pluginManager, PluginManager original) {
        this.pluginManager = pluginManager;
        this.original = original;
        this.commands = new ArrayList<>();
        inject();
    }

    @Override
    public Command getCommand(String name) {
        return Iterators.findOne(this.commands, command -> command.getConfiguration().getName().equalsIgnoreCase(name));
    }

    @Override
    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public void setNotFoundHandler(NotFoundHandler executor) {
        throw new UnsupportedOperationException("Not found handlers are not supported on BungeeCord, commands will be forwarded to sub server");
    }

    @Override
    public void dispatchCommand(CommandSender sender, String command) {//@Todo map sender
        original.dispatchCommand(null,command);
    }

    @Override
    public void registerCommand(Command command) {
        Validate.notNull(command,command.getConfiguration(),command.getOwner());
        if(!(command.getOwner() instanceof net.prematic.libraries.plugin.Plugin)){
            throw new IllegalArgumentException("Owner is not a plugin.");
        }
        Plugin plugin = getOriginalPlugin(command.getOwner());

        if(plugin == null) throw new IllegalArgumentException("Plugin is not enabled");
        this.original.registerCommand(plugin,new McNativeCommand(command));
        this.commands.add(command);
    }

    @Override
    public void unregisterCommand(String name) {
        Command result = Iterators.findOne(this.commands, command -> command.getConfiguration().getName().equalsIgnoreCase(name));
        if(result != null) unregisterCommand(result);
    }

    @Override
    public void unregisterCommand(Command command) {
        if(command instanceof BungeeCordCommand){
            original.unregisterCommand(((BungeeCordCommand) command).getOriginal());
        }else{
            net.md_5.bungee.api.plugin.Command mapped = null;
            for (Map.Entry<String, net.md_5.bungee.api.plugin.Command> entry : original.getCommands()) {
                if(entry.getValue().equals(command)) mapped = entry.getValue();
            }
            if(mapped != null)original.unregisterCommand(mapped);
        }
    }

    @Override
    public void unregisterCommand(ObjectOwner owner) {
        Plugin plugin = getOriginalPlugin(owner);
        original.unregisterCommands(plugin);
    }

    @Override
    public void unregisterCommands() {
        this.commands.clear();
        original.getCommands().forEach(entry -> original.unregisterCommand(entry.getValue()));
    }

    @Internal
    void registerCommand(Plugin plugin, net.md_5.bungee.api.plugin.Command command){
        Validate.notNull(plugin,command);
        this.commands.add(new BungeeCordCommand(command,pluginManager.getMappedPlugin(plugin)));
    }

    @Internal
    void unregisterCommand(net.md_5.bungee.api.plugin.Command command){
        this.commands.remove(command);
    }

    @Internal
    void unregisterCommands(Collection<net.md_5.bungee.api.plugin.Command> commands){
        this.commands.removeAll(commands);
    }

    private Plugin getOriginalPlugin(ObjectOwner owner) {
        Plugin plugin;
        if (owner instanceof MappedPlugin) {
            plugin = ((MappedPlugin) owner).getPlugin();
        }else if (owner instanceof net.prematic.libraries.plugin.Plugin) {
            plugin = pluginManager.getMappedPlugin((net.prematic.libraries.plugin.Plugin<?>) owner);
        } else throw new IllegalArgumentException("Object owner is not a plugin");
        return plugin;
    }

    @Internal
    @SuppressWarnings("unchecked")
    private void inject(){
        Multimap<Plugin, net.md_5.bungee.api.plugin.Command> oldMap = ReflectionUtil.getFieldValue(original,"commandsByPlugin", Multimap.class);
        MappedCommandMap newMap = new MappedCommandMap(this,oldMap);
        ReflectionUtil.changeFieldValue(original,"commandsByPlugin",newMap);
    }
}
