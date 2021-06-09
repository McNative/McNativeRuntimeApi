package org.mcnative.runtime.api.player.client.labymod.widget;

public interface ContainerWidget<T extends ContainerWidget<T>> extends Widget<T> {

    int getWidth();

    T setWidth(int width);


    int getHeight();

    T setHeight(int height);
}
