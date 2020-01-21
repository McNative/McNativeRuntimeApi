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

import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerConnectRequest;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.prematic.libraries.message.bml.variable.VariableSet;
import net.prematic.libraries.utility.annonations.Internal;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bungeecord.server.BungeeCordServerMap;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.player.*;
import org.mcnative.common.player.bossbar.BossBar;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.scoreboard.BelowNameInfo;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.player.scoreboard.sidebar.Sidebar;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.Endpoint;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.netty.rewrite.MinecraftProtocolRewriteDecoder;
import org.mcnative.common.protocol.netty.rewrite.MinecraftProtocolRewriteEncoder;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.type.MinecraftChatPacket;
import org.mcnative.common.protocol.packet.type.MinecraftResourcePackSendPacket;
import org.mcnative.common.protocol.packet.type.MinecraftTitlePacket;
import org.mcnative.common.protocol.support.DefaultProtocolChecker;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.proxy.McNativeProxyConfiguration;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/*
@Todo In proxy player
    - Below Name
    - Sidebar
    - Tablist
    - Actionbar
    - Bossbar
    - Sound / Note
 */
public class BungeeProxiedPlayer extends OfflineMinecraftPlayer implements ConnectedMinecraftPlayer {

    private final BungeeCordServerMap serverMap;
    private final BungeePendingConnection connection;

    private net.md_5.bungee.api.connection.ProxiedPlayer original;

    private MinecraftServer server;
    private PlayerSettings settings;

    private ChatChannel chatChannel;

    public BungeeProxiedPlayer(BungeeCordServerMap serverMap,BungeePendingConnection connection, MinecraftPlayerData playerData) {
        super(playerData);
        this.serverMap = serverMap;
        this.connection = connection;

        ChatChannel global = McNative.getInstance().getLocal().getServerChat();
        if(global != null) setChatChannel(global);
    }

    @Internal
    public net.md_5.bungee.api.connection.ProxiedPlayer getOriginal() {
        return original;
    }

    public BungeePendingConnection getPendingConnection(){
        return connection;
    }

    @Override
    public String getName() {
        return connection.getName();
    }

    @Override
    public void sendMessage(String message) {
        sendMessage(Text.of(message));
    }

    @Override
    public UUID getUniqueId() {
        return connection.getUniqueId();
    }

    @Override
    public MinecraftServer getServer() {
        return server;
    }

    @Override
    public void connect(MinecraftServer target) {
        if(original == null) throw new IllegalArgumentException("Player is not finally connected.");
        original.connect(serverMap.getMappedInfo(target));
    }

    @Override
    public void connect(MinecraftServer target, ServerConnectReason reason) {
        if(original == null) throw new IllegalArgumentException("Player is not finally connected.");
        original.connect(serverMap.getMappedInfo(target),mapReason(reason));
    }

