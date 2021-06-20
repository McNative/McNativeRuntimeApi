package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryCloseEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

import java.util.Collection;

public interface Screen<C extends GuiContext, P extends ScreenContext<C>> {

    String getName();

    int getSize();

    Collection<Element<C,P>> getElements();

    P createContext(C rootContext, Object... arguments);

    void render(P context);

    void handleClick(P context, MinecraftPlayerInventoryClickEvent event);

    void handleDrag(P context, MinecraftPlayerInventoryDragEvent event);

    void handleClose(P context, MinecraftPlayerInventoryCloseEvent event);

    String createPageTitle(P context);
}
