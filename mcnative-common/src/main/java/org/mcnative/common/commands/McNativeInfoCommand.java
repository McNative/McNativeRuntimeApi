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
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.SystemInfo;
import net.pretronic.libraries.utility.SystemUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.McNative;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.Messages;

public class McNativeInfoCommand extends BasicCommand {

    public McNativeInfoCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.newBuilder().name("info").create());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("mcnative.admin")) {
            String version = "";
            MinecraftProtocolVersion protocolVersion = McNative.getInstance().getPlatform().getProtocolVersion();
            String javaVersion = SystemUtil.getJavaVersion();

            sender.sendMessage(Messages.COMMAND_INFO_ADMIN, VariableSet.create()
                    .add("version", version)
                    .add("protocolVersion", protocolVersion)
                    .add("javaVersion", javaVersion)
                    .add("osName", SystemInfo.getOsName())
                    .add("osVersion", SystemInfo.getOsVersion())
                    .add("osArch", SystemInfo.getOsArch())
                    .add("maxMemory", SystemInfo.getMaxMemory())
                    .add("allocatedMemory", SystemInfo.getAllocatedMemory())
                    .add("freeMemory", SystemInfo.getFreeMemory())
                    .add("totalFreeMemory", SystemInfo.getTotalFreeMemory()));
        } else {
            sender.sendMessage(Messages.COMMAND_INFO_USER);
        }
    }
}
