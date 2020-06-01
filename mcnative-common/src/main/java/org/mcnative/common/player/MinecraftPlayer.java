/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.common.player;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.describer.VariableObjectToString;
import net.pretronic.libraries.message.language.Language;
import net.pretronic.libraries.message.language.LanguageAble;
import net.pretronic.libraries.utility.annonations.Nullable;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.network.component.server.ServerStatusResponse;
import org.mcnative.common.player.profile.GameProfile;
import org.mcnative.common.serviceprovider.permission.Permissable;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface MinecraftPlayer extends Permissable, ServerStatusResponse.PlayerInfo, VariableObjectToString, LanguageAble {

    UUID getUniqueId();

    long getXBoxId();

    long getFirstPlayed();

    long getLastPlayed();


    @Nullable
    GameProfile getGameProfile();

    Document getProperties();

    Language getLanguage();

    void setLanguage(Language language);


    String getDisplayName();

    String getDisplayName(MinecraftPlayer player);//Extra display name for another player (Useful for Nick).

    PlayerDesign getDesign();

    PlayerDesign getDesign(MinecraftPlayer player);

    void setDesign(PlayerDesign design);



    Collection<PlayerSetting> getSettings();

    default Collection<PlayerSetting> getSettings(ObjectOwner owner){
        return getSettings(owner.getName());
    }

    Collection<PlayerSetting> getSettings(String owner);

    default PlayerSetting getSetting(ObjectOwner owner, String key){
        return getSetting(owner.getName(),key);
    }

    PlayerSetting getSetting(String owner, String key);


    default PlayerSetting setSetting(ObjectOwner owner, String key, Object value){
        return setSetting(owner.getName(),key,value);
    }

    PlayerSetting setSetting(String owner, String key, Object value);

    default boolean hasSetting(ObjectOwner owner, String key){
        return hasSetting(owner.getName(),key);
    }

    default boolean hasSetting(String owner, String key){
        return getSetting(owner,key) != null;
    }

    default boolean hasSetting(ObjectOwner owner, String key,Object value){
        return hasSetting(owner.getName(),key,value);
    }

    default boolean hasSetting(String owner, String key,Object value){
        PlayerSetting settings = getSetting(owner, key);
        return settings != null && settings.equalsValue(value);
    }

    default void removeSetting(ObjectOwner owner, String key){
        removeSetting(owner.getName(),key);
    }

    void removeSetting(String owner, String key);


    @Nullable
    <T> T getAs(Class<T> otherPlayerClass);

    @Nullable
    ConnectedMinecraftPlayer getAsConnectedPlayer();

    @Nullable
    OnlineMinecraftPlayer getAsOnlinePlayer();


    boolean isConnected();

    boolean isOnline();

    default boolean hasPlayedBefore(){
        return getFirstPlayed() != 0 || getFirstPlayed() == getLastPlayed();
    }

    //@Todo per server integration? -> global / Server
    boolean isWhitelisted();

    void setWhitelisted(boolean whitelisted);

    boolean isBanned();

    String getBanReason();

    void ban(String reason);

    void ban(String reason, long time, TimeUnit unit);

    void unban();


    boolean isMuted();

    String getMuteReason();

    void mute(String reason);

    void mute(String reason, long time, TimeUnit unit);

    void unmute();

    @Override
    default String toStringVariable() {
        return getDisplayName();
    }
}
