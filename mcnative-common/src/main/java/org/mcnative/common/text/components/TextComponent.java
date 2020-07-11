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
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.util.Set;

public class TextComponent extends AbstractChatComponent<TextComponent>{

    private String text;

    public TextComponent() {
        this("");
    }

    public TextComponent(String text) {
        this.text = text;
    }

    public TextComponent(String text,TextColor color) {
        super(color);
        this.text = text;
    }

    public TextComponent(String text,TextColor color, Set<TextStyle> styling) {
        super(color, styling);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void compileToLegacy(StringBuilder builder, MinecraftConnection connection, VariableSet variables, Language language) {
        super.compileToLegacy(builder, connection, variables, language);
        builder.append(text);
        super.compileToLegacyPost(builder, connection, variables, language);
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        builder.append(variables.replace(text));
        super.toPlainText(builder, variables,language);
    }

    @Override
    public Document compile(String key, MinecraftConnection connection, VariableSet variables, Language language) {
        return super.compile(key, connection,variables,language).add("text",variables.replace(text));
    }

    @Override
    public void decompile(Document data) {
        text = data.getString("text");
        super.decompile(data);
    }
}
