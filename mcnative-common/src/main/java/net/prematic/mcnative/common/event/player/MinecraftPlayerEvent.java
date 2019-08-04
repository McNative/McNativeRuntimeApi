/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 03.08.19 13:54
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

package net.prematic.mcnative.common.event.player;

import net.prematic.mcnative.common.event.MinecraftEvent;
import net.prematic.mcnative.common.player.MinecraftPlayer;
import net.prematic.mcnative.common.player.OnlineMinecraftPlayer;

public class MinecraftPlayerEvent extends MinecraftEvent {

    private final MinecraftPlayer player;

    public MinecraftPlayerEvent(MinecraftPlayer player) {
        this.player = player;
    }

    public MinecraftPlayer getPlayer() {
        return player;
    }

    public OnlineMinecraftPlayer getOnlinePlayer() {
        return player.getAsOnlinePlayer();
    }

    /*
    PreLoginEvent
    LoginEvent
    PostLoginEvent
    PlayerJoinServerEvent - OnlyProxy
    JoinEvent - OnlyService



     */

}
