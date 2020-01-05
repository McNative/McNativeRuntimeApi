/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 05.10.19, 20:20
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

package org.mcnative.common.text;

import net.prematic.libraries.document.adapter.DocumentAdapter;
import net.prematic.libraries.document.entry.DocumentBase;
import net.prematic.libraries.document.entry.DocumentEntry;
import net.prematic.libraries.utility.reflect.TypeReference;
import org.mcnative.common.text.components.ChatComponent;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

public class ChatComponentDocumentAdapter implements DocumentAdapter<MessageComponent> {

    @Override
    public MessageComponent read(DocumentBase entry, TypeReference<MessageComponent> reference) {
        return Text.decompile(entry.toDocument());
    }

    @Override
    public DocumentEntry write(String key, MessageComponent component) {
        return component.compile(key, VariableSet.newEmptySet());
    }
}
