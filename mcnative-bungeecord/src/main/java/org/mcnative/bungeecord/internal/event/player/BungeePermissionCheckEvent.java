/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.11.19, 16:15
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

import net.pretronic.libraries.command.sender.CommandSender;
import org.mcnative.common.event.PermissionCheckEvent;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;

public class BungeePermissionCheckEvent implements PermissionCheckEvent {

    private final net.md_5.bungee.api.event.PermissionCheckEvent original;
    private final CommandSender sender;
    private final PermissionHandler handler;

    public BungeePermissionCheckEvent(net.md_5.bungee.api.event.PermissionCheckEvent original
            , CommandSender sender, PermissionHandler handler) {
        this.original = original;
        this.sender = sender;
        this.handler = handler;
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public PermissionHandler getHandler() {
        return handler;
    }

    @Override
    public boolean hasHandler() {
        return handler != null;
    }

    @Override
    public String getPermission() {
        return original.getPermission();
    }

    @Override
    public boolean hasPermission() {
        return original.hasPermission();
    }

    @Override
    public void setHasPermission(boolean hasPermission) {
        original.setHasPermission(hasPermission);
    }
}
