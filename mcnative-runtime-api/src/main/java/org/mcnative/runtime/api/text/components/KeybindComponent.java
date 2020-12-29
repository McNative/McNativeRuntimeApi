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

public class KeybindComponent extends AbstractChatComponent<KeybindComponent>{

    private String keybind;

    public KeybindComponent() {}

    public KeybindComponent(String keybind) {
        this.keybind = keybind;
    }

    public KeybindComponent(String keybind, TextColor color) {
        super(color);
        this.keybind = keybind;
    }

    public KeybindComponent(String keybind,TextColor color, Set<TextStyle> styling) {
        super(color, styling);
        this.keybind = keybind;
    }

    public String getKeybind() {
        return keybind;
    }

    public void setKeybind(String keybind) {
        this.keybind = keybind;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        builder.append("{keybind=").append(keybind).append("}");
        super.toPlainText(builder, variables,language);
    }

    @Override
    public Document compile(String key, MinecraftConnection connection,MinecraftProtocolVersion version, VariableSet variables, Language language) {
        return super.compile(key,connection,version,variables,language).add("keybind",keybind);
    }

    @Override
    void compileLegacyText(StringBuilder builder, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        //Empty and not supported
    }

    @Override
    public void decompile(Document data) {
        keybind = data.getString("keybind");
        super.decompile(data);
    }
}
