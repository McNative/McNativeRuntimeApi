package org.mcnative.runtime.api.protocol.packet.type;

import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;

public class CustomPayloadPacket implements MinecraftPacket {

    private String channel;
    private byte[] content;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
