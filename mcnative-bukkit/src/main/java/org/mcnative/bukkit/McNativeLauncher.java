/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.01.20, 20:22
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

package org.mcnative.bukkit;

import net.prematic.libraries.utility.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcnative.bukkit.event.McNativeBridgeEventHandler;
import org.mcnative.bukkit.player.BukkitPlayerManager;
import org.mcnative.bukkit.player.connection.BukkitChannelInjector;
import org.mcnative.bukkit.plugin.BukkitPluginManager;
import org.mcnative.bukkit.plugin.command.BukkitCommandManager;
import org.mcnative.bukkit.plugin.event.BukkitEventBus;
import org.mcnative.common.McNative;

import java.io.File;
import java.util.logging.Logger;

public class McNativeLauncher {

    private static Plugin PLUGIN;

    public static Plugin getPlugin() {
        return PLUGIN;
    }


    public static void launchMcNative(){
        launchMcNativeInternal(null);//@Todo create fake plugin
    }

    public static void launchMcNativeInternal(Plugin plugin){
        if(McNative.isAvailable()) return;
        PLUGIN = plugin;
        Logger logger = Bukkit.getLogger();
        logger.info(McNative.CONSOLE_PREFIX+"McNative is starting, please wait...");

        BukkitEventBus eventBus = new BukkitEventBus(GeneralUtil.getDefaultExecutorService(),getPlugin());//@Todo add better executor
        BukkitCommandManager commandManager = new BukkitCommandManager();
        BukkitPluginManager pluginManager = new BukkitPluginManager(Bukkit.getServicesManager());
        BukkitService localService = new BukkitService(commandManager,eventBus);
        BukkitPlayerManager playerManager = new BukkitPlayerManager();

        BukkitMcNative instance = new BukkitMcNative(pluginManager,playerManager,localService,null);
        McNative.setInstance(instance);
        instance.registerDefaultProviders();

        BukkitChannelInjector injector = new BukkitChannelInjector();

        new McNativeBridgeEventHandler(injector,eventBus,playerManager);

        injector.injectChannelInitializer();

        //Plugin

        //Command

        logger.info(McNative.CONSOLE_PREFIX+"McNative successfully started.");
    }

    private static void createDummyPlugin(){
        //@Todo add version
        PluginDescriptionFile description = new PluginDescriptionFile("McNative","1.0.0",McNativeLauncher.class.getName());
        McNativeDummyPlugin plugin = new McNativeDummyPlugin(description);
    }

    private static class McNativeDummyPlugin extends JavaPlugin{

        public McNativeDummyPlugin(PluginDescriptionFile description) {
            super(null, description, new File(""),null);
        }
    }

}
