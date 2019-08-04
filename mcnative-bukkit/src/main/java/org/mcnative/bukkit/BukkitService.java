/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:46
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

package org.mcnative.bukkit;

import net.prematic.libraries.command.manager.CommandManager;
import net.prematic.libraries.concurrent.TaskScheduler;
import net.prematic.libraries.event.EventManager;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.mcnative.common.McNative;
import net.prematic.mcnative.common.MinecraftPlatform;
import net.prematic.mcnative.common.player.MinecraftPlayer;
import net.prematic.mcnative.common.registry.Registry;
import net.prematic.mcnative.service.MinecraftService;
import net.prematic.mcnative.service.ObjectCreator;
import net.prematic.mcnative.service.world.World;

import java.util.UUID;

public class BukkitService implements MinecraftService {

    private final TaskScheduler scheduler;
    private final CommandManager commandManager;
    private final EventManager eventManager;

    public TaskScheduler getScheduler() {
        return this.scheduler;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public PluginManager<McNative> getPluginManager() {
        return null;
    }

    public MinecraftPlayer getPlayer(UUID uniqueId) {
        return null;
    }

    public MinecraftPlayer getPlayer(long xBoxId) {
        return null;
    }

    public MinecraftPlayer getPlayer(String name) {
        return null;
    }

    public ObjectCreator getObjectCreator() {
        return null;
    }

    public World getDefaultWorld() {
        return null;
    }

    public World getWorld(String name) {
        return null;
    }

    public MinecraftPlatform getPlatform() {
        return null;
    }

    public Registry getRegistry() {
        return null;
    }
}
