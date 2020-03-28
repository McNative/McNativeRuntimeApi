/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 17:27
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

package org.mcnative.proxy;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.annotations.DocumentIgnored;
import net.pretronic.libraries.document.annotations.DocumentKey;
import net.pretronic.libraries.document.annotations.DocumentRequired;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.logging.PretronicLogger;
import net.pretronic.libraries.utility.map.Pair;
import org.mcnative.common.McNative;
import org.mcnative.common.network.component.server.MinecraftServerType;
import org.mcnative.common.plugin.configuration.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class McNativeProxyConfiguration {

    public static transient DocumentFileType FORMAT = DocumentFileType.YAML;

    @DocumentIgnored
    public static Map<String,ConfiguredServer> SERVER_SERVERS = new HashMap<>();

    /*
    //@Todo currently unused and not implemented
    public static boolean WHITELIST_ENABLED = false;
    public static String WHITELIST_MESSAGE = "&6You are not whitelisted on this server.";
    public static String WHITELIST_PERMISSION = "mcnative.whitelist.join";
    public static Collection<String> WHITELIST_PLAYERS = Arrays.asList("Dkrieger","FridiousHD"
            ,"cb7f0812-1fbb-4715-976e-a81e52be4b67","21246bcb-b4ad-4d38-8662-69cb8debb7c6");

    public static boolean SERVER_CONNECT_PRIORITY_ENABLED = false;

    @DocumentKey("server.connect.connectToFallback.onJoin.allowNormalServers")
    public static boolean SERVER_CONNECT_PRIORITY_ON_JOIN_ALLOW_ONLY_NORMAL_SERVERS = false;

    @DocumentKey("server.connect.priority.noFallback")
    public static String SERVER_CONNECT_PRIORITY_NO_FALLBACK = "No fallback server available.";

    public static boolean GLOBAL_CHAT_ENABLED = false;
    public static String GLOBAL_CHAT_PERMISSION = "chat.use";
    public static boolean GLOBAL_CHAT_COLORS_ENABLED = true;
    public static String GLOBAL_CHAT_COLORS_PERMISSION = "chat.use.color";
    public static char GLOBAL_CHAT_COLORS_SYMBOL = '&';
    public static boolean GLOBAL_CHAT_MARKDOWN_ENABLED = true;
    public static String GLOBAL_CHAT_MARKDOWN_PERMISSION = "chat.use.markdown";
    public static String GLOBAL_CHAT_FORMAT = "&8[&6%server%&8] %display%%player% &8: &7%message%";

    public static boolean GLOBAL_TABLIST_ENABLED = false;
    public static String GLOBAL_TABLIST_HEADER = "&6McNative &ais a cool Minecraft Application Framework";
    public static String GLOBAL_TABLIST_FOOTER = "&aWebsite: https://mcnative.org";
    public static String GLOBAL_TABLIST_ORDER = "RANK";
    public static String GLOBAL_TABLIST_FORMAT_PLAYER = "%prefix% %player% %suffix%";
    public static String GLOBAL_TABLIST_FORMAT_SERVER = "&8<- %server% &8->";


        @DocumentKey("messaging.directConnect.enabled")
    public static boolean NETWORK_DIRECT_CONNECT_ENABLED = true;
    @DocumentKey("messaging.directConnect.password")
    public static String NETWORK_DIRECT_CONNECT_PASSWORD = StringUtil.getRandomString(15);
     */

    @DocumentKey("network.messaging.packetManipulation.upstream")
    public static boolean NETWORK_PACKET_MANIPULATION_UPSTREAM_ENABLED = true;
    @DocumentKey("network.messaging.packetManipulation.downstream")
    public static boolean NETWORK_PACKET_MANIPULATION_DOWNSTREAM_ENABLED = false;

    public static class ConfiguredServer {

        @DocumentRequired
        private final InetSocketAddress address;
        private final String permission;
        private final MinecraftServerType type;

        public ConfiguredServer(InetSocketAddress address, String permission, MinecraftServerType type) {
            this.address = address;
            this.permission = permission;
            this.type = type;
        }

        public InetSocketAddress getAddress() {
            return address;
        }

        public String getPermission() {
            return permission;
        }

        public MinecraftServerType getType() {
            return type;
        }
    }

    public static boolean load(PretronicLogger logger, File location){
        logger.info(McNative.CONSOLE_PREFIX+"Searching configuration file");
        Pair<File,DocumentFileType> configSpec = Document.findExistingType(location,"config");
        File configFile;
        DocumentFileType type;

        if(configSpec == null){
            configFile = new File(location,"config.yml");
            type = DocumentFileType.YAML;
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException exception) {
                logger.error(McNative.CONSOLE_PREFIX+"Could not create configuration file (config.yml)",exception);
                exception.printStackTrace();
                return false;
            }
        }else{
            configFile = configSpec.getKey();
            type = configSpec.getValue();
        }
        logger.info(McNative.CONSOLE_PREFIX+"Loading configuration (config.{})",type.getEnding());

        try{
            FORMAT = type;
            FileConfiguration.FILE_TYPE = type;
            Document config = type.getReader().read(configFile);
            Document.loadConfigurationClass(McNativeProxyConfiguration.class,config);
            type.getWriter().write(configFile,config);
        }catch (Exception exception){
            logger.info(McNative.CONSOLE_PREFIX+"Could not load configuration (config."+type.getEnding()+")",exception);
            exception.printStackTrace();
            return false;
        }
        return true;
    }

}
