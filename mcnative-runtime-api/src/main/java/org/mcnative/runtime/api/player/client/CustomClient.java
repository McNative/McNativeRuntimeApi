package org.mcnative.runtime.api.player.client;

public interface CustomClient {

    Class<LabyModClient> LABYMOD = LabyModClient.class;

    String getName();

    DiscordRichPresence getDiscordRichPresence();

    default boolean supportsDiscordRichPresence(){
        return getDiscordRichPresence() != null;
    }

    void sendCurrentGameModeInfo(String gamemode);

}
