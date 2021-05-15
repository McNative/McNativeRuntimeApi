package org.mcnative.runtime.api.service.inventory.gui.implemen.builder;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.builder.ElementList;
import org.mcnative.runtime.api.service.inventory.gui.builder.GuiBuilder;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.implemen.DefaultPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class DefaultGuiBuilder<C extends GuiContext> implements GuiBuilder<C> {

    private final Class<C> rootContext;
    private final Collection<Page<?>> pages;
    private String defaultPage;

    public DefaultGuiBuilder(Class<C> rootContext) {
        this.rootContext = rootContext;
        this.pages = new ArrayList<>();
    }

    public Collection<Page<?>> getPages() {
        return pages;
    }

    public String getDefaultPage() {
        return defaultPage;
    }

    @Override
    public Page<C> createPage(String name, int size, Consumer<ElementList<C>> elements) {
        return createPage(name,size, null,elements);
    }

    @Override
    public <P extends GuiContext> Page<P> createPage(String name, int size, Class<P> contextClass, Consumer<ElementList<P>> elements) {
        DefaultElementList<P> elementList = new DefaultElementList<>();
        elements.accept(elementList);
        Page<P> page = new DefaultPage<>(name,size,rootContext,contextClass,elementList.getElements());
        this.pages.add(page);
        return page;
    }

    @Override
    public void setDefaultPage(String page) {
        this.defaultPage = page;
    }
}
