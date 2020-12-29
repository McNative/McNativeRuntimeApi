/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 19.10.19, 20:29
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

package org.mcnative.runtime.api.serviceprovider.placeholder;

import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.player.MinecraftPlayer;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.Collection;

public interface PlaceholderProvider {

    String getName();

    Collection<String> getIdentifiers();

    boolean hasIdentifier(String identifier);


    void registerPlaceHolders(ObjectOwner owner,String identifier, PlaceholderHook hook);

    default void registerStaticPlaceHolder(ObjectOwner owner,String placeholder, String value){
        registerPlaceHolders(owner, placeholder, (PlaceholderReplacer) player -> value);
    }

    void unregisterPlaceHolders(String identifier);

    void unregisterPlaceHolders(ObjectOwner owner);

    String translate(MinecraftPlayer player, String identifier);

    String translate(MinecraftPlayer player, String identifier, String parameter);

    String replacePlaceholders(MinecraftPlayer player, String rawString);

    void replacePlaceholders(MinecraftPlayer player, MessageComponent<?> rawComponent);
}
