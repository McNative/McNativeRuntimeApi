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

package org.mcnative.common.player.bossbar;

import net.prematic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.common.text.components.MessageComponent;

public class BossBar {

    private MessageComponent<?> title;
    private VariableSet variables;

    private BarColor color;
    private BarStyle style;
    private BarFlag flag;

    private int maximum;
    private int progress;

    private ReceiverChannel receiver;

    public MessageComponent<?> getTitle() {
        return title;
    }

    public BossBar setTitle(MessageComponent<?> title) {
        this.title = title;
        return this;
    }

    public VariableSet getVariables() {
        return variables;
    }

    public BossBar setVariables(VariableSet variables) {
        this.variables = variables;
        return this;
    }

    public BarColor getColor() {
        return color;
    }

    public BossBar setColor(BarColor color) {
        this.color = color;
        return this;
    }

    public BarStyle getStyle() {
        return style;
    }

    public BossBar setStyle(BarStyle style) {
        this.style = style;
        return this;
    }

    public BarFlag getFlag() {
        return flag;
    }

    public BossBar setFlag(BarFlag flag) {
        this.flag = flag;
        return this;
    }

    public int getMaximum() {
        return maximum;
    }

    public BossBar setMaximum(int maximum) {
        this.maximum = maximum;
        return this;
    }

    public int getProgress() {
        return progress;
    }

    public BossBar setProgress(int progress) {
        this.progress = progress;
        return this;
    }

    public ReceiverChannel getReceiver() {
        return receiver;
    }

    public void setReceiver(ReceiverChannel receiver) {
        this.receiver = receiver;
    }

    public void update(){

    }

    public void destroy(){

    }
}
