package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.event.EventPriority;
import net.pretronic.libraries.event.Listener;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryCloseEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.InventoryOwner;
import org.mcnative.runtime.api.service.inventory.gui.context.ScreenContext;

public class GuiListener {

    @Listener(priority = EventPriority.LOW)
    public void onClick(MinecraftPlayerInventoryClickEvent event){
        InventoryOwner owner = event.getInventory().getOwner();
        if(owner instanceof ScreenContext<?>){
            ScreenContext<?> screenContext = (ScreenContext<?>) owner;
            screenContext.getPage().handleClick(screenContext.getRawPageContext(), event);
        }
    }

    @Listener(priority = EventPriority.LOW)
    public void onDrag(MinecraftPlayerInventoryDragEvent event){
        InventoryOwner owner = event.getInventory().getOwner();
        if(owner instanceof ScreenContext<?>){
            ScreenContext<?> screenContext = (ScreenContext<?>) owner;
            screenContext.getPage().handleDrag(screenContext.getRawPageContext(), event);
        }
    }

    @Listener(priority = EventPriority.LOW)
    public void onDrag(MinecraftPlayerInventoryCloseEvent event){
        InventoryOwner owner = event.getInventory().getOwner();
        if(owner instanceof ScreenContext<?>){
            ScreenContext<?> screenContext = (ScreenContext<?>) owner;
            screenContext.getPage().handleClose(screenContext.getRawPageContext(), event);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends ScreenContext<?>> T getPageContext(ScreenContext<?> context) {
        return (T) context;
    }
}
