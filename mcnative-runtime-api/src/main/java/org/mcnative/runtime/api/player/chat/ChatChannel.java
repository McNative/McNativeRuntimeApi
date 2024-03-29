/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.04.20, 20:51
 * @web %web%
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

package org.mcnative.runtime.api.player.chat;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.player.receiver.LocalReceiverChannel;

public interface ChatChannel extends LocalReceiverChannel {

    void setMessageFormatter(ChatFormatter formatter);

    void chat(OnlineMinecraftPlayer player, String message);

    void chat(OnlineMinecraftPlayer player, String message, VariableSet variables);

    static ChatChannel newChatChannel(){
        return McNative.getInstance().getObjectFactory().createObject(ChatChannel.class);
    }

    static ChatChannel newChatChannel(String name){
        return McNative.getInstance().getObjectFactory().createObject(ChatChannel.class,name);
    }
}
