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

package org.mcnative.runtime.api.text;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.entry.DocumentEntry;
import net.pretronic.libraries.document.type.DocumentFileType;
import org.mcnative.runtime.api.connection.MinecraftConnection;
import org.mcnative.runtime.api.text.components.*;
import org.mcnative.runtime.api.text.format.TextColor;
import org.mcnative.runtime.api.text.format.TextStyle;

import java.util.*;

public class Text {

    public static TextComponent EMPTY = of("");

    public static TextComponent SPACE = of(" ");

    public static TextComponent NEW_LINE = of("\n");

    public static final char FORMAT_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
    public static final char DEFAULT_ALTERNATE_COLOR_CHAR = '&';
    public static final char RGB_CODE_START = '#';

    public static TextBuilder newBuilder(){
        return new TextBuilder();
    }

    public static GroupMessageComponent newGroup(){
        return new GroupMessageComponent();
    }

    public static TextComponent of(String text){
        return of(text,TextColor.WHITE);
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



    public static MessageKeyComponent ofMessageKey(String key){
        return new MessageKeyComponent(key);
    }

    public static MessageKeyComponent ofMessageKey(String key, MinecraftConnection target){
        return new TargetMessageKeyComponent(target,key);
    }

    public static MessageComponent<?> parse(String text){
        return parse(text,true,DEFAULT_ALTERNATE_COLOR_CHAR);
    }

    public static MessageComponent<?> parse(String text, boolean colors, char alternateChar){
        TextComponent root = new TextComponent();
        TextComponent current = root;
        root.setColor(TextColor.WHITE);
        root.setText("");

        char[] chars = text.toCharArray();
        int textIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            char char0 = chars[i];
            if((char0 == Text.FORMAT_CHAR || char0 == Text.DEFAULT_ALTERNATE_COLOR_CHAR) && chars.length > ++i){
                TextColor color;

                int skip = 1;
                if(chars[i] == '#' && chars.length>(i+6)){
                    color = TextColor.make(new String(Arrays.copyOfRange(chars,i,i+7)));
                    skip = 7;
                } else color = TextColor.of(chars[i]);

                if(color != null){
                    TextComponent next = new TextComponent();
                    current.addExtra(next);
                    next.setColor(color);
                    next.setText("");
                    if(textIndex < i){
                        current.setText(new String(Arrays.copyOfRange(chars,textIndex,i-1)));
                    }
                    current = next;
                    textIndex = i+skip;
                }

                TextStyle style = TextStyle.of(char0);
                if(style != null) current.addStyle(style);
            }
        }
        if(textIndex < chars.length){
            current.setText(new String(Arrays.copyOfRange(chars,textIndex,chars.length)));
        }
        return root;
    }

    public static String translateAlternateColorCodes(char alternateChar,String text){
        char[] content = text.toCharArray();
        for(int i = 0; i < content.length-1; i++) {
            if(content[i] == alternateChar && ALL_CODES.indexOf(content[i+1]) > -1){
                content[i] = FORMAT_CHAR;
            }
        }
        return new String(content);
    }

    public static MessageComponent<?> decompile(String jsonText){
        return decompile(DocumentFileType.JSON.getReader().read(jsonText));
    }

    public static MessageComponent<?> decompile(Document data){
        if(data.isArray()) return new MessageComponentSet(decompileArray(data));
        else return decompileComponent(data);
    }

    public static MessageComponent<?> decompileComponent(DocumentEntry data){
        if(data.isPrimitive()) return new TextComponent(data.toPrimitive().getAsString());
        else{
            MessageComponent<?> component;
            Document document = data.toDocument();
            if(document.contains("text")) component = new TextComponent();
            else if(document.contains("keybind")) component = new KeybindComponent();
            else if(document.contains("translation")) component = new TranslationComponent();
            else if(document.contains("entityName")) component = new ScoreComponent();
            else throw new IllegalArgumentException("Invalid message component");
            component.decompile(data.toDocument());
            return component;
        }
    }

    public static List<MessageComponent<?>> decompileArray(Document data){
        List<MessageComponent<?>> components = new ArrayList<>();
        for (DocumentEntry entry : data.entries()) components.add(decompileComponent(entry));
        return components;
    }
}
