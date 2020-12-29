/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 09.03.20, 20:23
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

package org.mcnative.runtime.api.event.service.registry;

import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.event.MinecraftEvent;

public interface ServiceRegistryEvent extends MinecraftEvent {

    ObjectOwner getOwner();

    Object getService();

    <T> T getService(Class<T> serviceClass);

    Class<?> getServiceClass();

    Class<?> getServiceTypeClass();

    boolean isService(Class<?> serviceClass);

    boolean isServiceType(Class<?> serviceClass);
}
