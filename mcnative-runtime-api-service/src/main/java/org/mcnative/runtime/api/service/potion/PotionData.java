package org.mcnative.runtime.api.service.potion;

public interface PotionData {

    PotionEffectType getType();

    boolean isExtended();

    boolean isUpgraded();


    PotionData setType(PotionEffectType effectType);

    PotionData setExtended(boolean extended);

    PotionData setUpgraded(boolean upgraded);
}
