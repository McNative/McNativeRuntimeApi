package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public abstract class BasicElement<C extends GuiContext, P extends PageContext<C>> implements Element<C,P> {

    private final int[] slots;

    public BasicElement(int[] slots) {
        this.slots = slots;
    }

    @Override
    public int[] getSlots() {
        return this.slots;
    }

    @Override
    public void render(P context) {
        for (int slot : getSlots()) {
            context.getLinkedInventory().setItem(slot,create(context));
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
