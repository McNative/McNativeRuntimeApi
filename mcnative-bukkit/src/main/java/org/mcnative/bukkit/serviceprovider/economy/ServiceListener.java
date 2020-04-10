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
import net.pretronic.libraries.event.Listener;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.mcnative.bukkit.McNativeLauncher;
import org.mcnative.bukkit.plugin.BukkitPluginManager;
import org.mcnative.common.McNative;
import org.mcnative.common.event.service.ServiceRegisterEvent;
import org.mcnative.common.event.service.ServiceUnregisterEvent;
import org.mcnative.common.serviceprovider.economy.EconomyProvider;

import java.util.HashMap;
import java.util.Map;

public class ServiceListener {

    private final BukkitPluginManager pluginManager;

    private Map<EconomyProvider,VaultEconomyHook> vaultEconomyProviders;

    public ServiceListener(BukkitPluginManager pluginManager) {
        this.pluginManager = pluginManager;
        this.vaultEconomyProviders= new HashMap<>();
    }

    @Listener
    public void onServiceRegister(ServiceRegisterEvent event) {
        if(event.isServiceType(EconomyProvider.class)){
            if(!event.isService(VaultEconomyProvider.class)){
                VaultEconomyHook hook = new VaultEconomyHook((EconomyProvider) event.getService());
                Bukkit.getServer().getServicesManager().register(Economy.class
                        ,hook
                        ,findPlugin(event.getOwner())
                        ,mapPriority(event.getServicePriority()));
                McNative.getInstance().getLogger().info("[McNative] Economy provider [{}] was hooked to vault",
                        event.getService().getClass().getSimpleName());
                this.vaultEconomyProviders.put(event.getService(EconomyProvider.class),hook);
            }
        }
    }

    @Listener
    public void onServiceUnregister(ServiceUnregisterEvent event) {
        if(event.isServiceType(EconomyProvider.class)) {
            if(event.getService() instanceof VaultEconomyHook) {
                VaultEconomyHook hook = this.vaultEconomyProviders.get(event.getService(EconomyProvider.class));
                if(hook != null){
                    Bukkit.getServer().getServicesManager().unregister(hook);
                    McNative.getInstance().getLogger().info("Economy provider [{}] was unhooked from vault",
                            event.getService().getClass().getSimpleName());
                }
            }
        }
    }

    private Plugin findPlugin(ObjectOwner objectOwner) {
        if(objectOwner instanceof net.pretronic.libraries.plugin.Plugin<?>) {
            return this.pluginManager.getMappedPlugin((net.pretronic.libraries.plugin.Plugin<?>) objectOwner);
        } else {
            return McNativeLauncher.getPlugin();
        }
    }

    private ServicePriority mapPriority(byte priority) {
        switch (priority) {
            case net.pretronic.libraries.plugin.service.ServicePriority.HIGHEST : {
                return ServicePriority.Highest;
            }
            case net.pretronic.libraries.plugin.service.ServicePriority.HIGH : {
                return ServicePriority.High;
            }
            case net.pretronic.libraries.plugin.service.ServicePriority.LOW : {
                return ServicePriority.Low;
            }
            case net.pretronic.libraries.plugin.service.ServicePriority.LOWEST : {
                return ServicePriority.Lowest;
            }
            default: {
                return ServicePriority.Normal;
            }
        }
    }
}
