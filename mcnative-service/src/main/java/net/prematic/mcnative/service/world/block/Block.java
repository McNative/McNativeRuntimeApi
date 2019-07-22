package net.prematic.mcnative.service.world.block;

import net.prematic.mcnative.service.material.Material;
import net.prematic.mcnative.service.world.Location;

public interface Block {

    Material getMaterial();

    Location getLocation();
}
