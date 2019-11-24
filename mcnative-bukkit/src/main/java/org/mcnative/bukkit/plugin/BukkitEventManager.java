/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:46
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

package org.mcnative.bukkit.plugin;

import net.prematic.libraries.event.EventManager;
import net.prematic.libraries.event.Listener;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BukkitEventManager implements PluginManager, EventManager {

    //private final EventManager eventManager;
    private final PluginManager pluginManager;

   // private final Map<Class<?>, List<MethodEntry>> methods;

    public BukkitEventManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    //McNative event handle

    public void registerListener(ObjectOwner owner, Object listener) {
        for(Method method : listener.getClass().getDeclaredMethods()){
            if(method.getParameterTypes().length == 1){
                byte priority;
                Listener info = method.getAnnotation(Listener.class);
                if(info != null) priority = info.priority();
                else{
                    EventHandler bukkitInfo = method.getAnnotation(EventHandler.class);
                    if(bukkitInfo == null) return;
                }

               // List<MethodEntry> methods = this.methods.computeIfAbsent(method.getParameterTypes()[0], k -> new ArrayList<>());
                //methods.add(new MethodEntry(priority,owner,listener,method));

                //Sort all listeners by the priority.
               // methods.sort((o1, o2) -> o1.getPriority() >= o2.getPriority()?0:-1);

            }
        }
    }

    @Override
    public void subscribe(ObjectOwner objectOwner, Object o) {

    }

    @Override
    public <T> void subscribe(ObjectOwner objectOwner, Class<T> aClass, Consumer<T> consumer, byte b) {

    }

    @Override
    public void unsubscribe(Object o) {

    }

    @Override
    public void unsubscribe(Consumer<?> consumer) {

    }

    @Override
    public void unsubscribe(ObjectOwner objectOwner) {

    }

    @Override
    public void unsubscribeAll(Class<?> aClass) {

    }

    @Override
    public <T, E extends T> E callEvent(Class<T> aClass, E e) {
        return null;
    }

    @Override
    public <T, E extends T> void callEventAsync(Class<T> aClass, E e, Consumer<T> consumer) {

    }

    @Override
    public <T, E extends T> CompletableFuture<T> callEventAsync(Class<T> aClass, E e) {
        return null;
    }


    //Bukkit event handle

    @Override
    public void callEvent(Event event) throws IllegalStateException {

    }


    @Override
    public void registerEvents(org.bukkit.event.Listener listener, Plugin plugin) {
    }

    @Override
    public void registerInterface(Class<? extends PluginLoader> aClass) throws IllegalArgumentException {
        this.pluginManager.registerInterface(aClass);
    }

    @Override
    public Plugin getPlugin(String s) {
        return this.pluginManager.getPlugin(s);
    }

    @Override
    public Plugin[] getPlugins() {
        return this.pluginManager.getPlugins();
    }

    @Override
    public boolean isPluginEnabled(String s) {
        return this.pluginManager.isPluginEnabled(s);
    }

    @Override
    public boolean isPluginEnabled(Plugin plugin) {
        return this.pluginManager.isPluginEnabled(plugin);
    }

    @Override
    public Plugin loadPlugin(File file) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {
        return this.pluginManager.loadPlugin(file);
    }

    @Override
    public Plugin[] loadPlugins(File file) {
        return this.pluginManager.loadPlugins(file);
    }

    @Override
    public void disablePlugins() {
        this.pluginManager.disablePlugins();
    }

    @Override
    public void clearPlugins() {
        this.pluginManager.clearPlugins();
    }

    @Override
    public void registerEvent(Class<? extends Event> aClass, org.bukkit.event.Listener listener, EventPriority eventPriority, EventExecutor eventExecutor, Plugin plugin) {
        this.pluginManager.registerEvent(aClass,listener,eventPriority,eventExecutor,plugin);
    }

    @Override
    public void registerEvent(Class<? extends Event> aClass, org.bukkit.event.Listener listener, EventPriority eventPriority, EventExecutor eventExecutor, Plugin plugin, boolean b) {
        this.pluginManager.registerEvent(aClass,listener,eventPriority,eventExecutor,plugin,b);
    }

    @Override
    public void enablePlugin(Plugin plugin) {
        this.pluginManager.enablePlugin(plugin);
    }

    @Override
    public void disablePlugin(Plugin plugin) {
        this.pluginManager.disablePlugin(plugin);
    }

    //Wrap
    @Override
    public Permission getPermission(String s) {
        return this.pluginManager.getPermission(s);
    }

    @Override
    public void addPermission(Permission permission) {
        this.pluginManager.addPermission(permission);
    }

    @Override
    public void removePermission(Permission permission) {
        this.pluginManager.removePermission(permission);
    }

    @Override
    public void removePermission(String s) {
        this.pluginManager.removePermission(s);
    }

    @Override
    public Set<Permission> getDefaultPermissions(boolean b) {
        return this.pluginManager.getDefaultPermissions(b);
    }

    @Override
    public void recalculatePermissionDefaults(Permission permission) {
        this.pluginManager.recalculatePermissionDefaults(permission);
    }

    @Override
    public void subscribeToPermission(String s, Permissible permissible) {
        this.pluginManager.subscribeToPermission(s, permissible);
    }

    @Override
    public void unsubscribeFromPermission(String s, Permissible permissible) {
        this.pluginManager.unsubscribeFromPermission(s, permissible);
    }

    @Override
    public Set<Permissible> getPermissionSubscriptions(String s) {
        return this.pluginManager.getPermissionSubscriptions(s);
    }

    @Override
    public void subscribeToDefaultPerms(boolean b, Permissible permissible) {
        this.pluginManager.subscribeToDefaultPerms(b, permissible);
    }

    @Override
    public void unsubscribeFromDefaultPerms(boolean b, Permissible permissible) {
        this.pluginManager.unsubscribeFromDefaultPerms(b, permissible);
    }

    @Override
    public Set<Permissible> getDefaultPermSubscriptions(boolean b) {
        return this.pluginManager.getDefaultPermSubscriptions(b);
    }

    @Override
    public Set<Permission> getPermissions() {
        return this.pluginManager.getPermissions();
    }

    @Override
    public boolean useTimings() {
        return this.pluginManager.useTimings();
    }

    public static BukkitEventManager initialize(){
        BukkitEventManager manager = new BukkitEventManager(Bukkit.getServer().getPluginManager());

        Bukkit.getServer().getLogger().info("Overriding Bukkit plugin manager to McNative plugin manager.");
        ReflectionUtil.changeFieldValue(Bukkit.getServer(),"pluginManager",manager);

        return manager;
    }
}
