/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.03.20, 13:27
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

package org.mcnative.common.protocol.packet.type.scoreboard;

import io.netty.buffer.ByteBuf;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolUtil;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.PacketIdentifier;
import org.mcnative.common.protocol.packet.type.MinecraftChatPacket;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.format.TextColor;

import static org.mcnative.common.protocol.packet.MinecraftPacket.*;

public class MinecraftScoreboardTeamsPacket implements MinecraftPacket {

    public final static PacketIdentifier IDENTIFIER = newIdentifier(MinecraftChatPacket.class
            ,on(PacketDirection.OUTGOING
                    ,map(MinecraftProtocolVersion.JE_1_7,0x3E)
                    ,map(MinecraftProtocolVersion.JE_1_9,0x41)
                    ,map(MinecraftProtocolVersion.JE_1_12,0x43)
                    ,map(MinecraftProtocolVersion.JE_1_12_1,0x44)
                    ,map(MinecraftProtocolVersion.JE_1_13,0x47)
                    ,map(MinecraftProtocolVersion.JE_1_14,0x4B)
                    ,map(MinecraftProtocolVersion.JE_1_15,0x4C)));

    private String name;
    private Action action;

    private FriendlyFlag friendlyFlag;
    private OptionStatus nameTagVisibility;
    private OptionStatus collisionRule;

    private MessageComponent<?> displayName;
    private TextColor color;
    private MessageComponent<?> prefix;
    private MessageComponent<?> suffix;
    private String[] entities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public FriendlyFlag getFriendlyFlag() {
        return friendlyFlag;
    }

    public void setFriendlyFlag(FriendlyFlag friendlyFlag) {
        this.friendlyFlag = friendlyFlag;
    }

    public OptionStatus getNameTagVisibility() {
        return nameTagVisibility;
    }

    public void setNameTagVisibility(OptionStatus nameTagVisibility) {
        this.nameTagVisibility = nameTagVisibility;
    }

    public OptionStatus getCollisionRule() {
        return collisionRule;
    }

    public void setCollisionRule(OptionStatus collisionRule) {
        this.collisionRule = collisionRule;
    }

    public MessageComponent<?> getDisplayName() {
        return displayName;
    }

    public void setDisplayName(MessageComponent<?> displayName) {
        this.displayName = displayName;
    }

    public TextColor getColor() {
        return color;
    }

    public void setColor(TextColor color) {
        this.color = color;
    }

    public MessageComponent<?> getPrefix() {
        return prefix;
    }

    public void setPrefix(MessageComponent<?> prefix) {
        this.prefix = prefix;
    }

    public MessageComponent<?> getSuffix() {
        return suffix;
    }

    public void setSuffix(MessageComponent<?> suffix) {
        this.suffix = suffix;
    }

    public String[] getEntities() {
        return entities;
    }

    public void setEntities(String[] entities) {
        this.entities = entities;
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
        if(direction == PacketDirection.OUTGOING){
            MinecraftProtocolUtil.writeString(buffer,"TEST");//name
            buffer.writeByte(action.ordinal());//action.ordinal()
            if(action == Action.CREATE || action == Action.UPDATE){
                MinecraftProtocolUtil.writeString(buffer,"TEAM-DISPLAY");
                MinecraftProtocolUtil.writeString(buffer,"TEAM-prefix ");
                MinecraftProtocolUtil.writeString(buffer,"TEAM-suffix ");
                buffer.writeByte(friendlyFlag != null ? friendlyFlag.ordinal(): FriendlyFlag.DISABLED.ordinal());
                MinecraftProtocolUtil.writeString(buffer,"never");
                buffer.writeByte(0);
                MinecraftProtocolUtil.writeStringArray(buffer,new String[]{"Dkrieger","DkriesciTV"});
                //nameTagVisibility != null ? nameTagVisibility.name() : OptionStatus.ALWAYS.name()

                /*
                MinecraftProtocolUtil.writeString(buffer,displayName != null ? displayName.compileToString() : "");
                buffer.writeByte(friendlyFlag != null ? friendlyFlag.ordinal(): FriendlyFlag.ALLOW.ordinal());
                MinecraftProtocolUtil.writeString(buffer,"never");//nameTagVisibility != null ? nameTagVisibility.name() : OptionStatus.ALWAYS.name()
                MinecraftProtocolUtil.writeString(buffer,"never");//collisionRule != null ? collisionRule.name(): OptionStatus.ALWAYS.name()
                MinecraftProtocolUtil.writeVarInt(buffer,0);//buffer,color != null ? color.ordinal(): TextColor.WHITE.ordinal()
                System.out.println(prefix.compileToString());
                System.out.println(suffix.compileToString());
                MinecraftProtocolUtil.writeString(buffer,"[\"\",{\"text\":\"Test\"}]");//buffer,prefix != null ? prefix.compileToString() : ""
                MinecraftProtocolUtil.writeString(buffer,"[\"\",{\"text\":\"Test\"}]");
                if(action == Action.CREATE){
                    MinecraftProtocolUtil.writeStringArray(buffer,entities);
                }
                 */
            }else if(action == Action.ADD_ENTITIES){

            }else if(action == Action.REMOVE_ENTITIES){

            }
        }
    }

    public enum Action {

        CREATE(),
        DELETE(),
        UPDATE(),
        ADD_ENTITIES(),
        REMOVE_ENTITIES();

    }

    public enum OptionStatus {

        ALWAYS,
        NEVER,
        FOR_OTHER_TEAMS,
        FOR_OWN_TEAM;

    }

    public enum FriendlyFlag {

        DISABLED,
        ENABLED,
        CAN_SEE_INVISIBLE_PLAYERS;

    }
}
