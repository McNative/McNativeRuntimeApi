package org.mcnative.runtime.api.protocol.packet;

import io.netty.buffer.ByteBuf;
import org.mcnative.runtime.api.connection.MinecraftConnection;

public interface MinecraftPacketCodec<T extends MinecraftPacket> {

    void read(T packet,MinecraftConnection connection, PacketDirection direction, ByteBuf buffer);

    void write(T packet,MinecraftConnection connection,PacketDirection direction, ByteBuf buffer);

}
