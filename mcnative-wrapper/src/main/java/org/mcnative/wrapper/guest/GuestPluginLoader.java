/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 26.08.19, 19:34
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

package org.mcnative.wrapper.guest;


import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.lifecycle.LifecycleState;
import net.prematic.libraries.plugin.loader.PluginLoader;
import net.prematic.libraries.plugin.manager.PluginManager;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

public class GuestPluginLoader implements PluginLoader {

    private final Plugin instance;

    public GuestPluginLoader(Plugin instance) {
        this.instance = instance;
    }

    private boolean enabled;

    @Override
    public File getLocation() {
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        return null;
    }

    @Override
    public PluginDescription getDescription() {
        return null;
    }

    @Override
    public Collection<Class<?>> getLoadedClasses() {
        return Collections.emptyList();
    }

    @Override
    public Class<?> getLoadedClass(String s) {
        return null;
    }

    @Override
    public boolean isInstanceAvailable() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void executeLifeCycleState(String s) {

    }

    @Override
    public void executeLifeCycleState(String s, LifecycleState lifecycleState) {

    }

    @Override
    public Plugin getInstance() {
        return null;
    }

    @Override
    public Plugin enable() {
        return instance;
    }

    @Override
    public void disable() {

    }

    @Override
    public Plugin construct() {
        return instance;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void load() {

    }

    @Override
    public void bootstrap() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void unload() {

    }
}
