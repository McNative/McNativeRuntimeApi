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

package org.mcnative.service.inventory.item;

import net.prematic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.McNative;

import java.util.ArrayList;
import java.util.Collection;

public class ItemFlag {

    public static final Collection<ItemFlag> ITEM_FLAGS = new ArrayList<>();

    public static final ItemFlag HIDE_ENCHANTS = createAndRegister("HIDE_ENCHANTS");
    public static final ItemFlag HIDE_ATTRIBUTES = createAndRegister("HIDE_ATTRIBUTES");
    public static final ItemFlag HIDE_UNBREAKABLE = createAndRegister("HIDE_UNBREAKABLE");
    public static final ItemFlag HIDE_DESTROYS = createAndRegister("HIDE_DESTROYS");
    public static final ItemFlag HIDE_PLACED_ON = createAndRegister("HIDE_PLACED_ON");
    public static final ItemFlag HIDE_POTION_EFFECTS = createAndRegister("HIDE_POTION_EFFECTS");


    private final ObjectOwner owner;
    private final String name;

    public ItemFlag(ObjectOwner owner, String name) {
        this.name = name;
        this.owner = owner;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }


    public static ItemFlag create(String name) {
        return new ItemFlag(McNative.getInstance(), name);
    }

    public static ItemFlag register(ItemFlag itemFlag) {
        ITEM_FLAGS.add(itemFlag);
        return itemFlag;
    }

    public static ItemFlag createAndRegister(String name) {
        return register(create(name));
    }
}