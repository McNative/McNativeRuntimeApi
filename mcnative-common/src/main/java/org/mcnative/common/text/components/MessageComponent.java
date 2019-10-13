/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 06.10.19, 18:43
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

package org.mcnative.common.text.components;

import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Collection;

public interface MessageComponent<T extends MessageComponent> {

    Collection<MessageComponent> getExtras();

    T addExtra(MessageComponent component);

    T removeExtra(MessageComponent component);

    default String toPlainText(){
        return toPlainText(VariableSet.newEmptySet());
    }

    default String toPlainText(VariableSet variables){
        StringBuilder builder = new StringBuilder();
        toPlainText(builder,variables);
        return builder.toString();
    }

    default void toPlainText(StringBuilder builder){
        toPlainText(builder,VariableSet.newEmptySet());
    }

    void toPlainText(StringBuilder builder,VariableSet variables);

    default Document compile(){
        return compile(VariableSet.newEmptySet());
    }

    default Document compile(VariableSet variables){
        return compile(null,variables);
    }

    Document compile(String key, VariableSet variables);

    default String compileToString(){
        return compileToString(VariableSet.newEmptySet());
    }

    default String compileToString(VariableSet variables){
        return DocumentFileType.JSON.getWriter().write(compile(variables),false);
    }

}
