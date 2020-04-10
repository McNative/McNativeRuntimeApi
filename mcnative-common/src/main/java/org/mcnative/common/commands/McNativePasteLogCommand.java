/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 22.03.20, 20:23
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
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.logging.PretronicLogger;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.utility.SystemInfo;
import net.pretronic.libraries.utility.SystemUtil;
import net.pretronic.libraries.utility.http.HttpClient;
import net.pretronic.libraries.utility.http.HttpMethod;
import net.pretronic.libraries.utility.http.HttpResult;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import net.pretronic.libraries.utility.io.FileUtil;
import org.mcnative.common.McNative;
import org.mcnative.common.Messages;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

import java.util.concurrent.TimeUnit;

public class McNativePasteLogCommand extends BasicCommand {

    public McNativePasteLogCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.newBuilder().name("pasteLog").aliases("paste", "log").permission("mcnative.admin").create());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        PretronicLogger logger = McNative.getInstance().getLogger();

        String version = McNative.getInstance().getVersion().getName();
        MinecraftProtocolVersion protocolVersion = McNative.getInstance().getPlatform().getProtocolVersion();
        String javaVersion = SystemUtil.getJavaVersion();

        logger.info("----------------------------------------");
        logger.info("Pretronic Paste");
        logger.info(" ");
        logger.info("McNative Version: " + version);
        logger.info("Protocol Version: " + protocolVersion.getName() + " | " + protocolVersion.getEdition().getName());
        logger.info("Platform Version: " + McNative.getInstance().getPlatform().getName()+ " - " + McNative.getInstance().getPlatform().getVersion());
        logger.info("Java Version: " + javaVersion);
        logger.info(" ");
        logger.info("Operation system: " + SystemInfo.getOsName() + " version " + SystemInfo.getOsVersion());
        logger.info("OS architecture: " + SystemInfo.getOsArch());
        logger.info(" ");
        logger.info("Max memory: " + ((double)SystemInfo.getMaxMemory()/(double) (1024 * 1024)));
        logger.info("Free memory: " + ((double)SystemInfo.getFreeMemory()/(double) (1024 * 1024)));
        logger.info("Allocated memory: " + ((double)SystemInfo.getAllocatedMemory()/(double) (1024 * 1024)));
        logger.info("Total free memory: " + ((double)SystemInfo.getTotalFreeMemory()/(double) (1024 * 1024)));
        logger.info("");
        logger.info("Plugins:");
        for (Plugin plugin : McNative.getInstance().getPluginManager().getPlugins()) {
            logger.info("- {} v{}", plugin.getName(), plugin.getDescription().getVersion().getName());
        }
        logger.info("----------------------------------------");

        McNative.getInstance().getScheduler().createTask(McNative.getInstance()).delay(3, TimeUnit.SECONDS).execute(()-> {
            String content = FileUtil.readContent(McNative.getInstance().getPlatform().getLatestLogLocation());
            String url = publishLogAndGetUrl(content);
            logger.info("See your log on " + url);
            sender.sendMessage(Messages.COMMAND_PASTE_SUCCESSFUL, VariableSet.create().add("url", url));
        });
    }

    private String publishLogAndGetUrl(String content) {
        HttpClient client = new HttpClient();
        client.setUrl("https://paste.pretronic.net/documents");
        client.setRequestMethod(HttpMethod.POST);
        client.setContent(content);
        client.setTLSVersion("TLSv1.2");
        HttpResult result = client.connect();
        //@Todo check success
        return "https://paste.pretronic.net/" + DocumentFileType.JSON.getReader().read(result.getContent()).getString("key");
    }
}
