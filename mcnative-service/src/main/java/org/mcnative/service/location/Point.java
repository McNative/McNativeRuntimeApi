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

package org.mcnative.service.location;

import org.mcnative.service.world.World;

public interface Point {

    double getX();

    double getY();

    double getZ();

    void setX(double x);

    void setY(double x);

    void setZ(double x);

    Point calculateMiddle(Point location);

    double distance(Point location);

    double distanceSquared(Point location);


    void add(Point location);

    void subtract(Point location);

    void multiply(Point multiplier);

    void divide(Point divider);


    Location toLocation(World world);

    static Point of(double x, double y, double z){
        return null;
    }
}
