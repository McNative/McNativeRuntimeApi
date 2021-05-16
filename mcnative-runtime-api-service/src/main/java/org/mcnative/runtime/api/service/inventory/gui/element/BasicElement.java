package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public abstract class BasicElement<C extends GuiContext, P extends PageContext<C>> implements Element<C,P,Void> {

    private final int[] slots;

    protected BasicElement(int[] slots) {
        this.slots = slots;
    }


    @Override
    public int[] getSlots() {
        return this.slots;
    }

    @Override
    public void render(P context, Void value) {
        for (int slot : getSlots()) {
            context.getLinkedInventory().setItem(slot,create(context));
        }
    }

    protected abstract ItemStack create(P context);

    @Override
    public void handleClick(P context, MinecraftPlayerInventoryClickEvent event, Void value) {
        handleClick(context,event);
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
