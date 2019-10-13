/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.10.19, 15:46
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

import net.md_5.bungee.api.plugin.PluginManager;
import net.prematic.libraries.command.command.Command;
import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.command.notfound.CommandNotFoundHandler;
import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.utility.interfaces.ObjectOwner;

import java.util.Collection;
import java.util.List;

public class BungeeMcNativeCommandManager implements CommandManager {

    private PluginManager pluginManager;
    private Collection<Command> commands;

    @Override
    public String getName() {
        return "McNative";
    }

    @Override
    public Command getCommand(String s) {
        return null;
    }

    @Override
    public List<Command> getCommands() {
        return null;
    }

    @Override
    public void setNotFoundHandler(CommandNotFoundHandler commandNotFoundHandler) {

    }

    @Override
    public void dispatchCommand(CommandSender sender, String command) {
        pluginManager.dispatchCommand(null,command);
    }

    @Override
    public void registerCommand(ObjectOwner owner, Command command) {
        pluginManager.registerCommand(null,new MappedCommand(command));
    }

    @Override
    public void unregisterCommand(String command) {

    }

    @Override
    public void unregisterCommand(Command command) {

    }

    @Override
    public void unregisterCommand(ObjectOwner owner) {

    }

    @Override
    public void unregisterAll() {
        pluginManager.getCommands().forEach(entry -> pluginManager.unregisterCommand(entry.getValue()));
        commands.clear();
    }

    private static class MappedCommand extends net.md_5.bungee.api.plugin.Command {

        private final Command realCommand;

        public MappedCommand(Command realCommand) {
            super(realCommand.getName(),realCommand.getPermission(),realCommand.getAliases().toArray(new String[]{}));
            this.realCommand = realCommand;
        }

        @Override
        public void execute(net.md_5.bungee.api.CommandSender sender, String[] args) {
            realCommand.execute(null,args);
        }
    }
}
