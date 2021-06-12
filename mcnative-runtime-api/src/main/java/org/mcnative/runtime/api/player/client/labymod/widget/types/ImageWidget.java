package org.mcnative.runtime.api.player.client.labymod.widget.types;

import org.mcnative.runtime.api.player.client.labymod.widget.ContainerWidget;

public interface ImageWidget extends ContainerWidget<ImageWidget> {

    int getCutX();

    ImageWidget setCutX(int x);


    int getCutY();

    ImageWidget setCutY(int y);


    int getCutWidth();

    ImageWidget setCutWidth(int width);


    int getCutHeight();

    ImageWidget setCutHeight(int height);


    String getUrl();

    ImageWidget setUrl(String url);
}
