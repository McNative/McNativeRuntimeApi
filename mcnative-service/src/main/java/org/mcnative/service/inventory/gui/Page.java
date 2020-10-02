package org.mcnative.service.inventory.gui;

import org.mcnative.service.inventory.gui.components.FormComponent;

public class Page implements ComponentHolder{

    private final String name;
    private final int height;

    public Page(String name,int height) {
        this.name = name;
        this.height = height;
    }


    @Override
    public <F extends FormComponent> F addComponent(F component) {
        return null;
    }
}
