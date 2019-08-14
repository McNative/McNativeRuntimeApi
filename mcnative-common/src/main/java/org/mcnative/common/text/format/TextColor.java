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

public enum TextColor {

    BLACK( '0', "black" ),

    DARK_BLUE( '1', "dark_blue" ),

    DARK_GREEN( '2', "dark_green" ),

    DARK_AQUA( '3', "dark_aqua" ),

    DARK_RED( '4', "dark_red" ),

    DARK_PURPLE( '5', "dark_purple" ),

    GOLD( '6', "gold" ),

    GRAY( '7', "gray" ),

    DARK_GRAY( '8', "dark_gray" ),

    BLUE( '9', "blue" ),

    GREEN( 'a', "green" ),

    AQUA( 'b', "aqua" ),

    RED( 'c', "red" ),

    LIGHT_PURPLE( 'd', "light_purple" ),

    YELLOW( 'e', "yellow" ),

    WHITE( 'f', "white" ),

    RESET( 'r', "reset" );

    private char code;
    private String name;

    TextColor(char code, String name) {
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

    public static TextColor fromCode(char code){
        for(TextColor color : values()) if(color.code == code) return color;
        return null;
    }

}
