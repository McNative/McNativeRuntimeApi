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

import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.annonations.Internal;
import org.mcnative.common.McNative;
import org.mcnative.common.player.*;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.data.PlayerDataProvider;

import java.util.*;
import java.util.function.Function;

public class BungeeCordPlayerManager implements PlayerManager {

    private final Map<Class<?>,Function<MinecraftPlayer,MinecraftPlayer>> adapters;
    private final Collection<ConnectedMinecraftPlayer> onlineMinecraftPlayers;

    public BungeeCordPlayerManager() {
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
    public ConnectedMinecraftPlayer getMappedPlayer(net.md_5.bungee.api.connection.ProxiedPlayer player0){
        ConnectedMinecraftPlayer result = Iterators.findOne(this.onlineMinecraftPlayers, player -> player.getUniqueId() == player0.getUniqueId());
        if(result == null) throw new IllegalArgumentException("McNative mapping error (BungeeCord -> McNative)");
        return result;
    }

    @Internal
    public void registerPlayer(ConnectedMinecraftPlayer player){
        this.onlineMinecraftPlayers.add(player);
    }

    @Internal
    public OnlineMinecraftPlayer unregisterPlayer(UUID uniqueId){
        return Iterators.removeOne(this.onlineMinecraftPlayers, player -> player.getUniqueId().equals(uniqueId));
    }

}
