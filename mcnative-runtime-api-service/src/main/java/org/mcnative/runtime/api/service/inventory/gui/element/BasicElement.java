package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public abstract class BasicElement<C extends GuiContext, P extends ScreenContext<C>> implements Element<C,P> {

    private final int[] slots;

    public BasicElement(int[] slots) {
        this.slots = slots;
    }

    @Override
    public int[] getSlots(P context) {
        return this.slots;
    }

    @Override
    public void render(P context) {
        ItemStack stack = create(context);
        for (int slot : getSlots(context)) {
            context.getLinkedInventory().setItem(slot,stack);
        }
    }

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event) {
        //unused
    }

    @Override
    public void handleDrag(P context, MinecraftPlayerInventoryDragEvent event) {
        //unused
    }

    protected abstract ItemStack create(P context);
}
