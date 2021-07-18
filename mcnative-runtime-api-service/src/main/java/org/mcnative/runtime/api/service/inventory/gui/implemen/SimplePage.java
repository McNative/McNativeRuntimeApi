package org.mcnative.runtime.api.service.inventory.gui.implemen;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

public class SimplePage<C extends GuiContext> implements Page<C> {

    private final String name;
    private final String screenName;

    public SimplePage(String name, String screenName) {
        this.name = name;
        this.screenName = screenName;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String open(C context) {
        return screenName;
    }
}
