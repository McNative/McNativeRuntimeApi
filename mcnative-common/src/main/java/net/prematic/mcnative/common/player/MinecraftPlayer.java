package net.prematic.mcnative.common.player;

import java.awt.*;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;

public interface MinecraftPlayer {

    String getClientVersion();

    String getName();

    String getDisplayName();

    UUID getUniqueId();

    Locale getLocale();

    int getPing();

    byte getViewDistance();

    boolean hasPermission(String permission);

    boolean hasPermissionGroup(String group);

    void sendMessage(String message);

    void sendMessage(TextComponent... components);

    void sendTitle(String title, String subTitle, short duration);

    void sendTitle(String TextComponent, TextComponent subTitle, short duration);

    void sendActionbar(TextComponent message, short duration);



    void sendData(String channel, InputStream stream);

    void sendData(String channel,String data);

    void sendData(String channel, byte[] data);
}
