package org.mcnative.runtime.api.player.client;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.player.MinecraftPlayer;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.UUID;
import java.util.function.Consumer;

public interface LabyModClient extends CustomClient {

    void sendServerBanner(String imageUrl);

    void sendLanguageFlag(UUID playerId, String countryCode);

    void sendWatermark(boolean visible);


    void updateBalanceDisplay(EnumBalanceType type, int balance);


    void sendSubtitle(UUID target, double size, String value );

    void sendSubtitle(MinecraftPlayer target, double size, String value );

    default void sendSubtitle(MinecraftPlayer target, double size, MessageComponent<?> component){
        sendSubtitle(target,size,component,VariableSet.newEmptySet());
    }

    void sendSubtitle(MinecraftPlayer target, double size, MessageComponent<?> component, VariableSet variables);


    void sendToServer(String title, String address, boolean preview);

    void sendToServer(String title, String address, boolean preview, Consumer<Boolean> callback);


    void enableVoiceChat();

    void disableVoiceChat();

    void sendVoiceChatMuteInfo(UUID playerId,boolean muted);


    void sendLabyModData(String message, Document document);


    enum EnumBalanceType {
        CASH("cash"),
        BANK("bank");

        private final String key;

        EnumBalanceType(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
    }

}
