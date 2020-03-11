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

package org.mcnative.common.plugin;

import net.pretronic.databasequery.api.Database;
import net.pretronic.databasequery.api.driver.DatabaseDriver;
import net.pretronic.libraries.plugin.Plugin;
import org.mcnative.common.McNative;
import org.mcnative.common.plugin.configuration.Configuration;
import org.mcnative.common.plugin.configuration.ConfigurationProvider;

import java.io.File;

public class MinecraftPlugin extends Plugin<McNative> {

    public File getDataFolder(){
        return getConfigurationProvider().getPluginDataFolder(this);
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

    public Database getDatabase(String name){
        return getConfigurationProvider().getDatabase(this,name);
    }

    public DatabaseDriver getDatabaseDriver(String name){
        return getConfigurationProvider().getDatabaseDriver(name);
    }

    private ConfigurationProvider getConfigurationProvider(){
        return getRuntime().getRegistry().getService(ConfigurationProvider.class);
    }
}

