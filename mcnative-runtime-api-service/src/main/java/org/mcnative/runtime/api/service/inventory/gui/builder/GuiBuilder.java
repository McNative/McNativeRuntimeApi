package org.mcnative.runtime.api.service.inventory.gui.builder;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;

import java.util.function.Consumer;
import java.util.function.Function;

public interface GuiBuilder<C extends GuiContext> {

    Page<C, PageContext<C>> createPage(String name, int size, Function<PageContext<C>, String> titleCreator, Consumer<ElementList<C,PageContext<C>>> elements);

    <P extends PageContext<C>> Page<C,P> createPage(String name, int size, Class<P> contextClass, Function<P, String> titleCreator, Consumer<ElementList<C,P>> elements);

    void setDefaultPage(String page);
}
