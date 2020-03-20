/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.02.20, 22:49
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

package org.mcnative.bukkit.player.connection;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.reflect.ReflectException;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.mcnative.bukkit.utils.BukkitReflectionUtil;
import org.mcnative.common.player.profile.GameProfile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class BukkitChannelInjector {

    @SuppressWarnings("unchecked")
    private final static Class<? extends ChannelInitializer<?>> PACKET_HANDLER_CLASS = (Class<? extends ChannelInitializer<?>>) BukkitReflectionUtil.getMNSClass("NetworkManager");
    private final static Class<?> GAME_PROFILE_CLASS = BukkitReflectionUtil.getClass("com.mojang.authlib.GameProfile");
    private final static Class<?> LOGIN_LISTENER_CLASS = BukkitReflectionUtil.getMNSClass("LoginListener");

    private final static Field PACKET_LISTENER_FIELD = ReflectionUtil.findFieldBySimpleName(PACKET_HANDLER_CLASS,"PacketListener");
    private final static Field GAME_PROFILE_FIELD = ReflectionUtil.findFieldByType(LOGIN_LISTENER_CLASS,GAME_PROFILE_CLASS);
    private final static Field UUID_GAME_PROFILE_FIELD = ReflectionUtil.getField(GAME_PROFILE_CLASS,"id");
    private final static Field NAME_GAME_PROFILE_FIELD = ReflectionUtil.getField(GAME_PROFILE_CLASS,"name");

    static{
        PACKET_LISTENER_FIELD.setAccessible(true);
        GAME_PROFILE_FIELD.setAccessible(true);
        UUID_GAME_PROFILE_FIELD.setAccessible(true);
        NAME_GAME_PROFILE_FIELD.setAccessible(true);
    }

    private final Collection<ChannelConnection> handshakingConnections;

    public BukkitChannelInjector() {
        this.handshakingConnections = new ArrayList<>();
    }

    public ChannelConnection findConnection(UUID uniqueId){
        try{
            Iterator<ChannelConnection> iterator = handshakingConnections.iterator();
            while (iterator.hasNext()){
                ChannelConnection connection = iterator.next();
                GameProfile gameProfile = connection.getGameProfile();
                if(gameProfile == null){
                    Object packetListener = PACKET_LISTENER_FIELD.get(connection.getNetworkManager());
                    if(packetListener != null && packetListener.getClass().equals(LOGIN_LISTENER_CLASS)){
                        Object profile = GAME_PROFILE_FIELD.get(packetListener);
                        gameProfile = extractGameProfile(profile);
                        connection.setGameProfile(gameProfile);
                    }else continue;
                }
                if(gameProfile.getUniqueId().equals(uniqueId)){
                    connection.unregister();
                    iterator.remove();
                    return connection;
                }
            }
            return null;
        }catch (Exception exception){
            throw new UnsupportedOperationException("McNative is not able to extract profile information of connecting channel",exception);
        }
    }

    protected void registerConnection(ChannelConnection connection){
        this.handshakingConnections.add(connection);
    }

    protected void unregisterConnection(Channel channel){
        Iterators.removeOne(this.handshakingConnections, channelConnection -> channelConnection.getChannel().equals(channel));
    }

    //@Todo uninject ?
    @SuppressWarnings("unchecked")
    public void injectChannelInitializer(){
        try{
            Object connection = getServerConnection();
            for (Field field : connection.getClass().getDeclaredFields()) {
                if(field.getType().equals(List.class)){
                    if(((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].equals(ChannelFuture.class)){
                        field.setAccessible(true);
                        Object list = field.get(connection);
                        Object wrapper = new ChannelFutureWrapperList(this,(List<ChannelFuture>) list);
                        field.set(connection,wrapper);
                        return;
                    }
                }
            }
        }catch (Exception e){
            throw new ReflectException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected void injectChannelFuture(ChannelFuture future){
        try {
            List<String> names = future.channel().pipeline().names();
            ChannelHandler oldHandler = null;
            ChannelInitializer<SocketChannel> oldInitializer = null;
            for (String name : names) {
                ChannelHandler handler = future.channel().pipeline().get(name);
                if(handler != null){
                    Field field = ReflectionUtil.getField(handler.getClass(), "childHandler");
                    if(field != null && field.getType().equals(ChannelInitializer.class)){
                        field.setAccessible(true);
                        oldHandler = handler;
                        oldInitializer = (ChannelInitializer<SocketChannel>) field.get(handler);
                    }
                }
            }

            if(oldInitializer == null){
                oldHandler = future.channel().pipeline().first();
                Field field = ReflectionUtil.getField(oldHandler.getClass(), "childHandler");
                field.setAccessible(true);
                oldInitializer = (ChannelInitializer<SocketChannel>) field.get(oldHandler);
            }

            ChannelInitializer<?> newInit = new McNativeChannelInitializer(this,oldInitializer);
            ReflectionUtil.changeFieldValue(oldHandler,"childHandler", newInit);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to initialize channel future",e);
        }
    }

    //Code optimized from via version (https://github.com/ViaVersion/ViaVersion)
    private Object getServerConnection()  {
        try{
            Class<?> serverClazz = BukkitReflectionUtil.getMNSClass("MinecraftServer");
            Object server = serverClazz.getDeclaredMethod("getServer").invoke(null);
            for (Method method : serverClazz.getDeclaredMethods() ) {
                if (method.getReturnType() != null
                        && method.getReturnType().getSimpleName().equals("ServerConnection")
                        && method.getParameterTypes().length == 0) {
                    return method.invoke(server);
                }
            }
            throw new IllegalArgumentException("No ServerConnection found.");
        }catch (Exception exception){
            throw new ReflectException(exception);
        }
    }

    private GameProfile extractGameProfile(Object profile) throws Exception{//@Todo extract properties
        UUID uniqueId = (UUID) UUID_GAME_PROFILE_FIELD.get(profile);
        String name = (String) NAME_GAME_PROFILE_FIELD.get(profile);
        return new GameProfile(uniqueId,name,new GameProfile.Property[]{});
    }


}
