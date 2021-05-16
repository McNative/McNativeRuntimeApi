package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;

public interface Element<C extends GuiContext, P extends PageContext<C>,V> {

    int[] getSlots();

    void render(P context, V value);

    void handleClick(P context, MinecraftPlayerInventoryClickEvent event, V value);

    void handleDrag(P context, MinecraftPlayerInventoryDragEvent event, V value);

    <T> handleStream(Stream<T> yy)

    void onUpdate(P context);
}
