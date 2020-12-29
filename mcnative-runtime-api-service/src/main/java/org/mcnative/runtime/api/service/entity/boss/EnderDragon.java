/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 30.08.19, 19:37
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

package org.mcnative.runtime.api.service.entity.boss;

public interface EnderDragon extends Boss {

    Phase getPhase();

    void setPhase(Phase phase);

    enum Phase {

        CIRCLING,
        STRAFING,
        FLY_TO_PORTAL,
        LAND_ON_PORTAL,
        LEAVE_PORTAL,
        BREATH_ATTACK,
        SEARCH_FOR_BREATH_ATTACK_TARGET,
        ROAR_BEFORE_ATTACK,
        CHARGE_PLAYER,
        DYING,
        HOVER
    }
}
