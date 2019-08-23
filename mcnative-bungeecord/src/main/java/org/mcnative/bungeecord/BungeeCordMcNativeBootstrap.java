/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 18:18
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

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.mcnative.common.McNative;

public class BungeeCordMcNativeBootstrap extends Plugin {

    @Override
    public void onLoad() {
        if(!McNative.isAvailable()) return;
        getLogger().info("McNative is starting, please wait...");

        ProxyServer proxy = ProxyServer.getInstance();

        BungeeCordService instance = new BungeeCordService();
        McNative.setInstance(instance);

        proxy.setConfigurationAdapter(new McNativeConfigurationAdapter(instance.getServers(),proxy.getConfigurationAdapter()));
        getLogger().info("McNative overridden configuration adapter.");
    }
}
