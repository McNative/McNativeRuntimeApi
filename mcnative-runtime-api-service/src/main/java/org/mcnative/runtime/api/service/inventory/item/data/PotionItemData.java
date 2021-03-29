package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.service.potion.PotionEffect;
import org.mcnative.runtime.api.service.potion.PotionEffectType;

import java.util.List;
//@Todo finish
public interface PotionItemData extends ItemData {

    List<PotionEffect> getCustomEffects();

}
