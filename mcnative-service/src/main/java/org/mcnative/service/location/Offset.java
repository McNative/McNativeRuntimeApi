/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 12:53
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

public class Offset {

    /*
    Radius

    Offsets / 6


     */

    static Offset ofRadius(int radius){
        return null;
    }

    static Offset ofFlatRadius(int radius){
        return null;
    }

    static Offset ofSquare(int distance){
        return null;
    }

    static Offset ofFlatSquare(int distance){
        return null;
    }


    static Offset of(int offsetX, int offsetY, int offsetZ){
        return null;
    }


    static Offset ofX(int offsetX){
        return null;
    }

    static Offset ofX(int positiveOffsetX,int negativeOffsetX){
        return null;
    }


    static Offset ofY(int offsetY){
        return null;
    }

    static Offset ofY(int positiveOffsetY,int negativeOffsetY){
        return null;
    }


    static Offset ofZ(int offsetY){
        return null;
    }

    static Offset ofZ(int positiveOffsetZ,int negativeOffsetZ){
        return null;
    }


    static Offset ofPositive(int offsetX, int offsetY, int offsetZ){
        return null;
    }

    static Offset ofNegative(int offsetX, int offsetY, int offsetZ){
        return null;
    }


    static Offset of(int positiveOffsetX,int negativeOffsetX, int positiveOffsetY,int negativeOffsetY, int positiveOffsetZ,int negativeOffsetZ){
        return null;
    }
}
