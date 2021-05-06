package org.mcnative.runtime.api.service.inventory.item.data.banner;

import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.service.inventory.item.DyeColor;

public interface BannerPattern {

    DyeColor getColor();

    BannerPatternType getType();

    static BannerPattern newPattern(DyeColor color, BannerPatternType type) {
        return McNative.getInstance().getObjectFactory().createObject(BannerPattern.class, color, type);
    }
}
