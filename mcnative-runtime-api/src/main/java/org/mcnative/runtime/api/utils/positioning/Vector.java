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

package org.mcnative.runtime.api.utils.positioning;

import org.mcnative.runtime.api.McNative;

public interface Vector extends Cloneable {

    double getX();

    default int getBlockX() {
        return (int) getX();
    }

    double getY();

    default int getBlockY() {
        return (int) getY();
    }

    double getZ();

    default int getBlockZ() {
        return (int) getZ();
    }

    Vector setX(double x);

    Vector setY(double y);

    Vector setZ(double z);


    Vector middle(Vector other);

    double distance(Vector other);

    double distanceSquared(Vector other);

    float angle(Vector other);


    Vector add(Vector other);

    Vector add(double x, double y, double z);

    Vector add(int adder);

    Vector add(double adder);


    Vector subtract(Vector other);

    Vector subtract(double x, double y, double z);

    Vector subtract(int subtractor);

    Vector subtract(double subtractor);


    Vector multiply(Vector multiplier);

    Vector multiply(double x, double y, double z);

    Vector multiply(int multiplier);

    Vector multiply(double multiplier);


    Vector divide(Vector divider);

    Vector divide(double x, double y, double z);

    Vector divide(int divider);

    Vector divide(double divider);


    double length();

    double lengthSquared();


    boolean isIn(Vector min, Vector max);

    boolean isOut(Vector min, Vector max);

    double dot(Vector other);

    static Vector of(int x, int y, int z){
        return of((double) x, y, z);
    }

    static Vector of(double x, double y, double z){
        return McNative.getInstance().getObjectFactory().createObject(Vector.class,x,y,z);
    }

    static Vector min(Vector vector1, Vector vector2) {
        return of(Math.min(vector1.getX(), vector2.getX()), Math.min(vector1.getY(), vector2.getY()), Math.min(vector1.getZ(), vector2.getZ()));
    }

    static Vector max(Vector vector1,Vector vector2) {
        return of(Math.max(vector1.getX(), vector2.getX()), Math.max(vector1.getY(), vector2.getY()), Math.max(vector1.getZ(), vector2.getZ()));
    }

    Vector clone();
}
