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
import org.mcnative.common.text.Text;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Set;

public class TranslationComponent extends AbstractChatComponent<TranslationComponent>{

    private String translation;
    private MessageComponentSet with;

    public TranslationComponent() {}

    public TranslationComponent(String translation) {
        this.translation = translation;
    }

    public TranslationComponent(String translation, TextColor color) {
        super(color);
        this.translation = translation;
    }

    public TranslationComponent(String translation, TextColor color, Set<TextStyle> styling) {
        super(color, styling);
        this.translation = translation;
    }

    public MessageComponentSet getWith() {
        return with;
    }

    public void setWith(MessageComponentSet with) {
        this.with = with;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables) {
        builder.append("{translation=").append(translation).append("}");
        super.toPlainText(builder, variables);
    }

    @Override
    public Document compile(String key, VariableSet variables) {
        return super.compile(key,variables).add("translate",translation).add("with",with.compile(variables));
    }


    @Override
    public void decompile(Document data) {
        translation = data.getString("translate");
        Document with = data.getDocument("with");
        if(with != null){
            this.with = new MessageComponentSet();
            this.with.decompile(with);
        }
        super.decompile(data);
    }
}
