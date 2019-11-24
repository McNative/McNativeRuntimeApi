/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 19.10.19, 20:19
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

package org.mcnative.common.hook.economy;

import org.mcnative.common.player.MinecraftPlayer;

import java.util.Collection;

public interface Economy {

    String getName();

    boolean hasBankSupport();


    String getCurrencyName();

    String getCurrencySingularName();

    String getCurrencyPluralName();

    String formatBalance(double balance);


    boolean hasAccount(MinecraftPlayer player);

    double getPlayerBalance(MinecraftPlayer player);

    boolean hasPlayerBalance(MinecraftPlayer player, double amount);

    EconomyResponse setPlayerBalance(MinecraftPlayer player, double amount);

    EconomyResponse withdrawPlayerBalance(MinecraftPlayer player, double amount);

    EconomyResponse depositPlayerBalance(MinecraftPlayer player, double amount);


    Collection<String> getBanks();

    boolean doesBankExist(String name);

    boolean createBank(String name);

    boolean createBank(String name, MinecraftPlayer owner);

    boolean deleteBank(String name);

    double getBankBalance(String name);

    boolean hasBankBalance(String name, double amount);

    EconomyResponse setBankBalance(String name, double amount);

    EconomyResponse withdrawBankBalance(String name, double amount);

    EconomyResponse depositBankBalance(String name, double amount);

    boolean isBankOwner(String name, MinecraftPlayer owner);

    boolean isBankMember(String name, MinecraftPlayer member);
}
