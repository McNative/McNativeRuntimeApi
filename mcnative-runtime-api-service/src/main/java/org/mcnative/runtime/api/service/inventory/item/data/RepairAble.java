package org.mcnative.runtime.api.service.inventory.item.data;

public interface RepairAble extends ItemData {

    int getRepairCost();

    boolean hasRepairCost();

    RepairAble setRepairCost(int cost);
}
