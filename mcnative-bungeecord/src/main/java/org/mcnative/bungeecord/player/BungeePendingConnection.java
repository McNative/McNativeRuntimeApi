/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 12.10.19, 10:07
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
import net.prematic.libraries.utility.annonations.Internal;
import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.protocol.Endpoint;
import org.mcnative.common.protocol.MinecraftEdition;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.netty.MinecraftProtocolEncoder;
import org.mcnative.common.protocol.netty.rewrite.MinecraftProtocolRewriteDecoder;
import org.mcnative.common.protocol.netty.rewrite.MinecraftProtocolRewriteEncoder;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.PacketDirection;
import org.mcnative.common.protocol.packet.type.MinecraftDisconnectPacket;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;
import org.mcnative.proxy.McNativeProxyConfiguration;

import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class BungeePendingConnection implements PendingConnection {

    private static final Class<?> PENDING_CONNECTION_HANDLER_CLASS;

    static {
        Class<?> pending;
        try { pending  = Class.forName("net.md_5.bungee.connection.InitialHandler"); } catch (ClassNotFoundException ignored) {pending=null;}
        PENDING_CONNECTION_HANDLER_CLASS = pending;
    }

    private net.md_5.bungee.api.connection.PendingConnection original;
    private final Channel channel;
    private final Object channelWrapper;

    private ConnectionState state;
    private MinecraftProtocolVersion version;

    public BungeePendingConnection(net.md_5.bungee.api.connection.PendingConnection original) {
        this.original = original;
        this.version = MinecraftProtocolVersion.of(MinecraftEdition.JAVA,original.getVersion());
        this.state = ConnectionState.HANDSHAKE;
        if(PENDING_CONNECTION_HANDLER_CLASS != null && PENDING_CONNECTION_HANDLER_CLASS.isAssignableFrom(original.getClass())) {
            this.channelWrapper = ReflectionUtil.getFieldValue(PENDING_CONNECTION_HANDLER_CLASS,original, "ch");
            this.channel = ReflectionUtil.getFieldValue(channelWrapper, "ch", Channel.class);
        }else throw new IllegalArgumentException("Invalid pending connection.");
    }

    @Override
    public UUID getUniqueId() {
        return original.getUniqueId();
    }

    @Override
    public long getXBoxId() {
        return -1;//Not a bedrock player
    }

    @Override
    public void setUniqueId(UUID uniqueId) {
        original.setUniqueId(uniqueId);
    }

    @Override
    public boolean isOnlineMode() {
        return original.isOnlineMode();
    }

    @Override
    public void setOnlineMode(boolean online) {
        original.setOnlineMode(online);
    }

    @Override
    public String getName() {
        return original.getName();
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return version;
    }

    @Override
    public ConnectionState getState() {
        return state;
    }

    @Override
    public InetSocketAddress getAddress() {
        return original.getAddress();
    }

    @Override
    public boolean isConnected() {
        return original.isConnected();
    }

    @Override
    public void disconnect(MessageComponent<?> reason, VariableSet variables) {
        String state = getRawState().toString();
        if(state.equals("STATUS") || state.equals("PING")) channel.close();
        else{
            MinecraftDisconnectPacket packet = new MinecraftDisconnectPacket();
            packet.setReason(reason);
            packet.setVariables(variables);
            setClosing();
            channel.eventLoop().schedule(() -> channelWrapperClose(packet), 250, TimeUnit.MILLISECONDS);
        }
    }

    private Object getRawState(){
        return ReflectionUtil.getFieldValue(original,"thisState");
    }

    private void channelWrapperClose(Object packet){
        ReflectionUtil.invokeMethod(channelWrapper,"close",new Class[]{Object.class},new Object[]{packet});
    }

    private void setClosing(){
        ReflectionUtil.changeFieldValue(channelWrapper,"closing",true);
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
        if(channel.isOpen() && channel.isActive() && channel.isRegistered()) channel.writeAndFlush(packet);
    }

    @Override
    public void sendLocalLoopPacket(MinecraftPacket packet) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void sendData(String channel, byte[] output) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Internal
    public void setState(ConnectionState state){
        this.state = state;
    }

    @Internal
    public void injectUpstreamProtocolHandlersToPipeline(){
        this.channel.pipeline().addAfter("packet-encoder","mcnative-packet-encoder"
                ,new MinecraftProtocolEncoder(McNative.getInstance().getLocal().getPacketManager()
                        ,Endpoint.UPSTREAM, PacketDirection.OUTGOING,this));

        if(!McNativeProxyConfiguration.NETWORK_PACKET_MANIPULATION_UPSTREAM_ENABLED) return;

        this.channel.pipeline().addAfter("mcnative-packet-encoder","mcnative-packet-rewrite-encoder"
                ,new MinecraftProtocolRewriteEncoder(McNative.getInstance().getLocal().getPacketManager()
                        ,Endpoint.UPSTREAM, PacketDirection.OUTGOING,this));

        this.channel.pipeline().addBefore("packet-decoder","mcnative-packet-rewrite-decoder"
                ,new MinecraftProtocolRewriteDecoder(McNative.getInstance().getLocal().getPacketManager()
                        ,Endpoint.UPSTREAM, PacketDirection.INCOMING,this));
    }

    @Internal
    public GameProfile extractGameProfile(){
        Object loginResult = ReflectionUtil.getFieldValue(original,"loginProfile");
        Object originalProperties = ReflectionUtil.getFieldValue(loginResult,"properties");
        GameProfile.Property[] properties = new GameProfile.Property[Array.getLength(originalProperties)];
        if(properties.length > 0){
            for (int i = 0; i < properties.length; i++) {
                Object property = Array.get(originalProperties,i);
                properties[i] = new GameProfile.Property(
                        ReflectionUtil.getFieldValue(property,"name",String.class)
                        ,ReflectionUtil.getFieldValue(property,"value",String.class)
                        ,ReflectionUtil.getFieldValue(property,"signature",String.class));
            }
        }
        return new GameProfile(original.getUniqueId(),original.getName(),properties);
    }
}
