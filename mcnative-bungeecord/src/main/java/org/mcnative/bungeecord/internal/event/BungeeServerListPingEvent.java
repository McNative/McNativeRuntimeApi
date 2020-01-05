/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 27.12.19, 17:16
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

package org.mcnative.bungeecord.internal.event;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.PendingConnection;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.event.ServerListPingEvent;

import java.net.InetSocketAddress;

//@Todo Implement
public class BungeeServerListPingEvent implements ServerListPingEvent {

    private final PendingConnection connection;
    private ServerPing ping;
    private ServerStatusResponse response;

    public BungeeServerListPingEvent(PendingConnection connection, ServerPing ping) {
        this.connection = connection;
        this.ping = ping;
    }

    @Override
    public InetSocketAddress getClientAddress() {
        return connection.getAddress();
    }

    @Override
    public InetSocketAddress getVirtualHost() {
        return connection.getVirtualHost();
    }

    @Override
    public ServerStatusResponse getResponse() {
        return null;
    }

    @Override
    public void setResponse(ServerStatusResponse ping) {

    }
}
