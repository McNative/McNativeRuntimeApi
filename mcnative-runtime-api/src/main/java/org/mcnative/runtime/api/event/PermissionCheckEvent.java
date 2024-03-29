/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 05.01.20, 13:00
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

package org.mcnative.runtime.api.event;

import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.annonations.Nullable;
import org.mcnative.runtime.api.serviceprovider.permission.PermissionHandler;

public interface PermissionCheckEvent extends MinecraftEvent {

    CommandSender getSender();

    @Nullable
    PermissionHandler getHandler();

    boolean hasHandler();

    String getPermission();

    boolean hasPermission();

    void setHasPermission(boolean hasPermission);

}
