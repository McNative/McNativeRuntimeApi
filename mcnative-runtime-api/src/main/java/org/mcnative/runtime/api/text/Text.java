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
import org.mcnative.runtime.api.text.event.ClickAction;
import org.mcnative.runtime.api.text.event.TextEvent;
import org.mcnative.runtime.api.text.format.TextColor;
import org.mcnative.runtime.api.text.format.TextStyle;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    public static TextComponent EMPTY = of("");

    public static TextComponent SPACE = of(" ");

    public static TextComponent NEW_LINE = of("\n");

    private static final Pattern URL = Pattern.compile( "^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$" );

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
        ArrayList<MessageComponent<?>> components = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        TextComponent component = new TextComponent();

        Matcher matcher = URL.matcher(text);
        char[] chars = replaceLegacyMotdCodes(text).toCharArray();
        for ( int i = 0; i < chars.length; i++ )
        {
            char c = chars[i];
            if ((c == Text.FORMAT_CHAR || c == Text.DEFAULT_ALTERNATE_COLOR_CHAR)) {
                if ( ++i >= chars.length) break;
                c = chars[i];
                if (c >= 'A' && c <= 'Z' ) c += 32;

                TextColor color;
                if(chars[i] == '#' && chars.length>(i+6)){
                    color = TextColor.make(new String(Arrays.copyOfRange(chars,i,i+7)));
                    i += 7;
                } else color = TextColor.of(chars[i]);
                if(color != null) component.setColor(color);

                if (builder.length() > 0 ){
                    TextComponent old = component;
                    component = old.copy();
                    old.setText( builder.toString() );
                    builder = new StringBuilder();
                    components.add(old);
                }

                TextStyle style = TextStyle.of(c);
                if (style == TextStyle.RESET ) {
                    component = new TextComponent();
                    component.setColor(TextColor.WHITE);
                } else if(style != null){
                    component = new TextComponent();
                    component.addStyle(style);
                }
                continue;
            }
            int pos = text.indexOf( ' ', i );
            if (pos == -1 ) pos = text.length();
            if (matcher.region( i, pos ).find() )
            {
                if ( builder.length() > 0 ) {
                    TextComponent old = component;
                    component = old.copy();
                    old.setText( builder.toString() );
                    builder = new StringBuilder();
                    components.add( old );
                }

                TextComponent old = component;
                component = old.copy();
                String urlString = text.substring( i, pos );
                component.setText( urlString );
                component.setClickEvent( new TextEvent<>( ClickAction.OPEN_URL, urlString.startsWith( "http" ) ? urlString : "http://" + urlString ));
                components.add( component );
                i += pos - i - 1;
                component = old;
                continue;
            }
            builder.append( c );
        }

        component.setText( builder.toString() );
        components.add( component );

        return new MessageComponentSet(components);
    }

    public static MessageComponent<?> parseF(String text, boolean colors, char alternateChar){
        TextComponent root = new TextComponent();
        TextComponent current = root;
        root.setColor(TextColor.WHITE);
        root.setText("");

        char[] chars = replaceLegacyMotdCodes(text).toCharArray();
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
                }else{
                    TextStyle style = TextStyle.of(chars[i]);
                    System.out.println("style: "+chars[i]+" "+style);
                    if(style != null){
                        current.addStyle(style);
                    }
                }
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

    public static String replaceLegacyMotdCodes(String input){
        for (TextStyle style : TextStyle.values()) {
            input = input.replace(style.getLegacyMotdCode(),DEFAULT_ALTERNATE_COLOR_CHAR+""+style.getCode());
        }
        for (TextColor color : TextColor.getDefaultColors()) {
            input = input.replace(color.getLegacyMotdCode(),DEFAULT_ALTERNATE_COLOR_CHAR+""+color.getCode());
        }
        return input;
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
