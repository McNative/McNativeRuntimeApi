package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

import java.util.Collection;

public interface Page<C extends GuiContext, P extends PageContext<C>> {

    String getName();

    int getSize();

    Collection<Element<C,?>> getElements();

    C createContext(GuiContext rootContext);

    void render(C context);

    void handleClick(C context, MinecraftPlayerInventoryClickEvent event);

    void handleDrag(C context, MinecraftPlayerInventoryDragEvent event);

}
