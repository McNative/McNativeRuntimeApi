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

package org.mcnative.runtime.api.player;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

public class Title {

    private MessageComponent<?> title;
    private MessageComponent<?> subTitle;
    private VariableSet variables;
    private int[] timing;

    public Title() {
        this.timing = new int[]{20,60,20};
    }

    public MessageComponent<?> getTitle() {
        return title;
    }

    public MessageComponent<?> getSubTitle() {
        return subTitle;
    }

    public VariableSet getVariables() {
        return variables;
    }

    public int[] getTiming() {
        return timing;
    }

    public void setTiming(int[] timing) {
        if(timing.length != 3) throw new IllegalArgumentException("Invalid array length");
        this.timing = timing;
    }

    public Title title(String text){
        return title(Text.of(text));
    }

    public Title title(MessageComponent<?> component){
        title = component;
        return this;
    }

    public Title subTitle(String text){
        return subTitle(Text.of(text));
    }

    public Title subTitle(MessageComponent<?> component){
        this.subTitle = component;
        return this;
    }

    public Title variables(VariableSet variables){
        this.variables = variables;
        return this;
    }

    public Title fadeInTime(int ticks){
        this.timing[0] = ticks;
        return this;
    }

    public Title fadeOutTime(int ticks){
        this.timing[2] = ticks;
        return this;
    }

    public Title stayTime(int ticks){
        this.timing[1] = ticks;
        return this;
    }

    public void send(OnlineMinecraftPlayer player){
        player.sendTitle(this);
    }

    public void send(Iterable<OnlineMinecraftPlayer> players){
        players.forEach(player -> player.sendTitle(Title.this));
    }

    public static Title newTitle(){
        return new Title();
    }
}
