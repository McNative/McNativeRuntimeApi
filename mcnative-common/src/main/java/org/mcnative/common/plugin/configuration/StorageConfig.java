/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 09.01.20, 16:11
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

import net.prematic.databasequery.api.driver.config.DatabaseDriverConfig;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.prematic.libraries.utility.map.caseintensive.CaseIntensiveHashMap;
import net.prematic.libraries.utility.reflect.TypeReference;
import net.pretronic.databasequery.sql.dialect.Dialect;
import net.pretronic.databasequery.sql.driver.config.SQLDatabaseDriverConfigBuilder;
import org.mcnative.common.McNative;

import java.io.File;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class StorageConfig {

    private final static Type DRIVER_MAP_TYPE = new TypeReference<Map<String, DatabaseDriverConfig<?>>>(){}.getType();
    private final static Type DATABASE_COLLECTION_TYPE = new TypeReference<Collection<DatabaseEntry>>(){}.getType();

    static {
        DatabaseDriverConfig.registerDocumentAdapter();
    }

    private final Map<String, DatabaseDriverConfig<?>> databaseDrivers;
    private final Collection<DatabaseEntry> databaseEntries;
    private Configuration configuration;

    public StorageConfig(ConfigurationProvider configurationProvider,Configuration configuration) {
        this.configuration = configurationProvider.getConfiguration(McNative.getInstance(), "storage");
        this.databaseDrivers = new CaseIntensiveHashMap<>();
        this.databaseEntries = new ArrayList<>();
        this.configuration = configuration;
    }

    public DatabaseDriverConfig<?> getDriverConfig(String name) {
        DatabaseDriverConfig<?> config = this.databaseDrivers.get(name);
        if(config == null)  throw new IllegalArgumentException("No database driver for name " + name + " found");
        return config;
    }

    public DatabaseEntry getDatabaseEntry(ObjectOwner plugin, String name) {
        DatabaseEntry databaseEntry = Iterators.findOne(this.databaseEntries, entry -> entry.pluginName.equalsIgnoreCase(plugin.getName()) && entry.name.equalsIgnoreCase(name));
        if(databaseEntry == null) {
            databaseEntry = Iterators.findOne(this.databaseEntries, entry -> entry.pluginName.equalsIgnoreCase(plugin.getName()) && entry.name.equalsIgnoreCase("default"));
            if(databaseEntry == null) {
                throw new IllegalArgumentException("Database "+name+" for " + plugin.getName() + " not found");
            }
        }
        return databaseEntry;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void createDefault() {
        this.databaseDrivers.clear();
        this.databaseEntries.clear();

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.databaseDrivers.put("default", new SQLDatabaseDriverConfigBuilder()
                .setName("Default")
                .setDialect(Dialect.H2_PORTABLE)
                .setLocation(new File("plugins/McNative/databases/"))
                .build());

        this.databaseDrivers.put("MySQL", new SQLDatabaseDriverConfigBuilder()
                .setName("MySQL")
                .setDialect(Dialect.MYSQL)
                .setAddress(new InetSocketAddress("127.0.0.1", 3306))
                .setUsername("McNative")
                .setPassword("masked")
                .build());

        this.databaseEntries.add(new DatabaseEntry("McNative", "Default", "McNative", "default"));
        save();
    }

    public StorageConfig load() {
        configuration.load();
        if(configuration.isFirstCreation()){
            createDefault();
        }else{
            this.databaseDrivers.putAll(configuration.getObject("drivers",DRIVER_MAP_TYPE));
            this.databaseEntries.addAll(configuration.getObject("databases",DATABASE_COLLECTION_TYPE));
        }
        return this;
    }

    public StorageConfig save() {
        configuration.clear();
        configuration.add("drivers", this.databaseDrivers);
        configuration.add("databases", this.databaseEntries);
        configuration.save();
        return this;
    }

    static class DatabaseEntry {

        final String pluginName;
        final String name;
        final String database;
        final String driverName;

        DatabaseEntry(String pluginName, String name, String database, String driverName) {
            this.pluginName = pluginName;
            this.name = name;
            this.database = database;
            this.driverName = driverName;
        }

        public String getPluginName() {
            return pluginName;
        }

        public String getName() {
            return name;
        }

        public String getDatabase() {
            return database;
        }

        public String getDriverName() {
            return driverName;
        }

        @Override
        public String toString() {
            return "DatabaseEntry{" +
                    "pluginName='" + pluginName + '\'' +
                    ", name='" + name + '\'' +
                    ", database='" + database + '\'' +
                    ", driverName='" + driverName + '\'' +
                    '}';
        }
    }
}
