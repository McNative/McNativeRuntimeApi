/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.07.20, 11:23
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

package org.mcnative.network.integrations;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.exception.OperationFailedException;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.player.chat.ChatPosition;
import org.mcnative.common.protocol.MinecraftEdition;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.text.components.MessageComponent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class McNativePlayerExecutor {

    public static int getPing(UUID uniqueId) {
        try {
            return getPingAsync(uniqueId).get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new OperationFailedException(e);
        }
    }

    public static CompletableFuture<Integer> getPingAsync(UUID uniqueId) {
        CompletableFuture<Integer> resultFuture = new CompletableFuture<>();
        executePlayerBasedFuture(uniqueId,Document.newDocument()
                .set("action","getPing"))
                .thenAccept(documentEntries -> resultFuture.complete(documentEntries.getInt("ping")));
        return resultFuture;
    }

    public static void connect(UUID uniqueId,MinecraftServer target, ServerConnectReason reason) {
        executePlayerBased(uniqueId,Document.newDocument()
                .set("action","connect")
                .set("target",target.getName())
                .set("reason",reason));
    }

    public static CompletableFuture<ServerConnectResult> connectAsync(UUID uniqueId,MinecraftServer target, ServerConnectReason reason) {
        CompletableFuture<ServerConnectResult> resultFuture = new CompletableFuture<>();
        executePlayerBasedFuture(uniqueId,Document.newDocument()
                .set("action","connectAsync")
                .set("target",target.getName())
                .set("reason",reason))
                .thenAccept(documentEntries -> resultFuture.complete(documentEntries.getObject("result",ServerConnectResult.class)));
        return resultFuture;
    }

    public static void kick(UUID uniqueId,MessageComponent<?> message, VariableSet variables) {
        executePlayerBased(uniqueId,Document.newDocument()
                .set("action","kick")//@Todo force compile to 1.8
                .set("message",message.compile(MinecraftProtocolVersion.JE_1_8,variables)));
    }

    public static void performCommand(UUID uniqueId,String command) {
        executePlayerBased(uniqueId,Document.newDocument()
                .set("action","performCommand")
                .set("command",command));
    }

    public static void chat(UUID uniqueId,String message) {
        executePlayerBased(uniqueId,Document.newDocument()
                .set("action","chat")
                .set("message",message));
    }

    public static void sendMessage(UUID uniqueId,ChatPosition position, MessageComponent<?> component, VariableSet variables) {
        executePlayerBased(uniqueId,Document.newDocument()
                .set("action","sendMessage")
                .set("position",position.getId())
                .set("text",component.compile(MinecraftProtocolVersion.getLatest(MinecraftEdition.JAVA),variables)));
    }

    private static void executePlayerBased(UUID uniqueId, Document data){
        data.set("uniqueId",uniqueId);
        McNative.getInstance().getNetwork().getMessenger()
                .sendMessage(NetworkIdentifier.BROADCAST_PROXY,"mcnative_player",data);
    }

    private static CompletableFuture<Document> executePlayerBasedFuture(UUID uniqueId, Document data){
        data.set("uniqueId",uniqueId);
        return McNative.getInstance().getNetwork().getMessenger()
                .sendQueryMessageAsync(NetworkIdentifier.BROADCAST_PROXY,"mcnative_player",data);
    }
}
