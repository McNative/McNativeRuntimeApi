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

import net.prematic.libraries.document.annotations.DocumentKey;
import net.prematic.libraries.utility.GeneralUtil;
import net.prematic.libraries.utility.StringUtil;
import org.mcnative.common.network.component.server.MinecraftServerType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class McNativeProxyConfiguration {

    public static boolean WHITELIST_ENABLED = false;
    public static String WHITELIST_MESSAGE = "&6You are not whitelisted on this server.";
    public static String WHITELIST_PERMISSION = "mcnative.whitelist.join";
    public static Collection<String> WHITELIST_PLAYERS = Arrays.asList("Dkrieger","cb7f0812-1fbb-4715-976e-a81e52be4b67");

    private static List<ConfiguredServer> SERVERS;

    public static boolean GLOBAL_CHAT_ENABLED = false;
    public static String GLOBAL_CHAT_PERMISSION = "chat.use";
    public static boolean GLOBAL_CHAT_COLORS_ENABLED = true;
    public static String GLOBAL_CHAT_COLORS_PERMISSION = "chat.use.color";
    public static char GLOBAL_CHAT_COLORS_SYMBOL = '&';
    public static boolean GLOBAL_CHAT_MARKDOWN_ENABLED = true;
    public static String GLOBAL_CHAT_MARKDOWN_PERMISSION = "chat.use.markdown";
    public static String GLOBAL_CHAT_FORMAT = "&8[&6%server%&8] %display%%player% &8: &7%message%";

    public static boolean GLOBAL_TABLIST_ENABLED = false;
    public static String GLOBAL_TABLIST_HEADER = "&6McNative &ais a cool Minecraft Application Framework (MAF)";
    public static String GLOBAL_TABLIST_FOOTER = "&aWebsite: Https://mcnative.org";
    public static String GLOBAL_TABLIST_ORDER = "RANK";
    public static String GLOBAL_TABLIST_FORMAT_PLAYER = "%prefix% %player% %suffix%";
    public static String GLOBAL_TABLIST_FORMAT_SERVER = "&8<- %server% &8->";

    public static boolean NETWORK_MESSAGING_ENABLED = true;

    @DocumentKey("messaging.directConnect.enabled")
    public static boolean NETWORK_DIRECT_CONNECT_ENABLED = true;
    @DocumentKey("messaging.directConnect.password")
    public static String NETWORK_DIRECT_CONNECT_PASSWORD = StringUtil.getRandomString(15);

    @DocumentKey("messaging.packetManipulation.upstream")
    public static boolean NETWORK_PACKET_MANIPULATION_UPSTREAM_ENABLED = true;
    @DocumentKey("messaging.packetManipulation.downstream")
    public static boolean NETWORK_PACKET_MANIPULATION_DOWNSTREAM_ENABLED = false;

    @DocumentKey("messaging.directConnect")
    public static boolean MESSAGING_DIRECT_CONNECT = false;
    public static boolean MESSAGING_PASSWORD = false;

    public static class ConfiguredServer {

        private final String address;
        private final String permission;
        private final MinecraftServerType type;

        public ConfiguredServer(String address, String permission, MinecraftServerType type) {
            this.address = address;
            this.permission = permission;
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public String getPermission() {
            return permission;
        }

        public MinecraftServerType getType() {
            return type;
        }
    }
}
