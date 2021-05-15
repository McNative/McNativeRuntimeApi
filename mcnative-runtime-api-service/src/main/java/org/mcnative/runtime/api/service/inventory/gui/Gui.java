package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

import java.util.Collection;

public interface Gui<C extends GuiContext> {

    String getName();


    Collection<Page<?>> getPages();

    Page<?> getPage(String name);

    <P extends GuiContext> Page<P> getPage(String name, Class<P> contextClass);

    String getDefaultPage();


    Collection<C> getContexts();

    C getContext(ConnectedMinecraftPlayer player);

    void destroyContext(ConnectedMinecraftPlayer player);


    C open(ConnectedMinecraftPlayer player);

    C open(ConnectedMinecraftPlayer player,String page);

}
