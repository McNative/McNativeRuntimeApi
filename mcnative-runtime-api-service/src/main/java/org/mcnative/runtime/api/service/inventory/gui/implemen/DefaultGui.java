package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.entity.living.Player;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.Gui;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.Screen;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultGui<C extends GuiContext> implements Gui<C> {

    private final String name;
    private final Constructor<C> constructor;
    private final Collection<Page<C>> pages;
    private final Collection<Screen<C, ?>> screens;
    private final Collection<C> contexts;
    private final String defaultPage;

    public DefaultGui(String name, Class<C> rootClass, Collection<Page<C>> pages, Collection<Screen<C, ?>> screens, String defaultPage) {
        this.name = name;
        this.pages = pages;
        this.screens = screens;
        this.contexts = new ArrayList<>();

        try {
            constructor = rootClass.getDeclaredConstructor(Gui.class, ConnectedMinecraftPlayer.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("A McNative page context ("+rootClass+") requires an constructor with "+Gui.class+ " and " + ConnectedMinecraftPlayer.class+" as parameter");
        }
        this.defaultPage = defaultPage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Screen<C, ?>> getScreens() {
        return this.screens;
    }

    @Override
    public Screen<C, ?> getScreen(String name) {
        return Iterators.findOne(this.screens, screen -> screen.getName().equalsIgnoreCase(name));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P extends ScreenContext<C>> Screen<C, P> getScreen(String name, Class<P> contextClass) {
        return (Screen<C, P>) getScreen(name);
    }

    @Override
    public Collection<Page<C>> getPages() {
        return pages;
    }

    @Override
    public Page<C> getPage(String name) {
        return Iterators.findOne(this.pages, page -> page.getName().equalsIgnoreCase(name));
    }

    @Override
    public String getDefaultPage() {
        return this.defaultPage;
    }

    @Override
    public Collection<C> getContexts() {
        return this.contexts;
    }

    @Override
    public C getContext(ConnectedMinecraftPlayer player) {
        return Iterators.findOne(this.contexts, c -> c.getPlayer().getUniqueId().equals(player.getUniqueId()));
    }

    @Override
    public void destroyContext(ConnectedMinecraftPlayer player) {
        Iterators.removeOne(this.contexts, c -> c.getPlayer().getUniqueId().equals(player.getUniqueId()));
    }

    @Override
    public C open(ConnectedMinecraftPlayer player) {
        return open(player, null);
    }

    @Override
    public C open(ConnectedMinecraftPlayer player, String page) {
        C context = getOrCreateContext(player);
        if(page == null) {
            page = getDefaultPage();
        }

        Page<C> nextPage = getPage(page);
        if(nextPage == null) throw new IllegalArgumentException("Page "+page+" does not exist");

        String screenName = nextPage.open(context);
        openScreen(player, screenName, context);

        return context;
    }

    @Override
    public C openScreen(ConnectedMinecraftPlayer player, String screenName, Object... arguments) {
        C context = getOrCreateContext(player);
        openScreen(player, screenName, context, arguments);
        return context;
    }

    private C getOrCreateContext(ConnectedMinecraftPlayer player) {
        C context = getContext(player);
        if(context == null){
            try {
                context = constructor.newInstance(this, player);
                this.contexts.add(context);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new ReflectException(e);
            }
        }
        return context;
    }

    private void openScreen(ConnectedMinecraftPlayer player, String screenName, C context, Object... arguments) {
        Screen<C, ?> screen = getScreen(screenName);
        if(screen == null) throw new IllegalArgumentException("Screen "+screenName+" does not exist");
        ScreenContext<C> screenContext = screen.createContext(context, arguments);

        Inventory inventory = Inventory.newInventory(Inventory.class, screenContext, screen.getSize(), screen.createPageTitle(screenContext.getRawPageContext()));
        screenContext.setInventory(inventory);
        screen.render(screenContext.getRawPageContext());

        ((Player)(player)).openInventory(screenContext.getLinkedInventory());
    }
}
