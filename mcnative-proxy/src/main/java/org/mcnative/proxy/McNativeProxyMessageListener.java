/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 08.10.19, 18:54
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

import net.prematic.libraries.document.Document;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.messaging.PluginMessageHelper;
import org.mcnative.common.messaging.PluginMessageListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class McNativeProxyMessageListener implements PluginMessageListener {

    @Override
    public void onMessageReceive(MinecraftConnection sender, UUID requestId, InputStream input, OutputStream response) {
        Document request = PluginMessageHelper.getAsDocument(input);
        String action = request.getString("action");
        /*
        General
            getServers -> All server names and unique ids
            getServerInfo -> Server information
            getAllServerInfos
        Player
            getOnlineCount
            getOnlinePlayers
            getServer
            connectToServer
            connectToFallback
            disconnect
            sendMessage

        EventListeners
            RegisterRemoteEventListener
            UnregisterRemoteEventListener
         */
    }
}
