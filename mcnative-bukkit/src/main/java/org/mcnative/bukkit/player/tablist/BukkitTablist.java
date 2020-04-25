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

package org.mcnative.bukkit.player.tablist;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.bml.variable.describer.DescribedHashVariableSet;
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.annonations.Internal;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.PlayerDesign;
import org.mcnative.common.player.tablist.SimpleTablistEntry;
import org.mcnative.common.player.tablist.Tablist;
import org.mcnative.common.player.tablist.TablistEntry;
import org.mcnative.common.player.tablist.TablistFormatter;
import org.mcnative.common.protocol.packet.type.player.PlayerListHeaderAndFooterPacket;
import org.mcnative.common.protocol.packet.type.scoreboard.MinecraftScoreboardTeamsPacket;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.format.TextColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BukkitTablist implements Tablist {

    private final Collection<ConnectedMinecraftPlayer> receivers;

    private MessageComponent<?> header;
    private MessageComponent<?> footer;

    private VariableSet headerVariables;
    private VariableSet footerVariables;

    private List<TablistEntry> entries;

    private TablistFormatter formatter;

    public BukkitTablist() {
        this.receivers = new ArrayList<>();
        this.entries = new ArrayList<>();
    }

    @Override
    public Collection<ConnectedMinecraftPlayer> getReceivers() {
        return this.receivers;
    }

    @Override
    public List<TablistEntry> getEntries() {
        return this.entries;
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
        this.headerVariables = variables;
        this.footerVariables = variables;
    }

    @Override
    public void clearHeaderAndFooter() {
        this.header = null;
        this.footer = null;
        this.headerVariables = null;
        this.footerVariables = null;
    }

    @Override
    public TablistFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setFormatter(TablistFormatter formatter) {
        this.formatter = formatter;
    }


    @Override
    public void addEntry(ConnectedMinecraftPlayer player) {
        addEntry((TablistEntry)player);
    }

    @Override
    public void addEntry(ConnectedMinecraftPlayer player, PlayerDesign design) {
        Validate.notNull(player,design);
        addEntry(new SimpleTablistEntry(player.getName(),design));
    }

    @Override
    public void addEntry(TablistEntry entry) {
        Validate.notNull(entry);
        this.entries.add(entry);
        for (ConnectedMinecraftPlayer receiver : this.receivers) sendEntry(receiver,entry,true);
    }

    @Override
    public void removeEntry(ConnectedMinecraftPlayer player) {
        removeEntry((TablistEntry)player);
    }

    @Override
    public void removeEntry(TablistEntry entry) {
        Validate.notNull(entry);
        this.entries.remove(entry);
        for (ConnectedMinecraftPlayer receiver : this.receivers) sendRemoveEntry(receiver,entry);
    }

    @Override
    public void updateOverview() {
        //for (ConnectedMinecraftPlayer receiver : receivers) updateOverview(receiver);
    }

    @Override
    public void updateOverview(ConnectedMinecraftPlayer player) {
        PlayerListHeaderAndFooterPacket packet = new PlayerListHeaderAndFooterPacket();
        packet.setFooter(footer);
        packet.setFooterVariables(footerVariables);
        packet.setHeader(header);
        packet.setHeaderVariables(headerVariables);
        //player.sendPacket(packet);
    }

    @Override
    public void updateEntries() {
        for (ConnectedMinecraftPlayer receiver : receivers){
            updateEntries(receiver);
        }
    }

    @Override
    public void updateEntries(ConnectedMinecraftPlayer player) {
        for (TablistEntry entry : entries) sendEntry(player,entry,false);
    }

    private void sendEntry(ConnectedMinecraftPlayer receiver,TablistEntry entry,boolean create){
        PlayerDesign design = entry.getDesign(receiver);
        VariableSet variables = new DescribedHashVariableSet();
        variables.add("entry",entry);
        if(entry.isPlayer()) variables.add("player",entry);
        variables.add("design",design);

        design.appendAdditionalVariables(variables);

        MinecraftScoreboardTeamsPacket packet = new MinecraftScoreboardTeamsPacket();
        packet.setName("TL-"+entry.getName());
        packet.setAction(create ? MinecraftScoreboardTeamsPacket.Action.CREATE : MinecraftScoreboardTeamsPacket.Action.UPDATE);
        packet.setDisplayName(Text.newBuilder().color(TextColor.RED).text("TabList").build());
        packet.setPrefix(formatter.formatPrefix(receiver,entry,variables));
        packet.setSuffix(formatter.formatSuffix(receiver,entry,variables));
        packet.setColor(formatter.getColor(receiver,entry));
        packet.setVariables(variables);
        if(create) packet.setEntities(new String[]{entry.getName()});
        receiver.sendPacket(packet);
    }

    private void sendRemoveEntry(ConnectedMinecraftPlayer receiver,TablistEntry entry){
        MinecraftScoreboardTeamsPacket packet = new MinecraftScoreboardTeamsPacket();
        packet.setName("TL-"+entry.getName());
        packet.setAction(MinecraftScoreboardTeamsPacket.Action.DELETE);
        receiver.sendPacket(packet);
    }

    @Internal
    public void attachReceiver(ConnectedMinecraftPlayer player){
        this.receivers.add(player);
        //updateOverview();
        for (TablistEntry entry : entries) sendEntry(player,entry,true);
    }

    @Internal
    public void detachReceiver(ConnectedMinecraftPlayer player){
        this.receivers.remove(player);
        for (TablistEntry entry : entries) sendRemoveEntry(player,entry);
    }
}
