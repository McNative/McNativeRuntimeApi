package org.mcnative.runtime.api.player.client;

import java.util.UUID;

public interface CustomClient {

    String getName();

    boolean supportsDiscordRichPresence();


    void sendDiscordRichPresenceGameSecrets();


    void sendDiscordRichPresenceAddGameInfo(String gamemode, long startTime, long endTime);

    void sendDiscordRichPresenceRemoveGameInfo();

    void sendJoinSecret();


    void sendDiscordRichPresencePartyInfo(UUID partyId, int size, int maxMembers);

    void sendDiscordRichPresenceRemovePartyInfo();


}
