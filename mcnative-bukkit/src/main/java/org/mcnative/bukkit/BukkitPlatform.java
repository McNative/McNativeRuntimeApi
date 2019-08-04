/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:46
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

package org.mcnative.bukkit;

import net.prematic.mcnative.common.MinecraftPlatform;
import net.prematic.mcnative.common.protocol.MinecraftProtocolVersion;
import net.prematic.mcnative.common.protocol.support.ProtocolCheck;
import org.bukkit.Bukkit;

import java.util.Collection;

public class BukkitPlatform implements MinecraftPlatform {

    public String getName() {
        return "Bukkit";
    }

    public String getVersion() {
        return Bukkit.getBukkitVersion();
    }

    public MinecraftProtocolVersion getProtocolVersion() {
        return null;
    }

    public Collection<MinecraftProtocolVersion> getJoinableProtocolVersions() {
        return null;
    }

    public boolean isProxy() {
        return false;
    }

    public ProtocolCheck check() {
        return null;
    }
}
