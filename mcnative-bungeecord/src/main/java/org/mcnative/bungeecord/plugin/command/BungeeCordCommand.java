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


import net.prematic.libraries.command.command.Command;
import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.command.sender.CommandSender;
import net.prematic.libraries.utility.interfaces.ObjectOwner;

import java.util.Arrays;
import java.util.Collection;

public class BungeeCordCommand implements Command {

    private final net.md_5.bungee.api.plugin.Command original;
    private final ObjectOwner owner;

    public BungeeCordCommand(net.md_5.bungee.api.plugin.Command original, ObjectOwner owner) {
        this.original = original;
        this.owner = owner;
    }

    public net.md_5.bungee.api.plugin.Command getOriginal() {
        return original;
    }

    @Override
    public String getName() {
        return original.getName();
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getPermission() {
        return original.getPermission();
    }

    @Override
    public String getPrefix() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ObjectOwner getOwner() {
        return owner;
    }

    @Override
    public Collection<String> getAliases() {
        return Arrays.asList(original.getAliases());
    }

    @Override
    public boolean hasAlias(String command) {
        return getName().equalsIgnoreCase(command) || getAliases().contains(command);
    }

    @Override
    public void setPrefix(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void init(CommandManager manager, ObjectOwner owner) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(CommandSender sender, String command, String[] arguments) {

        original.execute(null,arguments);//@Todo implement
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        else return original.equals(obj);
    }
}
