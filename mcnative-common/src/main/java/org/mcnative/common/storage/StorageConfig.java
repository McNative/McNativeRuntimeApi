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

import net.prematic.databasequery.api.config.DatabaseDriverConfig;
import net.prematic.databasequery.sql.SqlDatabaseDriverConfig;
import net.prematic.databasequery.sql.h2.H2PortableDatabaseDriver;
import net.prematic.databasequery.sql.mysql.MySqlDatabaseDriver;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.DocumentEntry;
import net.prematic.libraries.document.WrappedDocument;
import net.prematic.libraries.utility.Iterators;
import org.mcnative.common.McNative;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class StorageConfig extends WrappedDocument {

    private static final String GLOBAL = "global";

    private final Collection<Entry> entries;

    public StorageConfig(Document original) {
        super(original);
        this.entries = new ArrayList<>();
        load();
    }

    public Collection<Entry> getEntries() {
        return entries;
    }

    public Entry getPluginStorageConfig(String pluginName) {
        Supplier<Entry> supplier = () -> Iterators.findOne(getEntries(), entry -> entry.plugins.containsKey(GLOBAL));
        return Iterators.findOneOrWhenNull(this.entries, entry -> entry.plugins.containsKey(pluginName), supplier);
    }

    public void createDefault() {
        Document driverConfigs = Document.newDocument();
        driverConfigs.add("global", new SqlDatabaseDriverConfig(H2PortableDatabaseDriver.class).setHost("./data/"));
        driverConfigs.add("mySqlExample", new SqlDatabaseDriverConfig(MySqlDatabaseDriver.class)
                .setHost("127.0.0.1").setPort(3306)
                .setUsername("root").setPassword("masked")
                .setMultipleDatabaseConnectionsAble(true)
                .getDataSourceConfig().setClassName("com.zaxxer.hikari.HikariDataSource")
                .out());
        add("drivers", driverConfigs);

        Document databases = Document.newDocument();
        databases.add("Example", Document.newDocument().add("driver", "mySqlExample").add("database", "Example"));
        add("databases", databases);
    }

    private void load() {
        Document driverConfigs = getDocument("drivers");
        for (DocumentEntry child : driverConfigs) {
            Document driverConfigDocument = child.toDocument();
            String driverName = driverConfigDocument.getString("driverName");
            Class<? extends DatabaseDriverConfig> driverConfigClass = DatabaseDriverConfig.getDriverConfigNameByDriverName(driverName);
            try {
                DatabaseDriverConfig driverConfig = driverConfigClass.getConstructor(Document.class).newInstance(driverConfigDocument);
                entries.add(new Entry(child.getKey(), driverConfig));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                McNative.getInstance().getLogger().error("Can't initialize config[{}] for driver[{}]", driverConfigClass, driverName);
            }
        }
        for(DocumentEntry child : getDocument("databases")) {
            Document pluginDriverSettings = child.toDocument();
            String pluginName = child.getKey();
            String driverAlias = pluginDriverSettings.getString("driver");
            Entry entry = Iterators.findOne(this.entries, entry1 -> entry1.driverAlias.equalsIgnoreCase(driverAlias));
            if(entry == null) {
                McNative.getInstance().getLogger().error("Can't find driver[{}] for plugin {}. Please check, if you have configured a driver under {}.", driverAlias, pluginName, driverAlias);
                continue;
            }
            String databaseName = pluginDriverSettings.getString("database");
            entry.addPlugin(pluginName, databaseName == null ? pluginName : databaseName);
        }
    }

    static class Entry {

        private final String driverAlias;
        private final DatabaseDriverConfig driverConfig;
        //PluginName, DatabaseName
        private final Map<String, String> plugins;

        public Entry(String driverAlias, DatabaseDriverConfig driverConfig) {
            this.driverAlias = driverAlias;
            this.driverConfig = driverConfig;
            this.plugins = new HashMap<>();
        }

        public String getDriverAlias() {
            return driverAlias;
        }

        public DatabaseDriverConfig getDriverConfig() {
            return driverConfig;
        }

        public Map<String, String> getPlugins() {
            return plugins;
        }

        public Entry addPlugin(String pluginName, String databaseName) {
            this.plugins.put(pluginName, databaseName);
            return this;
        }
    }
}