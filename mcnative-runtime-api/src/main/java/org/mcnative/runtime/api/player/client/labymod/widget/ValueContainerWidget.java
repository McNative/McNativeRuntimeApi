package org.mcnative.runtime.api.player.client.labymod.widget;

public interface ValueContainerWidget<T extends ValueContainerWidget<T>> extends ContainerWidget<T> {

    String getValue();

    T setValue(String value);
}
