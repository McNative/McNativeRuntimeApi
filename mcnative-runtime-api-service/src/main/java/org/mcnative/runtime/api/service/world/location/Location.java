/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 12:24
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

package org.mcnative.runtime.api.service.world.location;

import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.service.world.Chunk;
import org.mcnative.runtime.api.service.world.World;
import org.mcnative.runtime.api.utils.positioning.Position;
import org.mcnative.runtime.api.utils.positioning.Vector;

public interface Location extends Position {

    World getWorld();

    Chunk getChunk();

    void setWorld(World world);

    Position toPosition();

    Vector toVector();

    static Location of(int x, int y, int z,World world){
        return of(x, y, z,0,0, world);
    }

    static Location of(double x, double y, double z,World world){
        return of(x, y, z,0,0, world);
    }

    static Location of(int x, int y, int z, float pitch, float yaw,World world){
        return of((double) x, y, z, pitch, yaw, world);
    }

    static Location of(double x, double y, double z, float pitch, float yaw,World world){
        return McNative.getInstance().getObjectFactory().createObject(Location.class,x,y,z,pitch,yaw,world);
    }

    @Override
    Location clone();
}
