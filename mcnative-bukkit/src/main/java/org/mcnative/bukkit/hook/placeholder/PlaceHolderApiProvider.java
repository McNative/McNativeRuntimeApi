/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 19.10.19, 21:34
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

package org.mcnative.bukkit.hook.placeholder;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.mcnative.common.hook.placeholder.PlaceholderHook;
import org.mcnative.common.hook.placeholder.PlaceholderProvider;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.text.components.TextComponent;

import java.util.Collection;

public class PlaceHolderApiProvider implements PlaceholderProvider {

    @Override
    public String getName() {
        return "PlaceholderApi";
    }

    @Override
    public Collection<String> getIdentifiers() {
        return PlaceholderAPI.getRegisteredIdentifiers();
    }

    @Override
    public void registerPlaceHolders(String identifier, PlaceholderHook hook) {
        PlaceholderAPI.registerPlaceholderHook(identifier, new me.clip.placeholderapi.PlaceholderHook() {
            @Override
            public String onRequest(OfflinePlayer p, String params) {
                Object result =  hook.onRequest(null,params);//@Todo map player
                return result instanceof String ? (String) result : result.toString();
            }
        });
    }

    @Override
    public void unregisterPlaceHolders(String identifier) {
        PlaceholderAPI.unregisterPlaceholderHook(identifier);
    }

    @Override
    public boolean hasIdentifier(String identifier) {
        return PlaceholderAPI.isRegistered(identifier);
    }

    @Override
    public String translate(MinecraftPlayer player, String identifier, String parameter) {
        return PlaceholderAPI.getPlaceholders().get(identifier).onRequest(null,parameter);//@Todo map player
    }

    @Override
    public String replacePlaceholders(MinecraftPlayer player, String rawString) {
        return PlaceholderAPI.setPlaceholders(null,rawString);//@Todo map player
    }

    @Override
    public void replacePlaceholder(MinecraftPlayer player, TextComponent rawComponent) {

    }
}
