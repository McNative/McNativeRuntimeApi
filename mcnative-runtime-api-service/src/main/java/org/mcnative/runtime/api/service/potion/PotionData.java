package org.mcnative.runtime.api.service.potion;

public interface PotionData {

    PotionEffectType getType();

    boolean isExtended();

    boolean isUpgraded();
}
