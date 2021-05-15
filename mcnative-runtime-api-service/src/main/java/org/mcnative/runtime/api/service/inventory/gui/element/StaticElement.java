package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public class StaticElement<C extends GuiContext> implements Element<C,Void> {

    private final ItemStack itemStack;

    public StaticElement(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void render(C context, Inventory inventory, int[] slots, Void value) {
        for (int slot : slots) inventory.setItem(slot,itemStack);
    }

    @Override
    public void handleClick(C context, MinecraftPlayerInventoryClickEvent event, Void value) {
        handleClick(context, event);
    }

    @Override
    public void handleDrag(C context, MinecraftPlayerInventoryDragEvent event, Void value) {
        handleDrag(context, event);
    }

    public void handleClick(C context, MinecraftPlayerInventoryClickEvent event) {
        //Unused
    }

    public void handleDrag(C context, MinecraftPlayerInventoryDragEvent event) {
        //Unused
    }
}
