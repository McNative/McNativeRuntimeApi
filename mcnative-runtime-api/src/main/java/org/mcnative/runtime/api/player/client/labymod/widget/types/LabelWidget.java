package org.mcnative.runtime.api.player.client.labymod.widget.types;

import org.mcnative.runtime.api.player.client.labymod.widget.Widget;
import org.mcnative.runtime.api.text.components.MessageComponent;

public interface LabelWidget extends Widget<LabelWidget> {

    MessageComponent<?> getValue();

    void setValue(MessageComponent<?> value);


    int getAlignment();

    LabelWidget setAlignment(int alignment);


    double getScale();

    LabelWidget setScale(double scale);
}
