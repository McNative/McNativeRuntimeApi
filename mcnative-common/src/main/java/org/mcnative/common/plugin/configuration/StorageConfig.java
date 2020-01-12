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

import net.prematic.databasequery.api.driver.DatabaseDriverFactory;
import net.prematic.databasequery.api.driver.config.DatabaseDriverConfig;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import net.pretronic.databasequery.sql.dialect.Dialect;
import net.pretronic.databasequery.sql.driver.config.SQLDatabaseDriverConfigBuilder;
import org.mcnative.common.McNative;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;

public class StorageConfig {

    private final Collection<DatabaseDriverEntry> databaseDriverEntries;
    private final Collection<DatabaseEntry> databaseEntries;
    private Configuration configuration;

    public StorageConfig(ConfigurationProvider configurationProvider,Configuration configuration) {
        this.configuration = configurationProvider.getConfiguration(McNative.getInstance(), "storage");
        this.databaseDriverEntries = new ArrayList<>();
        this.databaseEntries = new ArrayList<>();
        this.configuration = configuration;
    }

    public DatabaseDriverEntry getDriverEntry(String name) {
        DatabaseDriverEntry driverEntry = Iterators.findOne(this.databaseDriverEntries, entry -> entry.name.equalsIgnoreCase(name));
        if(driverEntry == null) {
            driverEntry = Iterators.findOne(this.databaseDriverEntries, entry -> entry.name.equalsIgnoreCase("default"));
            if(driverEntry == null) {
                throw new IllegalArgumentException("No database driver for name " + name + " found");
            }
        }
        return driverEntry;
    }

    public DatabaseEntry getDatabaseEntry(ObjectOwner plugin, String name) {
        DatabaseEntry databaseEntry = Iterators.findOne(this.databaseEntries, entry -> entry.pluginName.equalsIgnoreCase(plugin.getName()) && entry.name.equalsIgnoreCase(name));
        if(databaseEntry == null) {
            databaseEntry = Iterators.findOne(this.databaseEntries, entry -> entry.pluginName.equalsIgnoreCase(plugin.getName()) && entry.name.equalsIgnoreCase("default"));
            if(databaseEntry == null) {
                throw new IllegalArgumentException("No database for name " + name + " found");
            }
        }
        return databaseEntry;
    }

    public void createDefault() {
        this.databaseDriverEntries.clear();
        this.databaseEntries.clear();

        this.databaseDriverEntries.add(new DatabaseDriverEntry("MySQL",
                new SQLDatabaseDriverConfigBuilder()
                        .setAddress(new InetSocketAddress("127.0.0.1", 3306))
                        .setDialect(Dialect.MYSQL)
                        .setDataSourceClassName("com.zaxxer.hikari.HikariDataSource")
                        .setUsername("McNative")
                        .setPassword("masked").build()));

        this.databaseEntries.add(new DatabaseEntry("McNative", "Default", "McNative", "MySql"));
        save();
    }

    public StorageConfig load() {
        Collection<DatabaseDriverEntry> driverEntries = new ArrayList<>();
        configuration.getDocument("drivers").forEach(entry -> {
            DatabaseDriverConfig<?> driverConfig = DatabaseDriverFactory.create(entry.toDocument());
            driverEntries.add(new DatabaseDriverEntry(entry.getKey(), driverConfig));
        });
        Collection<DatabaseEntry> databaseEntries = new ArrayList<>();
        configuration.getDocument("databases").forEach(entry -> databaseEntries.add(entry.toDocument().getAsObject(DatabaseEntry.class)));
        this.databaseDriverEntries.addAll(driverEntries);
        this.databaseEntries.addAll(databaseEntries);
        return this;
    }

    public StorageConfig save() {
        configuration.clear();
        configuration.add("drivers", this.databaseDriverEntries);
        configuration.add("databases", this.databaseEntries);
        configuration.save();
        return this;
    }

    static class DatabaseDriverEntry {

        final String name;
        final DatabaseDriverConfig<?> driverConfig;

        DatabaseDriverEntry(String name, DatabaseDriverConfig<?> driverConfig) {
            this.name = name;
            this.driverConfig = driverConfig;
        }

        public String getName() {
            return name;
        }

        public DatabaseDriverConfig<?> getDriverConfig() {
            return driverConfig;
        }
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
    }
}
