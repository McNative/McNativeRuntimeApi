/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.07.19 22:26
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

package net.prematic.mcnative.common.chat;

import net.prematic.mcnative.common.chat.event.ClickAction;
import net.prematic.mcnative.common.chat.event.HoverAction;
import net.prematic.mcnative.common.chat.event.TextEvent;

public interface TextComponent {

    TextComponent setClickEvent(TextEvent<ClickAction> event);

    TextComponent setHoverEvent(TextEvent<HoverAction> event);



    default TextComponent setClickEvent(ClickAction action, Object value){
        return setClickEvent(new TextEvent<>(action, value));
    }

    default TextComponent setHoverEvent(HoverAction action, Object value){
        return setHoverEvent(new TextEvent<>(action, value));
    }

    default void onClick(Runnable runnable){
        setClickEvent(ClickAction.EXECUTE,runnable);
    }

}
