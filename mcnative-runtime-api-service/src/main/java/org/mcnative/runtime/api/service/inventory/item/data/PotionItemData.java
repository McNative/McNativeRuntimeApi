package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.service.potion.PotionData;
import org.mcnative.runtime.api.service.potion.PotionEffect;
import org.mcnative.runtime.api.service.potion.PotionEffectType;
import org.mcnative.runtime.api.text.format.TextColor;

import java.util.List;
import java.util.function.Consumer;

//@Todo finish
public interface PotionItemData extends ItemData {

    List<PotionEffect> getCustomEffects();

    PotionItemData addCustomEffect(PotionEffect effect, boolean overwrite);

    PotionItemData removeCustomEffect(PotionEffectType effectType);

    PotionItemData clearCustomEffects();

    boolean hasCustomEffects();

    boolean hasCustomEffect(PotionEffectType effectType);


    boolean hasColor();

    TextColor getColor();

    PotionItemData setColor(TextColor color);


    PotionData getBasePotionData();

    PotionData getBasePotionData(Consumer<PotionData> potionData);
}
