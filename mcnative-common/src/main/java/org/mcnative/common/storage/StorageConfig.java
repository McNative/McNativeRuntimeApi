/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 13.10.19, 22:04
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

import net.prematic.databasequery.api.DatabaseDriver;
import net.prematic.databasequery.api.config.DatabaseDriverConfig;
import net.prematic.databasequery.sql.SqlDatabaseDriverConfig;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.interfaces.ObjectOwner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class StorageConfig {

    private final Collection<DatabaseDriverEntry> databaseDriverEntries;
    private final Collection<DatabaseEntry> databaseEntries;

    public StorageConfig() {
        this.databaseDriverEntries = new ArrayList<>();
        this.databaseEntries = new ArrayList<>();
    }

    public DatabaseDriverEntry getDriverEntry(String name) {
        return Iterators.findOneOrWhenNull(this.databaseDriverEntries,
                entry -> entry.name.equalsIgnoreCase(name),
                () -> { throw new IllegalArgumentException("No database driver found.");});
    }

    public DatabaseEntry getDatabaseEntry(ObjectOwner plugin, String name) {
        return Iterators.findOneOrWhenNull(this.databaseEntries,
                entry -> entry.pluginName.equalsIgnoreCase(plugin.getName()) && entry.name.equalsIgnoreCase(name),
                ()-> { throw new IllegalArgumentException("No database driver found.");});
    }

    public Document createDefault() {
        this.databaseDriverEntries.clear();
        this.databaseEntries.clear();

        this.databaseDriverEntries.add(new DatabaseDriverEntry("MySql",
                new SqlDatabaseDriverConfig("net.prematic.databasequery.sql.mysql.MySqlDatabaseDriver")
                        .setHost("127.0.0.1").setPort(3307).setUsername("McNative").setPassword("masked")));

        this.databaseEntries.add(new DatabaseEntry("McNative", "Default", "McNative", "MySql"));
        return save();
    }

    public StorageConfig load(Document config) {
        Collection<DatabaseDriverEntry> driverEntries = new ArrayList<>();
        config.getDocument("drivers").forEach(entry -> {
            String name = entry.getKey();
            String driverName = entry.toDocument().getString("driverName");
            Class<? extends DatabaseDriverConfig> driverConfigClass = DatabaseDriverConfig.getDriverConfigNameByDriverName(driverName);
            try {
                DatabaseDriverConfig driverConfig = driverConfigClass.getConstructor(Document.class).newInstance(entry.toDocument());
                driverEntries.add(new DatabaseDriverEntry(name, driverConfig));
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
                throw new IllegalArgumentException("Can't load driver for driver with name: " + name + ".");
            }
        });
        Collection<DatabaseEntry> databaseEntries = new ArrayList<>();
        config.getDocument("databases").forEach(entry -> databaseEntries.add(entry.toDocument().getAsObject(DatabaseEntry.class)));
        this.databaseDriverEntries.addAll(driverEntries);
        this.databaseEntries.addAll(databaseEntries);
        return this;
    }

    public Document save() {
        Document config = Document.newDocument();
        config.add("drivers", this.databaseDriverEntries);
        config.add("databases", this.databaseEntries);
        return config;
    }

    public StorageConfig addDatabaseDriver(String name, DatabaseDriverConfig driverConfig) {
        this.databaseDriverEntries.add(new DatabaseDriverEntry(name, driverConfig));
        return this;
    }

    public StorageConfig addDatabase(ObjectOwner plugin, String name, String database, Class<? extends DatabaseDriver> driverClass) {
        return addDatabase(plugin, name, database, driverClass.getName());
    }

    public StorageConfig addDatabase(ObjectOwner plugin, String name, String database, String driverName) {
        this.databaseEntries.add(new DatabaseEntry(plugin.getName(), name, database, driverName));
        return this;
    }

    public static class DatabaseDriverEntry {

        final String name;
        final DatabaseDriverConfig driverConfig;

        DatabaseDriverEntry(String name, DatabaseDriverConfig driverConfig) {
            this.name = name;
            this.driverConfig = driverConfig;
        }

        public String getName() {
            return name;
        }

        public DatabaseDriverConfig getDriverConfig() {
            return driverConfig;
        }
    }

    public static class DatabaseEntry {

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