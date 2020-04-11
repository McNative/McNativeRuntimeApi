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
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcnative.loader.GuestPluginExecutor;
import org.mcnative.loader.McNativeLoader;

import java.util.logging.Level;

public class BukkitMcNativePluginBootstrap extends JavaPlugin implements Listener {

    private static final String ENVIRONMENT_NAME = "Bukkit";
    private GuestPluginExecutor executor;

    @Override
    public void onLoad() {
        try{
            if(!McNativeLoader.install(getLogger(),ENVIRONMENT_NAME)) return;
            this.executor = new GuestPluginExecutor(getFile(),getLogger(),ENVIRONMENT_NAME);

            if(!this.executor.install() || !this.executor.installDependencies()){
                this.executor = null;
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
}
