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

import java.util.Collection;

//@Todo add legacy builder
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


    default String compileToLegacy(){
        return compileToLegacy(VariableSet.newEmptySet());
    }

    default String compileToLegacy(Language language){
        return compileToLegacy(VariableSet.newEmptySet(),language);
    }

    default String compileToLegacy(VariableSet variables){
        return compileToLegacy(variables,null);
    }

    default String compileToLegacy(VariableSet variables,Language language){
        return compileToLegacy((MinecraftConnection)null,variables,language);
    }

    default String compileToLegacy(MinecraftConnection connection,VariableSet variables,Language language){
        StringBuilder builder = new StringBuilder();
        compileToLegacy(builder,connection,variables,language);
        return builder.toString();
    }

    default void compileToLegacy(StringBuilder builder, VariableSet variables,Language language){
        compileToLegacy(builder,null,variables,language);
    }

    void compileToLegacy(StringBuilder builder, MinecraftConnection connection, VariableSet variables,Language language);


    default Document compile(){
        return compile(VariableSet.newEmptySet());
    }

    default Document compile(Language language){
        return compile(VariableSet.newEmptySet(),language);
    }

    default Document compile(VariableSet variables){
        return compile(variables,null);
    }

    default Document compile(VariableSet variables,Language language){
        return compile(null,null,variables,language);
    }

    default Document compile(MinecraftConnection connection,VariableSet variables,Language language){
        return compile(null,connection,variables,language);
    }

    default Document compile(String key, VariableSet variables,Language language){
        return compile(key,null,variables,language);
    }

    Document compile(String key, MinecraftConnection connection, VariableSet variables,Language language);


    default String compileToString(){
        return compileToString(VariableSet.newEmptySet(),null);
    }

    default String compileToString(Language language){
        return compileToString(VariableSet.newEmptySet(),language);
    }

    default String compileToString(VariableSet variables){
        return compileToString(variables,null);
    }

    default String compileToString(VariableSet variables,Language language){
        return DocumentFileType.JSON.getWriter().write(compile(variables,language),false);
    }

    default String compileToString(MinecraftConnection connection,VariableSet variables,Language language){
        return DocumentFileType.JSON.getWriter().write(compile(connection,variables,language),false);
    }

    void decompile(Document data);

    default String toText(VariableSet variables){
        return toPlainText(variables);
    }
}
