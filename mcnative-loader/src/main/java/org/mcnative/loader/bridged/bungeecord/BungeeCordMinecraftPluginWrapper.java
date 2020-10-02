package org.mcnative.loader.bridged.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;
import net.pretronic.libraries.plugin.lifecycle.Lifecycle;
import net.pretronic.libraries.plugin.lifecycle.LifecycleState;
import org.mcnative.common.plugin.MinecraftPlugin;

public class BungeeCordMinecraftPluginWrapper extends MinecraftPlugin {

    private final Plugin plugin;

    public BungeeCordMinecraftPluginWrapper(Plugin plugin) {
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
