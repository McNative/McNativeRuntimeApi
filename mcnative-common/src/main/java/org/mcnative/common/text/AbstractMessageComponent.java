/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.08.19, 18:15
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
import java.util.Collection;

public abstract class AbstractMessageComponent<T extends MessageComponent> implements MessageComponent<T> {

    private TextEvent<ClickAction> clickEvent;
    private TextEvent<HoverAction> hoverEvent;
    private final Collection<MessageComponent> extras;

    public AbstractMessageComponent() {
        this.extras = new ArrayList<>();
    }

    @Override
    public TextEvent<ClickAction> getClickEvent() {
        return this.clickEvent;
    }

    @Override
    public TextEvent<HoverAction> getHoverEvent() {
        return this.hoverEvent;
    }

    @Override
    public T setClickEvent(TextEvent<ClickAction> event) {
        this.clickEvent = event;
        return (T) this;
    }

    @Override
    public T setHoverEvent(TextEvent<HoverAction> event) {
        this.hoverEvent = event;
        return (T) this;
    }

    @Override
    public Collection<MessageComponent> getExtras() {
        return extras;
    }

    @Override
    public T addExtra(MessageComponent component) {
        this.extras.add(component);
        return (T) this;
    }

    @Override
    public T removeExtra(MessageComponent component) {
        this.extras.remove(component);
        return (T) this;
    }

}
