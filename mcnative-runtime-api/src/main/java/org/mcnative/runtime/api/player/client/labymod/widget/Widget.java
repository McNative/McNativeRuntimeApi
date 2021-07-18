package org.mcnative.runtime.api.player.client.labymod.widget;

public interface Widget<T extends Widget<T>> {

    String getAlias();


    Anchor getAnchor();

    T setAnchor(Anchor anchor);


    double getOffsetX();

    T setOffsetX(double offsetX);


    double getOffsetY();

    T setOffsetY(double offsetY);
}
