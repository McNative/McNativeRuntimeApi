package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.inventory.gui.context.Context;

import java.util.Collection;

public interface Gui<C extends Context> {

    String getName();


    Collection<Page<C,?>> getPages();

    Page<C,?> getPage(String name);

    <P extends Context> Page<C,P> getPage(String name,Class<P> contextClass);

    String getDefaultPage();


    Collection<C> getContexts();

    C getContext(ConnectedMinecraftPlayer player);

    void destroyContext(ConnectedMinecraftPlayer player);


    C open(ConnectedMinecraftPlayer player);

    C open(ConnectedMinecraftPlayer player,String page);

}
