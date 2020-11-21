package org.mcnative.loader.loaders;

import net.pretronic.libraries.utility.exception.OperationFailedException;
import net.pretronic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcnative.loader.bootstrap.BukkitMcNativePluginBootstrap;

import java.io.File;

public class BukkitGuestPluginLoader implements GuestPluginLoader {

    private final File location;
    private final File loaderLocation;
    private Plugin plugin;

    public BukkitGuestPluginLoader(File location,File loaderLocation){
        this.location = location;
        this.loaderLocation = loaderLocation;
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
            if(plugin == null) return;
            File folder = new File("plugins/"+plugin.getName());
            if(!folder.exists()) folder.mkdirs();
            ReflectionUtil.changeFieldValue(JavaPlugin.class,plugin,"dataFolder",folder);
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
