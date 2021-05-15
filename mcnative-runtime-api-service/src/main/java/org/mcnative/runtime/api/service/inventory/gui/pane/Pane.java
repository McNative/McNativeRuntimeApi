package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

public interface Pane<C extends GuiContext,V> extends Element<C,Void> {

    Element<C,V> getElement();
}
