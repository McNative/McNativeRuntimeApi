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
import net.prematic.libraries.command.command.CommandExecutor;
import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.map.callback.CallbackMap;
import net.prematic.libraries.utility.map.callback.LinkedHashCallbackMap;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.plugin.MappedPlugin;
import org.mcnative.bungeecord.plugin.PluginObjectOwner;

import java.util.List;
import java.util.Map;

public class BungeeCordCommandManager implements CommandManager {

    private final PluginManager original;
    private final List<Command> commands;

    @Override
    public String getName() {
        return "BungeeCord";
    }

    @Override
    public Command getCommand(String name) {
        return Iterators.findOne(this.commands, command -> command.getName().equalsIgnoreCase(name));
    }

    @Override
    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public void setNotFoundHandler(CommandExecutor executor) {
        throw new UnsupportedOperationException("Not found handlers are not supported on BungeeCord, commands will be forwarded to spigot");
    }

    @Override
    public void dispatchCommand(CommandSender sender, String command) {
        original.dispatchCommand(null,command);
    }

    @Override
    public void registerCommand(ObjectOwner owner, Command command) {
        Plugin plugin = null;
        if(owner instanceof MappedPlugin) plugin = ((MappedPlugin) owner).getPlugin();
        else if(owner instanceof net.prematic.libraries.plugin.Plugin){

        }else throw new IllegalArgumentException("Object owner is not a plugin");

        if(plugin == null) throw new IllegalArgumentException("Plugin is not enabled");
        original.registerCommand(null,new McNativeCommand(command));
        this.commands.add(command);
        command.init(this,owner);
    }

    @Override
    public void unregisterCommand(String name) {
        Command result = Iterators.findOne(this.commands, command -> command.getName().equalsIgnoreCase(name));
        if(result != null) unregisterCommand(result);
    }

    @Override
    public void unregisterCommand(Command command) {
        if(command instanceof BungeeCordCommand){
            original.unregisterCommand(((BungeeCordCommand) command).getOriginal());
            //this.commands.remove(command);
        }else{

        }
    }

    @Override
    public void unregisterCommand(ObjectOwner owner) {
        Plugin plugin = null;
        if(owner instanceof MappedPlugin) plugin = ((MappedPlugin) owner).getPlugin();
        else if(owner instanceof net.prematic.libraries.plugin.Plugin){

        }else throw new IllegalArgumentException("Object owner is not a plugin");
        original.unregisterCommands(plugin);
    }

    @Override
    public void unregisterCommands() {
        original.getCommands().forEach(entry -> original.unregisterCommand(entry.getValue()));
        this.commands.clear();
    }

    @SuppressWarnings("unchecked")
    public void inject(){
        PluginManager
        Map<Plugin, net.md_5.bungee.api.plugin.Command> oldMap = ReflectionUtil.getFieldValue(original,"commandsByPlugin", Multimap.class).asMap();

        CallbackMap<Plugin, net.md_5.bungee.api.plugin.Command> newMap = new LinkedHashCallbackMap<>();
        newMap.setPutCallback((owner, command) -> commands.add(new BungeeCordCommand(command,new PluginObjectOwner(owner))));
        newMap.setRemoveCallback((owner, command) -> commands.remove(command));

        ReflectionUtil.changeFieldValue(original,"commandsByPlugin",newMap);
        newMap.putAll(oldMap);
    }
}
