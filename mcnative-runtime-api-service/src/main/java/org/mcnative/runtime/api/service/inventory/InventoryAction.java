/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 21.03.20, 13:56
 * @web %web%
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

package org.mcnative.runtime.api.service.inventory;

//Copied from bukkit api
public enum InventoryAction {

    NOTHING,
    PICKUP_ALL,
    PICKUP_SOME,
    PICKUP_HALF,
    PICKUP_ONE,
    PLACE_ALL,
    PLACE_SOME,
    PLACE_ONE,
    SWAP_WITH_CURSOR,

    DROP_ALL_CURSOR,
    DROP_ONE_CURSOR,
    DROP_ALL_SLOT,
    DROP_ONE_SLOT,
    MOVE_TO_OTHER_INVENTORY,
    HOTBAR_MOVE_AND_READD,
    HOTBAR_SWAP,
    CLONE_STACK,
    COLLECT_TO_CURSOR,
    UNKNOWN;
}
