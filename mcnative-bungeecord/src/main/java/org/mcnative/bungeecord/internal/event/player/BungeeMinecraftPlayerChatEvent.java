/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 05.01.20, 16:30
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

package org.mcnative.bungeecord.internal.event.player;

import net.md_5.bungee.api.event.ChatEvent;
import org.mcnative.common.event.player.MinecraftPlayerChatEvent;
import org.mcnative.common.player.ChatChannel;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;

public class BungeeMinecraftPlayerChatEvent implements MinecraftPlayerChatEvent {

    private final ChatEvent original;
    private final OnlineMinecraftPlayer player;
    private ChatChannel channel;

    public BungeeMinecraftPlayerChatEvent(ChatEvent original, OnlineMinecraftPlayer player) {
        this.original = original;
        this.player = player;
        this.channel = player.getChatChannel();
    }

    @Override
    public ChatChannel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(ChatChannel channel) {
        this.channel = channel;
    }

    @Override
    public String getMessage() {
        return original.getMessage();
    }

    @Override
    public void setMessage(String message) {
        original.setMessage(message);
    }

    @Override
    public boolean isCancelled() {
        return original.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        original.setCancelled(cancelled);
    }

    @Override
    public OnlineMinecraftPlayer getOnlinePlayer() {
        return player;
    }

    @Override
    public MinecraftPlayer getPlayer() {
        return player;
    }
}
