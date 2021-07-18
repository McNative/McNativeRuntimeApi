package org.mcnative.runtime.api.service.inventory.gui.builder;

import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

public interface ElementList<C extends GuiContext, P extends ScreenContext<C>> {

    ElementList<C,P> addElement(Element<C,P> element);

}
