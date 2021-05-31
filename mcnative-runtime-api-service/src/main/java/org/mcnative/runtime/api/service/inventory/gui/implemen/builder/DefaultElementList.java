package org.mcnative.runtime.api.service.inventory.gui.implemen.builder;

import org.mcnative.runtime.api.service.inventory.gui.builder.ElementList;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultElementList<C extends GuiContext,P extends PageContext<C>> implements ElementList<C,P> {

    private final Collection<Element<C,P>> elements = new ArrayList<>();

    public Collection<Element<C, P>> getElements() {
        return elements;
    }

    @Override
    public ElementList<C, P> addElement(Element<C, P> element) {
        this.elements.add(element);
        return this;
    }
}
