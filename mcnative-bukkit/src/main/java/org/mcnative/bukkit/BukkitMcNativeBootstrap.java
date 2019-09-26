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

import net.prematic.libraries.event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcnative.bukkit.plugin.BukkitEventManager;
import org.mcnative.common.McNative;

public class BukkitMcNativeBootstrap extends JavaPlugin {

    @Override
    public void onLoad() {
        if(McNative.isAvailable()) return;

        getLogger().info("McNative is starting, please wait...");

        EventManager eventManager = BukkitEventManager.initialize();


        getLogger().info("McNative successfully started.");
    }

    @Override
    public void onDisable() {

        getLogger().info("McNative successfully shut down.");
    }
}
