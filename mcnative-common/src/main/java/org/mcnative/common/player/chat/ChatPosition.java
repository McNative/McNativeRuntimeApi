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

import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.ArrayList;
import java.util.List;

public class ChatPosition {

    public static final ChatPosition PLAYER_CHAT = new ChatPosition(ObjectOwner.SYSTEM,"PLAYER_CHAT", (byte) 0);
    public static final ChatPosition SYSTEM_CHAT = new ChatPosition(ObjectOwner.SYSTEM,"SYSTEM_INFO", (byte) 1);
    public static final ChatPosition ACTIONBAR = new ChatPosition(ObjectOwner.SYSTEM,"ACTIONBAR", (byte) 2);

    private static final List<ChatPosition> POSITIONS = new ArrayList<>();

    static {
        POSITIONS.add(PLAYER_CHAT);
        POSITIONS.add(SYSTEM_CHAT);
        POSITIONS.add(ACTIONBAR);
    }

    private final ObjectOwner owner;
    private final String name;
    private final byte id;

    public ChatPosition(ObjectOwner owner, String name, byte id) {
        this.owner = owner;
        this.name = name;
        this.id = id;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public byte getId() {
        return id;
    }

    public static ChatPosition of(byte id){
        for (ChatPosition position : POSITIONS) {
            if(position.getId() == id) return position;
        }
        throw new IllegalArgumentException("Chat Position not found");
    }

    public static void registerChatPosition(ChatPosition position){
        POSITIONS.add(PLAYER_CHAT);
    }
}
