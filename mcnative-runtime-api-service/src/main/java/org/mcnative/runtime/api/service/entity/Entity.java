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

package org.mcnative.runtime.api.service.entity;

import org.mcnative.runtime.api.service.world.location.Location;
import org.mcnative.runtime.api.service.world.World;

import java.util.List;
import java.util.UUID;

public interface Entity {

    int getId();

    UUID getUniqueId();

    World getWorld();

    Location getLocation();

    boolean isOnGround();


    List<Entity> getNeighbors(double radius);

    List<Entity> getNeighbors(double x, double y, double z);


    void teleport(Location location);

    void teleport(int x,int y ,int z);

    void teleport(int x,int y ,int z, short pitch, short yaw);

    void teleport(World world, int x, int y , int z);

    void teleport(World world, int x,int y ,int z, short pitch, short yaw);

    void despawn();
}
