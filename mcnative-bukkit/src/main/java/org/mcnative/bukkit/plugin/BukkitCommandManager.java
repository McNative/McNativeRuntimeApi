/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 04.11.19, 18:46
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

package org.mcnative.bukkit.plugin;

import net.prematic.libraries.command.command.Command;
import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.command.notfound.CommandNotFoundHandler;
import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.mcnative.common.McNative;
import org.mcnative.common.player.OnlineMinecraftPlayer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BukkitCommandManager implements CommandManager {

    @Override
    public String getName() {
        return "McNative";
    }

    @Override
    public Command getCommand(String command) {
        CommandMap commandMap = getCommandMap();
        org.bukkit.command.Command bukkitCommand = commandMap.getCommand(command);
        if(bukkitCommand == null) return null;
        if(bukkitCommand instanceof MappedCommand) return ((MappedCommand)bukkitCommand).original;
        MappedCommand mappedCommand = createMappedCommandByBukkitCommand(bukkitCommand);
        Map<String, org.bukkit.command.Command> commands = getBukkitCommands();
        commands.put(command, mappedCommand);
        return mappedCommand.original;
    }

    @Override
    public List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        CommandMap commandMap = getCommandMap();
        Map<String, org.bukkit.command.Command> bukkitCommands = getBukkitCommands();
        for (Map.Entry<String, org.bukkit.command.Command> entry : bukkitCommands.entrySet()) {
            org.bukkit.command.Command command = entry.getValue();
            if(command instanceof MappedCommand) commands.add(((MappedCommand)command).original);
            else {
                MappedCommand mappedCommand = createMappedCommandByBukkitCommand(command);
                unregisterCommand(entry.getKey());
                commandMap.register("", mappedCommand);
                commands.add(mappedCommand.original);
            }
        }
        return commands;
    }

    @Override
    public void setNotFoundHandler(CommandNotFoundHandler commandNotFoundHandler) {

    }

    @Override
    public void dispatchCommand(CommandSender commandSender, String command) {
        getCommandMap().dispatch(getBukkitConsoleSender(commandSender), command);
    }

    @Override
    public void registerCommand(ObjectOwner objectOwner, Command command) {
        getCommandMap().register("", new MappedCommand(objectOwner, command));
    }

    @Override
    public void unregisterCommand(String commandName) {
        getBukkitCommands().remove(commandName);
    }

    @Override
    public void unregisterCommand(Command command) {
        unregisterCommand(command.getName());
    }

    @Override
    public void unregisterCommand(ObjectOwner objectOwner) {
        for (org.bukkit.command.Command command : getBukkitCommands().values()) {
            if(command instanceof MappedCommand && ((MappedCommand)command).owner.equals(objectOwner)) {
                unregisterCommand(((MappedCommand)command).original);
            }
        }
    }

    @Override
    public void unregisterAll() {
        getBukkitCommands().clear();
    }

    private SimpleCommandMap getCommandMap() {
        try {
            final Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            return (SimpleCommandMap) field.get(Bukkit.getServer());
        } catch (Exception ignored){}
        throw new IllegalArgumentException("CommandMap is null");
    }

    private MappedCommand createMappedCommandByBukkitCommand(org.bukkit.command.Command bukkitCommand) {
        return new MappedCommand(McNative.getInstance(), new Command(bukkitCommand.getName(), bukkitCommand.getDescription(), bukkitCommand.getPermission(), bukkitCommand.getAliases()) {
            @Override
            public void execute(CommandSender commandSender, String[] args) {
                bukkitCommand.execute(getBukkitConsoleSender(commandSender), bukkitCommand.getName(), args);
            }
        });
    }

    private org.bukkit.command.CommandSender getBukkitConsoleSender(CommandSender commandSender) {
        org.bukkit.command.CommandSender bukkitCommandSender;
        if(commandSender instanceof OnlineMinecraftPlayer) {
            bukkitCommandSender = Bukkit.getPlayer(((OnlineMinecraftPlayer)commandSender).getUniqueId());
        } else {
            bukkitCommandSender = Bukkit.getConsoleSender();
        }
        return bukkitCommandSender;
    }

    @SuppressWarnings("unchecked")
    private Map<String, org.bukkit.command.Command> getBukkitCommands() {
        return (Map<String, org.bukkit.command.Command>) ReflectionUtil.getFieldValue(SimpleCommandMap.class, "knownCommands");
    }

    private static class MappedCommand extends org.bukkit.command.Command {

        private final ObjectOwner owner;
        private final Command original;

        MappedCommand(ObjectOwner owner, Command original) {
            super(original.getName(), original.getDescription(), "", new ArrayList<>(original.getAliases()));
            this.owner = owner;
            this.original = original;
        }

        @Override
        public boolean execute(org.bukkit.command.CommandSender sender, String commandLabel, String[] args) {
            this.original.execute(null, args);
            return true;
        }
    }
}
