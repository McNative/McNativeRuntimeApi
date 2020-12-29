/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.09.19, 20:24
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

import net.pretronic.libraries.utility.interfaces.Castable;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.text.event.ClickAction;
import org.mcnative.runtime.api.text.event.HoverAction;
import org.mcnative.runtime.api.text.event.TextEvent;
import org.mcnative.runtime.api.text.format.MessageFormatAble;

import java.util.function.Consumer;

public interface ChatComponent<T extends ChatComponent<?>> extends MessageComponent<T>, MessageFormatAble<T>, Castable<ChatComponent<?>> {

    TextEvent<ClickAction> getClickEvent();

    TextEvent<HoverAction> getHoverEvent();

    T setClickEvent(TextEvent<ClickAction> event);

    T setHoverEvent(TextEvent<HoverAction> event);

    String getInsertion();

    T setInsertion(String insertion);


    default T onClick(ClickAction action, Object value){
        return setClickEvent(new TextEvent<>(action, value));
    }

    default T onClick(Runnable callback){
        return onClick(ClickAction.EXECUTE,callback);
    }

    default T onClick(Consumer<OnlineMinecraftPlayer> callback){
        return onClick(ClickAction.EXECUTE,callback);
    }

    default T onHover(HoverAction action, String value){
        return setHoverEvent(new TextEvent<>(action, value));
    }


}
