/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 02.03.20, 20:48
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
import net.pretronic.libraries.document.DocumentRegistry;
import net.pretronic.libraries.document.entry.ArrayEntry;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.connection.MinecraftConnection;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.text.Text;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupMessageComponent implements MessageComponent<GroupMessageComponent>{

    private final Map<MessageComponent<?>,VariableSet> components;

    public GroupMessageComponent() {
        this.components = new HashMap<>();
    }

    public GroupMessageComponent(Map<MessageComponent<?>,VariableSet> components) {
        this.components = components;
    }

    @Override
    public Collection<MessageComponent<?>> getExtras() {
        return components.keySet();
    }

    @Override
    public GroupMessageComponent addExtra(MessageComponent<?> component) {
        add(component,VariableSet.newEmptySet());
        return this;
    }

    @Override
    public GroupMessageComponent removeExtra(MessageComponent<?> component) {
        Validate.notNull(component);
        components.remove(component);
        return this;
    }

    public void add(MessageComponent<?> component,VariableSet variables){
        Validate.notNull(component,variables);
        this.components.put(component, variables);
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variablesUnused, Language language) {
        for (Map.Entry<MessageComponent<?>, VariableSet> entry : components.entrySet()) {
            entry.getKey().toPlainText(entry.getValue(),language);
            builder.append("\n");
        }
        builder.setLength(builder.length()-1);
    }

    @Override
    public Document compile(String key, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables0, Language language) {
        ArrayEntry result = DocumentRegistry.getFactory().newArrayEntry(key);
        boolean first = true;
        for (Map.Entry<MessageComponent<?>, VariableSet> entry : components.entrySet()) {
            if(first) first = false;
            else result.entries().add(Text.NEW_LINE.compile(version));
            VariableSet variables = entry.getValue() != null ? entry.getValue() : variables0;
            result.entries().add(entry.getKey().compile(null,connection,version,variables,language));
        }
        return result;
    }

    @Override
    public void compileToString(StringBuilder builder, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables0, Language language) {
        if(version.isOlder(MinecraftProtocolVersion.JE_1_8)){
            boolean first = true;
            for (Map.Entry<MessageComponent<?>, VariableSet> entry : components.entrySet()) {
                if(first) first = false;
                else builder.append("\n");
                VariableSet variables = entry.getValue() != null ? entry.getValue() : variables0;
                entry.getKey().compileToString(builder,connection,version,variables,language);
            }
        }else{
            Document result = compile(null,connection,version,variables0,language);
            builder.append(DocumentFileType.JSON.getWriter().write(result,false));
        }
    }

    @Override
    public void decompile(Document data) {
        for (MessageComponent<?> messageComponent : Text.decompileArray(data)) {
            this.components.put(messageComponent,VariableSet.newEmptySet());
        }
    }

    @Override
    public GroupMessageComponent copy() {
        return new GroupMessageComponent(components);
    }
}
