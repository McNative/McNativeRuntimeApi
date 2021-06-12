package org.mcnative.runtime.api.player.client.labymod.widget;

import org.mcnative.runtime.api.McNative;

public interface Anchor {

    double getX();

    double getY();


    static Anchor of(double x, double y) {
        return McNative.getInstance().getObjectFactory().createObject(Anchor.class, x, y);
    }
}
