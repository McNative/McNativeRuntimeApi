/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.03.20, 13:20
 * @web %web%
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

package org.mcnative.common.serviceprovider.message.builder;

import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.builder.BuildContext;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.player.MinecraftPlayer;

public class MinecraftBuildContext extends BuildContext {

    private final CommandSender sender;
    private final TextBuildType type;

    public MinecraftBuildContext(Language language, VariableSet variables, CommandSender sender,TextBuildType type) {
        super(language, variables);
        this.sender = sender;
        this.type = type;
    }

    public CommandSender getSender() {
        return sender;
    }

    public TextBuildType getType() {
        return type;
    }

    public MinecraftPlayer getPlayer(){
        if(sender instanceof  MinecraftPlayer) return (MinecraftPlayer) getSender();
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <N extends BuildContext> N getAs(Class<N> castedClass) {
        return (N) this;
    }
}
