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

package org.mcnative.bungeecord.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.messaging.MessageChannelListener;
import org.mcnative.common.network.messaging.MessageReceiver;
import org.mcnative.common.network.messaging.MessagingProvider;
import org.mcnative.common.protocol.MinecraftProtocolUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

/*
Request
    ToProxy
        UUID | Receiver
        String | Channel
        UUID | Request Id
        Document | Request
    ToClient
        UUID | Sender
        String | Channel
        UUID | Request Id
        Document | Request

 */
public class PluginMessageGateway implements MessagingProvider,Listener {

    private final String CHANNEL_NAME_REQUEST = "mcnative:request";

    private final String CHANNEL_NAME_RESPONSE = "mcnative:response";

    private final Executor executor;
    private final BungeeCordServerMap serverMap;
    private final Map<UUID,CompletableFuture<Document>> resultListeners;

    public PluginMessageGateway(Executor executor, BungeeCordServerMap serverMap) {
        this.executor = executor;
        this.serverMap = serverMap;

        this.resultListeners = new HashMap<>();
    }

    @Override
    public void sendMessage(MessageReceiver receiver, String channel, Document request,UUID requestId) {
        if(receiver instanceof MinecraftServer){
            byte[] data = writeData(channel,request,requestId,McNative.getInstance().getNetwork().getLocalIdentifier().getUniqueId());
            ((MinecraftServer) receiver).sendData(CHANNEL_NAME_REQUEST,data);
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

    private byte[] writeData(String channel, Document request,UUID requestId, UUID sender) {
        ByteBuf buffer = Unpooled.directBuffer();
        MinecraftProtocolUtil.writeUUID(buffer,sender);//Sender
        MinecraftProtocolUtil.writeString(buffer,channel);//Channel
        MinecraftProtocolUtil.writeUUID(buffer,requestId);//RequestId
        DocumentFileType.BINARY.getWriter().write(new ByteBufOutputStream(buffer),StandardCharsets.UTF_8,request,false);//Request
        byte[] data = new byte[buffer.readableBytes()];
        buffer.readBytes(data);
        buffer.release();
        return data;
    }

    private byte[] rewriteData(ByteBuf buffer,MinecraftServer sender){
        buffer.resetReaderIndex();
        MinecraftProtocolUtil.writeUUID(buffer,sender.getIdentifier().getUniqueId());
        byte[] data = new byte[buffer.readableBytes()];
        buffer.readBytes(data);
        return data;
    }

    @EventHandler
    public void handleRequest(PluginMessageEvent event){
        if(event.getSender() instanceof Server){
            MinecraftServer sender = serverMap.getMappedServer(((Server) event.getSender()).getInfo());
            if(event.getTag().equals(CHANNEL_NAME_RESPONSE)){
                ByteBuf buffer = Unpooled.copiedBuffer(event.getData());
                UUID receiver = MinecraftProtocolUtil.readUUID(buffer);//Receiver
                if(NetworkIdentifier.BROADCAST.getUniqueId().equals(receiver) || NetworkIdentifier.BROADCAST_PROXY.getUniqueId().equals(receiver)){
                    String channel = MinecraftProtocolUtil.readString(buffer);
                    MessageChannelListener listener = McNative.getInstance().getLocal().getMessageMessageChannelListener(channel);
                    if(listener != null){
                        UUID requestId = MinecraftProtocolUtil.readUUID(buffer);//Request Id
                        Document data = DocumentFileType.BINARY.getReader().read(new ByteBufInputStream(buffer));//Request
                        listener.onMessageReceive(sender,requestId,data);
                    }
                }
                byte[] data = rewriteData(buffer,sender);
                if(NetworkIdentifier.BROADCAST.getUniqueId().equals(receiver) || NetworkIdentifier.BROADCAST_SERVER.getUniqueId().equals(receiver)){
                    for (MinecraftServer server : McNative.getInstance().getNetwork().getServers()) {
                        server.sendData(CHANNEL_NAME_REQUEST,data);
                    }
                }else{
                    MinecraftServer server = McNative.getInstance().getNetwork().getServer(receiver);
                    server.sendData(CHANNEL_NAME_REQUEST,data);
                }
                buffer.release();
            }else if(event.getTag().equals(CHANNEL_NAME_RESPONSE)){
                ByteBuf buffer = Unpooled.copiedBuffer(event.getData());
                UUID requestId = MinecraftProtocolUtil.readUUID(buffer);//Request Id
                Document response = DocumentFileType.BINARY.getReader().read(new ByteBufInputStream(buffer));//Response
                buffer.release();
                CompletableFuture<Document> resultListener = resultListeners.get(requestId);
                if(resultListener != null) resultListener.complete(response);
            }
        }
    }

}
