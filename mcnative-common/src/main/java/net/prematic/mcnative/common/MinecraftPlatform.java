package net.prematic.mcnative.common;

import net.prematic.mcnative.common.protocol.MinecraftProtocolVersion;

import java.util.Collection;
import java.util.List;

public interface MinecraftPlatform {

    String getName();

    String getVersion();

    boolean isProxy();

    MinecraftProtocolVersion getProtocolVersion();

    Collection<MinecraftProtocolVersion> getJoinableProtocolVersions();

}
