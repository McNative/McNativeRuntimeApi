package org.mcnative.runtime.api;

import net.pretronic.libraries.command.sender.CommandSender;
import org.mcnative.runtime.api.player.MinecraftPlayer;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

public interface RenderContext {

    MinecraftProtocolVersion getProtocolVersion();

    CommandSender getSender();

    default MinecraftPlayer getPlayer(){
        CommandSender sender = getSender();
        return sender instanceof MinecraftPlayer ? (MinecraftPlayer) sender : null;
    }

}
