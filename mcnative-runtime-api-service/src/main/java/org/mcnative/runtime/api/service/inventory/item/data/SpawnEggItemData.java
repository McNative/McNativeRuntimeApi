package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.service.entity.Entity;

public interface SpawnEggItemData extends ItemData {

    Class<? extends Entity> getEntityType();

    SpawnEggItemData setEntityType(Class<? extends Entity> typeClass);
}
