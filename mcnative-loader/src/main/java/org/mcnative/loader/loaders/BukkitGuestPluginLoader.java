package org.mcnative.loader.loaders;

import net.pretronic.libraries.utility.exception.OperationFailedException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.mcnative.loader.bootstrap.BukkitMcNativePluginBootstrap;

import java.io.File;

public class BukkitGuestPluginLoader implements GuestPluginLoader {

    private final File location;
    private Plugin plugin;

    public BukkitGuestPluginLoader(File location){
        this.location = location;
    }

    @Override
    public Object getInstance() {
        return plugin;
    }

    @Override
    public String getLoadedVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public void handlePluginLoad() {
        try {
            plugin = Bukkit.getPluginManager().loadPlugin(location);
        } catch (InvalidPluginException | InvalidDescriptionException e) {
            throw new OperationFailedException(e);
        }
    }

    @Override
    public void handlePluginEnable() {
        Bukkit.getPluginManager().enablePlugin(plugin);
        Bukkit.getPluginManager().disablePlugin(BukkitMcNativePluginBootstrap.INSTANCE);
    }

    @Override
    public void handlePluginDisable() {

    }
}
