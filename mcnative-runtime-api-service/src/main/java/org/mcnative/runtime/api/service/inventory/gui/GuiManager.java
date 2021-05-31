package org.mcnative.runtime.api.service.inventory.gui;

import org.mcnative.runtime.api.service.inventory.gui.builder.GuiBuilder;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

import java.util.Collection;
import java.util.function.Consumer;

public interface GuiManager {


    Collection<Gui<?>> getGuis();

    Gui<?> getGui(String name);

    <C extends GuiContext> Gui<C> getGui(String name, Class<C> contextClass);


    <C extends GuiContext> Gui<C> createGui(String name, Class<C> contextClass, Consumer<GuiBuilder<C>> builder);


    boolean deleteGui(Gui<?> gui);

    boolean deleteGui(String name);
}
