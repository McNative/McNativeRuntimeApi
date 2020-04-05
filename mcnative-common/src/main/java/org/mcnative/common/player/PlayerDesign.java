/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 19:45
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

package org.mcnative.common.player;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.bml.variable.reflect.ReflectVariableObjectToString;
import org.mcnative.common.text.format.TextColor;

public interface PlayerDesign extends ReflectVariableObjectToString {

    String getColor();

    String getPrefix();

    String getSuffix();

    String getChat();

    String getDisplayName();

    int getPriority();

    default TextColor getTextColor(){
        if(getColor().length() == 1) return TextColor.of(getColor().charAt(0));
        else if(getColor().length() == 2) return TextColor.of(getColor().charAt(1));
        else return TextColor.WHITE;
    }

    default String toJson(){
        return DocumentFileType.JSON.getWriter().write(Document.newDocument(this),false);
    }

    @Override
    default String toStringVariable() {
        return getDisplayName();
    }
}
