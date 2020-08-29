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
import net.pretronic.libraries.utility.GeneralUtil;
import org.mcnative.common.serviceprovider.message.ColoredString;
import org.mcnative.common.serviceprovider.message.builder.context.MinecraftBuildContext;
import org.mcnative.common.serviceprovider.message.builder.context.TextBuildType;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageKeyComponent;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TextBuilder implements BasicMessageBuilder {

    private final ColoredString input;

    public TextBuilder(String input) {
        this.input = new ColoredString(input);
    }

    @Override
    public Object build(BuildContext context,boolean requiresUnformatted, Object[] parameters,Object next) {
        if(requiresUnformatted){
            return TextBuildUtil.buildUnformattedText(input,next);
        }else{
            if(context instanceof MinecraftBuildContext){
                MinecraftBuildContext minecraftContext = context.getAs(MinecraftBuildContext.class);
                if(minecraftContext.getType() == TextBuildType.COMPILE){
                    return TextBuildUtil.buildCompileText(minecraftContext,input,next);
                }else if(minecraftContext.getType() == TextBuildType.LEGACY){

                }
            }
            return TextBuildUtil.buildPlainText(input,next);
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
