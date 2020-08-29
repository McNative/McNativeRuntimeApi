/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 07.10.19, 19:33
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
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.player.bossbar.BarColor;
import org.mcnative.common.player.bossbar.BarFlag;
import org.mcnative.common.player.bossbar.BarStyle;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.text.components.MessageComponent;

import java.util.UUID;

public class MinecraftBossBarPacket implements MinecraftPacket {

    private UUID barId;
    private Action action;

    private MessageComponent<?> title;
    private VariableSet titleVariables;
    private float health;
    private BarColor color;
    private BarStyle style;
    private BarFlag flag;

    public UUID getBarId() {
        return barId;
    }

    public void setBarId(UUID barId) {
        this.barId = barId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public MessageComponent<?> getTitle() {
        return title;
    }

    public void setTitle(MessageComponent<?> title) {
        this.title = title;
    }

    public VariableSet getTitleVariables() {
        return titleVariables;
    }

    public void setTitleVariables(VariableSet titleVariables) {
        this.titleVariables = titleVariables;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public BarColor getColor() {
        return color;
    }

    public void setColor(BarColor color) {
        this.color = color;
    }

    public BarStyle getStyle() {
        return style;
    }

    public void setStyle(BarStyle style) {
        this.style = style;
    }

    public BarFlag getFlag() {
        return flag;
    }

    public void setFlag(BarFlag flag) {
        this.flag = flag;
    }

    @Override
    public PacketIdentifier getIdentifier() {
        return null;
    }

    @Override
    public void read(MinecraftConnection connection,PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {

    }

    @Override
    public void write(MinecraftConnection connection, PacketDirection direction, MinecraftProtocolVersion version, ByteBuf buffer) {
        MinecraftProtocolUtil.writeUUID(buffer,barId);
        MinecraftProtocolUtil.writeVarInt(buffer,action.ordinal());
        if(action == Action.ADD){
            MinecraftProtocolUtil.writeString(buffer,title.compileToString(connection.getProtocolVersion(),titleVariables));
            buffer.writeFloat(health);
            MinecraftProtocolUtil.writeVarInt(buffer,color.ordinal());
            MinecraftProtocolUtil.writeVarInt(buffer,style.ordinal());
            MinecraftProtocolUtil.writeUnsignedInt(buffer,flag.ordinal());
        }else if(action == Action.UPDATE_HEALTH){
            buffer.writeFloat(health);
        }else if(action == Action.UPDATE_TITLE){
            MinecraftProtocolUtil.writeString(buffer,title.compileToString(connection.getProtocolVersion(),titleVariables));
        }else if(action == Action.UPDATE_STYLE){
            MinecraftProtocolUtil.writeVarInt(buffer,color.ordinal());
            MinecraftProtocolUtil.writeVarInt(buffer,style.ordinal());
        }else if(action == Action.UPDATE_FLAGS){
            MinecraftProtocolUtil.writeVarInt(buffer,style.ordinal());
        }
    }

    public enum Action {

        ADD(),
        REMOVE(),
        UPDATE_HEALTH(),
        UPDATE_TITLE(),
        UPDATE_STYLE(),
        UPDATE_FLAGS();

    }
}
