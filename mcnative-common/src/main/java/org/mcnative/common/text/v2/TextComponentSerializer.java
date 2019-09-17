/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 15.09.19, 15:56
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

package org.mcnative.common.text.v2;

import net.prematic.libraries.document.DocumentEntry;
import net.prematic.libraries.document.adapter.DocumentAdapter;
import net.prematic.libraries.utility.reflect.TypeReference;
import org.mcnative.common.text.ChatComponent;

public class TextComponentSerializer implements DocumentAdapter<ChatComponent> {

    @Override
    public ChatComponent read(DocumentEntry documentEntry, TypeReference<ChatComponent> typeReference) {
        return null;
    }

    @Override
    public DocumentEntry write(String s, ChatComponent chatComponent) {
        return null;
    }
}
