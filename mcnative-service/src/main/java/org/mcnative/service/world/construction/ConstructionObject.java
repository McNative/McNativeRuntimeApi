/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 16:40
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

package org.mcnative.service.world.construction;

import org.mcnative.service.location.Location;
import org.mcnative.service.location.Vector;
import org.mcnative.service.world.block.BlockDirection;
import org.mcnative.service.world.region.Region;

import java.util.Collection;

//With packets?
public interface ConstructionObject {

    String getName(String name);

    void setName(String name);


    Location getLocation();

    void place(Vector location);

    boolean isPlaced();

    void remove();


    Collection<VirtualBlock> getBlocks();


    void move(BlockDirection direction);


    static ConstructionObject createObjectOf(Vector pointA, Vector pointB){
        return null;
    }

    static ConstructionObject createObjectOf(Region region){
        return null;
    }
}
