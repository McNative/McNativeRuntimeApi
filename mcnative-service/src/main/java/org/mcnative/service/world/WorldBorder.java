/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.08.19, 20:02
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

package org.mcnative.service.world;

import org.mcnative.service.location.Location;
import org.mcnative.service.location.LocationAble;
import org.mcnative.service.location.Vector;

public interface WorldBorder {

    double getSize();

    void setSize(double size);


    Location getCenter();

    void setCenter(Vector location);


    Location[] getCorners();


    double getDamageBuffer();

    void setDamageBuffer(double blocks);


    double getDamageAmount();

    void setDamageAmount(double damage);



    int getWarningTime();

    void setWarningTime(int seconds);


    int getWarningDistance();

    void setWarningDistance(int distance);


    boolean isInside(Location location);

    boolean isInside(LocationAble entity);

    boolean isOutside(Location location);

    boolean isOutside(LocationAble entity);
}
