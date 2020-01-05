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

package org.mcnative.common.text.components;

import net.prematic.libraries.document.Document;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Set;

public class KeyComponent extends AbstractChatComponent<KeyComponent>{

    private String key;

    public KeyComponent() {}

    public KeyComponent(String key) {
        this.key = key;
    }

    public KeyComponent(String key, TextColor color) {
        super(color);
        this.key = key;
    }

    public KeyComponent(String key, TextColor color, Set<TextStyle> styling) {
        super(color, styling);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables) {
        builder.append(replaceVariablesAndTranslate(variables));
        super.toPlainText(builder, variables);
    }

    @Override
    public Document compile(String key, VariableSet variables) {
        return super.compile(key,variables).add("text",replaceVariablesAndTranslate(variables));
    }

    private String replaceVariablesAndTranslate(VariableSet variables){
        return variables.replace(key);//Todo get message form translate engine
    }
}
