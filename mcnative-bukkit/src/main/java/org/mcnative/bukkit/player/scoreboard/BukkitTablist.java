/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.03.20, 13:09
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

package org.mcnative.bukkit.player.scoreboard;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.player.scoreboard.ScoreboardEntry;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.text.components.MessageComponent;

import java.util.Collection;
import java.util.List;

public class BukkitTablist implements Tablist {

    private MessageComponent<?> header;
    private MessageComponent<?> footer;

    private VariableSet headerVariables;
    private VariableSet footerVariables;

    //private final List<ScoreboardEntry> entries;

    @Override
    public Collection<MinecraftPlayer> getPlayers() {
        return null;
    }

    @Override
    public List<ScoreboardEntry> getEntries() {
        return null;//entries;
    }

    @Override
    public void setHeader(MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(component,variables);
        this.header = component;
        this.headerVariables = variables;
    }

    @Override
    public void setFooter(MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(component,variables);
        this.footer = component;
        this.footerVariables = variables;
    }

    @Override
    public void setHeaderAndFooter(MessageComponent<?> header, MessageComponent<?> footer, VariableSet variables) {
        Validate.notNull(header,footer,variables);
        this.header = header;
        this.footer = footer;
        this.footerVariables = variables;
        this.headerVariables = variables;
    }

    @Override
    public void clearHeaderAndFooter() {
        this.footer = null;
        this.footerVariables = null;
        this.header = null;
        this.headerVariables = null;
    }

    @Override
    public void addEntry(MinecraftPlayer player) {

    }

    @Override
    public void addEntry(MinecraftPlayer player, PlayerDesign design) {

    }

    @Override
    public void addEntry(MinecraftPlayer player, String prefix, String suffix, int priority) {

    }

    @Override
    public void addEntry(String entry, String prefix, String suffix, int priority) {

    }

    @Override
    public void addEntry(ScoreboardEntry entry) {

    }

    @Override
    public void removeEntry(MinecraftPlayer player) {

    }

    @Override
    public void removeEntry(ScoreboardEntry entry) {

    }

    @Override
    public void removeEntry(String entry) {

    }

    @Override
    public void updateEntries() {

    }

    @Override
    public void updateMeta() {

    }

    @Override
    public void update() {

    }
}
