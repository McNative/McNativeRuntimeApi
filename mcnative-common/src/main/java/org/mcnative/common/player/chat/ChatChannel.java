/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.04.20, 12:25
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

package org.mcnative.common.player.chat;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.receiver.ReceiverChannel;

public interface ChatChannel extends ReceiverChannel {

    //boolean isReadOnly(OnlineMinecraftPlayer player);

    //void setReadOnly(OnlineMinecraftPlayer player, boolean readOnly);

    void setMessageFormatter(ChatFormatter formatter);

    void chat(OnlineMinecraftPlayer player, String message);

    void chat(OnlineMinecraftPlayer player, String message, VariableSet variables);
}
