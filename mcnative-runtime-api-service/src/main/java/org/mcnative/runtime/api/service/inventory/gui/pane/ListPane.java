package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;
import org.mcnative.runtime.api.stream.StreamOptional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ListPane<C extends GuiContext,P extends ScreenContext<C>,V> implements Element<C,P> {

    private final Function<P,ListSource<V>> sourceProvider;
    private final int[] slots;

    private final Map<P,Integer> temporarySlots;

    public ListPane(Function<P, ListSource<V>> sourceProvider, int[] slots){
        this.sourceProvider = sourceProvider;
        this.slots = slots;
        this.temporarySlots = new ConcurrentHashMap<>();
    }

    @Override
    public int[] getSlots() {
        return this.slots;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void render(P context) {
        context.clear(this);
        ListSource<V> source = sourceProvider.apply(context);
        StreamOptional<List<V>> stream = source.get();
        List<V> result = context.subscribeAndGet(this,stream, (Consumer) o -> renderInternal(context, (List<V>) o));
        renderInternal(context,result);
    }

    private void renderInternal(P context,List<V> entries){
        int index = 0;
        for (V object : entries) {
            int slot = slots[index];
            this.temporarySlots.put(context,slot);
            context.getLinkedInventory().setItem(slot,create(context,slot,object));
            index++;
        }

        this.temporarySlots.remove(context);

        if(index < slots.length){
            for (int i = index; i < slots.length; i++) {
                int slot = slots[i];
                context.getLinkedInventory().setItem(slot,null);
            }
        }
    }

    private void renderSlot(P context, int slot){
        context.getLinkedInventory().setItem(slot,null);
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

    @Override
    public <O> O subscribeAndGet(P context, StreamOptional<O> stream) {
        int slot = this.temporarySlots.get(context);
        return context.subscribeAndGet(this, stream, o -> renderSlot(context,slot));
    }

    protected abstract ItemStack create(P context, int slot, V value);

    public void handleClick(P context, MinecraftPlayerInventoryClickEvent even, V value) {

    }

}
