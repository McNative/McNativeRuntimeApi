/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.08.19, 21:53
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

package org.mcnative.runtime.api.text.format;

import org.mcnative.runtime.api.text.components.ChatComponent;

import java.util.Set;

public interface MessageFormatAble<T extends MessageFormatAble<?>> {

    TextColor getColor();

    T setColor(TextColor color);

    TextFont getFont();

    T setFont(TextFont font);


    Set<TextStyle> getStyling();

    T setStyling(Set<TextStyle> styling);


    default boolean isBold(){
        return getStyling().contains(TextStyle.BOLD);
    }

    default boolean isItalic(){
        return getStyling().contains(TextStyle.ITALIC);
    }

    default boolean isUnderlined(){
        return getStyling().contains(TextStyle.UNDERLINE);
    }

    default boolean isStrikeThrough(){
        return getStyling().contains(TextStyle.STRIKETHROUGH);
    }

    default boolean isObfuscated(){
        return getStyling().contains(TextStyle.OBFUSCATED);
    }


    default T setBold(boolean bold){
        if(bold) getStyling().add(TextStyle.BOLD);
        else getStyling().remove(TextStyle.BOLD);
        return (T) this;
    }

    default T setItalic(boolean italic){
        if(italic) getStyling().add(TextStyle.STRIKETHROUGH);
        else getStyling().remove(TextStyle.STRIKETHROUGH);
        return (T) this;
    }

    default T setUnderlined(boolean underlined){
        if(underlined) getStyling().add(TextStyle.UNDERLINE);
        else getStyling().remove(TextStyle.UNDERLINE);
        return (T) this;
    }

    default T setStrikeThrough(boolean strikeThrough){
        if(strikeThrough) getStyling().add(TextStyle.STRIKETHROUGH);
        else getStyling().remove(TextStyle.STRIKETHROUGH);
        return (T) this;
    }

    default T setObfuscated(boolean obfuscated){
        if(obfuscated) getStyling().add(TextStyle.OBFUSCATED);
        else getStyling().remove(TextStyle.OBFUSCATED);
        return (T) this;
    }

    default T addStyle(TextStyle... styles){
        for (TextStyle style : styles) getStyling().add(style);
        return (T) this;
    }

    default T removeStyle(TextStyle... styles){
        for (TextStyle style : styles) getStyling().remove(style);
        return (T) this;
    }

    default T clearStyling(){
        getStyling().clear();
        return (T) this;
    }

    default void copyFormatting(ChatComponent component){
        component.setColor(getColor());
        component.setStyling(getStyling());
    }


}
