/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.11.19, 16:25
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

package org.mcnative.loader;

import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.logging.bridge.JdkPrematicLogger;
import net.prematic.libraries.plugin.RuntimeEnvironment;
import net.prematic.libraries.plugin.description.DefaultPluginDescription;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.loader.DefaultPluginLoader;
import net.prematic.libraries.plugin.loader.PluginLoader;
import net.prematic.libraries.plugin.loader.classloader.BridgedPluginClassLoader;
import org.mcnative.common.McNative;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public class GuestPluginExecutor {

    private final RuntimeEnvironment<McNative> environment;
    private final PluginLoader loader;

    public GuestPluginExecutor(File location, Logger logger, String runtimeName) {
        this.environment = new RuntimeEnvironment<>(runtimeName, McNative.getInstance());
        this.loader = setup(location,logger);
    }

    private PluginLoader setup(File location,Logger logger){
        InputStream stream = getClass().getClassLoader().getResourceAsStream("mcnative.json");
        if(stream != null){
            PluginDescription description = DefaultPluginDescription.create(
                    McNative.getInstance().getPluginManager()
                    , DocumentFileType.JSON.getReader().read(stream));
            return new DefaultPluginLoader(McNative.getInstance().getPluginManager(),environment
                    ,new JdkPrematicLogger(logger),new BridgedPluginClassLoader(getClass().getClassLoader())
                    ,location,description,false);
        }else{
            return null;
        }
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
