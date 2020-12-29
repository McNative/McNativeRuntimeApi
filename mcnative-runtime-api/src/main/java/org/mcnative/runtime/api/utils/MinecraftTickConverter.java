/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 15:09
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

package org.mcnative.runtime.api.utils;

import java.util.concurrent.TimeUnit;

public final class MinecraftTickConverter {

    public static final long TICKS_PER_SECOND = 20;

    public static long toTicks(long time, TimeUnit unit){
        return unit.toSeconds(time)*TICKS_PER_SECOND;
    }

    public static long toTime(long ticks, TimeUnit unit){
        return unit.convert((ticks/TICKS_PER_SECOND),TimeUnit.SECONDS);
    }

}
