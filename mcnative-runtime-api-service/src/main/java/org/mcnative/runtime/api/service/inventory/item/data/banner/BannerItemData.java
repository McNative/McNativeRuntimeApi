package org.mcnative.runtime.api.service.inventory.item.data.banner;

import org.mcnative.runtime.api.service.inventory.item.DyeColor;
import org.mcnative.runtime.api.service.inventory.item.data.ItemData;

import java.util.List;

public interface BannerItemData extends ItemData {

    DyeColor getBaseColor();

    BannerItemData setBaseColor(DyeColor color);


    List<BannerPattern> getPatterns();

    BannerItemData setPatterns(List<BannerPattern> patterns);

    void setPattern(int index, BannerPattern pattern);

    BannerItemData addPattern(BannerPattern pattern);

    BannerItemData removePattern(BannerPattern pattern);

    BannerItemData removePattern(int index);
}
