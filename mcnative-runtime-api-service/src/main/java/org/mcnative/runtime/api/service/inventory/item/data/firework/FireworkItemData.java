package org.mcnative.runtime.api.service.inventory.item.data.firework;

import org.mcnative.runtime.api.service.inventory.item.data.ItemData;

import java.util.List;

public interface FireworkItemData extends ItemData {

    List<FireworkEffect> getEffects();

    FireworkItemData setEffects(List<FireworkEffect> effects);

    FireworkItemData addEffect(FireworkEffect effect);

    FireworkEffect setEffect(int index, FireworkEffect effect);

    FireworkEffect clearEffects();


    int getPower();

    FireworkEffect setPower(int power);
}
