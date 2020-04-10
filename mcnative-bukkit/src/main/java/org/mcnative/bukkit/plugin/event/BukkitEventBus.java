/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.02.20, 09:44
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

package org.mcnative.bukkit.plugin.event;


import net.pretronic.libraries.event.EventBus;
import net.pretronic.libraries.event.Listener;
import net.pretronic.libraries.event.executor.ConsumerEventExecutor;
import net.pretronic.libraries.event.executor.EventExecutor;
import net.pretronic.libraries.event.executor.MethodEventExecutor;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.mcnative.bukkit.plugin.BukkitPluginManager;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BukkitEventBus implements EventBus {

    private final Executor executor;
    private final BukkitPluginManager pluginManager;
    private final Plugin owner;
    private final Map<Class<?>,Class<?>> mappedClasses;

    private final Map<Class<?>, List<EventExecutor>> executors;

    public BukkitEventBus(Executor executor,BukkitPluginManager pluginManager,Plugin owner) {
        this.executor = executor;
        this.pluginManager = pluginManager;
        this.owner = owner;
        this.executors = new LinkedHashMap<>();
        this.mappedClasses = new HashMap<>();
    }

    @Override
    public void subscribe(ObjectOwner owner, Object listener) {
        Validate.notNull(owner,listener);

        for(Method method : listener.getClass().getDeclaredMethods()){
            try{
                Listener info = method.getAnnotation(Listener.class);
                if(info != null && method.getParameterTypes().length == 1){
                    Class<?> eventClass = method.getParameterTypes()[0];
                    Class<?> mappedClass = this.mappedClasses.get(eventClass);
                    if(mappedClass == null) mappedClass = eventClass;
                    addExecutor(mappedClass,new MethodEventExecutor(owner,info.priority(),listener,eventClass,method));
                }
            }catch (Exception exception){
                throw new IllegalArgumentException("Could not register listener "+listener,exception);
            }
        }
    }

    @Override
    public <T> void subscribe(ObjectOwner owner, Class<T> original, Consumer<T> listener, byte priority) {
        Validate.notNull(owner,original,listener);

        Class<?> mappedClass = this.mappedClasses.get(original);
        if(mappedClass == null) mappedClass = original;

        addExecutor(mappedClass,new ConsumerEventExecutor<>(owner,priority,original,listener));
    }

    @Override
    public void unsubscribe(Object listener) {
        executors.forEach((event, executors) -> Iterators.removeSilent(executors,
                executor -> executor instanceof MethodEventExecutor
                        && ((MethodEventExecutor) executor).getListener().equals(listener)));

        for (HandlerList handlerList : HandlerList.getHandlerLists()) {
            if(handlerList instanceof McNativeHandlerList){
                ((McNativeHandlerList) handlerList).unregister(listener);
            }else if(listener instanceof org.bukkit.event.Listener){
                handlerList.unregister((org.bukkit.event.Listener) listener);
            }
        }
    }

    @Override
    public void unsubscribe(Consumer<?> handler) {
        executors.forEach((event, executors) -> Iterators.removeSilent(executors,
                executor -> executor instanceof ConsumerEventExecutor
                        && ((ConsumerEventExecutor) executor).getConsumer().equals(handler)));

        for (HandlerList handlerList : HandlerList.getHandlerLists()) {
            if(handlerList instanceof McNativeHandlerList) ((McNativeHandlerList) handlerList).unregister(handler);
        }
    }

    @Override
    public void unsubscribe(ObjectOwner owner) {
        Validate.notNull(owner);
        executors.forEach((event, executors) -> Iterators.removeSilent(executors, executor -> executor.getOwner().equals(owner)));
        for (HandlerList handlerList : HandlerList.getHandlerLists()) {
            if(handlerList instanceof McNativeHandlerList) ((McNativeHandlerList) handlerList).unregister(owner);
            else{
                if(owner instanceof net.pretronic.libraries.plugin.Plugin<?>){
                    HandlerList.unregisterAll(pluginManager.getMappedPlugin((net.pretronic.libraries.plugin.Plugin<?>) owner));
                }
            }
        }
    }

    @Override
    public void unsubscribeAll(Class<?> event) {
        Validate.notNull(event);
        if(Event.class.isAssignableFrom(event)){
            McNativeHandlerList handlerList = McNativeHandlerList.get(event);
            if(handlerList != null) handlerList.clear();
        }else{
            this.executors.remove(event);
        }
    }

    @Override
    public void addExecutor(Class<?> event, EventExecutor executor) {
        Validate.notNull(event,executor);
        if(Event.class.isAssignableFrom(event)){
            McNativeHandlerList handlerList = getHandlerList(event);
            handlerList.registerExecutor(executor);
            handlerList.bake();
        }else{
            List<EventExecutor> executors = this.executors.computeIfAbsent(event, k -> new ArrayList<>());
            executors.add(executor);
            executors.sort((o1, o2) -> o1.getPriority() >= o2.getPriority()?0:-1);
        }
    }

    @Override
    public <T> void callEvents(Class<T> event, Object... objects) {
        Validate.notNull(event,objects);
        if(Event.class.isAssignableFrom(event)){
            McNativeHandlerList handlerList = getHandlerList(event);
            handlerList.callEvents(objects);
        }else{
            List<EventExecutor> executors = this.executors.get(event);
            if(executors != null) executors.forEach(executor ->executor.execute(objects));
        }
    }

    @Override
    public <T> void callEventsAsync(Class<T> executionClass, Runnable runnable, Object... events) {
        List<EventExecutor> executors = this.executors.get(executionClass);
        if(executors != null) executors.forEach(executor -> executor.execute(events));
    }

    @Override
    public Class<?> getMappedClass(Class<?> original) {
        return this.mappedClasses.get(original);
    }

    @Override
    public <T, E extends T> void callEventAsync(Class<T> executionClass, E event, Consumer<T> callback) {
        if(callback != null) executor.execute(()-> callback.accept(callEvent(executionClass,event)));
        else executor.execute(()-> callEvent(event));
    }

    @Override
    public <T, E extends T> CompletableFuture<T> callEventAsync(Class<T> executionClass, E event) {
        CompletableFuture<T> future = new CompletableFuture<>();
        executor.execute(()->{
            try{
                future.complete(callEvent(executionClass,event));
            }catch (Exception exception){
                future.completeExceptionally(exception);
            }
        });
        return future;
    }

    @Override
    public void registerMappedClass(Class<?> original, Class<?> mapped) {
        Validate.notNull(original,mapped);
        this.mappedClasses.put(original,mapped);
    }

    public <E extends Event> void registerManagedEvent(Class<E> original, BiConsumer<McNativeHandlerList,E> manager){
        McNativeHandlerList handlerList = McNativeHandlerList.get(original);
        if(handlerList == null){
            handlerList = new McNativeHandlerList(owner,original);
            McNativeHandlerList.inject(original,handlerList);
        }
        handlerList.registerManagedEvent(manager);
    }

    private McNativeHandlerList getHandlerList(Class<?> event){
        McNativeHandlerList handlerList = McNativeHandlerList.get(event);
        if(handlerList == null){
            handlerList = new McNativeHandlerList(owner,event);
            McNativeHandlerList.inject(event,handlerList);
        }
        return handlerList;
    }
}
