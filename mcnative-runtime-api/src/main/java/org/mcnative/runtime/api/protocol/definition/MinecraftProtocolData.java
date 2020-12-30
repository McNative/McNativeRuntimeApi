package org.mcnative.runtime.api.protocol.definition;

import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketCodec;

public class MinecraftProtocolData {

    private final int packetId;
    private final Class<? extends MinecraftPacket> packetClass;
    private final MinecraftPacketCodec<?> codec;

    public MinecraftProtocolData(int packetId, Class<? extends MinecraftPacket> packetClass, MinecraftPacketCodec<?> codec) {
        this.packetId = packetId;
        this.packetClass = packetClass;
        this.codec = codec;
    }

    public int getPacketId() {
        return packetId;
    }

    public Class<? extends MinecraftPacket> getPacketClass() {
        return packetClass;
    }

    public MinecraftPacketCodec<?> getCodec() {
        return codec;
    }
}
