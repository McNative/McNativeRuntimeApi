package org.mcnative.runtime.api.protocol.packet.type.sound;

import com.sun.tools.jdi.Packet;
import org.mcnative.runtime.api.player.sound.SoundCategory;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketValidationException;

public class MinecraftStopSoundPacket implements MinecraftPacket {

    private Action action;
    private SoundCategory category;
    private String soundName;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public SoundCategory getCategory() {
        return category;
    }

    public void setCategory(SoundCategory category) {
        this.category = category;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    @Override
    public void validate() {
        if(action == null) throw new MinecraftPacketValidationException("Action cannot be null");
    }

    public enum Action {

        ALL(),
        CATEGORY(),
        SOUND(),
        BOTH();

    }
}
