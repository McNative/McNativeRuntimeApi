package org.mcnative.runtime.api.service.inventory.item.data.firework;


import java.awt.*;
import java.util.List;

public interface FireworkEffect {

    List<Color> getColors();

    FireworkEffect setColors(List<Color> colors);

    FireworkEffect addColor(Color color);

    List<Color> setColor(int index, Color color);


    List<Color> getFadeColors();

    FireworkEffect setFadeColors(List<Color> colors);

    FireworkEffect addFadeColor(Color color);

    List<Color> setFadeColor(int index, Color color);


    FireworkEffectType getType();

    FireworkEffect setType(FireworkEffectType type);


    boolean hasFlicker();

    FireworkEffect setFlicker(boolean flicker);


    boolean hasTrail();

    FireworkEffect setTrail(boolean trail);
}
