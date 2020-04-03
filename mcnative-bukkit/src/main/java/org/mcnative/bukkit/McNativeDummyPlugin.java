/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 03.04.20, 11:45
 * @web %web%
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

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class McNativeDummyPlugin implements Plugin {

    private final PluginLoader pluginLoader;
    private final PluginDescriptionFile description;

    protected McNativeDummyPlugin(String version) {
        this.pluginLoader = (PluginLoader) getClass().getClassLoader();
        this.description = new PluginDescriptionFile("McNative",version,getClass().getName());
    }

    @Override
    public File getDataFolder() {
        return new File("plugins/McNative");
    }

    @Override
    public PluginDescriptionFile getDescription() {
        return description;
    }

    @Override
    public FileConfiguration getConfig() {
        throw new UnsupportedOperationException("McNative dummy plugin is not able to provide a configuration");
        //return new YamlConfiguration();
    }

    @Override
    public InputStream getResource(String s) {
        return null;//Unused
    }

    @Override
    public void saveConfig() {
        //unused
    }

    @Override
    public void saveDefaultConfig() {
        //Unused
    }

    @Override
    public void saveResource(String s, boolean b) {
        //Unused
    }

    @Override
    public void reloadConfig() {
        //Unused
    }

    @Override
    public PluginLoader getPluginLoader() {
        return pluginLoader;
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void onDisable() {
        //Unused
    }

    @Override
    public void onLoad() {
        //Unused
    }

    @Override
    public void onEnable() {
        //Unused
    }

    @Override
    public boolean isNaggable() {
        return false;
    }

    @Override
    public void setNaggable(boolean b) {
        //Unused
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String s, String s1) {
        throw new UnsupportedOperationException("McNative dummy plugin is not able to provide a world generator");
    }

    @Override
    public Logger getLogger() {
        return Bukkit.getLogger();
    }

    @Override
    public String getName() {
        return "McNative";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;      //Unused
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;      //Unused
    }
}
