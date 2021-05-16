package org.mcnative.runtime.api.player.client;

import net.pretronic.libraries.utility.annonations.NotNull;

public class DiscordRichPresence {

    private final String domain;
    private final String matchSecret;
    private final String spectateSecret;
    private final String joinSecret;

    public DiscordRichPresence(@NotNull String domain, String matchSecret, String spectateSecret, String joinSecret) {
        this.domain = domain;
        this.matchSecret = matchSecret;
        this.spectateSecret = spectateSecret;
        this.joinSecret = joinSecret;
    }

    public String getDomain() {
        return domain;
    }

    public String getMatchSecret() {
        return matchSecret;
    }

    public String getSpectateSecret() {
        return spectateSecret;
    }

    public String getJoinSecret() {
        return joinSecret;
    }


}
