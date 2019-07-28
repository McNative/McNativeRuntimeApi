/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 27.07.19 14:33
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

package net.prematic.mcnative.common.chat;

public interface MessageFormatComponent<T extends MessageFormatComponent> {

    String getText();

    ChatColor getColor();

    boolean isBold();

    boolean isItalic();

    boolean isUnderlined();

    boolean isStrikeThrough();

    boolean isObfuscated();

    T setText(String text);

    T setColor(ChatColor color);

    T setBold(boolean bold);

    T setItalic(boolean italic);

    T setUnderlined(boolean underlined);

    T setStrikeThrough(boolean strikeThrough);

    T setObfuscated(boolean obfuscated);




}
