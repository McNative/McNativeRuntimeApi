package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.entity.living.Player;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.InventoryOwner;
import org.mcnative.runtime.api.service.inventory.gui.Gui;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultGui<C extends GuiContext> implements Gui<C> {

    private final String name;
    private final Constructor<C> constructor;
    private final Collection<Page<?>> pages;
    private final Collection<PlayerContextHolder> contexts;
    private final String defaultPage;

    public DefaultGui(String name,Class<C> rootClass, Collection<Page<?>> pages, String defaultPage) {
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
    public Collection<Page<?>> getPages() {
        return pages;
    }

    @Override
    public Page<?> getPage(String name) {
        return Iterators.findOne(this.pages, page -> page.getName().equalsIgnoreCase(name));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P extends GuiContext> Page<P> getPage(String name, Class<P> contextClass) {
        return (Page<P>) getPage(name);
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

        Page<?> nextPage;
        if(page != null) nextPage = getPage(page);
        else if(context.page != null) nextPage = context.page;
        else nextPage = getPage(defaultPage);

        if(nextPage == null) throw new IllegalArgumentException("Page "+page+" does not exist");

        Inventory inventory = context.inventory;
        if(inventory == null || inventory.getSize() != nextPage.getSize()) {
            inventory = Inventory.newInventory(Inventory.class,context, nextPage.getSize(), "Hallo");
            context.inventory = inventory;
        }
        context.page = nextPage;

        context.createPageContext();
        nextPage.render(context.getPageContext(),inventory);
        ((Player)(player)).openInventory(inventory);

        return context.context;
    }

    public final class PlayerContextHolder implements InventoryOwner {

        private final C context;
        private Page<?> page;
        private GuiContext pageContext;
        private Inventory inventory;

        public PlayerContextHolder(C context) {
            this.context = context;
        }

        @Override
        public Inventory getLinkedInventory() {
            return inventory;
        }

        public void handleClick(MinecraftPlayerInventoryClickEvent event){
            page.handleClick(getPageContext(),inventory,event);
        }

        @SuppressWarnings("unchecked")
        private  <P extends GuiContext > P getPageContext() {
            return (P) pageContext;
        }

        private void createPageContext(){
            pageContext = page.createContext(context);
        }
    }
}
