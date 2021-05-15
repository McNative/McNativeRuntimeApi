package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

public interface Element<C extends GuiContext,V> {

    void render(C context, Inventory inventory, int[] slots, V value);

    void handleClick(C context, MinecraftPlayerInventoryClickEvent event, V value);

    void handleDrag(C context, MinecraftPlayerInventoryDragEvent event, V value);
}
