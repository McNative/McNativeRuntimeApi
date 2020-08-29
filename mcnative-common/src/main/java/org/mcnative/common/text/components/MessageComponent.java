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

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

import java.util.Collection;

public interface MessageComponent<T extends MessageComponent<?>> extends Textable {

    Collection<MessageComponent<?>> getExtras();

    T addExtra(MessageComponent<?> component);

    T removeExtra(MessageComponent<?> component);


    default String toPlainText(){
        return toPlainText(VariableSet.newEmptySet());
    }

    default String toPlainText(Language language){
        return toPlainText(VariableSet.newEmptySet(),language);
    }

    default String toPlainText(VariableSet variables){
        return toPlainText(variables,null);
    }

    default String toPlainText(VariableSet variables,Language language){
        StringBuilder builder = new StringBuilder();
        toPlainText(builder,variables,language);
        return builder.toString();
    }

    default void toPlainText(StringBuilder builder){
        toPlainText(builder,VariableSet.newEmptySet());
    }

    default void toPlainText(StringBuilder builder,Language language){
        toPlainText(builder,VariableSet.newEmptySet(),language);
    }

    default void toPlainText(StringBuilder builder,VariableSet variables){
        toPlainText(builder,variables,null);
    }

    void toPlainText(StringBuilder builder,VariableSet variables,Language language);

    default Document compile(MinecraftProtocolVersion version){
        return compile(version,VariableSet.newEmptySet());
    }

    default Document compile(MinecraftProtocolVersion version,VariableSet variables){
        return compile(version,variables,null);
    }

    default Document compile(MinecraftProtocolVersion version,VariableSet variables,Language language){
        return compile(null,null,version,variables,language);
    }

    default Document compile(MinecraftConnection connection,VariableSet variables,Language language){
        return compile(null,connection,connection.getProtocolVersion(),variables,language);
    }

    Document compile(String key, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language);


    default String compileToString(MinecraftProtocolVersion version){
        return compileToString(version,VariableSet.newEmptySet(),null);
    }

    default String compileToString(MinecraftProtocolVersion version,VariableSet variables){
        return compileToString(version,variables,null);
    }

    default String compileToString(MinecraftProtocolVersion version,VariableSet variables,Language language){
        return compileToString(null,version,variables,language);
    }

    default String compileToString(MinecraftConnection connection,VariableSet variables,Language language){
        return compileToString(connection,connection.getProtocolVersion(),variables,language);
    }

    default String compileToString(MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language){
        StringBuilder builder = new StringBuilder();
        compileToString(builder,connection,version,variables,language);
        return builder.toString();
    }

    void compileToString(StringBuilder builder,MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language);

    void decompile(Document data);

    default String toText(VariableSet variables){
        return toPlainText(variables);
    }
}
