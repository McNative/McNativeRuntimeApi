package org.mcnative.runtime.api.service.inventory.gui.context;

import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.InventoryOwner;
import org.mcnative.runtime.api.service.inventory.gui.Page;

public class PageContext<C extends GuiContext> implements InventoryOwner {

    private final C guiContext;
    private Inventory inventory;
    private final Page<C, ?> page;

    public PageContext(C guiContext, Page<C, ?> page) {
        this.guiContext = guiContext;
        this.page = page;
    }

    public C root() {
        return guiContext;
    }

    @SuppressWarnings("unchecked")
    public  <T extends PageContext<?>> T getRawPageContext() {
        return (T) this;
    }

    public Page<C, ?> getPage() {
        return page;
    }

    @Override
    public Inventory getLinkedInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
