/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.02.20, 22:50
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

package org.mcnative.bukkit.utils;

import io.netty.channel.Channel;
import net.pretronic.libraries.utility.reflect.ReflectException;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class BukkitReflectionUtil {

    private static final String BUKKIT_BASE = Bukkit.getServer().getClass().getPackage().getName();
    private static final String NMS_BASE = BUKKIT_BASE.replace("org.bukkit.craftbukkit", "net.minecraft.server");

    public static Class<?> getClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ReflectException(e);
        }
    }

    public static Class<?> getCraftClass(String className){
        try {
            return Class.forName(BUKKIT_BASE + "." + className);
        } catch (ClassNotFoundException e) {
            throw new ReflectException(e);
        }
    }

    public static Class<?> getMNSClass(String className){
        try {
            return Class.forName(NMS_BASE + "." + className);
        } catch (ClassNotFoundException e) {
            throw new ReflectException(e);
        }
    }

    public static Object getServerConnection()  {
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

    public static Channel getPlayerChannel(Player player){
        Object entityPlayer = ReflectionUtil.invokeMethod(player,"getHandle");
        Object connection = ReflectionUtil.getFieldValue(entityPlayer,"playerConnection");
        Object networkManager = ReflectionUtil.getFieldValue(connection,"networkManager");
        return ReflectionUtil.getFieldValue(networkManager,"channel", Channel.class);
    }

    public static Object getGameProfile(Player player){
        Object entityPlayer = ReflectionUtil.invokeMethod(player,"getHandle");
        return ReflectionUtil.invokeMethod(getMNSClass("EntityHuman") ,entityPlayer,"getProfile",new Object[]{});
    }

}
