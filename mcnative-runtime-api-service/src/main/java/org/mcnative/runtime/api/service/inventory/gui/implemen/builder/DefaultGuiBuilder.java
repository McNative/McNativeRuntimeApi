package org.mcnative.runtime.api.service.inventory.gui.implemen.builder;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.builder.ElementList;
import org.mcnative.runtime.api.service.inventory.gui.builder.GuiBuilder;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.implemen.DefaultPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class DefaultGuiBuilder<C extends GuiContext> implements GuiBuilder<C> {

    private final Class<C> rootContext;
    private final Collection<Page<C,?>> pages;
    private String defaultPage;

    public DefaultGuiBuilder(Class<C> rootContext) {
        this.rootContext = rootContext;
        this.pages = new ArrayList<>();
    }

    public Collection<Page<C,?>> getPages() {
        return pages;
    }

    public String getDefaultPage() {
        return defaultPage;
    }

    @Override
    public Page<C, PageContext<C>> createPage(String name, int size, Function<PageContext<C>, String> titleCreator, Consumer<ElementList<C, PageContext<C>>> elements) {
        return createPage(name,size, null, titleCreator, elements);
    }

    @Override
    public <P extends PageContext<C>> Page<C, P> createPage(String name, int size, Class<P> contextClass0, Function<P, String> titleCreator, Consumer<ElementList<C, P>> elements) {
        Class<P> contextClass = contextClass0;
        if(contextClass == null) {
           contextClass = getClazz( PageContext.class);
        }
        DefaultElementList<C,P> elementList = new DefaultElementList<>();
        elements.accept(elementList);
        Page<C,P> page = new DefaultPage<>(name,size,rootContext,contextClass,elementList.getElements(), titleCreator);
        this.pages.add(page);
        return page;
    }

    private <P> Class<P> getClazz(Class<?> clazz) {
        return (Class<P>) clazz;
    }

    @Override
    public void setDefaultPage(String page) {
        this.defaultPage = page;
    }
}
