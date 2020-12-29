/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 23.08.19, 22:06
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

package org.mcnative.runtime.api.player.sound;

import net.pretronic.libraries.utility.Iterators;

import java.util.ArrayList;
import java.util.Collection;

public class SoundCategory {

    private static final Collection<SoundCategory> CATEGORIES = new ArrayList<>();

    public static final SoundCategory MASTER = createDefault(0,"MASTER");
    public static final SoundCategory MUSIC = createDefault(1,"MUSIC");
    public static final SoundCategory RECORDS = createDefault(2,"RECORDS");
    public static final SoundCategory WEATHER = createDefault(3,"WEATHER");
    public static final SoundCategory BLOCKS = createDefault(4,"BLOCKS");
    public static final SoundCategory HOSTILE = createDefault(5,"HOSTILE");
    public static final SoundCategory NEUTRAL = createDefault(6,"NEUTRAL");
    public static final SoundCategory PLAYERS = createDefault(7,"PLAYERS");
    public static final SoundCategory AMBIENT = createDefault(8,"AMBIENT");
    public static final SoundCategory VOICE = createDefault(9,"VOICE");

    private final int id;
    private final String name;

    public SoundCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static SoundCategory byName(String name) {
        return Iterators.findOne(CATEGORIES, category -> category.getName().equals(name));
    }

    public static void register(SoundCategory category) {
        CATEGORIES.add(category);
    }

    public static void unregister(SoundCategory category) {
        CATEGORIES.remove(category);
    }

    public static void unregister(String categoryName) {
        Iterators.remove(CATEGORIES, category -> category.getName().equals(categoryName));
    }

    private static SoundCategory createDefault(int id, String name) {
        SoundCategory category = new SoundCategory(id, name);
        CATEGORIES.add(category);
        return category;
    }
}
