/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.03.20, 20:27
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

package org.mcnative.common.event.player.design;

import org.mcnative.common.event.player.MinecraftOnlinePlayerEvent;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;

public class MinecraftPlayerDesignUpdateEvent implements MinecraftOnlinePlayerEvent {

    private final OnlineMinecraftPlayer player;
    private PlayerDesign design;

    public MinecraftPlayerDesignUpdateEvent(OnlineMinecraftPlayer player, PlayerDesign design) {
        this.player = player;
        this.design = design;
    }

    public PlayerDesign getDesign() {
        return design;
    }

    public void setDesign(PlayerDesign design) {
        this.design = design;
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


