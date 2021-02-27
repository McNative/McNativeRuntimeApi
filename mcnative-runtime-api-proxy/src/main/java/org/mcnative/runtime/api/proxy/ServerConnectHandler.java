package org.mcnative.runtime.api.proxy;

import org.mcnative.runtime.api.network.component.server.MinecraftServer;
import org.mcnative.runtime.api.player.MinecraftPlayer;
import org.mcnative.runtime.api.text.components.MessageComponent;

public interface ServerConnectHandler {

    MinecraftServer getFallbackServer(MinecraftPlayer player, MinecraftServer kickedFrom);

    MessageComponent<?> getNoFallBackServerMessage(MinecraftPlayer player);

}
