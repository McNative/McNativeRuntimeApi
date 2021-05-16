package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

public class DefaultPage<C extends GuiContext,P extends PageContext<C>> implements Page<C,P> {

    private final String name;
    private final int size;
    private final Constructor<P> constructor;
    private final Collection<Element<C,P,?>> elements;

    public DefaultPage(String name, int size, Class<C> rootContextClass, Class<P> contextClass, Collection<Element<C,P, ?>> elements) {
        this.name = name;
        this.size = size;
        this.elements = elements;

        try {
            constructor = contextClass.getDeclaredConstructor(GuiContext.class, Page.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("A McNative page context ("+contextClass+") requires an constructor with "+rootContextClass+" as parameter");
        }

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Collection<Element<C,P,?>> getElements() {
        return elements;
    }

    @Override
    public P createContext(C rootContext) {
        try {
            return constructor.newInstance(rootContext, this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

    @Override
    public void render(P context) {
        for (Element<C,P,?> element : elements) {
            element.render(context, null);
        }
    }

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event) {
        for (Element<C,P,?> element : elements) {
            for (int slot : element.getSlots()){
                if(slot == event.getRawSlot()){
                    element.handleClick(context,event,null);
                    return;
                }
            }
        }
    }

    @Override
    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event) {
        for (Integer inventorySlot : event.getRawSlots()) {
            outer:
            for (Element<C,P,?> element : elements) {
                for (int slot : element.getSlots()){
                    if(slot == inventorySlot){
                        element.handleDrag(context,event,null);
                        break outer;
                    }
                }
            }
        }
    }
}
