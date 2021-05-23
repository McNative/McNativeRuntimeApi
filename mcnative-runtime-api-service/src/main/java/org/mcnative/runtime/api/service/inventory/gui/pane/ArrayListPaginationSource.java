package org.mcnative.runtime.api.service.inventory.gui.pane;

import org.mcnative.runtime.api.stream.StreamController;
import org.mcnative.runtime.api.stream.StreamOptional;

import java.util.*;

public class ArrayListPaginationSource<V> extends ArrayList<V> implements ListSource<V>{

    private final List<V> source;
    private final int pageSize;
    private int page;
    private StreamController<List<V>> controller;

    public ArrayListPaginationSource(List<V> source, int pageSize) {
        this.source = source;
        this.pageSize = pageSize;
        page = 0;

        int end = pageSize;
        if(end >= source.size()) end = source.size();
        controller = new StreamController<>(source.subList(0,end));
    }

    @Override
    public StreamOptional<List<V>> get() {
        return controller;
    }

    @Override
    public V getItem(int index0) {
        int index = page*pageSize+index0;
        if(index >= source.size()) return null;
        return source.get(index);
    }

    public int getPage() {
        return page;
    }

    public boolean nextPage(){
        int newPage = page+1;
        boolean success = calculate(newPage);
        if(success)this.page = newPage;
        return success;
    }

    public boolean previousPage(){
        int newPage = page-1;
        if(newPage < 0) return false;
        boolean success = calculate(newPage);
        if(success) this.page = newPage;
        return success;
    }

    private boolean calculate(int newPage){
        int start = newPage*pageSize;
        if(start >= source.size()-1) return false;
        else {
            int end = start+pageSize;
            if(end >= source.size()) end = source.size();
            controller.setValue(source.subList(start,end));
            return true;
        }
    }
}
