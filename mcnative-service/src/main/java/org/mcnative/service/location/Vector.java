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

public interface Vector {

    double getX();

    double getY();

    double getZ();

    void setX(double x);

    void setY(double x);

    void setZ(double x);


    Vector middle(Vector location);

    double distance(Vector location);

    double distanceSquared(Vector location);

    float angle(Vector otherPoint);


    void add(Vector location);

    void subtract(Vector location);

    void multiply(Vector multiplier);

    void divide(Vector divider);


    double length();

    double lengthSquared();

    boolean isIn(Vector min, Vector max);

    boolean isOut(Vector min, Vector max);

    boolean isIn(Vector original, Offset offset);

    boolean isOut(Vector original, Offset offset);


    Location toLocation(World world);


    static Vector of(double x, double y, double z){
        return null;
    }

    static Vector min(Vector vector1, Vector vector2) {
        return of(Math.min(vector1.getX(), vector2.getX()), Math.min(vector1.getY(), vector2.getY()), Math.min(vector1.getZ(), vector2.getZ()));
    }

    static Vector max(Vector vector1,Vector vector2) {
        return of(Math.max(vector1.getX(), vector2.getX()), Math.max(vector1.getY(), vector2.getY()), Math.max(vector1.getZ(), vector2.getZ()));
    }

}
