/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 31.05.20, 19:25
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

package org.mcnative.runtime.api.event.player;

import net.pretronic.libraries.event.Cancellable;

import java.util.Arrays;

public interface MinecraftPlayerCommandPreprocessEvent extends MinecraftOnlinePlayerEvent, Cancellable {

    String getCommand();

    void setCommand(String command);

    default String getBaseCommand(){
        int index = getCommand().indexOf(" ");
        if(index == -1) return getCommand();
        else return getCommand().substring(0,index);
    }

    default String[] getArguments(){
        String[] arguments = getCommand().split(" ");
        if(arguments.length <= 1) return new String[]{};
        return Arrays.copyOfRange(arguments,1,arguments.length);
    }
}
