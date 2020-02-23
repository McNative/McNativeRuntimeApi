/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.01.20, 18:26
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

package org.mcnative.bukkit.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.mcnative.bukkit.McNativeLauncher;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.messaging.MessageReceiver;
import org.mcnative.common.network.messaging.MessagingChannelListener;
import org.mcnative.common.network.messaging.MessagingProvider;
import org.mcnative.common.protocol.MinecraftProtocolUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

public class PluginMessageGateway implements PluginMessageListener,MessagingProvider {

    private final String CHANNEL_NAME_REQUEST = "mcnative:request";

    private final String CHANNEL_NAME_RESPONSE = "mcnative:response";

    private final Executor executor;
    private final Map<UUID,CompletableFuture<Document>> resultListeners;

    private Player transport;

    public PluginMessageGateway(Executor executor) {
        this.executor = executor;

        this.resultListeners = new HashMap<>();

        Bukkit.getMessenger().registerIncomingPluginChannel(McNativeLauncher.getPlugin(),CHANNEL_NAME_REQUEST,this);
        Bukkit.getMessenger().registerIncomingPluginChannel(McNativeLauncher.getPlugin(),CHANNEL_NAME_RESPONSE,this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(McNativeLauncher.getPlugin(),CHANNEL_NAME_REQUEST);
        Bukkit.getMessenger().registerOutgoingPluginChannel(McNativeLauncher.getPlugin(),CHANNEL_NAME_RESPONSE);
    }

    @Override
    public String getTechnology() {
        return "Plugin Messages";
    }

    @Override
    public void sendMessage(NetworkIdentifier receiver, String channel, Document request, UUID requestId) {
        byte[] data = writeData(channel,request,requestId,receiver.getUniqueId());
        transport.sendPluginMessage(McNativeLauncher.getPlugin(),CHANNEL_NAME_REQUEST,data);
    }

    @Override
    public void sendMessage(MessageReceiver receiver, String channel, Document request,UUID requestId) {
        if(receiver instanceof MinecraftServer){
            sendMessage(receiver.getIdentifier(), channel, request, requestId);
        }else throw new IllegalArgumentException("Plugin messaging requires a minecraft server as receiver");
    }

    @Override
    public Document sendQueryMessage(MessageReceiver receiver, String channel, Document request) {
        try {
            return sendQueryMessageAsync(receiver,channel,request).get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new IllegalArgumentException("");
        }
    }

    @Override
    public CompletableFuture<Document> sendQueryMessageAsync(MessageReceiver receiver, String channel, Document request) {
        CompletableFuture<Document> result = new CompletableFuture<>();
        UUID id = UUID.randomUUID();
        this.resultListeners.put(id,result);
        executor.execute(()-> sendMessage(receiver, channel, request,id));
        return result;
    }

    private byte[] writeData(String channel, Document request,UUID requestId, UUID receiver) {
        ByteBuf buffer = Unpooled.directBuffer();
        MinecraftProtocolUtil.writeUUID(buffer,receiver);//Receiver
        MinecraftProtocolUtil.writeString(buffer,channel);//Channel
        MinecraftProtocolUtil.writeUUID(buffer,requestId);//RequestId
        DocumentFileType.BINARY.getWriter().write(new ByteBufOutputStream(buffer),StandardCharsets.UTF_8,request,false);//Request
        byte[] data = new byte[buffer.readableBytes()];
        buffer.readBytes(data);
        buffer.release();
        return data;
    }

    @Override
    public void onPluginMessageReceived(String tag, Player unused0, byte[] data) {
        ByteBuf buffer = Unpooled.copiedBuffer(data);
        UUID senderId = MinecraftProtocolUtil.readUUID(buffer);
        String channel = MinecraftProtocolUtil.readString(buffer);
        UUID requestId = MinecraftProtocolUtil.readUUID(buffer);
        Document document = DocumentFileType.BINARY.getReader().read(new ByteBufInputStream(buffer));

        if(tag.equals(CHANNEL_NAME_REQUEST)){
            MessagingChannelListener listener = McNative.getInstance().getLocal().getMessageMessageChannelListener(channel);
            if(listener != null){
                MinecraftServer sender = McNative.getInstance().getNetwork().getServer(senderId);
                Document result = listener.onMessageReceive(sender,requestId,document);
                if(result != null){
                    byte[] response = writeData(channel,result,requestId,senderId);
                    sender.sendData(CHANNEL_NAME_RESPONSE,response);
                }
            }
        }else if(tag.equals(CHANNEL_NAME_RESPONSE)){
            CompletableFuture<Document> resultListener = resultListeners.get(requestId);
            if(resultListener != null) resultListener.complete(document);
        }

        buffer.release();
    }

   /*
    ToClient
        UUID | Sender
        String | Channel
        UUID | Request Id
        Document | Request
    */
}
