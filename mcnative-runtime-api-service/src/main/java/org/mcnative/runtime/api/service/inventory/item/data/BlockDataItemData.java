package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.service.inventory.item.material.Material;
import org.mcnative.runtime.api.service.world.block.data.BlockData;

public interface BlockDataItemData extends ItemData {

    BlockData getBlockData(Material material);

    boolean hasBlockData();

    BlockDataItemData setBlockData(BlockData blockData);
}
