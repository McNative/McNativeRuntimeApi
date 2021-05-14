package org.mcnative.runtime.api.service.inventory.gui.builder;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.Context;

import java.util.function.Consumer;

public interface GuiBuilder<C extends Context> {

    Page<C,C> createPage(String name, int size, Consumer<ElementList<C>> elements);

    <P extends Context> Page<C,P> createPage(String name, int size, Class<P> contextClass, Consumer<ElementList<P>> elements);

    void setDefaultPage(String page);
}
