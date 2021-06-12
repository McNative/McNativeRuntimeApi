package org.mcnative.runtime.api.player.client.labymod.widget.types;

import org.mcnative.runtime.api.player.client.labymod.widget.ContainerWidget;
import org.mcnative.runtime.api.text.format.TextColor;

public interface ColorPickerWidget extends ContainerWidget<ColorPickerWidget> {

    String getTitle();

    ColorPickerWidget setTitle(String title);


    TextColor getSelectedColor();

    ColorPickerWidget setSelectedColor(TextColor selectedColor);


    boolean isRgb();

    ColorPickerWidget setRgb(boolean rgb);
}
