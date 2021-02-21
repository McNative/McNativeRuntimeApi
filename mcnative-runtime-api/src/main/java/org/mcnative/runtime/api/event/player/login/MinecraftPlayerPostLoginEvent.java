/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.10.19, 19:37
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

package org.mcnative.runtime.api.event.player.login;

import net.pretronic.libraries.event.network.NetworkEvent;
import net.pretronic.libraries.event.network.NetworkEventType;
import org.mcnative.runtime.api.event.player.MinecraftOnlinePlayerEvent;

@NetworkEvent(type = NetworkEventType.SELF_MANAGED,ignoreNetworkException = true)
public interface MinecraftPlayerPostLoginEvent extends MinecraftOnlinePlayerEvent {}
