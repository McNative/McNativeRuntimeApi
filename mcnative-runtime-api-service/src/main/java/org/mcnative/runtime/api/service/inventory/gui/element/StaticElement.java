package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public class StaticElement<C extends GuiContext, P extends PageContext<C>> implements Element<C,P,Void> {

    private final ItemStack itemStack;
    private final int[] slots;


    public StaticElement(ItemStack itemStack, int[] slots) {
        this.itemStack = itemStack;
        this.slots = slots;
    }

    @Override
    public int[] getSlots() {
        return this.slots;
    }

    @Override
    public void render(P context, Void value) {
        for (int slot : slots) context.getInventory().setItem(slot,itemStack);
    }

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event, Void value) {
        handleClick(context, event);
    }

    @Override
    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event, Void value) {
        handleDrag(context, event);
    }

    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event) {
        //Unused
    }

    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event) {
        //Unused
    }
}
