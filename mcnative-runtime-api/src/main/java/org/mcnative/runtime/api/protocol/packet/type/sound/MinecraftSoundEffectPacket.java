package org.mcnative.runtime.api.protocol.packet.type.sound;

import org.mcnative.runtime.api.player.sound.SoundCategory;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketValidationException;

public class MinecraftSoundEffectPacket implements MinecraftPacket {

    private String soundName;
    private SoundCategory category;
    private int positionX;
    private int positionY;
    private int positionZ;
    private float volume;
    public float pitch;

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String name) {
        this.soundName = name;
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

    public float getPitch() {
        return pitch;
    }

    public void setPitch(byte pitch) {
        this.pitch = pitch;
    }

    @Override
    public void validate() {
        if(soundName == null) throw new MinecraftPacketValidationException("Sound name cannot be null");
    }
}
