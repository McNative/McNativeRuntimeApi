/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 18:20
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
import org.mcnative.common.MinecraftPlatform;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.support.DefaultProtocolChecker;
import org.mcnative.common.protocol.support.ProtocolCheck;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

/*
@Todo In platform
    - Versions
 */
public class BungeeCordPlatform implements MinecraftPlatform {

    @Override
    public String getName() {
        return "BungeeCord";
    }

    @Override
    public String getVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return MinecraftProtocolVersion.JE_1_8;
    }

    @Override
    public Collection<MinecraftProtocolVersion> getJoinableProtocolVersions() {
        return Collections.singleton(MinecraftProtocolVersion.JE_1_8);
    }

    @Override
    public boolean isProxy() {
        return true;
    }

    @Override
    public boolean isService() {
        return false;
    }

    @Override
    public void check(Consumer<ProtocolCheck> checker) {
        ProtocolCheck check = new DefaultProtocolChecker();
        checker.accept(check);
        check.process(getProtocolVersion());
    }
}
