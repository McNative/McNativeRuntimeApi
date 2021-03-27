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

package org.mcnative.runtime.api.protocol.packet.type.scoreboard;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacketValidationException;
import org.mcnative.runtime.api.text.components.MessageComponent;
import org.mcnative.runtime.api.text.format.TextColor;

public class MinecraftScoreboardTeamsPacket implements MinecraftPacket {

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

    private VariableSet variables;

    public MinecraftScoreboardTeamsPacket() {
        this.friendlyFlag = FriendlyFlag.ENABLED;
        this.nameTagVisibility = OptionStatus.ALWAYS;
        this.collisionRule = OptionStatus.ALWAYS;
        this.color = TextColor.WHITE;
    }

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

    public VariableSet getVariables() {
        if(variables == null) return VariableSet.newEmptySet();
        return variables;
    }

    public void setVariables(VariableSet variables) {
        this.variables = variables;
    }

    @Override
    public void validate() {
        if(name == null) throw new MinecraftPacketValidationException("Team cannot be null");
        if(action == null) throw new MinecraftPacketValidationException("Action cannot be null");
    }

    public enum Action {

        CREATE(),
        DELETE(),
        UPDATE(),
        ADD_ENTITIES(),
        REMOVE_ENTITIES();

    }

    public enum OptionStatus {

        ALWAYS("always", "always"),
        NEVER("never", "never"),
        FOR_OTHER_TEAMS("hideForOtherTeams", "pushOtherTeams"),
        FOR_OWN_TEAM("hideForOwnTeam", "pushOwnTeam");

        private final String nameTagVisibilityName;
        private final String collisionRuleName;

        OptionStatus(String nameTagVisibilityName, String collisionRuleName) {
            this.nameTagVisibilityName = nameTagVisibilityName;
            this.collisionRuleName = collisionRuleName;
        }

        public String getNameTagVisibilityName() {
            return nameTagVisibilityName;
        }

        public String getCollisionRuleName() {
            return collisionRuleName;
        }
    }

    public enum FriendlyFlag {

        DISABLED((byte) 0x02),
        ENABLED((byte) 0x01),
        CAN_SEE_INVISIBLE_PLAYERS((byte) 0);

        private final byte code;

        FriendlyFlag(byte code) {
            this.code = code;
        }

        public byte getCode() {
            return code;
        }
    }
}
