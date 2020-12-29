/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 12:52
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

package org.mcnative.runtime.api.service.world;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.ArrayList;
import java.util.Collection;

public class WorldEnvironment {

    private static Collection<WorldEnvironment> ENVIRONMENTS = new ArrayList<>();

    public static WorldEnvironment EARTH = createDefault("Earth",0);
    public static WorldEnvironment NETHER = createDefault("Nether",-1);
    public static WorldEnvironment End = createDefault("End",1);

    private final ObjectOwner owner;
    private final String name;
    private final int id;

    public WorldEnvironment(ObjectOwner owner, String name, int id) {
        this.owner = owner;
        this.name = name;
        this.id = id;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    static WorldEnvironment byName(String name){
        return Iterators.findOne(ENVIRONMENTS, environment -> environment.getName().equalsIgnoreCase(name));
    }

    static WorldEnvironment byId(int id){
        return Iterators.findOne(ENVIRONMENTS, environment -> environment.getId() == id);
    }

    public static void register(WorldEnvironment environment) {
        ENVIRONMENTS.add(environment);
    }

    public static void unregister(WorldEnvironment environment) {
        ENVIRONMENTS.remove(environment);
    }

    public static void unregister(String soundName) {
        Iterators.remove(ENVIRONMENTS, environment -> environment.getName().equalsIgnoreCase(soundName));
    }

    private static WorldEnvironment createDefault(String name, int id){
        WorldEnvironment environment = new WorldEnvironment(ObjectOwner.SYSTEM,name,id);
        ENVIRONMENTS.add(environment);
        return environment;
    }

}
