/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.09.19, 20:24
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

package org.mcnative.common.text.components;

import net.prematic.libraries.document.Document;
import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.event.TextEvent;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;
import org.mcnative.common.text.variable.VariableSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AbstractChatComponent<T extends AbstractChatComponent> implements ChatComponent<T>{

    private TextColor color;
    private Set<TextStyle> styling;

    private TextEvent<ClickAction> clickEvent;
    private TextEvent<HoverAction> hoverEvent;

    private Collection<ChatComponent> extras;

    public AbstractChatComponent(){
        this(TextColor.WHITE);
    }

    public AbstractChatComponent(TextColor color){
        this(color,new HashSet<>());
    }

    public AbstractChatComponent(TextColor color, Set<TextStyle> styling){
        this.color = color;
        this.styling = styling;
        this.extras = new ArrayList<>();
    }

    @Override
    public TextEvent<ClickAction> getClickEvent() {
        return clickEvent;
    }

    @Override
    public TextEvent<HoverAction> getHoverEvent() {
        return hoverEvent;
    }

    @Override
    public T setClickEvent(TextEvent<ClickAction> event) {
        this.clickEvent = event;
        return (T) this;
    }

    @Override
    public T setHoverEvent(TextEvent<HoverAction> event) {
        this.hoverEvent = event;
        return (T) this;
    }

    @Override
    public Collection<ChatComponent> getExtras() {
        return extras;
    }

    @Override
    public T addExtra(ChatComponent component) {
        extras.add(component);
        return (T) this;
    }

    @Override
    public T removeExtra(ChatComponent component) {
        extras.remove(component);
        return (T) this;
    }

    @Override
    public String toPlainText(VariableSet variables) {
        return null;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables) {

    }

    @Override
    public Document compile(VariableSet variables) {
        return null;
    }

    @Override
    public TextColor getColor() {
        return color;
    }

    @Override
    public T setColor(TextColor color) {
        this.color = color;
        return (T) this;
    }

    @Override
    public Set<TextStyle> getStyling() {
        return styling;
    }

    @Override
    public T setStyling(Set<TextStyle> styling) {
        this.styling = styling;
        return (T) this;
    }
}
