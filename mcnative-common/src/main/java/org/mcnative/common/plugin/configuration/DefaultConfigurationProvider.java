/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.12.19, 19:35
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

package org.mcnative.common.plugin.configuration;

import net.prematic.databasequery.api.Database;
import net.prematic.databasequery.api.DatabaseDriver;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.map.caseintensive.CaseIntensiveHashMap;
import net.prematic.libraries.utility.map.caseintensive.CaseIntensiveMap;
import org.mcnative.common.McNative;
import org.mcnative.common.storage.StorageConfig;

import java.io.File;

//@Todo implement database stuff
public class DefaultConfigurationProvider implements ConfigurationProvider {

    private final StorageConfig storageConfig;
    private final CaseIntensiveMap<DatabaseDriver> databaseDrivers;

    public DefaultConfigurationProvider(StorageConfig storageConfig) {
        this.storageConfig = storageConfig;
        this.databaseDrivers = new CaseIntensiveHashMap<>();
    }

    @Override
    public File getPluginDataFolder(ObjectOwner owner) {
        return null;
    }

    @Override
    public Configuration getConfiguration(ObjectOwner owner, String name) {
        return null;
    }

    @Override
    public Database getDatabase(ObjectOwner owner, String name) {
        StorageConfig.DatabaseEntry entry = this.storageConfig.getDatabaseEntry(owner, name);
        DatabaseDriver databaseDriver = getDatabaseDriver(entry.getDriverName());
        return databaseDriver.getDatabase(entry.getDatabase());
    }

    @Override
    public DatabaseDriver getDatabaseDriver(String name) {
        if(!this.databaseDrivers.containsKey(name)) {
            this.databaseDrivers.put(name, this.storageConfig.getDriverEntry(name).getDriverConfig().createDatabaseDriver(name, McNative.getInstance().getLogger()));
        }
        return this.databaseDrivers.get(name);
    }
}
