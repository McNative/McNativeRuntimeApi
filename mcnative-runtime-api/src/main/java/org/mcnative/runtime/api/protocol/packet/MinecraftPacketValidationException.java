package org.mcnative.runtime.api.protocol.packet;

public class MinecraftPacketValidationException extends RuntimeException{

    public MinecraftPacketValidationException(String message) {
        super(message);
    }

    public MinecraftPacketValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinecraftPacketValidationException(Throwable cause) {
        super(cause);
    }

    public MinecraftPacketValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
