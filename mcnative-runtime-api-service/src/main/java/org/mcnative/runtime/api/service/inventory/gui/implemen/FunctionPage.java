package org.mcnative.runtime.api.service.inventory.gui.implemen;

import org.mcnative.runtime.api.service.inventory.gui.Page;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;

import java.util.function.Function;

public class FunctionPage<C extends GuiContext> implements Page<C> {

    private final String name;
    private final Function<C, String> screenGetter;

    public FunctionPage(String name, Function<C, String> screenGetter) {
        this.name = name;
        this.screenGetter = screenGetter;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String open(C context) {
        return this.screenGetter.apply(context);
    }
}
