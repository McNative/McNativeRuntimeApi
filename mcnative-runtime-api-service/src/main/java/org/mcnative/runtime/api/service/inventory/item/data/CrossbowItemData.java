package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.service.inventory.item.ItemStack;

import java.util.List;

public interface CrossbowItemData extends ItemData {

    List<ItemStack> getChargedProjectiles();

    boolean hasChargedProjectiles();

    CrossbowItemData setChargedProjectiles(List<ItemStack> projectiles);

    CrossbowItemData addChargedProjectile(ItemStack projectile);
}
