/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 12:28
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

package org.mcnative.service.world;

import net.pretronic.libraries.utility.Iterators;

import java.util.HashSet;
import java.util.Set;

public class TreeType {

    private static final Set<TreeType> TREE_TYPES = new HashSet<>();

    public static final TreeType TREE = registerTreeType("TREE");
    public static final TreeType BIG_TREE = registerTreeType("BIG_TREE");
    public static final TreeType REDWOOD = registerTreeType("REDWOOD");
    public static final TreeType TALL_REDWOOD = registerTreeType("TALL_REDWOOD");
    public static final TreeType BIRCH = registerTreeType("BIRCH");
    public static final TreeType JUNGLE = registerTreeType("JUNGLE");
    public static final TreeType SMALL_JUNGLE = registerTreeType("SMALL_JUNGLE");
    public static final TreeType COCOA_TREE = registerTreeType("COCOA_TREE");
    public static final TreeType JUNGLE_BUSH = registerTreeType("JUNGLE_BUSH");
    public static final TreeType RED_MUSHROOM = registerTreeType("RED_MUSHROOM");
    public static final TreeType BROWN_MUSHROOM = registerTreeType("BROWN_MUSHROOM");
    public static final TreeType SWAMP = registerTreeType("SWAMP");
    public static final TreeType ACACIA = registerTreeType("ACACIA");
    public static final TreeType DARK_OAK = registerTreeType("DARK_OAK");
    public static final TreeType MEGA_REDWOOD = registerTreeType("MEGA_REDWOOD");
    public static final TreeType TALL_BIRCH = registerTreeType("TALL_BIRCH");
    public static final TreeType CHORUS_PLANT = registerTreeType("CHORUS_PLANT");


    private final String name;

    private TreeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public static TreeType getTreeType(String name) {
        TreeType treeType = Iterators.findOne(TREE_TYPES, treeType0 -> treeType0.getName().equals(name));
        if(treeType == null) throw new IllegalArgumentException("Tree type with name " + name + " does't exist");
        return treeType;
    }

    public static TreeType registerTreeType(String name) {
        TreeType treeType = new TreeType(name);
        TREE_TYPES.add(treeType);
        return treeType;
    }

    public static void unregisterTreeType(TreeType treeType) {
        TREE_TYPES.remove(treeType);
    }

    public static void unregisterTreeType(String name) {
        unregisterTreeType(getTreeType(name));
    }
}
