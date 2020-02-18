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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcnative.bukkit.player.BukkitPlayerManager;
import org.mcnative.common.McNative;
import org.mcnative.common.plugin.CustomCommandSender;

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
    public boolean execute(CommandSender sender, String label, String[] arguments) {
        net.prematic.libraries.command.sender.CommandSender mapped;
        if(sender.equals(Bukkit.getConsoleSender())){
            mapped = McNative.getInstance().getConsoleSender();
        }else if(sender instanceof Player) {
            mapped = ((BukkitPlayerManager) McNative.getInstance().getPlayerManager()).getMappedPlayer((Player) sender);
        }else{
            mapped = new MappedCommandSender(sender);
        }
        original.execute(mapped,arguments);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return Collections.emptyList();
    }


    //@Todo implement custom permission messages

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
        if(obj == this) return true;
        else return original.equals(obj);
    }

    public final static class MappedCommandSender implements CustomCommandSender {

        private final CommandSender original;

        public MappedCommandSender(CommandSender original) {
            this.original = original;
        }

        @Override
        public Object getOriginal() {
            return original;
        }

        @Override
        public Class<?> getOriginalClass() {
            return original.getClass();
        }

        @Override
        public boolean instanceOf(Class<?> originalClass) {
            return original.getClass().isAssignableFrom(originalClass);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T to(Class<T> originalClass) {
            return (T) original;
        }

        @Override
        public String getName() {
            return original.getName();
        }

        @Override
        public boolean hasPermission(String permission) {
            return original.hasPermission(permission);
        }

        @Override
        public void sendMessage(String message) {
            original.sendMessage(message);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this
                    || (obj instanceof CustomCommandSender && original.equals(((CustomCommandSender) obj).getOriginal()))
                    ||  original.equals(obj);
        }

        @Override
        public String toString() {
            return original.toString();
        }
    }

}
