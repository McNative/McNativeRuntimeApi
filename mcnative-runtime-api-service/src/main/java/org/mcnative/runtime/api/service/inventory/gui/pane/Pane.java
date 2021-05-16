package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

public interface Pane<C extends GuiContext, P extends PageContext<C>,V> extends Element<C, P, Void> {

    Element<C,P,V> getElement();
}
