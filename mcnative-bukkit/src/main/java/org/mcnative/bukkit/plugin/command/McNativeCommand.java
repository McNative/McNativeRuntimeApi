/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.02.20, 12:40
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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class McNativeCommand extends Command {

    private final net.prematic.libraries.command.command.Command original;

    public McNativeCommand(net.prematic.libraries.command.command.Command original) {
        super(original.getConfiguration().getName()
                , original.getConfiguration().getDescription()
                , ""
                , Arrays.asList(original.getConfiguration().getAliases()));
        this.original = original;
    }

    @Override
    public boolean execute(CommandSender sender0, String label, String[] args) {
        CommandSender sender = null;
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return Collections.emptyList();
    }


    @Override
    public boolean testPermission(CommandSender target) {
        return super.testPermission(target);
    }

    @Override
    public boolean testPermissionSilent(CommandSender target) {
        return super.testPermissionSilent(target);
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
