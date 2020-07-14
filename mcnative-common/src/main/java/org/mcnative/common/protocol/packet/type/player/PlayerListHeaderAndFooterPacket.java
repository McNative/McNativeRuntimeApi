/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.04.20, 20:20
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
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.text.components.MessageComponent;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class PlayerListHeaderAndFooterPacket implements MinecraftPacket {

    private static final String REMOVE_CONTENT = "{\"translate\":\"\"}";

    private MessageComponent<?> header;
    private MessageComponent<?> footer;

    private VariableSet headerVariables;
    private VariableSet footerVariables;

    public final static PacketIdentifier IDENTIFIER = newIdentifier(PlayerListHeaderAndFooterPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_8,0x47)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x48)
                    ,map(MinecraftProtocolVersion.JE_1_12,0x49)
                    ,map(MinecraftProtocolVersion.JE_1_12_1,0x4A)
                    ,map(MinecraftProtocolVersion.JE_1_12_2,0x53)
                    ,map(MinecraftProtocolVersion.JE_1_13,0x4E)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x53)
                    ,map(MinecraftProtocolVersion.JE_1_15,0x54)
                    ,map(MinecraftProtocolVersion.JE_1_16,0x53)));

    public PlayerListHeaderAndFooterPacket() {
        this.headerVariables = VariableSet.createEmpty();
        this.footerVariables = VariableSet.createEmpty();
    }

    public MessageComponent<?> getHeader() {
        return header;
    }

    public void setHeader(MessageComponent<?> header) {
        this.header = header;
    }

    public MessageComponent<?> getFooter() {
        return footer;
    }

    public void setFooter(MessageComponent<?> footer) {
        this.footer = footer;
    }

    public VariableSet getHeaderVariables() {
        return headerVariables;
    }

    public void setHeaderVariables(VariableSet headerVariables) {
        this.headerVariables = headerVariables;
    }

    public VariableSet getFooterVariables() {
        return footerVariables;
    }

    public void setFooterVariables(VariableSet footerVariables) {
        this.footerVariables = footerVariables;
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
        String header = this.header == null ? REMOVE_CONTENT : this.header.compileToString(headerVariables);
        String footer = this.footer == null ? REMOVE_CONTENT : this.footer.compileToString(footerVariables);

        MinecraftProtocolUtil.writeString(buffer, header);
        MinecraftProtocolUtil.writeString(buffer, footer);
    }
}
