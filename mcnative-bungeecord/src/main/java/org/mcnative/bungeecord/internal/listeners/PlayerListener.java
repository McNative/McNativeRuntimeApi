/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 15.09.19, 16:12
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

package org.mcnative.bungeecord.internal.listeners;


import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.mcnative.bungeecord.player.BungeeCordPlayerManager;

public class PlayerListener implements Listener {

    private final BungeeCordPlayerManager playerManager;

    public PlayerListener(BungeeCordPlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerPreLogin(PreLoginEvent event){
    }

    @EventHandler
    public void onPlayerLogin(LoginEvent event){
        playerManager.registerPlayer(null);
    }

    @EventHandler
    public void onPlayerLogout(PlayerDisconnectEvent event){
        playerManager.unregisterPlayer(event.getPlayer());
    }
}
