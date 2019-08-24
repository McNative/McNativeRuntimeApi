/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.08.19, 15:16
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

package org.mcnative.bungeecord;

import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import org.mcnative.bungeecord.server.McNativeBungeeServerMap;
import org.mcnative.proxy.server.MinecraftServer;

import java.util.Collection;
import java.util.Map;

public class McNativeConfigurationAdapter implements ConfigurationAdapter {

    private final ConfigurationAdapter original;
    private final McNativeBungeeServerMap serverMap;

    public McNativeConfigurationAdapter(Collection<MinecraftServer> servers,ConfigurationAdapter original) {
        this.original = original;
        this.serverMap = new McNativeBungeeServerMap(servers);
        this.serverMap.putAll(original.getServers());
        original.getServers().clear();
    }

    @Override
    public void load() {
        original.load();
    }

    @Override
    public int getInt(String path, int def) {
        return original.getInt(path,def);
    }

    @Override
    public String getString(String path, String def) {
        return original.getString(path, def);
    }

    @Override
    public boolean getBoolean(String path, boolean def) {
        return original.getBoolean(path, def);
    }

    @Override
    public Collection<?> getList(String path, Collection<?> def) {
        return original.getList(path, def);
    }

    @Override
    public Map<String, ServerInfo> getServers() {
        return this.serverMap;
    }

    @Override
    public Collection<ListenerInfo> getListeners() {
        return original.getListeners();
    }

    @Override
    public Collection<String> getGroups(String player) {
        return null;
    }

    @Override
    public Collection<String> getPermissions(String group) {
        return null;
    }
}
