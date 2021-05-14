package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

public class StaticPane<C extends GuiContext> implements Pane<C,Void> {

    private final Element<C,Void> element;

    public StaticPane(Element<C,Void> element) {
        this.element = element;
    }

    @Override
    public void render(C context, Inventory inventory, int[] slots, Void unused) {
        for (int slot : slots) {
            element.render(context,inventory, new int[]{slot},null);
        }
    }

    @Override
    public Element<C,Void> getElement() {
        return this.element;
    }
}
