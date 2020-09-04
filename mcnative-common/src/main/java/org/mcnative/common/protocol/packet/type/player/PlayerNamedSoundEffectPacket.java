/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 01.09.20, 19:21
 * @web %web%
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

package org.mcnative.common.protocol.packet.type.player;

import io.netty.buffer.ByteBuf;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;


public class PlayerNamedSoundEffectPacket implements MinecraftPacket {

    public static final PacketIdentifier IDENTIFIER = newIdentifier(PlayerNamedSoundEffectPacket.class, on(PacketDirection.OUTGOING,
            map(MinecraftProtocolVersion.JE_1_8,0x29),
            map(MinecraftProtocolVersion.JE_1_9,0x19),
            map(MinecraftProtocolVersion.JE_1_13,0x1A),
            map(MinecraftProtocolVersion.JE_1_14,0x19),
            map(MinecraftProtocolVersion.JE_1_15,0x1A),
            map(MinecraftProtocolVersion.JE_1_16,0x19),
            map(MinecraftProtocolVersion.JE_1_16_2,0x18)));


    private final Sound sound;
    private final SoundCategory soundCategory;
    private final int positionX;
    private final int positionY;
    private final int positionZ;
    private final float volume;
    private final float pitch;

    public PlayerNamedSoundEffectPacket(Sound sound, SoundCategory soundCategory, int positionX, int positionY, int positionZ, float volume, float pitch) {
        Validate.notNull(sound, positionX, positionY, positionZ, volume, pitch);
        this.sound = sound;
        this.soundCategory = soundCategory == null ? SoundCategory.MASTER : soundCategory;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }

    @Override
    public void write(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeString(buffer, this.sound.getName());
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_9)) {
            MinecraftProtocolUtil.writeVarInt(buffer, this.soundCategory.getId());
        }
        buffer.writeInt(this.positionX);
        buffer.writeInt(this.positionY);
        buffer.writeInt(this.positionZ);
        buffer.writeFloat(this.volume);
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_10)) {
            buffer.writeFloat(this.pitch);
        } else {
            buffer.writeByte((int) this.pitch);
        }
    }
}
