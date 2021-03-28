package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.service.world.location.Location;

public interface CompassItemData extends ItemData {

    Location getLodestone();

    boolean hasLodestone();

    CompassItemData setLodestone(Location lodestone);


    boolean isLodestoneTracked();

    CompassItemData setLodestoneTracked(boolean tracked);
}
