/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.10.19, 10:04
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

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.prematic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.event.player.login.MinecraftPlayerLoginEvent;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.text.components.MessageComponent;

public class BungeeMinecraftLoginEvent implements MinecraftPlayerLoginEvent {

    private final LoginEvent original;
    private final PendingConnection connection;
    private final OnlineMinecraftPlayer player;

    private MessageComponent<?> cancelReason;
    private VariableSet variables;

    public BungeeMinecraftLoginEvent(LoginEvent original,PendingConnection connection, OnlineMinecraftPlayer player) {
        this.original = original;
        this.connection = connection;
        this.player = player;
    }

    @Override
    public MessageComponent<?> getCancelReason() {
        return cancelReason;
    }

    @Override
    public VariableSet getCancelReasonVariables() {
        return variables;
    }

    @Override
    public PendingConnection getConnection() {
        return connection;
    }

    @Override
    public void setCancelReason(MessageComponent<?> cancelReason, VariableSet variables) {
        this.cancelReason = cancelReason;
        this.variables = variables;
        this.original.setCancelReason((BaseComponent) null);
    }

    @Override
    public boolean isCancelled() {
        return original.isCancelled();
    }

    @Override
    public void setCancelled(boolean canceled) {
        original.setCancelled(canceled);
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
