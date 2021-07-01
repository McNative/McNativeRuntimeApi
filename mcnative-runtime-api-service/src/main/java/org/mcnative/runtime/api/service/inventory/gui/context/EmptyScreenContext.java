package org.mcnative.runtime.api.service.inventory.gui.context;

import org.mcnative.runtime.api.service.inventory.gui.Screen;

public class EmptyScreenContext extends ScreenContext<GuiContext> {

    public EmptyScreenContext(GuiContext guiContext, Screen<GuiContext, ?> page) {
        super(guiContext, page);
    }
}
