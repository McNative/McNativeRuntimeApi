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
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.proxy.McNativeProxyConfiguration;
import org.mcnative.proxy.ProxyService;

import java.util.Collection;
import java.util.Map;

public class McNativeConfigurationAdapter implements ConfigurationAdapter {

    private final ConfigurationAdapter original;
    private final BungeeCordServerMap serverMap;

    public McNativeConfigurationAdapter(BungeeCordServerMap serverMap, ConfigurationAdapter original) {
        this.original = original;
        this.serverMap = serverMap;
    }

    @Override
    public void load() {
        original.load();
        this.serverMap.clear();
        this.serverMap.putAll(original.getServers());
        this.original.getServers().clear();

        McNativeProxyConfiguration.SERVER_SERVERS.forEach((name, config) -> {
            MinecraftServer server = ProxyService.getInstance().registerServer(name,config.getAddress());
            if(config.getPermission() != null) server.setPermission(config.getPermission());
            if(config.getType() != null) server.setType(config.getType());
        });
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
        if(path.equalsIgnoreCase("log_pings")) return false;//Disables ping requests in BungeeCord (Better for Support)
        else return original.getBoolean(path, def);
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
        return original.getGroups(player);//@Todo Implement permission and groups
    }

    @Override
    public Collection<String> getPermissions(String group) {
        return original.getPermissions(group);
    }
}