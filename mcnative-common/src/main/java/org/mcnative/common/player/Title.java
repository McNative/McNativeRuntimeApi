/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 11.08.19, 17:19
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

package org.mcnative.common.player;

import org.mcnative.common.text.components.ChatComponent;
import org.mcnative.common.text.variable.VariableSet;

public interface Title {

    Title title(String text);

    default Title title(ChatComponent component){
        return title(component,VariableSet.newEmptySet());
    }

    Title title(ChatComponent component, VariableSet variables);

    Title subTitle(String text);

    default Title subTitle(ChatComponent component){
        return subTitle(component,VariableSet.newEmptySet());
    }

    Title subTitle(ChatComponent component, VariableSet variables);

    Title fadeInTime(int ticks);

    Title fadeOutTime(int ticks);

    Title stayTime(int ticks);

    void send(OnlineMinecraftPlayer player);
}
