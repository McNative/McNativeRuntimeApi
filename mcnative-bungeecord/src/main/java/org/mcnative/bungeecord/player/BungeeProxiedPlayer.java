/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.08.19, 18:57
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

package org.mcnative.bungeecord.player;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerConnectRequest;
import net.md_5.bungee.api.config.ServerInfo;
import net.prematic.libraries.utility.annonations.Internal;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.player.*;
import org.mcnative.common.player.bossbar.BossBar;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.common.player.scoreboard.BelowNameInfo;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.player.scoreboard.sidebar.Sidebar;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.type.MinecraftChatPacket;
import org.mcnative.common.protocol.packet.type.MinecraftTitlePacket;
import org.mcnative.common.protocol.support.DefaultProtocolChecker;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;
import org.mcnative.proxy.ProxiedPlayer;
import org.mcnative.proxy.ProxyService;
import org.mcnative.proxy.server.MinecraftServer;
import org.mcnative.proxy.server.ServerConnectResult;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BungeeProxiedPlayer extends OfflineMinecraftPlayer implements ProxiedPlayer {

    private final BungeePendingConnection connection;
    private final MinecraftPlayerData playerData;
    private final GameProfile gameProfile;

    private net.md_5.bungee.api.connection.ProxiedPlayer original;

    public BungeeProxiedPlayer(BungeePendingConnection connection, MinecraftPlayerData playerData, GameProfile gameProfile) {
        super(playerData);
        this.connection = connection;
        this.playerData = playerData;
        this.gameProfile = gameProfile;
    }

    @Override
    public String getName() {
        return connection.getName();
    }

    @Override
    public UUID getUniqueId() {
        return connection.getUniqueId();
    }

    @Override
    public MinecraftServer getServer() {
        return null;
    }

    @Override
    public MinecraftServer getDefaultConnectServer() {
        return ProxyService.getInstance().getConnectHandler().getServer(this);
    }

    @Override
    public MinecraftServer getDefaultFallbackServer() {
        return ProxyService.getInstance().getFallbackHandler().getFallbackServer(this,getServer(),null);
    }

    @Override
    public void connect(MinecraftServer target) {
        if(original == null) throw new IllegalArgumentException("Player is not finally connected.");
        if(target instanceof ServerInfo) original.connect((ServerConnectRequest) target);
        else{
            ServerInfo info = ProxyServer.getInstance().getServerInfo(target.getName());
            if(info != null) original.connect((ServerConnectRequest) target);
            else throw new IllegalArgumentException("The targeted server is not registered as a server.");
        }
    }
    @Override
    public CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target) {
        return null;
    }

    @Override
    public DeviceInfo getDevice() {
        return null;//Not supported on BungeeCord
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return connection.getProtocolVersion();
    }

    @Override
    public ConnectionState getState() {
        return connection.getState();
    }

    @Override
    public String getClientName() {
        return null;
    }

    @Override
    public boolean isOnlineMode() {
        return connection.isOnlineMode();
    }

    @Override
    public PlayerSettings getSettings() {
        return null;
    }

    @Override
    public int getPing() {
        if(original == null) throw new IllegalArgumentException("Player is not finally connected.");
        return original.getPing();
    }

    @Override
    public Sidebar getSidebar() {
        return null;
    }

    @Override
    public void setSidebar(Sidebar sidebar) {

    }

    @Override
    public Tablist getTablist() {
        return null;
    }

    @Override
    public void setTablist(Tablist tablist) {

    }

    @Override
    public BelowNameInfo getBelowNameInfo() {
        return null;
    }

    @Override
    public void setBelowNameInfo(BelowNameInfo info) {

    }

    @Override
    public void performCommand(String command) {

    }

    @Override
    public void chat(String message) {

    }

    @Override
    public Collection<ReceiverChannel> getChatChannels() {
        return null;
    }

    @Override
    public void addChatChannel(ReceiverChannel channel) {

    }

    @Override
    public void removeChatChannel(ReceiverChannel channel) {

    }

    @Override
    public void sendMessage(MessageComponent message, VariableSet variables) {
        MinecraftChatPacket packet = new MinecraftChatPacket();
        packet.setPosition(ChatPosition.PLAYER_CHAT);
        packet.setMessage(message);
        packet.setVariables(variables);
        sendPacket(packet);
    }

    @Override
    public void sendSystemMessage(MessageComponent message, VariableSet variables) {
        MinecraftChatPacket packet = new MinecraftChatPacket();
        packet.setPosition(ChatPosition.SYSTEM_CHAT);
        packet.setMessage(message);
        packet.setVariables(variables);
        sendPacket(packet);
    }

    @Override
    public void sendTitle(Title title) {
        MinecraftTitlePacket timePacket = new MinecraftTitlePacket();
        timePacket.setAction(MinecraftTitlePacket.Action.SET_TIME);
        timePacket.setTime(title.getTiming());
        sendPacket(timePacket);

        if(title.getTitle() != null){
            MinecraftTitlePacket titlePacket = new MinecraftTitlePacket();
            titlePacket.setAction(MinecraftTitlePacket.Action.SET_TITLE);
            titlePacket.setMessage(title.getTitle());
            titlePacket.setVariables(title.getVariables());
            sendPacket(titlePacket);
        }

        if(title.getSubTitle() != null){
            MinecraftTitlePacket subTitle = new MinecraftTitlePacket();
            subTitle.setAction(MinecraftTitlePacket.Action.SET_SUBTITLE);
            subTitle.setMessage(title.getSubTitle());
            subTitle.setVariables(title.getVariables());
            sendPacket(subTitle);
        }
    }

    @Override
    public void resetTitle() {
        MinecraftTitlePacket packet = new MinecraftTitlePacket();
        packet.setAction(MinecraftTitlePacket.Action.RESET);
        sendPacket(packet);
    }

    @Override
    public void sendActionbar(MessageComponent message, VariableSet variables) {
        MinecraftChatPacket packet = new MinecraftChatPacket();
        packet.setPosition(ChatPosition.ACTIONBAR);
        packet.setMessage(message);
        packet.setVariables(variables);
        sendPacket(packet);
    }

    @Override
    public void sendActionbar(MessageComponent message, VariableSet variables, long staySeconds) {
        //Attache to scheduler
    }

    @Override
    public Collection<BossBar> getActiveBossBars() {
        return null;
    }

    @Override
    public void addBossBar(BossBar bossBar) {

    }

    @Override
    public void removeBossBar(BossBar bossBar) {

    }

    @Override
    public void playNote(Instrument instrument, Note note) {

    }

    @Override
    public void playSound(Sound sound, SoundCategory category, float volume, float pitch) {

    }

    @Override
    public void stopSound(Sound sound) {

    }

    @Override
    public void stopSound(String sound, SoundCategory category) {

    }

    @Override
    public void setResourcePack(String url) {

    }

    @Override
    public void setResourcePack(String url, String hash) {

    }

    @Override
    public InetSocketAddress getAddress() {
        return connection.getAddress();
    }

    @Override
    public boolean isConnected() {
        return connection.isConnected();
    }

    @Override
    public void disconnect(MessageComponent reason, VariableSet variables) {
        connection.disconnect(reason, variables);
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
        connection.sendPacket(packet);
    }

    @Override
    public void sendLocalLoopPacket(MinecraftPacket packet) {

    }

    @Override
    public void sendData(String channel, byte[] output) {

    }

    @Override
    public InputStream sendDataQuery(String channel, byte[] output) {
        return null;
    }

    @Override
    public InputStream sendDataQuery(String channel, Consumer<OutputStream> output) {
        return null;
    }

    @Override
    public <T extends MinecraftPlayer> T getAs(Class<T> otherPlayerClass) {
        return (T) McNative.getInstance().getPlayerManager().translate(otherPlayerClass,this);
    }

    @Override
    public OnlineMinecraftPlayer getAsOnlinePlayer() {
        return this;
    }

    @Override
    public boolean isOnline() {
        return isConnected();
    }

    @Override
    public void check(Consumer<ProtocolCheck> checker) {
        ProtocolCheck check = new DefaultProtocolChecker();
        checker.accept(check);
        check.process(getProtocolVersion());
    }

    @Override
    public boolean equals(Object object) {
        if(object == this || (original != null && original.equals(object))) return true;
        else if(object instanceof MinecraftPlayerComparable) return ((MinecraftPlayerComparable) object).equals(this);
        else if(object instanceof MinecraftPlayer) return ((MinecraftPlayer) object).getUniqueId().equals(getUniqueId());
        return false;
    }

    @Internal
    public void postLogin(net.md_5.bungee.api.connection.ProxiedPlayer original){
        this.original = original;
        connection.setState(ConnectionState.GAME);
    }
}
