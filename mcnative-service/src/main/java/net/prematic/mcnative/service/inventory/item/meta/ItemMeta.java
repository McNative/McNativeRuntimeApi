/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.07.19 14:54
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

package net.prematic.mcnative.service.inventory.item.meta;

import net.prematic.mcnative.service.NBTTag;

import java.util.List;

public interface ItemMeta {

    String getDisplayName();

    NBTTag getTag();

    List<String> getLore();

    List<ItemFlag> getFlags();

    boolean hasDisplayName();

    boolean hasTag();

    boolean hasLore();

    boolean hasFlag(ItemFlag flag);

    default boolean isUnbreakable(){
        return hasTag() && getTag().getBoolean("unbreakable");
    }

    void setDisplayName(String name);

    void setTag(NBTTag tag);

    void setLore(List<String> lore);

    void setLore(String... lore);

    void setLore(int index, String lore);

    void addLore(List<String> lore);

    void addLore(String... lore);

    void addLore(String lore);

    void setFlags(ItemFlag... flags);

    void addFlags(ItemFlag... flags);

    default void setUnbreakable(boolean unbreakable){
        NBTTag tag;
        if(hasTag()) tag = getTag();
        else{
            tag = NBTTag.newTag();
            setTag(tag);
        }
        tag.setBoolean("unbreakable",unbreakable);
    }
}
