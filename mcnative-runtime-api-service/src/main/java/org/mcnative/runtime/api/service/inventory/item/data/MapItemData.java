package org.mcnative.runtime.api.service.inventory.item.data;

import org.mcnative.runtime.api.service.map.MapView;

import java.awt.*;

public interface MapItemData extends ItemData {

    Color getColor();

    MapItemData setColor(Color color);


    String getLocationName();

    MapItemData setLocationName(String name);


    MapView getMapView();

    MapItemData setMapView(MapView view);


    boolean isScaling(boolean scaling);

    MapItemData setScaling(boolean scaling);
}
