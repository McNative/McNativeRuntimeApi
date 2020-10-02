package org.mcnative.service.inventory.gui;

import org.mcnative.service.inventory.gui.components.FormComponent;

public interface ComponentHolder {

    <F extends FormComponent> F addComponent(F component);

}
