package org.mcnative.runtime.api.player.client;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;

public interface CustomPluginMessageListener {

    void onReceive(ConnectedMinecraftPlayer player, String channel,byte[] message);

}
