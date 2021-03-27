/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.04.20, 20:51
 * @web %web%
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

package org.mcnative.runtime.api.player.tablist;

import org.mcnative.runtime.api.player.MinecraftPlayer;
import org.mcnative.runtime.api.player.PlayerDesign;

public interface TablistEntry {

    String getName();

    PlayerDesign getDesign();

    PlayerDesign getDesign(MinecraftPlayer player);

    boolean isPlayer();

    default Object getHolder(){
        return null;
    }

    default void setHolder(Object holder){
        throw new UnsupportedOperationException("This entry implementation does not support holders");
    }
}
