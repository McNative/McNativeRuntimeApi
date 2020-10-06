package org.mcnative.loader.bridged.bungeecord;

import net.pretronic.libraries.plugin.description.PluginDescription;
import net.pretronic.libraries.utility.exception.OperationFailedException;
import org.mcnative.common.plugin.MinecraftPlugin;
import org.mcnative.loader.GuestPluginLoader;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class BungeeCordHelper {

    private final static Yaml YAML;

    static {
        Constructor yamlConstructor = new Constructor();
        PropertyUtils propertyUtils = yamlConstructor.getPropertyUtils();
        propertyUtils.setSkipMissingProperties( true );
        yamlConstructor.setPropertyUtils( propertyUtils );
        YAML = new Yaml( yamlConstructor );
    }

    public static PluginDescription readPluginDescription(InputStream stream){
        net.md_5.bungee.api.plugin.PluginDescription desc = YAML.loadAs( stream, net.md_5.bungee.api.plugin.PluginDescription.class );
        return new BungeeCordHybridPluginDescription(desc,null);
    }

    public static MinecraftPlugin constructPlugin(GuestPluginLoader loader,PluginDescription description){
        try {
            Class<?> pluginClass = loader.getClassLoader().loadClass(description.getMain().getMainClass("bungeecord"));
            Object plugin = pluginClass.getDeclaredConstructor().newInstance();

            return new BungeeCordMinecraftPluginWrapper((Plugin) plugin);
        }catch (ClassNotFoundException e){
            throw new IllegalArgumentException("Main class not found");
        }catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new OperationFailedException(e);
        }
    }

}
