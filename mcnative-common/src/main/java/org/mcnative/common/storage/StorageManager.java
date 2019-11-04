/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 13.10.19, 20:09
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

package org.mcnative.common.storage;

import net.prematic.databasequery.api.Database;
import net.prematic.databasequery.api.DatabaseDriver;
import net.prematic.libraries.utility.Validate;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.map.caseintensive.CaseIntensiveHashMap;
import net.prematic.libraries.utility.map.caseintensive.CaseIntensiveMap;
import org.mcnative.common.McNative;

public class StorageManager {

    private final CaseIntensiveMap<DatabaseDriver> databaseDrivers;
    private final StorageConfig storageConfig;

    public StorageManager(StorageConfig storageConfig) {
        this.databaseDrivers = new CaseIntensiveHashMap<>();
        this.storageConfig = storageConfig;
    }

    public Database getDatabase(ObjectOwner plugin) {
        StorageConfig.Entry entry = this.storageConfig.getPluginStorageConfig(plugin.getName());
        DatabaseDriver driver = findDatabaseDriver(plugin.getName(), entry);
        Validate.notNull(driver, "No database driver found.");
        if(!driver.isConnected()) driver.connect();
        return driver.getDatabase(entry.getPlugins().get(plugin.getName()));
    }

    private DatabaseDriver findDatabaseDriver(String pluginName, StorageConfig.Entry entry) {
        if(this.databaseDrivers.containsKey(entry.getDriverAlias())) return this.databaseDrivers.get(entry.getDriverAlias());
        DatabaseDriver driver = entry.getDriverConfig().createDatabaseDriver(pluginName, McNative.getInstance().getLogger());
        this.databaseDrivers.put(entry.getDriverAlias(), driver);
        return driver;
    }
}