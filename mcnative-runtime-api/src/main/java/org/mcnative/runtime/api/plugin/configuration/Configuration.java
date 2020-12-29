/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.12.19, 18:20
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

package org.mcnative.runtime.api.plugin.configuration;

import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.configuration.DefaultCommandConfiguration;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.text.components.MessageComponent;

public interface Configuration extends Document {

    String getName();

    ObjectOwner getOwner();

    default CommandConfiguration getCommandConfiguration(String key){
        return getObject(key,DefaultCommandConfiguration.class);
    }

    default MessageComponent<?> getText(String key){
        return getObject(key,MessageComponent.class);
    }

    boolean isFirstCreation();

    void load();

    void save();

    void load(Class<?> configurationClass);
}
