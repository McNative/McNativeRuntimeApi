/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.02.20, 20:56
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

import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.lifecycle.LifecycleState;
import net.prematic.libraries.plugin.loader.PluginLoader;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.mcnative.bukkit.McNativeLauncher;

import java.io.File;
import java.util.Collection;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

//@Todo finish implementation
public class BukkitPluginManager implements PluginManager {

    private final ServicesManager serviceManager;

    public BukkitPluginManager(ServicesManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public PrematicLogger getLogger() {
        return null;
    }

    @Override
    public Collection<Plugin> getPlugins() {
        return null;
    }

    @Override
    public Plugin getPlugin(String s) {
        return null;
    }

    @Override
    public Plugin getPlugin(UUID uuid) {
        return null;
    }

    @Override
    public boolean isPluginEnabled(String s) {
        return false;
    }

    @Override
    public Collection<PluginLoader> getLoaders() {
        return null;
    }

    @Override
    public PluginLoader createPluginLoader(File file) {
        return null;
    }

    @Override
    public PluginLoader createPluginLoader(File file, PluginDescription pluginDescription) {
        return null;
    }

    @Override
    public PluginDescription detectPluginDescription(File file) {
        return null;
    }

    @Override
    public Collection<PluginDescription> detectPluginDescriptions(File file) {
        return null;
    }

    @Override
    public void setLifecycleStateListener(String s, BiConsumer<Plugin, LifecycleState> biConsumer) {

    }

    @Override
    public void executeLifecycleStateListener(String s, LifecycleState lifecycleState, Plugin plugin) {

    }

    @Override
    public Collection<Plugin> enablePlugins(File file) {
        return null;
    }

    @Override
    public void disablePlugins() {

    }

    @Override
    public void provideLoader(PluginLoader pluginLoader) {

    }

    @Override
    public Collection<Class<?>> getAvailableServices() {
        return serviceManager.getKnownServices();
    }

    @Override
    public <T> Collection<T> getServices(Class<T> serviceClass) {
        Collection<RegisteredServiceProvider<T>> services = serviceManager.getRegistrations(serviceClass);
        return Iterators.map(services, RegisteredServiceProvider::getProvider);
    }

    @Override
    public <T> T getService(Class<T> serviceClass) {
        T result = getServiceOrDefault(serviceClass,null);
        if(result == null) throw new UnsupportedOperationException("Service is not available.");
        return result;
    }

    @Override
    public <T> T getServiceOrDefault(Class<T> serviceClass) {
        return getServiceOrDefault(serviceClass,null);
    }

    @Override
    public <T> T getServiceOrDefault(Class<T> serviceClass, Supplier<T> supplier) {
        RegisteredServiceProvider<T> services = serviceManager.getRegistration(serviceClass);
        if(services != null) return services.getProvider();
        else if(supplier != null) return supplier.get();
        return null;
    }

    @Override
    public <T> void registerService(ObjectOwner objectOwner, Class<T> serviceClass, T service, byte priority) {
        serviceManager.register(serviceClass,service, McNativeLauncher.getPlugin(), ServicePriority.Normal);//@Todo map service priority and owner
    }

    @Override
    public <T> boolean isServiceAvailable(Class<T> serviceClass) {
        return serviceManager.getKnownServices().contains(serviceClass);
    }

    @Override
    public void unregisterService(Object o) {

    }

    @Override
    public void unregisterServices(Class<?> aClass) {

    }

    @Override
    public void unregisterServices(ObjectOwner objectOwner) {

    }

    @Override
    public void shutdown() {

    }
}
