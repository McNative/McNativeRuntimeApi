/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.02.20, 12:37
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

package org.mcnative.bukkit.plugin.command;

import net.prematic.libraries.command.command.NotFoundHandler;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.mcnative.common.McNative;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class McNativeCommandMap extends SimpleCommandMap {

    private final BukkitCommandManager commandManager;
    private final SimpleCommandMap original;
    private final Map<String,Command> commands;

    @SuppressWarnings("unchecked")
    public McNativeCommandMap(BukkitCommandManager commandManager,SimpleCommandMap original) {
        super(Bukkit.getServer());
        this.commandManager = commandManager;
        this.original = original;
        System.out.println("NEW COMMAND MAP "+original);
        this.commands = (Map<String, Command>) ReflectionUtil.getFieldValue(original,"knownCommands");
    }

    @Override
    public void setFallbackCommands() {
        original.setFallbackCommands();
    }

    @Override
    public void registerAll(String fallbackPrefix, List<Command> commands) {
        original.registerAll(fallbackPrefix, commands);
    }

    @Override
    public boolean register(String fallbackPrefix, Command command) {
        if(original == null) return false;
        return register(command.getName(),fallbackPrefix, command);
    }

    @Override
    public boolean register(String label, String fallbackPrefix, Command command) {
        boolean result =  original.register(label, fallbackPrefix, command);
        if(result && !(command instanceof McNativeCommand)){
            commandManager.provideCommand(new BukkitCommand(command,getOwner(fallbackPrefix)));
        }
        return result;
    }

    private ObjectOwner getOwner(String name){
        Plugin<?> plugin = McNative.getInstance().getPluginManager().getPlugin(name);
        return plugin != null ? plugin : ObjectOwner.SYSTEM;
    }

    @Override
    public boolean dispatch(CommandSender sender, String command) throws CommandException {
        if(!original.dispatch(sender,command)){
            NotFoundHandler handler = commandManager.getNotFoundHandler();
            if(handler != null){
                String[] arguments = command.split(" ");
                handler.handle(McNativeCommand.getMappedSender(sender),arguments[0], Arrays.copyOfRange(arguments,1,arguments.length));
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void clearCommands() {
        commandManager.clearCommands();
        original.clearCommands();
    }

    @Override
    public Command getCommand(String name) {
        return original.getCommand(name);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String cmdLine) {
        return original.tabComplete(sender, cmdLine);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String cmdLine, Location location) {
        return original.tabComplete(sender, cmdLine, location);
    }

    @Override
    public Collection<Command> getCommands() {
        System.out.println("GET COMMANDS");
        return original.getCommands();
    }

    @Override
    public void registerServerAliases() {
        original.registerServerAliases();
    }



    public void unregister(Object command){
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            if(entry.getValue().equals(command)){
                commands.remove(entry.getKey());
            }
        }
    }
}
