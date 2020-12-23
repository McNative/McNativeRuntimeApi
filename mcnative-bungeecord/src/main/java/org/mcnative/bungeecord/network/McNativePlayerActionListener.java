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
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.McNative;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.network.messaging.MessageReceiver;
import org.mcnative.common.network.messaging.MessagingChannelListener;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.Title;
import org.mcnative.common.player.chat.ChatPosition;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.components.RawMessageComponent;
import org.mcnative.proxy.ProxyService;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class McNativePlayerActionListener implements MessagingChannelListener {

    @Override
    public Document onMessageReceive(MessageReceiver sender, UUID requestId, Document request) {
        String action = request.getString("action");
        UUID uniqueId = request.getObject("uniqueId",UUID.class);
        ConnectedMinecraftPlayer player = McNative.getInstance().getLocal().getConnectedPlayer(uniqueId);
        if(player != null){
            if(action.equalsIgnoreCase("getPing")){
                return Document.newDocument().set("ping",player.getPing());
            }else if(action.equalsIgnoreCase("connect")){
                MinecraftServer target = ProxyService.getInstance().getServer(request.getString("target"));
                ServerConnectReason reason = request.getObject("reason",ServerConnectReason.class);
                player.connect(target,reason);
            }else if(action.equalsIgnoreCase("connectAsync")){
                MinecraftServer target = ProxyService.getInstance().getServer(request.getString("target"));
                ServerConnectReason reason = request.getObject("reason",ServerConnectReason.class);
                try {
                    ServerConnectResult result = player.connectAsync(target,reason).get(3, TimeUnit.SECONDS);
                    return Document.newDocument().set("result",result);
                } catch (InterruptedException | ExecutionException | TimeoutException ignored) {}


            }else if(action.equalsIgnoreCase("kick")){
                Document jsonText = request.getDocument("message");
                player.kick(new RawMessageComponent("Pre compiled","Pre compiled",jsonText));
            }else if(action.equalsIgnoreCase("performCommand")){
                String command = request.getString("command");
                player.performCommand(command);
            }else if(action.equalsIgnoreCase("chat")){
                String message = request.getString("message");
                player.chat(message);
            }else if(action.equalsIgnoreCase("sendMessage")){
                Document jsonText = request.getDocument("text");
                ChatPosition position = ChatPosition.of(request.getByte("position"));
                MessageComponent<?> text = Text.decompile(jsonText);
                player.sendMessage(position,text);
            }else if(action.equalsIgnoreCase("sendActionbar")){
                Document jsonText = request.getDocument("text");
                int staySeconds = request.getInt("staySeconds");
                MessageComponent<?> text = Text.decompile(jsonText);
                player.sendActionbar(text, VariableSet.createEmpty(),staySeconds);
            }else if(action.equalsIgnoreCase("sendTitle")){
                Title title = new Title();

                int[] timing = request.getArray("timing",new int[]{});
                title.setTiming(timing);

                Document jsonTitle = request.getDocument("title");
                if(jsonTitle != null) title.title(Text.decompile(jsonTitle));

                Document jsonSubTitle = request.getDocument("subTitle");
                if(jsonSubTitle != null) title.subTitle(Text.decompile(jsonSubTitle));

                player.sendTitle(title);
            }else if(action.equalsIgnoreCase("resetTitle")){
                player.resetTitle();
            }
        }
        return null;
    }
}
