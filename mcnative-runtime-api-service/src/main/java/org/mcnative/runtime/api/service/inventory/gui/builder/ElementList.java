package org.mcnative.runtime.api.service.inventory.gui.builder;

import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

public interface ElementList<C extends GuiContext> {

    ElementList<C> addElement(int[] slots, Element<C,?> element);

}
