/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 12:52
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

package org.mcnative.runtime.api.network.messaging;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.network.EventOrigin;
import org.mcnative.runtime.api.network.NetworkIdentifier;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface MessageReceiver extends EventOrigin {

    NetworkIdentifier getIdentifier();

    void sendMessage(String channel, Document request);

    @Override
    default String getName(){
        return getIdentifier().getName();
    }

    @Override
    default UUID getUniqueId(){
        return getIdentifier().getUniqueId();
    }

    @Override
    default boolean isLocal(){
        return false;
    }

    default Document sendQueryMessage(String channel, Document request){
        try {
            return sendQueryMessageAsync(channel, request).get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new IllegalArgumentException("Query timed out");
        }
    }

    CompletableFuture<Document> sendQueryMessageAsync(String channel, Document request);
}
