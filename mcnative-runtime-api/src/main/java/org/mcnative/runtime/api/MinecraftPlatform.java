/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.runtime.api;

import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

import java.io.File;
import java.util.Collection;

/**
 * McNative is running on different platforms and servers like Bukkit and BungeeCord. this interface is used
 * to get information about the actual platform, type and supported Minecraft versions.
 */
public interface MinecraftPlatform {

    /**
     * Get the name of the current platform (e.g. Bukkit).
     *
     * @return The name of the platform
     */
    String getName();

    /**
     * Get the version of the platform.
     *
     * @return The version of the platform
     */
    String getVersion();

    /**
     * Get the Minecraft protocol version on which the server is originally operating.
     *
     * @return The base version
     */
    MinecraftProtocolVersion getProtocolVersion();

    /**
     * Get a list of all joinable Minecraft versions.
     *
     * @return A list with all joinable versions
     */
    Collection<MinecraftProtocolVersion> getJoinableProtocolVersions();

    /**
     * Check if a protocol version is supported by the server.
     *
     * @param protocolVersion The version to check
     * @return True if this version is supported
     */
    default boolean canJoin(MinecraftProtocolVersion protocolVersion){
        return getJoinableProtocolVersions().contains(protocolVersion);
    }

    /**
     * Get the minimum supported protocol version.
     *
     * @return The minimum version
     */
    default MinecraftProtocolVersion getMinVersion(){
        MinecraftProtocolVersion result = getProtocolVersion();
        for (MinecraftProtocolVersion version : getJoinableProtocolVersions()) {
            if(result.isNewer(version)) result = version;
        }
        return result;
    }

    /**
     * Get the maximum supported protocol version.
     *
     * @return The maximum version
     */
    default MinecraftProtocolVersion getMaxVersion(){
        MinecraftProtocolVersion result = getProtocolVersion();
        for (MinecraftProtocolVersion version : getJoinableProtocolVersions()) {
            if(result.isOlder(version)) result = version;
        }
        return result;
    }

    /**
     * Check if the current platform operates as a proxy.
     *
     * @return True if the platform is a Proxy server
     */
    boolean isProxy();

    /**
     * Check if the current platform operates as a normal Minecraft server.
     * @return True if the platform is a Minecraft server
     */
    boolean isService();

    /**
     * Get the location of the log files.
     * @return The file location
     */
    File getLatestLogLocation();

}
