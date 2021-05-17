package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.player.MinecraftPlayer;
import org.mcnative.runtime.api.player.profile.GameProfile;

public interface SkullItemData extends ItemData {

    boolean hasOwner();

    MinecraftPlayer getOwningPlayer();

    GameProfile getGameProfile();

    SkullItemData setOwningPlayer(MinecraftPlayer player);

    SkullItemData setGameProfile(GameProfile profile);
}
