/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.runtime.api.plugin;

import net.pretronic.databasequery.api.Database;
import net.pretronic.databasequery.api.driver.DatabaseDriver;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.plugin.service.ServiceRegistry;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.LocalService;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.Setting;
import org.mcnative.runtime.api.network.Network;
import org.mcnative.runtime.api.player.PlayerSetting;
import org.mcnative.runtime.api.plugin.configuration.Configuration;
import org.mcnative.runtime.api.plugin.configuration.ConfigurationProvider;

import java.io.File;
import java.util.Collection;
import java.util.Set;

public class MinecraftPlugin extends Plugin<McNative> {

    public File getDataFolder(){
        return getConfigurationProvider().getPluginDataFolder(this);
    }

    public ServiceRegistry getRegistry(){
        return getRuntime().getRegistry();
    }

    public Network getNetwork(){
        return getRuntime().getNetwork();
    }

    public LocalService getLocal(){
        return getRuntime().getLocal();
    }

    public Configuration getConfiguration(){
        return getConfiguration("config");
    }

    public Configuration getConfiguration(String name){
        return getConfigurationProvider().getConfiguration(this,name);
    }

    public Database getDatabase(){
        return getConfigurationProvider().getDatabase(this);
    }

    public Database getDatabaseOrCreate(){
        return getConfigurationProvider().getDatabase(this,true);
    }

    public Database getDatabase(String name){
        return getConfigurationProvider().getDatabase(this,name);
    }

    public Database getDatabaseOrCreate(String name){
        return getConfigurationProvider().getDatabase(this,name,true);
    }

    public DatabaseDriver getDatabaseDriver(String name){
        return getConfigurationProvider().getDatabaseDriver(name);
    }

    public Collection<Setting> getSettings(){
        return getConfigurationProvider().getSettings(this.getName());
    }

    public Setting getSetting(String key){
        return getConfigurationProvider().getSetting(this.getName(),key);
    }

    public Setting setSetting(String key, Object value){
        Setting setting = getSetting(key);
        if(setting == null){
            setting = getConfigurationProvider().createSetting(this.getName(),key,value);
        }else{
            setting.setValue(value);
            getConfigurationProvider().updateSetting(setting);
        }
        return setting;
    }

    public boolean hasSetting(String key){
        return getSetting(key) != null;
    }

    public boolean hasSetting(String key,Object value){
        Setting settings = getSetting(key);
        return settings != null && settings.equalsValue(value);
    }

    public void deleteSetting(String key){
        getConfigurationProvider().deleteSetting(this.getName(),key);
    }

    private ConfigurationProvider getConfigurationProvider(){
        return getRuntime().getRegistry().getService(ConfigurationProvider.class);
    }
}

