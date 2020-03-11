/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.10.19, 11:26
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

import net.pretronic.libraries.utility.Iterators;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.protocol.Endpoint;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

import java.util.*;
import java.util.function.Function;

public class DefaultPacketManager implements PacketManager {

    private static Function<Class<?>, List<MinecraftPacketListener>> COMPUTE_FUNCTION = class0 -> new ArrayList<>();

    private final Collection<PacketIdentifier> identifiers;
    private final Map<Class<?>, List<MinecraftPacketListener>> upstreamOutgoingListeners;
    private final Map<Class<?>, List<MinecraftPacketListener>> upstreamIncomingListeners;
    private final Map<Class<?>, List<MinecraftPacketListener>> downstreamOutgoingListeners;
    private final Map<Class<?>, List<MinecraftPacketListener>> downstreamIncomingListeners;

    public DefaultPacketManager() {
        this.identifiers = new ArrayList<>();

        this.upstreamOutgoingListeners = new HashMap<>();
        this.upstreamIncomingListeners = new HashMap<>();
        this.downstreamOutgoingListeners = new HashMap<>();
        this.downstreamIncomingListeners = new HashMap<>();

        PacketManager.registerDefaultPackets(this);
    }

    @Override
    public PacketIdentifier getPacketIdentifier(Class<?> packetClass) {
        PacketIdentifier identifier = Iterators.findOne(identifiers, identifier0 -> identifier0.getPacketClass().equals(packetClass));
        if(identifier == null) throw new IllegalArgumentException("No packet identifier for "+packetClass+" found.");
        return identifier;
    }

    @Override
    public PacketIdentifier getPacketIdentifier(ConnectionState state, PacketDirection direction, MinecraftProtocolVersion version, int packetId) {
        for (PacketIdentifier identifier : identifiers) {
            PacketIdentifier.PacketCondition condition = identifier.getCondition(direction, state);
            if(condition != null){
                for (int i = condition.getMappings().length - 1; i >= 0; i--) {
                    PacketIdentifier.IdMapping mapping = condition.getMappings()[i];
                    if(version.isNewerOrSame(mapping.getVersion()) && mapping.getId() == packetId) return identifier;
                }
                for (PacketIdentifier.IdMapping mapping : condition.getMappings()) {
                    if(mapping.getVersion().equals(version) && mapping.getId() == packetId) return identifier;
                }
            }
        }
        return null;
    }

    @Override
    public void registerPacket(PacketIdentifier identifier) {
        identifiers.add(identifier);
    }

    @Override
    public void unregisterPacket(PacketIdentifier identifier) {
        identifiers.remove(identifier);
    }

    @Override
    public List<MinecraftPacketListener> getPacketListeners(Endpoint endpoint, PacketDirection direction, Class<?> packetClass) {
        return getListenerMap(endpoint, direction).get(packetClass);
    }

    @Override
    public void registerPacketListener(Endpoint endpoint, PacketDirection direction, Class<?> packetClass, MinecraftPacketListener listener) {
        Map<Class<?>, List<MinecraftPacketListener>> listeners =  getListenerMap(endpoint, direction);
        listeners.computeIfAbsent(packetClass,COMPUTE_FUNCTION).add(listener);
    }

    @Override
    public void unregisterPacketListener(MinecraftPacketListener listener) {
        throw new UnsupportedOperationException("@Todo implement");
    }

    private Map<Class<?>, List<MinecraftPacketListener>> getListenerMap(Endpoint endpoint, PacketDirection direction){
        if(endpoint == Endpoint.UPSTREAM){
            return direction == PacketDirection.OUTGOING ? upstreamOutgoingListeners : upstreamIncomingListeners;
        }else{
            return direction == PacketDirection.OUTGOING ? downstreamOutgoingListeners : downstreamIncomingListeners;
        }
    }
}
