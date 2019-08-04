/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 03.08.19 18:11
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

package net.prematic.mcnative.common;

public enum MinecraftColor {

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

    MAGIC( 'k', "obfuscated" ),

    BOLD( 'l', "bold" ),

    STRIKETHROUGH( 'm', "strikethrough" ),

    UNDERLINE( 'n', "underline" ),

    ITALIC( 'o', "italic" ),

    RESET( 'r', "reset" );


    public static final char COLOR_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";


    private char code;
    private String name;

    MinecraftColor(char code, String name) {
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

    public static MinecraftColor fromCode(char code){
        for(MinecraftColor color : values()) if(color.code == code) return color;
        return null;
    }

    public static String translateAlternateColorCodes(char alternateChar,String text){
        char[] content = text.toCharArray();
        for(int i = 0; i < content.length; i++) {
            if(content[i] == alternateChar && ALL_CODES.indexOf(content[i]+1) > -1){
                content[i] = COLOR_CHAR;
            }
        }
        return new String(content);
    }
}
