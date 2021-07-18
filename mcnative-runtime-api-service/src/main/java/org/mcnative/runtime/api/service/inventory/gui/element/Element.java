package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.stream.StreamOptional;

public interface Element<C extends GuiContext, P extends ScreenContext<C>> {

    int[] getSlots(P context);

    void render(P context);

    void handleClick(P context, MinecraftPlayerInventoryClickEvent event);

    void handleDrag(P context, MinecraftPlayerInventoryDragEvent event);

    default <O> O subscribeAndGet(P context, StreamOptional<O> stream){
        return context.subscribeAndGet(this, stream, o -> render(context));
    }
}
