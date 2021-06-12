package org.mcnative.runtime.api.player.client.labymod.widget.types;

import org.mcnative.runtime.api.player.client.labymod.widget.ValueContainerWidget;

public interface ButtonWidget extends ValueContainerWidget<ButtonWidget> {

    boolean isCloseScreenOnClick();

    ButtonWidget setCloseScreenOnClick(boolean closeScreenOnClick);
}
