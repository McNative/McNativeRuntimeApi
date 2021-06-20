package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;

import java.util.Collection;

public interface Gui<C extends GuiContext> {

    String getName();


    Collection<Screen<C,?>> getScreens();

    Screen<C,?> getScreen(String name);

    <P extends ScreenContext<C>> Screen<C,P> getScreen(String name, Class<P> contextClass);


    Collection<Page<C>> getPages();

    Page<C> getPage(String name);

    String getDefaultPage();


    Collection<C> getContexts();

    C getContext(ConnectedMinecraftPlayer player);

    void destroyContext(ConnectedMinecraftPlayer player);


    C open(ConnectedMinecraftPlayer player);

    C open(ConnectedMinecraftPlayer player,String page);

    C openScreen(ConnectedMinecraftPlayer player, String screen, Object... arguments);
}
