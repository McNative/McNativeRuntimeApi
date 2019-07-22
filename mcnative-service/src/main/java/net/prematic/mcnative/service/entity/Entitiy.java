package net.prematic.mcnative.service.entity;

import net.prematic.mcnative.service.world.Location;
import net.prematic.mcnative.service.world.World;

public interface Entitiy {

    World getWorld();

    Location getLocation();

    void teleport(Location location);

    void teleport(int x,int y ,int z);

    void teleport(int x,int y ,int z, short pitch, short yaw);

    void teleport(World world, int x, int y , int z);

    void teleport(World world, int x,int y ,int z, short pitch, short yaw);

}
