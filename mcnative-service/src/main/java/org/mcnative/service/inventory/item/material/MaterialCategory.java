/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 14.09.19, 22:48
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

public class MaterialCategory {

    public static final MaterialCategory BLOCK = createDefault("BLOCK");

    public static final MaterialCategory FOOD = createDefault("FOOD");
    public static final MaterialCategory RECORD = createDefault("RECORD");
    public static final MaterialCategory ARMOR_HEAD = createDefault("ARMOR_HEAD");
    public static final MaterialCategory ARMOR_CHEST = createDefault("ARMOR_CHEST");
    public static final MaterialCategory ARMOR_LEGS = createDefault("ARMOR_LEGS");
    public static final MaterialCategory ARMOR_FEET = createDefault("ARMOR_FEET");
    public static final MaterialCategory ARMOR = createDefault("ARMOR", ARMOR_HEAD, ARMOR_CHEST, ARMOR_LEGS, ARMOR_FEET);
    public static final MaterialCategory TOOL = createDefault("TOOL");
    public static final MaterialCategory WEAPON = createDefault("WEAPON");
    public static final MaterialCategory WEARABLE = createDefault("WEARABLE");
    public static final MaterialCategory ITEM = createDefault("ITEM", FOOD, RECORD, ARMOR, TOOL, WEAPON, WEARABLE);


    private final String name;
    private final MaterialCategory[] childs;

    public MaterialCategory(String name, MaterialCategory... childs) {
        this.name = name;
        this.childs = childs;
    }

    public String getName() {
        return name;
    }

    public boolean hasChild(MaterialCategory materialCategory) {
        if(this.childs == null) return false;
        for (MaterialCategory child : this.childs) {
            if(child.equals(materialCategory)) return true;
        }
        return false;
    }

    public MaterialCategory[] getChilds() {
        return childs;
    }

    private static MaterialCategory createDefault(String name, MaterialCategory... childs) {
        return new MaterialCategory(name, childs);
    }

    private static MaterialCategory createDefault(String name) {
        return createDefault(name, new MaterialCategory[0]);
    }
}