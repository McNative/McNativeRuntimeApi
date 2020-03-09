/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 09.03.20, 20:59
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

package org.mcnative.bukkit.serviceprovider.economy;

import net.milkbowl.vault.economy.Economy;
import net.prematic.libraries.event.Listener;
import net.prematic.libraries.utility.interfaces.ObjectOwner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.mcnative.bukkit.McNativeLauncher;
import org.mcnative.bukkit.plugin.BukkitPluginManager;
import org.mcnative.common.event.service.ServiceRegisterEvent;
import org.mcnative.common.event.service.ServiceUnregisterEvent;

public class ServiceListener {

    private final BukkitPluginManager pluginManager;

    public ServiceListener(BukkitPluginManager pluginManager) {
        this.pluginManager = pluginManager;

    }

    @Listener
    public void onServiceRegister(ServiceRegisterEvent event) {
        if(event.isService(VaultEconomyHook.class)) {
            VaultEconomyHook hook = (VaultEconomyHook) event.getService();
            Bukkit.getServer().getServicesManager().register(Economy.class, hook, findPlugin(event.getOwner()),
                    mapPriority(event.getServicePriority()));
        }
    }

    @Listener
    public void onServiceUnregister(ServiceUnregisterEvent event) {
        if(event.isService(VaultEconomyHook.class)) {
            Bukkit.getServer().getServicesManager().unregister(event.getService());
        }
    }

    private Plugin findPlugin(ObjectOwner objectOwner) {
        if(objectOwner instanceof net.prematic.libraries.plugin.Plugin<?>) {
            return this.pluginManager.getMappedPlugin((net.prematic.libraries.plugin.Plugin<?>) objectOwner);
        } else {
            return McNativeLauncher.getPlugin();
        }
    }

    private ServicePriority mapPriority(byte priority) {
        switch (priority) {
            case net.prematic.libraries.plugin.service.ServicePriority.HIGHEST : {
                return ServicePriority.Highest;
            }
            case net.prematic.libraries.plugin.service.ServicePriority.HIGH : {
                return ServicePriority.High;
            }
            case net.prematic.libraries.plugin.service.ServicePriority.LOW : {
                return ServicePriority.Low;
            }
            case net.prematic.libraries.plugin.service.ServicePriority.LOWEST : {
                return ServicePriority.Lowest;
            }
            default: {
                return ServicePriority.Normal;
            }
        }
    }
}
