package org.mcnative.runtime.api.service.event.player;

import net.pretronic.libraries.event.Cancellable;
import org.mcnative.runtime.api.service.entity.Item;

public interface MinecraftPlayerDropItemEvent extends MinecraftEntityPlayerEvent, Cancellable {

    Item getItemDrop();
}
