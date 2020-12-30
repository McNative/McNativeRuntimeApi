package org.mcnative.runtime.api.protocol.definition;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.connection.ConnectionState;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketCodec;
import org.mcnative.runtime.api.protocol.packet.PacketDirection;

import java.util.ArrayList;
import java.util.Collection;

public class MinecraftProtocolStateDefinition {

    private final ConnectionState state;
    private final Collection<MinecraftProtocolData> incoming;
    private final Collection<MinecraftProtocolData> outgoing;

    public MinecraftProtocolStateDefinition(ConnectionState state) {
        this.state = state;
        incoming = new ArrayList<>();
        outgoing = new ArrayList<>();
    }

    public ConnectionState getState() {
        return state;
    }

    public Collection<MinecraftProtocolData> getIncoming() {
        return incoming;
    }

    public Collection<MinecraftProtocolData> getOutgoing() {
        return outgoing;
    }

    public MinecraftProtocolData getProtocolData(PacketDirection direction, Class<?> packetClass) {
        Validate.notNull(direction);
        if(direction == PacketDirection.INCOMING) {
            MinecraftProtocolData entry = Iterators.findOne(this.incoming, entry0 -> entry0.getPacketClass().equals(packetClass));
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return entry;
        } else if(direction == PacketDirection.OUTGOING) {
            MinecraftProtocolData entry = Iterators.findOne(this.outgoing, entry0 -> entry0.getPacketClass().equals(packetClass));
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return entry;
        }
        throw new IllegalArgumentException("Invalid packet direction " + direction);
    }

    public MinecraftProtocolData getProtocolData(PacketDirection direction, int packetId) {
        Validate.notNull(direction);
        if(direction == PacketDirection.INCOMING) {
            MinecraftProtocolData entry = Iterators.findOne(this.incoming, entry0 -> entry0.getPacketId() == packetId);
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return entry;
        } else if(direction == PacketDirection.OUTGOING) {
            MinecraftProtocolData entry = Iterators.findOne(this.outgoing, entry0 -> entry0.getPacketId() == packetId);
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return entry;
        }
        throw new IllegalArgumentException("Invalid packet direction " + direction);
    }

    @SuppressWarnings("unchecked")
    public <T extends MinecraftPacket> MinecraftPacketCodec<T> getCodec(PacketDirection direction, Class<T> packetClass) {
        Validate.notNull(direction);
        if(direction == PacketDirection.INCOMING) {
            MinecraftProtocolData entry = Iterators.findOne(this.incoming, entry0 -> entry0.getPacketClass().equals(packetClass));
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return (MinecraftPacketCodec<T>) entry.getCodec();
        } else if(direction == PacketDirection.OUTGOING) {
            MinecraftProtocolData entry = Iterators.findOne(this.outgoing, entry0 -> entry0.getPacketClass().equals(packetClass));
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return (MinecraftPacketCodec<T>) entry.getCodec();
        }
        throw new IllegalArgumentException("Invalid packet direction " + direction);
    }

    @SuppressWarnings("unchecked")
    public <T extends MinecraftPacket> MinecraftPacketCodec<T> getCodec(PacketDirection direction, int packetId) {
        Validate.notNull(direction);
        if(direction == PacketDirection.INCOMING) {
            MinecraftProtocolData entry = Iterators.findOne(this.incoming, entry0 -> entry0.getPacketId() == packetId);
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return (MinecraftPacketCodec<T>) entry.getCodec();
        } else if(direction == PacketDirection.OUTGOING) {
            MinecraftProtocolData entry = Iterators.findOne(this.outgoing, entry0 -> entry0.getPacketId() == packetId);
            if(entry == null) throw new UnsupportedOperationException("packet not supported");
            return (MinecraftPacketCodec<T>) entry.getCodec();
        }
        throw new IllegalArgumentException("Invalid packet direction " + direction);
    }

    public <T extends MinecraftPacket> void registerIncoming(Class<T> packetClass,int packetID, MinecraftPacketCodec<T> codec){
        this.incoming.add(new MinecraftProtocolData(packetID,packetClass,codec));
    }

    public <T extends MinecraftPacket> void registerOutgoing(Class<T> packetClass,int packetID, MinecraftPacketCodec<T> codec){
        this.outgoing.add(new MinecraftProtocolData(packetID,packetClass,codec));
    }

}
