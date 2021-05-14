package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.reflect.ReflectException;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.ElementHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class DefaultPage<C extends GuiContext,P extends GuiContext> implements Page<C,P> {

    private final String name;
    private final int size;
    private final Constructor<P> constructor;
    private final Collection<P> contexts;
    private final Collection<ElementHolder<P,?>> elements;

    public DefaultPage(String name, int size, Class<C> rootContextClass, Class<P> contextClass, Collection<ElementHolder<P, ?>> elements) {
        this.name = name;
        this.size = size;
        this.elements = elements;

        this.contexts = new ArrayList<>();

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
    public Collection<P> getContexts() {
        return contexts;
    }

    @Override
    public P getContext(ConnectedMinecraftPlayer player) {
        return Iterators.findOne(contexts, p -> p.getPlayer().equals(player));
    }

    @Override
    public Collection<ElementHolder<P, ?>> getElements() {
        return elements;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void render(C context, Inventory inventory) {
        P newContext = null;
        if(constructor == null) newContext = (P) context;
        else{
            try {
                newContext = constructor.newInstance(context);
                this.contexts.add(newContext);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new ReflectException(e);
            }
        }
        for (ElementHolder<P, ?> element : elements) {
            element.getElement().render(newContext,inventory,element.getSlots(),null);
        }
    }

    @Override
    public void destroyContext(ConnectedMinecraftPlayer player) {
        Iterators.removeOne(this.contexts, p -> p.getPlayer().equals(player));
    }

}
