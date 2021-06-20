package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryCloseEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.Screen;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.function.Function;

public class DefaultScreen<C extends GuiContext,P extends ScreenContext<C>> implements Screen<C,P> {

    private final String name;
    private final int size;
    private final Collection<Element<C,P>> elements;
    private final Function<P, String> titleCreator;
    private final Class<?> rootContextClass;
    private final Class<?> contextClass;

    public DefaultScreen(String name, int size, Class<C> rootContextClass, Class<P> contextClass, Collection<Element<C,P>> elements, Function<P, String> titleCreator) {
        this.name = name;
        this.size = size;
        this.elements = elements;
        this.titleCreator = titleCreator;
        this.rootContextClass = rootContextClass;
        this.contextClass = contextClass;
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
    public Collection<Element<C,P>> getElements() {
        return elements;
    }

    @Override
    public P createContext(C rootContext, Object... arguments) {
        try {
            return getConstructor(arguments).newInstance(rootContext, this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

    private Constructor<P> getConstructor(Object[] arguments) {
        outer:
        for (Constructor<?> constructor : contextClass.getDeclaredConstructors()) {
            int index = 0;
            for (Class<?> parameterType : constructor.getParameterTypes()) {
                if(index == 0 && parameterType != rootContextClass) {
                    continue outer;
                } else if(index == 1 && parameterType != Page.class) {
                    continue outer;
                } else {
                   Class<?> argumentType = arguments[index-2].getClass();
                   if(!argumentType.equals(parameterType)) continue outer;
                }
                index++;
            }
            return (Constructor<P>) constructor;
        }
        throw new IllegalArgumentException("A McNative page context ("+contextClass+") requires an constructor with "+rootContextClass+" as parameter");
    }

    @Override
    public void render(P context) {
        for (Element<C,P> element : elements) {
            element.render(context);
        }
    }

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event) {
        for (Element<C,P> element : elements) {
            for (int slot : element.getSlots()){
                if(slot == event.getRawSlot()){
                    event.setCancelled(true);
                    element.handleClick(context,event);
                    return;
                }
            }
        }
    }

    @Override
    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event) {
        for (Integer inventorySlot : event.getRawSlots()) {
            outer:
            for (Element<C,P> element : elements) {
                for (int slot : element.getSlots()){
                    if(slot == inventorySlot){
                        event.setCancelled(true);
                        element.handleDrag(context,event);
                        break outer;
                    }
                }
            }
        }
    }

    @Override
    public void handleClose(P context, MinecraftPlayerInventoryCloseEvent event) {
        context.destroy();
        //context.root().setScreenContext(null);
    }

    @Override
    public String createPageTitle(P context) {
        return this.titleCreator.apply(context);
    }
}
