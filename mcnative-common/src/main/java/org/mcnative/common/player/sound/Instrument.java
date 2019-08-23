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

package org.mcnative.common.player.sound;

import net.prematic.libraries.utility.Iterators;

import java.util.ArrayList;
import java.util.Collection;

public class Instrument {

    private static final Collection<Instrument> INSTRUMENTS = new ArrayList<>();

    public static final Instrument PIANO = createDefault("PIANO");
    public static final Instrument BASS_DRUM = createDefault("BASS_DRUM");
    public static final Instrument SNARE_DRUM = createDefault("SNARE_DRUM");
    public static final Instrument STICKS = createDefault("STICKS");
    public static final Instrument BASS_GUITAR = createDefault("BASS_GUITAR");
    public static final Instrument FLUTE = createDefault("FLUTE");
    public static final Instrument BELL = createDefault("BELL");
    public static final Instrument GUITAR = createDefault("GUITAR");
    public static final Instrument CHIME = createDefault("CHIME");
    public static final Instrument XYLOPHONE = createDefault("XYLOPHONE");
    public static final Instrument IRON_XYLOPHONE = createDefault("IRON_XYLOPHONE");
    public static final Instrument COW_BELL = createDefault("COW_BELL");
    public static final Instrument DIDGERIDOO = createDefault("DIDGERIDOO");
    public static final Instrument BIT = createDefault("BIT");
    public static final Instrument BANJO = createDefault("BANJO");
    public static final Instrument PLING = createDefault("PLING");

    private final String name;

    public Instrument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Instrument byName(String name) {
        return Iterators.findOne(INSTRUMENTS, instrument -> instrument.getName().equals(name));
    }

    public static void register(Instrument instrument) {
        INSTRUMENTS.add(instrument);
    }

    public static void unregister(Instrument instrument) {
        INSTRUMENTS.remove(instrument);
    }

    public static void unregister(String instrumentName) {
        Iterators.remove(INSTRUMENTS, instrument -> instrument.getName().equals(instrumentName));
    }

    private static Instrument createDefault(String name) {
        Instrument instrument = new Instrument(name);
        INSTRUMENTS.add(instrument);
        return instrument;
    }
}