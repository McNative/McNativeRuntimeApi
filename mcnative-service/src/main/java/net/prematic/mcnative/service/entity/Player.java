package net.prematic.mcnative.service.entity;

import net.prematic.mcnative.common.player.MinecraftPlayer;
import net.prematic.mcnative.service.inventory.PlayerInventory;

public interface Player extends MinecraftPlayer, HumanEntity{

    PlayerInventory getInventory();

}
