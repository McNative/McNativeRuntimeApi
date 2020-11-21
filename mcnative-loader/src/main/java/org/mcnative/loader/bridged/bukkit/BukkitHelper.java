package org.mcnative.loader.bridged.bukkit;

import net.pretronic.libraries.plugin.description.PluginDescription;
import net.pretronic.libraries.utility.exception.OperationFailedException;
import net.pretronic.libraries.utility.reflect.UnsafeInstanceCreator;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.mcnative.common.plugin.MinecraftPlugin;
import org.mcnative.loader.loaders.mcnative.McNativePluginLoader;

import java.io.InputStream;

public class BukkitHelper {

    public static PluginDescription readPluginDescription(InputStream stream){
        try {
            PluginDescriptionFile description = new PluginDescriptionFile(stream);
            System.out.println(description.getName());
            System.out.println(description.getMain());
            return new BukkitHybridPluginDescription(description);
        } catch (InvalidDescriptionException e) {
            throw new OperationFailedException(e);
        }
    }

    public static MinecraftPlugin constructPlugin(McNativePluginLoader loader, PluginDescription description){
        try {
            System.out.println(description.getMain().getMainClass("bukkit"));
            Class<?> pluginClass = loader.getClassLoader().loadClass(description.getMain().getMainClass("bukkit"));
            System.out.println(pluginClass);
            Object plugin = UnsafeInstanceCreator.newInstance(pluginClass);
            System.out.println(plugin);
            return new BukkitMinecraftPluginWrapper((Plugin) plugin);
        }catch (ClassNotFoundException e){
            throw new IllegalArgumentException("Main class not found");
        }
    }

}
