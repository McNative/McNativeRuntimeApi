package org.mcnative.loader.bridged.bukkit;

import net.md_5.bungee.api.ProxyServer;
import net.pretronic.libraries.plugin.lifecycle.Lifecycle;
import net.pretronic.libraries.plugin.lifecycle.LifecycleState;
import org.bukkit.plugin.Plugin;
import org.mcnative.common.plugin.MinecraftPlugin;

public class BukkitMinecraftPluginWrapper extends MinecraftPlugin {

    private final Plugin plugin;

    public BukkitMinecraftPluginWrapper(Plugin plugin) {
        this.plugin = plugin;
    }

    @Lifecycle(state=LifecycleState.LOAD)
    public void onLoad(LifecycleState state){
        plugin.onLoad();
    }

    @Lifecycle(state=LifecycleState.BOOTSTRAP)
    public void onBootstrap(LifecycleState state){
        plugin.onEnable();
    }

    @Lifecycle(state=LifecycleState.SHUTDOWN)
    public void onShutdown(LifecycleState state){
        plugin.onDisable();
    }
}
