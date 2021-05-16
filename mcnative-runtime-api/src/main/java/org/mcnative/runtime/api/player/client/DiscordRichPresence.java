package org.mcnative.runtime.api.player.client;

import java.util.UUID;

public interface DiscordRichPresence {

    void sendGameSecrets(String secret);

    void sendSpectateSecret(String secret);

    void sendJoinSecret(String secret);


    void sendGameInfo(String gamemode, long startTime, long endTime);


    void sendPartyInfo(UUID partyId, int size, int maxMembers);

    void sendRemovePartyInfo();



}
