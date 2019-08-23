/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.common.registry;

import net.prematic.libraries.utility.interfaces.ObjectOwner;

public interface Registry {

    String DEFAULT_INSTANCE = "DEFAULT";


    <T, S extends T> S getService(Class<T> serviceClass);

    default <T, S extends T> void registerService(ObjectOwner owner, Class<T> serviceClass, S service){
        registerService(owner,serviceClass,service,ServicePriority.NORMAL);
    }

    <T, S extends T> void registerService(ObjectOwner owner, Class<T> serviceClass, S service,byte priority);

    void unregisterService(Class<?> serviceClass);

    void unregisterService(Object service);

    void unregisterServices(ObjectOwner owner);



    default <T> T getInstance(Class<T> instanceClass){
        return getInstance(instanceClass,DEFAULT_INSTANCE);
    }

    <T> T getInstance(Class<T> instanceClass, String name);

    <T,O extends T> T registerInstance(Class<T> instanceClass, O instance);

}
