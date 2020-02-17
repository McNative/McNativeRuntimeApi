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

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;

import java.util.Collection;
import java.util.List;

public class McNativeCommandMap extends SimpleCommandMap {

    public McNativeCommandMap(Server server) {
        super(server);
    }

    @Override
    public void setFallbackCommands() {
        super.setFallbackCommands();
    }

    @Override
    public void registerAll(String fallbackPrefix, List<Command> commands) {
        super.registerAll(fallbackPrefix, commands);
    }

    @Override
    public boolean register(String fallbackPrefix, Command command) {
        return super.register(fallbackPrefix, command);
    }

    @Override
    public boolean register(String label, String fallbackPrefix, Command command) {
        return super.register(label, fallbackPrefix, command);
    }

    @Override
    public boolean dispatch(CommandSender sender, String commandLine) throws CommandException {
        return super.dispatch(sender, commandLine);
    }

    @Override
    public synchronized void clearCommands() {
        super.clearCommands();
    }

    @Override
    public Command getCommand(String name) {
        return super.getCommand(name);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String cmdLine) {
        return super.tabComplete(sender, cmdLine);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String cmdLine, Location location) {
        return super.tabComplete(sender, cmdLine, location);
    }

    @Override
    public Collection<Command> getCommands() {
        return super.getCommands();
    }

    @Override
    public void registerServerAliases() {
        super.registerServerAliases();
    }
}
