package org.mcnative.runtime.api.service.inventory.gui.pane;

import net.pretronic.libraries.synchronisation.observer.Observable;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

import java.util.List;
import java.util.function.Function;

public class ListPane<C extends GuiContext,V> implements Pane<C,?,V> {

    private final Function<C,ListSource<V>> sourceProvider;
    private final Element<C,V> element;
    private

    public ListPane(Function<C,ListSource<V>> sourceProvider,Element<C,V> element){
        this.sourceProvider = sourceProvider;
        this.element = element;
    }

    @Override
    public void render(C context, Inventory inventory, int[] slots, Void unused) {
        ListSource<V> source = sourceProvider.apply(context);
        if(source instanceof Observable<?, ?>) {

            context.subsribe(element,Observable)

            Observable<?, V> observable = (Observable<?, V>) source;
            observable.subscribeObserver((vObservable, v) -> {

            });
        }
        int index = 0;
        for (V object : source.get()) {
            if(index >= slots.length) return;
            element.render(context,inventory, new int[]{slots[index]},object);
            index++;
        }
    }

    @Override
    public void handleClick(C context, int[] slots, MinecraftPlayerInventoryClickEvent event, Void value) {
        ListSource<V> source = sourceProvider.apply(context);

        int index = 0;
        for (int slot : slots) {
            if(slot == event.getRawSlot()) break;
            index++;
        }

        V object = source.getItem(index);
        element.handleClick(context, new int[]{event.getRawSlot()},event,object);
    }

    @Override
    public void handleDrag(C context, int[] slots, MinecraftPlayerInventoryDragEvent event, Void value) {

    }

    @Override
    public Element<C,V> getElement() {
        return this.element;
    }
}
