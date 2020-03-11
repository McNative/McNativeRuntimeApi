/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:45
 *
 * The McNative Project is under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.mcnative.service.inventory.item.material;

import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.McNative;
import org.mcnative.service.NamespacedKey;
import org.mcnative.service.inventory.item.data.ItemData;
import org.mcnative.service.world.block.data.BlockData;

import java.util.ArrayList;
import java.util.Collection;

public class Material implements NamespacedKey {

    public static final Collection<Material> MATERIALS = new ArrayList<>();

    public static final Material ACACIA_BOAT = create("ACACIA_BOAT").buildAndRegister();
    public static final Material ACACIA_BUTTON = create("ACACIA_BUTTON").buildAndRegister();
    public static final Material ACACIA_DOOR = create("ACACIA_DOOR").buildAndRegister();
    public static final Material ACACIA_FENCE = create("ACACIA_FENCE").buildAndRegister();
    public static final Material ACACIA_FENCE_GATE = create("ACACIA_FENCE_GATE").buildAndRegister();
    public static final Material ACACIA_LEAVES = create("ACACIA_LEAVES").buildAndRegister();
    public static final Material ACACIA_LOG = create("ACACIA_LOG").buildAndRegister();
    public static final Material ACACIA_PLANKS = create("ACACIA_PLANKS").buildAndRegister();
    public static final Material ACACIA_PRESSURE_PLATE = create("ACACIA_PRESSURE_PLATE").buildAndRegister();
    public static final Material ACACIA_SAPLING = create("ACACIA_SAPLING").buildAndRegister();
    public static final Material ACACIA_SLAB = create("ACACIA_SLAB").buildAndRegister();
    public static final Material ACACIA_STAIRS = create("ACACIA_STAIRS").buildAndRegister();
    public static final Material ACACIA_TRAPDOOR = create("ACACIA_TRAPDOOR").buildAndRegister();
    public static final Material ACACIA_WOOD = create("ACACIA_WOOD").buildAndRegister();
    public static final Material ACTIVATOR_RAIL = create("ACTIVATOR_RAIL").buildAndRegister();
    public static final Material AIR = create("AIR").buildAndRegister();
    public static final Material ALLIUM = create("ALLIUM").buildAndRegister();
    public static final Material ANDESITE = create("ANDESITE").buildAndRegister();
    public static final Material ANVIL = create("ANVIL").buildAndRegister();
    public static final Material APPLE = create("APPLE").buildAndRegister();
    public static final Material ARMOR_STAND = create("ARMOR_STAND").buildAndRegister();
    public static final Material ARROW = create("ARROW").buildAndRegister();
    public static final Material ATTACHED_MELON_STEM = create("ATTACHED_MELON_STEM").buildAndRegister();
    public static final Material ATTACHED_PUMPKIN_STEM = create("ATTACHED_PUMPKIN_STEM").buildAndRegister();
    public static final Material AZURE_BLUET = create("AZURE_BLUET").buildAndRegister();
    public static final Material BAKED_POTATO = create("BAKED_POTATO").buildAndRegister();
    public static final Material BARRIER = create("BARRIER").buildAndRegister();
    public static final Material BAT_SPAWN_EGG = create("BAT_SPAWN_EGG").buildAndRegister();
    public static final Material BEACON = create("BEACON").buildAndRegister();
    public static final Material BEDROCK = create("BEDROCK").buildAndRegister();
    public static final Material BEEF = create("BEEF").buildAndRegister();
    public static final Material BEETROOT = create("BEETROOT").buildAndRegister();
    public static final Material BEETROOTS = create("BEETROOTS").buildAndRegister();
    public static final Material BEETROOT_SEEDS = create("BEETROOT_SEEDS").buildAndRegister();
    public static final Material BEETROOT_SOUP = create("BEETROOT_SOUP").buildAndRegister();
    public static final Material BIRCH_BOAT = create("BIRCH_BOAT").buildAndRegister();
    public static final Material BIRCH_BUTTON = create("BIRCH_BUTTON").buildAndRegister();
    public static final Material BIRCH_DOOR = create("BIRCH_DOOR").buildAndRegister();
    public static final Material BIRCH_FENCE = create("BIRCH_FENCE").buildAndRegister();
    public static final Material BIRCH_FENCE_GATE = create("BIRCH_FENCE_GATE").buildAndRegister();
    public static final Material BIRCH_LEAVES = create("BIRCH_LEAVES").buildAndRegister();
    public static final Material BIRCH_LOG = create("BIRCH_LOG").buildAndRegister();
    public static final Material BIRCH_PLANKS = create("BIRCH_PLANKS").buildAndRegister();
    public static final Material BIRCH_PRESSURE_PLATE = create("BIRCH_PRESSURE_PLATE").buildAndRegister();
    public static final Material BIRCH_SAPLING = create("BIRCH_SAPLING").buildAndRegister();
    public static final Material BIRCH_SLAB = create("BIRCH_SLAB").buildAndRegister();
    public static final Material BIRCH_STAIRS = create("BIRCH_STAIRS").buildAndRegister();
    public static final Material BIRCH_TRAPDOOR = create("BIRCH_TRAPDOOR").buildAndRegister();
    public static final Material BIRCH_WOOD = create("BIRCH_WOOD").buildAndRegister();
    public static final Material BLACK_BANNER = create("BLACK_BANNER").buildAndRegister();
    public static final Material BLACK_BED = create("BLACK_BED").buildAndRegister();
    public static final Material BLACK_CARPET = create("BLACK_CARPET").buildAndRegister();
    public static final Material BLACK_CONCRETE = create("BLACK_CONCRETE").buildAndRegister();
    public static final Material BLACK_CONCRETE_POWDER = create("BLACK_CONCRETE_POWDER").buildAndRegister();
    public static final Material BLACK_GLAZED_TERRACOTTA = create("BLACK_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material BLACK_SHULKER_BOX = create("BLACK_SHULKER_BOX").buildAndRegister();
    public static final Material BLACK_STAINED_GLASS = create("BLACK_STAINED_GLASS").buildAndRegister();
    public static final Material BLACK_STAINED_GLASS_PANE = create("BLACK_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material BLACK_TERRACOTTA = create("BLACK_TERRACOTTA").buildAndRegister();
    public static final Material BLACK_WALL_BANNER = create("BLACK_WALL_BANNER").buildAndRegister();
    public static final Material BLACK_WOOL = create("BLACK_WOOL").buildAndRegister();
    public static final Material BLAZE_POWDER = create("BLAZE_POWDER").buildAndRegister();
    public static final Material BLAZE_ROD = create("BLAZE_ROD").buildAndRegister();
    public static final Material BLAZE_SPAWN_EGG = create("BLAZE_SPAWN_EGG").buildAndRegister();
    public static final Material BLUE_BANNER = create("BLUE_BANNER").buildAndRegister();
    public static final Material BLUE_BED = create("BLUE_BED").buildAndRegister();
    public static final Material BLUE_CARPET = create("BLUE_CARPET").buildAndRegister();
    public static final Material BLUE_CONCRETE = create("BLUE_CONCRETE").buildAndRegister();
    public static final Material BLUE_CONCRETE_POWDER = create("BLUE_CONCRETE_POWDER").buildAndRegister();
    public static final Material BLUE_GLAZED_TERRACOTTA = create("BLUE_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material BLUE_ICE = create("BLUE_ICE").buildAndRegister();
    public static final Material BLUE_ORCHID = create("BLUE_ORCHID").buildAndRegister();
    public static final Material BLUE_SHULKER_BOX = create("BLUE_SHULKER_BOX").buildAndRegister();
    public static final Material BLUE_STAINED_GLASS = create("BLUE_STAINED_GLASS").buildAndRegister();
    public static final Material BLUE_STAINED_GLASS_PANE = create("BLUE_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material BLUE_TERRACOTTA = create("BLUE_TERRACOTTA").buildAndRegister();
    public static final Material BLUE_WALL_BANNER = create("BLUE_WALL_BANNER").buildAndRegister();
    public static final Material BLUE_WOOL = create("BLUE_WOOL").buildAndRegister();
    public static final Material BONE = create("BONE").buildAndRegister();
    public static final Material BONE_BLOCK = create("BONE_BLOCK").buildAndRegister();
    public static final Material BONE_MEAL = create("BONE_MEAL").buildAndRegister();
    public static final Material BOOK = create("BOOK").buildAndRegister();
    public static final Material BOOKSHELF = create("BOOKSHELF").buildAndRegister();
    public static final Material BOW = create("BOW").buildAndRegister();
    public static final Material BOWL = create("BOWL").buildAndRegister();
    public static final Material BRAIN_CORAL = create("BRAIN_CORAL").buildAndRegister();
    public static final Material BRAIN_CORAL_BLOCK = create("BRAIN_CORAL_BLOCK").buildAndRegister();
    public static final Material BRAIN_CORAL_FAN = create("BRAIN_CORAL_FAN").buildAndRegister();
    public static final Material BRAIN_CORAL_WALL_FAN = create("BRAIN_CORAL_WALL_FAN").buildAndRegister();
    public static final Material BREAD = create("BREAD").buildAndRegister();
    public static final Material BREWING_STAND = create("BREWING_STAND").buildAndRegister();
    public static final Material BRICK = create("BRICK").buildAndRegister();
    public static final Material BRICKS = create("BRICKS").buildAndRegister();
    public static final Material BRICK_SLAB = create("BRICK_SLAB").buildAndRegister();
    public static final Material BRICK_STAIRS = create("BRICK_STAIRS").buildAndRegister();
    public static final Material BROWN_BANNER = create("BROWN_BANNER").buildAndRegister();
    public static final Material BROWN_BED = create("BROWN_BED").buildAndRegister();
    public static final Material BROWN_CARPET = create("BROWN_CARPET").buildAndRegister();
    public static final Material BROWN_CONCRETE = create("BROWN_CONCRETE").buildAndRegister();
    public static final Material BROWN_CONCRETE_POWDER = create("BROWN_CONCRETE_POWDER").buildAndRegister();
    public static final Material BROWN_GLAZED_TERRACOTTA = create("BROWN_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material BROWN_MUSHROOM = create("BROWN_MUSHROOM").buildAndRegister();
    public static final Material BROWN_MUSHROOM_BLOCK = create("BROWN_MUSHROOM_BLOCK").buildAndRegister();
    public static final Material BROWN_SHULKER_BOX = create("BROWN_SHULKER_BOX").buildAndRegister();
    public static final Material BROWN_STAINED_GLASS = create("BROWN_STAINED_GLASS").buildAndRegister();
    public static final Material BROWN_STAINED_GLASS_PANE = create("BROWN_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material BROWN_TERRACOTTA = create("BROWN_TERRACOTTA").buildAndRegister();
    public static final Material BROWN_WALL_BANNER = create("BROWN_WALL_BANNER").buildAndRegister();
    public static final Material BROWN_WOOL = create("BROWN_WOOL").buildAndRegister();
    public static final Material BUBBLE_COLUMN = create("BUBBLE_COLUMN").buildAndRegister();
    public static final Material BUBBLE_CORAL = create("BUBBLE_CORAL").buildAndRegister();
    public static final Material BUBBLE_CORAL_BLOCK = create("BUBBLE_CORAL_BLOCK").buildAndRegister();
    public static final Material BUBBLE_CORAL_FAN = create("BUBBLE_CORAL_FAN").buildAndRegister();
    public static final Material BUBBLE_CORAL_WALL_FAN = create("BUBBLE_CORAL_WALL_FAN").buildAndRegister();
    public static final Material BUCKET = create("BUCKET").buildAndRegister();
    public static final Material CACTUS = create("CACTUS").buildAndRegister();
    public static final Material CACTUS_GREEN = create("CACTUS_GREEN").buildAndRegister();
    public static final Material CAKE = create("CAKE").buildAndRegister();
    public static final Material CARROT = create("CARROT").buildAndRegister();
    public static final Material CARROTS = create("CARROTS").buildAndRegister();
    public static final Material CARROT_ON_A_STICK = create("CARROT_ON_A_STICK").buildAndRegister();
    public static final Material CARVED_PUMPKIN = create("CARVED_PUMPKIN").buildAndRegister();
    public static final Material CAULDRON = create("CAULDRON").buildAndRegister();
    public static final Material CAVE_AIR = create("CAVE_AIR").buildAndRegister();
    public static final Material CAVE_SPIDER_SPAWN_EGG = create("CAVE_SPIDER_SPAWN_EGG").buildAndRegister();
    public static final Material CHAINMAIL_BOOTS = create("CHAINMAIL_BOOTS").buildAndRegister();
    public static final Material CHAINMAIL_CHESTPLATE = create("CHAINMAIL_CHESTPLATE").buildAndRegister();
    public static final Material CHAINMAIL_HELMET = create("CHAINMAIL_HELMET").buildAndRegister();
    public static final Material CHAINMAIL_LEGGINGS = create("CHAINMAIL_LEGGINGS").buildAndRegister();
    public static final Material CHAIN_COMMAND_BLOCK = create("CHAIN_COMMAND_BLOCK").buildAndRegister();
    public static final Material CHARCOAL = create("CHARCOAL").buildAndRegister();
    public static final Material CHEST = create("CHEST").buildAndRegister();
    public static final Material CHEST_MINECART = create("CHEST_MINECART").buildAndRegister();
    public static final Material CHICKEN = create("CHICKEN").buildAndRegister();
    public static final Material CHICKEN_SPAWN_EGG = create("CHICKEN_SPAWN_EGG").buildAndRegister();
    public static final Material CHIPPED_ANVIL = create("CHIPPED_ANVIL").buildAndRegister();
    public static final Material CHISELED_QUARTZ_BLOCK = create("CHISELED_QUARTZ_BLOCK").buildAndRegister();
    public static final Material CHISELED_RED_SANDSTONE = create("CHISELED_RED_SANDSTONE").buildAndRegister();
    public static final Material CHISELED_SANDSTONE = create("CHISELED_SANDSTONE").buildAndRegister();
    public static final Material CHISELED_STONE_BRICKS = create("CHISELED_STONE_BRICKS").buildAndRegister();
    public static final Material CHORUS_FLOWER = create("CHORUS_FLOWER").buildAndRegister();
    public static final Material CHORUS_FRUIT = create("CHORUS_FRUIT").buildAndRegister();
    public static final Material CHORUS_PLANT = create("CHORUS_PLANT").buildAndRegister();
    public static final Material CLAY = create("CLAY").buildAndRegister();
    public static final Material CLAY_BALL = create("CLAY_BALL").buildAndRegister();
    public static final Material CLOCK = create("CLOCK").buildAndRegister();
    public static final Material COAL = create("COAL").buildAndRegister();
    public static final Material COAL_BLOCK = create("COAL_BLOCK").buildAndRegister();
    public static final Material COAL_ORE = create("COAL_ORE").buildAndRegister();
    public static final Material COARSE_DIRT = create("COARSE_DIRT").buildAndRegister();
    public static final Material COBBLESTONE = create("COBBLESTONE").buildAndRegister();
    public static final Material COBBLESTONE_SLAB = create("COBBLESTONE_SLAB").buildAndRegister();
    public static final Material COBBLESTONE_STAIRS = create("COBBLESTONE_STAIRS").buildAndRegister();
    public static final Material COBBLESTONE_WALL = create("COBBLESTONE_WALL").buildAndRegister();
    public static final Material COBWEB = create("COBWEB").buildAndRegister();
    public static final Material COCOA = create("COCOA").buildAndRegister();
    public static final Material COCOA_BEANS = create("COCOA_BEANS").buildAndRegister();
    public static final Material COD = create("COD").buildAndRegister();
    public static final Material COD_BUCKET = create("COD_BUCKET").buildAndRegister();
    public static final Material COD_SPAWN_EGG = create("COD_SPAWN_EGG").buildAndRegister();
    public static final Material COMMAND_BLOCK = create("COMMAND_BLOCK").buildAndRegister();
    public static final Material COMMAND_BLOCK_MINECART = create("COMMAND_BLOCK_MINECART").buildAndRegister();
    public static final Material COMPARATOR = create("COMPARATOR").buildAndRegister();
    public static final Material COMPASS = create("COMPASS").buildAndRegister();
    public static final Material CONDUIT = create("CONDUIT").buildAndRegister();
    public static final Material COOKED_BEEF = create("COOKED_BEEF").buildAndRegister();
    public static final Material COOKED_CHICKEN = create("COOKED_CHICKEN").buildAndRegister();
    public static final Material COOKED_COD = create("COOKED_COD").buildAndRegister();
    public static final Material COOKED_MUTTON = create("COOKED_MUTTON").buildAndRegister();
    public static final Material COOKED_PORKCHOP = create("COOKED_PORKCHOP").buildAndRegister();
    public static final Material COOKED_RABBIT = create("COOKED_RABBIT").buildAndRegister();
    public static final Material COOKED_SALMON = create("COOKED_SALMON").buildAndRegister();
    public static final Material COOKIE = create("COOKIE").buildAndRegister();
    public static final Material COW_SPAWN_EGG = create("COW_SPAWN_EGG").buildAndRegister();
    public static final Material CRACKED_STONE_BRICKS = create("CRACKED_STONE_BRICKS").buildAndRegister();
    public static final Material CRAFTING_TABLE = create("CRAFTING_TABLE").buildAndRegister();
    public static final Material CREEPER_HEAD = create("CREEPER_HEAD").buildAndRegister();
    public static final Material CREEPER_SPAWN_EGG = create("CREEPER_SPAWN_EGG").buildAndRegister();
    public static final Material CREEPER_WALL_HEAD = create("CREEPER_WALL_HEAD").buildAndRegister();
    public static final Material CUT_RED_SANDSTONE = create("CUT_RED_SANDSTONE").buildAndRegister();
    public static final Material CUT_SANDSTONE = create("CUT_SANDSTONE").buildAndRegister();
    public static final Material CYAN_BANNER = create("CYAN_BANNER").buildAndRegister();
    public static final Material CYAN_BED = create("CYAN_BED").buildAndRegister();
    public static final Material CYAN_CARPET = create("CYAN_CARPET").buildAndRegister();
    public static final Material CYAN_CONCRETE = create("CYAN_CONCRETE").buildAndRegister();
    public static final Material CYAN_CONCRETE_POWDER = create("CYAN_CONCRETE_POWDER").buildAndRegister();
    public static final Material CYAN_DYE = create("CYAN_DYE").buildAndRegister();
    public static final Material CYAN_GLAZED_TERRACOTTA = create("CYAN_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material CYAN_SHULKER_BOX = create("CYAN_SHULKER_BOX").buildAndRegister();
    public static final Material CYAN_STAINED_GLASS = create("CYAN_STAINED_GLASS").buildAndRegister();
    public static final Material CYAN_STAINED_GLASS_PANE = create("CYAN_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material CYAN_TERRACOTTA = create("CYAN_TERRACOTTA").buildAndRegister();
    public static final Material CYAN_WALL_BANNER = create("CYAN_WALL_BANNER").buildAndRegister();
    public static final Material CYAN_WOOL = create("CYAN_WOOL").buildAndRegister();
    public static final Material DAMAGED_ANVIL = create("DAMAGED_ANVIL").buildAndRegister();
    public static final Material DANDELION = create("DANDELION").buildAndRegister();
    public static final Material DANDELION_YELLOW = create("DANDELION_YELLOW").buildAndRegister();
    public static final Material DARK_OAK_BOAT = create("DARK_OAK_BOAT").buildAndRegister();
    public static final Material DARK_OAK_BUTTON = create("DARK_OAK_BUTTON").buildAndRegister();
    public static final Material DARK_OAK_DOOR = create("DARK_OAK_DOOR").buildAndRegister();
    public static final Material DARK_OAK_FENCE = create("DARK_OAK_FENCE").buildAndRegister();
    public static final Material DARK_OAK_FENCE_GATE = create("DARK_OAK_FENCE_GATE").buildAndRegister();
    public static final Material DARK_OAK_LEAVES = create("DARK_OAK_LEAVES").buildAndRegister();
    public static final Material DARK_OAK_LOG = create("DARK_OAK_LOG").buildAndRegister();
    public static final Material DARK_OAK_PLANKS = create("DARK_OAK_PLANKS").buildAndRegister();
    public static final Material DARK_OAK_PRESSURE_PLATE = create("DARK_OAK_PRESSURE_PLATE").buildAndRegister();
    public static final Material DARK_OAK_SAPLING = create("DARK_OAK_SAPLING").buildAndRegister();
    public static final Material DARK_OAK_SLAB = create("DARK_OAK_SLAB").buildAndRegister();
    public static final Material DARK_OAK_STAIRS = create("DARK_OAK_STAIRS").buildAndRegister();
    public static final Material DARK_OAK_TRAPDOOR = create("DARK_OAK_TRAPDOOR").buildAndRegister();
    public static final Material DARK_OAK_WOOD = create("DARK_OAK_WOOD").buildAndRegister();
    public static final Material DARK_PRISMARINE = create("DARK_PRISMARINE").buildAndRegister();
    public static final Material DARK_PRISMARINE_SLAB = create("DARK_PRISMARINE_SLAB").buildAndRegister();
    public static final Material DARK_PRISMARINE_STAIRS = create("DARK_PRISMARINE_STAIRS").buildAndRegister();
    public static final Material DAYLIGHT_DETECTOR = create("DAYLIGHT_DETECTOR").buildAndRegister();
    public static final Material DEAD_BRAIN_CORAL = create("DEAD_BRAIN_CORAL").buildAndRegister();
    public static final Material DEAD_BRAIN_CORAL_BLOCK = create("DEAD_BRAIN_CORAL_BLOCK").buildAndRegister();
    public static final Material DEAD_BRAIN_CORAL_FAN = create("DEAD_BRAIN_CORAL_FAN").buildAndRegister();
    public static final Material DEAD_BRAIN_CORAL_WALL_FAN = create("DEAD_BRAIN_CORAL_WALL_FAN").buildAndRegister();
    public static final Material DEAD_BUBBLE_CORAL = create("DEAD_BUBBLE_CORAL").buildAndRegister();
    public static final Material DEAD_BUBBLE_CORAL_BLOCK = create("DEAD_BUBBLE_CORAL_BLOCK").buildAndRegister();
    public static final Material DEAD_BUBBLE_CORAL_FAN = create("DEAD_BUBBLE_CORAL_FAN").buildAndRegister();
    public static final Material DEAD_BUBBLE_CORAL_WALL_FAN = create("DEAD_BUBBLE_CORAL_WALL_FAN").buildAndRegister();
    public static final Material DEAD_BUSH = create("DEAD_BUSH").buildAndRegister();
    public static final Material DEAD_FIRE_CORAL = create("DEAD_FIRE_CORAL").buildAndRegister();
    public static final Material DEAD_FIRE_CORAL_BLOCK = create("DEAD_FIRE_CORAL_BLOCK").buildAndRegister();
    public static final Material DEAD_FIRE_CORAL_FAN = create("DEAD_FIRE_CORAL_FAN").buildAndRegister();
    public static final Material DEAD_FIRE_CORAL_WALL_FAN = create("DEAD_FIRE_CORAL_WALL_FAN").buildAndRegister();
    public static final Material DEAD_HORN_CORAL = create("DEAD_HORN_CORAL").buildAndRegister();
    public static final Material DEAD_HORN_CORAL_BLOCK = create("DEAD_HORN_CORAL_BLOCK").buildAndRegister();
    public static final Material DEAD_HORN_CORAL_FAN = create("DEAD_HORN_CORAL_FAN").buildAndRegister();
    public static final Material DEAD_HORN_CORAL_WALL_FAN = create("DEAD_HORN_CORAL_WALL_FAN").buildAndRegister();
    public static final Material DEAD_TUBE_CORAL = create("DEAD_TUBE_CORAL").buildAndRegister();
    public static final Material DEAD_TUBE_CORAL_BLOCK = create("DEAD_TUBE_CORAL_BLOCK").buildAndRegister();
    public static final Material DEAD_TUBE_CORAL_FAN = create("DEAD_TUBE_CORAL_FAN").buildAndRegister();
    public static final Material DEAD_TUBE_CORAL_WALL_FAN = create("DEAD_TUBE_CORAL_WALL_FAN").buildAndRegister();
    public static final Material DEBUG_STICK = create("DEBUG_STICK").buildAndRegister();
    public static final Material DETECTOR_RAIL = create("DETECTOR_RAIL").buildAndRegister();
    public static final Material DIAMOND = create("DIAMOND").buildAndRegister();
    public static final Material DIAMOND_AXE = create("DIAMOND_AXE").buildAndRegister();
    public static final Material DIAMOND_BLOCK = create("DIAMOND_BLOCK").buildAndRegister();
    public static final Material DIAMOND_BOOTS = create("DIAMOND_BOOTS").buildAndRegister();
    public static final Material DIAMOND_CHESTPLATE = create("DIAMOND_CHESTPLATE").buildAndRegister();
    public static final Material DIAMOND_HELMET = create("DIAMOND_HELMET").buildAndRegister();
    public static final Material DIAMOND_HOE = create("DIAMOND_HOE").buildAndRegister();
    public static final Material DIAMOND_HORSE_ARMOR = create("DIAMOND_HORSE_ARMOR").buildAndRegister();
    public static final Material DIAMOND_LEGGINGS = create("DIAMOND_LEGGINGS").buildAndRegister();
    public static final Material DIAMOND_ORE = create("DIAMOND_ORE").buildAndRegister();
    public static final Material DIAMOND_PICKAXE = create("DIAMOND_PICKAXE").buildAndRegister();
    public static final Material DIAMOND_SHOVEL = create("DIAMOND_SHOVEL").buildAndRegister();
    public static final Material DIAMOND_SWORD = create("DIAMOND_SWORD").buildAndRegister();
    public static final Material DIORITE = create("DIORITE").buildAndRegister();
    public static final Material DIRT = create("DIRT").buildAndRegister();
    public static final Material DISPENSER = create("DISPENSER").buildAndRegister();
    public static final Material DOLPHIN_SPAWN_EGG = create("DOLPHIN_SPAWN_EGG").buildAndRegister();
    public static final Material DONKEY_SPAWN_EGG = create("DONKEY_SPAWN_EGG").buildAndRegister();
    public static final Material DRAGON_BREATH = create("DRAGON_BREATH").buildAndRegister();
    public static final Material DRAGON_EGG = create("DRAGON_EGG").buildAndRegister();
    public static final Material DRAGON_HEAD = create("DRAGON_HEAD").buildAndRegister();
    public static final Material DRAGON_WALL_HEAD = create("DRAGON_WALL_HEAD").buildAndRegister();
    public static final Material DRIED_KELP = create("DRIED_KELP").buildAndRegister();
    public static final Material DRIED_KELP_BLOCK = create("DRIED_KELP_BLOCK").buildAndRegister();
    public static final Material DROPPER = create("DROPPER").buildAndRegister();
    public static final Material DROWNED_SPAWN_EGG = create("DROWNED_SPAWN_EGG").buildAndRegister();
    public static final Material EGG = create("EGG").buildAndRegister();
    public static final Material ELDER_GUARDIAN_SPAWN_EGG = create("ELDER_GUARDIAN_SPAWN_EGG").buildAndRegister();
    public static final Material ELYTRA = create("ELYTRA").buildAndRegister();
    public static final Material EMERALD = create("EMERALD").buildAndRegister();
    public static final Material EMERALD_BLOCK = create("EMERALD_BLOCK").buildAndRegister();
    public static final Material EMERALD_ORE = create("EMERALD_ORE").buildAndRegister();
    public static final Material ENCHANTED_BOOK = create("ENCHANTED_BOOK").buildAndRegister();
    public static final Material ENCHANTED_GOLDEN_APPLE = create("ENCHANTED_GOLDEN_APPLE").buildAndRegister();
    public static final Material ENCHANTING_TABLE = create("ENCHANTING_TABLE").buildAndRegister();
    public static final Material ENDERMAN_SPAWN_EGG = create("ENDERMAN_SPAWN_EGG").buildAndRegister();
    public static final Material ENDERMITE_SPAWN_EGG = create("ENDERMITE_SPAWN_EGG").buildAndRegister();
    public static final Material ENDER_CHEST = create("ENDER_CHEST").buildAndRegister();
    public static final Material ENDER_EYE = create("ENDER_EYE").buildAndRegister();
    public static final Material ENDER_PEARL = create("ENDER_PEARL").buildAndRegister();
    public static final Material END_CRYSTAL = create("END_CRYSTAL").buildAndRegister();
    public static final Material END_GATEWAY = create("END_GATEWAY").buildAndRegister();
    public static final Material END_PORTAL = create("END_PORTAL").buildAndRegister();
    public static final Material END_PORTAL_FRAME = create("END_PORTAL_FRAME").buildAndRegister();
    public static final Material END_ROD = create("END_ROD").buildAndRegister();
    public static final Material END_STONE = create("END_STONE").buildAndRegister();
    public static final Material END_STONE_BRICKS = create("END_STONE_BRICKS").buildAndRegister();
    public static final Material EVOKER_SPAWN_EGG = create("EVOKER_SPAWN_EGG").buildAndRegister();
    public static final Material EXPERIENCE_BOTTLE = create("EXPERIENCE_BOTTLE").buildAndRegister();
    public static final Material FARMLAND = create("FARMLAND").buildAndRegister();
    public static final Material FEATHER = create("FEATHER").buildAndRegister();
    public static final Material FERMENTED_SPIDER_EYE = create("FERMENTED_SPIDER_EYE").buildAndRegister();
    public static final Material FERN = create("FERN").buildAndRegister();
    public static final Material FILLED_MAP = create("FILLED_MAP").buildAndRegister();
    public static final Material FIRE = create("FIRE").buildAndRegister();
    public static final Material FIREWORK_ROCKET = create("FIREWORK_ROCKET").buildAndRegister();
    public static final Material FIREWORK_STAR = create("FIREWORK_STAR").buildAndRegister();
    public static final Material FIRE_CHARGE = create("FIRE_CHARGE").buildAndRegister();
    public static final Material FIRE_CORAL = create("FIRE_CORAL").buildAndRegister();
    public static final Material FIRE_CORAL_BLOCK = create("FIRE_CORAL_BLOCK").buildAndRegister();
    public static final Material FIRE_CORAL_FAN = create("FIRE_CORAL_FAN").buildAndRegister();
    public static final Material FIRE_CORAL_WALL_FAN = create("FIRE_CORAL_WALL_FAN").buildAndRegister();
    public static final Material FISHING_ROD = create("FISHING_ROD").buildAndRegister();
    public static final Material FLINT = create("FLINT").buildAndRegister();
    public static final Material FLINT_AND_STEEL = create("FLINT_AND_STEEL").buildAndRegister();
    public static final Material FLOWER_POT = create("FLOWER_POT").buildAndRegister();
    public static final Material FROSTED_ICE = create("FROSTED_ICE").buildAndRegister();
    public static final Material FURNACE = create("FURNACE").buildAndRegister();
    public static final Material FURNACE_MINECART = create("FURNACE_MINECART").buildAndRegister();
    public static final Material GHAST_SPAWN_EGG = create("GHAST_SPAWN_EGG").buildAndRegister();
    public static final Material GHAST_TEAR = create("GHAST_TEAR").buildAndRegister();
    public static final Material GLASS = create("GLASS").buildAndRegister();
    public static final Material GLASS_BOTTLE = create("GLASS_BOTTLE").buildAndRegister();
    public static final Material GLASS_PANE = create("GLASS_PANE").buildAndRegister();
    public static final Material GLISTERING_MELON_SLICE = create("GLISTERING_MELON_SLICE").buildAndRegister();
    public static final Material GLOWSTONE = create("GLOWSTONE").buildAndRegister();
    public static final Material GLOWSTONE_DUST = create("GLOWSTONE_DUST").buildAndRegister();
    public static final Material GOLDEN_APPLE = create("GOLDEN_APPLE").buildAndRegister();
    public static final Material GOLDEN_AXE = create("GOLDEN_AXE").buildAndRegister();
    public static final Material GOLDEN_BOOTS = create("GOLDEN_BOOTS").buildAndRegister();
    public static final Material GOLDEN_CARROT = create("GOLDEN_CARROT").buildAndRegister();
    public static final Material GOLDEN_CHESTPLATE = create("GOLDEN_CHESTPLATE").buildAndRegister();
    public static final Material GOLDEN_HELMET = create("GOLDEN_HELMET").buildAndRegister();
    public static final Material GOLDEN_HOE = create("GOLDEN_HOE").buildAndRegister();
    public static final Material GOLDEN_HORSE_ARMOR = create("GOLDEN_HORSE_ARMOR").buildAndRegister();
    public static final Material GOLDEN_LEGGINGS = create("GOLDEN_LEGGINGS").buildAndRegister();
    public static final Material GOLDEN_PICKAXE = create("GOLDEN_PICKAXE").buildAndRegister();
    public static final Material GOLDEN_SHOVEL = create("GOLDEN_SHOVEL").buildAndRegister();
    public static final Material GOLDEN_SWORD = create("GOLDEN_SWORD").buildAndRegister();
    public static final Material GOLD_BLOCK = create("GOLD_BLOCK").buildAndRegister();
    public static final Material GOLD_INGOT = create("GOLD_INGOT").buildAndRegister();
    public static final Material GOLD_NUGGET = create("GOLD_NUGGET").buildAndRegister();
    public static final Material GOLD_ORE = create("GOLD_ORE").buildAndRegister();
    public static final Material GRANITE = create("GRANITE").buildAndRegister();
    public static final Material GRASS = create("GRASS").buildAndRegister();
    public static final Material GRASS_BLOCK = create("GRASS_BLOCK").buildAndRegister();
    public static final Material GRASS_PATH = create("GRASS_PATH").buildAndRegister();
    public static final Material GRAVEL = create("GRAVEL").buildAndRegister();
    public static final Material GRAY_BANNER = create("GRAY_BANNER").buildAndRegister();
    public static final Material GRAY_BED = create("GRAY_BED").buildAndRegister();
    public static final Material GRAY_CARPET = create("GRAY_CARPET").buildAndRegister();
    public static final Material GRAY_CONCRETE = create("GRAY_CONCRETE").buildAndRegister();
    public static final Material GRAY_CONCRETE_POWDER = create("GRAY_CONCRETE_POWDER").buildAndRegister();
    public static final Material GRAY_DYE = create("GRAY_DYE").buildAndRegister();
    public static final Material GRAY_GLAZED_TERRACOTTA = create("GRAY_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material GRAY_SHULKER_BOX = create("GRAY_SHULKER_BOX").buildAndRegister();
    public static final Material GRAY_STAINED_GLASS = create("GRAY_STAINED_GLASS").buildAndRegister();
    public static final Material GRAY_STAINED_GLASS_PANE = create("GRAY_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material GRAY_TERRACOTTA = create("GRAY_TERRACOTTA").buildAndRegister();
    public static final Material GRAY_WALL_BANNER = create("GRAY_WALL_BANNER").buildAndRegister();
    public static final Material GRAY_WOOL = create("GRAY_WOOL").buildAndRegister();
    public static final Material GREEN_BANNER = create("GREEN_BANNER").buildAndRegister();
    public static final Material GREEN_BED = create("GREEN_BED").buildAndRegister();
    public static final Material GREEN_CARPET = create("GREEN_CARPET").buildAndRegister();
    public static final Material GREEN_CONCRETE = create("GREEN_CONCRETE").buildAndRegister();
    public static final Material GREEN_CONCRETE_POWDER = create("GREEN_CONCRETE_POWDER").buildAndRegister();
    public static final Material GREEN_GLAZED_TERRACOTTA = create("GREEN_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material GREEN_SHULKER_BOX = create("GREEN_SHULKER_BOX").buildAndRegister();
    public static final Material GREEN_STAINED_GLASS = create("GREEN_STAINED_GLASS").buildAndRegister();
    public static final Material GREEN_STAINED_GLASS_PANE = create("GREEN_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material GREEN_TERRACOTTA = create("GREEN_TERRACOTTA").buildAndRegister();
    public static final Material GREEN_WALL_BANNER = create("GREEN_WALL_BANNER").buildAndRegister();
    public static final Material GREEN_WOOL = create("GREEN_WOOL").buildAndRegister();
    public static final Material GUARDIAN_SPAWN_EGG = create("GUARDIAN_SPAWN_EGG").buildAndRegister();
    public static final Material GUNPOWDER = create("GUNPOWDER").buildAndRegister();
    public static final Material HAY_BLOCK = create("HAY_BLOCK").buildAndRegister();
    public static final Material HEART_OF_THE_SEA = create("HEART_OF_THE_SEA").buildAndRegister();
    public static final Material HEAVY_WEIGHTED_PRESSURE_PLATE = create("HEAVY_WEIGHTED_PRESSURE_PLATE").buildAndRegister();
    public static final Material HOPPER = create("HOPPER").buildAndRegister();
    public static final Material HOPPER_MINECART = create("HOPPER_MINECART").buildAndRegister();
    public static final Material HORN_CORAL = create("HORN_CORAL").buildAndRegister();
    public static final Material HORN_CORAL_BLOCK = create("HORN_CORAL_BLOCK").buildAndRegister();
    public static final Material HORN_CORAL_FAN = create("HORN_CORAL_FAN").buildAndRegister();
    public static final Material HORN_CORAL_WALL_FAN = create("HORN_CORAL_WALL_FAN").buildAndRegister();
    public static final Material HORSE_SPAWN_EGG = create("HORSE_SPAWN_EGG").buildAndRegister();
    public static final Material HUSK_SPAWN_EGG = create("HUSK_SPAWN_EGG").buildAndRegister();
    public static final Material ICE = create("ICE").buildAndRegister();
    public static final Material INFESTED_CHISELED_STONE_BRICKS = create("INFESTED_CHISELED_STONE_BRICKS").buildAndRegister();
    public static final Material INFESTED_COBBLESTONE = create("INFESTED_COBBLESTONE").buildAndRegister();
    public static final Material INFESTED_CRACKED_STONE_BRICKS = create("INFESTED_CRACKED_STONE_BRICKS").buildAndRegister();
    public static final Material INFESTED_MOSSY_STONE_BRICKS = create("INFESTED_MOSSY_STONE_BRICKS").buildAndRegister();
    public static final Material INFESTED_STONE = create("INFESTED_STONE").buildAndRegister();
    public static final Material INFESTED_STONE_BRICKS = create("INFESTED_STONE_BRICKS").buildAndRegister();
    public static final Material INK_SAC = create("INK_SAC").buildAndRegister();
    public static final Material IRON_AXE = create("IRON_AXE").buildAndRegister();
    public static final Material IRON_BARS = create("IRON_BARS").buildAndRegister();
    public static final Material IRON_BLOCK = create("IRON_BLOCK").buildAndRegister();
    public static final Material IRON_BOOTS = create("IRON_BOOTS").buildAndRegister();
    public static final Material IRON_CHESTPLATE = create("IRON_CHESTPLATE").buildAndRegister();
    public static final Material IRON_DOOR = create("IRON_DOOR").buildAndRegister();
    public static final Material IRON_HELMET = create("IRON_HELMET").buildAndRegister();
    public static final Material IRON_HOE = create("IRON_HOE").buildAndRegister();
    public static final Material IRON_HORSE_ARMOR = create("IRON_HORSE_ARMOR").buildAndRegister();
    public static final Material IRON_INGOT = create("IRON_INGOT").buildAndRegister();
    public static final Material IRON_LEGGINGS = create("IRON_LEGGINGS").buildAndRegister();
    public static final Material IRON_NUGGET = create("IRON_NUGGET").buildAndRegister();
    public static final Material IRON_ORE = create("IRON_ORE").buildAndRegister();
    public static final Material IRON_PICKAXE = create("IRON_PICKAXE").buildAndRegister();
    public static final Material IRON_SHOVEL = create("IRON_SHOVEL").buildAndRegister();
    public static final Material IRON_SWORD = create("IRON_SWORD").buildAndRegister();
    public static final Material IRON_TRAPDOOR = create("IRON_TRAPDOOR").buildAndRegister();
    public static final Material ITEM_FRAME = create("ITEM_FRAME").buildAndRegister();
    public static final Material JACK_O_LANTERN = create("JACK_O_LANTERN").buildAndRegister();
    public static final Material JUKEBOX = create("JUKEBOX").buildAndRegister();
    public static final Material JUNGLE_BOAT = create("JUNGLE_BOAT").buildAndRegister();
    public static final Material JUNGLE_BUTTON = create("JUNGLE_BUTTON").buildAndRegister();
    public static final Material JUNGLE_DOOR = create("JUNGLE_DOOR").buildAndRegister();
    public static final Material JUNGLE_FENCE = create("JUNGLE_FENCE").buildAndRegister();
    public static final Material JUNGLE_FENCE_GATE = create("JUNGLE_FENCE_GATE").buildAndRegister();
    public static final Material JUNGLE_LEAVES = create("JUNGLE_LEAVES").buildAndRegister();
    public static final Material JUNGLE_LOG = create("JUNGLE_LOG").buildAndRegister();
    public static final Material JUNGLE_PLANKS = create("JUNGLE_PLANKS").buildAndRegister();
    public static final Material JUNGLE_PRESSURE_PLATE = create("JUNGLE_PRESSURE_PLATE").buildAndRegister();
    public static final Material JUNGLE_SAPLING = create("JUNGLE_SAPLING").buildAndRegister();
    public static final Material JUNGLE_SLAB = create("JUNGLE_SLAB").buildAndRegister();
    public static final Material JUNGLE_STAIRS = create("JUNGLE_STAIRS").buildAndRegister();
    public static final Material JUNGLE_TRAPDOOR = create("JUNGLE_TRAPDOOR").buildAndRegister();
    public static final Material JUNGLE_WOOD = create("JUNGLE_WOOD").buildAndRegister();
    public static final Material KELP = create("KELP").buildAndRegister();
    public static final Material KELP_PLANT = create("KELP_PLANT").buildAndRegister();
    public static final Material KNOWLEDGE_BOOK = create("KNOWLEDGE_BOOK").buildAndRegister();
    public static final Material LADDER = create("LADDER").buildAndRegister();
    public static final Material LAPIS_BLOCK = create("LAPIS_BLOCK").buildAndRegister();
    public static final Material LAPIS_LAZULI = create("LAPIS_LAZULI").buildAndRegister();
    public static final Material LAPIS_ORE = create("LAPIS_ORE").buildAndRegister();
    public static final Material LARGE_FERN = create("LARGE_FERN").buildAndRegister();
    public static final Material LAVA = create("LAVA").buildAndRegister();
    public static final Material LAVA_BUCKET = create("LAVA_BUCKET").buildAndRegister();
    public static final Material LEAD = create("LEAD").buildAndRegister();
    public static final Material LEATHER = create("LEATHER").buildAndRegister();
    public static final Material LEATHER_BOOTS = create("LEATHER_BOOTS").buildAndRegister();
    public static final Material LEATHER_CHESTPLATE = create("LEATHER_CHESTPLATE").buildAndRegister();
    public static final Material LEATHER_HELMET = create("LEATHER_HELMET").buildAndRegister();
    public static final Material LEATHER_LEGGINGS = create("LEATHER_LEGGINGS").buildAndRegister();
    public static final Material LEVER = create("LEVER").buildAndRegister();
    public static final Material LIGHT_BLUE_BANNER = create("LIGHT_BLUE_BANNER").buildAndRegister();
    public static final Material LIGHT_BLUE_BED = create("LIGHT_BLUE_BED").buildAndRegister();
    public static final Material LIGHT_BLUE_CARPET = create("LIGHT_BLUE_CARPET").buildAndRegister();
    public static final Material LIGHT_BLUE_CONCRETE = create("LIGHT_BLUE_CONCRETE").buildAndRegister();
    public static final Material LIGHT_BLUE_CONCRETE_POWDER = create("LIGHT_BLUE_CONCRETE_POWDER").buildAndRegister();
    public static final Material LIGHT_BLUE_DYE = create("LIGHT_BLUE_DYE").buildAndRegister();
    public static final Material LIGHT_BLUE_GLAZED_TERRACOTTA = create("LIGHT_BLUE_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material LIGHT_BLUE_SHULKER_BOX = create("LIGHT_BLUE_SHULKER_BOX").buildAndRegister();
    public static final Material LIGHT_BLUE_STAINED_GLASS = create("LIGHT_BLUE_STAINED_GLASS").buildAndRegister();
    public static final Material LIGHT_BLUE_STAINED_GLASS_PANE = create("LIGHT_BLUE_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material LIGHT_BLUE_TERRACOTTA = create("LIGHT_BLUE_TERRACOTTA").buildAndRegister();
    public static final Material LIGHT_BLUE_WALL_BANNER = create("LIGHT_BLUE_WALL_BANNER").buildAndRegister();
    public static final Material LIGHT_BLUE_WOOL = create("LIGHT_BLUE_WOOL").buildAndRegister();
    public static final Material LIGHT_GRAY_BANNER = create("LIGHT_GRAY_BANNER").buildAndRegister();
    public static final Material LIGHT_GRAY_BED = create("LIGHT_GRAY_BED").buildAndRegister();
    public static final Material LIGHT_GRAY_CARPET = create("LIGHT_GRAY_CARPET").buildAndRegister();
    public static final Material LIGHT_GRAY_CONCRETE = create("LIGHT_GRAY_CONCRETE").buildAndRegister();
    public static final Material LIGHT_GRAY_CONCRETE_POWDER = create("LIGHT_GRAY_CONCRETE_POWDER").buildAndRegister();
    public static final Material LIGHT_GRAY_DYE = create("LIGHT_GRAY_DYE").buildAndRegister();
    public static final Material LIGHT_GRAY_GLAZED_TERRACOTTA = create("LIGHT_GRAY_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material LIGHT_GRAY_SHULKER_BOX = create("LIGHT_GRAY_SHULKER_BOX").buildAndRegister();
    public static final Material LIGHT_GRAY_STAINED_GLASS = create("LIGHT_GRAY_STAINED_GLASS").buildAndRegister();
    public static final Material LIGHT_GRAY_STAINED_GLASS_PANE = create("LIGHT_GRAY_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material LIGHT_GRAY_TERRACOTTA = create("LIGHT_GRAY_TERRACOTTA").buildAndRegister();
    public static final Material LIGHT_GRAY_WALL_BANNER = create("LIGHT_GRAY_WALL_BANNER").buildAndRegister();
    public static final Material LIGHT_GRAY_WOOL = create("LIGHT_GRAY_WOOL").buildAndRegister();
    public static final Material LIGHT_WEIGHTED_PRESSURE_PLATE = create("LIGHT_WEIGHTED_PRESSURE_PLATE").buildAndRegister();
    public static final Material LILAC = create("LILAC").buildAndRegister();
    public static final Material LILY_PAD = create("LILY_PAD").buildAndRegister();
    public static final Material LIME_BANNER = create("LIME_BANNER").buildAndRegister();
    public static final Material LIME_BED = create("LIME_BED").buildAndRegister();
    public static final Material LIME_CARPET = create("LIME_CARPET").buildAndRegister();
    public static final Material LIME_CONCRETE = create("LIME_CONCRETE").buildAndRegister();
    public static final Material LIME_CONCRETE_POWDER = create("LIME_CONCRETE_POWDER").buildAndRegister();
    public static final Material LIME_DYE = create("LIME_DYE").buildAndRegister();
    public static final Material LIME_GLAZED_TERRACOTTA = create("LIME_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material LIME_SHULKER_BOX = create("LIME_SHULKER_BOX").buildAndRegister();
    public static final Material LIME_STAINED_GLASS = create("LIME_STAINED_GLASS").buildAndRegister();
    public static final Material LIME_STAINED_GLASS_PANE = create("LIME_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material LIME_TERRACOTTA = create("LIME_TERRACOTTA").buildAndRegister();
    public static final Material LIME_WALL_BANNER = create("LIME_WALL_BANNER").buildAndRegister();
    public static final Material LIME_WOOL = create("LIME_WOOL").buildAndRegister();
    public static final Material LINGERING_POTION = create("LINGERING_POTION").buildAndRegister();
    public static final Material LLAMA_SPAWN_EGG = create("LLAMA_SPAWN_EGG").buildAndRegister();
    public static final Material MAGENTA_BANNER = create("MAGENTA_BANNER").buildAndRegister();
    public static final Material MAGENTA_BED = create("MAGENTA_BED").buildAndRegister();
    public static final Material MAGENTA_CARPET = create("MAGENTA_CARPET").buildAndRegister();
    public static final Material MAGENTA_CONCRETE = create("MAGENTA_CONCRETE").buildAndRegister();
    public static final Material MAGENTA_CONCRETE_POWDER = create("MAGENTA_CONCRETE_POWDER").buildAndRegister();
    public static final Material MAGENTA_DYE = create("MAGENTA_DYE").buildAndRegister();
    public static final Material MAGENTA_GLAZED_TERRACOTTA = create("MAGENTA_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material MAGENTA_SHULKER_BOX = create("MAGENTA_SHULKER_BOX").buildAndRegister();
    public static final Material MAGENTA_STAINED_GLASS = create("MAGENTA_STAINED_GLASS").buildAndRegister();
    public static final Material MAGENTA_STAINED_GLASS_PANE = create("MAGENTA_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material MAGENTA_TERRACOTTA = create("MAGENTA_TERRACOTTA").buildAndRegister();
    public static final Material MAGENTA_WALL_BANNER = create("MAGENTA_WALL_BANNER").buildAndRegister();
    public static final Material MAGENTA_WOOL = create("MAGENTA_WOOL").buildAndRegister();
    public static final Material MAGMA_BLOCK = create("MAGMA_BLOCK").buildAndRegister();
    public static final Material MAGMA_CREAM = create("MAGMA_CREAM").buildAndRegister();
    public static final Material MAGMA_CUBE_SPAWN_EGG = create("MAGMA_CUBE_SPAWN_EGG").buildAndRegister();
    public static final Material MAP = create("MAP").buildAndRegister();
    public static final Material MELON = create("MELON").buildAndRegister();
    public static final Material MELON_SEEDS = create("MELON_SEEDS").buildAndRegister();
    public static final Material MELON_SLICE = create("MELON_SLICE").buildAndRegister();
    public static final Material MELON_STEM = create("MELON_STEM").buildAndRegister();
    public static final Material MILK_BUCKET = create("MILK_BUCKET").buildAndRegister();
    public static final Material MINECART = create("MINECART").buildAndRegister();
    public static final Material MOOSHROOM_SPAWN_EGG = create("MOOSHROOM_SPAWN_EGG").buildAndRegister();
    public static final Material MOSSY_COBBLESTONE = create("MOSSY_COBBLESTONE").buildAndRegister();
    public static final Material MOSSY_COBBLESTONE_WALL = create("MOSSY_COBBLESTONE_WALL").buildAndRegister();
    public static final Material MOSSY_STONE_BRICKS = create("MOSSY_STONE_BRICKS").buildAndRegister();
    public static final Material MOVING_PISTON = create("MOVING_PISTON").buildAndRegister();
    public static final Material MULE_SPAWN_EGG = create("MULE_SPAWN_EGG").buildAndRegister();
    public static final Material MUSHROOM_STEM = create("MUSHROOM_STEM").buildAndRegister();
    public static final Material MUSHROOM_STEW = create("MUSHROOM_STEW").buildAndRegister();
    public static final Material MUSIC_DISC_11 = create("MUSIC_DISC_11").buildAndRegister();
    public static final Material MUSIC_DISC_13 = create("MUSIC_DISC_13").buildAndRegister();
    public static final Material MUSIC_DISC_BLOCKS = create("MUSIC_DISC_BLOCKS").buildAndRegister();
    public static final Material MUSIC_DISC_CAT = create("MUSIC_DISC_CAT").buildAndRegister();
    public static final Material MUSIC_DISC_CHIRP = create("MUSIC_DISC_CHIRP").buildAndRegister();
    public static final Material MUSIC_DISC_FAR = create("MUSIC_DISC_FAR").buildAndRegister();
    public static final Material MUSIC_DISC_MALL = create("MUSIC_DISC_MALL").buildAndRegister();
    public static final Material MUSIC_DISC_MELLOHI = create("MUSIC_DISC_MELLOHI").buildAndRegister();
    public static final Material MUSIC_DISC_STAL = create("MUSIC_DISC_STAL").buildAndRegister();
    public static final Material MUSIC_DISC_STRAD = create("MUSIC_DISC_STRAD").buildAndRegister();
    public static final Material MUSIC_DISC_WAIT = create("MUSIC_DISC_WAIT").buildAndRegister();
    public static final Material MUSIC_DISC_WARD = create("MUSIC_DISC_WARD").buildAndRegister();
    public static final Material MUTTON = create("MUTTON").buildAndRegister();
    public static final Material MYCELIUM = create("MYCELIUM").buildAndRegister();
    public static final Material NAME_TAG = create("NAME_TAG").buildAndRegister();
    public static final Material NAUTILUS_SHELL = create("NAUTILUS_SHELL").buildAndRegister();
    public static final Material NETHERRACK = create("NETHERRACK").buildAndRegister();
    public static final Material NETHER_BRICK = create("NETHER_BRICK").buildAndRegister();
    public static final Material NETHER_BRICKS = create("NETHER_BRICKS").buildAndRegister();
    public static final Material NETHER_BRICK_FENCE = create("NETHER_BRICK_FENCE").buildAndRegister();
    public static final Material NETHER_BRICK_SLAB = create("NETHER_BRICK_SLAB").buildAndRegister();
    public static final Material NETHER_BRICK_STAIRS = create("NETHER_BRICK_STAIRS").buildAndRegister();
    public static final Material NETHER_PORTAL = create("NETHER_PORTAL").buildAndRegister();
    public static final Material NETHER_QUARTZ_ORE = create("NETHER_QUARTZ_ORE").buildAndRegister();
    public static final Material NETHER_STAR = create("NETHER_STAR").buildAndRegister();
    public static final Material NETHER_WART = create("NETHER_WART").buildAndRegister();
    public static final Material NETHER_WART_BLOCK = create("NETHER_WART_BLOCK").buildAndRegister();
    public static final Material NOTE_BLOCK = create("NOTE_BLOCK").buildAndRegister();
    public static final Material OAK_BOAT = create("OAK_BOAT").buildAndRegister();
    public static final Material OAK_BUTTON = create("OAK_BUTTON").buildAndRegister();
    public static final Material OAK_DOOR = create("OAK_DOOR").buildAndRegister();
    public static final Material OAK_FENCE = create("OAK_FENCE").buildAndRegister();
    public static final Material OAK_FENCE_GATE = create("OAK_FENCE_GATE").buildAndRegister();
    public static final Material OAK_LEAVES = create("OAK_LEAVES").buildAndRegister();
    public static final Material OAK_LOG = create("OAK_LOG").buildAndRegister();
    public static final Material OAK_PLANKS = create("OAK_PLANKS").buildAndRegister();
    public static final Material OAK_PRESSURE_PLATE = create("OAK_PRESSURE_PLATE").buildAndRegister();
    public static final Material OAK_SAPLING = create("OAK_SAPLING").buildAndRegister();
    public static final Material OAK_SLAB = create("OAK_SLAB").buildAndRegister();
    public static final Material OAK_STAIRS = create("OAK_STAIRS").buildAndRegister();
    public static final Material OAK_TRAPDOOR = create("OAK_TRAPDOOR").buildAndRegister();
    public static final Material OAK_WOOD = create("OAK_WOOD").buildAndRegister();
    public static final Material OBSERVER = create("OBSERVER").buildAndRegister();
    public static final Material OBSIDIAN = create("OBSIDIAN").buildAndRegister();
    public static final Material OCELOT_SPAWN_EGG = create("OCELOT_SPAWN_EGG").buildAndRegister();
    public static final Material ORANGE_BANNER = create("ORANGE_BANNER").buildAndRegister();
    public static final Material ORANGE_BED = create("ORANGE_BED").buildAndRegister();
    public static final Material ORANGE_CARPET = create("ORANGE_CARPET").buildAndRegister();
    public static final Material ORANGE_CONCRETE = create("ORANGE_CONCRETE").buildAndRegister();
    public static final Material ORANGE_CONCRETE_POWDER = create("ORANGE_CONCRETE_POWDER").buildAndRegister();
    public static final Material ORANGE_DYE = create("ORANGE_DYE").buildAndRegister();
    public static final Material ORANGE_GLAZED_TERRACOTTA = create("ORANGE_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material ORANGE_SHULKER_BOX = create("ORANGE_SHULKER_BOX").buildAndRegister();
    public static final Material ORANGE_STAINED_GLASS = create("ORANGE_STAINED_GLASS").buildAndRegister();
    public static final Material ORANGE_STAINED_GLASS_PANE = create("ORANGE_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material ORANGE_TERRACOTTA = create("ORANGE_TERRACOTTA").buildAndRegister();
    public static final Material ORANGE_TULIP = create("ORANGE_TULIP").buildAndRegister();
    public static final Material ORANGE_WALL_BANNER = create("ORANGE_WALL_BANNER").buildAndRegister();
    public static final Material ORANGE_WOOL = create("ORANGE_WOOL").buildAndRegister();
    public static final Material OXEYE_DAISY = create("OXEYE_DAISY").buildAndRegister();
    public static final Material PACKED_ICE = create("PACKED_ICE").buildAndRegister();
    public static final Material PAINTING = create("PAINTING").buildAndRegister();
    public static final Material PAPER = create("PAPER").buildAndRegister();
    public static final Material PARROT_SPAWN_EGG = create("PARROT_SPAWN_EGG").buildAndRegister();
    public static final Material PEONY = create("PEONY").buildAndRegister();
    public static final Material PETRIFIED_OAK_SLAB = create("PETRIFIED_OAK_SLAB").buildAndRegister();
    public static final Material PHANTOM_MEMBRANE = create("PHANTOM_MEMBRANE").buildAndRegister();
    public static final Material PHANTOM_SPAWN_EGG = create("PHANTOM_SPAWN_EGG").buildAndRegister();
    public static final Material PIG_SPAWN_EGG = create("PIG_SPAWN_EGG").buildAndRegister();
    public static final Material PINK_BANNER = create("PINK_BANNER").buildAndRegister();
    public static final Material PINK_BED = create("PINK_BED").buildAndRegister();
    public static final Material PINK_CARPET = create("PINK_CARPET").buildAndRegister();
    public static final Material PINK_CONCRETE = create("PINK_CONCRETE").buildAndRegister();
    public static final Material PINK_CONCRETE_POWDER = create("PINK_CONCRETE_POWDER").buildAndRegister();
    public static final Material PINK_DYE = create("PINK_DYE").buildAndRegister();
    public static final Material PINK_GLAZED_TERRACOTTA = create("PINK_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material PINK_SHULKER_BOX = create("PINK_SHULKER_BOX").buildAndRegister();
    public static final Material PINK_STAINED_GLASS = create("PINK_STAINED_GLASS").buildAndRegister();
    public static final Material PINK_STAINED_GLASS_PANE = create("PINK_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material PINK_TERRACOTTA = create("PINK_TERRACOTTA").buildAndRegister();
    public static final Material PINK_TULIP = create("PINK_TULIP").buildAndRegister();
    public static final Material PINK_WALL_BANNER = create("PINK_WALL_BANNER").buildAndRegister();
    public static final Material PINK_WOOL = create("PINK_WOOL").buildAndRegister();
    public static final Material PISTON = create("PISTON").buildAndRegister();
    public static final Material PISTON_HEAD = create("PISTON_HEAD").buildAndRegister();
    public static final Material PLAYER_HEAD = create("PLAYER_HEAD").buildAndRegister();
    public static final Material PLAYER_WALL_HEAD = create("PLAYER_WALL_HEAD").buildAndRegister();
    public static final Material PODZOL = create("PODZOL").buildAndRegister();
    public static final Material POISONOUS_POTATO = create("POISONOUS_POTATO").buildAndRegister();
    public static final Material POLAR_BEAR_SPAWN_EGG = create("POLAR_BEAR_SPAWN_EGG").buildAndRegister();
    public static final Material POLISHED_ANDESITE = create("POLISHED_ANDESITE").buildAndRegister();
    public static final Material POLISHED_DIORITE = create("POLISHED_DIORITE").buildAndRegister();
    public static final Material POLISHED_GRANITE = create("POLISHED_GRANITE").buildAndRegister();
    public static final Material POPPED_CHORUS_FRUIT = create("POPPED_CHORUS_FRUIT").buildAndRegister();
    public static final Material POPPY = create("POPPY").buildAndRegister();
    public static final Material PORKCHOP = create("PORKCHOP").buildAndRegister();
    public static final Material POTATO = create("POTATO").buildAndRegister();
    public static final Material POTATOES = create("POTATOES").buildAndRegister();
    public static final Material POTION = create("POTION").buildAndRegister();
    public static final Material POTTED_ACACIA_SAPLING = create("POTTED_ACACIA_SAPLING").buildAndRegister();
    public static final Material POTTED_ALLIUM = create("POTTED_ALLIUM").buildAndRegister();
    public static final Material POTTED_AZURE_BLUET = create("POTTED_AZURE_BLUET").buildAndRegister();
    public static final Material POTTED_BIRCH_SAPLING = create("POTTED_BIRCH_SAPLING").buildAndRegister();
    public static final Material POTTED_BLUE_ORCHID = create("POTTED_BLUE_ORCHID").buildAndRegister();
    public static final Material POTTED_BROWN_MUSHROOM = create("POTTED_BROWN_MUSHROOM").buildAndRegister();
    public static final Material POTTED_CACTUS = create("POTTED_CACTUS").buildAndRegister();
    public static final Material POTTED_DANDELION = create("POTTED_DANDELION").buildAndRegister();
    public static final Material POTTED_DARK_OAK_SAPLING = create("POTTED_DARK_OAK_SAPLING").buildAndRegister();
    public static final Material POTTED_DEAD_BUSH = create("POTTED_DEAD_BUSH").buildAndRegister();
    public static final Material POTTED_FERN = create("POTTED_FERN").buildAndRegister();
    public static final Material POTTED_JUNGLE_SAPLING = create("POTTED_JUNGLE_SAPLING").buildAndRegister();
    public static final Material POTTED_OAK_SAPLING = create("POTTED_OAK_SAPLING").buildAndRegister();
    public static final Material POTTED_ORANGE_TULIP = create("POTTED_ORANGE_TULIP").buildAndRegister();
    public static final Material POTTED_OXEYE_DAISY = create("POTTED_OXEYE_DAISY").buildAndRegister();
    public static final Material POTTED_PINK_TULIP = create("POTTED_PINK_TULIP").buildAndRegister();
    public static final Material POTTED_POPPY = create("POTTED_POPPY").buildAndRegister();
    public static final Material POTTED_RED_MUSHROOM = create("POTTED_RED_MUSHROOM").buildAndRegister();
    public static final Material POTTED_RED_TULIP = create("POTTED_RED_TULIP").buildAndRegister();
    public static final Material POTTED_SPRUCE_SAPLING = create("POTTED_SPRUCE_SAPLING").buildAndRegister();
    public static final Material POTTED_WHITE_TULIP = create("POTTED_WHITE_TULIP").buildAndRegister();
    public static final Material POWERED_RAIL = create("POWERED_RAIL").buildAndRegister();
    public static final Material PRISMARINE = create("PRISMARINE").buildAndRegister();
    public static final Material PRISMARINE_BRICKS = create("PRISMARINE_BRICKS").buildAndRegister();
    public static final Material PRISMARINE_BRICK_SLAB = create("PRISMARINE_BRICK_SLAB").buildAndRegister();
    public static final Material PRISMARINE_BRICK_STAIRS = create("PRISMARINE_BRICK_STAIRS").buildAndRegister();
    public static final Material PRISMARINE_CRYSTALS = create("PRISMARINE_CRYSTALS").buildAndRegister();
    public static final Material PRISMARINE_SHARD = create("PRISMARINE_SHARD").buildAndRegister();
    public static final Material PRISMARINE_SLAB = create("PRISMARINE_SLAB").buildAndRegister();
    public static final Material PRISMARINE_STAIRS = create("PRISMARINE_STAIRS").buildAndRegister();
    public static final Material PUFFERFISH = create("PUFFERFISH").buildAndRegister();
    public static final Material PUFFERFISH_BUCKET = create("PUFFERFISH_BUCKET").buildAndRegister();
    public static final Material PUFFERFISH_SPAWN_EGG = create("PUFFERFISH_SPAWN_EGG").buildAndRegister();
    public static final Material PUMPKIN = create("PUMPKIN").buildAndRegister();
    public static final Material PUMPKIN_PIE = create("PUMPKIN_PIE").buildAndRegister();
    public static final Material PUMPKIN_SEEDS = create("PUMPKIN_SEEDS").buildAndRegister();
    public static final Material PUMPKIN_STEM = create("PUMPKIN_STEM").buildAndRegister();
    public static final Material PURPLE_BANNER = create("PURPLE_BANNER").buildAndRegister();
    public static final Material PURPLE_BED = create("PURPLE_BED").buildAndRegister();
    public static final Material PURPLE_CARPET = create("PURPLE_CARPET").buildAndRegister();
    public static final Material PURPLE_CONCRETE = create("PURPLE_CONCRETE").buildAndRegister();
    public static final Material PURPLE_CONCRETE_POWDER = create("PURPLE_CONCRETE_POWDER").buildAndRegister();
    public static final Material PURPLE_DYE = create("PURPLE_DYE").buildAndRegister();
    public static final Material PURPLE_GLAZED_TERRACOTTA = create("PURPLE_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material PURPLE_SHULKER_BOX = create("PURPLE_SHULKER_BOX").buildAndRegister();
    public static final Material PURPLE_STAINED_GLASS = create("PURPLE_STAINED_GLASS").buildAndRegister();
    public static final Material PURPLE_STAINED_GLASS_PANE = create("PURPLE_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material PURPLE_TERRACOTTA = create("PURPLE_TERRACOTTA").buildAndRegister();
    public static final Material PURPLE_WALL_BANNER = create("PURPLE_WALL_BANNER").buildAndRegister();
    public static final Material PURPLE_WOOL = create("PURPLE_WOOL").buildAndRegister();
    public static final Material PURPUR_BLOCK = create("PURPUR_BLOCK").buildAndRegister();
    public static final Material PURPUR_PILLAR = create("PURPUR_PILLAR").buildAndRegister();
    public static final Material PURPUR_SLAB = create("PURPUR_SLAB").buildAndRegister();
    public static final Material PURPUR_STAIRS = create("PURPUR_STAIRS").buildAndRegister();
    public static final Material QUARTZ = create("QUARTZ").buildAndRegister();
    public static final Material QUARTZ_BLOCK = create("QUARTZ_BLOCK").buildAndRegister();
    public static final Material QUARTZ_PILLAR = create("QUARTZ_PILLAR").buildAndRegister();
    public static final Material QUARTZ_SLAB = create("QUARTZ_SLAB").buildAndRegister();
    public static final Material QUARTZ_STAIRS = create("QUARTZ_STAIRS").buildAndRegister();
    public static final Material RABBIT = create("RABBIT").buildAndRegister();
    public static final Material RABBIT_FOOT = create("RABBIT_FOOT").buildAndRegister();
    public static final Material RABBIT_HIDE = create("RABBIT_HIDE").buildAndRegister();
    public static final Material RABBIT_SPAWN_EGG = create("RABBIT_SPAWN_EGG").buildAndRegister();
    public static final Material RABBIT_STEW = create("RABBIT_STEW").buildAndRegister();
    public static final Material RAIL = create("RAIL").buildAndRegister();
    public static final Material REDSTONE = create("REDSTONE").buildAndRegister();
    public static final Material REDSTONE_BLOCK = create("REDSTONE_BLOCK").buildAndRegister();
    public static final Material REDSTONE_LAMP = create("REDSTONE_LAMP").buildAndRegister();
    public static final Material REDSTONE_ORE = create("REDSTONE_ORE").buildAndRegister();
    public static final Material REDSTONE_TORCH = create("REDSTONE_TORCH").buildAndRegister();
    public static final Material REDSTONE_WALL_TORCH = create("REDSTONE_WALL_TORCH").buildAndRegister();
    public static final Material REDSTONE_WIRE = create("REDSTONE_WIRE").buildAndRegister();
    public static final Material RED_BANNER = create("RED_BANNER").buildAndRegister();
    public static final Material RED_BED = create("RED_BED").buildAndRegister();
    public static final Material RED_CARPET = create("RED_CARPET").buildAndRegister();
    public static final Material RED_CONCRETE = create("RED_CONCRETE").buildAndRegister();
    public static final Material RED_CONCRETE_POWDER = create("RED_CONCRETE_POWDER").buildAndRegister();
    public static final Material RED_GLAZED_TERRACOTTA = create("RED_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material RED_MUSHROOM = create("RED_MUSHROOM").buildAndRegister();
    public static final Material RED_MUSHROOM_BLOCK = create("RED_MUSHROOM_BLOCK").buildAndRegister();
    public static final Material RED_NETHER_BRICKS = create("RED_NETHER_BRICKS").buildAndRegister();
    public static final Material RED_SAND = create("RED_SAND").buildAndRegister();
    public static final Material RED_SANDSTONE = create("RED_SANDSTONE").buildAndRegister();
    public static final Material RED_SANDSTONE_SLAB = create("RED_SANDSTONE_SLAB").buildAndRegister();
    public static final Material RED_SANDSTONE_STAIRS = create("RED_SANDSTONE_STAIRS").buildAndRegister();
    public static final Material RED_SHULKER_BOX = create("RED_SHULKER_BOX").buildAndRegister();
    public static final Material RED_STAINED_GLASS = create("RED_STAINED_GLASS").buildAndRegister();
    public static final Material RED_STAINED_GLASS_PANE = create("RED_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material RED_TERRACOTTA = create("RED_TERRACOTTA").buildAndRegister();
    public static final Material RED_TULIP = create("RED_TULIP").buildAndRegister();
    public static final Material RED_WALL_BANNER = create("RED_WALL_BANNER").buildAndRegister();
    public static final Material RED_WOOL = create("RED_WOOL").buildAndRegister();
    public static final Material REPEATER = create("REPEATER").buildAndRegister();
    public static final Material REPEATING_COMMAND_BLOCK = create("REPEATING_COMMAND_BLOCK").buildAndRegister();
    public static final Material ROSE_BUSH = create("ROSE_BUSH").buildAndRegister();
    public static final Material ROSE_RED = create("ROSE_RED").buildAndRegister();
    public static final Material ROTTEN_FLESH = create("ROTTEN_FLESH").buildAndRegister();
    public static final Material SADDLE = create("SADDLE").buildAndRegister();
    public static final Material SALMON = create("SALMON").buildAndRegister();
    public static final Material SALMON_BUCKET = create("SALMON_BUCKET").buildAndRegister();
    public static final Material SALMON_SPAWN_EGG = create("SALMON_SPAWN_EGG").buildAndRegister();
    public static final Material SAND = create("SAND").buildAndRegister();
    public static final Material SANDSTONE = create("SANDSTONE").buildAndRegister();
    public static final Material SANDSTONE_SLAB = create("SANDSTONE_SLAB").buildAndRegister();
    public static final Material SANDSTONE_STAIRS = create("SANDSTONE_STAIRS").buildAndRegister();
    public static final Material SCUTE = create("SCUTE").buildAndRegister();
    public static final Material SEAGRASS = create("SEAGRASS").buildAndRegister();
    public static final Material SEA_LANTERN = create("SEA_LANTERN").buildAndRegister();
    public static final Material SEA_PICKLE = create("SEA_PICKLE").buildAndRegister();
    public static final Material SHEARS = create("SHEARS").buildAndRegister();
    public static final Material SHEEP_SPAWN_EGG = create("SHEEP_SPAWN_EGG").buildAndRegister();
    public static final Material SHIELD = create("SHIELD").buildAndRegister();
    public static final Material SHULKER_BOX = create("SHULKER_BOX").buildAndRegister();
    public static final Material SHULKER_SHELL = create("SHULKER_SHELL").buildAndRegister();
    public static final Material SHULKER_SPAWN_EGG = create("SHULKER_SPAWN_EGG").buildAndRegister();
    public static final Material SIGN = create("SIGN").buildAndRegister();
    public static final Material SILVERFISH_SPAWN_EGG = create("SILVERFISH_SPAWN_EGG").buildAndRegister();
    public static final Material SKELETON_HORSE_SPAWN_EGG = create("SKELETON_HORSE_SPAWN_EGG").buildAndRegister();
    public static final Material SKELETON_SKULL = create("SKELETON_SKULL").buildAndRegister();
    public static final Material SKELETON_SPAWN_EGG = create("SKELETON_SPAWN_EGG").buildAndRegister();
    public static final Material SKELETON_WALL_SKULL = create("SKELETON_WALL_SKULL").buildAndRegister();
    public static final Material SLIME_BALL = create("SLIME_BALL").buildAndRegister();
    public static final Material SLIME_BLOCK = create("SLIME_BLOCK").buildAndRegister();
    public static final Material SLIME_SPAWN_EGG = create("SLIME_SPAWN_EGG").buildAndRegister();
    public static final Material SMOOTH_QUARTZ = create("SMOOTH_QUARTZ").buildAndRegister();
    public static final Material SMOOTH_RED_SANDSTONE = create("SMOOTH_RED_SANDSTONE").buildAndRegister();
    public static final Material SMOOTH_SANDSTONE = create("SMOOTH_SANDSTONE").buildAndRegister();
    public static final Material SMOOTH_STONE = create("SMOOTH_STONE").buildAndRegister();
    public static final Material SNOW = create("SNOW").buildAndRegister();
    public static final Material SNOWBALL = create("SNOWBALL").buildAndRegister();
    public static final Material SNOW_BLOCK = create("SNOW_BLOCK").buildAndRegister();
    public static final Material SOUL_SAND = create("SOUL_SAND").buildAndRegister();
    public static final Material SPAWNER = create("SPAWNER").buildAndRegister();
    public static final Material SPECTRAL_ARROW = create("SPECTRAL_ARROW").buildAndRegister();
    public static final Material SPIDER_EYE = create("SPIDER_EYE").buildAndRegister();
    public static final Material SPIDER_SPAWN_EGG = create("SPIDER_SPAWN_EGG").buildAndRegister();
    public static final Material SPLASH_POTION = create("SPLASH_POTION").buildAndRegister();
    public static final Material SPONGE = create("SPONGE").buildAndRegister();
    public static final Material SPRUCE_BOAT = create("SPRUCE_BOAT").buildAndRegister();
    public static final Material SPRUCE_BUTTON = create("SPRUCE_BUTTON").buildAndRegister();
    public static final Material SPRUCE_DOOR = create("SPRUCE_DOOR").buildAndRegister();
    public static final Material SPRUCE_FENCE = create("SPRUCE_FENCE").buildAndRegister();
    public static final Material SPRUCE_FENCE_GATE = create("SPRUCE_FENCE_GATE").buildAndRegister();
    public static final Material SPRUCE_LEAVES = create("SPRUCE_LEAVES").buildAndRegister();
    public static final Material SPRUCE_LOG = create("SPRUCE_LOG").buildAndRegister();
    public static final Material SPRUCE_PLANKS = create("SPRUCE_PLANKS").buildAndRegister();
    public static final Material SPRUCE_PRESSURE_PLATE = create("SPRUCE_PRESSURE_PLATE").buildAndRegister();
    public static final Material SPRUCE_SAPLING = create("SPRUCE_SAPLING").buildAndRegister();
    public static final Material SPRUCE_SLAB = create("SPRUCE_SLAB").buildAndRegister();
    public static final Material SPRUCE_STAIRS = create("SPRUCE_STAIRS").buildAndRegister();
    public static final Material SPRUCE_TRAPDOOR = create("SPRUCE_TRAPDOOR").buildAndRegister();
    public static final Material SPRUCE_WOOD = create("SPRUCE_WOOD").buildAndRegister();
    public static final Material SQUID_SPAWN_EGG = create("SQUID_SPAWN_EGG").buildAndRegister();
    public static final Material STICK = create("STICK").buildAndRegister();
    public static final Material STICKY_PISTON = create("STICKY_PISTON").buildAndRegister();
    public static final Material STONE = create("STONE").buildAndRegister();
    public static final Material STONE_AXE = create("STONE_AXE").buildAndRegister();
    public static final Material STONE_BRICKS = create("STONE_BRICKS").buildAndRegister();
    public static final Material STONE_BRICK_SLAB = create("STONE_BRICK_SLAB").buildAndRegister();
    public static final Material STONE_BRICK_STAIRS = create("STONE_BRICK_STAIRS").buildAndRegister();
    public static final Material STONE_BUTTON = create("STONE_BUTTON").buildAndRegister();
    public static final Material STONE_HOE = create("STONE_HOE").buildAndRegister();
    public static final Material STONE_PICKAXE = create("STONE_PICKAXE").buildAndRegister();
    public static final Material STONE_PRESSURE_PLATE = create("STONE_PRESSURE_PLATE").buildAndRegister();
    public static final Material STONE_SHOVEL = create("STONE_SHOVEL").buildAndRegister();
    public static final Material STONE_SLAB = create("STONE_SLAB").buildAndRegister();
    public static final Material STONE_SWORD = create("STONE_SWORD").buildAndRegister();
    public static final Material STRAY_SPAWN_EGG = create("STRAY_SPAWN_EGG").buildAndRegister();
    public static final Material STRING = create("STRING").buildAndRegister();
    public static final Material STRIPPED_ACACIA_LOG = create("STRIPPED_ACACIA_LOG").buildAndRegister();
    public static final Material STRIPPED_ACACIA_WOOD = create("STRIPPED_ACACIA_WOOD").buildAndRegister();
    public static final Material STRIPPED_BIRCH_LOG = create("STRIPPED_BIRCH_LOG").buildAndRegister();
    public static final Material STRIPPED_BIRCH_WOOD = create("STRIPPED_BIRCH_WOOD").buildAndRegister();
    public static final Material STRIPPED_DARK_OAK_LOG = create("STRIPPED_DARK_OAK_LOG").buildAndRegister();
    public static final Material STRIPPED_DARK_OAK_WOOD = create("STRIPPED_DARK_OAK_WOOD").buildAndRegister();
    public static final Material STRIPPED_JUNGLE_LOG = create("STRIPPED_JUNGLE_LOG").buildAndRegister();
    public static final Material STRIPPED_JUNGLE_WOOD = create("STRIPPED_JUNGLE_WOOD").buildAndRegister();
    public static final Material STRIPPED_OAK_LOG = create("STRIPPED_OAK_LOG").buildAndRegister();
    public static final Material STRIPPED_OAK_WOOD = create("STRIPPED_OAK_WOOD").buildAndRegister();
    public static final Material STRIPPED_SPRUCE_LOG = create("STRIPPED_SPRUCE_LOG").buildAndRegister();
    public static final Material STRIPPED_SPRUCE_WOOD = create("STRIPPED_SPRUCE_WOOD").buildAndRegister();
    public static final Material STRUCTURE_BLOCK = create("STRUCTURE_BLOCK").buildAndRegister();
    public static final Material STRUCTURE_VOID = create("STRUCTURE_VOID").buildAndRegister();
    public static final Material SUGAR = create("SUGAR").buildAndRegister();
    public static final Material SUGAR_CANE = create("SUGAR_CANE").buildAndRegister();
    public static final Material SUNFLOWER = create("SUNFLOWER").buildAndRegister();
    public static final Material TALL_GRASS = create("TALL_GRASS").buildAndRegister();
    public static final Material TALL_SEAGRASS = create("TALL_SEAGRASS").buildAndRegister();
    public static final Material TERRACOTTA = create("TERRACOTTA").buildAndRegister();
    public static final Material TIPPED_ARROW = create("TIPPED_ARROW").buildAndRegister();
    public static final Material TNT = create("TNT").buildAndRegister();
    public static final Material TNT_MINECART = create("TNT_MINECART").buildAndRegister();
    public static final Material TORCH = create("TORCH").buildAndRegister();
    public static final Material TOTEM_OF_UNDYING = create("TOTEM_OF_UNDYING").buildAndRegister();
    public static final Material TRAPPED_CHEST = create("TRAPPED_CHEST").buildAndRegister();
    public static final Material TRIDENT = create("TRIDENT").buildAndRegister();
    public static final Material TRIPWIRE = create("TRIPWIRE").buildAndRegister();
    public static final Material TRIPWIRE_HOOK = create("TRIPWIRE_HOOK").buildAndRegister();
    public static final Material TROPICAL_FISH = create("TROPICAL_FISH").buildAndRegister();
    public static final Material TROPICAL_FISH_BUCKET = create("TROPICAL_FISH_BUCKET").buildAndRegister();
    public static final Material TROPICAL_FISH_SPAWN_EGG = create("TROPICAL_FISH_SPAWN_EGG").buildAndRegister();
    public static final Material TUBE_CORAL = create("TUBE_CORAL").buildAndRegister();
    public static final Material TUBE_CORAL_BLOCK = create("TUBE_CORAL_BLOCK").buildAndRegister();
    public static final Material TUBE_CORAL_FAN = create("TUBE_CORAL_FAN").buildAndRegister();
    public static final Material TUBE_CORAL_WALL_FAN = create("TUBE_CORAL_WALL_FAN").buildAndRegister();
    public static final Material TURTLE_EGG = create("TURTLE_EGG").buildAndRegister();
    public static final Material TURTLE_HELMET = create("TURTLE_HELMET").buildAndRegister();
    public static final Material TURTLE_SPAWN_EGG = create("TURTLE_SPAWN_EGG").buildAndRegister();
    public static final Material VEX_SPAWN_EGG = create("VEX_SPAWN_EGG").buildAndRegister();
    public static final Material VILLAGER_SPAWN_EGG = create("VILLAGER_SPAWN_EGG").buildAndRegister();
    public static final Material VINDICATOR_SPAWN_EGG = create("VINDICATOR_SPAWN_EGG").buildAndRegister();
    public static final Material VINE = create("VINE").buildAndRegister();
    public static final Material VOID_AIR = create("VOID_AIR").buildAndRegister();
    public static final Material WALL_SIGN = create("WALL_SIGN").buildAndRegister();
    public static final Material WALL_TORCH = create("WALL_TORCH").buildAndRegister();
    public static final Material WATER = create("WATER").buildAndRegister();
    public static final Material WATER_BUCKET = create("WATER_BUCKET").buildAndRegister();
    public static final Material WET_SPONGE = create("WET_SPONGE").buildAndRegister();
    public static final Material WHEAT = create("WHEAT").buildAndRegister();
    public static final Material WHEAT_SEEDS = create("WHEAT_SEEDS").buildAndRegister();
    public static final Material WHITE_BANNER = create("WHITE_BANNER").buildAndRegister();
    public static final Material WHITE_BED = create("WHITE_BED").buildAndRegister();
    public static final Material WHITE_CARPET = create("WHITE_CARPET").buildAndRegister();
    public static final Material WHITE_CONCRETE = create("WHITE_CONCRETE").buildAndRegister();
    public static final Material WHITE_CONCRETE_POWDER = create("WHITE_CONCRETE_POWDER").buildAndRegister();
    public static final Material WHITE_GLAZED_TERRACOTTA = create("WHITE_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material WHITE_SHULKER_BOX = create("WHITE_SHULKER_BOX").buildAndRegister();
    public static final Material WHITE_STAINED_GLASS = create("WHITE_STAINED_GLASS").buildAndRegister();
    public static final Material WHITE_STAINED_GLASS_PANE = create("WHITE_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material WHITE_TERRACOTTA = create("WHITE_TERRACOTTA").buildAndRegister();
    public static final Material WHITE_TULIP = create("WHITE_TULIP").buildAndRegister();
    public static final Material WHITE_WALL_BANNER = create("WHITE_WALL_BANNER").buildAndRegister();
    public static final Material WHITE_WOOL = create("WHITE_WOOL").buildAndRegister();
    public static final Material WITCH_SPAWN_EGG = create("WITCH_SPAWN_EGG").buildAndRegister();
    public static final Material WITHER_SKELETON_SKULL = create("WITHER_SKELETON_SKULL").buildAndRegister();
    public static final Material WITHER_SKELETON_SPAWN_EGG = create("WITHER_SKELETON_SPAWN_EGG").buildAndRegister();
    public static final Material WITHER_SKELETON_WALL_SKULL = create("WITHER_SKELETON_WALL_SKULL").buildAndRegister();
    public static final Material WOLF_SPAWN_EGG = create("WOLF_SPAWN_EGG").buildAndRegister();
    public static final Material WOODEN_AXE = create("WOODEN_AXE").buildAndRegister();
    public static final Material WOODEN_HOE = create("WOODEN_HOE").buildAndRegister();
    public static final Material WOODEN_PICKAXE = create("WOODEN_PICKAXE").buildAndRegister();
    public static final Material WOODEN_SHOVEL = create("WOODEN_SHOVEL").buildAndRegister();
    public static final Material WOODEN_SWORD = create("WOODEN_SWORD").buildAndRegister();
    public static final Material WRITABLE_BOOK = create("WRITABLE_BOOK").buildAndRegister();
    public static final Material WRITTEN_BOOK = create("WRITTEN_BOOK").buildAndRegister();
    public static final Material YELLOW_BANNER = create("YELLOW_BANNER").buildAndRegister();
    public static final Material YELLOW_BED = create("YELLOW_BED").buildAndRegister();
    public static final Material YELLOW_CARPET = create("YELLOW_CARPET").buildAndRegister();
    public static final Material YELLOW_CONCRETE = create("YELLOW_CONCRETE").buildAndRegister();
    public static final Material YELLOW_CONCRETE_POWDER = create("YELLOW_CONCRETE_POWDER").buildAndRegister();
    public static final Material YELLOW_GLAZED_TERRACOTTA = create("YELLOW_GLAZED_TERRACOTTA").buildAndRegister();
    public static final Material YELLOW_SHULKER_BOX = create("YELLOW_SHULKER_BOX").buildAndRegister();
    public static final Material YELLOW_STAINED_GLASS = create("YELLOW_STAINED_GLASS").buildAndRegister();
    public static final Material YELLOW_STAINED_GLASS_PANE = create("YELLOW_STAINED_GLASS_PANE").buildAndRegister();
    public static final Material YELLOW_TERRACOTTA = create("YELLOW_TERRACOTTA").buildAndRegister();
    public static final Material YELLOW_WALL_BANNER = create("YELLOW_WALL_BANNER").buildAndRegister();
    public static final Material YELLOW_WOOL = create("YELLOW_WOOL").buildAndRegister();
    public static final Material ZOMBIE_HEAD = create("ZOMBIE_HEAD").buildAndRegister();
    public static final Material ZOMBIE_HORSE_SPAWN_EGG = create("ZOMBIE_HORSE_SPAWN_EGG").buildAndRegister();
    public static final Material ZOMBIE_PIGMAN_SPAWN_EGG = create("ZOMBIE_PIGMAN_SPAWN_EGG").buildAndRegister();
    public static final Material ZOMBIE_SPAWN_EGG = create("ZOMBIE_SPAWN_EGG").buildAndRegister();
    public static final Material ZOMBIE_VILLAGER_SPAWN_EGG = create("ZOMBIE_VILLAGER_SPAWN_EGG").buildAndRegister();
    public static final Material ZOMBIE_WALL_HEAD = create("ZOMBIE_WALL_HEAD").buildAndRegister();


    private final ObjectOwner owner;
    private final String name, namespace, key;
    private final Class<?> materialClass;
    private final MaterialCategory[] categories;
    private final int maxStackSize;
    private final short maxDurability;
    private final float hardness, blastResistance;
    private final boolean empty, solid, transparent, flammable, burnable, fuel, occluding, gravity, interactable;

    public Material(ObjectOwner owner, String name, String namespace, String key, Class<?> materialClass, MaterialCategory[] categories
            , int maxStackSize, short maxDurability, float hardness, float blastResistance, boolean empty
            , boolean solid, boolean transparent, boolean flammable, boolean burnable, boolean fuel
            , boolean occluding, boolean gravity, boolean interactable) {
        this.owner = owner;
        this.name = name;
        this.namespace = namespace;
        this.key = key;
        this.materialClass = materialClass;
        this.categories = categories;
        this.maxStackSize = maxStackSize;
        this.maxDurability = maxDurability;
        this.hardness = hardness;
        this.blastResistance = blastResistance;
        this.empty = empty;
        this.solid = solid;
        this.transparent = transparent;
        this.flammable = flammable;
        this.burnable = burnable;
        this.fuel = fuel;
        this.occluding = occluding;
        this.gravity = gravity;
        this.interactable = interactable;
    }

    public ObjectOwner getOwner() {
        return owner;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public Class<?> getMaterialClass() {
        return materialClass;
    }

    public boolean hasCategory(MaterialCategory materialCategory) {
        if(materialCategory == null) return false;
        for (MaterialCategory category : this.categories) {
            if(category.equals(materialCategory)) return true;
        }
        return false;
    }

    public MaterialCategory[] getCategories() {
        return categories;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public short getMaxDurability() {
        return maxDurability;
    }

    public float getHardness() {
        return hardness;
    }

    public float getBlastResistance() {
        return blastResistance;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public boolean isFlammable() {
        return flammable;
    }

    public boolean isBurnable() {
        return burnable;
    }

    public boolean isFuel() {
        return fuel;
    }

    public boolean isOccluding() {
        return occluding;
    }

    public boolean hasGravity() {
        return gravity;
    }

    public boolean isInteractable() {
        return interactable;
    }

    private BlockData newBlockData(){
        return null;
    }

    private ItemData newItemData(){
        return null;
    }


    public static Builder create(String name){
        return new Builder(name);
    }

    public static Material register(Material material) {
        MATERIALS.add(material);
        return material;
    }

    private static class Builder {

        private ObjectOwner owner;
        private final String name;
        private String namespace, key;
        private Class<?> materialClass;
        private MaterialCategory[] categories;
        private int maxStackSize;
        private short maxDurability;
        private float hardness, blastResistance;
        private boolean empty, solid, transparent, flammable, burnable, fuel, occluding, gravity, interactable;

        public Builder(String name) {
            this.owner = McNative.getInstance();
            this.name = name;
            this.namespace = NamespacedKey.MCNATIVE;
            this.key = name;
            this.maxStackSize = 64;
            this.maxDurability = -1;
            this.hardness = 0.0f;
            this.blastResistance = 0.0f;
        }

        public Builder owner(ObjectOwner owner) {
            this.owner = owner;
            return this;
        }

        public Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder materialClass(Class<?> materialClass) {
            this.materialClass = materialClass;
            return this;
        }

        public Builder categories(MaterialCategory... categories) {
            this.categories = categories;
            return this;
        }

        public Builder maxStackSize(int maxStackSize) {
            this.maxStackSize = maxStackSize;
            return this;
        }

        public Builder maxDurability(short maxDurability) {
            this.maxDurability = maxDurability;
            return this;
        }

        public Builder hardness(float hardness) {
            this.hardness = hardness;
            return this;
        }

        public Builder blastResistance(float blastResistance) {
            this.blastResistance = blastResistance;
            return this;
        }

        public Builder empty(boolean empty) {
            this.empty = empty;
            return this;
        }

        public Builder solid(boolean solid) {
            this.solid = solid;
            return this;
        }

        public Builder transparent(boolean transparent) {
            this.transparent = transparent;
            return this;
        }

        public Builder flammable(boolean flammable) {
            this.flammable = flammable;
            return this;
        }

        public Builder burnable(boolean burnable) {
            this.burnable = burnable;
            return this;
        }

        public Builder fuel(boolean fuel) {
            this.fuel = fuel;
            return this;
        }

        public Builder occluding(boolean occluding) {
            this.occluding = occluding;
            return this;
        }

        public Builder gravity(boolean gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder interactable(boolean interactable) {
            this.interactable = interactable;
            return this;
        }

        public Material build() {
            return new Material(owner, name, namespace, key, materialClass, categories, maxStackSize, maxDurability, hardness, blastResistance, empty, solid, transparent, flammable, burnable, fuel, occluding, gravity, interactable);
        }

        public Material buildAndRegister() {
            return register(build());
        }
    }
}
