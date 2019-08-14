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

public interface PlayerSettings {

    Locale getLocale();

    byte getViewDistance();

    ChatMode getChatMode();

    MainHand getMainHand();

    SkinParts getSkinParts();

    boolean hasChatColors();

    enum ChatMode {
        SHOWN,
        COMMANDS_ONLY,
        HIDDEN
    }

    enum MainHand {
        LEFT,
        RIGHT
    }

    class SkinParts {

        private final byte bitmask;

        public SkinParts(byte skinBitmask) {
            this.bitmask = skinBitmask;
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
