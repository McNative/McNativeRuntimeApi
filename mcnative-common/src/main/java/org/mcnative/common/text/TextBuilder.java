/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.common.text;

import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.format.TextStyle;

public class TextBuilder {

    private final MessageComponentSet components;

    public TextBuilder() {
        components = new MessageComponentSet();
    }

    public TextBuilder addText(String text){
        return addText(text, TextColor.RESET);
    }

    public TextBuilder addText(String text, TextColor color){
        components.add(Text.of(text, color));
        return this;
    }

    public TextBuilder addText(String text,TextStyle... styles){
        components.add(Text.of(text, styles));
        return this;
    }

    public TextBuilder addText(String text, TextColor color, TextStyle... styles){
        components.add(Text.of(text, color,styles));
        return this;
    }

    public TextBuilder addClickText(String text, Runnable value){
        return addClickText(text,ClickAction.EXECUTE, value);
    }

    public TextBuilder addClickText(String text, ClickAction action, Object value){
        components.add(Text.of(text).setClickEvent(action, value));
        return this;
    }

    public TextBuilder addHoverText(String text, HoverAction action, Object value){
        components.add(Text.of(text).setHoverEvent(action, value));
        return this;
    }

    public MessageComponent build(){
        return components;
    }

}
