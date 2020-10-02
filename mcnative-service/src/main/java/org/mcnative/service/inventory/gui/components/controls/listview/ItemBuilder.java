package org.mcnative.service.inventory.gui.components.controls.listview;

import org.mcnative.service.inventory.item.ItemStack;

public interface ItemBuilder<T> {

    ItemStack create(T element);

}
