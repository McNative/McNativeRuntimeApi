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

package org.mcnative.runtime.api.text.format;

import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class TextColor {

    private final static String CUSTOM_COLOR_NAME = "custom";
    private final static char CUSTOM_COLOR_CODE = 'X';
    private final static byte CUSTOM_COLOR__CLIENT_CODE = -1;

    private final static List<TextColor> DEFAULT_COLORS = new ArrayList<>();

    public static TextColor BLACK = createDefault('0', "black", (byte) 0,new Color(0, 0, 0));

    public static TextColor DARK_BLUE = createDefault('1', "dark_blue", (byte) 1,new Color(0, 0, 170));

    public static TextColor DARK_GREEN = createDefault('2', "dark_green", (byte) 2,new Color(0, 170, 0));

    public static TextColor DARK_AQUA = createDefault('3', "dark_aqua", (byte) 3,new Color(0, 170, 170));

    public static TextColor DARK_RED = createDefault('4', "dark_red", (byte) 4,new Color(170, 0, 0));

    public static TextColor DARK_PURPLE = createDefault('5', "dark_purple", (byte) 5,new Color(170, 0, 170));

    public static TextColor GOLD = createDefault('6', "gold", (byte) 6,new Color(255, 170, 0));

    public static TextColor GRAY = createDefault('7', "gray", (byte) 7,new Color(170, 170, 170));

    public static TextColor DARK_GRAY = createDefault('8', "dark_gray", (byte) 8,new Color(85, 85, 85));

    public static TextColor BLUE = createDefault('9', "blue", (byte) 9,new Color(85, 85, 255));

    public static TextColor GREEN = createDefault( 'a', "green", (byte) 10,new Color(85, 255, 85));

    public static TextColor AQUA = createDefault('b', "aqua", (byte) 11,new Color(85, 255, 255));

    public static TextColor RED = createDefault('c', "red", (byte) 12,new Color(255, 85, 85));

    public static TextColor LIGHT_PURPLE = createDefault('d', "light_purple", (byte) 13,new Color(255, 85, 255));

    public static TextColor YELLOW = createDefault('e', "yellow", (byte) 14,new Color(255, 255, 85));

    public static TextColor WHITE = createDefault('f', "white", (byte) 15,new Color(255, 255, 255));

    private final char code;
    private final String name;
    private final byte clientCode;

    private final Color color;

    private TextColor(char code, String name, byte clientCode,Color color) {
        this.code = code;
        this.name = name;
        this.clientCode = clientCode;
        this.color = color;
    }

    public char getCode() {
        return code;
    }

    public byte getClientCode() {
        return clientCode;
    }

    public String getLegacyMotdCode(){
        return "\\u00A7"+code;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public String toFormatCode(MinecraftProtocolVersion version){
        if(isCustom()){
            if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_16)){
                StringBuilder builder = new StringBuilder();
                builder.append(Text.FORMAT_CHAR).append("x");
                for (char c : toHexString().substring(1).toCharArray()) {
                    builder.append(Text.FORMAT_CHAR).append(c);
                }
                return builder.toString();
            }else{
                return ""+ Text.FORMAT_CHAR + toLegacyColor().getCode();
            }
        }
        return ""+ Text.FORMAT_CHAR + getCode();
    }

    public String toHexString(){
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public boolean isCustom(){
        return code == CUSTOM_COLOR_CODE;
    }

    public TextColor toLegacyColor(){
        if(!isCustom()) return this;
        else return findClosestDefaultColor(this);
    }

    public String compileColor(MinecraftProtocolVersion version){
        if(isCustom()){
            if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_16)){
                return toHexString();
            }else{
                return toLegacyColor().getName();
            }
        }
        return getName();
    }

    @Override
    public String toString() {
        return name;
    }

    public static TextColor of(char code){
        for(TextColor color : DEFAULT_COLORS) if(color.code == code) return color;
        return null;
    }

    public static Collection<TextColor> getDefaultColors(){
        return Collections.unmodifiableCollection(DEFAULT_COLORS);
    }

    public static TextColor of(String name){
        for(TextColor color : DEFAULT_COLORS) if(color.getName().equalsIgnoreCase(name)) return color;
        return null;
    }

    public static TextColor make(Color color){
        return make(CUSTOM_COLOR_NAME,color);
    }

    public static TextColor make(String name,Color color){
        return new TextColor(CUSTOM_COLOR_CODE,name,CUSTOM_COLOR__CLIENT_CODE,color);
    }

    public static TextColor make(int red,int green, int blue){
        return make(CUSTOM_COLOR_NAME,red,green,blue);
    }

    public static TextColor make(String name, int red,int green, int blue){
        return new TextColor(CUSTOM_COLOR_CODE,name,CUSTOM_COLOR__CLIENT_CODE,new Color(red,green,blue));
    }

    public static TextColor make(String hexCode){
        return make(CUSTOM_COLOR_NAME,hexCode);
    }

    public static TextColor make(String name, String hexCode){
        return new TextColor(CUSTOM_COLOR_CODE,name,CUSTOM_COLOR__CLIENT_CODE,Color.decode(hexCode));
    }

    private static TextColor createDefault(char code, String name, byte clientCode,Color color){
        TextColor textColor = new TextColor(code, name, clientCode, color);
        DEFAULT_COLORS.add(textColor);
        return textColor;
    }

    public static TextColor findClosestDefaultColor(TextColor color){
        return findClosestDefaultColor(color.getColor());
    }

    public static TextColor findClosestDefaultColor(Color color){
        if(color.getAlpha() < 128) return null;
        int index = 0;
        double best = -1.0D;
        for (TextColor defaultColor : DEFAULT_COLORS) {
            if (areIdentical(defaultColor.getColor(), color)) return defaultColor;
        }
        for(int i = 0; i < DEFAULT_COLORS.size(); i++){
            double distance = calculateDistance(color, DEFAULT_COLORS.get(i).getColor());
            if((distance < best) || (best == -1.0D)){
                best = distance;
                index = i;
            }
        }
        return DEFAULT_COLORS.get(index);
    }

    private static double calculateDistance(Color c1, Color c2) {
        double difference = (c1.getRed() + c2.getRed()) / 2.0D;
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2.0D + difference / 256.0D;
        double weightG = 4.0D;
        double weightB = 2.0D + (255.0D - difference) / 256.0D;
        return weightR * r * r + weightG * g * g + weightB * b * b;
    }

    private static boolean areIdentical(Color c1, Color c2) {
        return (Math.abs(c1.getRed() - c2.getRed()) <= 5) &&
                (Math.abs(c1.getGreen() - c2.getGreen()) <= 5) &&
                (Math.abs(c1.getBlue() - c2.getBlue()) <= 5);
    }
}
