package org.mcnative.runtime.api.protocol.definition;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.connection.ConnectionState;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketCodec;
import org.mcnative.runtime.api.protocol.packet.PacketDirection;

import java.util.ArrayList;
import java.util.Collection;

public class MinecraftProtocolDefinition {

    private final MinecraftProtocolVersion version;
    private final Collection<MinecraftProtocolStateDefinition> stateDefinitions;

    public MinecraftProtocolDefinition(MinecraftProtocolVersion version) {
        this.version = version;
        this.stateDefinitions = new ArrayList<>();
    }

    public MinecraftProtocolVersion getVersion() {
        return this.version;
    }

    public MinecraftProtocolStateDefinition getDefinition(ConnectionState state){
        Validate.notNull(state);
        MinecraftProtocolStateDefinition definition = Iterators.findOne(stateDefinitions, definition1 -> definition1.getState() == state);
        if(definition == null){
            definition = new MinecraftProtocolStateDefinition(state);
            stateDefinitions.add(definition);
        }
        return definition;
    }

    public <T extends MinecraftPacket> MinecraftPacketCodec<T> getCodec(ConnectionState state, PacketDirection direction, Class<T> packetClass) {
        Validate.notNull(state, direction, packetClass);
        MinecraftProtocolStateDefinition definition = getDefinition(state);
        return definition.getCodec(direction, packetClass);
    }

    @SuppressWarnings("unchecked")
    public <T extends MinecraftPacket> MinecraftPacketCodec<T> getCodec(ConnectionState state, PacketDirection direction, T packet) {
        Validate.notNull(packet);
        return (MinecraftPacketCodec<T>) getCodec(state, direction, packet.getClass());
    }

    public MinecraftPacketCodec<?> getCodec(ConnectionState state, PacketDirection direction, int packetId){
        Validate.notNull(state, direction);
        MinecraftProtocolStateDefinition definition = getDefinition(state);
        return definition.getCodec(direction, packetId);
    }

    public MinecraftProtocolStateDefinition registerState(ConnectionState state){
        MinecraftProtocolStateDefinition definition = getDefinition(state);
        if(definition == null){
            definition = new MinecraftProtocolStateDefinition(state);
            stateDefinitions.add(definition);
        }
        return definition;
    }

}
