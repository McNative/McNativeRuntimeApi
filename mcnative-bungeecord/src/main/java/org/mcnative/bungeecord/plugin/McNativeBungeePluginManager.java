/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 26.08.19, 19:20
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

package org.mcnative.bungeecord.plugin;

import com.google.common.collect.Multimap;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.*;
import net.prematic.libraries.event.EventBus;
import net.prematic.libraries.event.executor.MethodEventExecutor;
import net.prematic.libraries.utility.annonations.Internal;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.reflect.ReflectionUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

public class McNativeBungeePluginManager extends PluginManager {

    private final PluginManager original;
    private final EventBus eventBus;
    private final Map<Class<?>, Consumer<?>> mangedEvents;

    public McNativeBungeePluginManager(PluginManager original,EventBus eventBus) {
        super(null, null, null);
        this.original = original;
        this.eventBus = eventBus;
        this.mangedEvents = new LinkedHashMap<>();
        addEventsFromOriginalManager();
    }

    @Override
    public void registerCommand(Plugin plugin, Command command) {
        original.registerCommand(plugin, command);
    }

    @Override
    public void unregisterCommand(Command command) {
        original.unregisterCommand(command);
    }

    @Override
    public void unregisterCommands(Plugin plugin) {
        original.unregisterCommands(plugin);
    }

    @Override
    public boolean isExecutableCommand(String commandName, CommandSender sender) {
        return original.isExecutableCommand(commandName, sender);
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) {
        return original.dispatchCommand(sender, commandLine);
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine, List<String> tabResults) {
        return original.dispatchCommand(sender, commandLine, tabResults);
    }

    @Override
    public Collection<Plugin> getPlugins() {
        return original.getPlugins();
    }

    @Override
    public Plugin getPlugin(String name) {
        return original.getPlugin(name);
    }

    @Override
    public void loadPlugins() {
        original.loadPlugins();
    }

    @Override
    public void enablePlugins() {
        original.enablePlugins();
    }

    @Override
    public void detectPlugins(File folder) {
        original.detectPlugins(folder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Event> T callEvent(T event) {
        Consumer<Object> manager = (Consumer<Object>) mangedEvents.get(event.getClass());
        if(manager != null){
            manager.accept(event);
            event.postCall();
        }
        else{
            T result = eventBus.callEvent(event);
            event.postCall();
            return result;
        }
        return event;
    }

    @Override
    public void registerListener(Plugin plugin, Listener listener) {
        eventBus.subscribe(new PluginObjectOwner(plugin),listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        eventBus.unsubscribe(listener);
    }

    @Override
    public void unregisterListeners(Plugin plugin) {
        eventBus.unsubscribe(new PluginObjectOwner(plugin));
    }

    @Override
    public Collection<Map.Entry<String, Command>> getCommands() {
        return original.getCommands();
    }

    public <E> void registerMangedEvent(Class<E> eventClass, Consumer<E> manager){
        this.mangedEvents.put(eventClass,manager);
    }

    @SuppressWarnings("unchecked")
    @Internal
    private void addEventsFromOriginalManager(){
        Object bus = ReflectionUtil.getFieldValue(original,"eventBus");
        Map<Class<?>, Map<Byte, Map<Object, Method[]>>> listeners = ReflectionUtil.getFieldValue(bus,"byListenerAndPriority",Map.class);
        Set<Map.Entry<Plugin, Collection<Listener>>> entries = ReflectionUtil.getFieldValue(original,"listenersByPlugin",Multimap.class).asMap().entrySet();

        for (Map.Entry<Class<?>, Map<Byte, Map<Object, Method[]>>> entry : listeners.entrySet()) {
            Class<?> eventClass = entry.getKey();
            for (Map.Entry<Byte, Map<Object, Method[]>> entry2 : entry.getValue().entrySet()) {
                byte priority = entry2.getKey();
                for (Map.Entry<Object, Method[]> entry3 : entry2.getValue().entrySet()) {
                    Object listener = entry.getKey();
                    for (Method method : entry3.getValue()) {
                        Plugin plugin = findOwner(entries,listener);
                        eventBus.addExecutor(eventClass,new MethodEventExecutor(plugin!=null?
                                new PluginObjectOwner(plugin):ObjectOwner.SYSTEM
                                ,priority,listener,eventClass,method));
                    }
                }
            }
        }
    }

    @Internal
    private Plugin findOwner(Set<Map.Entry<Plugin, Collection<Listener>>> entries, Object listener){
        for (Map.Entry<Plugin, Collection<Listener>> entry : entries) {
            for (Listener listener0 : entry.getValue()) if(listener0.equals(listener)) return entry.getKey();
        }
        return null;
    }
}
