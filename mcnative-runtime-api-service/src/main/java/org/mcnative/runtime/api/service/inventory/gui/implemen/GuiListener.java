package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.event.EventPriority;
import net.pretronic.libraries.event.Listener;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryDragEvent;
import org.mcnative.runtime.api.service.inventory.InventoryOwner;

public class GuiListener {

    @Listener(priority = EventPriority.LOW)
    public void onClick(MinecraftPlayerInventoryClickEvent event){
        InventoryOwner owner = event.getInventory().getOwner();
        if(owner instanceof DefaultGui.PlayerContextHolder){
            ((DefaultGui<?>.PlayerContextHolder) owner).handleClick(event);
        }
    }

    @Listener(priority = EventPriority.LOW)
    public void onDrag(MinecraftPlayerInventoryDragEvent event){
        InventoryOwner owner = event.getInventory().getOwner();
        if(owner instanceof DefaultGui.PlayerContextHolder){
            ((DefaultGui<?>.PlayerContextHolder) owner).handleDrag(event);
        }
    }

}
