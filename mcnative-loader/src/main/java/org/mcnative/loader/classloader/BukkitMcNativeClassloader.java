/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 26.04.20, 19:23
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

package org.mcnative.loader.classloader;

import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class BukkitMcNativeClassloader extends URLClassLoader {

    private final Map<String,Class<?>> loadedClasses;

    public BukkitMcNativeClassloader() {
        super(new URL[]{});
        loadedClasses = findClasses();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> result = super.findClass(name);
        loadedClasses.put(name,result);
        return result;
    }

    @SuppressWarnings("unchecked")
    private static Map<String,Class<?>> findClasses(){
        Map<Pattern, PluginLoader> loaders = (Map<Pattern, PluginLoader>) ReflectionUtil.getFieldValue(Bukkit.getPluginManager(),"fileAssociations");
        for (Map.Entry<Pattern, PluginLoader> loader : loaders.entrySet()) {
            if(loader.getValue() instanceof JavaPluginLoader){
                Map<String, Class<?>> classes = (Map<String, Class<?>>) ReflectionUtil.getFieldValue(loader.getValue(),"classes");
                return classes;
            }
        }
        return new HashMap<>();
    }
}
