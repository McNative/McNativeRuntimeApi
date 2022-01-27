package org.mcnative.runtime.api.service.inventory.item;

import net.pretronic.libraries.utility.Validate;

public enum DyeColor {

    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    LIGHT_GRAY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK;

    public static DyeColor parse(String name) {
        Validate.notNull(name);
        for (DyeColor value : values()) {
            if(value.toString().equalsIgnoreCase(name)) return value;
        }
        throw new IllegalArgumentException("DyeColor with name " + name + " doesn't exist");
    }
}
