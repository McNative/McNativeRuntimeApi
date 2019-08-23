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

package org.mcnative.common.text.format;

import org.mcnative.common.text.TextComponent;

import java.util.Collection;

public interface MessageFormatComponent<T extends MessageFormatComponent> {

    TextColor getColor();

    Collection<TextStyle> getStyling();

    boolean isBold();

    boolean isItalic();

    boolean isUnderlined();

    boolean isStrikeThrough();

    boolean isObfuscated();

    T setColor(TextColor color);

    T setBold(boolean bold);

    T setItalic(boolean italic);

    T setUnderlined(boolean underlined);

    T setStrikeThrough(boolean strikeThrough);

    T setObfuscated(boolean obfuscated);

    T setStyling(Collection<TextStyle> styling);

    T addStyle(TextStyle... style);

    T removeStyle(TextStyle... styles);

    T clearStyling();

    void copyFormatting(TextComponent component);


}
