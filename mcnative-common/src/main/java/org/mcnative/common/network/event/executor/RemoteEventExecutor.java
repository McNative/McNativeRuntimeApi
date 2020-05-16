/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.05.20, 18:06
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

package org.mcnative.common.network.event.executor;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.event.executor.EventExecutor;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.McNative;
import org.mcnative.common.network.component.server.MinecraftServer;

public class RemoteEventExecutor implements EventExecutor {

    private final MinecraftServer receiver;
    private final byte priority;

    public RemoteEventExecutor(MinecraftServer receiver, byte priority) {
        this.receiver = receiver;
        this.priority = priority;
    }

    @Override
    public byte getPriority() {
        return priority;
    }

    @Override
    public ObjectOwner getOwner() {
        return McNative.getInstance();
    }

    @Override
    public void execute(Object... events) {
        if(events.length > 0){
            Document eventData = Document.newDocument(events[0]);
            Document result = receiver.sendQueryMessage("mcnative_event",eventData);
        }
    }
}
