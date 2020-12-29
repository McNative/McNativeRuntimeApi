package org.mcnative.runtime.api.event.player.design;

import org.mcnative.runtime.api.event.player.MinecraftPlayerEvent;
import org.mcnative.runtime.api.player.PlayerDesign;

public interface MinecraftPlayerDesignEvent extends MinecraftPlayerEvent {

    PlayerDesign getDesign();

    void setDesign(PlayerDesign design);

}
