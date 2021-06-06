package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.entity.living.Player;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.Gui;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultGui<C extends GuiContext> implements Gui<C> {

    private final String name;
    private final Constructor<C> constructor;
    private final Collection<Page<C, ?>> pages;
    private final Collection<C> contexts;
    private final String defaultPage;

    public DefaultGui(String name,Class<C> rootClass, Collection<Page<C, ?>> pages, String defaultPage) {
        this.name = name;
        this.pages = pages;
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
    public Collection<Page<C, ?>> getPages() {
        return pages;
    }

    @Override
    public Page<C, ?> getPage(String name) {
        return Iterators.findOne(this.pages, page -> page.getName().equalsIgnoreCase(name));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P extends PageContext<C>> Page<C, P> getPage(String name, Class<P> contextClass) {
        return (Page<C, P>) getPage(name);
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
        return Iterators.findOne(this.contexts, c -> c.getPlayer().equals(player));
    }

    @Override
    public void destroyContext(ConnectedMinecraftPlayer player) {
        Iterators.removeOne(this.contexts, c -> c.getPlayer().equals(player));
    }

    @Override
    public C open(ConnectedMinecraftPlayer player) {
        return open(player, null);
    }

    @Override
    public C open(ConnectedMinecraftPlayer player, String page) {
        C context = getContext(player);
        if(context == null){
            try {
                context = constructor.newInstance(this, player);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new ReflectException(e);
            }
        }

        Page<C,?> nextPage;
        PageContext<? extends GuiContext> pageContext = null;
        if(page != null) {
            nextPage = getPage(page);

        } else if(context.getPageContext() != null) {
            nextPage = (Page<C, ?>) context.getPageContext().getPage();
            pageContext = context.getPageContext();
        } else {
            nextPage = getPage(defaultPage);
        }

        if(nextPage == null) throw new IllegalArgumentException("Page "+page+" does not exist");

        if(pageContext == null) {
            pageContext = nextPage.createContext(context);
            context.setPageContext(pageContext);

            Inventory inventory = Inventory.newInventory(Inventory.class, pageContext, nextPage.getSize(), nextPage.createPageTitle(pageContext.getRawPageContext()));
            pageContext.setInventory(inventory);

            nextPage.render(pageContext.getRawPageContext());
        }

        ((Player)(player)).openInventory(pageContext.getLinkedInventory());
        return context;
    }

    /*public final class PlayerContextHolder implements InventoryOwner {

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
            if(event.isTopInventory()){
                event.setCancelled(true);
                page.handleClick(getPageContext(),event);
            }
        }

        public void handleDrag(MinecraftPlayerInventoryDragEvent event){
            for (Integer rawSlot : event.getRawSlots()) {
                if(rawSlot < event.getInventory().getSize()) {
                    event.setCancelled(true);
                    page.handleDrag(getPageContext(),event);
                    return;
                }
            }
        }

        @SuppressWarnings("unchecked")
        private  <P extends GuiContext > P getPageContext() {
            return (P) pageContext;
        }

        private void createPageContext(){
            pageContext = page.createContext(context);
        }
    }*/
}
