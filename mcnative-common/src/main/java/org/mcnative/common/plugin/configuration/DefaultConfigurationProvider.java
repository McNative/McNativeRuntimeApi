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

import net.pretronic.databasequery.api.Database;
import net.pretronic.databasequery.api.driver.DatabaseDriver;
import net.pretronic.databasequery.api.driver.DatabaseDriverFactory;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import net.pretronic.libraries.utility.interfaces.ShutdownAble;
import net.pretronic.libraries.utility.map.caseintensive.CaseIntensiveHashMap;
import net.pretronic.libraries.utility.map.caseintensive.CaseIntensiveMap;
import org.mcnative.common.McNative;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class DefaultConfigurationProvider implements ConfigurationProvider, ShutdownAble {

    private final CaseIntensiveMap<DatabaseDriver> databaseDrivers;
    private StorageConfig storageConfig;

    public DefaultConfigurationProvider() {
        this.databaseDrivers = new CaseIntensiveHashMap<>();
        this.storageConfig = new StorageConfig(this,getConfiguration(McNative.getInstance(), "storage"));
        storageConfig.load();
    }

    @Override
    public File getPluginDataFolder(ObjectOwner owner) {
        return new File("plugins/"+owner.getName()+"/");
    }

    @Override
    public Configuration getConfiguration(ObjectOwner owner, String name) {
        Objects.requireNonNull(owner,name);
        return new FileConfiguration(owner,name,new File("plugins/"+owner.getName()+"/"+name+"."+FileConfiguration.FILE_TYPE.getEnding()));
    }

    @Override
    public Database getDatabase(ObjectOwner owner, String name) {
        StorageConfig.DatabaseEntry entry = this.storageConfig.getDatabaseEntry(owner, name);
        if(entry != null) {
            DatabaseDriver databaseDriver = getDatabaseDriver(entry.getDriverName());
            if(!databaseDriver.isConnected()) {
                databaseDriver.connect();
            }
            return databaseDriver.getDatabase(entry.getDatabase());
        }
        throw new IllegalArgumentException("No database found for plugin " + owner.getName() + " and name " + name);
    }

    @Override
    public Database getDatabase(ObjectOwner owner, String name, boolean configCreate) {
        try {
            Database database = getDatabase(owner, name);
            if(database != null) return database;
        } catch (IllegalArgumentException ignored) {}
        if(configCreate) {
            this.storageConfig.addDatabaseEntry(new StorageConfig.DatabaseEntry(owner.getName(),
                    "default", owner.getName(), "default"));
            this.storageConfig.save();
            return getDatabase(owner, name);
        }
        throw new IllegalArgumentException("Can't create or get database for " + owner.getName() + " with name " + name);
    }

    @Override
    public DatabaseDriver getDatabaseDriver(String name) {
        Validate.notNull(name);
        if(!this.databaseDrivers.containsKey(name)) {
            DatabaseDriver driver = DatabaseDriverFactory.create("(Database-Driver) "+name, this.storageConfig.getDriverConfig(name),
                    McNative.getInstance().getLogger(), GeneralUtil.getDefaultExecutorService());
            this.databaseDrivers.put(name, driver);
        }
        return this.databaseDrivers.get(name);
    }

    @Override
    public void shutdown() {
        for (Map.Entry<String, DatabaseDriver> drivers : this.databaseDrivers.entrySet()) {
            drivers.getValue().disconnect();
        }
    }
}
