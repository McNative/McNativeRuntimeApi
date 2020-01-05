/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 13:27
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

package org.mcnative.common.player;

import java.util.Locale;

public class PlayerSettings {

    private final Locale locale;
    private final byte viewDistance;
    private final ChatMode chatMode;
    private final boolean chatColorsEnabled;
    private final SkinParts skinParts;
    private final MainHand mainHand;

    public PlayerSettings(Locale locale, byte viewDistance, ChatMode chatMode, boolean chatColorsEnabled, SkinParts skinParts, MainHand mainHand) {
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatMode = chatMode;
        this.chatColorsEnabled = chatColorsEnabled;
        this.skinParts = skinParts;
        this.mainHand = mainHand;
    }

    public Locale getLocale() {
        return locale;
    }

    public byte getViewDistance() {
        return viewDistance;
    }

    public ChatMode getChatMode() {
        return chatMode;
    }

    public boolean isChatColorsEnabled() {
        return chatColorsEnabled;
    }

    public SkinParts getSkinParts() {
        return skinParts;
    }

    public MainHand getMainHand() {
        return mainHand;
    }

    public enum ChatMode {
        SHOWN,

        COMMANDS_ONLY,

        HIDDEN;

        public static ChatMode of(int ordinal){
            for (ChatMode value : values()) if(value.ordinal() == ordinal) return value;
            throw new IllegalArgumentException("Invalid Ordinal");
        }
    }

    public enum MainHand {
        LEFT,

        RIGHT;

        public static MainHand of(int ordinal){
            for (MainHand value : values()) if(value.ordinal() == ordinal) return value;
            throw new IllegalArgumentException("Invalid Ordinal");
        }
    }

    public static class SkinParts {

        public static final SkinParts SKIN_SHOW_ALL = new SkinParts((byte)127);

        private final byte bitmask;

        public SkinParts(byte skinBitmask) {
            this.bitmask = skinBitmask;
        }

        public byte getBitmask() {
            return bitmask;
        }

        public boolean hasCape() {
            return (bitmask & 1) == 1;
        }

        public boolean hasJacket() {
            return ((bitmask >> 1) & 1) == 1;
        }

        public boolean hasLeftSleeve() {
            return ((bitmask >> 2) & 1) == 1;
        }

        public boolean hasRightSleeve() {
            return ((bitmask >> 3) & 1) == 1;
        }

        public boolean hasLeftPants() {
            return ((bitmask >> 4) & 1) == 1;
        }

        public boolean hasRightPants() {
            return ((bitmask >> 5) & 1) == 1;
        }

        public boolean hasHat() {
            return ((bitmask >> 6) & 1) == 1;
        }
    }
}
