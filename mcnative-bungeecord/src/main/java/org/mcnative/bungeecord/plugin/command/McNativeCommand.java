/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 15:26
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

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class McNativeCommand extends Command {

    private final net.prematic.libraries.command.command.Command original;

    public McNativeCommand(net.prematic.libraries.command.command.Command original) {
        super(original.getName(),original.getPermission(),original.getAliases().toArray(new String[]{}));
        this.original = original;
    }

    public net.prematic.libraries.command.command.Command getOriginal() {
        return original;
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {
        original.execute(null,null,strings);
    }
}
