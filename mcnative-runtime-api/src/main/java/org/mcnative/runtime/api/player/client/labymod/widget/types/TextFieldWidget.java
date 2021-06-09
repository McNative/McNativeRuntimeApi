package org.mcnative.runtime.api.player.client.labymod.widget.types;

import org.mcnative.runtime.api.player.client.labymod.widget.ValueContainerWidget;

public interface TextFieldWidget extends ValueContainerWidget<TextFieldWidget> {

    String getPlaceholder();

    TextFieldWidget setPlaceholder(String placeholder);


    int getMaxLength();

    TextFieldWidget setMaxLength(int maxLength);


    boolean isFocused();

    TextFieldWidget setFocused(boolean focused);
}
