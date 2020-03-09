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

import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.message.MessagePack;
import net.prematic.libraries.message.MessageProvider;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.lifecycle.LifecycleState;
import net.prematic.libraries.plugin.loader.PluginLoader;
import net.prematic.libraries.plugin.manager.PluginManager;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.Validate;
import net.prematic.libraries.utility.annonations.Internal;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.*;
import org.mcnative.bukkit.McNativeLauncher;
import org.mcnative.common.McNative;
import org.mcnative.common.event.service.ServiceRegisterEvent;
import org.mcnative.common.event.service.ServiceUnregisterEvent;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

//@Todo find better parameterized type solution
public class BukkitPluginManager implements PluginManager {

    private final static String LOADER_CLASS_NAME = "BukkitMcNativePluginBootstrap";

    private final ServicesManager serviceManager;
    private final Map<String, BiConsumer<Plugin,LifecycleState>> stateListeners;
    private final Collection<PluginLoader> loaders;
    private final Collection<Plugin> plugins;

    public BukkitPluginManager() {
        this.serviceManager = Bukkit.getServicesManager();
        this.stateListeners = new HashMap<>();
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
        Plugin<?> plugin =  getPlugin(name);
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
    public PluginLoader createPluginLoader(File file, PluginDescription pluginDescription) {
        throw new UnsupportedOperationException("Bukkit bridge is not able to detect plugin descriptions");
    }

    @Override
    public PluginDescription detectPluginDescription(File file) {
        throw new UnsupportedOperationException("Bukkit bridge is not able to detect plugin descriptions");
    }

    @Override
    public Collection<PluginDescription> detectPluginDescriptions(File file) {
        throw new UnsupportedOperationException("Bukkit bridge is not able to detect plugin descriptions");
    }

    @Override
    public void setLifecycleStateListener(String s, BiConsumer<Plugin, LifecycleState> biConsumer) {
        this.stateListeners.put(s,biConsumer);
    }

    @Override
    public void executeLifecycleStateListener(String state, LifecycleState stateEvent, Plugin plugin) {
        if(state.equals(LifecycleState.CONSTRUCTION)) this.plugins.add(plugin);
        else if(state.equals(LifecycleState.INITIALISATION)) loadMessages(plugin);
        else if(state.equals(LifecycleState.UNLOAD)) this.plugins.remove(plugin);

        BiConsumer<Plugin,LifecycleState> listener = this.stateListeners.get(state);
        if(listener != null) listener.accept(plugin,stateEvent);
    }

    private void loadMessages(Plugin plugin) {
        MessageProvider messageProvider = McNative.getInstance().getRegistry().getServiceOrDefault(MessageProvider.class);
        if(messageProvider != null){
            String module = plugin.getDescription().getMessageModule();
            if(module != null){
                List<MessagePack> result = messageProvider.loadPacks(module);
                if(result.isEmpty()){
                    String languageTag = Locale.getDefault().toLanguageTag().replace("-","_");
                    String language = Locale.getDefault().getLanguage();
                    InputStream stream = plugin.getLoader().getClassLoader().getResourceAsStream("messages/"+languageTag+".yml");
                    if(stream == null){
                        stream = plugin.getLoader().getClassLoader().getResourceAsStream("messages/"+language+".yml");
                    }
                    if(stream == null){
                        stream = plugin.getLoader().getClassLoader().getResourceAsStream("messages/default.yml");
                    }
                    if(stream != null){
                        Document pack = DocumentFileType.YAML.getReader().read(stream, StandardCharsets.UTF_8);
                        messageProvider.importPack(pack);
                    }
                }

                messageProvider.calculateMessages();
            }
        }
    }


    @Override
    public Collection<Plugin> enablePlugins(File file) {//@Todo return mapped plugin
        try {
            Bukkit.getPluginManager().loadPlugin(file);
        } catch (InvalidPluginException | InvalidDescriptionException e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    @Override
    public void disablePlugins() {
        Bukkit.getPluginManager().disablePlugins();
    }

    @Override
    public void provideLoader(PluginLoader pluginLoader) {
        this.loaders.add(pluginLoader);
    }

    @Override
    public Collection<Class<?>> getAvailableServices() {
        return serviceManager.getKnownServices();
    }

    @Override
    public <T> Collection<T> getServices(Class<T> serviceClass) {
        Validate.notNull(serviceClass);
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
        Validate.notNull(serviceClass);
        RegisteredServiceProvider<T> services = serviceManager.getRegistration(serviceClass);
        if(services != null) return services.getProvider();
        else if(supplier != null) return supplier.get();
        return null;
    }

    @Override
    public <T> void registerService(ObjectOwner owner, Class<T> serviceClass, T service, byte priority) {
        Validate.notNull(owner,serviceClass,service);

        org.bukkit.plugin.Plugin mappedOwner;
        if(owner instanceof Plugin<?>) mappedOwner = getMappedPlugin((Plugin<?>) owner);
        else mappedOwner = McNativeLauncher.getPlugin();

        serviceManager.register(serviceClass,service,mappedOwner, ServicePriority.Normal);
        McNative.getInstance().getLocal().getEventBus().callEvent(new ServiceRegisterEvent(service, owner, priority));
    }

    @Override
    public <T> boolean isServiceAvailable(Class<T> serviceClass) {
        Validate.notNull(serviceClass);
        return serviceManager.getKnownServices().contains(serviceClass);
    }

    @Override
    public void unregisterService(Object o) {
        Validate.notNull(o);
        serviceManager.unregister(o);
        for (RegisteredServiceProvider<?> registration : serviceManager.getRegistrations(o.getClass())) {
            if(registration.getProvider().equals(o)) {
                serviceManager.unregister(registration.getProvider());
                McNative.getInstance().getLocal().getEventBus().callEvent(new ServiceUnregisterEvent(registration.getProvider(),
                        getMappedPlugin(registration.getPlugin())));
            }
        }
    }

    @Override
    public void unregisterServices(Class<?> aClass) {
        Validate.notNull(aClass);
        for (RegisteredServiceProvider<?> registration : serviceManager.getRegistrations(aClass)) {
            serviceManager.unregister(registration.getProvider());
            McNative.getInstance().getLocal().getEventBus().callEvent(new ServiceUnregisterEvent(registration.getProvider(),
                    getMappedPlugin(registration.getPlugin())));
        }
    }

    @Override
    public void unregisterServices(ObjectOwner owner) {
        if(owner instanceof Plugin<?>){
            for (RegisteredServiceProvider<?> registration : serviceManager.getRegistrations(getMappedPlugin((Plugin<?>) owner))) {
                serviceManager.unregister(registration.getProvider());
                McNative.getInstance().getLocal().getEventBus().callEvent(new ServiceUnregisterEvent(registration.getProvider()
                        ,getMappedPlugin(registration.getPlugin())));
            }
            //serviceManager.unregister(getMappedPlugin((Plugin<?>) owner));
        }else throw new IllegalArgumentException("It is not possible to unsubscribe services, if the owner is not a plugin");
    }

    @Override
    public void shutdown() {
        //Unused and ignored
    }

    protected void registerBukkitPlugin(org.bukkit.plugin.Plugin plugin){
        if(!plugin.getClass().getSimpleName().equals(LOADER_CLASS_NAME)) plugins.add(new MappedPlugin(plugin));
    }

    protected void unregisterBukkitPlugin(org.bukkit.plugin.Plugin plugin){
        this.plugins.remove(plugin);
    }

    @Internal
    public org.bukkit.plugin.Plugin getMappedPlugin(Plugin<?> original){//@Todo find better solution
        Validate.notNull(original);
        for (org.bukkit.plugin.Plugin plugin : Bukkit.getPluginManager().getPlugins()){
            if(plugin.getClass().getSimpleName().equals(LOADER_CLASS_NAME) && plugin.getName().equals(original.getName())){
                return plugin;
            }
        }
        throw new IllegalArgumentException("McNative Mapping error (plugin / mcnative -> bukkit)");
    }

    @Internal
    public Plugin<?> getMappedPlugin(org.bukkit.plugin.Plugin original){
        Validate.notNull(original);
        for (Plugin<?> plugin : plugins) if(plugin.equals(original)) return plugin;
        throw new IllegalArgumentException("McNative Mapping error (plugin / bukkit -> mcnative)");
    }

    @Internal
    @SuppressWarnings("unchecked")
    public void inject(){//Must be synchronized for security reasons
        synchronized (this){
            List<org.bukkit.plugin.Plugin> original = (List<org.bukkit.plugin.Plugin>) ReflectionUtil.getFieldValue(Bukkit.getPluginManager(),"plugins");
            McNativePluginWrapperList override = new McNativePluginWrapperList(original,this);
            ReflectionUtil.changeFieldValue(Bukkit.getPluginManager(),"plugins",override);
            for (org.bukkit.plugin.Plugin plugin : original) registerBukkitPlugin(plugin);
        }
    }

    private byte mapServicePriority(ServicePriority priority) {
        switch (priority) {
            case Highest: return net.prematic.libraries.plugin.service.ServicePriority.HIGHEST;
            case High: return net.prematic.libraries.plugin.service.ServicePriority.HIGH;
            case Low: return net.prematic.libraries.plugin.service.ServicePriority.LOW;
            case Lowest: return net.prematic.libraries.plugin.service.ServicePriority.LOWEST;
            default: return net.prematic.libraries.plugin.service.ServicePriority.NORMAL;
        }
    }
}
