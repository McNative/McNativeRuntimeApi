package org.mcnative.runtime.api.service.inventory.gui.builder;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.Screen;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.function.Consumer;
import java.util.function.Function;

public interface GuiBuilder<C extends GuiContext> {

    Screen<C, ScreenContext<C>> createScreen(String name, int size, Function<ScreenContext<C>, MessageComponent<?>> titleCreator, Consumer<ElementList<C, ScreenContext<C>>> elements);

    <P extends ScreenContext<C>> Screen<C,P> createScreen(String name, int size, Class<P> contextClass, Function<P, MessageComponent<?>> titleCreator, Consumer<ElementList<C,P>> elements);

    Page<C> registerPage(String name, String screen);

    Page<C> registerPage(String name, Function<C, String> screen);

    void setDefaultPage(String page);
}
