/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 06.10.19, 18:44
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
import net.prematic.libraries.document.DocumentRegistry;
import net.prematic.libraries.document.entry.ArrayEntry;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.variable.VariableSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MessageComponentSet implements MessageComponent<MessageComponentSet> {

    private final List<MessageComponent<?>> components;

    public MessageComponentSet() {
        this(new ArrayList<>());
    }

    public MessageComponentSet(List<MessageComponent<?>> components) {
        this.components = components;
    }

    public List<MessageComponent<?>> getComponents() {
        return components;
    }

    public int size(){
        return components.size();
    }

    public void add(MessageComponent<?> component){
        components.add(component);
    }

    public void remove(MessageComponent<?> component){
        components.add(component);
    }

    public void clear(){
        components.clear();
    }

    @Override
    public Collection<MessageComponent<?>> getExtras() {
        return components;
    }

    @Override
    public MessageComponentSet addExtra(MessageComponent<?> component) {
        add(component);
        return this;
    }

    @Override
    public MessageComponentSet removeExtra(MessageComponent<?> component) {
        remove(component);
        return this;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables) {
        components.forEach(component -> component.toPlainText(builder, variables));
    }

    @Override
    public Document compile(String key, VariableSet variables) {
        ArrayEntry entry = DocumentRegistry.getFactory().newArrayEntry(key);
        components.forEach(component -> entry.entries().add(component.compile(variables)));
        return entry;
    }

    @Override
    public void decompile(Document data) {
        this.components.addAll(Text.decompileArray(data));
    }

}