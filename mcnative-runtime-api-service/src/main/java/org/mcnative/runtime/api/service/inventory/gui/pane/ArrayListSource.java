package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.stream.StreamOptional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayListSource<V> extends ArrayList<V> implements ListSource<V>{

    public ArrayListSource(int initialCapacity) {
        super(initialCapacity);
    }

    public ArrayListSource() {
    }

    public ArrayListSource(Collection<? extends V> c) {
        super(c);
    }

    @Override
    public StreamOptional<List<V>> get() {
        return null;
    }

    @Override
    public V getItem(int index) {
        return get(index);
    }
}
