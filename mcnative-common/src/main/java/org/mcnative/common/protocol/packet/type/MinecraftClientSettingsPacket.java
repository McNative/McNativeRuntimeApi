/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.01.20, 19:11
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

package org.mcnative.common.protocol.packet.type;

import io.netty.buffer.ByteBuf;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.player.PlayerSettings;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;

import java.util.Locale;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class MinecraftClientSettingsPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(MinecraftDisconnectPacket.class
            ,on(PacketDirection.INCOMING
                    ,map(MinecraftProtocolVersion.JE_1_7,0x15)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x04)
                    ,map(MinecraftProtocolVersion.JE_1_12,0x05)
                    ,map(MinecraftProtocolVersion.JE_1_12_1,0x04)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x05)));

    private String languageTag;
    private PlayerSettings settings;

    public static PacketIdentifier getIDENTIFIER() {
        return IDENTIFIER;
    }

    public String getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    public PlayerSettings getSettings() {
        return settings;
    }

    public void setSettings(PlayerSettings settings) {
        this.settings = settings;
        this.languageTag = settings.getLocale().toLanguageTag();
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void read(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        languageTag = MinecraftProtocolUtil.readString(buffer);
        byte viewDistance = buffer.readByte();
        int chatMode;
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_9)){
            chatMode = MinecraftProtocolUtil.readVarInt(buffer);
        }else{
            chatMode = buffer.readByte();
        }
        boolean chatColor = buffer.readBoolean();
        byte skinParts = buffer.readByte();
        int mainHand = 0;
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_9)) mainHand = MinecraftProtocolUtil.readVarInt(buffer);
        settings = new PlayerSettings(Locale.forLanguageTag(languageTag)
                ,viewDistance
                ,PlayerSettings.ChatMode.of(chatMode)
                ,chatColor
                ,new PlayerSettings.SkinParts(skinParts)
                ,PlayerSettings.MainHand.of(mainHand));
    }

    @Override
    public void write(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeString(buffer,languageTag);
        buffer.writeByte(settings.getViewDistance());
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_9)){
            MinecraftProtocolUtil.writeVarInt(buffer,settings.getChatMode().ordinal());
        }else{
            buffer.writeByte(settings.getChatMode().ordinal());
        }
        buffer.writeBoolean(settings.isChatColorsEnabled());
        buffer.writeByte(settings.getSkinParts().getBitmask());
        buffer.writeByte(settings.getSkinParts().getBitmask());
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_9)){
            MinecraftProtocolUtil.writeVarInt(buffer,settings.getMainHand().ordinal());
        }
    }
}
