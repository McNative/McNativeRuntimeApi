package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.Gui;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultGui<C extends Context> implements Gui<C> {

    private final String name;
    private final Constructor<C> constructor;
    private final Collection<Page<C,?>> pages;
    private final Collection<PlayerContextHolder> contexts;
    private final String defaultPage;

    public DefaultGui(String name,Class<C> rootClass, Collection<Page<C, ?>> pages, String defaultPage) {
        this.name = name;
        this.pages = pages;
        this.contexts = new ArrayList<>();

        try {
            constructor = rootClass.getDeclaredConstructor(ConnectedMinecraftPlayer.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("A McNative page context ("+rootClass+") requires an constructor with "+ConnectedMinecraftPlayer.class+" as parameter");
        }
        this.defaultPage = defaultPage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Page<C, ?>> getPages() {
        return pages;
    }

    @Override
    public Page<C, ?> getPage(String name) {
        return Iterators.findOne(this.pages, page -> page.getName().equalsIgnoreCase(name));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P extends Context> Page<C, P> getPage(String name, Class<P> contextClass) {
        return (Page<C, P>) getPage(name);
    }

    @Override
    public String getDefaultPage() {
        return this.defaultPage;
    }

    @Override
    public Collection<C> getContexts() {
        return Iterators.map(this.contexts, playerContextHolder -> playerContextHolder.context);
    }

    @Override
    public C getContext(ConnectedMinecraftPlayer player) {
        PlayerContextHolder contextHolder = getRawContext(player);
        return contextHolder != null ? contextHolder.context : null;
    }

    public PlayerContextHolder getRawContext(ConnectedMinecraftPlayer player) {
        return Iterators.findOne(this.contexts, c -> c.context.getPlayer().equals(player));
    }

    @Override
    public void destroyContext(ConnectedMinecraftPlayer player) {
        Iterators.removeOne(this.contexts, c -> c.context.getPlayer().equals(player));
    }

    @Override
    public C open(ConnectedMinecraftPlayer player) {
        return open(player, null);
    }

    @Override
    public C open(ConnectedMinecraftPlayer player, String page) {
        PlayerContextHolder context = getRawContext(player);
        if(context == null){
            try {
                context = new PlayerContextHolder(constructor.newInstance(player));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new ReflectException(e);
            }
        }

        String nextPage = page;
        if(nextPage == null) nextPage = context.lastPage;
        if(nextPage == null) nextPage = defaultPage;
        if(nextPage == null) throw new IllegalArgumentException("No page available");
        context.lastPage = nextPage;

        Page<C,?> realPage = getPage(nextPage);

        Inventory inventory = context.inventory;
        if(inventory == null || inventory.getSize() != realPage.getSize()) {
            inventory = Inventory.newInventory(Inventory.class,null, realPage.getSize(), "Hallo");
            context.inventory = inventory;
        }

        realPage.render(context.context,inventory);
        return context.context;
    }

    private final class PlayerContextHolder {

        private final C context;
        private String lastPage;
        private Inventory inventory;

        public PlayerContextHolder(C context) {
            this.context = context;
        }
    }
}
