/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:45
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

package org.mcnative.service;

import org.mcnative.common.LocalService;
import org.mcnative.common.McNative;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.service.world.World;
import org.mcnative.service.world.WorldCreator;

public interface MinecraftService extends LocalService, MinecraftServer {

    ObjectCreator getObjectCreator();

    World getDefaultWorld();

    World getWorld(String name);

    World loadWorld(String name);

    void unloadWorld(World world, boolean save);

    default void unloadWorld(World world) {
        unloadWorld(world, true);
    }

    World createWorld(WorldCreator creator);


    static MinecraftService getInstance(){
        return (MinecraftService) McNative.getInstance().getLocal();
    }
}
