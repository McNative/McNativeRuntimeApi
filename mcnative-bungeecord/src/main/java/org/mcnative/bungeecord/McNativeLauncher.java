/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.08.19, 19:22
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
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.plugin.McNativeBungeePluginManager;
import org.mcnative.common.McNative;

import java.util.logging.Logger;

public class McNativeLauncher {

    public static void launchMcNative(){
        Logger logger = ProxyServer.getInstance().getLogger();
        if(!McNative.isAvailable()) return;
        logger.info("McNative is starting, please wait...");

        ProxyServer proxy = ProxyServer.getInstance();

        BungeeCordService instance = new BungeeCordService();
        McNative.setInstance(instance);

        proxy.setConfigurationAdapter(new McNativeConfigurationAdapter(instance.getServers(),proxy.getConfigurationAdapter()));
        logger.info("McNative has overwritten the configuration adapter.");

        //Override plugin manager
        ReflectionUtil.changeFieldValue(proxy,"pluginManager",new McNativeBungeePluginManager(proxy));
        logger.info("McNative initialised plugin manager.");

        //initialise netty pipeline hook

        //override registry

        //initialise connection handlers
    }
}
