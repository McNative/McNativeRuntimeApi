/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 18:22
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

package org.mcnative.runtime.api.plugin.configuration;

import net.pretronic.databasequery.api.Database;
import net.pretronic.databasequery.api.driver.DatabaseDriver;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.Setting;

import java.io.File;
import java.util.Collection;

public interface ConfigurationProvider {

    File getPluginDataFolder(ObjectOwner owner);

    Configuration getConfiguration(ObjectOwner owner, String name);

    Collection<Setting> getSettings(String owner);

    Setting getSetting(String owner, String name);

    Setting createSetting(String owner, String key, Object value);

    void updateSetting(String owner,Setting setting);

    void deleteSetting(String owner,Setting setting);

    void deleteSetting(String owner,String key);


    default Database getDatabase(ObjectOwner owner){
        return getDatabase(owner,"default");
    }

    Database getDatabase(ObjectOwner owner,String name);

    default Database getDatabase(ObjectOwner owner, boolean configCreate) {
        return getDatabase(owner, "default", configCreate);
    }

    Database getDatabase(ObjectOwner owner, String name, boolean configCreate);

    DatabaseDriver getDatabaseDriver(String name);

    Collection<String> getDatabaseTypes();

    Collection<String> getDatabaseTypes(Plugin<?> plugin);
}
