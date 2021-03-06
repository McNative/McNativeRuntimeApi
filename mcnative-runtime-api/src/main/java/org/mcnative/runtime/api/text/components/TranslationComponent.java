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

package org.mcnative.runtime.api.text.components;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.runtime.api.connection.MinecraftConnection;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.text.format.TextColor;
import org.mcnative.runtime.api.text.format.TextStyle;

import java.util.Set;

public class TranslationComponent extends AbstractChatComponent<TranslationComponent>{

    private String translation;
    private MessageComponentSet with;

    public TranslationComponent() {}

    public TranslationComponent(String translation) {
        this.translation = translation;
        this.with = new MessageComponentSet();
    }

    public TranslationComponent(String translation, TextColor color) {
        super(color);
        this.with = new MessageComponentSet();
        this.translation = translation;
    }

    public TranslationComponent(String translation, TextColor color, Set<TextStyle> styling) {
        super(color, styling);
        this.with = new MessageComponentSet();
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
    public void toPlainText(StringBuilder builder, VariableSet variables,Language language) {
        builder.append("{translation=").append(translation).append("}");
        super.toPlainText(builder, variables);
    }

    @Override
    void compileLegacyText(StringBuilder builder, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        //Empty and not supported
    }

    @Override
    public Document compile(String key, MinecraftConnection connection,MinecraftProtocolVersion version, VariableSet variables, Language language) {
        Document result = super.compile(key,connection,version,variables,language).add("translate",translation);
        result.addEntry(with.compile("with",connection,version,variables,language));
        return result;
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

    @Override
    public TranslationComponent copy() {
        return new TranslationComponent(translation);
    }

}
