package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.ElementHolder;

import java.util.Collection;

public interface Page<C extends GuiContext> {

    String getName();

    int getSize();

    Collection<ElementHolder<C,?>> getElements();

    C createContext(GuiContext rootContext);

    void render(C context,Inventory inventory);

    void handleClick(C context, Inventory inventory, MinecraftPlayerInventoryClickEvent event);


}
