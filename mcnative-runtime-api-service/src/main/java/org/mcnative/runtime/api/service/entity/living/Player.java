/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.02.20, 20:44
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

package org.mcnative.runtime.api.service.entity.living;

import net.pretronic.libraries.utility.annonations.Nullable;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.service.GameMode;
import org.mcnative.runtime.api.service.advancement.AdvancementProgress;
import org.mcnative.runtime.api.service.entity.Entity;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;
import org.mcnative.runtime.api.service.location.Location;
import org.mcnative.runtime.api.service.world.Effect;

public interface Player extends HumanEntity, OnlineMinecraftPlayer, ConnectedMinecraftPlayer {

    Inventory getEnderchestInventory();

    Location getBedSpawnLocation();

    void setBedSpawnLocation(Location location);


    void openBook(ItemStack book);


    void hide(OnlineMinecraftPlayer forPlayer);

    void show(OnlineMinecraftPlayer forPlayer);

    boolean canSee(OnlineMinecraftPlayer forPlayer);


    boolean isSneaking();

    void setSneaking(boolean sneak);


    boolean isSprinting();

    void setSprinting(boolean sprinting);


    boolean isSleepingIgnored();

    void setSleepingIgnored(boolean isSleeping);


    GameMode getGameMode();

    void setGameMode(GameMode mode);


    Location getCompassTarget();

    void setCompassTarget(Location location);


    long getPlayerTime();

    void setPlayerTime(long time, boolean relative);

    void resetPlayerTime();

    boolean isPlayerTimeRelative();


    //Weather


    float getExperience();

    void setExperience(float xp);

    void addExperience(float xp);

    void removeExperience(float xp);

    int getLevel();

    void setLevel(int level);

    void addLevel(int level);

    void removeLevel(int level);

    int getTotalExperience();

    void setTotalExperience(int exp);


    int getFoodLevel();

    void setFoodLevel(int food);

    float getSaturation();

    void setSaturation(float value);


    float getExhaustion();

    void setExhaustion(float value);


    boolean isAllowFlight();

    void setAllowFlight(boolean flight);

    boolean isFlying();

    void setFlying(boolean value);


    float getFlySpeed();

    void setFlySpeed(float value);

    float getWalkSpeed();

    void setWalkSpeed(float value);


    @Nullable
    Entity getSpectatorTarget();

    void setSpectatorTarget(Entity entity);


    void playEffect(Location location, Effect effect, int data);

    AdvancementProgress getAdvancementProgress();
}
    /*
    @Todo
    Time
    Weather
    Xp
     */
