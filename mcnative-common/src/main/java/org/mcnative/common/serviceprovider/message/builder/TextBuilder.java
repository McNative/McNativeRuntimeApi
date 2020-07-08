/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.03.20, 12:25
 * @web %web%
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

package org.mcnative.common.serviceprovider.message.builder;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.entry.DocumentEntry;
import net.pretronic.libraries.message.bml.builder.BasicMessageBuilder;
import net.pretronic.libraries.message.bml.builder.BuildContext;
import net.pretronic.libraries.message.bml.builder.MessageBuilder;
import net.pretronic.libraries.message.bml.builder.MessageBuilderFactory;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageKeyComponent;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TextBuilder implements BasicMessageBuilder {

    private final String input;

    public TextBuilder(String input) {
        this.input = input;
    }

    @Override
    public Object build(BuildContext context,boolean requiresString, Object[] parameters,Object next) {
        if(requiresString){
            if(next == null) return input;
            else return input+next;
        }else{
            if(context instanceof MinecraftBuildContext){
                if(((MinecraftBuildContext) context).getType() == TextBuildType.COMPILE){
                    return buildCompileText(input,next);
                }else  if(((MinecraftBuildContext) context).getType() == TextBuildType.LEGACY){
                    return buildLegacyCompileText(input,next);
                }
            }
            return buildPlainText(input,next);
        }
    }

    @Override
    public boolean isUnformattedResultRequired() {
        return false;
    }

    public static class Factory implements MessageBuilderFactory {

        @Override
        public MessageBuilder create(String name) {
            return new TextBuilder(name);
        }
    }

    protected static String buildPlainText(String input,Object nextComp){
        StringBuilder builder = new StringBuilder(input);


        for (int i = 0; i < builder.length(); i++) {
            char char0 = builder.charAt(i);
            if((char0 == Text.FORMAT_CHAR || char0 == Text.DEFAULT_ALTERNATE_COLOR_CHAR) && builder.length() > ++i){
                builder.delete(i-1,i+1);
                 i -= 1;
            }
        }

        if(nextComp != null){
            builder.append(nextComp instanceof String ? nextComp : nextComp.toString());
        }

        return builder.toString();
    }

    protected static String buildLegacyCompileText(String input,Object nextComp){
        StringBuilder builder = new StringBuilder(input);

        for(int i = 0; i < builder.length()-1; i++) {
            if(builder.charAt(i) == '&' && Text.ALL_CODES.indexOf(builder.charAt(i+1)) > -1){
                builder.setCharAt(i,Text.FORMAT_CHAR);
            }
        }

        if(nextComp != null){
            builder.append(nextComp instanceof String ? nextComp : nextComp.toString());
        }

        return builder.toString();
    }

    protected static Document buildCompileText(BuildContext context,Object input,Object nextComp){
        if(input instanceof MessageKeyComponent){
            Document result = ((MessageKeyComponent) input).compile(context);
            if(nextComp != null){
                Document root = Document.newDocument();
                root.set("text","");

                if(nextComp instanceof DocumentEntry){
                    root.set("extra",new Object[]{result,nextComp});
                }else if(nextComp.getClass().isArray()){
                    int length = Array.getLength(nextComp);
                    if(length >= 0){
                        root.set("extra",nextComp);
                        //@Todo optimize with direct method in document
                        root.getDocument("extra").entries().add(0,result);
                    }
                }else{
                    root.set("extra",new Object[]{result,nextComp.toString()});
                }

                return root;
            }
            return result;
        }else{
            return buildCompileText(input.toString(),nextComp);
        }
    }

    protected static Document buildCompileText(String input,Object nextComp){
        Document root = Document.newDocument();
        Document current = root;

        current.set("text","");

        char[] chars = input.toCharArray();
        int textIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            char char0 = chars[i];
            if((char0 == Text.FORMAT_CHAR || char0 == Text.DEFAULT_ALTERNATE_COLOR_CHAR) && chars.length > ++i){
                TextColor color = TextColor.of(chars[i]);
                if(color != null){
                    Document next = Document.newDocument();
                    current.set("extra",new Document[]{next});
                    next.set("color",color.getName());
                    next.set("text","");
                    if(textIndex < i) current.set("text",new String(Arrays.copyOfRange(chars,textIndex,i-1)).replace("\\","\\\\"));
                    current = next;
                    textIndex = i+1;
                }

                TextStyle style = TextStyle.of(chars[i]);
                if(style != null){
                    current.set(style.getName().toLowerCase(),true);
                    textIndex = i+1;
                }
            }
        }
        if(textIndex < chars.length){
            current.set("text",new String(Arrays.copyOfRange(chars,textIndex,chars.length)).replace("\\","\\\\"));
        }
        if(nextComp != null){
            if(nextComp instanceof DocumentEntry){
                current.set("extra",new Object[]{nextComp});
            }else if(nextComp.getClass().isArray()){
                int length = Array.getLength(nextComp);
                if(length >= 0){
                    current.set("extra",nextComp);
                }
            }else{
                current.set("extra",new Object[]{nextComp.toString()});
            }
        }

        return root;
    }

    protected static Object buildTextData(Object input, Object input2){
        if(input2 == null && input == null){
            return null;
        }else if(input == null){
            if(input2.getClass().isArray()) return Array.getLength(input2) > 0 ? input2 : null;
            else return new Object[]{input2};
        }else if(input2 == null){
            if(input.getClass().isArray()) return Array.getLength(input) > 0 ? input : null;
            else return new Object[]{input};
        }
        if(input.getClass().isArray() && input2.getClass().isArray() ){
            int input2Length = Array.getLength(input);
            int input3Length = Array.getLength(input2);
            Object[] result = Arrays.copyOf((Object[])input,input2Length+input3Length);
            int index = input2Length+1;
            for (int i = 0; i < input3Length; i++) {
                result[index] = Array.get(input2,i);
            }
            return result;
        }else if(input.getClass().isArray()){
            Object[] result = Arrays.copyOf((Object[])input,Array.getLength(input)+1);
            result[result.length-1] = input2;
            return result;
        }else if(input2.getClass().isArray()){
            Object[] result = Arrays.copyOf((Object[])input2,Array.getLength(input2)+1);
            result[result.length-1] = input;
            return result;
        }else{
            return new Object[]{
                    input instanceof DocumentEntry ? input : input.toString()
                    ,input2 instanceof DocumentEntry ? input2 : input2.toString()};
        }
    }
}
