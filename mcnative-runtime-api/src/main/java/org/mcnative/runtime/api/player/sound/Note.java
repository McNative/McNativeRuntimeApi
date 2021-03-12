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

import net.pretronic.libraries.utility.Validate;

import java.util.HashMap;
import java.util.Map;

public class Note {

    private final byte note;

    public Note(int note) {
        Validate.isTrue(note >= 0 && note <= 24, "The note value has to be between 0 and 24.");
        this.note = (byte) note;
    }

    public Note(int octave, Tone tone, boolean sharped) {
        if (sharped && !tone.isSharpable()) {
            tone = Tone.values()[tone.ordinal() + 1];
            sharped = false;
        }
        Validate.isTrue(octave < 0 || octave > 2 || (octave == 2 && !(tone == Tone.F && sharped)), "Tone and octave have to be between F#0 and F#2");
        this.note = (byte) (octave * Tone.TONES_COUNT + tone.getId(sharped));
    }

    public byte getNote() {
        return note;
    }

    public static Note flat(int octave, Tone tone) {
        Validate.isTrue(octave != 2, "Octave cannot be 2 for flats");
        tone = tone == Tone.G ? Tone.F : Tone.values()[tone.ordinal() - 1];
        return new Note(octave, tone, tone.isSharpable());
    }

    public static Note sharp(int octave, Tone tone) {
        return new Note(octave, tone, true);
    }

    public static Note natural(int octave, Tone tone) {
        Validate.isTrue(octave != 2, "Octave cannot be 2 for naturals");
        return new Note(octave, tone, false);
    }

    public Note sharped() {
        Validate.isTrue(note < 24, "This note cannot be sharped because it is the highest known note!");
        return new Note(note + 1);
    }

    public Note flattened() {
        Validate.isTrue(note > 0, "This note cannot be flattened because it is the lowest known note!");
        return new Note(note - 1);
    }

    public int getOctave() {
        return note / Tone.TONES_COUNT;
    }

    private byte getToneByte() {
        return (byte) (note % Tone.TONES_COUNT);
    }

    public Tone getTone() {
        return Tone.getById(getToneByte());
    }

    public boolean isSharped() {
        byte note = getToneByte();
        return Tone.getById(note).isSharped(note);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + note;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Note other = (Note) obj;
        return note == other.note;
    }

    public enum Tone {
        G(0x1, true),
        A(0x3, true),
        B(0x5, false),
        C(0x6, true),
        D(0x8, true),
        E(0xA, false),
        F(0xB, true);

        private final boolean sharpable;
        private final byte id;

        private static final Map<Byte, Tone> BY_DATA = new HashMap<>();
        public static final byte TONES_COUNT = 12;

        Tone(int id, boolean sharpable) {
            this.id = (byte) (id % TONES_COUNT);
            this.sharpable = sharpable;
        }

        public byte getId() {
            return getId(false);
        }

        public byte getId(boolean sharped) {
            byte id = (byte) (sharped && sharpable ? this.id + 1 : this.id);

            return (byte) (id % TONES_COUNT);
        }

        public boolean isSharpable() {
            return sharpable;
        }

        public boolean isSharped(byte id) {
            if (id == getId(false)) {
                return false;
            } else if (id == getId(true)) {
                return true;
            } else {
                throw new IllegalArgumentException("The id isn't matching to the tone.");
            }
        }

        public static Tone getById(byte id) {
            return BY_DATA.get(id);
        }

        static {
            for (Tone tone : values()) {
                int id = tone.id % TONES_COUNT;
                BY_DATA.put((byte) id, tone);

                if (tone.isSharpable()) {
                    id = (id + 1) % TONES_COUNT;
                    BY_DATA.put((byte) id, tone);
                }
            }
        }
    }
}
