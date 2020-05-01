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

package org.mcnative.common;

import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.support.ProtocolCheck;

import java.io.File;
import java.util.Collection;
import java.util.function.Consumer;

public interface MinecraftPlatform {

    String getName();

    String getVersion();

    MinecraftProtocolVersion getProtocolVersion();

    Collection<MinecraftProtocolVersion> getJoinableProtocolVersions();

    default boolean canJoin(MinecraftProtocolVersion protocolVersion){
        return getJoinableProtocolVersions().contains(protocolVersion);
    }

    default MinecraftProtocolVersion getMinVersion(){
        MinecraftProtocolVersion result = getProtocolVersion();
        for (MinecraftProtocolVersion version : getJoinableProtocolVersions()) {
            if(result.isNewer(version)) result = version;
        }
        return result;
    }

    default MinecraftProtocolVersion getMaxVersion(){
        MinecraftProtocolVersion result = getProtocolVersion();
        for (MinecraftProtocolVersion version : getJoinableProtocolVersions()) {
            if(result.isOlder(version)) result = version;
        }
        return result;
    }

    boolean isProxy();

    boolean isService();

    File getLatestLogLocation();

    void check(Consumer<ProtocolCheck> checker);

}
