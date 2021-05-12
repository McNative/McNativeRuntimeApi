/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 14:33
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

package org.mcnative.runtime.api.player.scoreboard.belowname;

import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.text.components.MessageComponent;

public interface BelowNameEntry {

    MessageComponent<?> getText();

    void setText(MessageComponent<?> text);


    int getScore();

    void setScore(int score);


    boolean isNative();

    boolean setNative(boolean value);


    static BelowNameEntry newBelowNameInfo(){
        return McNative.getInstance().getObjectFactory().createObject(BelowNameEntry.class);
    }
}
