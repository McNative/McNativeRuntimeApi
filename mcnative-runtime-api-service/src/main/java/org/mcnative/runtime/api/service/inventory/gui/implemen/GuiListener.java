package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.event.EventPriority;
import net.pretronic.libraries.event.Listener;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.InventoryOwner;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;

public class GuiListener {

    @Listener(priority = EventPriority.LOW)
    public void onClick(MinecraftPlayerInventoryClickEvent event){
        InventoryOwner owner = event.getInventory().getOwner();
        if(owner instanceof PageContext<?>){
            PageContext<?> pageContext = (PageContext<?>) owner;
            pageContext.getPage().handleClick(pageContext.getRawPageContext(), event);
        }
    }

    @Listener(priority = EventPriority.LOW)
    public void onDrag(MinecraftPlayerInventoryDragEvent event){
        InventoryOwner owner = event.getInventory().getOwner();
        if(owner instanceof PageContext<?>){
            PageContext<?> pageContext = (PageContext<?>) owner;
            pageContext.getPage().handleDrag(pageContext.getRawPageContext(), event);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends PageContext<?>> T getPageContext(PageContext<?> context) {
        return (T) context;
    }
}
