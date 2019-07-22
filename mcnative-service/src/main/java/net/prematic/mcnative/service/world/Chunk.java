package net.prematic.mcnative.service.world;

import net.prematic.mcnative.service.material.Material;

public interface Chunk {




    void fill(Material material);

    void fill(int z, Material material);

    void fill(int startZ,,int endZ, Material material);

    void clear();

}
