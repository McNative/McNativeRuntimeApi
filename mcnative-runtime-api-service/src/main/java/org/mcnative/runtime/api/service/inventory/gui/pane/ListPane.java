package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

import java.util.function.Function;

public class ListPane<C extends GuiContext,P extends PageContext<C>,V> implements Pane<C,P,V> {

    private final Function<P,ListSource<V>> sourceProvider;
    private final Element<C,P,V> element;
    private final int[] slots;

    public ListPane(Function<P, ListSource<V>> sourceProvider, Element<C, P, V> element, int[] slots){
        this.sourceProvider = sourceProvider;
        this.element = element;
        this.slots = slots;
    }

    @Override
    public Element<C,P,V> getElement() {
        return this.element;
    }

    @Override
    public int[] getSlots() {
        return this.slots;
    }

    @Override
    public void render(P context, Void value) {
        ListSource<V> source = sourceProvider.apply(context);
        int index = 0;
        for (V object : source.get()) {
            if(index >= slots.length) return;
            element.render(context,object);
            index++;
        }
    }

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event, Void value) {
        ListSource<V> source = sourceProvider.apply(context);

        int index = 0;
        for (int slot : slots) {
            if(slot == event.getRawSlot()) break;
            index++;
        }

        V object = source.getItem(index);
        element.handleClick(context,event,object);
    }

    @Override
    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event, Void value) {

    }
}
