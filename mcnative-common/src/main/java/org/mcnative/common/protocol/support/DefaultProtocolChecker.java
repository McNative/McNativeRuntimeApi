/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 22:00
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

package org.mcnative.common.protocol.support;

import org.mcnative.common.protocol.MinecraftProtocolVersion;

import java.util.Collection;
import java.util.function.Consumer;

public class DefaultProtocolChecker implements ProtocolCheck{

    private Collection<Entry> entries;

    @Override
    public ProtocolCheck execute(MinecraftProtocolVersion version, Runnable execute) {
        entries.add(new Entry(version,version,execute));
        return this;
    }

    @Override
    public ProtocolCheck min(MinecraftProtocolVersion version, Runnable execute) {
        return null;
    }

    @Override
    public ProtocolCheck min(MinecraftProtocolVersion version, MinecraftProtocolVersion version2, Runnable execute) {
        return null;
    }

    @Override
    public ProtocolCheck max(MinecraftProtocolVersion version, Runnable execute) {
        return null;
    }

    @Override
    public ProtocolCheck max(MinecraftProtocolVersion version, MinecraftProtocolVersion version2, Runnable execute) {
        return null;
    }

    @Override
    public ProtocolCheck range(MinecraftProtocolVersion min, MinecraftProtocolVersion max, Runnable execute) {
        return null;
    }

    @Override
    public ProtocolCheck orElse(Consumer<MinecraftProtocolVersion> execute) {
        return null;
    }

    @Override
    public void process(MinecraftProtocolVersion currentVersion) {

    }

    private class Entry {
        private MinecraftProtocolVersion min, max;
        private Runnable executor;

        public Entry(MinecraftProtocolVersion min, MinecraftProtocolVersion max, Runnable executor) {
            this.min = min;
            this.max = max;
            this.executor = executor;
        }
    }
}
