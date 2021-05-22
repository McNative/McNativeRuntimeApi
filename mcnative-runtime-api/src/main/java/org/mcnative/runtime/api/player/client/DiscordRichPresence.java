package org.mcnative.runtime.api.player.client;

import java.util.UUID;

public interface DiscordRichPresence {

    void sendGameSecrets(String domain,String secret);

    void sendSpectateSecret(String domain,String secret);

    void sendJoinSecret(String domain,String secret);


    void sendGameInfo(String gamemode, long startTime, long endTime);

    void sendRemoveGameInfo();


    void sendPartyInfo(UUID partyId, int size, int maxMembers);

    void sendRemovePartyInfo();

}
