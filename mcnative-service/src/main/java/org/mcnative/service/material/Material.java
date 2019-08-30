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

package org.mcnative.service.material;

import org.mcnative.service.inventory.item.data.ItemData;
import org.mcnative.service.world.block.data.BlockData;

public class Material {

    public static final Material AIR = createDefault("Air");

    public static final Material STONE = createDefault("Stone");


    private final String name;
    private final Class<?> materialClass;

    public Material(String name) {
        this(name,Material.class);
    }

    public Material(String name,Class<?> materialClass) {
        this.name = name;
        this.materialClass = materialClass;
    }

    public String getName() {
        return name;
    }

    private BlockData newBlockData(){
        return null;
    }

    private ItemData newItemData(){
        return null;
    }



    /*

        Extra data


     */


    private static Material createDefault(String name){
        return createDefault(name,Material.class);
    }

    private static Material createDefault(String name, Class<?> materialClass){
        return new Material(name, materialClass);
    }

}
