/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.04.20, 20:37
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

package org.mcnative.common.commands.plugin;

import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.bml.variable.describer.DescribedHashVariableSet;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.plugin.loader.PluginLoader;
import org.mcnative.common.McNative;
import org.mcnative.common.utils.Messages;

public class PluginCommandUtil {

    protected static Plugin<?> getPlugin(CommandSender sender, String[] arguments,String command,String usage) {
        if(arguments.length < 1){
            sender.sendMessage(Messages.COMMAND_MCNATIVE_INVALID_USAGE, VariableSet.create()
                    .add("command",command)
                    .add("usage",usage));
            return null;
        }
        Plugin<?> plugin = McNative.getInstance().getPluginManager().getPlugin(arguments[0]);
        if(plugin == null){
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_NOTFOUND, VariableSet.create()
                    .add("plugin",arguments[0]).add("plugin.name",arguments[0]));
            return null;
        }
        return plugin;
    }

    protected static boolean enablePlugin(CommandSender sender, Plugin<?> plugin) {
        sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_ENABLE_STARTING, new DescribedHashVariableSet()
                .add("plugin",plugin));

        try{
            plugin.getLoader().bootstrap();//In McNative is enable called bootstrap
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_ENABLE_SUCCESSFULLY, new DescribedHashVariableSet()
                    .add("plugin",plugin));
        }catch (Exception exception){
            exception.printStackTrace();
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_ENABLE_FAILED, new DescribedHashVariableSet()
                    .add("plugin",plugin)
                    .add("error",exception.getMessage()));
            return false;
        }
        return true;
    }

    protected static boolean disablePlugin(CommandSender sender, Plugin<?> plugin) {
        sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_DISABLE_STARTING, new DescribedHashVariableSet()
                .add("plugin",plugin));

        try{
            plugin.getLoader().shutdown();//In McNative is disable called shutdown
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_DISABLE_SUCCESSFULLY, new DescribedHashVariableSet()
                    .add("plugin",plugin));
        }catch (Exception exception){
            exception.printStackTrace();
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_DISABLE_FAILED, new DescribedHashVariableSet()
                    .add("plugin",plugin)
                    .add("error",exception.getMessage()));
            return false;
        }
        return true;
    }


    protected static boolean unloadPlugin(CommandSender sender, Plugin<?> plugin) {
        sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_UNLOAD_STARTING, new DescribedHashVariableSet()
                .add("plugin",plugin));

        try{
            plugin.getLoader().unload();//In McNative is enable called UNLOAD

            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_UNLOAD_SUCCESSFULLY, new DescribedHashVariableSet()
                    .add("plugin",plugin));
        }catch (Exception exception){
            exception.printStackTrace();
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_UNLOAD_FAILED, new DescribedHashVariableSet()
                    .add("plugin",plugin)
                    .add("error",exception.getMessage()));
            return false;
        }
        return true;
    }

    protected static void loadPlugin(CommandSender sender, PluginLoader loader) {
        if (loader.isEnabled()) {
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_ALREADY_ENABLED, VariableSet.create()
                    .add("plugin", loader.getInstance()));
            return;
        }

        sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_LOAD_STARTING, VariableSet.create()
                .add("plugin", loader));

        try {
            loader.enable();
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_LOAD_SUCCESSFULLY, VariableSet.create()
                    .add("plugin", loader.getInstance()));
        } catch (Exception exception) {
            exception.printStackTrace();
            sender.sendMessage(Messages.COMMAND_MCNATIVE_PLUGIN_LOAD_FAILED, VariableSet.create()
                    .add("plugin", loader.getInstance())
                    .add("exception", exception.getMessage()));
        }
    }
}
