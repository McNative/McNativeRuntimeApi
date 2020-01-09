/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.01.20, 19:55
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

package org.mcnative.bungeecord.server;

import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.proxy.ServerConnectHandler;

//@Todo implement
public class PriorityServerConnectHandler implements ServerConnectHandler {

    @Override
    public MinecraftServer getFallbackServer(MinecraftPlayer player, MinecraftServer kickedFrom) {
        return null;
    }

    @Override
    public MessageComponent<?> getNoFallBackServerMessage(MinecraftPlayer player) {
        return null;
    }
}
