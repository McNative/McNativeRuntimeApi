package org.mcnative.runtime.api.service.inventory.item.data.firework;

import java.util.List;

public interface FireworkItemData {

    List<FireworkEffect> getEffects();

    FireworkItemData setEffects(List<FireworkEffect> effects);

    FireworkItemData addEffect(FireworkEffect effect);

    FireworkEffect setEffect(int index, FireworkEffect effect);

    FireworkEffect clearEffects();


    int getPower();

    FireworkEffect setPower(int power);
}
