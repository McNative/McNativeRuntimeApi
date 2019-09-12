/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.09.19, 19:46
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

package org.mcnative.bungeecord.plugin;

import net.prematic.libraries.logging.PrematicLogger;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.lifecycle.LifecycleState;
import net.prematic.libraries.plugin.loader.PluginLoader;
import net.prematic.libraries.plugin.manager.PluginManager;

import java.io.File;
import java.util.Collection;
import java.util.UUID;
import java.util.function.BiConsumer;

public class BungeeMcNativePluginManager implements PluginManager {

    @Override
    public PrematicLogger getLogger() {
        return null;
    }

    @Override
    public Collection<Plugin> getPlugins() {
        return null;
    }

    @Override
    public Plugin getPlugin(String s) {
        return null;
    }

    @Override
    public Plugin getPlugin(UUID uuid) {
        return null;
    }

    @Override
    public boolean isPluginEnabled(String s) {
        return false;
    }

    @Override
    public <T> T getService(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> void registerService(Class<T> aClass, T t) {

    }

    @Override
    public <T> boolean isServiceAvailable(Class<T> aClass) {
        return false;
    }

    @Override
    public Collection<PluginLoader> getLoaders() {
        return null;
    }

    @Override
    public PluginLoader createPluginLoader(File file) {
        return null;
    }

    @Override
    public PluginLoader createPluginLoader(PluginDescription pluginDescription) {
        return null;
    }

    @Override
    public PluginDescription detectPluginDescription(File file) {
        return null;
    }

    @Override
    public Collection<PluginDescription> detectPluginDescriptions(File file) {
        return null;
    }

    @Override
    public void setLifecycleStateListener(String s, BiConsumer<Plugin, LifecycleState> biConsumer) {

    }

    @Override
    public void executeLifecycleStateListener(String s, LifecycleState lifecycleState, Plugin plugin) {

    }

    @Override
    public void executeLifecycleStateListener(String s, LifecycleState lifecycleState, Collection<Plugin> collection) {

    }

    @Override
    public Collection<Plugin> enablePlugins(File file) {
        return null;
    }

    @Override
    public void disablePlugins() {

    }

    @Override
    public void shutdown() {

    }
}
