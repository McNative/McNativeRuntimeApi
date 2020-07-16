/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.07.20, 11:46
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

package org.mcnative.bungeecord.network;

import net.pretronic.libraries.document.Document;
import org.mcnative.common.McNative;
import org.mcnative.common.network.messaging.MessageReceiver;
import org.mcnative.common.network.messaging.MessagingChannelListener;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.components.RawMessageComponent;

import java.util.UUID;

public class McNativeGlobalActionListener implements MessagingChannelListener {

    @Override
    public Document onMessageReceive(MessageReceiver sender, UUID requestId, Document request) {
        String action = request.getString("action");
        if(action.equalsIgnoreCase("broadcast")){
            String permission = request.getString("permission");
            Document jsonText = request.getDocument("message");
            MessageComponent<?> component = new RawMessageComponent("Pre compiled","Pre compiled",jsonText);

            for (OnlineMinecraftPlayer onlinePlayer : McNative.getInstance().getLocal().getOnlinePlayers()) {
                if(permission == null || onlinePlayer.hasPermission(permission)){
                    onlinePlayer.sendMessage(component);
                }
            }

        }else if(action.equalsIgnoreCase("kickAll")){
            Document jsonText = request.getDocument("message");
            MessageComponent<?> component = new RawMessageComponent("Pre compiled","Pre compiled",jsonText);
            for (OnlineMinecraftPlayer onlinePlayer : McNative.getInstance().getLocal().getOnlinePlayers()) {
                onlinePlayer.kick(component);
            }
        }
        return null;
    }
}
