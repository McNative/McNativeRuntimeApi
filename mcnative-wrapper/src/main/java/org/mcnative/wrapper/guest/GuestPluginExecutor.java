/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 26.08.19, 19:25
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
import net.prematic.libraries.plugin.loader.PluginLoader;

public class GuestPluginExecutor {

    private final PluginLoader loader;

    public GuestPluginExecutor(Plugin instance) {
        this.loader = new GuestPluginLoader(instance);
    }

    public PluginLoader getLoader() {
        return loader;
    }

    public void loadGuestPlugin(){
        loader.construct();
        loader.initialize();
        loader.load();
    }

    public void enableGuestPlugin(){
        loader.bootstrap();
    }

    public void disableGuestPlugin(){
        loader.disable();
    }

}
