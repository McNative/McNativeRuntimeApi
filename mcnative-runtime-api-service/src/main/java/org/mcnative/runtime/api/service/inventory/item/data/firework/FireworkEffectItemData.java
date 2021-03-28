package org.mcnative.runtime.api.service.inventory.item.data.firework;

import org.mcnative.runtime.api.service.inventory.item.data.ItemData;

public interface FireworkEffectItemData extends ItemData {

    FireworkEffect getEffect();

    FireworkEffectItemData setEffect(FireworkEffect effect);
}
