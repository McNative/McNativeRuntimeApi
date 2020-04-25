/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.04.20, 19:07
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

package org.mcnative.common.text.components;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.connection.MinecraftConnection;

import java.util.Collection;

public class RawMessageComponent implements MessageComponent<RawMessageComponent>{

    private final String plainText;
    private final String compileLegacyText;
    private final Document compile;

    public RawMessageComponent(String plainText,String compileLegacyText, Document compile) {
        this.plainText = plainText;
        this.compileLegacyText = compileLegacyText;
        this.compile = compile;
    }

    @Override
    public Collection<MessageComponent<?>> getExtras() {
        throw new UnsupportedOperationException("Not supported in raw message component");
    }

    @Override
    public RawMessageComponent addExtra(MessageComponent<?> component) {
        throw new UnsupportedOperationException("Not supported in raw message component");
    }

    @Override
    public RawMessageComponent removeExtra(MessageComponent<?> component) {
        throw new UnsupportedOperationException("Not supported in raw message component");
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        builder.append(plainText);
    }

    @Override
    public void compileToLegacy(StringBuilder builder, MinecraftConnection connection, VariableSet variables, Language language) {
        builder.append(compileLegacyText);
    }

    @Override
    public Document compile(String key, MinecraftConnection connection, VariableSet variables, Language language) {
        return compile;
    }

    @Override
    public void decompile(Document data) {
        throw new UnsupportedOperationException("Not supported in raw message component");
    }
}
