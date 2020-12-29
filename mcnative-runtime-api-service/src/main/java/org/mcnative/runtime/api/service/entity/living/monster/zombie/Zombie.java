/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 30.08.19, 18:19
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

package org.mcnative.runtime.api.service.entity.living.monster.zombie;

import org.mcnative.runtime.api.service.entity.living.monster.Monster;

public interface Zombie extends Monster {

    boolean isBaby();

    void setBaby(boolean flag);

    boolean isZombieVillager();

    int getConversionTime();

    boolean isConverting();

    void setConversionTime(int time);

    boolean isDrowning();

    void stopDrowning();

    boolean isArmsRaised();

    void setArmsRaised(boolean raised);

    boolean shouldBurnInDay();

    void setShouldBurnInDay(boolean shouldBurnInDay);
}
