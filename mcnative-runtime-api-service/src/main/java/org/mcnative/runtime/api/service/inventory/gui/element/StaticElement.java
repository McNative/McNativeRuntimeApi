package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.inventory.gui.context.Context;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public abstract class StaticElement<C extends Context> extends DynamicElement<C,Void> {

    protected abstract ItemStack create(C context);

    @Override
    protected ItemStack create(C context, Void value) {
        return create(context);
    }
}
