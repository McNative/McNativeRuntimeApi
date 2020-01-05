/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.01.20, 20:37
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

package org.mcnative.common.network.messaging;

import net.prematic.libraries.document.Document;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MessagingProvider {

    default void sendMessage(MessageReceiver receiver, String channel, Document request){
        sendMessage(receiver, channel, request,UUID.randomUUID());
    }

    void sendMessage(MessageReceiver receiver, String channel, Document request, UUID requestId);

    Document sendQueryMessage(MessageReceiver receiver, String channel, Document request);

    CompletableFuture<Document> sendQueryMessageAsync(MessageReceiver receiver, String channel, Document request);

}
