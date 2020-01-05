/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.10.19, 22:50
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

package org.mcnative.common.protocol.packet;

import net.prematic.libraries.utility.reflect.UnsafeInstanceCreator;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

public class PacketIdentifier {

    private final Class<? extends MinecraftPacket> packetClass;
    private final PacketCondition[] conditions;

    public PacketIdentifier(Class<? extends MinecraftPacket> packetClass, PacketCondition[] conditions) {
        this.packetClass = packetClass;
        this.conditions = conditions;
    }

    public Class<? extends MinecraftPacket> getPacketClass() {
        return packetClass;
    }

    public PacketCondition[] getConditions() {
        return conditions;
    }

    public PacketCondition getCondition(PacketDirection direction, ConnectionState state){
        for (PacketCondition condition : conditions) if(condition.getDirection().equals(direction) && condition.getState().equals(state)) return condition;
        return null;
    }

    public int getId(PacketDirection direction, ConnectionState state, MinecraftProtocolVersion version){
        PacketCondition condition = getCondition(direction, state);
        if(condition == null) throw new UnsupportedOperationException("This packet is not supported in state "+state+" -> "+direction);
        int id = -1;
        for (IdMapping mapping : condition.getMappings()) if(version.isNewerOrSame(mapping.version)) id = mapping.id;
        if(id == -1) throw new IllegalArgumentException("This packet is not supported for version "+version.getEdition().getName()+" "+version.getName());
        return id;
    }

    public MinecraftPacket newPacketInstance(){
        return UnsafeInstanceCreator.newInstance(packetClass);
    }


    public static class PacketCondition {

        private final PacketDirection direction;
        private final ConnectionState state;
        private final IdMapping[] mappings;

        public PacketCondition(PacketDirection direction, ConnectionState state, IdMapping[] mappings) {
            this.direction = direction;
            this.state = state;
            this.mappings = mappings;
        }

        public PacketDirection getDirection() {
            return direction;
        }

        public ConnectionState getState() {
            return state;
        }

        public IdMapping[] getMappings() {
            return mappings;
        }
    }

    public static class IdMapping {

        private final MinecraftProtocolVersion version;
        private final int id;

        public IdMapping(MinecraftProtocolVersion version, int id) {
            this.version = version;
            this.id = id;
        }

        public MinecraftProtocolVersion getVersion() {
            return version;
        }

        public int getId() {
            return id;
        }

    }
}
