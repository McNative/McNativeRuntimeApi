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

import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.event.TextEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MessageComponentSet extends ArrayList<MessageComponent> implements MessageComponent<MessageComponentSet> {

    private TextEvent<ClickAction> clickEvent;
    private TextEvent<HoverAction> hoverEvent;

    public MessageComponentSet() {}

    public MessageComponentSet(Collection<? extends MessageComponent> c) {
        super(c);
    }

    @Override
    public TextEvent<ClickAction> getClickEvent() {
        return clickEvent;
    }

    @Override
    public TextEvent<HoverAction> getHoverEvent() {
        return hoverEvent;
    }

    @Override
    public MessageComponentSet setClickEvent(TextEvent<ClickAction> event) {
        this.clickEvent = event;
        return this;
    }

    @Override
    public MessageComponentSet setHoverEvent(TextEvent<HoverAction> event) {
        this.hoverEvent = event;
        return null;
    }

    public MessageComponentSet add(MessageComponent... components){
        addAll(Arrays.asList(components));
        return this;
    }

    @Override
    public Collection<MessageComponent> getExtras() {
        return this;
    }

    @Override
    public MessageComponentSet addExtra(MessageComponent component) {
        add(component);
        return this;
    }

    @Override
    public MessageComponentSet removeExtra(MessageComponent component) {
        remove(component);
        return this;
    }


    @Override
    public void compile(StringBuilder builder) {

    }

    @Override
    public void toPlainText(StringBuilder builder) {

    }
}
