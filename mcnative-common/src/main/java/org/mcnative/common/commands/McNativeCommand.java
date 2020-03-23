/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 22.03.20, 20:22
 * @web %web%
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

package org.mcnative.common.commands;

import net.pretronic.libraries.command.NotFindable;
import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.MainCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.command.sender.ConsoleCommandSender;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.Messages;

public class McNativeCommand extends MainCommand implements NotFindable {

    private final BasicCommand infoCommand;

    public McNativeCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("mcnative"));
        this.infoCommand = new McNativeInfoCommand(owner);
        registerCommand(infoCommand);
        registerCommand(new McNativePluginsCommand(owner));
        registerCommand(new McNativeInfoCommand(owner));
    }

    @Override
    public void commandNotFound(CommandSender sender, String command, String[] args) {
        if(sender.hasPermission("mcnative.admin")) {
            infoCommand.execute(sender, args);
        } else {
            sender.sendMessage(sender instanceof ConsoleCommandSender ? Messages.PREFIX_CONSOLE : Messages.PREFIX_USER + "See https://mcnative.org");
        }
    }
}
