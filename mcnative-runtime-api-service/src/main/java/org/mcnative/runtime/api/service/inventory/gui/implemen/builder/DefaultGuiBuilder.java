package org.mcnative.runtime.api.service.inventory.gui.implemen.builder;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.Screen;
import org.mcnative.runtime.api.service.inventory.gui.builder.ElementList;
import org.mcnative.runtime.api.service.inventory.gui.builder.GuiBuilder;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.service.inventory.gui.implemen.DefaultScreen;
import org.mcnative.runtime.api.service.inventory.gui.implemen.FunctionPage;
import org.mcnative.runtime.api.service.inventory.gui.implemen.SimplePage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class DefaultGuiBuilder<C extends GuiContext> implements GuiBuilder<C> {

    private final Class<C> rootContext;
    private final Collection<Page<C>> pages;
    private final Collection<Screen<C, ?>> screens;
    private String defaultPage;

    public DefaultGuiBuilder(Class<C> rootContext) {
        this.rootContext = rootContext;
        this.pages = new ArrayList<>();
        this.screens = new ArrayList<>();
    }

    public Collection<Page<C>> getPages() {
        return pages;
    }

    public Collection<Screen<C, ?>> getScreens() {
        return screens;
    }

    public String getDefaultPage() {
        return defaultPage;
    }

    @Override
    public Screen<C, ScreenContext<C>> createScreen(String name, int size, Function<ScreenContext<C>, String> titleCreator, Consumer<ElementList<C, ScreenContext<C>>> elements) {
        return createScreen(name,size, null, titleCreator, elements);
    }

    @Override
    public <P extends ScreenContext<C>> Screen<C, P> createScreen(String name, int size, Class<P> contextClass0, Function<P, String> titleCreator, Consumer<ElementList<C, P>> elements) {
        Class<P> contextClass = contextClass0;
        if(contextClass == null) {
           contextClass = getClazz( ScreenContext.class);
        }
        DefaultElementList<C,P> elementList = new DefaultElementList<>();
        elements.accept(elementList);
        Screen<C,P> screen = new DefaultScreen<>(name,size,rootContext,contextClass,elementList.getElements(), titleCreator);
        this.screens.add(screen);
        return screen;
    }

    @Override
    public Page<C> registerPage(String name, String screen) {
        Page<C> page = new SimplePage<>(name, screen);
        this.pages.add(page);
        return page;
    }

    @Override
    public Page<C> registerPage(String name, Function<C, String> screen) {
        Page<C> page = new FunctionPage<>(name, screen);
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
