package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

public interface Page<C extends GuiContext> {

    String getName();

    String open(C context);
}
