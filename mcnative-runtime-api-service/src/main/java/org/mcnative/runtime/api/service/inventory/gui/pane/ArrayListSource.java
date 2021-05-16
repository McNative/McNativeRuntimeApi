package org.mcnative.runtime.api.service.inventory.gui.pane;

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
    public List<V> get() {
        return this;
    }

    @Override
    public V getItem(int index) {
        return get(index);
    }
}
