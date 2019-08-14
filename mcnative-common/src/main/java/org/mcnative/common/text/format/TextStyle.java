/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 07.08.19, 19:27
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

public enum TextStyle {

    OBFUSCATED( 'k', "obfuscated"),

    BOLD( 'l', "bold"),

    STRIKETHROUGH( 'm', "strikethrough"),

    UNDERLINE( 'n', "underline"),

    ITALIC( 'o', "italic"),

    RESET( 'r', "reset");

    private char code;
    private String name;

    TextStyle(char code, String name) {
        this.code = code;
        this.name = name;
    }

    public char getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static TextStyle fromCode(char code){
        for(TextStyle color : values()) if(color.code == code) return color;
        return null;
    }

}
