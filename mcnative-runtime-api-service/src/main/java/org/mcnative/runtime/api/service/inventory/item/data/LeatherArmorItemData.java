package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.text.format.TextColor;

public interface LeatherArmorItemData extends ItemData {

    TextColor getColor();

    LeatherArmorItemData setColor(TextColor color);
}
