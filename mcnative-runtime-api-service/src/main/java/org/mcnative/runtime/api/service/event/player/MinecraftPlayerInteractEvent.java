package org.mcnative.runtime.api.service.event.player;

import net.pretronic.libraries.event.Cancellable;
import org.mcnative.runtime.api.service.inventory.EquipmentSlot;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;
import org.mcnative.runtime.api.service.world.block.Block;
import org.mcnative.runtime.api.service.world.block.BlockAction;
import org.mcnative.runtime.api.service.world.block.BlockDirection;

public interface MinecraftPlayerInteractEvent extends MinecraftEntityPlayerEvent, Cancellable {

    BlockAction getAction();

    BlockDirection getBlockDirection();

    Block getClickedBlock();

    EquipmentSlot getHand();

    ItemStack getItem();

    boolean hasBlock();

    boolean hasItem();
}
