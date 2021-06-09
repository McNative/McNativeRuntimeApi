package org.mcnative.runtime.api.player.client.labymod;

public interface WidgetScreenLayout {

    int getSlotWidth();

    WidgetScreenLayout setSlotWidth(int slotWidth);

    int getSlotHeight();

    WidgetScreenLayout setSlotHeight(int slotHeight);

    int getSlotMarginX();

    WidgetScreenLayout setSlotMarginX(int slotMarginX);

    int getSlotMarginY();

    WidgetScreenLayout setSlotMarginY(int slotMarginY);

    int getBorderPaddingX();

    WidgetScreenLayout setBorderPaddingX(int borderPaddingX);

    int getBorderPaddingY();

    WidgetScreenLayout setBorderPaddingY(int borderPaddingY);

    double getFontSize();

    WidgetScreenLayout setFontSize(double fontSize);
}
