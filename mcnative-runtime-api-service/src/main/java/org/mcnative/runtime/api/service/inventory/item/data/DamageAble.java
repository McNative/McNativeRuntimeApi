package org.mcnative.runtime.api.service.inventory.item.data;

public interface DamageAble extends ItemData {

    int getDamage();

    boolean hasDamage();

    void setDamage(int damage);
}
