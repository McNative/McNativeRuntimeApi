/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:45
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

package org.mcnative.service.material;

import net.prematic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.service.inventory.item.ItemStack;

public abstract class Enchantment {

    private final ObjectOwner owner;
    private final String name;
    private final int id, startLevel, maxLevel;

    public Enchantment(ObjectOwner owner, String name, int id, int startLevel, int maxLevel) {
        this.owner = owner;
        this.name = name;
        this.id = id;
        this.startLevel = startLevel;
        this.maxLevel = maxLevel;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStartLevel() {
        return startLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public abstract boolean canEnchant(ItemStack itemStack);

    public abstract boolean conflictsWith(Enchantment enchantment);

    //private static Enchantment createDefault(String name, int id, int maxLevel, Enchantment[] conflicts, )
}