package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

import java.util.function.Function;

public abstract class ListPane<C extends GuiContext,P extends PageContext<C>,V> implements Element<C,P> {

    private final Function<P,ListSource<V>> sourceProvider;
    private final int[] slots;

    public ListPane(Function<P, ListSource<V>> sourceProvider, int[] slots){
        this.sourceProvider = sourceProvider;
        this.slots = slots;
    }

    @Override
    public int[] getSlots() {
        return this.slots;
    }

    @Override
    public void render(P context) {
        ListSource<V> source = sourceProvider.apply(context);

        source.get().subscribeAndGet(entries -> {
            System.out.println("Render " + entries);
            int index = 0;
            for (V object : entries) {

                //if(index >= slots.length) return;
                int slot = slots[index];
                context.getLinkedInventory().setItem(slot,create(context,slot,object));
                index++;
            }

            if(index < slots.length){
                for (int i = index; i < slots.length; i++) {
                    int slot = slots[i];
                    context.getLinkedInventory().setItem(slot,null);
                }
            }
        });
    }

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event) {
        ListSource<V> source = sourceProvider.apply(context);
        int index = 0;
        for (int slot : slots) {
            if(slot == event.getRawSlot()) break;
            index++;
        }
        V object = source.getItem(index);
        if(object == null) return;
        handleClick(context,event,object);
    }

    @Override
    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event) {
        //Unused
    }

    protected abstract ItemStack create(P context, int slot, V value);

    public void handleClick(P context, MinecraftPlayerInventoryClickEvent even, V value) {

    }

}
