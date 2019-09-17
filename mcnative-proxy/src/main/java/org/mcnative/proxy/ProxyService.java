/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 11:43
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

package org.mcnative.proxy;

import org.mcnative.common.McNative;
import org.mcnative.proxy.server.ConnectHandler;
import org.mcnative.proxy.server.FallbackHandler;
import org.mcnative.proxy.server.MinecraftServer;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;

public interface ProxyService extends McNative<ProxiedPlayer> {


    Collection<MinecraftServer> getServers();

    MinecraftServer getServer(String name);

    MinecraftServer getServer(InetSocketAddress address);

    MinecraftServer registerServer(String name, InetSocketAddress address);

    MinecraftServer registerServer(MinecraftServer server);


    void unregisterServer(String name);

    void unregisterServer(InetSocketAddress address);

    void unregisterServer(MinecraftServer server);


    ConnectHandler getConnectHandler();

    void setConnectHandler(ConnectHandler handler);


    FallbackHandler getFallbackHandler();

    void setFallbackHandler(FallbackHandler handler);

    static ProxyService getInstance(){
        return (ProxyService) McNative.getInstance();
    }
}