    @Override
    public CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target,ServerConnectReason reason) {
        if(original == null) throw new IllegalArgumentException("Player is not finally connected.");
        CompletableFuture<ServerConnectResult> future = new CompletableFuture<>();
        ServerConnectRequest.Builder requestBuilder = ServerConnectRequest.builder()
                .retry(false)
                .reason(mapReason(reason))
                .target(serverMap.getMappedInfo(target))
                .callback((result, error) -> {
                    if(error != null) future.completeExceptionally(error);
                    else future.complete(mapResult(result));
                });
        original.connect(requestBuilder.build());
        return future;
    }

    private ServerConnectEvent.Reason mapReason(ServerConnectReason reason){
        switch (reason){
            case LOGIN: return ServerConnectEvent.Reason.JOIN_PROXY;
            case API: return ServerConnectEvent.Reason.PLUGIN;
            case FALLBACK: return ServerConnectEvent.Reason.LOBBY_FALLBACK;
            case REDIRECT_KICK: return ServerConnectEvent.Reason.KICK_REDIRECT;
            case REDIRECT_SERVER_DOWN: return ServerConnectEvent.Reason.SERVER_DOWN_REDIRECT;
            default:return ServerConnectEvent.Reason.UNKNOWN;
        }
    }

    private ServerConnectResult mapResult(ServerConnectRequest.Result result){
        switch (result){
            case SUCCESS: return ServerConnectResult.SUCCESS;
            case ALREADY_CONNECTED: return ServerConnectResult.ALREADY_CONNECTED;
            case ALREADY_CONNECTING: return ServerConnectResult.ALREADY_CONNECTING;
            case EVENT_CANCEL: return ServerConnectResult.CANCEL;
            default: return ServerConnectResult.FAIL;
        }
    }

    @Override
    public ChatChannel getChatChannel() {
        return chatChannel;
    }

    @Override
    public void setChatChannel(ChatChannel channel) {
        if(!channel.containsPlayer(this)) channel.addPlayer(this);
        this.chatChannel = channel;
    }

    @Override
    public void kick(MessageComponent<?> message, VariableSet variables) {
        connection.disconnect(message,variables);
    }

    @Override
    public DeviceInfo getDevice() {
        return DeviceInfo.JAVA;
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
    public boolean isOnlineMode() {
        return connection.isOnlineMode();
    }

    @Override
    public PlayerSettings getSettings() {
        return settings;
    }

    @Override
    public int getPing() {
        if(original == null) throw new IllegalArgumentException("Player is not finally connected.");
        return original.getPing();
    }

    @Override
    public org.mcnative.common.network.component.server.ProxyServer getProxy() {
        return (org.mcnative.common.network.component.server.ProxyServer) McNative.getInstance().getLocal();
    }

    @Override
    public Sidebar getSidebar() {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void setSidebar(Sidebar sidebar) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public Tablist getTablist() {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void setTablist(Tablist tablist) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public BelowNameInfo getBelowNameInfo() {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void setBelowNameInfo(BelowNameInfo info) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void performCommand(String command) {
        ProxyServer.getInstance().getPluginManager().dispatchCommand(original,command);
    }

    @Override
    public void chat(String message) {
        original.chat(message);
    }

    @Override
    public void sendMessage(ChatPosition position, MessageComponent<?> message, VariableSet variables) {
        MinecraftChatPacket packet = new MinecraftChatPacket();
        packet.setPosition(position);
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
    public void sendActionbar(MessageComponent<?> message, VariableSet variables) {
        MinecraftChatPacket packet = new MinecraftChatPacket();
        packet.setPosition(ChatPosition.ACTIONBAR);
        packet.setMessage(message);
        packet.setVariables(variables);
        sendPacket(packet);
    }

    @Override
    public void sendActionbar(MessageComponent<?> message, VariableSet variables, long staySeconds) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public Collection<BossBar> getActiveBossBars() {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void addBossBar(BossBar bossBar) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void removeBossBar(BossBar bossBar) {
        throw new UnsupportedOperationException("Coming soon");
    }


    @Override
    public void playNote(Instrument instrument, Note note) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void playSound(Sound sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void stopSound(Sound sound) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void setResourcePack(String url) {
        setResourcePack(url,"");
    }

    @Override
    public void setResourcePack(String url, String hash) {
        MinecraftResourcePackSendPacket packet = new MinecraftResourcePackSendPacket();
        packet.setUrl(url);
        packet.setHash(hash);
        sendPacket(packet);
    }

    @Override
    public InetSocketAddress getVirtualHost() {
        return original.getPendingConnection().getVirtualHost();
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
    public void disconnect(MessageComponent<?> reason, VariableSet variables) {
        connection.disconnect(reason, variables);
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
        connection.sendPacket(packet);
    }

    @Override
    public void sendLocalLoopPacket(MinecraftPacket packet) {
        connection.sendLocalLoopPacket(packet);
    }

    @Override
    public void sendData(String channel, byte[] output) {
        original.sendData(channel,output);
    }

    @Override
    public <T> T getAs(Class<T> otherPlayerClass) {
        return McNative.getInstance().getPlayerManager().translate(otherPlayerClass,this);
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
        connection.injectUpstreamProtocolHandlersToPipeline();
    }

    @Internal
    public void setSettings(PlayerSettings settings){
        this.settings = settings;
    }

    @Internal
    public void setServer(MinecraftServer server){
        this.server = server;
    }

    public void injectDownstreamProtocolHandlersToPipeline(){
        if(!McNativeProxyConfiguration.NETWORK_PACKET_MANIPULATION_DOWNSTREAM_ENABLED) return;
        Object connection = ReflectionUtil.getFieldValue(original,"server");
        if(connection == null) return;

        Object channelWrapper = ReflectionUtil.getFieldValue(connection,"ch");
        Channel channel = ReflectionUtil.getFieldValue(channelWrapper,"ch",Channel.class);

        channel.pipeline().addAfter("packet-encoder","mcnative-packet-rewrite-encoder"
                ,new MinecraftProtocolRewriteEncoder(McNative.getInstance().getLocal().getPacketManager()
                        ,Endpoint.DOWNSTREAM, PacketDirection.OUTGOING,this));

         channel.pipeline().addBefore("packet-decoder","mcnative-packet-rewrite-decoder"
                ,new MinecraftProtocolRewriteDecoder(McNative.getInstance().getLocal().getPacketManager()
                        ,Endpoint.DOWNSTREAM, PacketDirection.INCOMING,this));
    }
}

