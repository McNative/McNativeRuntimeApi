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

package org.mcnative.bungeecord.player;

import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.annonations.Internal;
import org.mcnative.common.McNative;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.player.OfflineMinecraftPlayer;
import org.mcnative.common.player.PlayerManager;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.proxy.ProxiedPlayer;

import java.util.*;
import java.util.function.Function;

public class BungeeCordPlayerManager implements PlayerManager<ProxiedPlayer> {

    private final Map<Class<?>,Function<MinecraftPlayer,MinecraftPlayer>> adapters;
    private final Collection<ProxiedPlayer> onlineMinecraftPlayers;

    public BungeeCordPlayerManager() {
        this.adapters = new LinkedHashMap<>();
        this.onlineMinecraftPlayers = new ArrayList<>();
    }

    @Override
    public int getOnlineCount() {
        return ProxyServer.getInstance().getOnlineCount();
    }

    @Override
    public Collection<ProxiedPlayer> getOnlinePlayers() {
        return onlineMinecraftPlayers;
    }

    @Override
    public <T extends MinecraftPlayer> Collection<T> getOnlinePlayers(Class<T> playerClass) {
        Collection<T> translatedPlayers = new ArrayList<>(onlineMinecraftPlayers.size());
        onlineMinecraftPlayers.forEach(player -> translate(playerClass,player));
        return translatedPlayers;
    }

    @Override
    public MinecraftPlayer getPlayer(UUID uniqueId) {
        ProxiedPlayer online = getOnlinePlayer(uniqueId);
        if(online != null) return online;
        MinecraftPlayerData data = McNative.getInstance().getPlayerDataStorageHandler().getPlayerData(uniqueId);
        return data!=null?new OfflineMinecraftPlayer(data):null;
    }

    @Override
    public MinecraftPlayer getPlayer(long xBoxId) {
        ProxiedPlayer online = getOnlinePlayer(xBoxId);
        if(online != null) return online;
        MinecraftPlayerData data = McNative.getInstance().getPlayerDataStorageHandler().getPlayerData(xBoxId);
        return data!=null?new OfflineMinecraftPlayer(data):null;
    }

    @Override
    public MinecraftPlayer getPlayer(String name) {
        ProxiedPlayer online = getOnlinePlayer(name);
        if(online != null) return online;
        MinecraftPlayerData data = McNative.getInstance().getPlayerDataStorageHandler().getPlayerData(name);
        return data!=null?new OfflineMinecraftPlayer(data):null;
    }

    @Override
    public ProxiedPlayer getOnlinePlayer(UUID uniqueId) {
        return Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getUniqueId().equals(uniqueId));
    }

    @Override
    public ProxiedPlayer getOnlinePlayer(long xBoxId) {
        return Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getXBoxId() == xBoxId);
    }

    @Override
    public ProxiedPlayer getOnlinePlayer(String name) {
        return Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getName().equalsIgnoreCase(name));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends MinecraftPlayer> void registerPlayerAdapter(Class<T> playerClass, Function<MinecraftPlayer, T> translator) {
        this.adapters.put(playerClass, (Function<MinecraftPlayer, MinecraftPlayer>) translator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends MinecraftPlayer> T translate(Class<T> translatedClass, MinecraftPlayer player) {
        Function<MinecraftPlayer,MinecraftPlayer> translator = this.adapters.get(translatedClass);
        if(translator == null) throw new IllegalArgumentException(String.format("No translator for player %s class found.",translatedClass));
        return (T) translator.apply(player);
    }

    @Internal
    public void registerPlayer(ProxiedPlayer player){
        this.onlineMinecraftPlayers.add(player);
    }

    @Internal
    public void unregisterPlayer(ProxiedPlayer player){
        Iterators.removeOne(this.onlineMinecraftPlayers, player0 -> player0.equals(player));
    }
}
