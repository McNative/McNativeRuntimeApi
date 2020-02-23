/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 20.02.20, 21:12
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

package org.mcnative.bukkit.player;

import net.prematic.libraries.utility.reflect.ReflectionUtil;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.mcnative.bukkit.utils.BukkitReflectionUtil;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.serviceprovider.permission.PermissionResult;

import java.lang.reflect.Field;
import java.util.Set;

//@Todo implement permission attachment bridge
/*
@Todo create glowstone implementation
 Class.forName("net.glowstone.entity.GlowHumanEntity").getDeclaredField("permissions");
 */
public class McNativePermissible extends PermissibleBase {

    private final static Class<?> CRAFT_HUMAN_ENTITY_CLASS = BukkitReflectionUtil.getCraftClass("entity.CraftHumanEntity");
    private final static Field CRAFT_HUMAN_ENTITY_PERMISSIBLE_FIELD = ReflectionUtil.getField(CRAFT_HUMAN_ENTITY_CLASS,"perm");

    private final Player bukkitPlayer;
    private final MinecraftPlayer player;

    static {
        CRAFT_HUMAN_ENTITY_PERMISSIBLE_FIELD.setAccessible(true);
    }

    public McNativePermissible(Player bukkitPlayer,MinecraftPlayer player) {
        super(bukkitPlayer);
        this.bukkitPlayer = bukkitPlayer;
        this.player = player;
        clearPermissions();
    }

    @Override
    public boolean isOp() {
        if(player == null) return false;
        return player.getPermissionHandler().isOperator();
    }

    @Override
    public void setOp(boolean operator) {
        player.getPermissionHandler().setOperator(operator);
    }

    @Override
    public boolean isPermissionSet(String permission) {
        if(player.getPermissionHandler().isPermissionSet(permission)) return true;
        return super.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        PermissionResult result = player.getPermissionHandler().hasPermissionExact(permission);
        if(result == PermissionResult.NORMAL) return super.hasPermission(permission);
        return result == PermissionResult.ALLOWED;
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {//@Todo optimize
        Set<PermissionAttachmentInfo> result = super.getEffectivePermissions();
        for (String permission : player.getPermissionHandler().getEffectivePermissions()) {
            result.add(new PermissionAttachmentInfo(this,permission,null,true));
        }
        return result;
    }

    public void inject(){
        try {
            CRAFT_HUMAN_ENTITY_PERMISSIBLE_FIELD.set(bukkitPlayer,this);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to inject McNative Permission Bridge");
        }
    }
}
