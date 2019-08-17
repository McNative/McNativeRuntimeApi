/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.08.19, 20:05
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MessageComponentSet extends ArrayList<MessageComponent> implements MessageComponent{

    public MessageComponentSet() {}

    public MessageComponentSet(Collection<? extends MessageComponent> c) {
        super(c);
    }

    public MessageComponentSet addComponent(MessageComponent component){
        add(component);
        return this;
    }

    public MessageComponentSet addComponent(MessageComponent... component){
        addAll(Arrays.asList(component));
        return this;
    }

    public MessageComponentSet removeComponent(MessageComponent component){
        remove(component);
        return this;
    }

    public MessageComponentSet removeComponent(MessageComponent... component){
        removeAll(Arrays.asList(component));
        return this;
    }

    @Override
    public void compile(StringBuilder builder) {

    }

    @Override
    public void toPlainText(StringBuilder builder) {

    }
}
