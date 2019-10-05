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

public abstract class AbstractChatComponent<T extends AbstractChatComponent> implements ChatComponent<T>{

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

    @Override
    public void compile(Document document, VariableSet variables) {
        if(isBold()) document.add("bold",true);
        if(isItalic()) document.add("italic",true);
        if(isUnderlined()) document.add("underlined",true);
        if(isStrikeThrough()) document.add("strikethrough",true);
        if(isObfuscated()) document.add("obfuscated",true);
        if(this.color != null) document.add("color",color.getCode());
        if(this.clickEvent != null){//Register temp command
            if(clickEvent.getAction().isDefaultMinecraftEvent()){
                document.add(clickEvent.getAction().getName().toLowerCase(),clickEvent.getValue().toString());
            }else document.add("run_command","mcnOnTextClick");//@Todo add custom event managing
        }else if(this.hoverEvent != null){
            if(hoverEvent.getAction() == HoverAction.SHOW_TEXT) document.add("show_text",hoverEvent.getValue());
            else if(hoverEvent.getAction() == HoverAction.SHOW_ITEM) document.add("show_item",hoverEvent.getValue());
            else if(hoverEvent.getAction() == HoverAction.SHOW_ENTITY) document.add("show_entity",hoverEvent.getValue());
            else if(hoverEvent.getAction() == HoverAction.SHOW_ACHIEVEMENT) document.add("show_achievement",hoverEvent.getValue());
            //SHOW_ACHIEVEMENT is deprecated Since 1.12
        }
        if(extras != null){
            Document[] extras = new Document[this.extras.size()];
            int index = 0;
            for (ChatComponent extra : this.extras) {
                extras[index] = extra.compile(variables);
                index++;
            }
            document.add("extra",extras);
        }
    }

    @Override
    public String toString() {
        return toPlainText();
    }
}
