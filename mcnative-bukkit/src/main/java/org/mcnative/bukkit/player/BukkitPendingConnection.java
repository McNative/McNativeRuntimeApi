/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.02.20, 18:42
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

package org.mcnative.bukkit.player;

import io.netty.channel.Channel;
import io.netty.handler.codec.MessageToByteEncoder;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.annonations.Internal;
import org.bukkit.Bukkit;
import org.mcnative.bukkit.player.connection.ViaVersionEncoderWrapper;
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

import java.net.InetSocketAddress;
import java.util.UUID;

public class BukkitPendingConnection implements PendingConnection {

    private static boolean VIA_VERSION = Bukkit.getPluginManager().getPlugin("ViaVersion") != null;

    private final Channel channel;
    private final MinecraftProtocolVersion version;
    private final GameProfile gameProfile;
    private final InetSocketAddress address;
    private final InetSocketAddress virtualHost;
    private ConnectionState state;

    public BukkitPendingConnection(Channel channel,GameProfile gameProfile,InetSocketAddress address
            ,InetSocketAddress virtualHost, int protocolVersion) {
        this.channel = channel;
        this.gameProfile = gameProfile;
        this.address = address;
        this.virtualHost = virtualHost;
        this.version = MinecraftProtocolVersion.of(MinecraftEdition.JAVA,protocolVersion);
        this.state = ConnectionState.LOGIN;

        injectPacketCoders();
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public UUID getUniqueId() {
        return gameProfile.getUniqueId();
    }

    @Override
    public long getXBoxId() {
        return -1;
    }

    @Override
    public GameProfile getGameProfile() {
        return gameProfile;
    }

    @Override
    public void setGameProfile(GameProfile profile) {
        throw new UnsupportedOperationException("Currently not supported on bukkit servers");
    }

    @Override
    public void setUniqueId(UUID uniqueId) {
        throw new UnsupportedOperationException("Currently not supported on bukkit servers");
    }

    @Override
    public boolean isOnlineMode() {
        return Bukkit.getServer().getOnlineMode();
    }

    @Override
    public void setOnlineMode(boolean online) {
        throw new UnsupportedOperationException("It is not possible to change the online mode of a player on a bukkit server.");
    }

    @Override
    public InetSocketAddress getVirtualHost() {
        return virtualHost;
    }

    @Override
    public String getName() {
        return gameProfile.getName();
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
        return address;
    }

    @Override
    public boolean isConnected() {
        return channel != null && channel.isOpen();
    }

    @Override
    public void disconnect(MessageComponent<?> reason, VariableSet variables) {
        MinecraftDisconnectPacket packet = new MinecraftDisconnectPacket();
        packet.setReason(reason);
        packet.setVariables(variables);
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
       if(isConnected()){//@Todo implement packet queue
           channel.writeAndFlush(packet);
       }
    }

    @Override
    public void sendLocalLoopPacket(MinecraftPacket packet) {

    }

    @Override
    public void sendData(String channel, byte[] output) {

    }

    @Internal
    public void setState(ConnectionState state){
        this.state = state;
    }

    @SuppressWarnings("unchecked")
    private void injectPacketCoders(){
        if(VIA_VERSION){
            MessageToByteEncoder<Object> original = (MessageToByteEncoder<Object>) channel.pipeline().get("encoder");
            channel.pipeline().replace("encoder","encoder"
                    ,new MinecraftProtocolEncoder(McNative.getInstance().getLocal().getPacketManager()
                            , Endpoint.UPSTREAM, PacketDirection.OUTGOING,this));
            
            channel.pipeline().addAfter("encoder","via-encoder",new ViaVersionEncoderWrapper(original));

            //if(!McNativeProxyConfiguration.NETWORK_PACKET_MANIPULATION_UPSTREAM_ENABLED) return;//@Todo add configuration

            this.channel.pipeline().addBefore("encoder","mcnative-packet-rewrite-encoder"
                    ,new MinecraftProtocolRewriteEncoder(McNative.getInstance().getLocal().getPacketManager()
                            ,Endpoint.UPSTREAM, PacketDirection.OUTGOING,this));
        }else{
            this.channel.pipeline().addAfter("encoder","mcnative-packet-encoder"
                    ,new MinecraftProtocolEncoder(McNative.getInstance().getLocal().getPacketManager()
                            , Endpoint.UPSTREAM, PacketDirection.OUTGOING,this));

            this.channel.pipeline().addAfter("mcnative-packet-encoder","mcnative-packet-rewrite-encoder"
                    ,new MinecraftProtocolRewriteEncoder(McNative.getInstance().getLocal().getPacketManager()
                            ,Endpoint.UPSTREAM, PacketDirection.OUTGOING,this));

        }

        this.channel.pipeline().addBefore("decoder","mcnative-packet-rewrite-decoder"
                ,new MinecraftProtocolRewriteDecoder(McNative.getInstance().getLocal().getPacketManager()
                        ,Endpoint.UPSTREAM, PacketDirection.INCOMING,this));

    }
}
