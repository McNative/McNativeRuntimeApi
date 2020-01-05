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

import org.mcnative.common.player.OnlineMinecraftPlayer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class ClickAction {

    private static Map<String,ClickAction> REGISTRY = new HashMap<>();

    public static final ClickAction OPEN_URL = new DefaultMinecraftEvent("OPEN_URL");
    public static final ClickAction RUN_COMMAND = new DefaultMinecraftEvent("RUN_COMMAND");
    public static final ClickAction SUGGEST_COMMAND = new DefaultMinecraftEvent("SUGGEST_COMMAND");
    public static final ClickAction CHANGE_PAGE = new DefaultMinecraftEvent("CHANGE_PAGE");

    public static final ClickAction EXECUTE = new ClickAction("EXECUTE"){

        @SuppressWarnings("unchecked")
        @Override
        public void execute(OnlineMinecraftPlayer player, Object value) {
            if(value instanceof Runnable) ((Runnable) value).run();
            else if(value instanceof Consumer<?>) ((Consumer) value).accept(player);
            else throw new IllegalArgumentException("Invalid execute type");
        }
    };

    static {
        registerAction(OPEN_URL);
        registerAction(RUN_COMMAND);
        registerAction(SUGGEST_COMMAND);
        registerAction(CHANGE_PAGE);
    }

    private final String name;
    private final boolean directEvent;

    public ClickAction(String name) {
        this(name,false);
    }

    public ClickAction(String name, boolean directEvent) {
        this.name = name;
        this.directEvent = directEvent;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectEvent(){
        return directEvent;
    }

    public abstract void execute(OnlineMinecraftPlayer player, Object value);

    public static void registerAction(ClickAction action){
        if(REGISTRY.containsKey(action.getName())) throw new IllegalArgumentException("A action with the name "+action.getName()+"is already registered");
        REGISTRY.put(action.getName(),action);
    }

    public static ClickAction of(String name){
        ClickAction action = REGISTRY.get(name);
        if(action == null) throw new IllegalArgumentException("Action "+name+" not found");
        return action;
    }

    private static class DefaultMinecraftEvent extends ClickAction {

        private DefaultMinecraftEvent(String name) {
            super(name,true);
        }

        @Override
        public void execute(OnlineMinecraftPlayer player, Object value) {
            //Unused (Implemented in Minecraft)
        }
    }

}
