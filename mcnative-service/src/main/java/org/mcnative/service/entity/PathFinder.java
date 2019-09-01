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

package org.mcnative.service.entity;

import org.mcnative.service.entity.living.LivingEntity;
import org.mcnative.service.location.Location;

import java.util.List;

public interface PathFinder {

    LivingEntity getEntity();

    PathResult getCurrentPath();

    PathResult findPath(Location location);

    PathResult findPath(LivingEntity target);

    default boolean moveTo(Location location) {
        return moveTo(location, 1);
    }

    default boolean moveTo(Location location, double speed) {
        PathResult path = findPath(location);
        return path != null && moveTo(path, speed);
    }

    default boolean moveTo(LivingEntity target) {
        return moveTo(target, 1);
    }

    default boolean moveTo(LivingEntity target, double speed) {
        PathResult path = findPath(target);
        return path != null && moveTo(path, speed);
    }

    default boolean moveTo(PathResult path) {
        return moveTo(path, 1);
    }

    boolean moveTo(PathResult path, double speed);

    boolean hasPath();

    void stopPathfinding();

    interface PathResult {

        List<Location> getPoints();

        int getNextPointIndex();

        Location getNextPoint();

        Location getFinalPoint();
    }
}
