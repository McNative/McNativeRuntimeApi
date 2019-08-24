/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 13:47
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

package org.mcnative.service.world;

import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.interfaces.ObjectOwner;

import java.util.ArrayList;
import java.util.Collection;

public class PortalType {

    private static Collection<PortalType> PORTAL_TYPES = new ArrayList<>();

    public static PortalType NETHER = createDefault("Nether");
    public static PortalType ENDER = createDefault("Ender");

    private ObjectOwner owner;
    private String name;

    public PortalType(ObjectOwner owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    static PortalType byName(String name){
        return Iterators.findOne(PORTAL_TYPES, portalType -> portalType.getName().equalsIgnoreCase(name));
    }

    public static void register(PortalType portalType) {
        PORTAL_TYPES.add(portalType);
    }

    public static void unregister(PortalType portalType) {
        PORTAL_TYPES.remove(portalType);
    }

    public static void unregister(String name) {
        Iterators.remove(PORTAL_TYPES, portalType -> portalType.getName().equalsIgnoreCase(name));
    }

    private static PortalType createDefault(String name){
        PortalType portalType = new PortalType(ObjectOwner.SYSTEM,name);
        PORTAL_TYPES.add(portalType);
        return portalType;
    }
}
