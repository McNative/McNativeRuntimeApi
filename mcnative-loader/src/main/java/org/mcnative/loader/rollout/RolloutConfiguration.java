/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.07.20, 12:24
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

package org.mcnative.loader.rollout;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RolloutConfiguration {

    public static File FILE = new File("plugins/McNative/update.yml");
    public static Yaml YAML;

    static {
        Constructor constructor = new Constructor(RolloutConfiguration.class);
        TypeDescription configDesc = new TypeDescription(RolloutConfiguration.class);
        configDesc.putMapPropertyType("profiles",String.class,RolloutProfile.class);//In old yaml versions new method not available
        configDesc.putListPropertyType("plugins",PluginEntry.class);
        constructor.addTypeDescription(configDesc);
        YAML = new Yaml(constructor);
    }

    private final Map<String,RolloutProfile> profiles;
    private final Collection<PluginEntry> plugins;

    public RolloutConfiguration() {
        profiles = new HashMap<>();
        plugins = new ArrayList<>();
    }

    public Map<String,RolloutProfile> getProfiles() {
        return profiles;
    }

    public Collection<PluginEntry> getPlugins() {
        return plugins;
    }

    public RolloutProfile getProfile(String name){
        for (PluginEntry plugin : plugins) {
            if(plugin.pluginName.equalsIgnoreCase(name)){
                RolloutProfile profile = profiles.get(plugin.profile);
                if(profile != null) return profile;
                break;
            }
        }
        return RolloutProfile.DEFAULT;
    }

    public static RolloutConfiguration load() throws Exception{
        if(FILE.exists()) return YAML.load(new FileInputStream(FILE));
        else return new RolloutConfiguration();
    }

    public static void save(RolloutConfiguration configuration) throws Exception{
        try{
            if(!FILE.exists()) FILE.createNewFile();
            YAML.dump(configuration,new FileWriter(FILE));
        }catch (Exception ignored){
            ignored.printStackTrace();
        }
    }

    public static class PluginEntry {

        private String pluginName;
        private String profile;

        public String getPluginName() {
            return pluginName;
        }

        public String getProfile() {
            return profile;
        }

        public void setPluginName(String pluginName) {
            this.pluginName = pluginName;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }
    }
}
