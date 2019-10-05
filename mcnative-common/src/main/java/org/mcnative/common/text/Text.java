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

package org.mcnative.common.text;

import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import org.mcnative.common.text.components.*;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.util.HashSet;
import java.util.Set;

public class Text {

    public static final char FORMAT_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
    public static final char DEFAULT_ALTERNATE_COLOR_CHAR = '&';

    public static TextBuilder newBuilder(){
        return new TextBuilder();
    }

    public static TextComponent of(String text){
        return null;
    }

    public static TextComponent of(String text, TextColor color){
        return of(text,color,new HashSet<>());
    }

    public static TextComponent of(String text, TextColor color, Set<TextStyle> styling){
        return new TextComponent(text,color,styling);
    }



    public static KeybindComponent ofKeybind(String keybind){
        return ofKeybind(keybind,TextColor.WHITE);
    }

    public static KeybindComponent ofKeybind(String keybind, TextColor color){
        return ofKeybind(keybind, color,new HashSet<>());
    }

    public static KeybindComponent ofKeybind(String keybind, TextColor color, Set<TextStyle> styling){
        return new KeybindComponent(keybind,color,styling);
    }



    public static ScoreComponent ofScore(String objective, String entity, String value){
        return ofScore(objective,entity,value,TextColor.WHITE);
    }

    public static ScoreComponent ofScore(String objective, String entity, String value, TextColor color){
        return ofScore(objective,entity,value, color,new HashSet<>());
    }

    public static ScoreComponent ofScore(String objective, String entity, String value, TextColor color, Set<TextStyle> styling){
        return new ScoreComponent(objective,entity,value,color,styling);
    }



    public static TranslationComponent ofTranslation(String translation){
        return ofTranslation(translation,TextColor.WHITE);
    }

    public static TranslationComponent ofTranslation(String translation, TextColor color){
        return ofTranslation(translation,color,new HashSet<>());
    }

    public static TranslationComponent ofTranslation(String translation, TextColor color, Set<TextStyle> styling){
        return new TranslationComponent(translation,color,styling);
    }



    public static KeyComponent ofMessageKey(String key){
        return ofMessageKey(key,TextColor.WHITE);
    }

    public static KeyComponent ofMessageKey(String key, TextColor color){
        return ofMessageKey(key,color,new HashSet<>());
    }

    public static KeyComponent ofMessageKey(String key, TextColor color, Set<TextStyle> styling){
        return new KeyComponent(key,color,styling);
    }


    public static ChatComponent parse(String text){
        return parse(text,true);
    }

    public static ChatComponent parse(String text,boolean markup){
        return parse(text,markup,true,DEFAULT_ALTERNATE_COLOR_CHAR);
    }

    public static ChatComponent parse(String text, boolean markup, boolean colors, char alternateChar){
        return null;
    }

    public static String translateAlternateColorCodes(char alternateChar,String text){
        char[] content = text.toCharArray();
        for(int i = 0; i < text.toCharArray().length; i++) {
            if(content[i] == alternateChar && ALL_CODES.indexOf(content[i]+1) > -1) content[i] = FORMAT_CHAR;
        }
        return new String(content);
    }

    public static ChatComponent decompile(String jsonText){
        return decompile(DocumentFileType.JSON.getReader().read(jsonText));
    }

    public static ChatComponent decompile(Document document){
        return null;
    }

}
