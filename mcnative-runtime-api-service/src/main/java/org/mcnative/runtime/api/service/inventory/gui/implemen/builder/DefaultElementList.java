package org.mcnative.runtime.api.service.inventory.gui.implemen.builder;

import org.mcnative.runtime.api.service.inventory.gui.builder.ElementList;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;
import org.mcnative.runtime.api.service.inventory.gui.element.ElementHolder;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultElementList<C extends GuiContext> implements ElementList<C> {

    private final Collection<ElementHolder<C, ?>> elements = new ArrayList<>();

    public Collection<ElementHolder<C, ?>> getElements() {
        return elements;
    }

    @Override
    public ElementList<C> addElement(int[] slots, Element<C, ?> element) {
        this.elements.add(new ElementHolder<>(slots,element));
        return this;//@Todo validate slots
    }
}
