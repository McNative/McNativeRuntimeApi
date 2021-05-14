package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.ElementHolder;

import java.util.Collection;

public interface Page<C extends GuiContext,P extends GuiContext> {

    String getName();

    int getSize();

    Collection<P> getContexts();

    P getContext(ConnectedMinecraftPlayer player);

    Collection<ElementHolder<P,?>> getElements();

    void render(C context,Inventory inventory);

    void destroyContext(ConnectedMinecraftPlayer player);
}
