package org.mcnative.service.inventory.gui.components.controls.listview;

import java.util.List;

public interface ItemProvider<T> {

    List<T> getItems(int page, int amount);

}
