package org.mcnative.runtime.api.protocol.packet.type.sound;

import org.mcnative.runtime.api.player.sound.SoundCategory;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketValidationException;

public class SoundEffectPacket implements MinecraftPacket {

    private String name;
    private SoundCategory category;
    private int positionX;
    private int positionY;
    private int positionZ;
    private float volume;
    public byte pitch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SoundCategory getCategory() {
        return category;
    }

    public void setCategory(SoundCategory category) {
        this.category = category;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(int positionZ) {
        this.positionZ = positionZ;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public byte getPitch() {
        return pitch;
    }

    public void setPitch(byte pitch) {
        this.pitch = pitch;
    }

    @Override
    public void validate() {
        if(name == null) throw new MinecraftPacketValidationException("Sound name cannot be null");
    }
}
