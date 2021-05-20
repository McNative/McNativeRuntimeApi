package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;

public interface Element<C extends GuiContext, P extends PageContext<C>> {

    int[] getSlots();

    void render(P context);

    void handleClick(P context, MinecraftPlayerInventoryClickEvent event);

    void handleDrag(P context, MinecraftPlayerInventoryDragEvent event);
}
