package org.mcnative.loader.bridged.bukkit;

import net.pretronic.libraries.plugin.description.PluginDescription;
import net.pretronic.libraries.utility.exception.OperationFailedException;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.mcnative.common.plugin.MinecraftPlugin;
import org.mcnative.loader.GuestPluginLoader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class BukkitHelper {

    public static PluginDescription readPluginDescription(InputStream stream){
        try {
            PluginDescriptionFile description = new PluginDescriptionFile(stream);
            return new BukkitHybridPluginDescription(description);
        } catch (InvalidDescriptionException e) {
            throw new OperationFailedException(e);
        }
    }

    public static MinecraftPlugin constructPlugin(GuestPluginLoader loader,PluginDescription description){
        try {
            Class<?> pluginClass = loader.getClassLoader().loadClass(description.getMain().getMainClass("bukkit"));
            Object plugin = pluginClass.getDeclaredConstructor().newInstance();

            return new BukkitMinecraftPluginWrapper((Plugin) plugin);
        }catch (ClassNotFoundException e){
            throw new IllegalArgumentException("Main class not found");
        }catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new OperationFailedException(e);
        }
    }

}
