package org.mcnative.runtime.api.service.inventory.gui.builder;

import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

public interface ElementList<C extends GuiContext, P extends PageContext<C>> {

    ElementList<C,P> addElement(Element<C,P> element);

}
