/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 26.04.20, 15:49
 * @web %web%
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

import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.logging.PretronicLogger;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.plugin.RuntimeEnvironment;
import net.pretronic.libraries.plugin.description.DefaultPluginDescription;
import net.pretronic.libraries.plugin.description.PluginDescription;
import net.pretronic.libraries.plugin.exception.PluginLoadException;
import net.pretronic.libraries.plugin.loader.DefaultPluginLoader;
import net.pretronic.libraries.plugin.loader.classloader.PluginClassLoader;
import net.pretronic.libraries.plugin.manager.PluginManager;
import org.mcnative.loader.bridged.bukkit.BukkitHelper;
import org.mcnative.loader.bridged.bungeecord.BungeeCordHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GuestPluginLoader extends DefaultPluginLoader {

    private final RuntimeEnvironment<?> environment;
    private final PlatformExecutor executor;
    private String pluginType;

    public GuestPluginLoader(PlatformExecutor executor,PluginManager pluginManager, RuntimeEnvironment<?> environment, PretronicLogger logger, PluginClassLoader classLoader, File location, PluginDescription description, boolean lifecycleLogging) {
        super(pluginManager, environment, logger, classLoader, location, description, lifecycleLogging);
        System.out.println("Environment: "+environment);
        this.environment = environment;
        this.executor = executor;
        this.pluginType = "mcnative";
    }

    @Override
    public boolean isEnabled() {
        return executor.isEnabled();
    }

    @Override
    public void bootstrap() {
        executor.bootstrap();
    }

    public void bootstrapInternal() {
        super.bootstrap();
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }

    public void shutdownInternal() {
        super.shutdown();
    }

    @Override
    public void unload() {
        if(isEnabled()) shutdown();
        super.unload();
        executor.unload();
    }

    @Override
    public Plugin<?> enable() {
        return super.enable();
    }

    @Override
    public PluginDescription loadDescription() {
        System.out.println(" ------ Loading Description ------ ");
        InputStream stream = getClassLoader().getResourceAsStream("mcnative.json");
        PluginDescription result;

        if(stream != null){
            result = DefaultPluginDescription.create(getPluginManager(), DocumentFileType.JSON.getReader().read(stream));
        }else{
            System.out.println("No mcnative.json");
            System.out.println("Environment: "+this.environment.getName());
            if(this.environment.getName().equals(EnvironmentNames.BUKKIT)){
                stream = getClassLoader().getResourceAsStream("plugin.yml");
                if(stream == null) throw new IllegalArgumentException("No plugin description found");
                this.pluginType = "bukkit";
                System.out.println("Found bukkit plugin");
                return BukkitHelper.readPluginDescription(stream);
            }else if(this.environment.getName().equals(EnvironmentNames.BUNGEECORD)){
                stream = getClassLoader().getResourceAsStream("plugin.yml");
                if(stream == null){
                    stream = getClassLoader().getResourceAsStream("bungee.yml");
                    if(stream == null) throw new IllegalArgumentException("No plugin description found");
                }
                this.pluginType = "bungeecord";
                return BungeeCordHelper.readPluginDescription(stream);
            }else throw new IllegalArgumentException("McNative loader is not supporting the "+this.environment.getName()+" environment");
        }
        try { stream.close(); } catch (IOException ignored) {}
        return result;
    }

    @Override
    public Plugin<?> construct() {
        if (this.isInstanceAvailable()) {
            throw new PluginLoadException("Plugin is already constructed.");
        }else if(this.pluginType.equals("bukkit")){
            return BukkitHelper.constructPlugin(this,getDescription());
        }else if(this.pluginType.equals("bungeecord")){
            return BungeeCordHelper.constructPlugin(this,getDescription());
        }else{
            return super.construct();
        }
    }
}
