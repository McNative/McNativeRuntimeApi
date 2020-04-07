/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 18.10.19, 00:11
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

import org.bukkit.Bukkit;
import org.mcnative.bukkit.utils.BukkitReflectionUtil;
import org.mcnative.common.MinecraftPlatform;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.support.DefaultProtocolChecker;
import org.mcnative.common.protocol.support.ProtocolCheck;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

public class BukkitPlatform implements MinecraftPlatform {

    private final MinecraftProtocolVersion protocolVersion = BukkitReflectionUtil.getProtocolVersionByServerVersion();
    private final File latestLogLocation;

    public BukkitPlatform() {
        this.latestLogLocation = new File("logs/latest.log");
    }

    @Override
    public String getName() {
        return "Bukkit";
    }

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public Collection<MinecraftProtocolVersion> getJoinableProtocolVersions() {
        return Collections.singleton(protocolVersion);//@Todo Check plugins like via version
    }

    @Override
    public boolean isProxy() {
        return false;
    }

    @Override
    public boolean isService() {
        return true;
    }

    @Override
    public File getLatestLogLocation() {
        return this.latestLogLocation;
    }

    @Override
    public void check(Consumer<ProtocolCheck> checker) {
        ProtocolCheck check = new DefaultProtocolChecker();
        checker.accept(check);
        check.process(getProtocolVersion());
    }
}
