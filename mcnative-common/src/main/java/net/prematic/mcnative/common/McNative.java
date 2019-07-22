package net.prematic.mcnative.common;

import net.prematic.libraries.concurrent.TaskScheduler;

public interface McNative {

    MinecraftPlatform getPlatform();

    TaskScheduler getScheduler();

    //CommandManager getCommandManager();

    //EventManager getEventManager();

    //PluginManager gePluginManager();
}
