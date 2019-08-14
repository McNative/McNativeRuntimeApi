/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.08.19, 20:16
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

import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.event.TextEvent;

import java.util.Collection;
import java.util.function.Consumer;

public interface MessageComponent<T extends MessageComponent> {

    TextEvent<ClickAction> getClickEvent();

    TextEvent<HoverAction> getHoverEvent();

    T setClickEvent(TextEvent<ClickAction> event);

    T setHoverEvent(TextEvent<HoverAction> event);

    default T setClickEvent(ClickAction action, Object value){
        return setClickEvent(new TextEvent<>(action, value));
    }

    default T setHoverEvent(HoverAction action, Object value){
        return setHoverEvent(new TextEvent<>(action, value));
    }

    default T onClick(Consumer<OnlineMinecraftPlayer> callback){
        return setClickEvent(ClickAction.EXECUTE,callback);
    }


    Collection<MessageComponent> getExtras();

    T addExtra(MessageComponent component);

    T removeExtra(MessageComponent component);


    default String toPlainText(){
        StringBuilder builder = new StringBuilder();
        toPlainText(builder);
        return builder.toString();
    }

    void toPlainText(StringBuilder builder);

    default String compile(){
        StringBuilder builder = new StringBuilder();
        compile(builder);
        return builder.toString();
    }

    void compile(StringBuilder builder);
}
