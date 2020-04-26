/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.11.19, 16:30
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

package org.mcnative.loader.bootstrap;

import net.pretronic.libraries.plugin.description.PluginVersion;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.mcnative.common.McNative;
import org.mcnative.loader.GuestPluginExecutor;
import org.mcnative.loader.McNativeLoader;
import org.mcnative.loader.PlatformExecutor;

import java.io.IOException;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class BukkitMcNativePluginBootstrap extends JavaPlugin implements Listener, PlatformExecutor {

    private static final String ENVIRONMENT_NAME = "Bukkit";
    private GuestPluginExecutor executor;

    @Override
    public void onLoad() {
        try{
            if(!McNativeLoader.install(getLogger(),ENVIRONMENT_NAME)) return;
            this.executor = new GuestPluginExecutor(this,getFile(),getLogger(),ENVIRONMENT_NAME);

            if(!this.executor.install() || !this.executor.installDependencies()){
                this.executor = null;
                getServer().getPluginManager().disablePlugin(this);
                return;
            }

            this.executor.loadGuestPlugin();

            PluginVersion version = this.executor.getLoader().getDescription().getVersion();
            ReflectionUtil.changeFieldValue(getDescription(),"version",version.getName());
        }catch (Exception exception){
            this.executor = null;
            getLogger().log(Level.SEVERE,String.format("Could not load plugin (%s)",exception.getMessage()));
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onEnable() {
        try{
            if(this.executor != null){
                this.executor.enableGuestPlugin();
                Bukkit.getPluginManager().registerEvents(this,this);
            }
        }catch (Exception exception){
            this.executor = null;
            exception.printStackTrace();
            getLogger().log(Level.SEVERE,String.format("Could not enable plugin (%s)",exception.getMessage()));
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try{
            if(this.executor != null) this.executor.disableGuestPlugin();
        }catch (Exception exception){
            this.executor = null;
            exception.printStackTrace();
            getLogger().log(Level.SEVERE,String.format("Could not disable plugin (%s)",exception.getMessage()));
        }
    }

    @EventHandler
    public void handleMcNativeShutdown(PluginDisableEvent event){
        if(event.getPlugin().getName().equalsIgnoreCase("McNative")){
            getLogger().info("(McNative-Loader) McNative is shutting down, thus plugins depends on McNative and is now also shutting down.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void shutdown() {
        Bukkit.getPluginManager().disablePlugin(this);
    }

    @Override
    public void bootstrap() {
        Bukkit.getPluginManager().enablePlugin(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void unload() {

        List<Plugin> plugins = (List<org.bukkit.plugin.Plugin>) ReflectionUtil.getFieldValue(Bukkit.getPluginManager(),"plugins");
        Map<String, Plugin> names = (Map<String, org.bukkit.plugin.Plugin>) ReflectionUtil.getFieldValue(Bukkit.getPluginManager(),"lookupNames");

        ClassLoader classLoader = getClass().getClassLoader();

        names.remove(this.getName());
        plugins.remove(this);

        if (classLoader instanceof URLClassLoader) {
            ReflectionUtil.changeFieldValue(classLoader,"plugin",null);
            ReflectionUtil.changeFieldValue(classLoader,"pluginInit",null);

            if(McNative.class.getClassLoader() == classLoader){
                getLogger().warning("Classes of "+getName()+" could not be unloaded, because this class loader is the host loader of McNative");
                return;
            }

            Map<String, Class<?>> classes = (Map<String, Class<?>>) ReflectionUtil.getFieldValue(classLoader,"classes");
            classes.clear();
            clearCachedClasses(classLoader);

            try {
                ((URLClassLoader) classLoader).close();
            } catch (IOException ignored) {}
        }

        System.gc();//Execute garbage collector
    }

    @SuppressWarnings("unchecked")
    private void clearCachedClasses(ClassLoader classLoader){
        Map<Pattern, PluginLoader> loaders = (Map<Pattern, PluginLoader>) ReflectionUtil.getFieldValue(Bukkit.getPluginManager(),"fileAssociations");
        for (Map.Entry<Pattern, PluginLoader> loader : loaders.entrySet()) {
            if(loader.getValue() instanceof JavaPluginLoader){
                Map<String, Class<?>> classes = (Map<String, Class<?>>) ReflectionUtil.getFieldValue(loader.getValue(),"classes");
                Iterators.removeSilent(classes.entrySet(), entry -> entry.getValue().getClassLoader().equals(classLoader));
            }
        }
    }
}
