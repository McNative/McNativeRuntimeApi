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

package org.mcnative.runtime.api.protocol.packet;

import net.pretronic.libraries.utility.map.Pair;
import net.pretronic.libraries.utility.reflect.UnsafeInstanceCreator;
import org.mcnative.runtime.api.connection.ConnectionState;
import org.mcnative.runtime.api.protocol.MinecraftEdition;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

/**
 * A Minecraft package has different @{@link MinecraftEdition} and
 * {@link MinecraftProtocolVersion}, this class is used to register and hold the different specifications.
 */
public class PacketRegistration {

    private final Class<? extends MinecraftPacket> packetClass;
    private final PacketCondition[] conditions;

    public PacketRegistration(Class<? extends MinecraftPacket> packetClass, PacketCondition[] conditions) {
        this.packetClass = packetClass;
        this.conditions = conditions;
    }

    /**
     * The implementation of the packet class.
     *
     * @return The packet class
     */
    public Class<? extends MinecraftPacket> getPacketClass() {
        return packetClass;
    }

    /**
     * A list of the different specifications.
     *
     * @return A list
     */
    public PacketCondition[] getConditions() {
        return conditions;
    }

    public PacketCondition getCondition(PacketDirection direction, ConnectionState state){
        for (PacketCondition condition : conditions) if(condition.getDirection().equals(direction) && condition.getState().equals(state)) return condition;
        return null;
    }

    public Pair<Integer,MinecraftPacketCodec> getCodecData(PacketDirection direction, ConnectionState state, MinecraftProtocolVersion version){
        PacketCondition condition = getCondition(direction, state);
        if(condition == null) throw new UnsupportedOperationException("This packet is not supported in state "+state+" -> "+direction);
        MinecraftPacketCodec<?> codec = null;
        int id = -1;
        for (IdMapping mapping : condition.getMappings()){
            if(mapping.getVersion().isNewer(version)) break;
            if(mapping.getCodec() != null) {
                codec = mapping.getCodec();
            }
            id = mapping.getId();
        }
        if(codec == null || id == -1) throw new IllegalArgumentException("This packet is not supported for version "+version.getEdition().getName()+" "+version.getName());
        return new Pair<>(id, codec);
    }

    public MinecraftPacketCodec<?> getCodec(PacketDirection direction,ConnectionState state, MinecraftProtocolVersion version){
        PacketCondition condition = getCondition(direction, state);
        if(condition == null) throw new UnsupportedOperationException("This packet is not supported in state "+state+" -> "+direction);
        MinecraftPacketCodec<?> codec = null;
        for (IdMapping mapping : condition.getMappings()){
            if(mapping.getVersion().isNewer(version)) break;
            if(mapping.getCodec() != null) {
                codec = mapping.getCodec();
            }

        }
        if(codec == null) throw new IllegalArgumentException("This packet is not supported for version "+version.getEdition().getName()+" "+version.getName());
        return codec;
    }

    public int getId(PacketDirection direction, ConnectionState state, MinecraftProtocolVersion version){
        PacketCondition condition = getCondition(direction, state);
        if(condition == null) throw new UnsupportedOperationException("This packet is not supported in state "+state+" -> "+direction);
        int id = -1;
        for (IdMapping mapping : condition.getMappings()){
            if(version.isNewerOrSame(mapping.version)){
                id = mapping.id;
            }
        }
        if(id == -1) throw new IllegalArgumentException("This packet is not supported for version "+version.getEdition().getName()+" "+version.getName());
        return id;
    }

    public MinecraftPacket newPacketInstance(){
        return UnsafeInstanceCreator.newInstance(packetClass);
    }

    public static PacketRegistration create(Class<? extends MinecraftPacket> packetClass, PacketRegistration.PacketCondition... conditions){
        return new PacketRegistration(packetClass, conditions);
    }

    public static PacketRegistration.IdMapping map(MinecraftProtocolVersion version, int identifier){
        return new PacketRegistration.IdMapping(version,identifier,null);
    }

    public static PacketRegistration.IdMapping map(MinecraftProtocolVersion version, int identifier,MinecraftPacketCodec<?> codec){
        return new PacketRegistration.IdMapping(version,identifier,codec);
    }

    public static PacketRegistration.PacketCondition on(PacketDirection direction, PacketRegistration.IdMapping... mappings){
        return new PacketRegistration.PacketCondition(direction,ConnectionState.GAME,mappings);
    }

    public static PacketRegistration.PacketCondition on(PacketDirection direction, ConnectionState state, PacketRegistration.IdMapping... mappings){
        return new PacketRegistration.PacketCondition(direction,state,mappings);
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
        private final MinecraftPacketCodec<?> codec;

        public IdMapping(MinecraftProtocolVersion version, int id, MinecraftPacketCodec<?> codec) {
            this.version = version;
            this.id = id;
            this.codec = codec;
        }

        public MinecraftProtocolVersion getVersion() {
            return version;
        }

        public int getId() {
            return id;
        }

        public MinecraftPacketCodec<?> getCodec() {
            return codec;
        }
    }

}
