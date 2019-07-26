/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.07.19 16:12
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

@ProtocolSupport(max= MinecraftProtocolVersion.V1_12_2)
public class LegacyData {

    private final String name;
    private final int id;
    private final byte subId;

    public LegacyData(String name, int id, byte subId) {
        this.name = name;
        this.id = id;
        this.subId = subId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public byte getSubId() {
        return subId;
    }
}
