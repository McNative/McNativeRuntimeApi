/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 22.03.20, 20:25
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

import net.pretronic.libraries.command.command.BasicCommand;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.McNative;

public class McNativePluginsCommand extends BasicCommand {

    public McNativePluginsCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.newBuilder().name("plugins").aliases("plugin", "pl").create());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        for (Plugin<?> plugin : McNative.getInstance().getPluginManager().getPlugins()) {
            String name = plugin.getName();
            String version = plugin.getDescription().getVersion().getName();
            int build = plugin.getDescription().getVersion().getBuild();
            String author = plugin.getDescription().getAuthor();


        }
    }
}
