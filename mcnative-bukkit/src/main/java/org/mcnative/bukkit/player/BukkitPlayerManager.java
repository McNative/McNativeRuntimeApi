/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 20.09.19, 20:27
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
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.MessageToByteEncoder;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.mcnative.bukkit.McNativeLauncher;
import org.mcnative.bukkit.player.connection.BukkitChannelInjector;
import org.mcnative.bukkit.utils.BukkitReflectionUtil;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.player.*;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.data.PlayerDataProvider;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.protocol.netty.McNativeMessageEncoderIgnoreWrapper;
import org.mcnative.service.entity.living.Player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.function.Function;

public class BukkitPlayerManager implements PlayerManager {

    private final Map<Class<?>,Function<MinecraftPlayer,?>> adapters;
    private final Collection<ConnectedMinecraftPlayer> onlineMinecraftPlayers;

    public BukkitPlayerManager() {
        this.adapters = new LinkedHashMap<>();
        this.onlineMinecraftPlayers = new ArrayList<>();
    }

    public Collection<ConnectedMinecraftPlayer> getConnectedPlayers() {
        return onlineMinecraftPlayers;
    }

    public ConnectedMinecraftPlayer getConnectedPlayer(UUID uniqueId) {
        return Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getUniqueId().equals(uniqueId));
    }

    public ConnectedMinecraftPlayer getConnectedPlayer(long xBoxId) {
        return Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getXBoxId() == xBoxId);
    }

    public ConnectedMinecraftPlayer getConnectedPlayer(String name) {
        return Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getName().equalsIgnoreCase(name));
    }

    @Override
    public MinecraftPlayer getPlayer(UUID uniqueId) {
        ConnectedMinecraftPlayer online = getConnectedPlayer(uniqueId);
        if(online != null) return online;
        MinecraftPlayerData data = getDataProvider().getPlayerData(uniqueId);
        return data!=null?new OfflineMinecraftPlayer(data):null;
    }

    @Override
    public MinecraftPlayer getPlayer(long xBoxId) {
        ConnectedMinecraftPlayer online = getConnectedPlayer(xBoxId);
        if(online != null) return online;
        MinecraftPlayerData data = getDataProvider().getPlayerData(xBoxId);
        return data!=null?new OfflineMinecraftPlayer(data):null;
    }

    @Override
    public MinecraftPlayer getPlayer(String name) {
        ConnectedMinecraftPlayer online = getConnectedPlayer(name);
        if(online != null) return online;
        MinecraftPlayerData data = getDataProvider().getPlayerData(name);
        return data!=null?new OfflineMinecraftPlayer(data):null;
    }

    private PlayerDataProvider getDataProvider(){
        return McNative.getInstance().getRegistry().getService(PlayerDataProvider.class);
    }

    @Override
    public <T> void registerPlayerAdapter(Class<T> playerClass, Function<MinecraftPlayer, T> translator) {
        this.adapters.put(playerClass, translator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T translate(Class<T> translatedClass, MinecraftPlayer player) {
        Function<MinecraftPlayer,?> translator = this.adapters.get(translatedClass);
        if(translator == null) throw new IllegalArgumentException(String.format("No translator for player %s class found.",translatedClass));
        return (T) translator.apply(player);
    }

    @Internal
    public BukkitPlayer getMappedPlayer(org.bukkit.entity.Player player0){
        ConnectedMinecraftPlayer result = Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getUniqueId().equals(player0.getUniqueId()));
        if(result == null) throw new IllegalArgumentException("McNative mapping error (BungeeCord -> McNative)");
        return (BukkitPlayer) result;
    }

    @Internal
    public void registerPlayer(Player player){
        this.onlineMinecraftPlayers.add(player);
    }

    @Internal
    public OnlineMinecraftPlayer unregisterPlayer(UUID uniqueId){
        return Iterators.removeOne(this.onlineMinecraftPlayers, player -> player.getUniqueId().equals(uniqueId));
    }

    @Internal
    public void loadConnectedPlayers(){
        PlayerDataProvider dataProvider = McNative.getInstance().getRegistry().getService(PlayerDataProvider.class);
        for (org.bukkit.entity.Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            MinecraftPlayerData data = dataProvider.getPlayerData(onlinePlayer.getUniqueId());
            if(data == null){
                Bukkit.getScheduler().runTask(McNativeLauncher.getPlugin(), () -> onlinePlayer.kickPlayer("Unrecognised login"));
                return;
            }

            Channel channel = BukkitReflectionUtil.getPlayerChannel(onlinePlayer);

            for (Map.Entry<String, ChannelHandler> entries : channel.pipeline()) {
                System.out.println(entries.getKey()+" | "+entries.getValue());
            }

            System.out.println("----------------------");

            GameProfile profile;
            try {
                profile = BukkitChannelInjector.extractGameProfile(BukkitReflectionUtil.getGameProfile(onlinePlayer));
            } catch (Exception ignored) {
                Bukkit.getScheduler().runTask(McNativeLauncher.getPlugin(), () -> onlinePlayer.kickPlayer("Unrecognised login"));
               return;
            }

            Object protocolEncoder = BukkitPendingConnection.VIA_VERSION ?
                    channel.pipeline().get("encoder") :
                    channel.pipeline().get("mcnative-packet-encoder");
            if(protocolEncoder == null){
                Bukkit.getScheduler().runTask(McNativeLauncher.getPlugin(), () -> onlinePlayer.kickPlayer("Unrecognised login"));
                return;
            }

            int protocolVersion = (int) ReflectionUtil.invokeMethod(protocolEncoder,"getProtocolNumber");

            resetChannelPipeline(channel);

            for (Map.Entry<String, ChannelHandler> entries : channel.pipeline()) {
                System.out.println(entries.getKey()+" | "+entries.getValue());
            }
            System.out.println("----------------------");

            BukkitPendingConnection connection;
            try {
                connection = new BukkitPendingConnection(channel,profile,onlinePlayer.getAddress()
                        , new InetSocketAddress(InetAddress.getLocalHost(),25565),protocolVersion);
                connection.setState(ConnectionState.GAME);
            } catch (UnknownHostException ignored) {
                Bukkit.getScheduler().runTask(McNativeLauncher.getPlugin(), () -> onlinePlayer.kickPlayer("Unrecognised login"));
                return;
            }

            for (Map.Entry<String, ChannelHandler> entries : channel.pipeline()) {
                System.out.println(entries.getKey()+" | "+entries.getValue());
            }

            BukkitPlayer player = new BukkitPlayer(onlinePlayer,connection,data);
            connection.setPlayer(player);
            player.setJoining(false);
            registerPlayer(player);
        }
    }

    @SuppressWarnings("unchecked")
    private void resetChannelPipeline(Channel channel){
        if(BukkitPendingConnection.VIA_VERSION && !BukkitPendingConnection.PROTOCOL_LIB){
            ChannelHandler viaEncoder = channel.pipeline().get("via-encoder");
            if(viaEncoder != null && viaEncoder.getClass().getName().equals(McNativeMessageEncoderIgnoreWrapper.class.getName())){
                MessageToByteEncoder<Object> object = (MessageToByteEncoder<Object>) ReflectionUtil.invokeMethod(viaEncoder,"getOriginal");
                channel.pipeline().replace("encoder","encoder",new McNativeMessageEncoderIgnoreWrapper(object));
                channel.pipeline().remove("via-encoder");
            }
        }
        if(channel.pipeline().get("mcnative-packet-encoder") != null){
            channel.pipeline().remove("mcnative-packet-encoder");
        }
        if(channel.pipeline().get("mcnative-packet-rewrite-encoder") != null){
            channel.pipeline().remove("mcnative-packet-rewrite-encoder");
        }
        if(channel.pipeline().get("mcnative-packet-rewrite-decoder") != null){
            channel.pipeline().remove("mcnative-packet-rewrite-decoder");
        }
    }
}
