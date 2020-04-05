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
import net.pretronic.libraries.message.bml.builder.BasicMessageBuilder;
import net.pretronic.libraries.message.bml.builder.BuildContext;
import net.pretronic.libraries.message.bml.builder.MessageBuilder;
import net.pretronic.libraries.message.bml.builder.MessageBuilderFactory;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.format.TextColor;

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
        }else return buildText(input,next);
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

    protected static Document buildText(String input,Object nextComp){
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
                    if(textIndex < i) current.set("text",new String(Arrays.copyOfRange(chars,textIndex,i-1)));
                    current = next;
                    textIndex = i+1;
                }
            }
        }
        if(textIndex < chars.length-1){
            current.set("text",new String(Arrays.copyOfRange(chars,textIndex,chars.length)));
        }
        if(nextComp != null){
            current.set("extra",nextComp.getClass().isArray() ? nextComp : nextComp.toString());
        }
        return root;
    }
}
