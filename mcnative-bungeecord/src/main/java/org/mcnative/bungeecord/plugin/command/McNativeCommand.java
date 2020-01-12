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
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;
import org.mcnative.common.McNative;
import org.mcnative.common.plugin.CustomCommandSender;

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
        net.prematic.libraries.command.sender.CommandSender mapped;
        if(sender.equals(ProxyServer.getInstance().getConsole())){
            mapped = McNative.getInstance().getConsoleSender();
        }else if(sender instanceof ProxiedPlayer) {
            mapped = ((BungeeCordPlayerManager) McNative.getInstance().getPlayerManager()).getMappedPlayer((ProxiedPlayer) sender);
        }else{
            mapped = new MappedCommandSender(sender);
        }
        original.execute(mapped,null,strings);
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
