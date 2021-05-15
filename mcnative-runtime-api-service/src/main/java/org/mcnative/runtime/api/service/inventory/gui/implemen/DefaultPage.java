package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.ElementHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class DefaultPage<C extends GuiContext> implements Page<C> {

    private final String name;
    private final int size;
    private final Constructor<C> constructor;
    private final Collection<ElementHolder<C,?>> elements;

    public DefaultPage(String name, int size, Class<?> rootContextClass, Class<C> contextClass, Collection<ElementHolder<C, ?>> elements) {
        this.name = name;
        this.size = size;
        this.elements = elements;

        if(contextClass == null){
            constructor = null;
        }else{
            try {
                constructor = contextClass.getDeclaredConstructor(rootContextClass);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("A McNative page context ("+contextClass+") requires an constructor with "+rootContextClass+" as parameter");
            }
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
    public Collection<ElementHolder<C, ?>> getElements() {
        return elements;
    }

    @SuppressWarnings("unchecked")
    @Override
    public C createContext(GuiContext rootContext) {
        if(constructor == null) return (C) rootContext;
        else{
            try {
                return constructor.newInstance(rootContext);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new ReflectException(e);
            }
        }
    }

    @Override
    public void render(C context, Inventory inventory) {
        for (ElementHolder<C, ?> element : elements) {
            element.getElement().render(context,inventory,element.getSlots(),null);
        }
    }

    @Override
    public void handleClick(C context, Inventory inventory, MinecraftPlayerInventoryClickEvent event) {
        for (ElementHolder<C, ?> holder : elements) {
            for (int slot : holder.getSlots()){
                if(slot == event.getSlot()){
                    holder.getElement().handleClick(context,event,null);
                    return;
                }
            }
        }
    }

    @Override
    public void handleDrag(C context, Inventory inventory, MinecraftPlayerInventoryDragEvent event) {
        for (Integer inventorySlot : event.getInventorySlots()) {
            outer:
            for (ElementHolder<C, ?> holder : elements) {
                for (int slot : holder.getSlots()){
                    if(slot == inventorySlot){
                        holder.getElement().handleDrag(context,event,null);
                        break outer;
                    }
                }
            }
        }
    }

}
