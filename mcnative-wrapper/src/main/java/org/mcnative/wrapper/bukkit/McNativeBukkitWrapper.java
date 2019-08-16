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

import org.mcnative.wrapper.loader.McNativeLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class McNativeBukkitWrapper extends JavaPlugin {

    @Override
    public void onLoad() {
        try {
            if(!McNativeLoader.isAvailable()){
                File location = McNativeLoader.install("bukkit");
                if(location != null){
                    Plugin plugin = Bukkit.getPluginManager().loadPlugin(location);
                    Bukkit.getPluginManager().enablePlugin(plugin);
                }else{
                    getPluginLoader().disablePlugin(this);
                    return;
                }
            }
        } catch (Exception exception) {
            //Print McNative load exception
            getPluginLoader().disablePlugin(this);
            return;
        }


        //execute plugin load
    }

    @Override
    public void onDisable() {
        System.out.println("Disable");
    }

    @Override
    public void onEnable() {
        System.out.println("Enable");
    }
}
