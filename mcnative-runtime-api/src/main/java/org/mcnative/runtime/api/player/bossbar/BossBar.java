/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 11.08.19, 19:02
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

package org.mcnative.runtime.api.player.bossbar;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.receiver.SendAble;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.Collection;
import java.util.UUID;

public interface BossBar extends SendAble {

    UUID getId();


    Collection<ConnectedMinecraftPlayer> getReceivers();


    MessageComponent<?> getTitle();

    BossBar setTitle(MessageComponent<?> title);


    VariableSet getVariables();

    BossBar setVariables(VariableSet variables);


    BarColor getColor();

    BossBar setColor(BarColor color);


    BarDivider getDivider();

    BossBar setDivider(BarDivider divider);


    BarFlag getFlag();

    BossBar setFlag(BarFlag flag);


    int getMaximum();

    BossBar setMaximum(int maximum);


    int getProgress();

    BossBar setProgress(int progress);


    void update();

    void update(ConnectedMinecraftPlayer receiver);


    static BossBar newBossBar(){
        return McNative.getInstance().getObjectFactory().createObject(BossBar.class);
    }
}
