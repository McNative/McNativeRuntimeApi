/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.07.19 22:26
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

package net.prematic.mcnative.service.material;

import net.prematic.mcnative.common.protocol.MinecraftProtocolVersion;
import net.prematic.mcnative.common.protocol.ProtocolSupport;

public class Material {

    private final String name, textType;
    private final LegacyData legacyData;

    public Material(String name, String textType, LegacyData legacyData) {
        this.name = name;
        this.textType = textType;
        this.legacyData = legacyData;
    }

    public String getName() {
        return name;
    }

    public String getTextType() {
        return textType;
    }

    @ProtocolSupport(max=MinecraftProtocolVersion.V1_12_2)
    public LegacyData getLegacyData() {
        return legacyData;
    }
}
