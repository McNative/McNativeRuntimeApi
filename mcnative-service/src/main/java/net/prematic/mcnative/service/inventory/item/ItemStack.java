package net.prematic.mcnative.service.inventory.item;

import net.prematic.mcnative.service.inventory.item.enchantment.Enchantment;
import net.prematic.mcnative.service.material.Material;

import java.util.Collection;

public interface ItemStack {

    Material getMaterial();

    Collection<Enchantment> getEntchantments();

    int getAmount();

    short getDurability();

    void setMaterial(Material material);

    void setAmount(int amount);

    void setDurability(short durability);

    void addEntchantment(Enchantment enchantment);

    void addEntchantment(Enchantment enchantment, int level);
}
