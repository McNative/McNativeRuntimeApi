/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.03.20, 19:13
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

import net.prematic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.McNative;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.text.components.MessageComponent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlaceholderService {

    public static Collection<String> getIdentifiers(){
        Set<String> result = new HashSet<>();
        for (PlaceholderProvider provider : getProviders()) result.addAll(provider.getIdentifiers());
        return result;
    }

    public static boolean hasIdentifier(String identifier){
        for (PlaceholderProvider provider : getProviders()) {
            if(provider.hasIdentifier(identifier)) return true;
        }
        return false;
    }


    public static void registerPlaceHolders(ObjectOwner owner, String identifier, PlaceholderHook hook){
        for (PlaceholderProvider provider : getProviders()) {
            provider.registerPlaceHolders(owner, identifier, hook);
        }
    }

    public static void registerStaticPlaceHolder(ObjectOwner owner,String placeholder, String value){
        for (PlaceholderProvider provider : getProviders()) {
            provider.registerStaticPlaceHolder(owner, placeholder, value);
        }
    }

    public static void unregisterPlaceHolders(String identifier){
        for (PlaceholderProvider provider : getProviders()) {
            provider.unregisterPlaceHolders(identifier);
        }
    }

    public static void unregisterPlaceHolders(ObjectOwner owner){
        for (PlaceholderProvider provider : getProviders()) {
            provider.unregisterPlaceHolders(owner);
        }
    }


    public static String translate(MinecraftPlayer player, String identifier, String parameter){
        for (PlaceholderProvider provider : getProviders()) {
            String result = provider.translate(player, identifier, parameter);
            if(result != null) return result;
        }
        return null;
    }

    public static String replacePlaceholders(MinecraftPlayer player, String input){
        String output  = input;
        for (PlaceholderProvider provider : getProviders()) {
            output = provider.replacePlaceholders(player,output);
        }
        return output;
    }

    public static void replacePlaceholders(MinecraftPlayer player, MessageComponent<?> rawComponent){
        for (PlaceholderProvider provider : getProviders()) {
            provider.replacePlaceholders(player,rawComponent);
        }
    }

    public static Collection<PlaceholderProvider> getProviders(){
        return McNative.getInstance().getRegistry().getServices(PlaceholderProvider.class);
    }

}
