/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.common.text.event;

import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.support.JEProtocolSupport;

public class HoverAction {

    public static HoverAction SHOW_TEXT = new HoverAction("show_text");
    public static HoverAction SHOW_ITEM = new HoverAction("show_item");
    public static HoverAction SHOW_ENTITY = new HoverAction("show_entity");

    @JEProtocolSupport(max= MinecraftProtocolVersion.JE_1_11_2)
    public static HoverAction SHOW_ACHIEVEMENT = new HoverAction("show_achievement");

    private final String name;

    public HoverAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static HoverAction of(String name){
        if(name.equalsIgnoreCase(SHOW_TEXT.name)) return SHOW_TEXT;
        else if(name.equalsIgnoreCase(SHOW_ITEM.name)) return SHOW_ITEM;
        else if(name.equalsIgnoreCase(SHOW_ENTITY.name)) return SHOW_ENTITY;
        else if(name.equalsIgnoreCase(SHOW_ACHIEVEMENT.name)) return SHOW_ACHIEVEMENT;
        else throw new IllegalArgumentException("Action "+name+" not found");
    }
}
