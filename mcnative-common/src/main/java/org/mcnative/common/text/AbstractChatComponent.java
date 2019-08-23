/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.08.19, 18:15
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

import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.event.TextEvent;
import org.mcnative.common.text.format.MessageFormatComponent;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class AbstractChatComponent<T extends ChatComponent> implements ChatComponent<T>, MessageFormatComponent<T> {

    private TextColor color;
    private Collection<TextStyle> styling;

    private TextEvent<ClickAction> clickEvent;
    private TextEvent<HoverAction> hoverEvent;

    private Collection<MessageComponent> extras;

    public AbstractChatComponent(TextColor color){
        this(color,new ArrayList<>(),null,null,null);
    }

    public AbstractChatComponent(TextColor color, Collection<TextStyle> styling){
        this(color,styling,null,null,null);
    }

    public AbstractChatComponent(TextColor color, Collection<TextStyle> styling, TextEvent<ClickAction> clickEvent, TextEvent<HoverAction> hoverEvent, Collection<MessageComponent> extras) {
        this.color = color;
        this.styling = styling;
        this.clickEvent = clickEvent;
        this.hoverEvent = hoverEvent;
        this.extras = extras;
    }

    @Override
    public TextEvent<ClickAction> getClickEvent() {
        return this.clickEvent;
    }

    @Override
    public TextEvent<HoverAction> getHoverEvent() {
        return this.hoverEvent;
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
    public Collection<MessageComponent> getExtras() {
        return extras;
    }

    @Override
    public T addExtra(MessageComponent component) {
        if(extras == null) extras = new ArrayList<>();
        this.extras.add(component);
        return (T) this;
    }

    @Override
    public T removeExtra(MessageComponent component) {
        if(extras == null) return (T) this;
        this.extras.remove(component);
        return (T) this;
    }


    @Override
    public TextColor getColor() {
        if(this.color == null) return TextColor.WHITE;
        return this.color;
    }

    @Override
    public Collection<TextStyle> getStyling() {
        return this.styling;
    }

    @Override
    public boolean isBold() {
        return this.styling.contains(TextStyle.BOLD);
    }

    @Override
    public boolean isItalic() {
        return this.styling.contains(TextStyle.ITALIC);
    }

    @Override
    public boolean isUnderlined() {
        return this.styling.contains(TextStyle.UNDERLINE);
    }

    @Override
    public boolean isStrikeThrough() {
        return this.styling.contains(TextStyle.STRIKETHROUGH);
    }

    @Override
    public boolean isObfuscated() {
        return this.styling.contains(TextStyle.OBFUSCATED);
    }

    @Override
    public T setColor(TextColor color) {
        this.color = color;
        return (T) this;
    }

    @Override
    public T setBold(boolean bold) {
        if(bold){
            if(!this.styling.contains(TextStyle.BOLD)) this.styling.add(TextStyle.BOLD);
        }else this.styling.remove(TextStyle.BOLD);
        return (T) this;
    }

    @Override
    public T setItalic(boolean italic) {
        if(italic){
            if(!this.styling.contains(TextStyle.ITALIC)) this.styling.add(TextStyle.ITALIC);
        }else this.styling.remove(TextStyle.ITALIC);
        return (T) this;
    }

    @Override
    public T setUnderlined(boolean underlined) {
        if(underlined){
            if(!this.styling.contains(TextStyle.UNDERLINE)) this.styling.add(TextStyle.UNDERLINE);
        }else this.styling.remove(TextStyle.UNDERLINE);
        return (T) this;
    }

    @Override
    public T setStrikeThrough(boolean strikeThrough) {
        if(strikeThrough){
            if(!this.styling.contains(TextStyle.STRIKETHROUGH)) this.styling.add(TextStyle.STRIKETHROUGH);
        }else this.styling.remove(TextStyle.STRIKETHROUGH);
        return (T) this;
    }

    @Override
    public T setObfuscated(boolean obfuscated) {
        if(obfuscated){
            if(!this.styling.contains(TextStyle.OBFUSCATED)) this.styling.add(TextStyle.OBFUSCATED);
        }else this.styling.remove(TextStyle.OBFUSCATED);
        return (T) this;
    }

    @Override
    public T setStyling(Collection<TextStyle> styling) {
        this.styling = styling;
        return (T) this;
    }

    @Override
    public T addStyle(TextStyle... style) {
        this.styling.addAll(Arrays.asList(style));
        return (T) this;
    }

    @Override
    public T removeStyle(TextStyle... styles) {
        this.styling.removeAll(Arrays.asList(styles));
        return (T) this;
    }

    @Override
    public T clearStyling() {
        this.styling.clear();
        return (T) this;
    }

    @Override
    public void copyFormatting(TextComponent component) {
        component.setColor(this.color);
        component.setStyling(new ArrayList<>(this.styling));
    }

}
