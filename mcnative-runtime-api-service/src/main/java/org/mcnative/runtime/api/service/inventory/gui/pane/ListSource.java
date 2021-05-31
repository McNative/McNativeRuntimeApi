package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.stream.StreamOptional;

import java.util.List;

public interface ListSource<V> extends Iterable<V> {

    StreamOptional<List<V>> get();

    V getItem(int index);

}
