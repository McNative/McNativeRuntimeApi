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

package org.mcnative.runtime.api.service.world.region;

import org.mcnative.runtime.api.service.entity.living.Player;
import org.mcnative.runtime.api.service.world.location.Location;
import org.mcnative.runtime.api.utils.positioning.Vector;
import org.mcnative.runtime.api.service.world.World;
import org.mcnative.runtime.api.service.world.WorldSequence;
import org.mcnative.runtime.api.service.world.block.Block;

import java.util.UUID;

public interface Region extends WorldSequence,Iterable<Block> {

    String getName();

    UUID getUniqueId();

    World getWorld();

    Vector getPointA();

    Vector getPointB();

    Vector[] getCorners();

    Block[] getCornerBlocks();

    default boolean isInside(Player player){
        return isInside(player.getLocation());
    }

    boolean isInside(Location player);

    default boolean isOutside(Player player){
        return isOutside(player.getLocation());
    }

    boolean isOutside(Location player);

    void setName(String name);

    void setPointA(Vector location);

    void setPointB(Vector location);


    static Region newRegion(String name){
        return null;
    }

    static Region newRegion(String name, Vector pointA, Vector pointB){
        return null;
    }

}
