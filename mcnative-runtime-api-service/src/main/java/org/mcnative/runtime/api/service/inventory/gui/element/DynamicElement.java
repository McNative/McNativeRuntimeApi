package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.Context;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public abstract class DynamicElement<C extends Context,V> implements Element<C,V> {

    @Override
    public void render(C context, Inventory inventory, int[] slot, V value) {
        inventory.setItem(slot[0],create(context,value));
    }

    protected abstract ItemStack create(C context,V value);
}
