package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

public interface Element<C extends GuiContext,V> {

    void render(C context, Inventory inventory, int[] slot, V value);

}
