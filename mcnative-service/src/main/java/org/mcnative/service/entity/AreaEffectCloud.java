/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 29.08.19, 15:59
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

package org.mcnative.service.entity;

import org.mcnative.service.entity.projectile.ProjectileSource;

public interface AreaEffectCloud extends Entity {

    int getDuration();

    void setDuration(int duration);

    int getWaitTime();

    void setWaitTime(int waitTime);

    int getReapplicationDelay();

    void setReapplicationDelay(int delay);

    int getDurationOnUse();

    void setDurationOnUse(int duration);

    float getRadius();

    void setRadius(float radius);

    float getRadiusOnUse();

    void setRadiusOnUse(float radius);

    float getRadiusPerTick();

    void setRadiusPerTick(float radius);

    //Particle getParticle();

    //void setParticle(Particle particle);

    //<T> void setParticle(Particle particle, T data);

    //void setBasePotionData(PotionData data);

    //PotionData getBasePotionData();

    boolean hasCustomEffects();

    //List<PotionEffect> getCustomEffects();

    //boolean addCustomEffect(PotionEffect effect, boolean overwrite);

    //boolean removeCustomEffect(PotionEffectType type);

    //boolean hasCustomEffect(PotionEffectType type);

    void clearCustomEffects();

    //Color getColor();

    //void setColor(Color color);

    ProjectileSource getSource();

    void setSource(ProjectileSource source);
}