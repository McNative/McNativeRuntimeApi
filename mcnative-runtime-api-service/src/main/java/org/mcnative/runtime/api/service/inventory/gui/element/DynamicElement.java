package org.mcnative.runtime.api.service.inventory.gui.element;

import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;

public abstract class DynamicElement<C extends GuiContext, P extends PageContext<C>,V> implements Element<C,P,V> {

    private final int[] slots;

    protected DynamicElement(int[] slots) {
        this.slots = slots;
    }

    @Override
    public int[] getSlots() {
        return this.slots;
    }

    @Override
    public void render(P context, V value) {
        context.getLinkedInventory().setItem(getSlots()[0],create(context,value));
    }

    @Override
    public void handleClick(P context,  MinecraftPlayerInventoryClickEvent event, V value) {

    }

    @Override
    public void handleDrag(P context,  MinecraftPlayerInventoryDragEvent event, V value) {

    }

    protected abstract ItemStack create(P context, V value);
}
