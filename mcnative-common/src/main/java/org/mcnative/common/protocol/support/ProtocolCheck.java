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

package org.mcnative.common.protocol.support;

import org.mcnative.common.protocol.MinecraftProtocolVersion;

import java.util.function.Consumer;

public interface ProtocolCheck {

    ProtocolCheck execute(MinecraftProtocolVersion version, Runnable execute);


    ProtocolCheck min(MinecraftProtocolVersion version, Runnable execute);

    ProtocolCheck min(MinecraftProtocolVersion version,MinecraftProtocolVersion version2, Runnable execute);


    ProtocolCheck max(MinecraftProtocolVersion version, Runnable execute);

    ProtocolCheck max(MinecraftProtocolVersion version,MinecraftProtocolVersion version2, Runnable execute);


    ProtocolCheck range(MinecraftProtocolVersion min,MinecraftProtocolVersion max, Runnable execute);


    ProtocolCheck orElse(Consumer<MinecraftProtocolVersion> execute);

    void process(MinecraftProtocolVersion currentVersion);

}
