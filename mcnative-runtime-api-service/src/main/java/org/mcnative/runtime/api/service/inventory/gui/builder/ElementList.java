package org.mcnative.runtime.api.service.inventory.gui.builder;

import org.mcnative.runtime.api.service.inventory.gui.context.Context;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

public interface ElementList<C extends Context> {

    ElementList<C> addElement(int[] slots, Element<C,?> element);

}
