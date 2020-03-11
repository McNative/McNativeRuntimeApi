/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 12:05
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

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;

import java.util.ArrayList;
import java.util.Collection;

public class Difficulty {

    private static Collection<Difficulty> DIFFICULTIES = new ArrayList<>();

    private static Difficulty PEACEFUL = createDefault("Peaceful",0);
    private static Difficulty EASY = createDefault("Easy",1);
    private static Difficulty NORMAL = createDefault("Normal",2);
    private static Difficulty HARD = createDefault("Hard",3);


    private final ObjectOwner owner;
    private final String name;
    private final int value;

    public Difficulty(ObjectOwner owner, String name, int value) {
        this.owner = owner;
        this.name = name;
        this.value = value;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    static Difficulty byName(String name){
        return Iterators.findOne(DIFFICULTIES, difficulty -> difficulty.getName().equalsIgnoreCase(name));
    }

    static Difficulty byValue(int value){
        return Iterators.findOne(DIFFICULTIES, difficulty -> difficulty.getValue() == value);
    }

    public static void register(Difficulty difficulty) {
        DIFFICULTIES.add(difficulty);
    }

    public static void unregister(Difficulty difficulty) {
        DIFFICULTIES.remove(difficulty);
    }

    public static void unregister(String name) {
        Iterators.remove(DIFFICULTIES, difficulty -> difficulty.getName().equalsIgnoreCase(name));
    }

    private static Difficulty createDefault(String name, int id){
        Difficulty difficulty = new Difficulty(ObjectOwner.SYSTEM,name,id);
        DIFFICULTIES.add(difficulty);
        return difficulty;
    }

}
