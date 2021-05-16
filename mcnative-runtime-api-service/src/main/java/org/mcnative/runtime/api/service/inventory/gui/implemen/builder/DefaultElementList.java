package org.mcnative.runtime.api.service.inventory.gui.implemen.builder;

import org.mcnative.runtime.api.service.inventory.gui.builder.ElementList;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultElementList<C extends GuiContext> implements ElementList<C> {

    private final Collection<Element<C, ?>> elements = new ArrayList<>();

    public Collection<Element<C, ?>> getElements() {
        return elements;
    }

    @Override
    public ElementList<C> addElement(Element<C, ?> element) {
        this.elements.add(element);
        return this;//@Todo validate slots
    }
}
