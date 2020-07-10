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

import org.mcnative.common.text.Text;

public enum TextColor {

    BLACK( '0', "black", (byte) 0),

    DARK_BLUE( '1', "dark_blue", (byte) 1),

    DARK_GREEN( '2', "dark_green", (byte) 2),

    DARK_AQUA( '3', "dark_aqua", (byte) 3),

    DARK_RED( '4', "dark_red", (byte) 4),

    DARK_PURPLE( '5', "dark_purple", (byte) 5),

    GOLD( '6', "gold", (byte) 6),

    GRAY( '7', "gray", (byte) 7),

    DARK_GRAY( '8', "dark_gray", (byte) 8),

    BLUE( '9', "blue", (byte) 9),

    GREEN( 'a', "green", (byte) 10),

    AQUA( 'b', "aqua", (byte) 11),

    RED( 'c', "red", (byte) 12),

    LIGHT_PURPLE( 'd', "light_purple", (byte) 13),

    YELLOW( 'e', "yellow", (byte) 14),

    WHITE( 'f', "white", (byte) 15);

    private final char code;
    private final String name;
    private final byte clientCode;

    TextColor(char code, String name, byte clientCode) {
        this.code = code;
        this.name = name;
        this.clientCode = clientCode;
    }

    public char getCode() {
        return code;
    }

    public byte getClientCode() {
        return clientCode;
    }

    public String getName() {
        return name;
    }

    public String toFormatCode(){
        return ""+ Text.FORMAT_CHAR+getCode();
    }

    @Override
    public String toString() {
        return name;
    }

    public static TextColor of(char code){
        for(TextColor color : values()) if(color.code == code) return color;
        return null;
    }

    public static TextColor of(String name){
        for(TextColor color : values()) if(color.getName().equalsIgnoreCase(name)) return color;
        return null;
    }

}
