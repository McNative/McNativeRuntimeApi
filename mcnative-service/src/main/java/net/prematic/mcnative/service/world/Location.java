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

package net.prematic.mcnative.service.world;

public interface Location extends Point{

    float getPitch();

    float getYaw();

    World getWorld();

    Chunk getChunk();

    Location calculateMiddle(Location location);

    double distance(Location location);

    double distanceSquared(Location location);

    void setPitch(float pitch);

    void setYaw(float yaw);

    void setWorld(World world);

    void add(Location location);

    void subtract(Location location);

    void multiply(Location multiplier);

    void divide(Location divider);

    Point toPoint();
}
