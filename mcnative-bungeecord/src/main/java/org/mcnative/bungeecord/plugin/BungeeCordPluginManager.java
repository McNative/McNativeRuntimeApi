/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.11.19, 20:24
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

import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.lifecycle.LifecycleState;
import net.prematic.libraries.plugin.loader.PluginLoader;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.annonations.Internal;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.map.callback.CallbackMap;
import net.prematic.libraries.utility.map.callback.LinkedHashCallbackMap;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.common.McNative;

import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;

//@Todo Maybe implement custom plugin detection and loader creation
public class BungeeCordPluginManager implements PluginManager {

    private final static String LOADER_CLASS_NAME = "org.mcnative.loader.bootstrap.BungeeCordMcNativePluginBootstrap";

    private final Collection<ServiceEntry> services;
    private final Map<String, BiConsumer<Plugin,LifecycleState>> stateListeners;
    private final Collection<PluginLoader> loaders;
    private final Collection<Plugin> plugins;

    public BungeeCordPluginManager() {
        this.services = new ArrayList<>();
        this.stateListeners = new LinkedHashMap<>();
        this.loaders = new ArrayList<>();
        this.plugins = new ArrayList<>();
    }

    @Override
    public PrematicLogger getLogger() {
        return McNative.getInstance().getLogger();
    }

    @Override
    public Collection<Plugin> getPlugins() {
        return plugins;
    }

    @Override
    public Plugin getPlugin(String name) {
        return Iterators.findOne(this.plugins, plugin -> plugin.getDescription().getName().equals(name));
    }

    @Override
    public Plugin getPlugin(UUID id) {
        return Iterators.findOne(this.plugins, plugin -> plugin.getDescription().getId().equals(id));
    }

    @Override
    public boolean isPluginEnabled(String name) {
        Plugin plugin =  getPlugin(name);
        return plugin != null && plugin.getLoader().isEnabled();
    }

    @Override
    public Collection<PluginLoader> getLoaders() {
        return loaders;
    }

    @Override
    public PluginLoader createPluginLoader(File file) {
        return createPluginLoader(file,null);
    }

    @Override
    public PluginLoader createPluginLoader(File location, PluginDescription description) {
        throw new UnsupportedOperationException("BungeeCord bridge is not able to create plugin loaders");
    }

    @Override
    public PluginDescription detectPluginDescription(File file) {
        throw new UnsupportedOperationException("BungeeCord bridge is not able to detect plugin descriptions");
    }

    @Override
    public Collection<PluginDescription> detectPluginDescriptions(File directory) {
        throw new UnsupportedOperationException("BungeeCord bridge is not able to detect plugin descriptions");
    }

    //Only for McNative Plugins
    @Override
    public void setLifecycleStateListener(String s, BiConsumer<Plugin, LifecycleState> biConsumer) {
        this.stateListeners.put(s,biConsumer);
    }

    @Internal
    @Override
    public void executeLifecycleStateListener(String state, LifecycleState stateEvent, Plugin plugin) {
        if(state.equals(LifecycleState.CONSTRUCTION)) this.plugins.add(plugin);
        else if(state.equals(LifecycleState.UNLOAD)) this.plugins.remove(plugin);

        BiConsumer<Plugin,LifecycleState> listener = this.stateListeners.get(state);
        if(listener != null) listener.accept(plugin,stateEvent);
    }

    @Override
    public Collection<Plugin> enablePlugins(File file) {
        throw new UnsupportedOperationException("BungeeCord bridge is not able to enable plugins");
    }

    @Override
    public void disablePlugins() {
        throw new UnsupportedOperationException("BungeeCord does not support disabling plugins");
    }


    @Override
    public Collection<Class<?>> getAvailableServices() {
        Collection<Class<?>> classes = new HashSet<>();
        services.forEach(serviceEntry -> classes.add(serviceEntry.serviceClass));
        return classes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getServices(Class<T> serviceClass) {
        List<T> services =  Iterators.map(this.services, entry -> (T) entry.service, entry -> entry.serviceClass.equals(serviceClass));
        if(services.isEmpty()) throw new UnsupportedOperationException("Service is not available.");
        return services;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getService(Class<T> serviceClass) {
        List<ServiceEntry> services = Iterators.filter(this.services, entry -> entry.serviceClass.equals(serviceClass));
        services.sort((o1, o2) -> {
            if(o1.priority < o2.priority) return -1;
            else if(o1.priority > o2.priority) return 1;
            return 0;
        });
        return (T) services.get(0).service;
    }

    @Override
    public <T> void registerService(ObjectOwner owner, Class<T> serviceClass, T service, byte priority) {
        this.services.add(new ServiceEntry(owner,serviceClass,service,priority));
    }

    @Override
    public <T> boolean isServiceAvailable(Class<T> serviceClass) {
        for (ServiceEntry entry : this.services) if(entry.serviceClass.equals(serviceClass)) return true;
        return false;
    }

    @Override
    public void unregisterService(Object service) {
        Iterators.removeOne(this.services, entry -> entry.service.equals(service));
    }

    @Override
    public void unregisterServices(Class<?> serviceClass) {
        Iterators.removeOne(this.services, entry -> entry.serviceClass.equals(serviceClass));
    }

    @Override
    public void unregisterServices(ObjectOwner owner) {
        Iterators.removeOne(this.services, entry -> entry.owner.equals(owner));
    }

    @Override
    public void shutdown() {
        //Unused
    }

    @Override
    public void provideLoader(PluginLoader loader) {
        if(loaders.contains(loader)) throw new IllegalArgumentException("Loader is already registered.");
        this.loaders.add(loader);
        if(loader.isInstanceAvailable()) this.plugins.add(loader.getInstance());
    }

    @SuppressWarnings("unchecked")
    public void inject(net.md_5.bungee.api.plugin.PluginManager original){
        Map<String, net.md_5.bungee.api.plugin.Plugin> oldMap = ReflectionUtil.getFieldValue(original,"plugins",Map.class);

        CallbackMap<String, net.md_5.bungee.api.plugin.Plugin> newMap = new LinkedHashCallbackMap<>();
        newMap.setPutCallback((s, plugin) ->{
            if(!plugin.getClass().getName().equals(LOADER_CLASS_NAME)) plugins.add(new MappedPlugin(plugin));
        });
        newMap.setRemoveCallback((s, plugin) -> plugins.remove(plugin));

        ReflectionUtil.changeFieldValue(original,"plugins",newMap);
        newMap.putAll(oldMap);
    }

    private static class ServiceEntry {

        private final ObjectOwner owner;
        private final Class<?> serviceClass;
        private final Object service;
        private final byte priority;

        private ServiceEntry(ObjectOwner owner, Class<?> serviceClass, Object service,byte priority) {
            this.owner = owner;
            this.serviceClass = serviceClass;
            this.service = service;
            this.priority = priority;
        }
    }
}
