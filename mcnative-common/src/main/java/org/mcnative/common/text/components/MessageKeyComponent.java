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

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.MessageProvider;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.McNative;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.util.Set;

public class MessageKeyComponent extends AbstractChatComponent<MessageKeyComponent>{

    private String key;

    public MessageKeyComponent() {}

    public MessageKeyComponent(String key) {
        this.key = key;
    }

    public MessageKeyComponent(String key, TextColor color) {
        super(color);
        this.key = key;
    }

    public MessageKeyComponent(String key, TextColor color, Set<TextStyle> styling) {
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
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        builder.append(replaceVariablesAndTranslate(variables,language));
        super.toPlainText(builder, variables,language);
    }

    @Override
    public Document compile(String key, VariableSet variables, Language language) {
        return super.compile(key,variables,language).add("text",replaceVariablesAndTranslate(variables,language));
    }

    private String replaceVariablesAndTranslate(VariableSet variables, Language language){
        return McNative.getInstance().getRegistry().getService(MessageProvider.class).buildMessage(key,variables,language);
    }
}
