package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

public class ElementHolder<C extends GuiContext,V> {

    private final byte id;
    private final int[] slots;
    private final Element<C,V> element;

    public ElementHolder(byte id,int[] slots, Element<C, V> element) {
        this.id = id;
        this.slots = slots;
        this.element = element;
    }

    public byte getId() {
        return id;
    }

    public Element<C, V> getElement() {
        return element;
    }

    public int[] getSlots() {
        return slots;
    }

}
