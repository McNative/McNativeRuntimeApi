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

package org.mcnative.wrapper.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcnative.wrapper.guest.GuestPluginLoader;
import org.mcnative.wrapper.loader.McNativeLoader;

import java.util.logging.Level;

public class BukkitMcNativePluginBootstrap extends JavaPlugin {

    /*
            -> CONSTRUCT
            -> INITIALISATION
            Load / LOAD

            Enable / BOOTSTRAP

                    ReloadEvent / RELOAD

            Disable / SHUTDOWN
            -> UNLOAD
     */


    private GuestPluginLoader loader;



    @Override
    public void onLoad() {

        try{
            McNativeLoader.install("BungeeCord");

            System.out.println("Enable real plugin.");

            /*

            CONSTRUCT
            INITIALISATION
            LOAD

             */

        }catch (Exception exception){
            getLogger().log(Level.SEVERE,String.format("Could not enable %s v%s by %s"
                    ,getDescription().getName()
                    ,getDescription().getVersion()
                    ,getDescription().getAuthors())
                    ,exception);
        }
    }

    @Override
    public void onEnable() {
        System.out.println("Enable");

        /*

        BOOTSTRAP

         */
    }

    @Override
    public void onDisable() {
        System.out.println("Disable");

        /*

        SHUTDOWN
        UNLOAD

         */
    }
}
