package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.inventory.gui.context.Context;

public class ElementHolder<C extends Context,V> {

    private final int[] slots;
    private final Element<C,V> element;

    public ElementHolder(int[] slots, Element<C, V> element) {
        this.slots = slots;
        this.element = element;
    }

    public Element<C, V> getElement() {
        return element;
    }

    public int[] getSlots() {
        return slots;
    }

}
