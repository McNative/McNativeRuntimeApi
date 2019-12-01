/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 19:57
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

package org.mcnative.common.serviceprovider.placeholder;

import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.text.components.TextComponent;

import java.util.Collection;

//@Todo implement for multiple placeholders
public class GroupPlaceholderProvider implements PlaceholderProvider{

    private final String NAME = "McNative Placeholder Bridge";


    public Collection<PlaceholderProvider> getProviders(){
        return null;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Collection<String> getIdentifiers() {
        return null;
    }

    @Override
    public void registerPlaceHolders(String identifier, PlaceholderHook hook) {

    }

    @Override
    public void unregisterPlaceHolders(String identifier) {

    }

    @Override
    public boolean hasIdentifier(String identifier) {
        for (PlaceholderProvider provider : getProviders()) if(provider.hasIdentifier(identifier)) return true;
        return false;
    }

    @Override
    public String translate(MinecraftPlayer player, String identifier, String parameter) {
        return null;
    }

    @Override
    public String replacePlaceholders(MinecraftPlayer player, String rawString) {
        return null;
    }

    @Override
    public void replacePlaceholder(MinecraftPlayer player, TextComponent rawComponent) {

    }
}
