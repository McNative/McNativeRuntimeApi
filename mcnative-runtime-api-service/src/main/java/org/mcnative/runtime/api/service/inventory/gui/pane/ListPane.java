package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

import java.util.List;
import java.util.function.Function;

public class ListPane<C extends GuiContext,V> implements Pane<C,V> {

    private final Function<C,List<V>> source;
    private final Element<C,V> element;

    public ListPane(Function<C,List<V>> source,Element<C,V> element){
        this.source = source;
        this.element = element;
    }

    @Override
    public void render(C context, Inventory inventory, int[] slots, Void unused) {
        for (int slot : slots) {
            V object = source.apply(context).get(slot);
            element.render(context,inventory, new int[]{slot},object);
        }
    }

    @Override
    public void handleClick(C context, MinecraftPlayerInventoryClickEvent event, Void value) {

    }

    @Override
    public Element<C,V> getElement() {
        return this.element;
    }
}
