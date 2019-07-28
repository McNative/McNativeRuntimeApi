/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.07.19 22:26
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

package net.prematic.mcnative.common;

import net.prematic.libraries.concurrent.TaskScheduler;
import net.prematic.mcnative.common.player.MinecraftPlayer;
import net.prematic.mcnative.common.protocol.support.ProtocolCheck;
import net.prematic.mcnative.common.registry.Registry;

import java.util.UUID;

public interface McNative {

    MinecraftPlatform getPlatform();

    Registry getRegistry();

    TaskScheduler getScheduler();

    //CommandManager getCommandManager();

    //EventManager getEventManager();

    //PluginManager gePluginManager();



    MinecraftPlayer getPlayer(UUID uniqueId);

    MinecraftPlayer getPlayer(long xBoxId);

    MinecraftPlayer getPlayer(String name);




    default ProtocolCheck check(){
        return getPlatform().check();
    }

    static McNative getInstance() {
        return InstanceHolder.INSTANCE;
    }

    static void setInstance(McNative instance) {
        if(InstanceHolder.INSTANCE != null) throw new IllegalArgumentException("Instance is already set.");
        InstanceHolder.INSTANCE = instance;
    }

    class InstanceHolder {

        private static McNative INSTANCE;
    }
}