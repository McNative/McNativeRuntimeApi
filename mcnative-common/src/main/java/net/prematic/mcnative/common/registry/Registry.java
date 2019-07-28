/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 28.07.19 18:19
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

package net.prematic.mcnative.common.registry;

import java.util.function.Supplier;

public interface Registry {

    <T, S extends T> S getService(Class<T> serviceClass);

    default <T, S extends T> void registerService(Class<T> serviceClass, S service){
        registerService(ServicePriority.NORMAL,serviceClass,service);
    }

    <T, S extends T> void registerService(byte priority, Class<T> serviceClass, S service);

    void unregisterService(Class<?> serviceClass);

    void unregisterService(Object service);


    <T> T create(Class<T> classToCreate);

    <T> void registerCreator(Class<T> clazz, Supplier<T> creator);


}
