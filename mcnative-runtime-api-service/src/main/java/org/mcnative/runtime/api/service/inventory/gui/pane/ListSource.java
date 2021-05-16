package org.mcnative.runtime.api.service.inventory.gui.pane;

import java.util.List;

public interface ListSource<V> extends Iterable<V> {

    List<V> get();

    V getItem(int index);

}
