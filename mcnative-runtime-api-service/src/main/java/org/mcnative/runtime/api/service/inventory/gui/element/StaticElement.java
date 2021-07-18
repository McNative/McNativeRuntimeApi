package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public class StaticElement<C extends GuiContext, P extends ScreenContext<C>> implements Element<C,P> {

    private final ItemStack itemStack;
    private final int[] slots;

    public StaticElement(ItemStack itemStack, int[] slots) {
        this.itemStack = itemStack;
        this.slots = slots;
    }

    @Override
    public int[] getSlots(P context) {
        return this.slots;
    }

    @Override
    public void render(P context) {
        for (int slot : getSlots(context)) context.getLinkedInventory().setItem(slot,itemStack);
    }

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event) {
        //Unused if not implemented
    }

    @Override
    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event) {
        //Unused if not implemented
    }
}
